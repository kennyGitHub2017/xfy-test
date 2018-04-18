package com.xuanfeiyang.erp.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.App;
import com.xuanfeiyang.erp.dao.GoodsDao;
import com.xuanfeiyang.erp.dao.IoOrderDao;
import com.xuanfeiyang.erp.dao.IoOrderItemDao;
import com.xuanfeiyang.erp.dao.StoreShelfDao;
import com.xuanfeiyang.erp.domain.Goods;
import com.xuanfeiyang.erp.domain.GoodsInventoryCost;
import com.xuanfeiyang.erp.domain.IoOrder;
import com.xuanfeiyang.erp.domain.IoOrderItem;
import com.xuanfeiyang.erp.domain.PurchaseOrderStatistic;
import com.xuanfeiyang.erp.domain.StoreShelf;
import com.xuanfeiyang.erp.param.IoOrderParam;
import com.xuanfeiyang.erp.param.SupplierScoreParam;
import com.xuanfeiyang.erp.service.FileImportException;
import com.xuanfeiyang.erp.service.GoodsInventoryService;
import com.xuanfeiyang.erp.service.InventoryShortageException;
import com.xuanfeiyang.erp.service.IoOrderService;
import com.xuanfeiyang.erp.service.TableKeyService;
import com.xuanfeiyang.erp.service.XxNoGenerateService;
import com.xuanfeiyang.erp.service.XxNoType;
import com.xuanfeiyang.erp.util.ExcelParser;

@Service
public class IoOrderServiceImpl implements IoOrderService {
	private static Logger logger = LoggerFactory.getLogger(IoOrderService.class);
	@Resource
	private IoOrderDao ioOrderDao;
	
	@Resource
	private IoOrderItemDao ioOrderItemDao;
	
	@Resource
	private GoodsDao goodsDao;
	
	@Resource
	private XxNoGenerateService xxNoGenerateService;
	
	@Resource
	private TableKeyService tableKeyService; 
	
	@Resource
	private GoodsInventoryService goodsInventoryService;
	
	@Resource
	private StoreShelfDao storeShelfDao;
	
	private DateFormat dft = new SimpleDateFormat("yyyyMMdd");

	@Override
	public List<IoOrderItem> supplierScore(SupplierScoreParam param) {
		return ioOrderItemDao.supplierScore(param);
	}

	@Override
	public List<IoOrderItem> detail(String orderNo) {
		return ioOrderItemDao.detail(orderNo);
	}
	
	@Override
	public Page<IoOrder> findPage(PageRequest pageRequest, IoOrderParam param) {
		
		return ioOrderDao.findIoOrder(pageRequest, param);
	}
	
	@Override
	public PurchaseOrderStatistic purchaseOrderStatistic(IoOrderParam param) {
		return ioOrderDao.purchaseOrderStatistic(param);
	}

	private void checkOrder(IoOrder order) {
		if (order == null) {
			throw new IllegalArgumentException("单据对象为空");
		}
		
		if (order.getType() == null || ! ( order.getType().equals(0) || order.getType().equals(1))) {
			throw new IllegalArgumentException("单据类型非法，可选值：0, 1");
		}
		
		if (order.getItems() == null || order.getItems().isEmpty()) {
			throw new IllegalArgumentException("单据至少应有一行");
		}
		
		for (IoOrderItem item : order.getItems()) {
			if (item.getGoodsSku() == null || item.getGoodsCost() == null 
					|| item.getQualifiedCount() == null) {
				throw new IllegalArgumentException("缺少必须项全部或其一：SKU, 成本价, 数量");
			}
		}
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void save(IoOrder order) throws InventoryShortageException {
		this.checkOrder(order);
		
		if (order.getOrderNo() == null) {
			order.setOrderNo(this.xxNoGenerateService.generate(order.getType().equals(0) ? XxNoType.QR : XxNoType.QC));
		}
		
		Date now = new Date();
		order.setCreatedTime(now);
		order.setLastUpdatedTime(now);
		
		for (IoOrderItem item : order.getItems()) {
			item.setOrderNo(order.getOrderNo());
			item.setLastUpdatedTime(now);
			this.ioOrderItemDao.add(item);
		}
		
		this.ioOrderDao.add(order);
		
		// 如果单是已审核单据，需要处理库存和计算成本
		if (order.getAuditStatus().equals(1)) {
			this.updateInventoryAndCalcCost(order);
		}
	}
	
	private void updateInventoryAndCalcCost(IoOrder order) {
		// 处理入库
		if (order.getType().equals(0)) {
			for (IoOrderItem item : order.getItems()) {
				this.updateInvetoryAndCalcCostForIn(item);
			}
		} 
		// 处理出库
		else if (order.getType().equals(1)) {
			for (IoOrderItem item : order.getItems()) {
				this.updateInvetoryAndCalcCostForOut(item);
			}
		}
	}
	
	// 入库时更新库存和计算成本
	private void updateInvetoryAndCalcCostForIn(IoOrderItem item) {
		// 合格产品入库
		this.goodsInventoryService.increase(item.getGoodsSku(), item.getStoreId(), item.getStoreShelfId(), item.getQualifiedCount());
		// 不合格产品入库
		if (item.getUnqualifiedCount() != null && item.getUnqualifiedCount() > 0) {
			this.goodsInventoryService.increase(item.getGoodsSku(), App.getConfigInt("unqualified.store.id"), item.getUnqualifiedShelfId(), item.getUnqualifiedCount());
		}
		
		// 入库数量为合格和不合格的总和
		Integer count = item.getQualifiedCount() + (item.getUnqualifiedCount() == null ? 0 : item.getUnqualifiedCount());
		GoodsInventoryCost cost = this.goodsInventoryService.updateCostWhenIncrease(item.getGoodsSku(), count, item.getGoodsCost());

		this.updateItemCost(item.getId(), cost);
	}
	
	// 出库时更新库存和计算成本
	private void updateInvetoryAndCalcCostForOut(IoOrderItem item) throws InventoryShortageException {
		// 合格产品你出库
		this.goodsInventoryService.decrease(item.getGoodsSku(), item.getStoreId(), item.getStoreShelfId(), item.getQualifiedCount());
		// 不合格产品出库
		if (item.getUnqualifiedCount() != null && item.getUnqualifiedCount() > 0) {
			this.goodsInventoryService.decrease(item.getGoodsSku(), App.getConfigInt("unqualified.store.id"), item.getUnqualifiedShelfId(), item.getUnqualifiedCount());
		}
		
		// 出库数量
		Integer count = item.getQualifiedCount();
		GoodsInventoryCost cost = this.goodsInventoryService.updateCostWhenDecrease(item.getGoodsSku(), count);
		
		this.updateItemCost(item.getId(), cost);
	}
	
	private void updateItemCost(Integer itemId, GoodsInventoryCost cost) {
		IoOrderItem tmp = new IoOrderItem();
		tmp.setId(itemId);
		tmp.setStatAmount(cost.getAmount());
		tmp.setStatCount(cost.getCount());
		tmp.setStatPrice(cost.getPrice());
		tmp.setLastUpdatedTime(new Date());
		
		this.ioOrderItemDao.update(tmp);
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void update(IoOrder order) {
		if (order == null || order.getType() == null
				|| order.getOrderNo() == null
				|| order.getItems() == null 
				|| order.getItems().isEmpty()) {
			return;
		}
		
		// 先删除所有的
		this.ioOrderItemDao.deleteByOrderNo(order.getOrderNo());
		
		Date now = new Date();
		order.setLastUpdatedTime(now);
		
		for (IoOrderItem item : order.getItems()) {
			item.setOrderNo(order.getOrderNo());
			item.setLastUpdatedTime(now);
			this.ioOrderItemDao.add(item);
		}
		
		this.ioOrderDao.update(order);
	}

	@Override
	public IoOrder load(Integer id) {
		return id == null ? null : this.ioOrderDao.load(id);
	}

	@Override
	@Transactional
	public Integer addOrder(IoOrder ioOrder, List<IoOrderItem> items) {
		this.ioOrderDao.add(ioOrder);
		if (null!=items && items.size()>0){
			for(IoOrderItem item:items){
				if (null==item.getOrderNo() || "".equals(item.getOrderNo())){
					item.setOrderNo(ioOrder.getOrderNo());
				}
				this.ioOrderItemDao.add(item);
			}
		}
		return ioOrder.getId();
	}

	@Override
	public boolean addOrderItem(IoOrderItem ioOrderItem) {
		
		int result = ioOrderItemDao.add(ioOrderItem);
		if(result > 0){
			return true;
		}else{
			return false;
		}
	}
	
	@Override
	public List<IoOrder> getOrderByPurchaseOrderNo(String purchaseOrderNo) {
		return ioOrderDao.getOrderByPurchaseOrderNo(purchaseOrderNo);
	}
	
	@Override
	public Page<IoOrderItem> goodsIoItem(PageRequest pageRequest,
			IoOrderItem ioOrderItem) {
		return this.ioOrderItemDao.goodsIoItem(pageRequest,ioOrderItem);
		 
	}
	
	
	@Override
	public PurchaseOrderStatistic statisticIoItem(IoOrderItem param) {
		return this.ioOrderItemDao.statisticIoItem(param);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void approve(Integer id, Integer userId) {
		if (id == null) {
			 return;
		}
		
		// 如果ID不存在 或 此单已经是已审核订单，不做任何处理
		IoOrder tmp = this.ioOrderDao.load(id);
		if (tmp == null || tmp.getAuditStatus().equals(1)) {
			return;
		}
		
		IoOrder ior = ioOrderDao.load(id);
		
		IoOrder order = new IoOrder();
		order.setId(id);
		order.setAuditStatus(1);
		String dateStr = dft.format(Calendar.getInstance().getTime());
		String fmt =String.format("LS-%s-",dateStr)+"%d";
		if (ior.getType()==0){
			order.setSerialNumber(this.tableKeyService.nextSerialNumber("i_orders",fmt));
		}else if (ior.getType()==1){
			order.setSerialNumber(this.tableKeyService.nextSerialNumber("o_orders",fmt));
		}
		order.setAuditUserId(userId);
		order.setAuditTime(new Date());
		
		this.ioOrderDao.update(order);
		
		// 审核通过后需处理库存和计算成本
		this.updateInventoryAndCalcCost(tmp);
	}



	@Override
	@Transactional(rollbackFor=Exception.class)
	public void delete(Integer id) {
		if (id == null) {
			return;
		}
		
		IoOrder order = this.ioOrderDao.load(id);
		// 审核通过的不能删除
		if (order == null || order.getAuditStatus().equals(1)) {
			return;
		}
		
		// 删除单头
		this.ioOrderDao.delete(id);
		// 删除单行
		this.ioOrderItemDao.deleteByOrderNo(order.getOrderNo());
	}
	
	
	@Transactional(rollbackFor=Exception.class)
	public void importIoOrder(MultipartFile file, IoOrder ioOrder)
			throws FileImportException {
		
		InputStream is = null;
		try {
			is = file.getInputStream();
		} catch (IOException e) {
			logger.error("获取上传文件出错", e);
		}
		
		if (is == null) {
			return;
		}

		String filename = file.getOriginalFilename();
		String fileExtension = FilenameUtils.getExtension(filename);
		
		List<String[]> data = ExcelParser.parseToString(is, fileExtension);
		if (data == null) {
			return;
		}
		
		Integer orderType = ioOrder.getType();
		
		String thisIoOrder = this.xxNoGenerateService.generate(orderType.equals(0) ? XxNoType.QR : XxNoType.QC);
		
		IoOrder ioOrder1 = new IoOrder();
		ioOrder1.setOrderNo(thisIoOrder);//出入单号码
		ioOrder1.setCreatedUserId(null);//制单人
		ioOrder1.setStoreId(142448);//合格仓
		ioOrder1.setCreatedTime(new Date());
		ioOrder1.setLastUpdatedTime(new Date());
		ioOrder1.setAuditStatus(0); //未审核
		ioOrder1.setType(orderType);//出入库
		ioOrder1.setCreatedUserId(ioOrder.getCreatedUserId());//制单人
		
		ioOrder1.setTypeDetail(ioOrder.getTypeDetail());//小类
		String dateStr = new SimpleDateFormat("yyyy-MM-dd hh:mm").format(new Date());
		ioOrder.setNote(dateStr);
		this.ioOrderDao.add(ioOrder1);
		
		for (int i = 2; i < data.size(); i++) {
			String[] rowData = data.get(i);
			rowData[0] = StringUtils.trimToNull(rowData[0]);
			if (rowData[0] == null) {
				continue;
			}
			
			IoOrderItem ioOrderItem = new IoOrderItem();
			ioOrderItem.setOrderNo(thisIoOrder);
			
			ioOrderItem.setGoodsSku(rowData[0]);//SKU
			Goods g1 = this.returnGoods(rowData[0], i, 0, rowData[0]);
			
			ioOrderItem.setGoodsName(g1.getName());//产品名
			
			ioOrderItem.setQualifiedCount(Integer.valueOf(rowData[1]));//数量

			StoreShelf ss = this.getShelfCode(rowData[2]);
			
			ioOrderItem.setStoreId(ss.getStoreId());
			ioOrderItem.setStoreShelfId(ss.getId());//货位号码
			
			ioOrderItem.setLastUpdatedTime(new Date());
			//ioOrderItem.setGoodsCost(g1.getCost());//采购价
			ioOrderItem.setGoodsCost(this.convertToBigDecimal(rowData[3], i, 0));//采购价
			this.ioOrderItemDao.add(ioOrderItem);
			
		}
		
	}

	private Goods returnGoods(String sku,int lineNo,int columnNo,String code) throws FileImportException{
		Goods res = this.goodsDao.findBySku(sku);
		if(res == null){
			throw fileImportException(lineNo, columnNo, "SKU不存在" + code);
		}else{
			return res;
		}
	}
	
	private FileImportException fileImportException(int lineNo, int columnNo, String message) throws FileImportException {
		return new FileImportException(lineNo + 1, columnNo+1, message);
	}
	
	private StoreShelf getShelfCode(String shelfCode)throws FileImportException{
		StoreShelf res = this.storeShelfDao.findByCode(shelfCode , null);
		if(res == null){
			throw fileImportException(0, 0, "货位号不存在" + shelfCode);
		}
		return res;
	}

	@Override
	public IoOrder loadByOrderNo(String orderNo) {
		return this.ioOrderDao.loadByOrderNo(orderNo);
	}
	
	private BigDecimal convertToBigDecimal(String value,
			int lineNo, int columnNo) throws FileImportException{
		if(value == null || value.trim().equals("")){
			throw fileImportException(lineNo, columnNo, "价格是空"+value);
		}else{
			try {
				return new BigDecimal(value);
			} catch (NumberFormatException e) {
				throw fileImportException(lineNo, columnNo, "转换数字出错"+value);
			}
		}
	}

	@Override
	public Page<IoOrderItem> findIoOrderItem(PageRequest pageRequest,
			IoOrderItem ioOrderItem) {
		return this.ioOrderItemDao.findIoOrderItem(pageRequest, ioOrderItem);
	}


	
	@Override
	public List<IoOrderItem> ioOrderItemList(IoOrderItem ioOrderItem) {
		return this.ioOrderItemDao.IoOrderList(ioOrderItem);
	}

	@Override
	public Integer findIoOrderItemCount(IoOrderItem ioOrderItem) {
		return this.ioOrderItemDao.findIoOrderItemCount(ioOrderItem);
	}



}
