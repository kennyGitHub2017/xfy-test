<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<c:set var="pageTitle" value="${shippingName } - 地址配置" />
<tiles:insertDefinition name="emptyMetronicTemplate">
	<tiles:putAttribute name="title">${pageTitle }</tiles:putAttribute>

	<tiles:putAttribute name="page-content">
		<div class="row">
			<!-- BEGIN SAMPLE FORM PORTLET-->
			<div class="portlet light">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-cogs font-green-sharp"></i> <span class="caption-subject font-green-sharp bold uppercase">${pageTitle }</span>
					</div>
				</div>
				<div class="portlet-body form">
					<%@ include file="/WEB-INF/view/include/message.jsp"%>

					<form:form id="data_form" modelAttribute="address" action="${pageContext.request.contextPath}/shipping/address" cssClass="form-horizontal form-group-sm" role="form" method="post">
						<div class="form-body">
							<form:hidden path="shippingId"/>

							<h4>默认揽货地址</h4>
							<div class="form-group">
								<label class="col-md-3 control-label">联系人</label>
								<div class="col-md-4">
									<form:input path="name" cssClass="form-control" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label">公司名称</label>
								<div class="col-md-4">
									<form:input path="company" cssClass="form-control" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label">国家</label>
								<div class="col-md-4">
									<form:input path="country" cssClass="form-control" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label">省</label>
								<div class="col-md-4">
									<form:input path="province" cssClass="form-control" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label">市</label>
								<div class="col-md-4">
									<form:input path="city" cssClass="form-control" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label">区</label>
								<div class="col-md-4">
									<form:input path="district" cssClass="form-control" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label">街道地址</label>
								<div class="col-md-4">
									<form:input path="street" cssClass="form-control" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label">邮政编码</label>
								<div class="col-md-4">
									<form:input path="postcode" cssClass="form-control" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label">手机号码</label>
								<div class="col-md-4">
									<form:input path="mobile" cssClass="form-control" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label">电子邮件</label>
								<div class="col-md-4">
									<form:input path="email" cssClass="form-control" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label">固定电话</label>
								<div class="col-md-4">
									<form:input path="telphone" cssClass="form-control" />
								</div>
							</div>

							<h4>英文退货地址</h4>
							<div class="form-group">
								<label class="col-md-3 control-label">联系人</label>
								<div class="col-md-4">
									<form:input path="enReturnName" cssClass="form-control" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label">公司名称</label>
								<div class="col-md-4">
									<form:input path="enReturnCompany" cssClass="form-control" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label">国家</label>
								<div class="col-md-4">
									<form:input path="enReturnCountry" cssClass="form-control" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label">省</label>
								<div class="col-md-4">
									<form:input path="enReturnProvince" cssClass="form-control" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label">市</label>
								<div class="col-md-4">
									<form:input path="enReturnCity" cssClass="form-control" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label">区</label>
								<div class="col-md-4">
									<form:input path="enReturnDistrict" cssClass="form-control" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label">街道地址</label>
								<div class="col-md-4">
									<form:input path="enReturnStreet" cssClass="form-control" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label">邮政编码</label>
								<div class="col-md-4">
									<form:input path="enReturnPostcode" cssClass="form-control" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label">手机号码</label>
								<div class="col-md-4">
									<form:input path="enReturnMobile" cssClass="form-control" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label">电子邮件</label>
								<div class="col-md-4">
									<form:input path="enReturnEmail" cssClass="form-control" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label">固定电话</label>
								<div class="col-md-4">
									<form:input path="enReturnTelphone" cssClass="form-control" />
								</div>
							</div>
							
							<h4>中文退货地址</h4>
							<div class="form-group">
								<label class="col-md-3 control-label">联系人</label>
								<div class="col-md-4">
									<form:input path="cnReturnName" cssClass="form-control" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label">公司名称</label>
								<div class="col-md-4">
									<form:input path="cnReturnCompany" cssClass="form-control" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label">国家</label>
								<div class="col-md-4">
									<form:input path="cnReturnCountry" cssClass="form-control" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label">省</label>
								<div class="col-md-4">
									<form:input path="cnReturnProvince" cssClass="form-control" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label">市</label>
								<div class="col-md-4">
									<form:input path="cnReturnCity" cssClass="form-control" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label">区</label>
								<div class="col-md-4">
									<form:input path="cnReturnDistrict" cssClass="form-control" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label">街道地址</label>
								<div class="col-md-4">
									<form:input path="cnReturnStreet" cssClass="form-control" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label">邮政编码</label>
								<div class="col-md-4">
									<form:input path="cnReturnPostcode" cssClass="form-control" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label">手机号码</label>
								<div class="col-md-4">
									<form:input path="cnReturnMobile" cssClass="form-control" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label">电子邮件</label>
								<div class="col-md-4">
									<form:input path="cnReturnEmail" cssClass="form-control" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label">固定电话</label>
								<div class="col-md-4">
									<form:input path="cnReturnTelphone" cssClass="form-control" />
								</div>
							</div>
							
						</div>

						<div class="form-actions">
							<div class="form-group">
								<div class="col-md-offset-3 col-md-9">
									<button type="submit" class="btn green"><spring:message code="g.label.save" /></button>
								</div>
							</div>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>

