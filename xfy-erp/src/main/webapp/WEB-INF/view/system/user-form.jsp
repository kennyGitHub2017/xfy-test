<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
	<h4 class="modal-title">用户信息</h4>
</div>
<form:form id="data_form" modelAttribute="user" action="${pageContext.request.contextPath}/user/${updateFlag == '1' ? 'update' : 'save' }" cssClass="form-horizontal" role="form" method="post">

<div class="modal-body form-group-sm">
	
		<input type="hidden" name="userId" value="${user.userId }" />
		<%-- 更新时为当前记录sellerId，新增时为操作用户的sellerId --%>
		<input type="hidden" name="sellerId" value="${user.sellerId != null ? user.sellerId : sessionUser.sellerId }" />
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label for="dict_code" class="col-md-4 control-label">工号</label>
					<div class="col-md-8">
						<form:input path="code" cssClass="form-control" id="code" maxlength="10" />
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label for="dict_code" class="col-md-4 control-label">姓名</label>
					<div class="col-md-8">
						<form:input path="name" cssClass="form-control" />
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label for="dict_code" class="col-md-4 control-label">英文名</label>
					<div class="col-md-8">
						<form:input path="enName" cssClass="form-control" />
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label for="dict_code" class="col-md-4 control-label">性别</label>
					<div class="col-md-8">
						<form:select path="sex" cssClass="form-control">
							<option></option>
							<form:option value="Male">Male</form:option>
							<form:option value="Female">Female</form:option>
						</form:select>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label for="dict_code" class="col-md-4 control-label">部门</label>
					<div class="col-md-8">
						<form:select path="departmentId" cssClass="form-control">
							<option></option>
							<form:options items="${departments }" itemLabel="name" itemValue="id" />
						</form:select>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label for="dict_code" class="col-md-4 control-label">入职日期</label>
					<div class="col-md-8">
						<div class="input-group date date-picker" data-date-format="yyyy-mm-dd">
							<form:input path="entryDate" cssClass="form-control" />
							<span class="input-group-btn">
							<button class="btn default btn-sm" type="button"><i class="fa fa-calendar"></i></button>
							</span>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label for="dict_code" class="col-md-4 control-label">出生日期</label>
					<div class="col-md-8">
						<div class="input-group date date-picker" data-date-format="yyyy-mm-dd">
							<form:input path="birthDate" cssClass="form-control" />
							<span class="input-group-btn">
							<button class="btn default btn-sm" type="button"><i class="fa fa-calendar"></i></button>
							</span>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label for="dict_code" class="col-md-4 control-label">离职日期</label>
					<div class="col-md-8">
						<div class="input-group date date-picker" data-date-format="yyyy-mm-dd">
							<form:input path="resignDate" cssClass="form-control" />
							<span class="input-group-btn">
							<button class="btn default btn-sm" type="button"><i class="fa fa-calendar"></i></button>
							</span>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label for="dict_code" class="col-md-4 control-label">文化程度</label>
					<div class="col-md-8">
						<form:select path="education" cssClass="form-control">
							<option></option>
							<form:option value="1">高中</form:option>
							<form:option value="2">大专</form:option>
							<form:option value="3">本科</form:option>
							<form:option value="4">研究生</form:option>
							<form:option value="5">博士</form:option>
							<form:option value="6">其它</form:option>
						</form:select>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label for="dict_code" class="col-md-4 control-label">职务</label>
					<div class="col-md-8">
						<form:input path="position" cssClass="form-control" />
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label for="dict_code" class="col-md-4 control-label">电话</label>
					<div class="col-md-8">
						<form:input path="phone" cssClass="form-control" />
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label for="dict_code" class="col-md-4 control-label">移动电话</label>
					<div class="col-md-8">
						<form:input path="mobile" cssClass="form-control" />
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<div class="form-group">
					<label for="dict_code" class="col-md-2 control-label">身份证</label>
					<div class="col-md-10">
						<form:input path="idCardNo" cssClass="form-control" />
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<div class="form-group">
					<label for="dict_code" class="col-md-2 control-label">备注</label>
					<div class="col-md-10">
						<form:input path="note" cssClass="form-control" />
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<div class="form-group">
					<label for="dict_code" class="col-md-2 control-label">角色</label>
					<div class="col-md-10">
						<div class="row">
						<c:forEach var="item" items="${roles }">
							<%-- 选中已有角色 --%>
							<c:set var="checked" value="" />
							<c:set var="endFlag" value="100" />
							<c:forEach var="userRole" items="${user.roles }" end="${endFlag }">
								<c:if test="${userRole.id == item.id }">
									<c:set var="checked" value="checked" />
									<c:set var="endFlag" value="0" />
								</c:if>
								
							</c:forEach>
							
							<div class="col-md-4"><label><input type="checkbox" name="roleId" value="${item.id }" ${checked }> ${item.name } </label></div>
						</c:forEach>
						</div>
					</div>
				</div>
			</div>
		</div>
</div>

<div class="modal-footer">
	<button type="button" class="btn default" data-dismiss="modal"><spring:message code="g.label.cancel"/></button>
	<button type="submit" class="btn blue"><spring:message code="g.label.save"/></button>
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
	messages : {
		name: "请输入姓名",
		code: {remote:'此工号已被使用,请重新输入',required:"请输入工号"},
		mobile: {remote:'此手机号已被使用'},
		idCardNo: {remote:'此身份证号已被使用'}
	},
	rules : {
		code : { required : true,
			remote: {
				url: "${pageContext.request.contextPath }/user/check.json",
				data: {
					code: function () {
						return $("#code").val();
					},
					id:function(){
						return "${user.userId }";
					}
				}
			}
		},
		mobile : {
			digits:true,
			maxlength: 11,
			minlength: 11,
			remote: {
				url: "${pageContext.request.contextPath }/user/check.json",
				data: {
					mobile: function () {
						return $("#mobile").val();
					},
					id:function(){
						return "${user.userId }";
					}
				}
			}
		},
		idCardNo : {
			maxlength: 18,
			minlength: 18,
			remote: {
				url: "${pageContext.request.contextPath }/user/check.json",
				data: {
					icCardNo: function () {
						return $("#idCardNo").val();
					},
					id:function(){
						return "${user.userId }";
					}
				}
			}
		},
		name : { required : true }
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

// 处理checkbox 样式
xfy.initUniformCheckbox();
</script>