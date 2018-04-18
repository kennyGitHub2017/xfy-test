<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<tiles:insertDefinition name="metronicTemplate">
	<tiles:putAttribute name="css-page">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-datepicker/css/datepicker3.css"/>
	</tiles:putAttribute>
	<tiles:putAttribute name="title">
		${not empty sysinfo.id ?'查看':'编辑' }系统资讯
	</tiles:putAttribute>
<tiles:putAttribute name="page-content">
<form:form id="data_form" modelAttribute="sysinfo" action="${pageContext.request.contextPath}/sysinfo/edit" cssClass="form-horizontal" role="form" method="post">
<div class="modal-body">
		<form:hidden path="id" />
		<div class="form-group form-group-sm">
			<label for="dict_code" class="col-md-3 control-label">标题</label>
			<div class="col-md-6">
				<form:input path="title" cssClass="form-control" />
			</div>
		</div>
		<div class="form-group form-group-sm">
			<label for="dict_code" class="col-md-3 control-label">类型</label>
			<div class="col-md-6">
				<input type="radio" id="nb" <c:if test="${empty sysinfo.type || sysinfo.type==1 }">checked="checked"</c:if>  name="type" value="1" /> <label for="nb">内部</label>
				<input type="radio"  id="mj" <c:if test="${sysinfo.type==0 }">checked="checked"</c:if>  name="type" value="0" /><label for="mj">卖家</label>
			</div>
		</div>
		<div class="form-group form-group-sm">
			<label for="inputPassword12" class="col-md-3 control-label">内容</label>
			<div class="col-md-6">
				<form:textarea path="content" rows="10"  cols="100" />
			</div>
		</div>
</div>

<div class="modal-footer">
	<button type="submit" class="btn blue">保存</button>
</div>

</form:form>
</tiles:putAttribute>
<tiles:putAttribute name="js-page">
<script>
var form1 = $('#data_form');

form1.validate({
	errorElement : 'span', //default input error message container
	errorClass : 'help-block help-block-error', // default input error message class
	focusInvalid : false, // do not focus the last invalid input
	ignore : "", // validate all fields including form hidden input
	rules : {
		"title" : { required : true },
		"content" : { required : true }
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
</tiles:putAttribute>
</tiles:insertDefinition>