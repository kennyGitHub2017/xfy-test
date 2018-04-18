<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://tags.xuanfeiyang.com/tags" prefix="x"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<tiles:putAttribute name="page-content">
	<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal">
		<span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>
	</button>
	<h4 class="modal-title" id="">产品价格维护</h4>
	</div>

	<div class="row">

		<div class="col-md-12">

			<div class="portlet light">
				<div class="portlet-body">
					<div class="table-scrollable">
						<table class="table table-bordered table-hover">
							<thead>
								<tr>
									<th>图片</th>
									<th>SKU</th>
									<th>名称</th>
									<th>产品成本价(*1.1+包装)</th>
									<th>SKU单价</th>
									<th>SKU单价修改</th>
									<th>SKU价格维护类型</th>
									<th>产品成本价修改</th>
									<th>产品成本维护类型</th>
									<th>操作</th>
									
								</tr>
							</thead>

							<tbody>
								<tr>
									<td><img class="small-img" src="" id="img_id"></td>
									<td>${goodsAttr.goodsSku}</td>
									<td>${goodsAttr.name}</td>
									<td>${goodsAttr.customCost}</td>
									<td>${goodsAttr.cost}</td>
									<td><input type="text" id="cost_id"><span id="span_id"></span></td>
									
									<td>
									<label><input name="costUp" type="radio" value="0" <c:if test="${goodsAttr.costUpdateType=='0' }">checked="checked"</c:if> />自动0</label> <br>
									<label><input name="costUp" type="radio"  value="1" <c:if test="${goodsAttr.costUpdateType=='1' }">checked="checked"</c:if> />手动1</label> 
									</td>
									
									<td><input type="text" id="customCost_id"><span id=""></span></td>
									<td>
									<label><input name="customUp" type="radio" value="0" <c:if test="${goodsAttr.customUpdateType=='0' }">checked="checked"</c:if> />自动0</label> <br>
									<label><input name="customUp" type="radio"  value="1" <c:if test="${goodsAttr.customUpdateType=='1' }">checked="checked"</c:if> />手动1</label> 
									</td>
									
									
									<td><input type="button" value="修改" onclick="changeInfo();"/></td>
								</tr>
							</tbody>

						</table>
					</div>

					<div class="table-scrollable">
						<table class="table table-bordered table-hover">
							<thead>
								<tr>
									<th>时间</th>
									<th>sku单价修改记录</th>
									<th>sku价格维护修改记录</th>
									<th>卖家价格修改记录</th>
									<th>卖家价格维护修改记录</th>
									<th>操作人</th>
									<th>记录说明</th>
								</tr>
							</thead>

							<tbody>
							<c:forEach var="item" items="${costListAttr}">
								<tr>
								<td><fmt:formatDate value="${item.createdTime }" type="both"/></td>
								<td>${item.cost }</td>
								<td>${item.costUpType == '0' ? '自动' : '手动'}${item.costUpType}</td>
								<td>${item.customCost}</td>
								<td>${item.customUpType == '0' ? '自动' : '手动'}${item.customUpType}</td>
								<td>${item.operUser }</td>
								<td>${item.note }</td>
								</tr>
							</c:forEach>
							</tbody>

						</table>
					</div>
				</div>
			</div>
			
		</div>
	</div>
</tiles:putAttribute>


<tiles:putAttribute name="js-page">
<script type="text/javascript">

$(function() {
	var sku = ${goodsAttr.goodsSku}
	var img_sku='${goodsAttr.goodsSku}'.substring(0,2);
	var path = '${x:getConfig("images.base.url")}/g/' + img_sku + '/' + sku + '/g-1-S.jpg'
	$('#img_id').attr("src",path);
});

$('#cost_id').mouseleave(function(){
	var costNew = $('#cost_id').val();
	if(costNew != '' ){
		var exp = /^([1-9][\d]{0,7}|0)(\.[\d]{1,2})?$/;
		if(!exp.test(costNew)){
			alert("价格输入有误");
			$('#cost_id').val('');
			$('#cost_id').focus();
		}
	}
});

function changeInfo(){
	
	if(window.confirm('请核对好,再确认提交!')){
		
		var costType = $("input[name='costUp']:checked").val();
		var customType = $("input[name='customUp']:checked").val();
		
		var costNew = $('#cost_id').val();
		var customCost = $('#customCost_id').val();
		
		var id = ${goodsAttr.id}
		var sku = ${goodsAttr.goodsSku}
		var path = '${pageContext.request.contextPath }/goods/modifyCost';
		var data = {id:id,cost:costNew,costUptype:costType,sku:sku,customUpType:customType,customCost:customCost};
		
			$.post(path,data,function(res){
				if(res == "succ"){
					$("#listingModal").modal('hide');
				}
		},'json');
			
	}
}

</script>
</tiles:putAttribute>


	