<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tags.xuanfeiyang.com/tags" prefix="x"%>

<c:set var="pageTitle" value="待发货工作台" />
<c:set scope="request" var="sysModule" value="order" />
<c:set scope="request" var="sysPage" value="package_waitsend-list" />
<tiles:insertDefinition name="metronicTemplate">
	<tiles:putAttribute name="title">${pageTitle }</tiles:putAttribute>
	
		<%-- 页面级别的 CSS --%>
	<tiles:putAttribute name="css-page">
	<style>
		td.details-control {
		    background: url('${pageContext.request.contextPath }/resources/assets/global/img/details_open.png') no-repeat center center;
		    cursor: pointer;
		}
		tr.details td.details-control {
		    background: url('${pageContext.request.contextPath }/resources/assets/global/img/details_close.png') no-repeat center center;
		}
	</style>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-select/bootstrap-select.min.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/select2/select2.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-datepicker/css/datepicker3.css"/>

	</tiles:putAttribute>
	
	<tiles:putAttribute name="page-content">
			<div class="row">
				<div class="col-md-12">
					<div class="portlet light ">
						<div class="portlet-title">
							<div class="caption caption-md">
								<i class="icon-note theme-font"></i>
								<span class="caption-subject theme-font bold uppercase">${pageTitle }</span>
							</div>
						</div>
						
						<div class="portlet-body">
						
						<form:form modelAttribute="search" id="package_search" search="1" class="form-horizontal" method="post">
							<input type="hidden" name="traceNumberFlag" value="1">
							<input type="hidden" name="printedFlag" value="1">
							<div class="row">
								<div class="form-group form-group-sm">
										<div class="col-md-4">
											<div class="form-group form-group-sm">
												<label class="control-label col-md-3">自营卖家</label>
												<div class="col-md-9">
														<select name="selfSeller" class="form-control">
															<option value="" >请选择</option>
															<option value="1">是</option>
															<option value="0">否</option>
														</select>
												</div>
											</div>
										</div>
								</div>		
							</div>
							<div class="row">
								<div class="col-md-4">
									<div class="form-group form-group-sm">
										<label class="control-label col-md-2">订单编号</label>
										<div class="col-md-10">
											<input type="text" class="form-control" name="orderId">
										</div>
									</div>
								</div>
								
								<div class="col-md-4">
									<div class="form-group form-group-sm">
										<label class="control-label col-md-2">跟踪号</label>
										<div class="col-md-10">
											<input type="text" class="form-control" name="trackNumber">
										</div>
									</div>
								</div>
								
								<div class="col-md-4">
									<div class="form-group form-group-sm">
										<label class="control-label col-md-2">SKU编号</label>
										<div class="col-md-10">
											<input type="text" class="form-control" name="goodsSku">
										</div>
									</div>
								</div>
							</div>
							
							<div class="row">
								<div class="col-md-4">
									<div class="form-group form-group-sm">
										<label class="control-label col-md-2">名称</label>
										<div class="col-md-10">
											<input type="text" class="form-control" name="goodsName">
										</div>
									</div>
								</div>
								
								<div class="col-md-4">
									<div class="form-group form-group-sm">
										<label class="control-label col-md-2">运输方式 </label>
										<div class="col-md-10">
											<select class="form-control" name="shippingName">
												<option value="">请选择</option>
												<c:forEach items="${shippingAttr}" var="item">
													<option value="${item.shippingName}">${item.shippingName}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>
								
								<div class="col-md-4">
									<div class="form-group form-group-sm">
										<label class="control-label col-md-2">混合</label>
										<div class="col-md-10">
											<select class="form-control" name="isMix">
												<option value="">请选择</option>
												<option value="0">否</option>
												<option value="1">是</option>
											</select>
										</div>
									</div>
								</div>
							</div>
							
							<div class="row">
								<div class="col-md-4">
										<div class="form-group">
													<label  class="col-md-2 control-label">包裹时间</label>
													<div class="col-md-5">
															<div class="input-group date date-picker" data-date-format="yyyy-mm-dd">
																<input name="cDateFrom" class="form-control input-sm" /> <span class="input-group-btn">
																	<button class="btn default btn-sm" type="button">
																		<i class="fa fa-calendar"></i>
																	</button>
																</span>
															</div>
													</div>
													<div class="col-md-5">
														<div class="input-group date date-picker" data-date-format="yyyy-mm-dd">
																<input name="cDateTo" class="form-control input-sm" /> <span class="input-group-btn">
																	<button class="btn default btn-sm" type="button">
																		<i class="fa fa-calendar"></i>
																	</button>
																</span>
														</div>
													</div>
										</div>
								</div>
								
								<div class="col-md-4">
										<div class="form-group">
													<label  class="col-md-2 control-label">打印时间</label>
													<div class="col-md-5">
															<div class="input-group date date-picker" data-date-format="yyyy-mm-dd">
																<input name="cPrintFrom" class="form-control input-sm" /> <span class="input-group-btn">
																	<button class="btn default btn-sm" type="button">
																		<i class="fa fa-calendar"></i>
																	</button>
																</span>
															</div>
													</div>
													<div class="col-md-5">
														<div class="input-group date date-picker" data-date-format="yyyy-mm-dd">
																<input name="cPrintTo" class="form-control input-sm" /> <span class="input-group-btn">
																	<button class="btn default btn-sm" type="button">
																		<i class="fa fa-calendar"></i>
																	</button>
																</span>
														</div>
													</div>
										</div>
								</div>	
							</div>
							
							<div>
								<div class="col-md-8"></div>
									<div class="col-md-4">
											<button type="button" id="searchbtn" class="btn green">
												<spring:message code="g.label.search" />
											</button>
											<button type="button" id="clearbtn" class="btn blue">清空</button>
											<button type="button" id="export_form_btn" class="btn blue">导出</button>
											<button type="button" id="sendflag_btn" class="btn blue">标记发货</button>
									</div>
							</div>
						</form:form>
							
							<table class="table table-striped table-bordered table-hover" id="list_table">
							<thead>
							<tr>
								<th></th>
								<th><input type="checkbox" class="c-all-and-no-select" data-selector="input[type=checkbox][name=id]" /></th>
								<th>订单ID</th>
								<th>自营卖家</th>
								<th>包裹编号/ID</th>
								<th>Buyer Email/ID</th>
								<th>运输方式</th>
								<th>跟踪号</th>
								<th>是否标发</th>
								<th>打印</th>
								<th>状态</th>
								<th>包装规格</th>
								<th>重量</th>
								<th>运费</th>
								<th>包裹生成时间</th>
								<th>包裹打印时间</th>
							</tr>
							</thead>
							<tbody></tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
	</tiles:putAttribute>

	<%-- 页面级别的 JS --%>
<tiles:putAttribute name="js-page">
<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/datatables/media/js/jquery.dataTables.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-select/bootstrap-select.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/select2/select2.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/resources/assets/admin/pages/scripts/table-advanced.js"></script>
<script>

 $("#package_search").validate({
	errorElement : 'span',
	errorClass : 'help-block error',
	focusInvalid : false,
	ignore : "ignore",
	rules:{
		orderId : {
			digits:true,
		}
	}
}); 
		 
 $("#sendflag_btn").click(function(){
		var idAry = new Array();
		$("input[type=checkbox][name='id']:checked").each(function(){
			idAry.push($(this).attr("orderId"));
		});
		if (idAry.length<1){
			alert("请选择要标记发货的订单");
			return;
		}
		if (confirm('请确认是否继续操作：' + $(this).text() + '？')) {
			
			$.ajax({
			    url:'${pageContext.request.contextPath }/order-package/sendFlag.json',     
			    type:'post',
			    data: {"orderId":idAry.join()}, 
			    async : false, //默认为true 异步
			    complete:function(){
			    	dt.api().ajax.reload();
			    }
			});
		}
	});

var dt;
$(function(){
	initFun();
	$('#list_table tbody').on( 'click', 'td.details-control', function () {
    	var tr = $(this).closest('tr');
        var row = dt.api().row( tr );
        if ( row.child.isShown() ) {
            tr.removeClass( 'details' );
            row.child.hide();
        }
        else {
        	row.child( formatitem( row.data() ) ).show();	
            tr.addClass( 'details' );
        }
    });

	dt.api().on('draw', function () {
		$('#list_table tbody td.details-control').click();
	});

});

$('.date-picker').datepicker({
	orientation: "right",
	autoclose: true
});

/* $("#searchbtn").click(function(){
	dt.api().ajax.reload();
});
 */
 
$("#searchbtn").click(function(){
	var valid = $("#package_search").validate().element($('input[name=orderId]'));
 	if(valid === true){
		if (dt != null) {
		 		dt.api().ajax.reload();
		 	} else {
		 		initFun();
		 	}
	} else {
		alert('请输入正确的订单编号');
	} 
});



function initFun(){
	
	xfy.initDataTable();
	dt = $("#list_table").dataTable({
		serverSide : true,
		ajax : {
			url : "${pageContext.request.contextPath }/order-package/printPageJson",
			type : 'post',
			contentType : "application/json;charset=UTF-8",
			data : function(d) {
 				d.params = $("#package_search").serializeObject();
				return JSON.stringify(d);
			}
		},
		columns : [
				
				{
					 "class":          "details-control",
					 "orderable":      false,
					 "data":           null,
					 "defaultContent": ""
				},
				{data:function(data){return "<input type='checkbox' name='id' value={0} orderId={1} >".format(data.id,data.orderId)}},
			
				{data:"orderId"},
				{data:function(data){
					return data.selfSeller==0?"否":"是";
				}},
				{data:"id"},
				{data:function(data){
							return data.buyerEmail+"<br/>"+data.buyerUserId;
					}
				},
				{data:"shippingName"},
				{data:"trackNumber",
					render:function(data){
						if(data == null){
							return '';
						}else{
							return data;
						}
					}
					},
				{data:function(data){
					return data.isSend && data.isSend==1?"是":"否"; 
				}},	
				{data:function(data){
					return data.printedFlag && data.printedFlag==1?"是":"否";
				}},
				{data:function(data){
					if (data.status==1){
						return "待处理";
					}
					if (data.status==2){
						return "已匹配运输方式";
					}
					if (data.status==3){
						return "已申请跟踪号";
					}
					if (data.status==4){
						return "已打印";
					}
					if (data.status==5){
						return "已扫描";
					}
					if (data.status==6){
						return "已交接";
					}
					if (data.status==7){
						return "已删除";
					}
					if (data.status==8){
						return "物流退回";
					}
					return "";
				}},
				{data:"specifications"},
				{data:"packageWeight"},
				{data:"shippingFee"},
				{data:"createdTime"},
				{data:"printedTime"}
		]
	});
}


function formatitem(d){
	var hasitem;
	
	var html = '<table>'
		+ '<tr><th>名称</th><th>SKU</th><th>订单数量</th>'
		+ '<th>包裹数量</th><th>单价</th><th>总价</th><th>包装规格</th>'
		+ '<th>仓库</th></tr>'
		
	$.ajax({
		type : "post",
		async: false,
		url : "${pageContext.request.contextPath }/order-package/packageItem",
		data : { 
			id : d.id,
		},
		dataType : "json",
		success : function(data) {
			hasitem = data.length>0?true:false;
			$.each(data, function(commentIndex, comment){
				
				html += '<tr><th>'+comment.goodsName+'</th><th>'+comment.sku+'</th><th>'+comment.orderAmount+'</th>'+
						'<th>'+comment.packageAmount+'</th><th>'+comment.price+'</th><th>'+comment.totalPrice+'</th><th>'+comment.specifications+'</th>'+
						'<th>'+comment.storeName+'</th></tr>'
					});
				html += '</table>';
		}
	});
	if (!hasitem){
		return "";
	}
	return html;
}


$("#export_form_btn").click(function(){
	var ids = xfy.getCheckedValues('id');
	var path = '${pageContext.request.contextPath}/order-package/exportPrint';
	if(ids == ''){
		$('#package_search').attr("method","post");
		$('#package_search').attr("action", path).submit();	
	}else{
		xfy.requestByForm({
			method: 'post',
			action: path,
			data: {ids : ids}
		});
	}
});

</script>

</tiles:putAttribute>
</tiles:insertDefinition>