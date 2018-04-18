<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>请注册</title>
<link rel="shortcut icon" href="${pageContext.request.contextPath }/resources/favicon.ico">
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/left-sidebar/css/login.css" />
</head>
<body id="register">

	<div id="page-wrapper">
		<div class="header">
			<div>
				<img src="${pageContext.request.contextPath }/resources/left-sidebar/images/logo.png" />
			</div>
		</div>
		<div class="reg-flow reg-flow-1"></div>
		<div class="login-panel">
			已有CSS账户？ <a class="x-link-btn" href="${pageContext.request.contextPath }/login">直接登录</a>
		</div>
		<div id="register-info">
			<h3 class="reg-title">以下均为必填项
				<div style="text-align:right;float:right;font-size:12px;">推荐最佳兼容浏览器： Google Chrome，FireFox 。如您注册接收不到验证码，请切换至上述浏览器重试。</div>
			</h3>
			<div id="register-msg"><spring:message code="${message }" text="${message }" /></div>
			<form:form modelAttribute="user" id="register-form" method="post" autocomplete="off">
				<div class="form-row">
					<label style="width:128px;">真实姓名:</label> <form:input path="name" autocomplete="off" />
				</div>
				<div class="form-row">
					<label style="width:128px;">身份证号码:</label> <form:input path="idCardNo" autocomplete="off" />
				</div>
				<div class="form-row">
					<label style="width:128px;">居住地址:</label> <form:input path="address" autocomplete="off" />
				</div>
				<div class="form-row">
					<label style="width:128px;">电子邮箱:</label> <form:input path="email" autocomplete="off" />
				</div>
				<!--<div class="form-row">
					 <label>选择销售平台</label> <input type="checkbox" name="salePlatform" value="" /> eBay <input type="checkbox" name="salePlatform" value="" /> Amazon <input type="checkbox" name="salePlatform" value="" /> Aliexpress 
					&nbsp;
				</div>-->
				<div class="form-row">
					<label style="width:128px;">登录密码:</label> <input type="password" name="password" value="" autocomplete="off" /> <span class="help">至少6位</span>
				</div>
				<div class="form-row">
					<label style="width:128px;">确认密码:</label> <input type="password" name="passwordConfirmed" value="" autocomplete="off" />
				</div>
				<div class="form-row">
					<label style="width:128px;">验证码</label> <input type="text" name="captchaCode" autocomplete="off" /> 
					<img id="captchaCodeImg" src="${pageContext.request.contextPath }/captcha" style="vertical-align:middle;cursor:pointer" title="看不清，请单击图片" />
				</div>
				<div class="form-row">
					<label style="width:128px;">手机号码:</label> <form:input path="mobile" autocomplete="off" /> 
					<button type="button" id="authCodeBtn" class="x-btn" style="width:auto;">获取短信验证码</button>
					<span id="msg" style="display:none"><strong id="timer"></strong>秒后重新获取</span>
				</div>
				<div class="form-row">
					<label style="width:128px;">短信验证码:</label> <input type="text" name="authCode" id="authCode" value="" autocomplete="off" />
				</div>
				<div class="form-row">
					<label style="width:128px;">推荐人手机/邀请码:</label> <input type="text" name="referrerMobile" id="referrerMobile" value="" autocomplete="off" />
				</div>
				<div class="form-row">
					<label style="width:128px;">代理商手机号:</label> <input type="text" name="agentMobile" id="agentMobile" value="" autocomplete="off" />
				</div>
				<div class="form-row" >
					<label style="width:128px;">平台信息获取渠道:</label> 
					<div style="width:800px;">
						<label>网络广告</label>
							<input type="checkbox" name="infoAcquisitionChannel"  value="网络广告" autocomplete="off" />
						<label>网络新闻</label>
							<input type="checkbox" name="infoAcquisitionChannel"  value="网络新闻" autocomplete="off" />
						<label>外贸论坛</label>
							<input type="checkbox" name="infoAcquisitionChannel"  value="外贸论坛" autocomplete="off" />
						<label>户外广告</label>
							<input type="checkbox" name="infoAcquisitionChannel"  value="户外广告" autocomplete="off" />
						<label>朋友介绍</label>
							<input type="checkbox" name="infoAcquisitionChannel"  value="朋友介绍" autocomplete="off" />
						<label>报刊杂志</label>
							<input type="checkbox" name="infoAcquisitionChannel"  value="报刊杂志" autocomplete="off" />
						<label>培训会议</label>
							<input type="checkbox" name="infoAcquisitionChannel"  value="培训会议" autocomplete="off" />
						<label>传单</label>
							<input type="checkbox" name="infoAcquisitionChannel"  value="传单" autocomplete="off" />
						<label>展会</label>
							<input type="checkbox" name="infoAcquisitionChannel"  value="展会" autocomplete="off" />
						<label>Q群或微信</label>
							<input type="checkbox" name="infoAcquisitionChannel"  value="Q群或微信" autocomplete="off" />
						<label>其他</label>
							<input type="checkbox" name="infoAcquisitionChannel"  value="其他" autocomplete="off" />
					</div>
				</div>
				<div class="form-row">
					<label style="width:128px;">&nbsp;</label>
					<a href="http://chinasalestore.com/service.html" target="_blank">《CSS服务协议》</a><span>（请阅读后注册）</span>
				</div>
				<div class="form-row">
					<label style="width:128px;">&nbsp;</label>
					<button type="submit" class="x-btn">同意协议，并注册</button>
				</div>
			</form:form>
		</div>
	</div>

	<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/jquery.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath }/resources/master.js" type="text/javascript"></script>
	<script>
		$(function() {
			$('#register-form').validate({
				errorElement : 'span', //default input error message container
				errorClass : 'help-block error', // default input error message class
				focusInvalid : false, // do not focus the last invalid input
				ignore : "", // validate all fields including form hidden input
				messages : {
					mobile: {remote:'此手机号已被使用', regex: '请输入正确手机号'},
					idCardNo: {remote:'此身份证号已被使用', regex: '请正确输入身份证号'},
					email: {remote:'此电子邮箱已被使用'},
					authCode: {remote: '验证码不正确'},
					passwordConfirmed: {equalTo: '确认密码输入不符'},
					infoAcquisitionChannel:{required:"请选择平台信息获取渠道"},
					captchaCode: {remote: '验证码错误'},
					agentMobile: {remote: '不是代理商账号或者输入不正确'}
				},
				errorPlacement:function(error,element){  //指定错误信息位置
					if(element.is(':checkbox')){
						error.appendTo(element.parent());  //将错误信息添加到当前元素的父结点后面
					} else {
						error.insertAfter(element);
					}
				},
				rules : {
					name : {required : true},
					address: {required : true},
					password: {required : true, minlength: 6,maxlength:20},
					passwordConfirmed: {required : true, equalTo : 'input[name=password]'},
					infoAcquisitionChannel:{required : true},
					email: {
						required : true,
						email: true,
						remote: {
							url: "${pageContext.request.contextPath }/user/check.json"
						}
					},
					mobile: {
						required : true,
						digits: true,
						regex: xfy.regex.mobile,
						remote: {
							url: "${pageContext.request.contextPath }/user/check.json"
						}
					},
					idCardNo: {
						required : true,
						/* minlength: 18,
						maxlength: 18, */
						regex: "(^[0-9]{15}$)|(^[0-9]{17}([0-9Xx])$)",
						remote: {
							url: "${pageContext.request.contextPath }/user/check.json"
						}
					},
					authCode: {
						required : true,
						minlength: 4,
						maxlength: 4,
						remote: {
							url: "${pageContext.request.contextPath }/check-auth-code.json",
							data: {
								mobile: function () {
									return $("#mobile").val();
								}
							}
						}
						
					},
					captchaCode: {
						required: true,
						minlength: 4,
						maxlength: 4,
						remote: {
							url: '${pageContext.request.contextPath }/captcha'
						}
					},
					agentMobile: {
						required: false,
						regex: xfy.regex.mobile,
						remote: {
							url: '${pageContext.request.contextPath }/user/check-agent-mobile.json'
						}
					}
					
				}
	
			});
			
			  var count=0;
			  var flag=null;
			  var val = null;
			  var $codeBtn = $('#authCodeBtn');
			  function sendMobileCode(){
			    if(count == 0){
				    $codeBtn.attr("disabled",false);
				    $('#timer').text(count);
				    $codeBtn.removeClass("code-btn");
				    clearInterval(flag);
				    $codeBtn.text("获取短信验证码");
			    }else{
			    	$('#timer').text(count--);
				    val = $('#msg').text();
				    $codeBtn.text(val);
			    }
			  } 
			
			$('#authCodeBtn').click(function() {
				var valid = $('#register-form').validate().element($('input[name=mobile]'));
				var valid2 = $('#register-form').validate().element($('input[name=captchaCode]'));
				
				// 手机号验证通过后再能申请发送验证码
				if (valid === true && valid2 === true) {
					var url = '${pageContext.request.contextPath}/get-auth-code.json?mobile=' + $('#mobile').val();
					$.get(url, function(r){
						alert(r === true ? '验证码已发送成功，请留意手机短信！' : '验证码发送失败，请重试，或检查网络。');
						if(r === true & count==0){
							count=60;
							$('#timer').text(count);
							val = $('#msg').text();
							$codeBtn.text(val);
							flag=setInterval(sendMobileCode,1000);
							$codeBtn.attr("disabled",true);
							$codeBtn.addClass("code-btn");
						}
						else{
							return;
						} 
					}, 'json');
					
				} 
			});
			
			$('#captchaCodeImg').click(function() {
				var $this = $(this);
				var src = $this.attr('src');
				$this.attr('src', src + (src.indexOf('?') == -1 ? '?' : '') + '' + new Date().getTime());
				$('input[name=captchaCode]').val('');
			});
	
			$('#mobile').focus(function() {
				$('.reg-flow').removeClass('reg-flow-1');
				$('.reg-flow').removeClass('reg-flow-3');
				$('.reg-flow').addClass('reg-flow-2');
			});

		});
	</script>
</body>
</html>