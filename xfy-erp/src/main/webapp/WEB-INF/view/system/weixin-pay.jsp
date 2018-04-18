<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>



<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal">
		<span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>
	</button>

	<h4 class="modal-title" id="">微信扫码充值 [1:填写充值信息,下一步 2:扫描二维码]</h4>
</div>


<div class="modal-body">

	<form class="form-horizontal form-group-sm" role="form" id="country_edit_form">
		
		<div class="form-group">
			<div class="form-group">
				<label  class="col-sm-3 control-label">充值描述</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" id="body_id" name="body" value=""><span id="bady_span_id"></span>
				</div>
			</div>
		</div>

		<div class="form-group">
			<label  class="col-sm-3 control-label">充值金额</label>
			<div class="col-sm-3">
				<input type="text" class="form-control" id="amount_id" name="amount" value=""><span id="amount_span_id"></span>
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-3 control-label"></label>
			<div class="col-sm-6">
				<button type="submit" id="weixinpay_bt_id" class="btn green btn-sm" onclick="weixinpayNext();">下一步</button>
			</div>
		</div>
		
		<div align="center" id="code_div_id"><img alt="" src="" id="code_img_id"></div>
		
	</form>
</div>

<script>
	function weixinpayNext() {
		
		$.validator.addMethod("isEn", function(value, element, params) {
			var exp = new RegExp(/^\d+$/);
			return exp.test(value);
		}, "输入内容有误");
		
        var validate = $("#country_edit_form").validate({
            debug: true, 
            submitHandler: function(form){
            	
            	$('#weixinpay_bt_id').attr("disabled", true);
            	var url = "${pageContext.request.contextPath}/seller/weixinPay";
    			var body = $('#body_id').val();
    			var amount = $('#amount_id').val();
    			
    			$.getJSON(url,{body:body,amount:amount}).done(function(res) {
    				var codeUrl = res;
    				if(codeUrl == 'error'){
    					$('#code_div_id').text("生成支付二维码错误!");
    				}else{
    					
    					$('#code_img_id').attr("src","${pageContext.request.contextPath}/makeQRCode?param=" + codeUrl);
    				}
    				
    			});

            },

            rules:{
                body:{
                	required : true,
                },
                amount:{
                	required : true,
                	isEn : "/^\d+$/"
                },
            },
            messages:{
                body:{
                	required : "请输入充值描述",
                },
                amount:{
                	required : "请输入充值金额",
                	isEn: "只能充值整数"
                },
            }

        });
	}
</script>