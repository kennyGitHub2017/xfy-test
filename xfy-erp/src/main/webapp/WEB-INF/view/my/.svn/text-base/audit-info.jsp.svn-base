<%--
卖家提交审核资料
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<c:set scope="request" var="sysModule" value="order" />
<c:set scope="request" var="sysPage" value="order-list" />

<c:set var="pageTitle" value="提交审核资料" />
<tiles:insertDefinition name="emptyMetronicTemplate">
	<tiles:putAttribute name="title">${pageTitle }</tiles:putAttribute>

	<%-- 页面级别的 CSS --%>
	<%-- <tiles:putAttribute name="css-page"></tiles:putAttribute> --%>

	<tiles:putAttribute name="page-content">
		<div class="row">
			<div class="col-md-12">
				<div class="portlet light">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-cogs font-green-sharp"></i> <span class="caption-subject font-green-sharp bold uppercase">${pageTitle }</span>
						</div>
						<div class="tools">
							<a href="javascript:;" class="collapse"></a>
						</div>
					</div>
					<div class="portlet-body form">
						
						<%@include file="/WEB-INF/view/include/message.jsp" %>
						
						<form id="audit-info-form" class="form-horizontal" role="form" method="post" enctype="multipart/form-data">
							<input type="hidden" name="id" value="${seller.id }" />
							<div class="form-body">
								<div class="form-group">
									<label for="dict_code" class="col-md-3 control-label">认证类型</label>
									<div class="col-md-4">
										<select class="form-control" name="type" id="seller-type-selector">
											<option value="">请选择认证类型</option>
											<option value="0">个人用户认证</option>
											<option value="1">公司用户认证</option>
										</select>
									</div>
								</div>
								<div id="audit-info-panel" hidden>
									<h3>基础信息</h3>
									<div class="form-group">
										<label for="dict_code" class="col-md-3 control-label">联系人</label>
										<div class="col-md-4">
											<input name="contacts" class="form-control" value="${seller.contacts }" />
										</div>
									</div>
									<div class="form-group">
										<label for="dict_code" class="col-md-3 control-label">身份证号</label>
										<div class="col-md-4">
											<input name="idCardNo" class="form-control" value="${seller.idCardNo }" />
										</div>
									</div>
									<div class="form-group">
										<label for="dict_code" class="col-md-3 control-label">手机</label>
										<div class="col-md-4">
											<input name="mobile" class="form-control" value="${seller.mobile }" />
										</div>
									</div>
									<div class="form-group">
										<label for="dict_code" class="col-md-3 control-label">邮箱</label>
										<div class="col-md-4">
											<input name="email" class="form-control" value="${seller.email }" />
										</div>
									</div >
									<div class="form-group">
										<label for="dict_code" class="col-md-3 control-label">QQ</label>
										<div class="col-md-4">
											<input name="qqNo" class="form-control" value="${seller.qqNo}" />
										</div>
									</div>
									<div class="form-group">
										<label for="dict_code" class="col-md-3 control-label">地址</label>
										<div class="col-md-4">
											<input name="address" class="form-control" value="${seller.address }" />
										</div>
									</div>
									<div class="form-group">
										<label for="dict_code" class="col-md-3 control-label">销售平台</label>
										<div class="col-md-4">
											<input type="hidden" name="platforms" />
											<div class="checkbox-list">
												<label class="checkbox-inline">
												<input type="checkbox" value="ebay" name="platformsCheckbox"> eBay </label>
												<label class="checkbox-inline">
												<input type="checkbox" value="amazon" name="platformsCheckbox"> Amazon </label>
												<label class="checkbox-inline">
												<input type="checkbox" value="smt" name="platformsCheckbox"> 速卖通 </label>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label for="dict_code" class="col-md-3 control-label">店铺名/URL</label>
										<div class="col-md-4">
											<input name="shop" class="form-control" value="${seller.shop }" />
										</div>
									</div>
									<h3>认证信息</h3>
									<div class="form-group com-type-info">
										<label for="dict_code" class="col-md-3 control-label">公司名称</label>
										<div class="col-md-4">
											<input name="comName" class="form-control" value="${seller.comName }" />
										</div>
									</div>
									<div class="form-group com-type-info">
										<label for="dict_code" class="col-md-3 control-label">法人代表</label>
										<div class="col-md-4">
											<input name="comLegalPerson" class="form-control" value="${seller.comLegalPerson }" />
										</div>
									</div>
									<div class="form-group com-type-info">
										<label for="dict_code" class="col-md-3 control-label">组织机构代码</label>
										<div class="col-md-4">
											<input name="comCode" class="form-control" value="${seller.comCode }" />
										</div>
									</div>
									<div class="form-group com-type-info">
										<label for="dict_code" class="col-md-3 control-label">营业执照</label>
										<div class="col-md-4">
											<input type="file" name="bizLicenseImg" class="form-control" />
											<span class="help-block">最大5M，只能上传 jpg, png, gif文件 </span>
										</div>
									</div>
									<div class="form-group person-type-info">
										<label for="dict_code" class="col-md-3 control-label">身份证正面</label>
										<div class="col-md-4">
											<input type="file" name="idCardImg1" class="form-control" />
											<span class="help-block">最大5M，只能上传 jpg, png, gif文件 </span>
										</div>
									</div>
									<div class="form-group person-type-info">
										<label for="dict_code" class="col-md-3 control-label">身份证反面</label>
										<div class="col-md-4">
											<input type="file" name="idCardImg2" class="form-control" />
											<span class="help-block">最大5M，只能上传 jpg, png, gif文件 </span>
										</div>
									</div>
									<div class="form-group person-type-info">
										<label for="dict_code" class="col-md-3 control-label">个人照片</label>
										<div class="col-md-4">
											<input type="file" name="photoImg" class="form-control" />
											<span class="help-block">最大5M，只能上传 jpg, png, gif文件 </span>
										</div>
									</div>
									
								<div class="form-actions">
									<div class="col-md-offset-3 col-md-9">
										<button type="submit" class="btn green"><spring:message code="g.label.submit"/></button>
									</div>
								</div>
				
								</div>
							</div>
				<span>小提示：部分使用360浏览器用户，如遇到浏览器无法正常显示页面问题，请按以下方式解决：1.360浏览器用户，请点击浏览器地址栏右侧，选择 极速模, 2.改用IE或火狐等浏览器进行注册</span>
						</form>
					</div>
				</div>

			</div>
		</div>
	</tiles:putAttribute>

	<%-- 页面级别的 JS --%>
	<tiles:putAttribute name="js-page">
		<script>
		$(function() {
			$('#seller-type-selector').change(function() {
				resetForm();
				var type = $(this).val();
				if (type == '') {
					$('#audit-info-panel').hide();
				} else {
					$('#audit-info-panel').show();
					
					if (type == 0) {
						$('.com-type-info').hide();
						$('.person-type-info').show();
					} else {
						$('.com-type-info').show();
						$('.person-type-info').hide();
					}
				}
			});
			
			$('#audit-info-form').validate({
				errorElement : 'span', 
				errorClass : 'help-block help-block-error', 
				focusInvalid : false,
				ignore : "",
				messages : {
					name: "请输入姓名",
					code: {remote:'此工号已被使用,请重新输入',required:"请输入工号"},
					mobile: {remote:'此手机号已被使用', regex: '请输入正确手机号'},
					idCardNo: {remote:'此身份证号已被使用'}
				},
				rules : {
					type: { required: true },
					qqNo:{required: true,minlength:6,digits:true},
					contacts: { required: true },
					idCardNo: { required: true },
					mobile: { required: true, regex: xfy.regex.mobile },
					email: { required: true },
					address: { required: true },
					comName: { required: function() { return $('#seller-type-selector').val() === '1'; } },
					comLegalPerson: { required: function() { return $('#seller-type-selector').val() === '1'; } },
					comCode: { required: function() { return $('#seller-type-selector').val() === '1'; } },
					idCardImg1: { required: function() { return $('#seller-type-selector').val() === '0'; } },
					idCardImg2: { required: function() { return $('#seller-type-selector').val() === '0'; } },
					photoImg: { required: function() { return $('#seller-type-selector').val() === '0'; } },
					bizLicenseImg: { required: function() { return $('#seller-type-selector').val() === '1'; } },
				},

				highlight : function(element) {
					$(element).closest('.form-group').addClass('has-error');
				},

				unhighlight : function(element) {
					$(element).closest('.form-group').removeClass('has-error');
				},

				success : function(label) {
					label.closest('.form-group').removeClass('has-error');
					label.closest('.form-group').addClass('has-success');
				}

			});
			
			$('input[type=file]').change(function() {
				var filename = $(this).val();
				if (filename.length > 0) { 
					var fileExtension = filename.substring(filename.lastIndexOf('.') + 1).toLowerCase();
					
					if (fileExtension != 'png' && fileExtension != 'jpg' && fileExtension != 'gif') {
						alert('只能上传 png 或者  jpg 或者 gif 格式的图片');
						$(this).val('');
						$(this).focus();
						return;
					}
				}
			});
			
			function resetForm() {
				var type = $('#seller-type-selector').val();
				$('#audit-info-form')[0].reset();
				$('#seller-type-selector').val(type);
			}
			
			$('input[name=platformsCheckbox]').click(function() {
				$('input[name=platforms]').val(xfy.getCheckedValues('platformsCheckbox'));
				
			});
		});
		</script>
	</tiles:putAttribute>
</tiles:insertDefinition>