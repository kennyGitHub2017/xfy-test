<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<style>
	input.error { border: 1px solid red; }
	label.error {
	padding-left: 16px;
	padding-bottom: 2px;
	color: red;
}
</style>
	
<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal">
		<span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>
		</button>
		<h4 class="modal-title" id="">编辑运费</h4>
</div>

<div class="modal-body">
	<form class="form-horizontal" role="form" method="post"  id="edit_shipFee_form"
	action="${pageContext.request.contextPath}/shipping/${subFlag == '0' ? 'addShipFee' : 'updateShipFee'}">
	
	<div class="row">
		<div class="col-md-4">
			<div class="form-group">
				<label class="control-label col-md-4">运费规则</label>
				<div class="col-md-8">
				<select class="form-control" id="type_id" name="type">
				<option value="0">--请选择--</option>
				<option value="1"  ${fee.type == 1 ? 'selected' : ''}>按重量计算</option>
				<option value="2"  ${fee.type == 2 ? 'selected' : ''} >按首/续重计算</option>
				</select>
				</div>
			</div>
		</div>
	</div>
	
	<input type="hidden" name="shippingId" value="${shipId}"/>
	<!--/row-->
	
<div id="div1" hidden="true">
		<div class="row">
			<div class="col-md-4">
				<div class="form-group">
					<label class="control-label col-md-4">区域</label>
					<div class="col-md-8">
						<input type="text" class="form-control required" name="region" value="${fee.region}">
					</div>
				</div>
			</div>
			
			<div class="col-md-4">
				<div class="form-group">
					<label class="control-label col-md-4">首重G</label>
					<div class="col-md-8">
						<input type="text" class="form-control required number" name="firstWeight" value="${fee.firstWeight}">
					</div>
				</div>
			</div>
			
			<div class="col-md-4">
				<div class="form-group">
					<label class="control-label col-md-4">首重金额</label>
					<div class="col-md-8">
						<input type="text" class="form-control required number" name="firstWeightAmount" value="${fee.firstWeightAmount}">
					</div>
				</div>
			</div>
		</div>
		
		<!--/row-->
		<div class="row">
			<div class="col-md-4">
				<div class="form-group">
					<label class="control-label col-md-4">续重</label>
					<div class="col-md-8">
						<input type="text" class="form-control required number" name="addWeight" value="${fee.addWeight}">
					</div>
				</div>
			</div>
			
			<div class="col-md-4">
				<div class="form-group">
					<label class="control-label col-md-4">续重金额</label>
					<div class="col-md-8">
						<input type="text" class="form-control required number" name="addWeightAmount" value="${fee.addWeightAmount}">
					</div>
				</div>
			</div>
			
			<div class="col-md-4">
				<div class="form-group">
					<label class="control-label col-md-4">挂号费</label>
					<div class="col-md-8">
						<input type="text" class="form-control required number" name="registeredFee" value="${fee.registeredFee}">
					</div>
				</div>
			</div>
		</div>
		
		<!--/row-->
		<div class="row">

			
			<div class="col-md-4">
				<div class="form-group">
					<label class="control-label col-md-4">处理费</label>
					<div class="col-md-8">
						<input type="text" class="form-control required number" name="serviceFee" value="${fee.serviceFee}">
					</div>
				</div>
			</div>
			
			<div class="col-md-4">
				<div class="form-group">
					<label class="control-label col-md-4">折扣率</label>
					<div class="col-md-8">
						<input type="text" class="form-control required number" name="discountRate" value="${fee.discountRate}">
					</div>
				</div>
			</div>
			
			<div class="col-md-4">
				<div class="form-group">
					<label class="control-label col-md-4">折扣费</label>
					<div class="col-md-8">
						<input type="text" class="form-control required number" name="discount" value="${fee.discount}">
					</div>
				</div>
			</div>
		</div>
		
		<!--/row-->
		<div class="row">
			<div class="col-md-4">
				<div class="form-group">
					<label class="control-label col-md-4">最小重量G</label>
					<div class="col-md-8">
						<input type="text" class="form-control required number" name="minWeight" value="${fee.minWeight}">
					</div>
				</div>
			</div>
			
			<div class="col-md-4">
				<div class="form-group">
					<label class="control-label col-md-4">最小金额</label>
					<div class="col-md-8">
						<input type="text" class="form-control required number" name="minWeightAmount" value="${fee.minWeightAmount}">
					</div>
				</div>
			</div>
		</div>
	
		<div class="row">
			<div class="col-md-8">
				<div class="form-group">
					<label class="control-label col-md-2">国家</label>
					<div class="col-md-10">
						<input type="text"  class="form-control required" id="country_id" name="country" value="${fee.country}">
						<span style="color: red">国家填写标准格式：国家名+英文逗号 -例子[Kyrgyzstan,United States,US,Algeria Armenia,]</span>
					</div>
				</div>
			</div>
		</div>
	
	
	</div>
	
	<div id = "div2" hidden="true">
			<div class="row">
				<div class="col-md-4">
					<div class="form-group">
						<label class="control-label col-md-4">区域</label>
						<div class="col-md-8">
							<input type="text" class="form-control required" name="region2" value="${fee.region}">
						</div>
					</div>
				</div>
				
				<div class="col-md-4">
					<div class="form-group">
						<label class="control-label col-md-4">折扣</label>
						<div class="col-md-8">
							<input type="text" class="form-control required" name="discountRate2" value="${fee.discountRate}">
						</div>
					</div>
				</div>
				
				<div class="col-md-4">
					<div class="form-group">
						<label class="control-label col-md-4">邮费</label>
						<div class="col-md-8">
							<input type="text"  class="form-control required number" name="postFee" value="${fee.postFee}">
						</div>
					</div>
				</div>
			</div>
			
			
			<div class="row">
					<div class="col-md-4">
						<div class="form-group">
							<label class="control-label col-md-4">处理费</label>
							<div class="col-md-8">
								<input type="text"  class="form-control required number" name="serviceFee2" value="${fee.serviceFee}">
							</div>
						</div>
					</div>
					
					<div class="col-md-4">
						<div class="form-group">
							<label class="control-label col-md-4">增项费</label>
							<div class="col-md-8">
								<input type="text"  class="form-control required number" name="addFee" value="${fee.addFee}">
							</div>
						</div>
					</div>
					
					<div class="col-md-4">
						<div class="form-group">
							<label class="control-label col-md-4">减项费</label>
							<div class="col-md-8">
								<input type="text"  class="form-control required number" name="subtractFee" value="${fee.subtractFee}">
							</div>
						</div>
					</div>
			</div>
		
		<div class="row">
			<div class="col-md-8">
				<div class="form-group">
					<label class="control-label col-md-2">国家</label>
					<div class="col-md-10">
						<input type="text"  class="form-control required" id="country2_id" name="country2" value="${fee.country}">
						<span style="color: red">国家填写标准格式：国家名+英文逗号 -例子[Kyrgyzstan,United States,US,Algeria Armenia,]</span>
					</div>
				</div>
			</div>
			
			<input type="hidden" name="id" value="${fee.id}"/>
		</div>

	</div>
	
	</form>
	
	<div class="modal-footer">
	<button type="button" class="btn btn-primary"  data-dismiss="alert" onclick="subFrom();">提交</button>
	<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
	</div>
</div>


<script type="text/javascript">

$(document).ready(function() {
	$("#type_id").change(function(){
		var val = $(this).children('option:selected').val();
		if(val == 1){
			$('#div2').show();
			$('#div1').hide();
		}else if(val == 2){
			$('#div1').show();
			$('#div2').hide();
		}else{
			$('#div1').hide();
			$('#div2').hide();
		}
	});
	
	
	
		var flag = '${fee.type}';
		if(flag == '1'){
			$('#div2').show();
			$('#div1').hide();
		}else if(flag == '2'){
			$('#div1').show();
			$('#div2').hide();
		}

});

	$().ready(function() {
		 $("#edit_shipFee_form").validate({
				 errorClass:"error"
		 });
	});

	function subFrom(){
		$("#edit_shipFee_form").submit()
	}
</script>




