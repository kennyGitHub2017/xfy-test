<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
	<h4 class="modal-title">供应商信息</h4>
</div>
<form:form id="data_form" modelAttribute="supplier" action="${pageContext.request.contextPath}/supplier/save" cssClass="form-horizontal" role="form" method="post">

<div class="modal-body form-group-sm">
	
		<input type="hidden" name="id" value="${supplier.id }" />
		<div class="row">
			<div class="col-md-4">
				<div class="form-group">
					<label class="col-md-4 control-label">名称</label>
					<div class="col-md-8">
						<form:input path="companyName" cssClass="form-control" maxlength="35" />
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="form-group">
					<label class="col-md-4 control-label">简称</label>
					<div class="col-md-8">
						<form:input path="shortName" cssClass="form-control" maxlength="10" />
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="form-group">
					<label class="col-md-4 control-label">供应商编码</label>
					<div class="col-md-8">
						<form:input path="code" cssClass="form-control" maxlength="15" />
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-4">
				<div class="form-group">
					<label class="col-md-4 control-label">联系人</label>
					<div class="col-md-8">
						<form:input path="contactName" cssClass="form-control" maxlength="20" />
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="form-group">
					<label class="col-md-4 control-label">联系电话</label>
					<div class="col-md-8">
						<form:input path="contactTel" cssClass="form-control" maxlength="20" />
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="form-group">
					<label class="col-md-4 control-label">联系手机</label>
					<div class="col-md-8">
						<form:input path="contactMobile" cssClass="form-control" maxlength="25" />
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-4">
				<div class="form-group">
					<label class="col-md-4 control-label">联系邮箱</label>
					<div class="col-md-8">
						<form:input path="contactMail" cssClass="form-control" maxlength="35" />
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="form-group">
					<label class="col-md-4 control-label">传真</label>
					<div class="col-md-8">
						<form:input path="contactFax" cssClass="form-control" maxlength="100" />
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="form-group">
					<label class="col-md-4 control-label">网址</label>
					<div class="col-md-8">
						<form:input path="url" cssClass="form-control" maxlength="150" />
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-4">
				<div class="form-group">
					<label class="col-md-4 control-label">所在城市</label>
					<div class="col-md-8">
						<form:input path="city" cssClass="form-control" maxlength="100" />
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="form-group">
					<label class="col-md-4 control-label">地址</label>
					<div class="col-md-8">
						<form:input path="contactAddress" cssClass="form-control" maxlength="100" />
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="form-group">
					<label class="col-md-4 control-label">邮编</label>
					<div class="col-md-8">
						<form:input path="postCode" cssClass="form-control" maxlength="100" />
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-4">
				<div class="form-group">
					<label class="col-md-4 control-label">开户行</label>
					<div class="col-md-8">
						<form:input path="bankName" cssClass="form-control" maxlength="100" />
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="form-group">
					<label class="col-md-4 control-label">开户名</label>
					<div class="col-md-8">
						<form:input path="bankAccountName" cssClass="form-control" maxlength="100" />
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="form-group">
					<label class="col-md-4 control-label">银行帐号</label>
					<div class="col-md-8">
						<form:input path="bankAccountCode" cssClass="form-control" maxlength="100" />
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-4">
				<div class="form-group">
					<label class="col-md-4 control-label">采购员</label>
					<div class="col-md-8">
						<form:select path="buyerId" cssClass="form-control">
							<option value="">请选择</option>
							<form:options items="${purchaseUsers }" itemLabel="name" itemValue="userId" />
						</form:select>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="form-group">
					<label class="col-md-4 control-label">开发员</label>
					<div class="col-md-8">
						<form:select path="developerId" cssClass="form-control">
							<option value="">请选择</option>
							<form:options items="${developUsers }" itemLabel="name" itemValue="userId" />
						</form:select>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="form-group">
					<label class="col-md-4 control-label">优先级</label>
					<div class="col-md-8">
						<form:select path="priority" cssClass="form-control">
							<option value="">请选择</option>
							<form:option value="A">A</form:option>
							<form:option value="B">B</form:option>
							<form:option value="C">C</form:option>
							<form:option value="D">D</form:option>
						</form:select>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-4">
				<div class="form-group">
					<label for="dict_code" class="col-md-4 control-label">类型</label>
					<div class="col-md-8">
						<form:select path="type" cssClass="form-control">
							<option value="">请选择</option>
							<form:option value="1">厂商</form:option>
							<form:option value="2">贸易商</form:option>
							<form:option value="3">电商</form:option>
							<form:option value="4">淘宝商</form:option>
						</form:select>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="form-group">
					<label for="dict_code" class="col-md-4 control-label">加入日期</label>
					<div class="col-md-8">
						<div class="input-group date date-picker" data-date-format="yyyy-mm-dd">
							<form:input path="startDate" cssClass="form-control" />
							<span class="input-group-btn">
							<button class="btn default btn-sm" type="button"><i class="fa fa-calendar"></i></button>
							</span>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="form-group">
					<label class="col-md-4 control-label">备注</label>
					<div class="col-md-8">
						<form:input path="note" cssClass="form-control" maxlength="500" />
					</div>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-md-4">
				<div class="form-group">
					<label for="dict_code" class="col-md-4 control-label">付款方式</label>
					<div class="col-md-8">
						<form:select path="payMethod" cssClass="form-control">
							<form:option value="">请选择</form:option>
							<form:option value="月结">月结</form:option>
							<form:option value="周结">周结</form:option>
							<form:option value="货到转账">货到转账</form:option>	
							<form:option value="快递代收">快递代收</form:option>
							<form:option value="款到发货">款到发货</form:option>	
							<form:option value="支付宝">支付宝</form:option>
						</form:select>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="form-group">
					<label class="col-md-4 control-label">采购周期</label>
					<div class="col-md-8">
						<form:input path="buyPeriod" cssClass="form-control" maxlength="500" />
					</div>
				</div>
			</div>
		</div>
</div>

<div class="modal-footer">
	<button type="button" class="btn default" data-dismiss="modal"><spring:message code="g.label.close"/></button>
	<c:if test="${param.view != 1 }">
	<button type="submit" class="btn blue"><spring:message code="g.label.save"/></button>
	</c:if>
</div>

</form:form>
<script>
$('.date-picker').datepicker({
	orientation: "right",
	autoclose: true
});
var form1 = $('#data_form');

form1.validate({
	errorElement : 'span', //default input error message container
	errorClass : 'help-block help-block-error', // default input error message class
	focusInvalid : false, // do not focus the last invalid input
	ignore : "", // validate all fields including form hidden input
	messages : {},
	rules : {
		companyName : { required : true},
		contactName: { required : true },
		contactTel: { required : true },
		buyerId: { required : true },
		developerId: { required : true },
		url: { url : true },
		contactMail: { email : true },
		type: { required : true },
		priority: { required : true },
		buyPeriod: { required : true, digits: true },
		code:{
			remote:{
				url:'${pageContext.request.contextPath }/supplier/checkSupplier',
				type:'post',
				dataType: "json",
				data:{
					code:function(){
						return $('#code').val();
					},
					id:function(){
						return "${supplier.id}";
					}
				}
				
			}
		}

		
	},
	
	messages : {
		code : {remote:"编码已经存在！"}
			
	},
	

	highlight : function(element) { // hightlight error inputs
		$(element).closest('.form-group').addClass('has-error'); // set error class to the control group
	},

	unhighlight : function(element) { // revert the change done by hightlight
		$(element).closest('.form-group').removeClass('has-error'); // set error class to the control group
	},

	success : function(label) {
		label.closest('.form-group').removeClass('has-error'); // set success class to the control group
	}

});
</script>