<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<c:set scope="request" var="sysModule" value="system" />
<c:set scope="request" var="sysPage" value="account_recharge" />

<tiles:insertDefinition name="metronicTemplate">
	<tiles:putAttribute name="title">账户充值</tiles:putAttribute>
	<tiles:putAttribute name="css-page">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-datepicker/css/datepicker3.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-select/bootstrap-select.min.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/select2/select2.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/jquery-file-upload/blueimp-gallery/blueimp-gallery.min.css"/>
	</tiles:putAttribute>
	
	<tiles:putAttribute name="page-content">
	
	<div class="portlet light">
		<div class="portlet-title">
			<div class="caption">
				<i class="fa fa-cogs font-green-sharp"></i>
				<span class="caption-subject font-green-sharp bold uppercase">账户充值</span>
			</div>
		</div>
		
	<form action="" id="pay_form_id" class="form-horizontal form-row-seperated horizontal-form portlet-body form" method="post"  target="_blank">
	
			<input type="hidden" name="subject" value="" id="subject_id">
			<input name="out_trade_no" id="out_trade_no" type="hidden" value="${out_trade_no }"/>
			<input name="sellerId" id="sellerId" type="hidden" value="${sellerId }"/>
			<h3 class="form-section">账户余额&nbsp;${sellerDeposit == null ? 0 : sellerDeposit.deposit }￥</h3>
			<h3 class="form-section">在线充值</h3>

			<div class="form-body">
				<div class="form-group">
					<label class="control-label col-md-2">金额</label>
					<div class="col-md-9">

						<div class="col-md-12">
							<input type="text" id="total_fee_id" name="total_fee" class="form-control input-inline input-small" value="" readonly="readonly" placeholder=""> <span class="help-inline">
								<div class="radio-list">
									<label class="radio-inline"> <input type="radio" name="money" id="inlineRadio1" value="100">100￥
									</label> <label class="radio-inline"> <input type="radio" name="money" id="inlineRadio2" value="300">300￥
									</label> <label class="radio-inline"> <input type="radio" name="money" id="inlineRadio3" value="500">500￥
									</label>
								</div>
							</span>
						</div>
					</div>
				</div>
				
				<h3 class="form-section">支付方式</h3>
				<div class="form-group">
					<label class="control-label col-md-2">选择</label>
					<div class="col-md-9">
						<label class="radio-inline"> <input type="radio" name="payMethod" id="inlineRadio1" value="1" checked="checked"> <img align="center" width="120px" height="32px" src="${pageContext.request.contextPath }/resources/alipay-image/u18.png">
						<label class="radio-inline"> <input type="radio" name="payMethod" id="inlineRadio1" value="0"> <img align="center" width="120px" height="32px" src="${pageContext.request.contextPath }/resources/alipay-image/u20.png">
					</div>
				</div>
			</div>

			<div class="form-actions left">
				<button type="button" id="weixinpay_bt_id" class="btn green" onclick="payNext();">下一步</button>
			</div>
		</form>

		<div style="display: none;" id="div_img_id">
			<div class="tab-pane active" id="tab_1">
				<p>微信扫码充值 [1:填写充值信息,下一步 2:扫描二维码]</p>
				<img alt="" src="" id="code_img_id" />
			</div>
		</div>
	</div>

</tiles:putAttribute>

	<tiles:putAttribute name="js-page">
		<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/jquery.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-select/bootstrap-select.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-select/bootstrap-select.min.js" type="text/javascript"></script>
	
		<script src="${pageContext.request.contextPath }/resources/master.js" type="text/javascript"></script>
		<script>
		
		$(function(){
			var fee=$("#total_fee_id");
			$("input[name='money']").click(function(){
				fee.val($(this).val());
			});
		});
		
		function payNext(){
			
			var fee = $('#total_fee_id').val();
			if(fee == ''){
				alert("请选择充值金额！");
				return ;
			}
			
			var checkedVal = $('input[name="payMethod"]:checked').val();
			var path1 = "${pageContext.request.contextPath }/seller/alipayapi";
			var path2 = "${pageContext.request.contextPath }/seller/weixinPay";
			if(checkedVal == 1){
		
				$('#subject_id').val('PC支付宝充值');
				$("#pay_form_id").attr("action",path1).submit();
				$('#weixinpay_bt_id').attr("disabled", true);
				
			}else if(checkedVal == 0){
				
				var amount = $("#total_fee_id").val();
				$.getJSON(path2,{body:'PC微信充值',amount:amount}).done(function(res) {
    				var codeUrl = res;
    				if(codeUrl == 'error'){
    					$('#code_div_id').text("生成支付二维码错误!");
    				}else{
    					$('#weixinpay_bt_id').attr("disabled", true);
    					$('#div_img_id').show();
    					$('#code_img_id').attr("src","${pageContext.request.contextPath}/makeQRCode?param=" + codeUrl);
    				}
    				
    			});
			}
			
		}

		</script>
	</tiles:putAttribute>
</tiles:insertDefinition>