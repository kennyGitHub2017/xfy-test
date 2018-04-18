package com.xuanfeiyang.erp.util;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;

public class SmtApiTest {
	private String smtfileName = "smt.properties";
	private String ebayfileName = "ebay.properties";
	@Before
	public void init() throws Exception{
		ResouresBundle.bundle(smtfileName, "/config/smt.properties");
		ResouresBundle.bundle(ebayfileName, "/config/ebay.properties");
	}
	@Test
	public void readResource() throws Exception{
		System.out.println(ResouresBundle.getValue(smtfileName, "smt.auth.url"));
	}
	@Test
	public void writeResource() throws Exception{
		ResouresBundle.setValue(smtfileName, "abc", "123");
	}
	
	@Test
	public void getSmtClientAuthUrl() throws Exception{
		String host = ResouresBundle.getValue(smtfileName, "smt.auth.url");
		Map<String,String> para = new HashMap<String,String>();
		para.put("site", "aliexpress");
		para.put("client_id", ResouresBundle.getValue(smtfileName, "smt.appKey"));
		para.put("redirect_uri", ResouresBundle.getValue(smtfileName, "smt.redirect_uri"));
		System.out.println(SmtAuthService.getClientAuthUrl(host, para,ResouresBundle.getValue(smtfileName,"smt.appSecret")));
	}
	
	@Test
	public void getSmtToken() throws Exception{
		String host =  ResouresBundle.getValue(smtfileName, Constants.SMT.AUTH_TOKEN);
		Map<String,String> params = new HashMap<String,String>();
		params.put("code", "983a14dc-5bd7-44d4-8796-3776390563d9");
		params.put("client_id", ResouresBundle.getValue(smtfileName, Constants.SMT.APP_KEY));
		params.put("client_secret", ResouresBundle.getValue(smtfileName, Constants.SMT.APP_SECRET));
		params.put("redirect_uri", ResouresBundle.getValue(smtfileName, Constants.SMT.REDIRECT_URL));
		String smtResponse = SmtAuthService.getToken(host, params, true);
	 	System.out.println(smtResponse);
	}
	
	@Test
	public void httpsInvoke() throws Exception{
		Map<String,String> para = new HashMap<String,String>();
		para.put("loginUserDTO.user_name", "541197003@qq.com");
		para.put("userDTO.password", "123");
		System.out.println(new HttpsRequest().postReturnString("https://kyfw.12306.cn/otn/login/userLogin", para,null));
	}
	
	@Test
	public void ebaygetSessionAndToken() throws Exception{
		
		//--------------------------get SessionId---------------------------------------------------------------
		String host = ResouresBundle.getValue(ebayfileName, Constants.EBAY.APP_SESSIONID_URL);		//get sessionId api地址
		String devId = ResouresBundle.getValue(ebayfileName, Constants.EBAY.APP_DEVID);		//ebay dev id	
		String appKey = ResouresBundle.getValue(ebayfileName, Constants.EBAY.APP_ID);		// ebay app id 	
		String appSecretKey = ResouresBundle.getValue(ebayfileName, Constants.EBAY.APP_SECRET);		//ebay app key
		String ruNames = ResouresBundle.getValue(ebayfileName, Constants.EBAY.APP_RUNAMES);		//ebay ruNames
		String xmldata = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><GetSessionIDRequest xmlns=\"urn:ebay:apis:eBLBaseComponents\"><RuName>" + ruNames +"</RuName></GetSessionIDRequest>";
		Map<String,String> headerMap = new HashMap<String,String>();
		headerMap.put(Constants.EBAY.GETSESSION_APIHEADER__APPNAME, appKey);
		headerMap.put(Constants.EBAY.GETSESSION_APIHEADER_DEVNAME, devId);
		headerMap.put(Constants.EBAY.GETSESSION_APIHEADER_APPCERT, appSecretKey);
		headerMap.put("X-EBAY-API-COMPATIBILITY-LEVEL", Constants.EBAY.API_VERSION);
		headerMap.put("X-EBAY-API-SITEID", "201");
		headerMap.put("X-EBAY-API-CALL-NAME", "GetSessionID");
		//System.out.println(xmldata);
		headerMap.put("Content-Type", "text/xml");
		headerMap.put("Content-Length", String.valueOf(xmldata.getBytes().length));
		String xml = EbayAuthService.getSessionId(host,xmldata,headerMap);
		System.out.println(xml);
		Map<String,String> mapReturn = XmlUtil.readStringXmlOut(xml);
		Set<Entry<String,String>> set = mapReturn.entrySet();
		Iterator<Entry<String,String>> it = set.iterator();
		while (it.hasNext()){
			Entry<String,String> entry = it.next();
			System.out.println(entry.getKey() +"\t" +entry.getValue());
		}
		String sessionID = mapReturn.get("SessionID");
		//--------------------------get AuthUrl---------------------------------------------------------------
		
		host = ResouresBundle.getValue(ebayfileName, Constants.EBAY.APP_AUTH_URL);		//获得授权链接地址
		Map<String,String> params = new HashMap<String,String>();
		params.put("RuName", ResouresBundle.getValue(ebayfileName, Constants.EBAY.APP_RUNAMES));
		params.put("SessID",  java.net.URLEncoder.encode(sessionID, "UTF-8"));
		String authUrl = EbayAuthService.getClientAuthUrl(host, params);
		System.out.println("Ebay AuthUrl:" + authUrl);
		
		//--------------------------getToken---------------------------------------------------------------
		headerMap = new HashMap<String,String>();
		host = ResouresBundle.getValue(ebayfileName, Constants.EBAY.APP_TOKEN);		//获得Token地址
		headerMap.put(Constants.EBAY.GETSESSION_APIHEADER__APPNAME, appKey);
		headerMap.put(Constants.EBAY.GETSESSION_APIHEADER_DEVNAME, devId);
		headerMap.put(Constants.EBAY.GETSESSION_APIHEADER_APPCERT, appSecretKey);
		headerMap.put(Constants.EBAY.GETSESSION_APIHEADER_CLEVEL, Constants.EBAY.API_VERSION);
		headerMap.put(Constants.EBAY.GETSESSION_APIHEADER_SITEID, "201");
		headerMap.put(Constants.EBAY.GETSESSION_APIHEADER_CALLNAME, "FetchToken");
		StringBuffer xmlBuf = new StringBuffer("<?xml version=\"1.0\" encoding=\"utf-8\"?><FetchTokenRequest xmlns=\"urn:ebay:apis:eBLBaseComponents\"><Version>613</Version><SessionID>"+sessionID+"</SessionID></FetchTokenRequest>");
		headerMap.put("Content-Type", "text/xml");
		headerMap.put("Content-Length", String.valueOf(xmlBuf.toString().getBytes().length));
		String ebayTokenResponse = EbayAuthService.getToken(host, xmlBuf.toString(), headerMap);
		System.out.println("eBbay GetToken :" + ebayTokenResponse);
	}
	
	
	@Test
	public void parseToken() throws Exception{
		StringBuffer tokenResponse = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		tokenResponse.append("<FetchTokenResponse xmlns=\"urn:ebay:apis:eBLBaseComponents\"><Timestamp>2015-01-17T08:58:59.492Z</Timestamp>"
				+ "<Ack>Success</Ack><Version>891</Version><Build>E891_INTL_API_17051666_R1</Build>"
				+ "<eBayAuthToken>AgAAAA**AQAAAA**aAAAAA**AyS6VA**nY+sHZ2PrBmdj6wVnY+sEZ2PrA2dj6wFk4GhDZGKqQ6dj6x9nY+seQ**BjkDAA**AAMAAA**TBbDCjKnnfG4LrOs11IzGVBSKjJRUlY1OjCJhYnXnB14k2OfyEDbdfmfL409UHm+oHQlgL+7LkIVnOsJNFNMKraG2DFpSuQzBffWbIcr/aGCh4G0iGPzWzJpCWA9VRNOb+dXm428e2xSUVVPMe3QKgdCligZHCgy7g1AXEDg21JWxoOKA/CfaY8drC0/qjtTtZ75YX28ALTf8CGZGY7Yb/4MpRw4RH3wJjmaTS0Ua/PgBF73TGyl8iLQX9DTfY87xshNKBCArTSuAVNDfp2Bq5TetbVr8VE6IC0tQe3wTcHMtw+huLAx8EARlTm+cuOmkvO89XOIr9nkkwq/r28RBBstXcyNf77LydvJGqJmdcw0D9gf2FrMWoKpoFJOam5jxfGfYk+ORYbqfFvfuhHyilsc4Ks6zi2yheewnpz4EWTizThnrpY97ivm/a0/gIUYu6qdmOPt8Cv0jvkhRtNJYjPZRm4Ajq9UP6/638i//AmmuS5hPH6MtYW0XR3bMdXQqcGOESpi6XetlOifNiUASIegeeBx1dJSYrR/JugZrP+ocRedHQYDSMGhmo94yepCRwL25jEaQXJuweltkD53Tw4fvXEM8aAj50E4rkrV1LiPMHM3VHaP09dIrkEC5EthQLbQaEMymmcSgZQo/W4eVR74YH3ivD10OeqWOgQQghQ6OfS4IDTxzkdInfNJoPzYFJzW92pglJ4FqxW8NiwoqPwid3FCTPC/HYWby/JhAYZOoznt3FAxQ8P1ca4vGQPE</eBayAuthToken>"
				+ "<HardExpirationTime>2016-07-10T08:57:39.000Z</HardExpirationTime></FetchTokenResponse>");
		XmlUtil.readStringXml(tokenResponse.toString());
		String expirateTime = "2016-07-10T08:57:39.000Z";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		System.out.println(sdf.parse(expirateTime));
	}
	
}
