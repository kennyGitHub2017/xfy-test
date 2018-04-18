package com.xuanfeiyang.erp.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import com.google.common.base.Preconditions;
import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.dao.GoodsCategoryDao;
import com.xuanfeiyang.erp.dao.GoodsCostLogDao;
import com.xuanfeiyang.erp.dao.GoodsDao;
import com.xuanfeiyang.erp.dao.GoodsDescDao;
import com.xuanfeiyang.erp.dao.GoodsPackingMaterialDao;
import com.xuanfeiyang.erp.dao.GoodsPlatformCategoryDao;
import com.xuanfeiyang.erp.dao.GoodsSupplierDao;
import com.xuanfeiyang.erp.dao.GoodsSupplierPriceDao;
import com.xuanfeiyang.erp.dao.IoOrderItemDao;
import com.xuanfeiyang.erp.dao.StoreShelfDao;
import com.xuanfeiyang.erp.dao.UserInfoDao;
import com.xuanfeiyang.erp.domain.Goods;
import com.xuanfeiyang.erp.domain.GoodsCategory;
import com.xuanfeiyang.erp.domain.GoodsCostLog;
import com.xuanfeiyang.erp.domain.GoodsDesc;
import com.xuanfeiyang.erp.domain.GoodsPackingMaterial;
import com.xuanfeiyang.erp.domain.GoodsSupplier;
import com.xuanfeiyang.erp.domain.StoreShelf;
import com.xuanfeiyang.erp.domain.UserInfo;
import com.xuanfeiyang.erp.param.GoodsParam;
import com.xuanfeiyang.erp.service.FileImportException;
import com.xuanfeiyang.erp.service.GoodsService;
import com.xuanfeiyang.erp.util.ExcelParser;
@Service
public class GoodsServiceImpl implements GoodsService {
	private static Logger logger = LoggerFactory.getLogger(GoodsServiceImpl.class);
	
	@Resource
	private GoodsDao goodsDao;
	
	@Resource
	private GoodsDescDao goodsDescDao;
	
	@Resource
	private GoodsPlatformCategoryDao goodsPlatformCategoryDao;
	
	@Resource
	private GoodsCategoryDao goodsCategoryDao;
	
	@Resource
	private GoodsPackingMaterialDao packingMaterialDao;
	
	@Resource
	private UserInfoDao userInfoDao;
	
	@Resource
	private StoreShelfDao storeShelfDao;
	
	@Resource
	private GoodsSupplierDao goodsSupplierDao;

	@Resource
	private GoodsSupplierPriceDao goodsSupplierPriceDao;
	
	@Resource
	private GoodsCostLogDao goodsCostLogDao;
	
	@Resource
	private IoOrderItemDao ioOrderItemDao;

	
	@Override
	public boolean insert(Goods goods) {
	 int result = this.goodsDao.insert(goods);
	 if(result > 0){
		 return true;
	 }else{
		 return false;
	 }
	}

	@Override
	public Page<Goods> findPage(PageRequest pageRequest, GoodsParam goodsParam) {
		return this.goodsDao.findPage(pageRequest, goodsParam);
		 
	}

	@Override
	public Goods findById(Integer id) {
		return this.goodsDao.findById(id);
	}

	@Override
	public Goods findByIdWithSupplierName(Integer id) {
		return this.goodsDao.findByIdWithSupplierName(id);
	}

	@Override
	public boolean mofify(Goods goods) {
		//int result = this.goodsDao.update(goods);
		int result = this.goodsDao.updateSelective(goods);
		if(result > 0){
		return true;
		}else{
			return false;
		}
		
	}

	@Override
	public boolean delete(Integer id) {
		int result = this.goodsDao.delete(id);
		if(result > 0){
			return true;
			
		}else{
				return false;
			}
	}

	@Override
	public Goods findBySku(String sku) {
		return this.goodsDao.findBySku(sku);
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void importGoodsDesc(MultipartFile file) throws FileImportException {
		
		InputStream is = null;
		try {
			is = file.getInputStream();
		} catch (IOException e) {
			logger.error("获取上传文件出错", e);
		}
		
		if (is == null) {
			throw new FileImportException("获取上传文件出错");
		}

		String filename = file.getOriginalFilename();
		String fileExtension = FilenameUtils.getExtension(filename);
		
		List<String[]> data = ExcelParser.parseToString(is, fileExtension);
		if (data == null) {
			throw new FileImportException("上传文件为空，未处理");
		}
		
		for (int i = 1; i < data.size(); i++) {
			String[] rowData = data.get(i);
			
			// 若整行都为空，则不处理
			if (this.isRowDataAllBlank(rowData)) {
				continue;
			}
			
			String sku = this.getData(rowData, 0); 
			if (sku == null) {
				throw new FileImportException(i+1, 0, "SKU 字段为空");
			}
			
			// 检查 SKU 是否存在
			Goods goods = this.goodsDao.findBySku(sku);
			if (goods == null) {
				throw new FileImportException(i+1, 0, "SKU 不存在");
			}
			
			// 保存描述信息
			this.saveGoodsDesc(rowData);
			
			// 保存平台类目信息
			// this.saveGoodsPlatformCategory(rowData);
		}
	}
	
	private boolean isRowDataAllBlank(String[] rowData) {
		
		if (rowData == null) {
			return true;
		} 

		boolean r = true;
		
		for (int i = rowData.length - 1; i >= 0; i--) {
			if (StringUtils.isNotBlank(rowData[i])) {
				r = false;
				break;
			}
		}
		
		return r;
	}

/*	private void saveGoodsPlatformCategory(String[] rowData) {
		if (rowData.length > 7) {
			GoodsPlatformCategory gpc = this.goodsPlatformCategoryDao.load(this.getData(rowData, 0));
			
			// 有则更新，无则新增
			if (gpc == null) {
				gpc = new GoodsPlatformCategory();
				this.setGoodsPlatformCategory(gpc, rowData);
				this.goodsPlatformCategoryDao.insert(gpc);
			} else {
				this.setGoodsPlatformCategory(gpc, rowData);
				this.goodsPlatformCategoryDao.update(gpc);
			}
		}
	}

	private void setGoodsPlatformCategory(GoodsPlatformCategory gpc, String[] rowData) {
		gpc.setGoodsSku(this.getData(rowData, 0));
		gpc.setEbayCatId(this.getData(rowData, 7));
		gpc.setSmtCatId(this.getData(rowData, 8));
		gpc.setAmCatId(this.getData(rowData, 9));
		gpc.setLastUpdatedTime(new Date());
	}*/
	
	private String getData(String[] rowData, int index) {
		try {
			return StringUtils.trimToNull(rowData[index]);
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}

	private static String[] languages = {"", "en", "de", "fr", "es", "ru", "it"};
	private void saveGoodsDesc(String[] rowData) {
		GoodsDesc gd = new GoodsDesc();
		gd.setGoodsSku(rowData[0]);
		gd.setLastUpdatedTime(new Date());
		
		// 有则更新，无则新增
		for (int i = 1; i < languages.length; i++) {
			gd.setLanguage(languages[i]);
			gd.setDescription(this.getData(rowData, i));
			
			GoodsDesc tmp = this.goodsDescDao.loadBySkuAndLanguage(rowData[0], languages[i]);
			if (tmp == null) {
				gd.setVersion(0);
				this.goodsDescDao.insert(gd);
			} else {
				gd.setId(tmp.getId());
				gd.setVersion(tmp.getVersion() + 1); // 更新时版本号+1
				this.goodsDescDao.update(gd);
			}
		}
		
	}

	@Override
	public List<GoodsDesc> findDescriptionsBySku(String goodsSku) {
		return goodsSku == null ? null : this.goodsDescDao.findBySku(goodsSku);
	}
	
	

	@Override
	public void importGoods(MultipartFile file, Goods goods)
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
		
		for (int i = 2; i < data.size(); i++) {
			String[] rowData = data.get(i);
			
			rowData[0] = StringUtils.trimToNull(rowData[0]);
			if (rowData[0] == null) {
				continue;
			}
			
			this.insertGoods(i,rowData);
		}
		
		this.goodsSupplierPriceDao.batchAddSupplierPrice();//添加采购报价
	}
	

	
	public String returnSku(String categoryCode, String brand,
			Integer categoryId, String name, String color, String goodsSize,
			String rules, String model, Integer baseCategoryId,int lineNo)throws FileImportException {
		String skuNo = "";

		Goods goods1 = new Goods();
		goods1.setBrand(brand);// 品牌
		goods1.setCategoryId(categoryId);// 小类
		goods1.setName(name);// 产品名
		goods1.setColor(color);// 颜色
		goods1.setGoodsSize(goodsSize);// 尺寸
		goods1.setRules(rules);// 规格
		goods1.setModel(model);// 型号
		Goods result1 = goodsDao.findGoods(goods1);
		if (result1 == null) {
			Goods goods3 = new Goods();
			goods3.setBaseCategoryId(baseCategoryId);
			Goods result2 = goodsDao.findGoods(goods3);
			String code = categoryCode.substring(0, 2);

			if (result2 == null) { // 大类不存在
				skuNo = code + "00001" + "001";
			} else {// 大类存在

				Goods goods2 = new Goods();
				goods2.setBrand(brand);// 品牌
				goods2.setCategoryId(categoryId);// 小类
				goods2.setName(name);// 产品名
				Goods result3 = goodsDao.findGoods(goods2);

				if (result3 != null) { //存在
					String sku5 = result3.getGoodsSku();

					String sku6 = sku5.substring(7, 10);
					Integer sku7 = Integer.parseInt(sku6);
					Integer sku8 = sku7 + 1;

					Integer sku8Length = sku8.toString().length();
					Integer length = 3 - sku8Length;
					StringBuffer sb = new StringBuffer();
					for (int i = 0; i < length; i++) {
						sb.append("0");
					}
					String sku9 = sku5.substring(0, 7);
					skuNo = sku9 + sb + sku8;

				} else {//不存在
					String sku1 = result2.getGoodsSku();
					String sku2 = sku1.substring(2, 7);
					Integer sku3 = Integer.parseInt(sku2);
					Integer sku4 = sku3 + 1;
					Integer sku4Length = sku4.toString().length();
					Integer length = 5 - sku4Length;
					StringBuffer sb = new StringBuffer();
					for (int i = 0; i < length; i++) {
						sb.append("0");
					}

					skuNo = code + sb + sku4 + "001";
				}
			}

		} else {
			logger.error("产品已经存在");
			throw fileImportException(lineNo, 0, "产品已经存在");
		}
		return skuNo;
	}

	
	/**
	 * 获得用户ID
	 * 根据code 查询ID
	 * @param code
	 * @return
	 */
	private Integer getUserId(String code, int lineNo, int columnNo)
			throws FileImportException {
		Integer returnVal = null;
		if (StringUtils.isBlank(code)) {
			return returnVal;
		} else {
			UserInfo result = userInfoDao.loadByCode(code.trim());
			if (result == null) {
				throw fileImportException(lineNo, columnNo, "人员编码输入错误：" + code);
			} else {
				returnVal = result.getUserId();
			}
		}
		return returnVal;
	}
	
	/**
	 * 获得供应商ID
	 * 根据code 查询ID
	 * @param code
	 * @return
	 * @throws Exception 
	 */
	private Integer getSupplierId(String code, int lineNo, int columnNo)
			throws FileImportException {
		Integer returnVal = null;
		if (StringUtils.isBlank(code)) {
			return returnVal;
		} else {
			GoodsSupplier supplier2 = goodsSupplierDao.findByCode(code.trim());
			if (supplier2 == null) {
				throw fileImportException(lineNo, columnNo, "供应商编码输入错误：" + code);
			} else {
				returnVal = supplier2.getId();
			}
		}
		return returnVal;
	}
	
	private BigDecimal convertToBigDecimal(String value,
			int lineNo, int columnNo) throws FileImportException{
		if(value == null || value.trim().equals("")){
			return new BigDecimal(0);
		}else{
			try {
				return new BigDecimal(value);
			} catch (NumberFormatException e) {
				throw fileImportException(lineNo, columnNo, "转换数字出错"+value);
			}
		}
	}
	
	private FileImportException fileImportException(int lineNo, int columnNo, String message) throws FileImportException {
		return new FileImportException(lineNo + 1, columnNo+1, message);
	}
	
	private String checkContent(String value,int lineNo, int columnNo)throws FileImportException{
		if(StringUtils.isBlank(value)){
			throw fileImportException(lineNo, columnNo, "不能为空"+value);
		}else{
			return value.trim();//去空格
		}
	}
	
	
	private void insertGoods(int lineNo,String[] rowData)throws FileImportException{
		Goods goods = new Goods();
		
		goods.setOldSku(this.trimVal(rowData[0]));//老SKU
		goods.setName(this.checkContent(rowData[1], lineNo, 1));//货品名称
		goods.setEnName(this.trimVal(rowData[2]));//英文名称
		goods.setUnit(this.trimVal(rowData[3]));//产品单位
		
		goods.setCost(this.convertToBigDecimal(rowData[4], lineNo, 4));//采购价
		goods.setFirstCost(this.convertToBigDecimal(rowData[4], lineNo, 4));//导入时的采购价
		goods.setWeight(this.convertToBigDecimal(rowData[6], lineNo,6));//产品重量(kg)
		
		goods.setPrice(this.returnPrice(rowData[4], rowData[6]));//建议售价
		
		goods.setDeclarationNameEn(this.trimVal(rowData[7])); //英文申报名称
		goods.setCustomsCode(this.trimVal(rowData[8]));//海关编码
		goods.setDeclarationNameCn(this.trimVal(rowData[9]));//中文申报名称
		
		goods.setDeclarationCost(this.convertToBigDecimal(rowData[10], lineNo, 10));//申报价值USD
		goods.setLength(this.convertToBigDecimal(rowData[11], lineNo,11));//产品长(cm)
		goods.setWidth(this.convertToBigDecimal(rowData[12], lineNo,12));//产品宽
		goods.setHeight(this.convertToBigDecimal(rowData[13], lineNo,13));//产品高
		
		
		goods.setColor(this.trimVal(rowData[14]));//颜色	
		goods.setGoodsSize(this.trimVal(rowData[15]));//码数大小	
		goods.setMateril(this.trimVal(rowData[16]));//材质
		goods.setBrand(this.trimVal(rowData[17]));//品牌
		
		goods.setIsMagnetic(this.trimVal(rowData[18]).equals("是")? 1:0);//是否带磁性
		goods.setIsBattery(this.trimVal(rowData[19]).equals("是")? (short)1 :(short)0);//是否含电池	
		goods.setIsCopyright(this.trimVal(rowData[20]).equals("是")? (short)1 :(short)0);//是否侵权
		goods.setIsShipping(this.trimVal(rowData[21]).equals("是")? (short)1 :(short)0);//是否指定运输方式
		goods.setIspacking(this.trimVal(rowData[22]).equals("是")? (short)1 :(short)0);//是否带包装
		goods.setIsLiquid(this.trimVal(rowData[23]).equals("是")? (short)1 :(short)0);//否是液体
		goods.setIsRegulated(this.trimVal(rowData[24]).equals("是")? 1 :0);//是否是管制品
		
		
		goods.setSaleUser(this.getUserId(rowData[25],lineNo,25));//销售人员
		goods.setBuyUser(this.getUserId(rowData[26],lineNo,26));//采购人员
		goods.setPickUser(this.getUserId(rowData[27],lineNo,27));//拣货人员
		goods.setAssembleUser(this.getUserId(rowData[28],lineNo,28));//配货人员
		
		goods.setPackingCapacity(this.trimVal(rowData[29])); //包装材料
		
		try {
			StoreShelf shelfId = storeShelfDao.findByCode(rowData[30].trim(), null);//根据货位编码查询
			goods.setStoreShelfId(shelfId.getId());//存入货位号码ID
			goods.setStoreId(shelfId.getStoreId());//存入仓库ID
		} catch (Exception e) {
			
			throw new FileImportException(lineNo, 30, "货位号码不存在："+rowData[30]);
		}
		
	
		goods.setPackingMaterialId(this.getPackageMaterial(rowData[31], lineNo, 30));//包装规格
			

		
		goods.setSupplierId(this.getSupplierId(rowData[32],lineNo,32));//供应商1
		goods.setSupplier2Id(this.getSupplierId(rowData[33],lineNo,33));//供应商2
		goods.setSupplier3Id(this.getSupplierId(rowData[34],lineNo,34));//供应商3
		
		goods.setStatus((short)1);//产品状态
		
		String cDate = rowData[36];
		if(StringUtils.isBlank(cDate)){
			goods.setDevelopTime(null);//开发时间
		}else{
			
			DateFormat fmt =new SimpleDateFormat("yyyy/MM/dd");
			Date date;
			try {
				date = fmt.parse(cDate);
			} catch (ParseException e) {
				throw new FileImportException(lineNo, 36, "日期格式有误："+rowData[36]);
			}
			goods.setDevelopTime(date);//开发时间
		}
		
		
	
		goods.setDevelopUser(this.getUserId(rowData[37],lineNo,37));//开发员
		
		goods.setNote(this.trimVal(rowData[38]));//产品描述1
		goods.setNote2(this.trimVal(rowData[39])); //产品描述2
		goods.setNote3(this.trimVal(rowData[40]));//产品描述3
		goods.setNote4(this.trimVal(rowData[41]));//产品描述4
		goods.setNote5(this.trimVal(rowData[42]));//产品描述5
		
		goods.setTestType(this.trimVal(rowData[43]).equals("全检")? (short)0 :(short)1);//检验方式
		goods.setPackingFee(this.convertToBigDecimal(StringUtils.trimToEmpty(rowData[44]), lineNo,44));//包装费用
		
		GoodsCategory category = goodsCategoryDao.loadByCode(StringUtils.trimToEmpty(rowData[45]));
		if (category == null) {
			throw new FileImportException(lineNo, 45, "类目编码输入错误："+rowData[45]);
		}
		goods.setCategoryId(category.getId());//产品小类
		goods.setMidCategoryId(category.getParentId());//中类
		GoodsCategory Category1 = goodsCategoryDao.findById(category.getParentId());
		goods.setBaseCategoryId(Category1.getParentId());//大类
		

		goods.setRules(this.trimVal(rowData[46]));//规格
		goods.setModel(this.trimVal(rowData[47]));//型号
		
		String sku = this.returnSku(StringUtils.trimToEmpty(rowData[45]), goods.getBrand(), category.getId(),
				goods.getName(), goods.getColor(), goods.getGoodsSize(), 
				goods.getRules(), goods.getModel(),Category1.getParentId(),lineNo);
		goods.setGoodsSku(sku);
		
		
		goods.setBaseCode(sku.substring(0, 7));//父类编码
		goods.setReferenceUrl(this.trimVal(rowData[48]));
		
		goods.setBelongSelf(this.trimVal(rowData[49]).equals("是")?(short) 1 :(short)0);
		
		if(goods.getOldSku() != null){
			goods.setCustomsCode(goods.getOldSku());//海关编码
		}else{
			goods.setCustomsCode(sku);//海关编码
		}
		goods.setCustomCost(this.countCustomCost(goods.getPackingMaterialId(), goods.getCost()));
		try {
			goodsDao.insert(goods);
		} catch (Exception e) {
			throw fileImportException(lineNo,0,e.getMessage());
		}
		
	}
	
	/**
	 * 去空格
	 * @param value
	 * @return
	 */
	private String trimVal(String value){
		
		if(value != null){
			return value.trim();
		}
		
		return null;
		
	}

	@Override
	public void updateOpenFlag(Integer id, String openFlag) {
		Preconditions.checkNotNull(id, "缺少商品ID");
		Preconditions.checkNotNull(openFlag, "缺少 openFlag");
		Preconditions.checkArgument("0".equals(openFlag) || "1".equals(openFlag), "openFlag取值不对");
		
		this.goodsDao.updateOpenFlag(id, openFlag);
	}
	
	/***
	 * 计算建议售价
	 * @param cost
	 * @param weight
	 * （（（产品成本+重量*90）+1.2）/0.96/0.88/6.1+0.2）*1.2
	 * @return
	 */
	private BigDecimal returnPrice(String cost, String weight){
		BigDecimal res = new BigDecimal(0);
		
		if(StringUtils.isBlank(cost) == false && StringUtils.isBlank(weight) == false){
			BigDecimal weight1 = new BigDecimal(weight);
			BigDecimal cost1 = new BigDecimal(cost);
			BigDecimal d0 = new BigDecimal(1.2);
			
			BigDecimal d1 = new BigDecimal(0.96);
			BigDecimal d2 = new BigDecimal(0.88);
			BigDecimal d3 = new BigDecimal(6.1);
			BigDecimal d4 = new BigDecimal(0.2);
			BigDecimal d5 = new BigDecimal(1.2);
			
			//((产品成本+重量*90)+1.2)
			BigDecimal a = new BigDecimal(90);
			BigDecimal b = weight1.multiply(a);
			BigDecimal c = cost1.add(b).add(d0);
			BigDecimal e = c.setScale(2, BigDecimal.ROUND_HALF_UP);
			
			//((产品成本+重量*90）+1.2)/0.96/0.88/6.1+0.2)
			BigDecimal f = e.divide(d1,3).divide(d2,3).divide(d3,3);
			BigDecimal g = f.add(d4);
			BigDecimal h = g.setScale(2, BigDecimal.ROUND_HALF_UP);
			
			//(((产品成本+重量*90）+1.2)/0.96/0.88/6.1+0.2)*1.2
			BigDecimal k = h.multiply(d5);
			res = k.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		
		return res;
	}

	@Override
	public List<Goods> findByParam(GoodsParam goodsParam) {
		return this.goodsDao.findByParam(goodsParam);
	}

	/***
	 * 根据包装规格型号 获得它的ID
	 * 
	 * @param model
	 * @return
	 * @throws FileImportException
	 */
	private Integer getPackageMaterial(String model, int lineNo, int columnNo)
			throws FileImportException {
		Integer returnVal = null;
		if (StringUtils.isNotBlank(model)) {
			GoodsPackingMaterial res = packingMaterialDao.getByModel(model,null);
					
			if (res == null) {
				throw fileImportException(lineNo, columnNo, "包装规格不存在" + model);
			} else {
				returnVal = res.getId();
			}
		} else {
			return returnVal;
		}
		return returnVal;

	}

	@Override
	public Integer getCountByParam(GoodsParam goodsParam) {
		return this.goodsDao.getCountByParam(goodsParam);
	}

	@Override
	public void update(Goods goods) {
		this.goodsDao.updateSelective(goods);
	}

	@Override
	public List<Goods> findListById(List<Integer> ids) {
		return this.goodsDao.findListById(ids);
	}

	@Override
	public Page<Goods> getListingInfo(PageRequest pageRequest,String goodsSku) {
		return this.goodsDao.getListingInfo(pageRequest, goodsSku);
	}

	@Override
	public List<Goods> getListingCount(List<String> goodsSku) {
		if(goodsSku.size() == 0){
			goodsSku.add(null);
		}
		return this.goodsDao.getListingCount(goodsSku);
	}

	@Override
	@Transactional
	public void modifyGoodsCost(Integer id, String costNew, Integer updateType,String operUser, 
			String sku,String customCostNew,Integer customUpType) {
		
		BigDecimal costNewFlag = null;
		Goods goods = new Goods();
		
		if (StringUtils.isNotBlank(costNew)
				&& StringUtils.isNotBlank(customCostNew)) {
			
			goods.setCost(new BigDecimal(costNew));
			goods.setCustomCost(new BigDecimal(customCostNew));
			costNewFlag = new BigDecimal(costNew);
			
			
		} else {

			if (StringUtils.isNotBlank(costNew)) {
				BigDecimal pkgFee = this.getGoodsPackageFee(sku);
				BigDecimal multiplyDate = new BigDecimal(1.1);
				BigDecimal costNewDecimal = new BigDecimal(costNew);
				BigDecimal thisCustomCost = costNewDecimal
						.multiply(multiplyDate).add(pkgFee)
						.setScale(2, BigDecimal.ROUND_HALF_UP);
				goods.setCustomCost(thisCustomCost);
				goods.setCost(new BigDecimal(costNew));
				costNewFlag = new BigDecimal(costNew);
			} else if (StringUtils.isNotBlank(customCostNew)) {
				goods.setCustomCost(new BigDecimal(customCostNew));
			}
			
		}
		
		goods.setCostUpdateType(updateType);
		goods.setCustomUpdateType(customUpType);
		goods.setLastUpdatedTime(new Date());
		goods.setId(id);
		this.goodsDao.updateSelective(goods);
		
		
		
		this.addCostLog(costNewFlag, updateType, operUser, sku, customCostNew, customUpType);// 增加修改日志
		
	}
	
	
	/***
	 * 产品价格修改增加日志
	 * 
	 */
	private void addCostLog(BigDecimal costNew, Integer updateType,
			String operUser,String goodsSku,String customCost,Integer customUpType){
		
		GoodsCostLog gcl = new GoodsCostLog();
		gcl.setCost(costNew);
		gcl.setCreatedTime(new Date());
		gcl.setNote("手动"+ "，sku改为" +costNew +"，sku维护"+updateType + "，卖家价格改" + customCost + "，卖家价格维护"+customUpType);
		gcl.setOperUser(operUser);
		gcl.setGoodsSku(goodsSku);
		gcl.setCostUpType(updateType);
		
		gcl.setCustomCost(customCost);
		gcl.setCustomUpType(customUpType);
		
		this.goodsCostLogDao.add(gcl);
	}

	@Override
	public List<GoodsCostLog> findLogBySku(String sku) {
		return this.goodsCostLogDao.findBySku(sku);
	}
	
	/***
	 * 根据sku得到包装袋的价格
	 * @param goodsSku
	 * @return
	 */
	private BigDecimal getGoodsPackageFee(String goodsSku){
		BigDecimal pkgFee = new BigDecimal(0);
		Goods goods = this.goodsDao.findBySku(goodsSku);
		
		if(goods.getPackingMaterialId() != null){
			GoodsPackingMaterial gpl = packingMaterialDao.getById(goods.getPackingMaterialId());
			pkgFee = gpl.getPrice();
		}
		
		return pkgFee;
	}
	
	/***
	 * 导入时卖家成本
	 * @param id
	 * @param cost
	 * @return
	 * @throws FileImportException
	 */
	private BigDecimal countCustomCost(Integer id, BigDecimal cost) throws FileImportException{
			
		BigDecimal customCost = cost;
		BigDecimal multiplyDate = new BigDecimal(1.1);
		BigDecimal pkFee = new BigDecimal(0);
		if(id != null){
			GoodsPackingMaterial res = packingMaterialDao.getById(id);
			if(res != null){
				pkFee = res.getPrice();
			}
		}
		customCost = cost.multiply(multiplyDate).add(pkFee).setScale(1, BigDecimal.ROUND_HALF_UP);
		return customCost;
	}
	
	
	
}
