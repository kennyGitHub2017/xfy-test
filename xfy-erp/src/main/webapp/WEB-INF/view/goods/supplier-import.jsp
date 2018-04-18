<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<c:set scope="request" var="sysModule" value="purchase" />
<c:set scope="request" var="sysPage" value="supplier" />

<c:set var="pageTitle" value="供应商信息导入" />
<tiles:insertDefinition name="metronicTemplate">
	<tiles:putAttribute name="title">${pageTitle }</tiles:putAttribute>

	<%-- 页面级别的 CSS --%>
	<tiles:putAttribute name="css-page">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-datepicker/css/datepicker3.css" />
		<%-- <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/jquery-file-upload/css/jquery.fileupload.css"/> --%>
	</tiles:putAttribute>

	<tiles:putAttribute name="page-content">
		<div class="row">
			<div class="col-md-12">
				<div class="portlet light ">
					<div class="portlet-title">
						<div class="caption caption-md">
							<i class="icon-note theme-font"></i> <span class="caption-subject theme-font bold uppercase">${pageTitle }</span>
						</div>
					</div>
					<div class="portlet-body">
						<%@include file="/WEB-INF/view/include/message.jsp"%>
						<div class="table-toolbar">
							<div class="row">
								<div class="col-md-12">
									  <form id="upload-form" action="${pageContext.request.contextPath}/supplier/import" method="post" enctype="multipart/form-data">
								       <span class="btn green">
											<input type="file"  id="fileId" name="goodsSupplierFile" />
		         						</span>
			         					<button type="submit" id="submitbtn" class="btn blue start">
											<i class="fa fa-upload"></i>
											开始导入
										</button>
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
			$('input[name=goodsSupplierFile]').change(function() {
				var filename = $(this).val();
				var fileExtension = filename.substring(filename.lastIndexOf('.') + 1).toLowerCase();
				
				if (fileExtension != 'xls' && fileExtension != 'xlsx') {
					alert('只支持Excel文件，后缀：xls 或者  xlsx');
					$(this).val('')
					return;
				}
			});
		
			$('#upload-form').submit(function() {
				var file = $(this).find('input[name=goodsSupplierFile]').val();
				console.log(file);
				
				if (file == '') {
					alert('请选择文件');
					return false;
				}
				$("#submitbtn").attr("disabled","disabled");
				return true;
			});
			
			
		</script>

	</tiles:putAttribute>

</tiles:insertDefinition>