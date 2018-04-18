package com.xuanfeiyang.erp.web.tag;

import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xuanfeiyang.erp.App;
import com.xuanfeiyang.erp.web.util.SessionUser;
import com.xuanfeiyang.erp.web.util.SessionUserPowersCache;

/**
 * 权限控制标签
 * &lt;x:power code="powerCode" type="powerType">Y&lt;/x:power> <br/>
 * type: module, page, function
 * 以便具有权限的用户能看到 Y
 * 
 * @author Adam
 *
 */
public class PowerTag extends SimpleTagSupport {
	
	private static Logger logger = LoggerFactory.getLogger(PowerTag.class);
	
	private String type;
	
	private String code;

	@Override
	public void doTag() throws JspException {
		try {
			boolean authorized = checkPower(type, code);
			// 授权通过 将标签中的内容输出
			if (authorized) {
				JspFragment f = getJspBody();
				if (f != null) {
					JspWriter out = getJspContext().getOut();
					f.invoke(out);
				}
			}
			
		} catch (java.io.IOException ex) {
			logger.error("Error in power tag", ex);
		} catch (Exception ex) {
			logger.error("Error in power tag", ex);
		}
	}

	private boolean checkPower(String type, String code) {

		PageContext pageContext = (PageContext)getJspContext();
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		SessionUser sessionUser = (SessionUser) request.getSession().getAttribute(App.SESSION_USER_KEY);
		
		if (sessionUser.isAdmin()) {
			return true;
		}
		
		Map<String, Set<String>> powers = getUserPowers(sessionUser);
		if (powers == null || powers.isEmpty()) {
			return false;
		}
		
		Set<String> typePowers= powers.get(type);
		if (typePowers == null || typePowers.isEmpty()) {
			return false;
		}
		
		return typePowers.contains(code);
	}

	// 获取当前用户权限
	private Map<String, Set<String>> getUserPowers(SessionUser sessionUser) {
		
		if (sessionUser == null || sessionUser.getUserId() == null) {
			return null;
		}
		
		return SessionUserPowersCache.getPowerMap(sessionUser.getUserId());
	}

	/**
	 * 
	 * @param type 功能类型
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 设置功能标识代码
	 * @param code 功能代码
	 */
	public void setCode(String code) {
		this.code = code;
	}
	
}
