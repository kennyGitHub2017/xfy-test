<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:insertDefinition name="metronicTemplate">
	<tiles:putAttribute name="title">根据订单号导出</tiles:putAttribute>
	
		<tiles:putAttribute name="page-content">
		
		<div class="row">
			<div class="col-md-12">
				<div class="portlet light ">
					<div class="portlet-title">
						<div class="caption caption-md">
							<i class="icon-note theme-font"></i> <span class="caption-subject theme-font bold uppercase">根据订单号导出</span>
						</div>
					</div>
					<div class="portlet-body">
						<%@include file="/WEB-INF/view/include/message.jsp"%>
						<div class="table-toolbar">
							<div class="row">
								<div class="col-md-12">
								
						<div class="row">
								<div class="col-md-4">
									<div class="form-group">
										<label class="control-label col-md-4">模板</label>
										<div class="col-md-8">
											<button type="button" class="btn blue start" onclick="getTmeplate();">
												<i class="fa fa-download"></i>下载导入模板
											</button>
										</div>
									</div>
								</div>
								<div class="col-md-8"></div>
						</div>
<p><p>
		<form id="upload-form" action="${pageContext.request.contextPath }/order/uploadOrderFile" method="post" enctype="multipart/form-data">
				<div class="row">
					<div class="col-md-4">
					
					<div class="form-group form-group-sm">
							<label class="control-label col-md-4">选择文件</label>
							<div class="col-md-8">

								<span class="btn blue"> <input type="file" name="fileName" id="fileId" />
								</span>
							</div>
						</div>
					</div>
					
							<div class="col-md-4">
									<div class="form-group">
										<label class="control-label col-md-4"></label>
										<div class="col-md-8">
											<button type="button" class="btn green start" onclick="exportOrder();">
												<i class="fa fa-download"></i>导出订单
											</button>
										</div>
									</div>
							</div>
							
					<div class="col-md-8"></div>
					
					<div class="col-md-6"></div>
				</div>
		</form>
			
				</div>
				</div>
				</div>
				</div>
				</div>
			</div>
		</div>
	</tiles:putAttribute>
	
	<%-- 页面级别的 JS --%>
	<tiles:putAttribute name="js-page">
	
		<script type="text/javascript">
			function exportOrder(){
				var path = '${pageContext.request.contextPath}/order/exportByOrderId';
				$('#upload-form').attr("action", path).submit(); 
			}
			function getTmeplate() {
				window.location.href = '${pageContext.request.contextPath }/resources/template/export-oderById.xls';
			}
		</script>
		
	</tiles:putAttribute>
	
</tiles:insertDefinition>