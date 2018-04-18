<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<tiles:insertDefinition name="metronicTemplate">
	<tiles:putAttribute name="title">卖家订单发货设置</tiles:putAttribute>
	
	<tiles:putAttribute name="page-content">
			<div class="row">
			
			
			<%@ include file="/WEB-INF/view/include/message.jsp" %>


<div class="portlet-body">

				<form class="form-horizontal" role="form" id="sellerShip_edit_form" method="post"
					action="${pageContext.request.contextPath}/sellerConfig/${subFlag == '1'? 'update' : 'editShipping'}">
					<input  type="hidden" value="${sellerConfigAttr.sellerId}" name="sellerId"/>
					
					

						<div class="form-group">
							<div class="radio-list">
							<label><input type="radio" name="shipType" id="optionsRadios0"  value="0" ${sellerConfigAttr.shipType == '0' ? 'checked' : ''}/>平台选择</label>
							</div>
						</div>
						
					<div class="form-group">
							<div class="radio-list">
							<label><input type="radio" name="shipType" id="optionsRadios2"  value="2" ${sellerConfigAttr.shipType == '2' ? 'checked' : ''} />卖家选择 </label>
							</div>
					</div>
						
			
						
					<div class="form-group">
							<div class="radio-list">
							<label><input type="radio" name="shipType" id="optionsRadios1" value="1" ${sellerConfigAttr.shipType == '1' ? 'checked' : ''}/>指定物流公司</label>
							</div>
					</div> 
					
					<label id="lable1" style="color:red "></label>
					<select class="form-control input-medium" name="carrierName" id="carrierName_id">
				
											<option value="">物流公司</option>
											<option value="value1">Option 1</option>
											<option value="value2">Option 2</option>
											<option value="value3">Option 3</option>
											<option value="value4">Option 4</option>
											<option value="value5">Option 5</option>
					</select>	
			
			
			<!-- BEGIN Portlet PORTLET-->
				<div class="portlet gren col-md-4">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-gift"></i>说明
						</div>
				
					</div>
					<div class="portlet-body">
						<div class="scroller" style="height:200px">
							<p>
							1 :平台选择<br>	选择此项后，卖家的订单公司人员帮卖家自动匹配运输方式或者帮忙选择运输方式，卖家可以修改
							</p><br>
							<p>
							2 :指定物流公司<br>只要选择此选项，卖家的订单统一使用一种物流公司; 选择此项后，卖家要选择指定的发货方式
							</p><br>
							
							<p>
							3 :卖家自己选择 <br>选择此项后，卖家的订单发货方式只能卖家进系统进行选择，公司人员不能修改发货方
							</p><br>
						
						</div>
					</div><br>
					
					<button type="button" class="btn btn-primary"  data-dismiss="alert" onclick="subFrom();">提交</button>
					
				</div><br>
				<!-- END Portlet PORTLET-->
			</form> 
	
 	
			<script type="text/javascript">

		
			function subFrom(){

				if(document.getElementById("optionsRadios1").checked){
					var name = $('#carrierName_id').val()
					if(name == ''){
						$('#lable1').text("请选择物流公司");
						return false;
					}
				} 
				
			$("#sellerShip_edit_form").submit()
				
			}
			
			</script>
						
	</div>
					
			</div>	
  

	</tiles:putAttribute>
</tiles:insertDefinition>