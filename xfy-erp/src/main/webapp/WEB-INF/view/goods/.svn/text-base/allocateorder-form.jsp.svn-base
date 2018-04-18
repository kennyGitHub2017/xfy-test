<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"  %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
	<%-- 页面级别的 CSS --%>
	<tiles:putAttribute name="css-page">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-datepicker/css/datepicker3.css"/>
	</tiles:putAttribute>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
	<h4 class="modal-title">新增调拨单</h4>
</div>
<form:form id="data_form" modelAttribute="allocateorder" action="${pageContext.request.contextPath}/allocateOrder/add" cssClass="form-horizontal" role="form" method="post">
<div class="modal-body">
	<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label for="dict_code" class="col-md-4 control-label">SKU</label>
					<div class="col-md-6">
						<form:input path="goodsSku" cssClass="form-control" id="goodsSku" />
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label for="dict_code" class="col-md-4 control-label">调拨日期</label>
					<div class="col-md-7">
							<div class="input-group date date-picker" data-date-format="yyyy-mm-dd">
								<input name="transferDate" value="<f:formatDate value='<%=new java.util.Date() %>'/>" class="form-control" />
								<span class="input-group-btn">
									<button class="btn default" type="button"><i class="fa fa-calendar"></i></button>
								</span>
							</div>
					</div>
				</div>
			</div>
	</div>
	<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label for="fromStoreId" class="col-md-4 control-label">调出仓库</label>
									<div class="col-md-6">
										<form:select path="fromStoreId"  cssClass="form-control" id="fromStoreId1" onchange="storeShelf('fromStoreId1','fromStoreShelf1')">
												<option value="">请选择</option>
												<c:forEach items="${ storeList}" var="item">
														<option  value="${item.id}">${item.name }
								                     	</option>
												</c:forEach>
										</form:select>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label for="fromStoreShelf" class="col-md-4 control-label">调出货位</label>
									<div class="col-md-7">
										<form:select path="fromStoreShelf"  cssClass="form-control" id="fromStoreShelf1">
										</form:select>
									</div>
								</div>
							</div>
	</div>
	<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label for="toStoreId" class="col-md-4 control-label">调入仓库</label>
									<div class="col-md-6">
										<form:select path="toStoreId"  cssClass="form-control" id="toStoreId1" onchange="storeShelf('toStoreId1','toShoreShelf1')">
																			<option value="">请选择</option>
																			<c:forEach items="${ storeList}" var="item">
																					<option  value="${item.id}">${item.name }
															                     </option>
																			</c:forEach>
										</form:select>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label for="toShoreShelf" class="col-md-4 control-label">调入货位</label>
									<div class="col-md-7">
										<form:select path="toShoreShelf"  cssClass="form-control" id="toShoreShelf1">
										</form:select>
									</div>
								</div>
							</div>
	</div>
	<div class="row">
		<div class="col-md-6">
			<div class="form-group">
				<label for="goodsCount" class="col-md-4 control-label">调入数量</label>
				<div class="col-md-5">
					<form:input path="goodsCount" cssClass="form-control" />
				</div>
			</div>
		</div>
	</div>
	
	<div class="row">
		<div class="col-md-6">
			<div class="form-group">
					<label for="note" class="col-md-4 control-label">备注</label>
					<div class="col-md-8">
						<form:input path="note" cssClass="form-control" />
					</div>
			</div>	
		</div>	
	</div>
</div>
<div class="modal-footer">
	<button type="button" class="btn default" data-dismiss="modal"><spring:message code="g.label.cancel"/></button>
	<button type="submit" id="submitbtn" class="btn blue"><spring:message code="g.label.save"/></button>
</div>
</form:form>
<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/datatables/media/js/jquery.dataTables.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js" type="text/javascript"></script>
<script>
//datepicker show
$('.date-picker').datepicker({
	orientation: "right",
	autoclose: true
});

var form1 = $('#data_form');
form1.validate({
	errorElement : 'span', //default input error message container
	errorClass : 'help-block help-block-error', // default input error message class
	focusInvalid : false, // do not focus the last invalid input
	ignore : "", // validate all fields including form hidden input
	messages : {
		'goodsSku':{ required:'请输入sku编码',remote:'此sku不存在,请重新填写'},
		'transferDate': '请选择调拨日期',
		'goodsCount':'调拨数量',
		'fromStoreId':'调出仓库',
		'fromStoreShelf':'调出货位',
		'toStoreId':'调入仓库',
		'toShoreShelf':'调入货位',
	},
	rules : {
		'goodsSku' : {
			required : true,
			remote: {
                url: "${pageContext.request.contextPath }/purchaserequest-order/goodsInfo",
                type: "post",
                dataType: "json",
                data: {
                	sku: function () {
                        return $("#goodsSku").val();//这个是取要验证的密码
                    }
                },
                dataFilter: function (data) {
                	var json = eval("("+data+")");
                    return json.result;
                }
            }
		},
		'transferDate' : {
			required : true
		},
		'goodsCount' : {
			required : true,
			number:true
		},
		'fromStoreId':{
			required : true
		},
		'fromStoreShelf':{
			required : true
		},
		'toStoreId':{
			required : true
		},
		'toShoreShelf':{
			required : true
		},
		
		
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

$("#goodsSku").blur(function(){
	$.ajax({
		 url:xfy.contextPath+'/purchaserequest-order/goodsInfo', 
	    type:'post',
	    data:'sku='+$(this).val(),     
	    async : false, //默认为true 异步     
	    error:function(){
	       alert('ajax error');     
	    }, 
	    success:function(data){
	    	var jsondata = eval("("+data+")");
	    	if(!jsondata.result){
	    		
	    	}
	    }
	}); 
})

//仓库选择
function storeShelf(storeId,shelftId){
	var p1 = "#"+storeId;
	var p2 = "#"+shelftId;
	$(p2).empty();	
	if ($.trim($(p1).val())==''){
		return;
	}
	$.ajax({
	    url:'${pageContext.request.contextPath}/allocateOrder/getStoreShelf', 
	    type:'post',
	    data:'storeId='+$.trim($(p1).val()),     
	    async : false, //默认为true 异步     
	    error:function(){
	       alert('ajax error');     
	    }, 
	    success:function(data){
	    	var jsondata = eval("("+data+")");
	    	if(jsondata.result){
	    		var fromStoreShelf = jsondata.datas;
	    		$(p2).append("<option value=''>请选择</option>");
	    		for(var i=0;i<fromStoreShelf.length;i++){
	    			var newoption = "<option value=" +fromStoreShelf[i].id+">"+fromStoreShelf[i].code+"</option>";
	    			$(p2).append(newoption);
	    		}
	    	}
	    }
	}); 
}

$("#data_form").submit(function(){
	var fromStore = $("#fromStoreId1").val();
	var fromShelf = $("#fromStoreShelf1").val();
	var toStore = $("#toStoreId1").val();
	var toShelf = $("#toShoreShelf1").val();
	$("#submitbtn").attr("disabled","disabled");
	if (fromStore==toStore && fromShelf==toShelf){
		alert("同货位不能进行调拨");
		$("#submitbtn").removeAttr("disabled");
		return false;
	}
	return true;
});
</script>