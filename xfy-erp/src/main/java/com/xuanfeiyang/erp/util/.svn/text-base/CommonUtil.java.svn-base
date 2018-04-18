package com.xuanfeiyang.erp.util;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 通用工具类，包括了签名工具、url拼装
 */
public final class CommonUtil {

    /**
     * 将urlPath和请求参数同时作为签名因子进行签名
     * @param urlPath protocol/version/namespace/name/appKey
     * @param params api请求的各参数键值对
     * @param appSecretKey app签名密钥
     * @return
     */
    public static String signatureWithParamsAndUrlPath(String urlPath, Map<String, String> params, String appSecretKey){
        List<String> paramValueList = new ArrayList<String>();
        if(params != null){
            for (Map.Entry<String, String> entry : params.entrySet()) {
                paramValueList.add(entry.getKey().concat(entry.getValue()));
            }
        }
        final String[] datas = new String[1 + paramValueList.size()];
        datas[0] = urlPath;
        Collections.sort(paramValueList);
        for (int i = 0; i < paramValueList.size(); i++) {
            datas[i+1] = paramValueList.get(i);
        }
        byte[] signature = SecurityUtil.hmacSha1(datas, StringUtil.toBytes(appSecretKey));
        return StringUtil.encodeHexStr(signature);
    }
    
    /**
     * 
     * 仅将请求参数作为签名因子进行签名
     * @param params api请求的各参数键值对
     * @param appSecretKey
     * @return
     */
    public static String signatureWithParamsOnly(Map<String, String> params, String appSecretKey){
        List<String> paramValueList = new ArrayList<String>();
        if(params != null){
            for (Map.Entry<String, String> entry : params.entrySet()) {
                paramValueList.add(entry.getKey().concat(entry.getValue()));
            }
        }
        Collections.sort(paramValueList);
        String[] datas = new String[paramValueList.size()];
        paramValueList.toArray(datas);
        byte[] signature = SecurityUtil.hmacSha1(datas, StringUtil.toBytes(appSecretKey));
        return StringUtil.encodeHexStr(signature);
    }    
    
    
    public static String getParameterEncoderUrl(Map<String, String> params){
    	List<String> paramValueList = new ArrayList<String>();
    	StringBuffer sbf = new StringBuffer();
    	try{
    		 if(params != null){
    	            for (Map.Entry<String, String> entry : params.entrySet()) {
    	                paramValueList.add(URLEncoder.encode(entry.getKey(),"utf-8").concat("=").concat(URLEncoder.encode(entry.getValue(),"utf-8")).concat("&"));
    	            }
    	     }
	        Collections.sort(paramValueList);
	        for(String param:paramValueList){
	        	sbf.append(param);
	        }
	        if (sbf.length() > 0) {
	            sbf.deleteCharAt(sbf.length() - 1);
	        }
    	}catch(UnsupportedEncodingException e){
    		
    	}
        return sbf.toString();
    }
    
    
    public static String signatureWithParams(Map<String, String> params, String appSecretKey) throws UnsupportedEncodingException{
    	String encoderParaUrl = getParameterEncoderUrl(params);
        return SecurityUtil.hmacDigest(encoderParaUrl, StringUtil.toBytes(appSecretKey));
    }
    
    
    /**
     * 获取完整的url
     * @param url 请求uri
     * @param params 请求参数
     * @return
     */
    public static String getWholeUrl(String url, Map<String, String> params){
        if(url == null){
            return null;
        }
        if(params == null){
            return url;
        }
        Set<Map.Entry<String, String>> set = params.entrySet();
        if(set.size() <= 0){
            return url;
        }
        if (!url.contains("?")){
        	url += "?";
        }else{
        	url += "&";
        }
        Iterator<Map.Entry<String, String>> it = set.iterator();
        if(it.hasNext()){
            Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
            String param = entry.getKey().concat("=").concat(entry.getValue());
            url += param;
        }
        while(it.hasNext()){
            Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
            String param = entry.getKey().concat("=" ).concat(entry.getValue());
            url += "&" + param;
        }
        return url;
    }
    
    private CommonUtil(){
    }
    
    public static void main(String []args) throws Exception{
    	Map<String,String> param = new HashMap<String,String>();
		param.put("Version", "1.0");
		param.put("Action","SetStatusToReadyToShip");
		param.put("Format","JSON");
		param.put("OrderItemIds","[1]");
		param.put("DeliveryType", "dropship");
		param.put("ShippingProvider", "DHL");
		param.put("TrackingNumber", "123456789");
		param.put("Timestamp","2016-01-11T17:02:23+08:00");
		param.put("UserID","dudsus@163.com");
    	signatureWithParams(param,"2017400e8bac6288a6cf37750f57f4a11ee74a87");
    }
}