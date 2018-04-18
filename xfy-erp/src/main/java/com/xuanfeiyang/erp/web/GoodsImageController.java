package com.xuanfeiyang.erp.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xuanfeiyang.erp.App;
import com.xuanfeiyang.erp.domain.Goods;
import com.xuanfeiyang.erp.service.GoodsService;

@Controller
@RequestMapping("/goods-image")
public class GoodsImageController extends BaseController {
	
	private static Logger logger = LogManager.getLogger(GoodsImageController.class);
	
	@Resource 
	private GoodsService goodsService;
	
	/**
	 * SKU 图片页面
	 * @param model
	 * @param sku
	 * @return
	 */
	@RequestMapping(value={"", "/"})
	public String index(Model model,
			@RequestParam(value="sku", required=false) String sku) {
		
		Goods goods = null;
		if (sku != null) {
			goods = this.goodsService.findBySku(sku);
			model.addAttribute("goods", goods);
		} 
		
		if (goods == null) {
			model.addAttribute("errorMessage", "未找到SKU对应的商品");
		}
		
		return "goods/goods-image";
	}
	
	/**
	 * 保存编辑后的图片信息
	 * 
	 * @param model
	 * @param sku
	 * @return
	 */
	@RequestMapping("/save")
	public String save(RedirectAttributes redirect,
			@RequestParam(value="sku") String sku,
			@RequestParam(value="filenames", required=false) String filenames) {
		
		try {
			Goods goods = this.goodsService.findBySku(sku);
			
			if (goods != null && StringUtils.isNotBlank(filenames)) {
				String url = App.getConfig("image.server.goods.url") + sku + "/resort";
				
				CloseableHttpClient httpClient = HttpClients.createDefault();
				HttpPost post = new HttpPost(url);
				
				post.setHeader("Authorization", App.getConfig("image.server.access.key"));
				
				NameValuePair data = new BasicNameValuePair("filenames", filenames);
				List<NameValuePair> params = new ArrayList<NameValuePair>(1);
				params.add(data);
				HttpEntity entity = null;
				try {
					entity = new UrlEncodedFormEntity(params, "utf-8");
				} catch (UnsupportedEncodingException e1) {
					
				}
				post.setEntity( entity );
				
				HttpResponse httpResponse = null;
				String response = null;
				try {
					httpResponse = httpClient.execute(post);
					HttpEntity re = httpResponse.getEntity();
					response = re != null ? EntityUtils.toString(re) : null;
	
				} catch (IOException e) {
					logger.error(e.getMessage(), e);
				} finally {
					try {
						httpClient.close();
					} catch (IOException e) {
					}
				}
				
				if (response != null) {
					Map<String, Object> resMap = new Gson().fromJson(response, 
							new TypeToken<Map<String, Object>>(){}.getType());
					
					String resSku = (String) resMap.get("sku");
					@SuppressWarnings({ "rawtypes" })
					List files = (List) resMap.get("files");
					if (sku.equals(resSku) && files != null) {
						int imgCount = files.size();
						if (imgCount != goods.getImgCount()) {
							Goods tmp = new Goods();
							tmp.setId(goods.getId());
							tmp.setImgCount(imgCount);
							this.goodsService.update(tmp);
						}
					}
				}
			}
			
			redirect.addFlashAttribute("successMessage", "操作成功");
		} catch (Exception e) {
			logger.error("保存图片出错", e);
			redirect.addFlashAttribute("errorMessage", "系统出错");
		}
		
		redirect.addAttribute("sku", sku);
		return "redirect:/goods-image";
	}
	
	// 更新图片数到数据库
	@RequestMapping("/update-img-count")
	public String updateImgCount(RedirectAttributes redirect,
			@RequestParam("sku") String sku) {
		Goods goods = this.goodsService.findBySku(sku);
		
		if (goods != null) {
			String url = App.getConfig("image.server.goods.url") + sku;
			CloseableHttpClient httpClient = HttpClients.createDefault();
			HttpGet get = new HttpGet(url);
			
			get.setHeader("Authorization", App.getConfig("image.server.access.key"));
	
			HttpResponse httpResponse = null;
			String response = null;
			try {
				httpResponse = httpClient.execute(get);
				HttpEntity re = httpResponse.getEntity();
				response = re != null ? EntityUtils.toString(re) : null;
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			} finally {
				try {
					httpClient.close();
				} catch (IOException e) {
				}
			}
			
			if (response != null) {
				Map<String, Object> resMap = new Gson().fromJson(response, 
						new TypeToken<Map<String, Object>>(){}.getType());
				
				String resSku = (String) resMap.get("sku");
				@SuppressWarnings({ "rawtypes" })
				List files = (List) resMap.get("files");
				if (sku.equals(resSku) && files != null) {
					int imgCount = files.size();
					if (imgCount != goods.getImgCount()) {
						Goods tmp = new Goods();
						tmp.setId(goods.getId());
						tmp.setImgCount(imgCount);
						this.goodsService.update(tmp);
					}
				}
			}
		}
		
		redirect.addAttribute("sku", sku);
		return "redirect:/goods-image";
	}
}
