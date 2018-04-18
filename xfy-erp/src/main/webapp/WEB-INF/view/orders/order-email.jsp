<%--
根据选择的订单发送邮件页面
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<c:set scope="request" var="sysModule" value="order" />
<c:set scope="request" var="sysPage" value="order-list" />

<c:set var="pageTitle" value="群发邮件" />
<tiles:insertDefinition name="emptyMetronicTemplate">
	<tiles:putAttribute name="title">${pageTitle }</tiles:putAttribute>
	
	<%-- 页面级别的 CSS --%>
	<tiles:putAttribute name="css-page">
		<link href="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-summernote/summernote.css" rel="stylesheet" type="text/css">
	</tiles:putAttribute>
	
	<tiles:putAttribute name="page-content">
			<div class="row">
				<div class="col-md-12">
					<div class="portlet light">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-cogs font-green-sharp"></i>
								<span class="caption-subject font-green-sharp bold uppercase">${pageTitle }</span>
							</div>
						</div>
						<div class="portlet-body">
							<form class="form-horizontal form-bordered" action="${pageContext.request.contextPath }/order-email/send" method="post">
								<div class="form-body">
									<div class="form-group">
										<label class="control-label col-md-1">收件人</label>
										<div class="col-md-11">
											<input type="text" class="form-control" name="toEmail" value="${toEmail }" readonly />
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-1">主题</label>
										<div class="col-md-11">
											<input type="text" class="form-control" name="subject" />
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-1">内容</label>
										<div class="col-md-11">
											<div id="summernote"></div>
											<input type="text" name="content" style="visibility: hidden;" />
										</div>
									</div>
								</div>
								<div class="form-actions">
									<div class="row">
										<div class="col-md-offset-3 col-md-9">
											<button type="submit" id="submit_btn" class="btn blue" data-loading-text="正在发送中，请稍后 ...">发送邮件</button>
										</div>
									</div>
								</div
							</form>
						</div>
					</div>
					
				</div>
			</div>
	</tiles:putAttribute>
	
	<%-- 页面级别的 JS --%>
	<tiles:putAttribute name="js-page">
		<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-summernote/summernote.min.js" type="text/javascript"></script>
		<script>
			$(function() {
				$editable = $('#summernote').summernote({
					height: 300,
					onChange: function(contents, $editable) {
						$('input[name=content]').val(contents);
					}
				});
				
				$('form').submit(function() {
					if ($.trim($('input[name=subject]').val()) == '') {
						$('input[name=subject]').focus();
						alert('请输入邮件主题');
						return false;
					}
					
					if ($.trim($('input[name=content]').val()) == '') {
						alert('请输入邮件内容');
						return false;
					}
					
					if (confirm('请确认是否立即发送邮件？')) {
						$('#submit_btn').button('loading');
						var url = '${pageContext.request.contextPath }/order-email/send.json';
						var data = $(this).serializeObject();
						$.post(url, data, function(r){
							console.log(r);
							$('#submit_btn').button('reset');
							if (r && r.success === true) {
								alert('发送成功');
							} else {
								alert('发送失败：' + r.message);
							}
						}, 'json');
					}
					
					return false;
				});
			});
		</script>
	</tiles:putAttribute>
</tiles:insertDefinition>