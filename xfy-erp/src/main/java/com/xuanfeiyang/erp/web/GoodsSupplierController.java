package com.xuanfeiyang.erp.web;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.App;
import com.xuanfeiyang.erp.domain.GoodsSupplier;
import com.xuanfeiyang.erp.domain.UserInfo;
import com.xuanfeiyang.erp.enums.GoodsSupplierTypeEnum;
import com.xuanfeiyang.erp.service.GoodsSupplierService;
import com.xuanfeiyang.erp.service.UserService;
import com.xuanfeiyang.erp.util.ExcelParser;
import com.xuanfeiyang.erp.web.util.DataTableRequest;
import com.xuanfeiyang.erp.web.util.DataTableResponse;
import com.xuanfeiyang.erp.web.util.SessionUser;
import com.xuanfeiyang.erp.web.util.WebHelper;

@Controller
@RequestMapping("/supplier")
@SessionAttributes(App.SESSION_USER_KEY)
public class GoodsSupplierController extends BaseController {
	
	private final static Logger logger = LoggerFactory.getLogger(GoodsSupplierController.class);
	
	@Resource
	private GoodsSupplierService goodsSupplierService;
	
	@Resource
	private UserService userService;
	
	@Resource
	private MessageSource messageSource;

	@RequestMapping({ "", "/" })
	public String index(RedirectAttributes redirectAttr,
			@RequestParam(value="status", required=false) Integer status) {
		
		return "goods/supplier";
	}
	
	@RequestMapping(value="page-json")
	@ResponseBody
	public DataTableResponse<GoodsSupplier> pageJson(
			@RequestParam(value="status", required=false) Integer status,
			@RequestBody DataTableRequest<?> dtr) {

		String keywords = dtr.getSearch() == null ? null : dtr.getSearch().getValue();
		
		PageRequest pageRequest = WebHelper.assemblePageRequest(dtr);
		Page<GoodsSupplier> page = this.goodsSupplierService.findPage(pageRequest, status, keywords);

		return WebHelper.assembleDataTableResponse(dtr, page);
	}
	
	/**
	 * 加载单个数据的form页面
	 */
	@RequestMapping("/form")
	public String form(Model model, 
			@RequestParam(value="id", required=false) Integer id) {
		GoodsSupplier supplier = null;
		if (id != null) {
			supplier = this.goodsSupplierService.load(id);
			// 修改标识
			model.addAttribute("updateFlag", "1");
		} else {
			supplier = new GoodsSupplier();
		}

		model.addAttribute("supplier", supplier);
		model.addAttribute("purchaseUsers", this.userService.findByDepartment(142679));
		model.addAttribute("developUsers", this.userService.findByDepartment(142678));
		
		return "goods/supplier-form";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(RedirectAttributes redirectAttr,
			@RequestHeader(value="Referer", defaultValue="/supplier") String referer,
			GoodsSupplier supplier) {
		if (supplier.getId() == null) {
			this.goodsSupplierService.save(supplier);
			redirectAttr.addAttribute("status", 0);
		} else {
			this.goodsSupplierService.update(supplier);
		}
		
		redirectAttr.addFlashAttribute("successMessage", "g.tips.success");
		
		return "redirect:" + referer;
	}
	
	/*
	@RequestMapping(value = "/delete")
	public String delete(Model model, RedirectAttributes redirectAttr,
			@RequestHeader(value="Referer", defaultValue="/supplier") String referer,
			@RequestParam(value="id", required=true) Integer id) {
		
		this.goodsSupplierService.delete(id);
		redirectAttr.addFlashAttribute("successMessage", "g.tips.success");
		
		return "redirect:" + referer;
	}
	*/
	
	@RequestMapping(value = "/approve")
	public String approve(RedirectAttributes redirectAttr,
			@RequestHeader(value="Referer") String referer,
			@RequestParam(value="id", required=true) Integer id,
			@RequestParam(value="status", required=true) Integer status,
			@ModelAttribute(App.SESSION_USER_KEY) SessionUser sessionUser) {
		
		this.goodsSupplierService.approve(id, status, sessionUser.getUserId());
		redirectAttr.addFlashAttribute("successMessage", "g.tips.success");
		
		return "redirect:" + referer;
	}
	
	/**
	 * 检查供应商CODE
	 * @param code
	 * @return
	 */
	@RequestMapping("checkSupplier")
	@ResponseBody
	public boolean checkSupplier(@RequestParam("code") String code,@RequestParam(value="id",required=false)Integer id){
		GoodsSupplier result = this.goodsSupplierService.findByCode(code);
		if (null==result  || result.getId().equals(id)){
			return  true;
		}
		return false;
	}
	
	@RequestMapping(value="import_page")
	public String supplierImportPage(){
		return "goods/supplier-import";
	}
	
	/**
	 * 导入供应商资料
	 * @param request
	 * @param attr
	 * @return
	 */
	@RequestMapping(value="import")
	public String supplierImport(MultipartHttpServletRequest request,RedirectAttributes attr){
		InputStream is = null;
		MultipartFile file = null;
		String fileName = null;
		try{
			file = request.getFile("goodsSupplierFile");
			fileName = file.getOriginalFilename();
			int idx = fileName.lastIndexOf(".");
			String extensionName = fileName.substring(idx + 1);
			if (null==file || idx==-1 || file.isEmpty() || !extensionName.startsWith("xls")){
				attr.addFlashAttribute("errorMessage","导入文件格式不对或导入文件为空");
			}else{
				is = file.getInputStream();
				List<GoodsSupplier> datas = readGoodsSupplierDataFromInputStream(is,extensionName);
				String resultmsg = goodsSupplierService.importGoodsSupplier(datas);
				attr.addFlashAttribute("successMessage",this.messageSource.getMessage("g.tips.success", null, null)+resultmsg);
			}
		}catch(Exception e){
			logger.error("导入供应商资料", e);
			attr.addFlashAttribute("errorMessage", this.messageSource.getMessage("g.tips.error", null, null));
		}finally{
			try {
				if (null!=is){
						is.close();
				}
				//移除并删除临时文件
				if (null!=file){
					File f = null;
					String path = GoodsSupplierController.class.getResource("").getPath();
					f = new File(path+File.separator+fileName);
					file.transferTo(f);
					f.delete();
				}
			} catch (IOException e) {
				logger.error("导入供应商资料", e);
			}
		}
		return "redirect:/supplier";
	}
	
	
	private List<GoodsSupplier> readGoodsSupplierDataFromInputStream(InputStream is,String extensionName){
		List<String[]> rowsList = ExcelParser.parseToString(is, extensionName);
		List<GoodsSupplier> dataList = new ArrayList<GoodsSupplier>();
		GoodsSupplier supplier = null;
		int idx=0;
		for(String[] row:rowsList){
			//去除第一行标题
			if (idx++==0){
				continue;
			}
			if (StringUtils.isEmpty(row[1])){
				continue;
			}
			supplier = new GoodsSupplier();
			supplier.setCode(row[1]);		//供应商编号
			supplier.setShortName(row[2]);  //供应商简称
			supplier.setCompanyName(row[3]); //供应商全称
			supplier.setType(GoodsSupplierTypeEnum.getValue(row[5]));  //供应商类别
			supplier.setCity(row[6]);		//供应商城市
			supplier.setContactAddress(row[7]);//联系地址
			supplier.setUrl(row[8]);//URL
			supplier.setContactName(row[9]);  //联系人
			supplier.setContactTel(row[10]);   //联系电话
			supplier.setContactMobile(row[11]); //联系手机
			UserInfo u1 = userService.loadByCode(row[15].trim());
			supplier.setDeveloperId(null==u1?null:u1.getUserId());//开发人员
			u1 = userService.loadByCode(row[16].trim());
			supplier.setBuyerId(null==u1?null:u1.getUserId());  //采购人员
			supplier.setPayMethod(row[18]);   //支付方式
			supplier.setBankAccountName(row[19]);//开户名
			supplier.setBankName(row[20]);  //开户行
			supplier.setBankAccountCode(row[21]);	//银行账号
			supplier.setBuyPeriod(NumberUtils.toInt(StringUtils.trimToEmpty(row[17]).replace("天", ""), 0));
			
			dataList.add(supplier);
		}
		return dataList;
	}
	 
}
