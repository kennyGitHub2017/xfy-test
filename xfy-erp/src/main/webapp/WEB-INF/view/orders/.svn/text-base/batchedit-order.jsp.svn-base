<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
	<h4 class="modal-title">订单批量修改</h4>
</div>
<div class="modal-body">
			<div class="row">
				<div class="col-md-12">
						<div class="portlet light">
							<div class="portlet-title">
								<div class="caption">
									<i class="fa fa-cogs font-green-sharp"></i>
									<span class="caption-subject font-green-sharp bold uppercase">订单批量修改</span>
								</div>
								<div class="tools">
									<a href="javascript:;" class="collapse"></a>
								</div>
							</div>
					<form  name="form1" action="${pageContext.request.contextPath }/order/batchedit" id="form1" method="post">
							<div class="portlet-body">
								<div class="">
									<table border="0" style="width:360px;border-collapse:separate; border-spacing:10px;" >
									<tr>
										<td>
											<input type="checkbox" idx="1" name="column" value="shipping_Name"/>
										</td>
										<td>
											运输方式
										</td>
										<td>
											<select name="columnValue" class="form-control" id="columnValue1">
												<c:forEach items="${shippingList}" var="item">
													<option value="${item.shippingName}">${item.shippingName }</option>
												</c:forEach>
											</select>
										</td>
									</tr>
									<tr>
										<td>
											<input type="checkbox" idx="2" name="column" value="order_type"/>
										</td>
										<td>订单类型</td>
										<td>
											<select name="columnValue" class="form-control" id="columnValue2">
												<c:forEach items="${orderTypeList}" var="item">
													<option value="${item.code}">${item.name }</option>
												</c:forEach>
											</select>
										</td>
									</tr>
									<tr>
										<td colspan="2" align="center">
											<input type="button" id="saveBtn" value="保存数据" />
										</td>
									</tr>
									</table>
								</div>
							</div>
						</form>	
				</div>
			</div>
		</div>
</div>

		<script>
		$(function(){
			var ids = xfy.getCheckedValues('id');
			var idAry = (ids.length==0)?new Array():ids.split(",");
			ids = ids.split(",");
			for(var i=0;i<ids.length;i++){
				if (ids[i].trim()==''){
					continue;
				}
				var idHidden = "<input type='hidden' name='id' value='{0}'>".format(ids[i]);
				$("#form1").append(idHidden);
			}
			
			var chkList = $("input[type='checkbox'][idx]");
			chkList.click(function(){
				var idx = $(this).attr("idx");
				var cleValue = $("#columnValue"+idx);
				if($(this).attr("checked")){
					cleValue.attr("disabled",false);
				}
			});
		});
			$("#saveBtn").click(function(){
				var ids = xfy.getCheckedValues('id');
				var idAry = (ids.length==0)?new Array():ids.split(",");
				if (idAry.length<1){
					alert("请选择要批量修改的订单");
					return;
				}
				/*
				ids = ids.split(",");
				for(var i=0;i<ids.length;i++){
					if (ids[i].trim()==''){
						continue;
					}
					var idHidden = "<input type='hidden' name='id' value='{0}'>".format(ids[i]);
					$("#form1").append(idHidden);
				}
				*/
				var disableCount = 0;
				$("#columnValue0").attr("disabled","disabled");
				var chkList = $("input[type='checkbox'][idx]");
				chkList.each(function(){
					var idx = $(this).attr("idx");
					var cleValue = $("#columnValue"+idx);
					if(!$(this).attr("checked")){
						disableCount++;
						cleValue.attr("disabled",true);
					}else{
						cleValue.attr("disabled",false);	
					}
				});
				
				if (disableCount==chkList.length){
					alert("请勾选要修改的内容");
					return ;
				}
				
				var jsondata = $("#form1").serializeObject();  //转化为json
				var data = JSON.stringify( jsondata );
				//alert(data);
				 $.ajax({
				    url:'${pageContext.request.contextPath}/order/batchedit',     
				    type:'post',
				    contentType:"application/json;charset=UTF-8",
				    data:data,     
				    async : false, //默认为true 异步     
				    complete:function(){
				    	$("#ajax").modal('hide');
				    },    
				    success:function(data){
				    	var jsondata = eval("("+data+")");
				    	if(jsondata.result){
				    		tb.api().ajax.reload();
				    	}
				    }
				});
				
			});
		</script>