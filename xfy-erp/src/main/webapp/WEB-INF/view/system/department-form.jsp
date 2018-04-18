<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
	<h4 class="modal-title">部门信息</h4>
</div>
<form:form id="data_form" modelAttribute="department" action="${pageContext.request.contextPath}/department/${updateFlag == '1' ? 'update' : 'save' }" cssClass="form-horizontal" role="form" method="post">
<div class="modal-body">
	
		<%-- <input type="hidden" name="id" value="${department.id }" /> --%>
		<form:hidden path="id" />
		<div class="form-group form-group-sm">
			<label for="dict_code" class="col-md-3 control-label">部门名称</label>
			<div class="col-md-6">
				<form:input path="name" id="name" cssClass="form-control" />
			</div>
		</div>
		<div class="form-group form-group-sm">
			<label for="dict_code" class="col-md-3 control-label">部门领导</label>
			<div class="col-md-6">
				<form:select path="leaderId" cssClass="form-control"> 
					<option value="">请选择</option>
					<form:options items="${users }" itemLabel="name" itemValue="userId" />
				</form:select>
			</div>
		</div>
		<div class="form-group form-group-sm">
			<label for="inputPassword12" class="col-md-3 control-label">备注</label>
			<div class="col-md-6">
				<form:input path="note" cssClass="form-control" />
			</div>
		</div>
</div>

<div class="modal-footer">
	<button type="button" class="btn default" data-dismiss="modal"><spring:message code="g.label.cancel"/></button>
	<button type="submit" class="btn blue"><spring:message code="g.label.save"/></button>
</div>

</form:form>

<script>
var form1 = $('#data_form');

form1.validate({
	errorElement : 'span', //default input error message container
	errorClass : 'help-block help-block-error', // default input error message class
	focusInvalid : false, // do not focus the last invalid input
	ignore : "", // validate all fields including form hidden input
	messages : {
		name: {required:'请输入部门名称',remote:'此名称已存在,请重新填写'},
		leaderId: '请选择部门领导'
	},
	rules : {
		name : { required : true,
			remote: {
                url: "${pageContext.request.contextPath }/department/check",
                type: "post",
                dataType: "json",
                data: {
                	depName: function () {
                        return $("#name").val();//这个是取要验证的密码
                    },
                    id:function(){
                    	var id = "${department.id }";
                    	return id;	
                    }
                },
                dataFilter: function (data) {
                	var json = eval("("+data+")");
                    return !json.result;
                }
            }
		},
		leaderId : { required : true }
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