
package com.xuanfeiyang.erp.domain;
import java.io.Serializable;
import java.util.Date;

/**
 * @author bernard
 * 
 */
public class Account implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String accountName;
	private String accountType;			//账号类型（可选值：smt,ebay,amazon）
	private Integer sellerId;
	private String ebayToken;
	private Date ebayExpireTime;
	private String amAccessKey;
	private String amSecretAccessKey;
	private String amMerchantId;
	private String amMarketplaceId;
	private String amServiceUrl;
	private String smtRefreshToken;
	private String smtAccessToken;
	private Date   smtExpireTime;
	private String smtAppkey;
	private String smtSecret;
	private String status;			//删除标识(0-未启用,1-正常,2-已删除)
	private Date createdTime;
	private Date lastUpdatedTime;
	private String mail;
	private String abbreviation;		//简称
	
	
	
	/*****************************************
	 * @param id
	 * @param accountName
	 * @param amAccessKey
	 * @param amSecretAccessKey
	 * @param amMerchantId
	 * @param amMarketplaceId
	 * @param amServiceUrl
	 * @param sellerId
	 */

	public Account(Long id,String accountName, String amAccessKey,
			String amSecretAccessKey, String amMerchantId,
			String amMarketplaceId, String amServiceUrl,Integer sellerId) {
		super();
		this.id = id;
		this.accountName = accountName;
		this.amAccessKey = amAccessKey;
		this.amSecretAccessKey = amSecretAccessKey;
		this.amMerchantId = amMerchantId;
		this.amMarketplaceId = amMarketplaceId;
		this.amServiceUrl = amServiceUrl;
		this.sellerId = sellerId;
	}
	
	public Account(){}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return accountName
	 */
	public String getAccountName() {
		return accountName;
	}

	/**
	 * @param accountName
	 *            要设置的 accountName
	 */
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	/**
	 * @return accountType
	 */
	public String getAccountType() {
		return accountType;
	}

	/**
	 * @param accountType
	 *            要设置的 accountType
	 */
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	/**
	 * @return sellerId
	 */
	public Integer getSellerId() {
		return sellerId;
	}

	/**
	 * @param sellerId
	 *            要设置的 sellerId
	 */
	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}

	/**
	 * @return ebayToken
	 */
	public String getEbayToken() {
		return ebayToken;
	}

	/**
	 * @param ebayToken
	 *            要设置的 ebayToken
	 */
	public void setEbayToken(String ebayToken) {
		this.ebayToken = ebayToken;
	}


	public Date getEbayExpireTime() {
		return ebayExpireTime;
	}

	public void setEbayExpireTime(Date ebayExpireTime) {
		this.ebayExpireTime = ebayExpireTime;
	}

	public Date getSmtExpireTime() {
		return smtExpireTime;
	}

	public void setSmtExpireTime(Date smtExpireTime) {
		this.smtExpireTime = smtExpireTime;
	}

	/**
	 * @return amAccessKey
	 */
	public String getAmAccessKey() {
		return amAccessKey;
	}

	/**
	 * @param amAccessKey
	 *            要设置的 amAccessKey
	 */
	public void setAmAccessKey(String amAccessKey) {
		this.amAccessKey = amAccessKey;
	}

	/**
	 * @return amSecretAccessKey
	 */
	public String getAmSecretAccessKey() {
		return amSecretAccessKey;
	}

	/**
	 * @param amSecretAccessKey
	 *            要设置的 amSecretAccessKey
	 */
	public void setAmSecretAccessKey(String amSecretAccessKey) {
		this.amSecretAccessKey = amSecretAccessKey;
	}

	/**
	 * @return amMerchantId
	 */
	public String getAmMerchantId() {
		return amMerchantId;
	}

	/**
	 * @param amMerchantId
	 *            要设置的 amMerchantId
	 */
	public void setAmMerchantId(String amMerchantId) {
		this.amMerchantId = amMerchantId;
	}

	/**
	 * @return amMarketplaceId
	 */
	public String getAmMarketplaceId() {
		return amMarketplaceId;
	}

	/**
	 * @param amMarketplaceId
	 *            要设置的 amMarketplaceId
	 */
	public void setAmMarketplaceId(String amMarketplaceId) {
		this.amMarketplaceId = amMarketplaceId;
	}

	/**
	 * @return amServiceUrl
	 */
	public String getAmServiceUrl() {
		return amServiceUrl;
	}

	/**
	 * @param amServiceUrl
	 *            要设置的 amServiceUrl
	 */
	public void setAmServiceUrl(String amServiceUrl) {
		this.amServiceUrl = amServiceUrl;
	}

	/**
	 * @return smtRefreshToken
	 */
	public String getSmtRefreshToken() {
		return smtRefreshToken;
	}

	/**
	 * @param smtRefreshToken
	 *            要设置的 smtRefreshToken
	 */
	public void setSmtRefreshToken(String smtRefreshToken) {
		this.smtRefreshToken = smtRefreshToken;
	}

	/**
	 * @return smtAccessToken
	 */
	public String getSmtAccessToken() {
		return smtAccessToken;
	}

	/**
	 * @param smtAccessToken
	 *            要设置的 smtAccessToken
	 */
	public void setSmtAccessToken(String smtAccessToken) {
		this.smtAccessToken = smtAccessToken;
	}

	/**
	 * @return smtAppkey
	 */
	public String getSmtAppkey() {
		return smtAppkey;
	}

	/**
	 * @param smtAppkey
	 *            要设置的 smtAppkey
	 */
	public void setSmtAppkey(String smtAppkey) {
		this.smtAppkey = smtAppkey;
	}

	/**
	 * @return smtSecret
	 */
	public String getSmtSecret() {
		return smtSecret;
	}

	/**
	 * @param smtSecret
	 *            要设置的 smtSecret
	 */
	public void setSmtSecret(String smtSecret) {
		this.smtSecret = smtSecret;
	}

	/**
	 * @return status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            要设置的 status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getLastUpdatedTime() {
		return lastUpdatedTime;
	}

	public void setLastUpdatedTime(Date lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}

	public Boolean getEbayExpire() {
		return  ebayExpireTime==null?false:new Date().after(ebayExpireTime);
	}
	
	public Boolean getSmtExpire() {
		return null==smtExpireTime?false:new Date().after(smtExpireTime);
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}
}
