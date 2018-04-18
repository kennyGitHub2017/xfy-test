<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>重量区间运费编辑</title>
</head>
<body>

<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal">
		<span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>
		</button>
		<h4 class="modal-title" id="">重量区间运费编辑</h4>
</div>

<div class="modal-body">

	<form  method="post" id="form_id">
	<input  value="${shipId}" name="shippingId" type="hidden" id="shippingId_id">
	<input value="${shippFee.id}" name="id" type="hidden">
		<div class="form-group">
		<label class="col-md-1 control-label">国家</label>
			<div class="col-md-10">
				<input class="form-control required" type="text" name="country" value="${shippFee.country}">
				<span class="help-block"></span>
				
			</div>
		</div>
		
			
			<table border="1" id="table_id">
			
			<tr><td>重量从KG</td> <td>重量到KG</td> <td>运费(元)</td><td>操作</td></tr>
			<tr id="initTr_id_0">
			<td><input name="weightFrom" class="form-control required number" value="${shippFee.weightFrom }"></td>
			<td><input name="weightTo" class="form-control required number" value="${shippFee.weightTo }"></td>
			<td><input name="shippFee" class="form-control required number" value="${shippFee.shippFee }"></td>
			
			<td>
			<c:if test="${flag != 1}">
			<input type="button" value="增加" onclick="addTable();"/>
			</c:if>
			</td>
			
			</tr>
			</table>
	</form>
</div>

<div class="modal-footer">

<c:if test="${flag != 1}">
<button type="button" class="btn btn-primary"  data-dismiss="alert" onclick="subForm();">提交</button>
</c:if>

<c:if test="${flag == 1}">
<button type="button" class="btn btn-primary"  data-dismiss="alert" onclick="updateForm();">修改</button>
</c:if>

<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
</div>

<script type="text/javascript">
var rowNo = 0;
function addTable(){
	var table = document.getElementById("table_id");
	var rows = table.rows.length-1;
	if(rows > 4){
		return;
	}
	
	rowNo++;
	
	var trStr = '<tr id="initTr_id_{0}" >'
	+'<td><input name="weightFrom" class="form-control required number" id="from_id_{1}"></td>'
	+'<td><input name="weightTo" class="form-control required number" id="to_id_{2}"></td>'
	+'<td><input name="shippFee" class="form-control required number" id="fee_id_{3}"></td>'
	+'<td><input type="button" class="c-row-del-btns" value="删除" onclick="removeTable();" ></td>'
	+'</tr>';
	
	var trStr2 = trStr.format(rowNo,rowNo,rowNo,rowNo);
	$('#initTr_id_0').after(trStr2);
}

	$(document).on('click', '.c-row-del-btns', function() {
		$(this).parents('tr').remove();
	});
	
	$().ready(function() {
		 $("#form_id").validate({
				 errorClass:"error"
		 });
	});
	
	function subForm(){
	
		$('#form_id').attr('action','${pageContext.request.contextPath }/shipping/addShipFee2');
		$("#form_id").submit();
	}
	
	function updateForm(){
		
		$('#shippingId_id').val(${shippFee.shippingId});
		$("#form_id").attr('action','${pageContext.request.contextPath }/shipping/modifyShippFee2');
		$("#form_id").submit();
	}
	

</script>
	
</body>
</html>