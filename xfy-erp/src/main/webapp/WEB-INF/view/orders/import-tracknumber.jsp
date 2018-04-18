<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:insertDefinition name="metronicTemplate">
	<tiles:putAttribute name="title">跟踪号导入</tiles:putAttribute>
	
		<tiles:putAttribute name="page-content">
		
		<div class="row">
			<div class="col-md-12">
				<div class="portlet light ">
					<div class="portlet-title">
						<div class="caption caption-md">
							<i class="icon-note theme-font"></i> <span class="caption-subject theme-font bold uppercase">顺邮宝导入</span>
						</div>
					</div>
					<div class="portlet-body">
						<%@include file="/WEB-INF/view/include/message.jsp"%>
						<div class="table-toolbar">
							<div class="row">
								<div class="col-md-12">
						
						<form id="upload-form" action="${pageContext.request.contextPath }/order/importTrackInfo" method="post" enctype="multipart/form-data">


										<div class="row">

											<div class="col-md-4">
												<div class="form-group form-group-sm">
													<label class="control-label col-md-4">选择平邮/挂号</label>
													<div class="col-md-8">
														<select class="form-control" name="type" id="type_id">
															<option value="">--请选择--</option>
															<option value="1">平邮</option>
															<option value="2">挂号</option>
															<option value="3">马来西亚挂号</option>
															<option value="4">香港挂号</option>
															<option value="5">瑞士挂号</option>
															<option value="6">国际小包平邮</option>
															<option value="7">国际小包挂号</option>
															<option value="8">英国小包</option>
															
														</select>
													</div>
												</div>
											</div>
											
											<div class="col-md-8"></div>

										</div>


										<p><p>
										
										<div class="row">

											<div class="col-md-4">
												<div class="form-group form-group-sm">
													<label class="control-label col-md-4">选择文件</label>
													<div class="col-md-8">

														<span class="btn green"> <input type="file" name="fileName" id="fileId" />
														</span>

									

													</div>
												</div>
											</div>
											
											
											<div class="col-md-4">
												<div class="form-group form-group-sm">
													<label class="control-label col-md-2"></label>
													<div class="col-md-8">

														<button type="submit" class="btn blue start">
															<i class="fa fa-upload"></i> 开始导入
														</button>

													</div>
												</div>
											</div>
											
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
		$('#upload-form').submit(function() {
				var type = $('#type_id').val();
				if (type == '') {
					alert('请选 平邮/挂号');
					return false;
				}
			
				var file = $(this).find('input[name=fileName]').val();
				
				if (file == '') {
					alert('请选择文件');
					return false;
				}
				
				return true;
			});
	
		</script>
	</tiles:putAttribute>
	
</tiles:insertDefinition>