<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="modal-header">
	<h4 class="modal-title">公告</h4>
</div>
<form:form id="data_form" modelAttribute="notice" action="${pageContext.request.contextPath}/notice/save" cssClass="form-horizontal" role="form" method="post">

<div class="modal-body form-group">
	<input type="hidden" name="id" value="${notice.id }" />
	<input type="hidden" name="type" value="${notice.type == null ? 1 : notice.type }" />
	<div class="col-md-12">
		<div class="form-group">
			<label class="col-md-1 control-label">标题</label>
			<div class="col-md-8">
				<form:input path="title" cssClass="form-control" />
			</div>
		</div>
	</div>
	<div class="col-md-12">
		<div class="form-group">
			<label for="dict_code" class="col-md-1 control-label">有效时间</label>
			<div class="col-md-4">
				<div class="input-group date form_datetime" data-date-format="yyyy-mm-dd HH:mm:ss">
					<form:input path="startTime" cssClass="form-control" readonly="true" />
					<span class="input-group-btn">
					<button class="btn default date-reset" type="button"><i class="fa fa-times"></i></button>
					<button class="btn default date-set" type="button"><i class="fa fa-calendar"></i></button>
					</span>
				</div>
			</div>
			<div class="col-md-4">
				<div class="input-group date form_datetime" data-date-format="yyyy-mm-dd HH:mm:ss">
					<form:input path="endTime" cssClass="form-control" readonly="true" />
					<span class="input-group-btn">
					<button class="btn default date-reset" type="button"><i class="fa fa-times"></i></button>
					<button class="btn default date-set" type="button"><i class="fa fa-calendar"></i></button>
					</span>
				</div>
			</div>
		</div>
	</div>
	<div class="col-md-12">
		<div class="form-group">
			<label class="col-md-1 control-label">内容</label>
			<div class="col-md-11">
				<div id="summernote">${notice.content }</div>
				<form:input path="content" cssClass="form-control" style="visibility: hidden;width:0;height:0;" />
			</div>
		</div>
	</div>
</div>

<div class="modal-footer">
	<button type="button" class="btn default" id="dismiss-modal"><spring:message code="g.label.close"/></button>
	<c:if test="${param.view != 1 }">
	<button type="submit" class="btn blue"><spring:message code="g.label.save"/></button>
	</c:if>
</div>

</form:form>
<script>
$(function() {
	$(".form_datetime").datetimepicker({
		isRTL: Metronic.isRTL(),
		format: "yyyy-mm-dd hh:ii",
		autoclose: true,
		todayBtn: true,
		pickerPosition: (Metronic.isRTL() ? "bottom-right" : "bottom-left"),
		minuteStep: 5
	});
	
	$('#summernote').summernote({
		height: 150,
		lang: 'zh-CN',
		onChange: function(contents, $editable) {
			$('input[name=content]').val(contents);
		}
	});
	
	$('.note-modal-form').each( function() { $(this).validate({}) });
	
	var form1 = $('#data_form');
	
	form1.validate({
		errorElement : 'span', //default input error message container
		errorClass : 'help-block help-block-error', // default input error message class
		focusInvalid : false, // do not focus the last invalid input
		ignore : "", // validate all fields including form hidden input
		messages : {endTime: {compareDate : '必须大于开始时间'}},
		rules : {
			title : { required : true, maxlength: 10},
			content: { required : true, maxlength: 1200 },
			startTime: { required : true },
			endTime: { required : true, compareDate: 'input[name=startTime]' }
		},
	
		highlight : function(element) { // hightlight error inputs
			$(element).closest('.form-group').addClass('has-error'); // set error class to the control group
		},
	
		unhighlight : function(element) { // revert the change done by hightlight
			$(element).closest('.form-group').removeClass('has-error'); // set error class to the control group
		},
	
		success : function(label) {
			label.closest('.form-group').removeClass('has-error'); // set success class to the control group
		},
		errorPlacement: function (error, element) { // render error placement for each input type
			if (element.parent(".input-group").size() > 0) {
				error.insertAfter(element.parent(".input-group"));
			} else if (element.attr("data-error-container")) { 
				error.appendTo(element.attr("data-error-container"));
			} else if (element.parents('.radio-list').size() > 0) { 
				error.appendTo(element.parents('.radio-list').attr("data-error-container"));
			} else if (element.parents('.radio-inline').size() > 0) { 
				error.appendTo(element.parents('.radio-inline').attr("data-error-container"));
			} else if (element.parents('.checkbox-list').size() > 0) {
				error.appendTo(element.parents('.checkbox-list').attr("data-error-container"));
			} else if (element.parents('.checkbox-inline').size() > 0) { 
				error.appendTo(element.parents('.checkbox-inline').attr("data-error-container"));
			} else {
				error.insertAfter(element); // for other inputs, just perform default behavior
			}
		},
	});
	
	$('#dismiss-modal').click(function() {
		window.location.reload();
	});
});
</script>