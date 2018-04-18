package com.xuanfeiyang.erp.util;

import com.xuanfeiyang.erp.App;

/**
 * 应用相关的公共逻辑
 * 
 * @author Adam
 *
 */
public class AppUtil {
	
	private AppUtil() {}
	
	/**
	 * 产品图片路径
	 * 
	 * @param goodsSku
	 * @param imgIndex
	 * @return
	 */
	public static String goodsImagePath(String goodsSku, int imgIndex) {
		return goodsImageThumbnailPath(goodsSku, imgIndex, null);
	}
	
	/**
	 * 产品图片缩略图路径 
	 * 
	 * @param goodsSku
	 * @param imgIndex
	 * @param type     可选值  S, M, L
	 * @return
	 */
	public static String goodsImageThumbnailPath(String goodsSku, int imgIndex, String type) {
		String baseUrl = App.getConfig("images.base.url");
		if (type != null && type.length() == 1 && "SML".indexOf(type) != -1) {
			return String.format("%s/g/%s/%s/g-%s-%s.jpg", baseUrl, goodsSku.substring(0, 2), goodsSku, imgIndex, type);
		} else {
			return String.format("%s/g/%s/%s/g-%s.jpg", baseUrl, goodsSku.substring(0, 2), goodsSku, imgIndex);
		}
	}
	
}
