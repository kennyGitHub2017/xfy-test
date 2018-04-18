<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
	<h4 class="modal-title">邮件内容</h4>
</div>
<div class="modal-body">
	<div class="row">
		<label for="dict_code" class="col-md-2 control-label">收件人</label>
		<div class="col-md-10" style="word-break:break-all !important;">${emailTask.receiverEmail }</div>
	</div>
	<div class="row">
		<label for="inputPassword12" class="col-md-2 control-label">主题</label>
		<div class="col-md-10" style="word-break:break-all !important;">${emailTask.subject }</div>
	</div>
	<div class="row">
		<label for="inputPassword12" class="col-md-2 control-label">内容</label>
		<div class="col-md-10" style="word-break:break-all !important;">${emailTask.content }</div>
	</div>
</div>

<div class="modal-footer">
	<button type="button" class="btn default" data-dismiss="modal">
		<spring:message code="g.label.close" />
	</button>
</div>

