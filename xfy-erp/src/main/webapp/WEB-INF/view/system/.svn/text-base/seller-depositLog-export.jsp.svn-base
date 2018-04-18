<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:insertDefinition name="metronicTemplate">
	<tiles:putAttribute name="title">导出卖家资金流水</tiles:putAttribute>
	
		<tiles:putAttribute name="page-content">
		
		<div class="row">
			<div class="col-md-12">
				<div class="portlet light ">
					<div class="portlet-title">
						<div class="caption caption-md">
							<i class="icon-note theme-font"></i> <span class="caption-subject theme-font bold uppercase">导出卖家资金流水</span>
						</div>
					</div>
					<div class="portlet-body">
						
						<div class="table-toolbar">
							<div class="row">
								<div class="col-md-12">
								
								<div class="row">
								
								<form id="searchform" method="post">
									
									<div class="col-md-2">
										<div class="form-group form-group-sm">
											<label  class="col-md-2 control-label">类型</label>
											<div class="col-md-8">
												<select class="form-control" name="type">
												<option value="">请选择</option>
												<option value="0">支出</option>
												<option value="1">收入</option>
												</select>
											</div>
										</div>
									</div>
								
									<div class="col-md-4">
										<div class="form-group">
											<label  class="col-md-2 control-label">时间</label>
											<div class="col-md-5">
													<div class="input-group date date-picker" data-date-format="yyyy-mm-dd">
														<input name="startDateStr" class="form-control input-sm" /> <span class="input-group-btn">
															<button class="btn default btn-sm" type="button">
																<i class="fa fa-calendar"></i>
															</button>
														</span>
													</div>
											</div>
											<div class="col-md-5">
												<div class="input-group date date-picker" data-date-format="yyyy-mm-dd">
														<input name="endDateStr" class="form-control input-sm" /> <span class="input-group-btn">
															<button class="btn default btn-sm" type="button">
																<i class="fa fa-calendar"></i>
															</button>
														</span>
												</div>
											</div>
										</div>
									</div>
									
									<div class="col-md-2">
									<button type="button" onclick="exportLog();" class="btn green btn-sm">导出报表</button>
									</div>
									
									</form>
								</div>
						
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
	<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js" type="text/javascript"></script>
	<script type="text/javascript">
	
	function exportLog(){
		var path = '${pageContext.request.contextPath}/seller/exportDeposit';
		$.ajax({
			url:path,
			type:'post',
			data: $("#searchform").serializeObject(),
			async : false,
			success:function(data){
				if(data == 'error'){
					alert("导出数量不能大于3W条,根据时间分批导出");
				}else{
					
					$('#searchform').attr("action", path).submit();
				}
			}
		}); 
	}
	
	</script>
	</tiles:putAttribute>
	
</tiles:insertDefinition>