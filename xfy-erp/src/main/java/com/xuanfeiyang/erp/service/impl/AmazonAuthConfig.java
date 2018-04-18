package com.xuanfeiyang.erp.service.impl;

/**
 * 亚马逊API授权信息配置封装，方便参数传递
 * 
 * @author Adam
 *
 */
public class AmazonAuthConfig {
	/** Developer AWS access key. */
    private String accessKey = null;

    /** Developer AWS secret key. */
    private String secretKey = null;

    /** The client application name. */
    private String appName = "";

    /** The client application version. */
    private String appVersion = "";

    /**
     * The endpoint for region service and version.
     * ex: serviceURL = MWSEndpoint.NA_PROD.toString();
     */
    private String serviceURL = null;
    
    /** seller id */
    private String sellerId = null;
    
    /** marketplaceId */
    private String marketplaceId = null;

	public String getAccessKey() {
		return accessKey;
	}

	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public String getServiceURL() {
		return serviceURL;
	}

	public void setServiceURL(String serviceURL) {
		this.serviceURL = serviceURL;
	}

	public String getSellerId() {
		return sellerId;
	}

	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}

	public String getMarketplaceId() {
		return marketplaceId;
	}

	public void setMarketplaceId(String marketplaceId) {
		this.marketplaceId = marketplaceId;
	}
    
    
}
