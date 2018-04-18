   <%@page import="com.xuanfeiyang.erp.domain.SysPage"%>
<%@page import="com.xuanfeiyang.erp.domain.SysModule"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Map"%>
<%@page import="com.xuanfeiyang.erp.App"%>
<%@page import="com.xuanfeiyang.erp.web.util.SessionUser"%>
<%@page import="com.xuanfeiyang.erp.web.util.SessionUserPowersCache"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
String curModule = (String) request.getAttribute("sysModule");
String curPage = (String) request.getAttribute("sysPage");

SessionUser sessionUser = (SessionUser) session.getAttribute(App.SESSION_USER_KEY);
Integer userId = sessionUser.getUserId();
Map<String, Set<String>> userPowers = SessionUserPowersCache.getPowerMap(userId);
List<SysModule> userModules = SessionUserPowersCache.getMenus(userId);

if (userModules != null && userModules.size() > 0) {
	out.print("<ul class=\"default_left_nav\" id=\"left_nav\">");
	
	for (SysModule sysModule : userModules) {
		
		List<SysPage> pages = sysModule.getPages();
		if (pages != null && pages.size() > 0) {

			// 打开当前模块
			String moduleCssClass = "";
			String style = " style=\"display:none;\"";
			if (sysModule.getCode().equals(curModule)) {
				style = "";
				moduleCssClass = " class=\"current\"";
			}
			
			out.println("<li" + moduleCssClass + ">");
			out.println("<span>");
			out.print(sysModule.getName());
			out.print("</span>");
			
			out.println("<ul" + style + ">");
			
			for (SysPage sysPage : pages) {
				// 高亮当前页链接
				String pageCssClass = "";
				if (sysPage.getCode().equals(curPage)) {
					pageCssClass = " class=\"current\"";
				}

				// 刊登系统的链接需要加上刊登的前缀
				String url = ("sale".equals(sysPage.getSysCode()) ? App.getConfig("kandeng.base.url") : request.getContextPath());
				url += sysPage.getUrl();
				
				String target = "";
				if ("ebay_publish_new".equals(sysPage.getCode())) {
					target=" target=\"_blank\"";
				}
				
				out.println("<li><a href=\"" + url + "\"" + pageCssClass + target + ">" + sysPage.getName() + "</a></li>");
			}
			
			out.println("</ul>");

			out.println("</li>");
		}
		
	}

	out.println("</ul>");
}

%>
