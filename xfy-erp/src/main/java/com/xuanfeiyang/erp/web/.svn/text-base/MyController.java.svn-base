package com.xuanfeiyang.erp.web;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.base.Preconditions;
import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.App;
import com.xuanfeiyang.erp.dao.SellerDao;
import com.xuanfeiyang.erp.domain.Seller;
import com.xuanfeiyang.erp.domain.SellerDeposit;
import com.xuanfeiyang.erp.domain.SellerDepositLog;
import com.xuanfeiyang.erp.domain.UserInfo;
import com.xuanfeiyang.erp.param.SellerDepositParams;
import com.xuanfeiyang.erp.service.SellerDepositService;
import com.xuanfeiyang.erp.service.SellerService;
import com.xuanfeiyang.erp.service.UserService;
import com.xuanfeiyang.erp.web.util.DataTableRequest;
import com.xuanfeiyang.erp.web.util.DataTableResponse;
import com.xuanfeiyang.erp.web.util.SessionUser;
import com.xuanfeiyang.erp.web.util.WebHelper;

/**
 * 用户中心
 * @author Adam
 *
 */
@Controller
@RequestMapping("/my/")
@SessionAttributes(App.SESSION_USER_KEY)
public class MyController extends BaseController {
	
	private static Logger logger = LoggerFactory.getLogger(MyController.class);
	
	@Resource
	private UserService userService;
	
	@Resource
	private SellerService sellerService;
	
	@Resource 
	private SellerDepositService sellerDepositService;
	
	@Resource
	private SellerDao sellerDao;
	
	/**
	 * 加载修改密码页面
	 * @return
	 */
	@RequestMapping(value="password", method=RequestMethod.GET)
	public String passwordPage() {
		return "my/password";
	}
	
	/**
	 * 修改密码
	 * @param id
	 * @param oldPassword
	 * @param newPassword
	 * @return
	 */
	@RequestMapping(value="password", method=RequestMethod.POST)
	public String modifyPassword(RedirectAttributes attr, 
			@RequestParam("id") Integer id,
			@RequestParam("oldPassword") String oldPassword,
			@RequestParam("newPassword") String newPassword) {
		
		if (oldPassword == null || newPassword == null) {
			return null;
		}
		
		try {
			this.userService.updatePassword2(id, oldPassword, newPassword);
			attr.addFlashAttribute("successMessage", "g.tips.success");
		} catch (IllegalArgumentException e) {
			attr.addFlashAttribute("errorMessage", e.getMessage());
		}
		
		return "redirect:/my/password";
	}
	
	/**
	 * 加载查看个人基本资料页面
	 * @return
	 */
	@RequestMapping(value="basic-info",method=RequestMethod.GET)
	public String viewBasicInfoPage(Model model,
			@ModelAttribute(App.SESSION_USER_KEY) SessionUser curUser){
		Integer userId = curUser.getUserId();
		UserInfo user = this.userService.loadUserInfo(userId);
		Integer sellerId = user.getSellerId();
		SellerDeposit sellerDeposit = this.sellerDepositService.load(sellerId);
		model.addAttribute("sellerDeposit", sellerDeposit);
		model.addAttribute("user", user);
		return "my/view-basic-info";
	}
	
	/**
	 * 卖家提交认证资料页面
	 * 
	 * @param model
	 * @param sessionUser
	 * @return
	 */
	@RequestMapping(value="audit-info", method=RequestMethod.GET)
	public String auditInfo(Model model, 
			@ModelAttribute(App.SESSION_USER_KEY) SessionUser sessionUser) {
		
		model.addAttribute("seller", this.sellerService.load(sessionUser.getSellerId()));
		return "my/audit-info";
	}
	
	/**
	 * 保存卖家提交认证资料
	 * 
	 * @param model
	 * @param seller
	 * @param redirect
	 * @param idCardImg1
	 * @param idCardImg2
	 * @param photoImg
	 * @param bizLicenseImg
	 * @return
	 */
	@RequestMapping(value="audit-info", method=RequestMethod.POST)
	public String saveAuditInfo(Model model, Seller seller, RedirectAttributes redirect,
			@ModelAttribute(App.SESSION_USER_KEY) SessionUser sessionUser,
			@RequestParam(value="idCardImg1", required=false) MultipartFile idCardImg1,
			@RequestParam(value="idCardImg2", required=false) MultipartFile idCardImg2,
			@RequestParam(value="photoImg", required=false) MultipartFile photoImg,
			@RequestParam(value="bizLicenseImg", required=false) MultipartFile bizLicenseImg) {
		
		long maxSize = App.getConfigLong("seller.audit.img.max.size");
		
		try {

			Integer sellerId = seller.getId();
			int type = seller.getType();
			
			if (type == 0) {
				Preconditions.checkNotNull(idCardImg1, "缺少身份证正面图片");
				Preconditions.checkNotNull(idCardImg2, "缺少身份证反面图片");
				Preconditions.checkNotNull(photoImg, "缺少个人照片");
	
				Preconditions.checkArgument(idCardImg1.getSize() <= maxSize, "身份证正面图片超过大小限制");
				Preconditions.checkArgument(idCardImg2.getSize() <= maxSize, "身份证反面图片超过大小限制");
				Preconditions.checkArgument(photoImg.getSize() <= maxSize, "个人照片超过大小限制");
				
				String idCardImg1FilePath = this.getRelativeImgPath(idCardImg1, sellerId, "id-card-img1");
				String idCardImg2FilePath = this.getRelativeImgPath(idCardImg2, sellerId, "id-card-img2");
				String photoImgFilePath = this.getRelativeImgPath(photoImg, sellerId, "photo-img");
				
				this.saveFile(idCardImg1, idCardImg1FilePath);
				this.saveFile(idCardImg2, idCardImg2FilePath);
				this.saveFile(photoImg, photoImgFilePath);
				
				seller.setIdCardUrl1(this.unixStyle(idCardImg1FilePath));
				seller.setIdCardUrl2(this.unixStyle(idCardImg2FilePath));
				seller.setPhotoUrl(this.unixStyle(photoImgFilePath));
				
				seller.setStatus(1);
				seller.setStatusTime(new Date());
				this.sellerService.update(seller);
			} else if (type == 1) {
				
				Preconditions.checkNotNull(bizLicenseImg, "缺少营业执照图片");
				Preconditions.checkArgument(bizLicenseImg.getSize() <= maxSize, "身份证正面图片超过大小限制");
				
				String bizLicenseImgFilePath = this.getRelativeImgPath(bizLicenseImg, sellerId, "biz-license-img");
				
				this.saveFile(bizLicenseImg, bizLicenseImgFilePath);
				
				seller.setComBizLicenseUrl(this.unixStyle(bizLicenseImgFilePath));
				
				seller.setStatus(1);
				seller.setStatusTime(new Date());
				this.sellerService.update(seller);
			}
		
			sessionUser._sellerStatus(1);
			redirect.addFlashAttribute("successMessage", "seller.auth.info.submited");
			return "redirect:/";
			
		} catch (Exception e) {
			logger.error("保存认证出错", e);
			model.addAttribute("errorMessage", e.getMessage());
			return "my/audit-info";
		}
		
	}
	
	private String unixStyle(String path) {
		return path.replace(File.separatorChar, '/');
	}
	
	private void saveFile(MultipartFile multipartFile, String relativePath) throws IllegalStateException, IOException {
		String path = this.realPath(relativePath);
		
		File file = new File(path);
		logger.debug("file:{}:",file);
		File dir = file.getParentFile();
		logger.debug("dir:{}" ,dir);
		if (! dir.exists()) {
			dir.mkdirs();
		}
		
		multipartFile.transferTo(file);
	}
	
	private String realPath(String relativePath) {
		return App.getConfig("upload.base.dir") + relativePath;
	}
	
	private String getRelativeImgPath(MultipartFile file, Integer sellerId, String targetFilenameNoExtension) {
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());
		return "seller-audit" + File.separator + sellerId + File.separator + targetFilenameNoExtension + "." + extension;
	}
	
	@RequestMapping(value="/deposit-log", method=RequestMethod.GET)
	public String depositLogPage(Model model, 
			@ModelAttribute(App.SESSION_USER_KEY) SessionUser sessionUser) {
		
		model.addAttribute("sellerDeposit", this.sellerDepositService.load(sessionUser.getSellerId()));
		
		model.addAttribute("sellerId", sessionUser.getSellerId());
		return "my/deposit-log";
	}
	
	@RequestMapping(value="/deposit-log.json")
	@ResponseBody
	public DataTableResponse<SellerDepositLog> depositLogJson(Model model, 
			@RequestBody DataTableRequest<SellerDepositParams> dtr) {
		
		SellerDepositParams params = dtr.getParams();
		PageRequest pageRequest = WebHelper.assemblePageRequest(dtr);
		
		Page<SellerDepositLog> logs = this.sellerDepositService.findLogPage(pageRequest, params);
		return WebHelper.assembleDataTableResponse(dtr, logs);
	}
	
	@RequestMapping(value="/deposit-log-seller", method=RequestMethod.GET)
	public String sellerDepositLogPage(Model model) {
		model.addAttribute("sellerList",sellerDao.findAll(2));
		return "my/deposit-log-seller";
	}
	
	@RequestMapping(value="/deposit-log-seller-total.json", method=RequestMethod.POST)
	@ResponseBody
	public Map<String,BigDecimal> sellerDepositLogTotal(SellerDepositParams params) {
		return this.sellerDepositService.getTotalInfo(params);
	}
}
