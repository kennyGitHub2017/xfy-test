<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style>
input.error {
	border: 1px solid red;
}

label.error {
	padding-left: 16px;
	padding-bottom: 2px;
	color: red;
}
</style>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<c:set scope="request" var="sysModule" value="system" />
<c:set scope="request" var="sysPage" value="shipping" />

<tiles:insertDefinition name="metronicTemplate">
	<tiles:putAttribute name="title">编辑发货方式</tiles:putAttribute>

	<tiles:putAttribute name="page-content">
		<div class="row">

			<%@ include file="/WEB-INF/view/include/message.jsp"%>

			<!-- BEGIN SAMPLE FORM PORTLET-->
			<div class="portlet light">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-cogs font-green-sharp"></i> <span class="caption-subject font-green-sharp bold uppercase">编辑发货方式</span>
					</div>

				</div>
				<div class="portlet-body form">

					<form class="form-horizontal" role="form" method="post" id="shipping_edit_form" action="${pageContext.request.contextPath}/shipping/${subFlag == '1' ? 'update' : 'add'}">

						<div class="form-body">
							<input type="hidden" name="id" value="${shippingKey.id}" />

							<div class="form-group">
								<label class="col-md-3 control-label">支持的eBay账号</label>
								<div class="col-md-6">
									<textarea class="form-control" rows="3" name="supportedAccounts">${shippingKey.supportedAccounts}</textarea>
									<span class="help-block"> 此处只支持输入账号对应的ID，请到账号管理页面查看ID </span>
								</div>
							</div>

							<div class="form-group">
								<label class="col-md-3 control-label">发货方式名称</label>
								<div class="col-md-9">
									<input type="text" name="shippingName" value="${shippingKey.shippingName}" class="form-control input-medium" placeholder="发货方式名称" />
								</div>
							</div>

							<div class="form-group">
								<label class="col-md-3 control-label">上传到ebay的名称</label>
								<div class="col-md-4">
									<input type="text" name="ebayValue" value="${shippingKey.ebayValue}" class="form-control" placeholder="上传到eBay的运送方式"> <span class="help-inline"> </span>
								</div>
							</div>

							<div class="form-group">
								<label class="col-md-3 control-label">上传到速卖通的名称</label>
								<div class="col-md-4">
									<input type="text" name="smtValue" value="${shippingKey.smtValue}" class="form-control" placeholder="请填写缩写如 CPAM表示Chin Post Air mail"> <span class="help-inline"> </span>
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-md-3 control-label">上传到wish的名称</label>
								<div class="col-md-4">
									<input type="text" name="wishValue" value="${shippingKey.wishValue}" class="form-control"> <span class="help-inline"> </span>
								</div>
							</div>

							<%--
							<div class="form-group">
								<label class="col-md-3 control-label">联系人国家</label>
								<div class="col-md-9">
									<input type="text" name="country" value="${shippingKey.country}" class="form-control input-inline input-medium" placeholder=""> <span class="help-inline"> </span>
								</div>
							</div>

							<div class="form-group">
								<label class="col-md-3 control-label">联系人省份</label>
								<div class="col-md-9">
									<input type="text" name="province" value="${shippingKey.province}" class="form-control input-inline input-medium" placeholder=""> <span class="help-inline"> </span>
								</div>
							</div>

							<div class="form-group">
								<label class="col-md-3 control-label">联系人城市</label>
								<div class="col-md-9">
									<input type="text" name="city" value="${shippingKey.city}" class="form-control input-inline input-medium" placeholder=""> <span class="help-inline"> </span>
								</div>
							</div>


							<div class="form-group">
								<label class="col-md-3 control-label">联系人姓名</label>
								<div class="col-md-9">
									<input type="text" name="name" value="${shippingKey.name}" class="form-control input-inline input-medium" placeholder=""> <span class="help-inline"> </span>
								</div>
							</div>


							<div class="form-group">
								<label class="col-md-3 control-label">联系人电话</label>
								<div class="col-md-9">
									<input type="text" name="tel" value="${shippingKey.tel}" class="form-control input-inline input-medium" placeholder=""> <span class="help-inline"> </span>
								</div>
							</div>


							<div class="form-group">
								<label class="col-md-3 control-label">联系人街道</label>
								<div class="col-md-4">
									<input type="text" name="street" value="${shippingKey.street}" class="form-control" placeholder="Enter text">
								</div>
							</div>


							<div class="form-group">
								<label class="col-md-3 control-label">回邮地址</label>
								<div class="col-md-6">
									<textarea class="form-control" name="address" rows="3">${shippingKey.address}</textarea>
								</div>
							</div>
							--%>

							<div class="form-group">
								<label class="col-md-3 control-label">物流公司代号</label>
								<div class="col-md-9">
									<input type="text" class="form-control input-inline input-medium" value="${shippingKey.carrierSn}" name="carrierSn" placeholder=""> <span class="help-inline"></span>
								</div>
							</div>


							<div class="form-group">
								<label class="col-md-3 control-label">签名</label>
								<div class="col-md-9">
									<input type="text" name="signature" value="${shippingKey.signature}" class="form-control input-inline input-medium" placeholder=""> <span class="help-inline"> </span>
								</div>
							</div>


							<div class="form-group">
								<label class="col-md-3 control-label">金额</label>
								<div class="col-md-2 input-inline">
									<input type="text" name="amountMin" value="${shippingKey.amountMin}" class="form-control input-inline input-x" placeholder=""> ~~ <input type="text" name="amountMax" value="${shippingKey.amountMax}" class="form-control input-inline  input-x" placeholder=""> <span class="help-inline"> USD </span>
								</div>
							</div>

							<div class="form-group">
								<label class="col-md-3 control-label">重量</label>
								<div class="col-md-2 input-inline">
									<input type="text" name="weightMin" value="${shippingKey.weightMin}" class="form-control input-inline input-x" placeholder=""> ~~ <input type="text" value="${shippingKey.weightMax}" name="weightMax" class="form-control input-inline  input-x" placeholder=""> <span class="help-inline"> KG </span>
								</div>
							</div>


							<div class="form-group">
								<label class="col-md-3 control-label">备注</label>
								<div class="col-md-4">
									<input type="text" value="${shippingKey.note}" name="note" class="form-control" placeholder=""> <span class="help-inline"> </span>
								</div>
							</div>


							<div class="form-group">
								<label class="col-md-3 control-label">优先级</label>
								<div class="col-md-9">
									<input type="text" value="${shippingKey.priority}" name="priority" class="form-control input-small" placeholder=""> <span class="help-inline"> 数字小，优先级高。如 0. 1, </span>
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-md-3 control-label">运费均价</label>
								<div class="col-md-9">
									<input type="text" value="${shippingKey.shipFeeAvg}" name="shipFeeAvg" class="form-control input-small" placeholder=""> <span class="help-inline"></span>
								</div>
							</div>

							<div class="form-group">
								<label class="col-md-3 control-label"></label>
								<div class="col-md-9">
									<div class="checkbox-list">
										<label class="checkbox-inline"> <input type="checkbox" value="1" name="isBattery" ${shippingKey.isBattery == 1 ? 'checked' : '' }>电子?
										</label> <label class="checkbox-inline"> <input type="checkbox" value="1" name="isRegulated" ${shippingKey.isRegulated == 1 ? 'checked' : '' }>管制?
										</label> <label class="checkbox-inline"> <input type="checkbox" value="1" name="isLiquid" ${shippingKey.isLiquid == 1 ? 'checked' : '' }>液体?
										</label> <label class="checkbox-inline"> <input type="checkbox" value="1" name="isMagnetic" ${shippingKey.isMagnetic == 1 ? 'checked' : '' }>磁性?
										</label> <label class="checkbox-inline"> <input type="checkbox" value="1" name="isCopyright" ${shippingKey.isCopyright == 1 ? 'checked' : '' }>侵权?
										</label>
									</div>
								</div>
							</div>


							<div class="form-group">
								<label class="col-md-3 control-label">包含国家</label>
								<div class="col-md-6">
									<textarea class="form-control" name="supportedCountries" rows="3">${shippingKey.supportedCountries}</textarea>
									<span class="help-block"> 在最后面需要加是英文,号结束，如何支持所有，请输入 ,any, </span>
								</div>
							</div>

							<div class="form-group">
								<label class="col-md-3 control-label">对应eBay运送方式</label>
								<div class="col-md-6">
									<textarea class="form-control" name="ebayShippings" rows="3">${shippingKey.ebayShippings}</textarea>
									<span class="help-block"> 在最后面需要加是英文,号结束，如何支持所有，请输入 ,any, </span>
								</div>
							</div>


							<div class="form-group">
								<label class="col-md-3 control-label">包含SKU</label>
								<div class="col-md-6">
									<textarea class="form-control" name="supportedSkus" rows="3">${shippingKey.supportedSkus}</textarea>
									<span class="help-block"> 在最后面需要加是英文,号结束，如何支持所有，请输入 ,any, </span>
								</div>
							</div>

						
						<div class="form-group">
								<label class="col-md-3 control-label">运费计算类型</label>
									<div class="col-md-2">
							<select class="form-control"  name="shippFeeType">
							<option value="">--请选择--</option>
							<option value="1" ${shippingKey.shippFeeType == 1 ? 'selected' : ''}>运费区间</option>
							<option value="0" ${shippingKey.shippFeeType == 0 ? 'selected' : ''}>首重/续重</option>
							</select>
							</div>
						</div>
	

						</div>

						<div class="form-actions">
							<div class="form-group">
								<div class="col-md-offset-3 col-md-9">
									<button type="button" class="btn green" onclick="subForm();"><spring:message code="g.label.save" /></button>
								</div>
							</div>
						</div>
	

					</form>
				</div>
			</div>
			<!-- END SAMPLE FORM PORTLET-->



			<script type="text/javascript">
				function subForm() {

					$().ready(function() {
						$("#shipping_edit_form").validate({
							errorClass : "error",
							rules : {
								shippingName : "required",
								priority : {
									maxlength : 1,
									required : false,
									number : true
								},
								amountMin : {
									required : true,
									number : true
								},
								amountMax : {
									required : true,
									number : true
								},
								weightMin : {
									required : true,
									number : true
								},
								weightMax : {
									required : true,
									number : true
								},
								shipFeeAvg:{
									number : true
								},
								shippFeeType : {
									required : true
								},
							},
							messages : {
								shippingName : "请输入发货方式名称",
							},
						});
					});

					$("#shipping_edit_form").submit()
				}
			</script>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>

