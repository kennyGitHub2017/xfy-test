package com.xuanfeiyang.erp.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.base.Preconditions;
import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.dao.OrderDao;
import com.xuanfeiyang.erp.dao.SellerDao;
import com.xuanfeiyang.erp.dao.SellerDepositDao;
import com.xuanfeiyang.erp.dao.SellerDepositLogDao;
import com.xuanfeiyang.erp.domain.Order;
import com.xuanfeiyang.erp.domain.SellerDeposit;
import com.xuanfeiyang.erp.domain.SellerDepositLog;
import com.xuanfeiyang.erp.param.SellerDepositParams;
import com.xuanfeiyang.erp.service.DepositBalanceShortageException;
import com.xuanfeiyang.erp.service.SellerDepositService;
import com.xuanfeiyang.erp.util.ExcelParser;

@Service
public class SellerDepositServiceImpl implements SellerDepositService {
	private static Logger logger = LoggerFactory.getLogger(SellerDepositService.class);
	//private DateFormat dft = new SimpleDateFormat("yyyyMMdd");
	
	@Resource
	private SellerDepositDao sellerDepositDao;

	@Resource
	private SellerDepositLogDao sellerDepositLogDao;

	@Resource
	private SellerDao sellerDao;
	
	@Resource
	private OrderDao orderDao;
	
	//@Resource
	//private TableKeyService tableKeyService;

	@Override
	public SellerDeposit load(Integer id) {
		return this.sellerDepositDao.load(id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public synchronized void increaseDeposit(Integer sellerId, 
			BigDecimal amount, String note) {
		this.increaseDeposit(sellerId, amount, note, null);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public synchronized void decreaseDeposit(Integer sellerId, 
			BigDecimal amount, String note)
			throws DepositBalanceShortageException {
		this.decreaseDeposit(sellerId, amount, note, null);
	}
	
	@Override
	public void increaseDeposit(Integer sellerId, BigDecimal amount,
			String note, Integer operId) {
		Preconditions.checkNotNull(sellerId, "卖家 ID 不能为空");
		Preconditions.checkNotNull(amount, "金额不能为空");
		Preconditions.checkArgument(amount.doubleValue() > 0, "金额需大于0");
		Preconditions.checkArgument(StringUtils.isNotBlank(note), "原因不能为空");

		SellerDeposit sd = this.sellerDepositDao.load(sellerId);

		BigDecimal balanceBefore = null;
		BigDecimal balanceAfter = null;

		if (sd != null) {
			balanceBefore = sd.getDeposit();
			balanceAfter = balanceBefore.add(amount);

			sd.setDeposit(balanceAfter);
			sd.setLastUpdatedTime(new Date());
			this.sellerDepositDao.update(sd);

		} else {

			balanceBefore = new BigDecimal(0);
			balanceAfter = amount;

			sd = new SellerDeposit();
			sd.setSellerId(sellerId);
			sd.setDeposit(balanceAfter);
			Date now = new Date();
			sd.setCreatedTime(now);
			sd.setLastUpdatedTime(now);
			this.sellerDepositDao.insert(sd);
		}

		this.saveLog(1, sellerId, amount, balanceBefore, balanceAfter, note, operId);
	}

	@Override
	public void decreaseDeposit(Integer sellerId, BigDecimal amount,
			String note, Integer operId) throws DepositBalanceShortageException {
		Preconditions.checkNotNull(sellerId, "卖家 ID 不能为空");
		Preconditions.checkNotNull(amount, "金额不能为空");
		Preconditions.checkArgument(amount.doubleValue() > 0, "金额需大于0");
		Preconditions.checkArgument(StringUtils.isNotBlank(note), "原因不能为空");

		SellerDeposit sd = this.sellerDepositDao.load(sellerId);

		if (sd == null || (sd.getDeposit().compareTo(amount) < 0)) {
			throw new DepositBalanceShortageException(new BigDecimal(0), amount);
		}
		
		amount = amount.setScale(2, RoundingMode.CEILING);
		BigDecimal balanceBefore = sd.getDeposit();
		BigDecimal balanceAfter = balanceBefore.subtract(amount);

		sd.setDeposit(balanceAfter);
		sd.setLastUpdatedTime(new Date());

		this.sellerDepositDao.update(sd);

		this.saveLog(0, sellerId, amount, balanceBefore, balanceAfter, note, operId);
	}

	private void saveLog(int type, Integer sellerId, BigDecimal amount, BigDecimal balanceBefore,
			BigDecimal balanceAfter, String note, Integer operId) {
		SellerDepositLog log = new SellerDepositLog();
		log.setSellerId(sellerId);
		log.setType(type);
		log.setBalanceBefore(balanceBefore);
		log.setBalanceAfter(balanceAfter);
		log.setAmount(amount);
		log.setNote(note);
		log.setCreatedTime(new Date());
		log.setOperId(operId);
		if(note != null){
			if(note.contains("订单号")){
				String[] str = note.split(":");
				Integer orderId = NumberUtils.toInt(str[str.length-1]);
				log.setOrderId(orderId);
			}
		}
		// 卖家订单交易
		Pattern p = Pattern.compile("订单费用，订单: ([0-9]+), 成本: ([0-9]+(\\.[0-9]+)?), 处理费: ([0-9]+(\\.[0-9]+)?), 运费: ([0-9]+(\\.[0-9]+)?)");
		Matcher matcher = p.matcher(note);
		if (matcher.find()) {
			log.setOrderId(NumberUtils.toInt(matcher.group(1), 0));
			log.setOrderCost(new BigDecimal(NumberUtils.toDouble(matcher.group(2), 0)).setScale(2, RoundingMode.HALF_UP));
			log.setOrderFee(new BigDecimal(NumberUtils.toDouble(matcher.group(4), 0)).setScale(2, RoundingMode.HALF_UP));
			log.setOrderShippingFee(new BigDecimal(NumberUtils.toDouble(matcher.group(6), 0)).setScale(2, RoundingMode.HALF_UP));
		}
		
		//增加资金流水号
//		String dateStr = dft.format(Calendar.getInstance().getTime());
//		String fmt = null;
//		if(type == 1){
//			fmt = String.format("in-%s-",dateStr)+"%05d";
//		} else if(type == 0){
//			fmt = String.format("ex-%s-",dateStr)+"%05d";
//		}
//		String fundsSerialId = this.tableKeyService.nextSerialNumber("seller_deposit_log", fmt);
//		log.setFundsSerialId(fundsSerialId);
		this.sellerDepositLogDao.insert(log);
	}

	@Override
	public Page<SellerDepositLog> findLogPage(PageRequest pageRequest, SellerDepositParams params) {
		return this.sellerDepositLogDao.findPage(pageRequest, params);
	}

	
	/***
	 * 补充卖家资金流水运费为0的问题
	 * 
	 * 临时使用函数
	 * 
	 */
	@Transactional
	public void importDepositLog(MultipartFile file){
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
		BigDecimal total = new BigDecimal(1622.64).setScale(2, RoundingMode.CEILING); //剩余总额 -------------需要修改
		
		for (int i = 0; i < data.size(); i++) {
			String[] rowData = data.get(i);
			rowData[0] = StringUtils.trimToNull(rowData[0]);
			if (rowData[0] == null) {
				continue;
			}
			SellerDepositLog sl = new SellerDepositLog();
			sl.setType(0);// 支出
			
			sl.setSellerId(427);// 卖家ID ------------------------需要修改
			
			sl.setOrderId(Integer.parseInt(rowData[0]));// 订单ID
			sl.setBalanceBefore(total);// 之前余额

			BigDecimal sf = new BigDecimal(rowData[1]).setScale(2, RoundingMode.CEILING);// 运费
			sl.setOrderShippingFee(sf);// 运费
			
			BigDecimal after = total.subtract(sf);
			sl.setBalanceAfter(after);// 之后余额

			sl.setOrderCost(new BigDecimal(0));// 订单成本
			sl.setOrderFee(new BigDecimal(0));// 订单处理费
			sl.setCreatedTime(new Date());
			sl.setAmount(sf);// 变动金额
			sl.setOperId(999998);//------------------------------------需要修改
			sl.setNote("订单运费, 订单:"+rowData[0]+",成本:0.00, 处理费:0.00, 运费:"+sf);// 变动原因
			this.sellerDepositLogDao.insert(sl);
			total = after;
		}
	}

	@Override
	public List<SellerDepositLog> findDepositLog(SellerDepositParams params) {
		return this.sellerDepositLogDao.findDepositLog(params);
	}

	@Override
	public Integer getSumAmountLog(SellerDeposit params) {
		return this.sellerDepositLogDao.getSumAmountLog(params);
	}

	@Override
	public Integer findDepositLogCount(SellerDepositParams params) {
		return this.sellerDepositLogDao.findDepositLogCount(params);
	}

	@Override
	public Map<String, BigDecimal> getTotalInfo(SellerDepositParams params) {
		return this.sellerDepositLogDao.getTotalInfo(params);
	}

	@Override
	public List<Map<String,String>> importLogistics(MultipartFile file) throws Exception {
		
		List<Map<String, String>> list = new ArrayList<Map<String,String>>();

		InputStream is = null;
		try {
			is = file.getInputStream();
		} catch (IOException e) {
			logger.error("获取上传文件出错", e);
		}
		
		if (is == null) {
			throw new Exception("上传文件出错");
		}

		String filename = file.getOriginalFilename();
		String fileExtension = FilenameUtils.getExtension(filename);
		
		List<String[]> data = ExcelParser.parseToString(is, fileExtension);
		if (data == null) {
			throw new Exception("上传文件出错");
		}
		
		for (int i = 1; i < data.size(); i++) {
			String[] rowData = data.get(i);
			
			Map<String,String> map = this.updateOneOrder(rowData[0], rowData[1], rowData[2], rowData[3]);
			String resMap = map.get("succ");
			if("success" != resMap){
				list.add(map);
			}
		}
		
		return list;
	}
	
	/***
	 * 根据订单号或者跟踪号修改
	 * @param orderId 订单编号
	 * @param weight 物流导入重量
	 * @param shipFee 物流导入运费
	 */
	private Map<String, String> updateOneOrder(String orderId,String trackNo, String weight, String shipFee){
		Map<String, String> map = new HashMap<String,String>();
		
		if(StringUtils.isNotBlank(orderId)){
			
			Integer id = Integer.parseInt(orderId);
			SellerDepositLog result = this.sellerDepositLogDao.getMaxByOrderId(id);
			if(result == null){
				map.put(orderId, "无此单");
				
			} else {
				
				SellerDepositLog sdl = new SellerDepositLog();
				sdl.setLogisticsShipFee(this.convertToBigDecimal(shipFee));
				sdl.setLogisticsWeight(this.convertToBigDecimal(weight));
				sdl.setImportFlag(1);
				sdl.setId(result.getId());
				this.sellerDepositLogDao.updateLogById(sdl);
				map.put("succ", "success");
			}
			
		} else if(StringUtils.isBlank(orderId) && StringUtils.isNotBlank(trackNo)){
			Order order = this.orderDao.getByTrackNo(trackNo);
			if(order == null){
				map.put(trackNo, "无此单");
			} else {
				SellerDepositLog result = this.sellerDepositLogDao.getMaxByOrderId(order.getId());
				if(result == null){
					map.put(trackNo, "无此单");
				} else {
					SellerDepositLog sdl = new SellerDepositLog();
					sdl.setLogisticsShipFee(this.convertToBigDecimal(shipFee));
					sdl.setLogisticsWeight(this.convertToBigDecimal(weight));
					sdl.setImportFlag(1);
					sdl.setId(result.getId());
					this.sellerDepositLogDao.updateLogById(sdl);
					map.put("succ", "success");
				}
			}
		}
		return map;
	}
	
	private BigDecimal convertToBigDecimal(String value) {
		if (value == null || value.trim().equals("")) {
			return new BigDecimal(0);
		} else {
			return new BigDecimal(value.trim());
		}
	}

	@Override
	public BigDecimal latestDaysAverageExpenses(Integer sellerId, Integer days) {
		return this.sellerDepositLogDao.latestDaysAverageAmount(sellerId, 0, days);
	}

	
	
}
