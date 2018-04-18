<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>



<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal">
		<span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>
	</button>
	<h4 class="modal-title" id="">处理退货订单</h4>
</div>


<div class="modal-body">
<p>
	<textarea rows="5" cols="80" id="note_text">
	</textarea>
</div>

<div class="modal-footer">
	<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
	<button type="button" class="btn btn-primary" data-dismiss="alert" onclick="subFrom();">提交</button>
</div>

<script>
function subFrom(){
	var ids = xfy.getCheckedValues('id');
	if(ids == ''){
		alert("请选择订单");
		return false;
	}else{
		
		$.ajax({url: "${pageContext.request.contextPath}/order/getOrderStatus", 
			type:'post',
			async:false,
			data: {ids:ids},
			success: function(data){
				var jsondata = eval("("+data+")");
				if(jsondata){
						$.post('${pageContext.request.contextPath}/order/saveReturnNote', 
							{ids:ids,returnNote:$('#note_text').val()},function(data){
								if(data == 'succ'){
									$("#ajax").modal('hide');
									tb.api().ajax.reload();
								}
							},'json');
				}else{
					alert("订单不能修改");
				}
			
			}
		});
		

	}	
}
</script>