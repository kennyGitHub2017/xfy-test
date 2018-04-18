package com.xuanfeiyang.erp.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.domain.UserLoginLog;
import com.xuanfeiyang.erp.param.UserLoginLogParams;
import com.xuanfeiyang.erp.service.UserLoginLogService;
import com.xuanfeiyang.erp.web.util.DataTableRequest;
import com.xuanfeiyang.erp.web.util.DataTableResponse;
import com.xuanfeiyang.erp.web.util.WebHelper;

@Controller
@RequestMapping("/user-login-log")
public class UserLoginLogController {

	@Resource
	private UserLoginLogService userLoginLogService;

	@RequestMapping("/page.json")
	@ResponseBody
	public DataTableResponse<UserLoginLog> pageJson(
			@RequestBody DataTableRequest<UserLoginLogParams> dtr) {
		PageRequest pageRequest = WebHelper.assemblePageRequest(dtr);
		Page<UserLoginLog> page = this.userLoginLogService.findPage(
				pageRequest, dtr.getParams());

		return WebHelper.assembleDataTableResponse(dtr, page);
	}

}
