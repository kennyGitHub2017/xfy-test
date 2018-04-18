<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>	
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:set scope="request" var="sysModule" value="system" />
<c:set scope="request" var="sysPage" value="amazon_account" />

<tiles:insertDefinition name="metronicTemplate">
	<tiles:putAttribute name="title">Amazon帐号管理 </tiles:putAttribute>
	
	<tiles:putAttribute name="page-content">
	
		<div class="portlet-body form">
													<!-- BEGIN FORM-->
				<form:form id="amform" modelAttribute="account"   class="form-horizontal"
					action="${pageContext.request.contextPath}/account/am/add" method="post">
					<input id="id" type="hidden"  name="id" value="${account.id}" />
					<div class="form-body">
						<div class="form-group">
							<label class="col-md-3 control-label">Aamzon 帐号名称</label>
							<div class="col-md-4">
								<input type="text" class="form-control" placeholder="Enter Aamzon 帐号名称" name="accountName" value="${account.accountName}">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">AWS_ACCESS_KEY_ID:</label>
							<div class="col-md-4">
								<input type="text" class="form-control" placeholder="Enter AWS_ACCESS_KEY_ID" name="amAccessKey" value="${account.amAccessKey}">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">AWS_SECRET_ACCESS_KEY:</label>
							<div class="col-md-4">
								<input type="text" class="form-control" placeholder="Enter AWS_SECRET_ACCESS_KEY" name="amSecretAccessKey" value="${account.amSecretAccessKey}">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">MERCHANT_ID: </label>
							<div class="col-md-4">
								<input type="text" class="form-control" placeholder="Enter MERCHANT_ID" name="amMerchantId" value="${account.amMerchantId}">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">MARKETPLACE_ID:</label>
							<div class="col-md-4">
								<input type="text" class="form-control" placeholder="Enter MARKETPLACE_ID" name="amMarketplaceId" value="${account.amMarketplaceId}">
							</div>
						</div>
						<div class="form-group">
						<label class="col-md-3 control-label">Amazon 站点:</label>
						<div class="col-md-4">
							<form:select path="amServiceUrl" cssClass="form-control">
								<option  value="https://mws.amazonservices.co.uk"
									 <c:if test="${account.amServiceUrl=='https://mws.amazonservices.co.uk'}">selected="selected"
				                     </c:if>>United Kingdom
			                      </option>
			                      <option value="https://mws.amazonservices.de" 
			                      		<c:if test="${account.amServiceUrl=='https://mws.amazonservices.de'}">selected="selected"
				                       </c:if>>Germany
			                      </option>
			                      <option value="https://mws.amazonservices.fr" 
				                      <c:if test="${account.amServiceUrl=='https://mws.amazonservices.fr'}">selected="selected"
					                    </c:if>>France
					               </option>
			                      <option value="https://mws.amazonservices.jp"
			                       <c:if test="${account.amServiceUrl=='https://mws.amazonservices.jp'}">selected="selected"
				                       </c:if>>Japan
				                   </option>
			                      <option value="https://mws.amazonservices.com.cn" 
			                     	 <c:if test="${account.amServiceUrl=='https://mws.amazonservices.com.cn'}">selected="selected"
				                       </c:if>>China
				                   </option>
			                      <option value="https://mws.amazonservices.ca" 
			                      	<c:if test="${account.amServiceUrl=='https://mws.amazonservices.ca'}">selected="selected"
				                       </c:if>>Canada
				                   </option>    
			                      <option value="https://mws.amazonservices.in" 
			                      <c:if test="${account.amServiceUrl=='https://mws.amazonservices.in'}">selected="selected"
				                       </c:if>>India
				                  </option>    
			                      <option value="https://mws.amazonservices.it" 
			                      		<c:if test="${account.amServiceUrl=='https://mws.amazonservices.it'}">selected="selected"
				                       </c:if>>Italy
				                       </option>   
			                      <option value="https://mws.amazonservices.es" 
			                      		<c:if test="${account.amServiceUrl=='https://mws.amazonservices.es'}">selected="selected"
				                       </c:if>>Spain
				                   </option>
							</form:select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">账号状态:</label>
						<div class="col-md-9">
							<div class="radio-list">
								<label class="radio-inline">
								<input type="radio" name="status" id="optionsRadios25" value="1" <c:if test="${account.status==null or account.status==1}">checked="checked"</c:if> >启用  </label>
								<label class="radio-inline">
								<input type="radio" name="status" id="optionsRadios26" value="0" <c:if test="${account.status==0}">checked="checked"</c:if> >禁用</label>
							</div>
						</div>
					</div>
					</div>
					<div class="form-actions">
						<div class="row">
							<div class="col-md-offset-3 col-md-9">
								<button type="submit" class="btn green">提交</button>
							</div>
						</div>
					</div>
				</form:form>
			</div>
	</tiles:putAttribute>
</tiles:insertDefinition>
<script type="text/javascript">
  $(function(){
	  var id = $("#id").attr("value");
	  if (id){
		  $("#amform").attr("action","${pageContext.request.contextPath}/account/am/edit");
	  }
	  
	  //form validate
	  var form1 = $('#amform');
		var error1 = $('.alert-danger', form1);
		var success1 = $('.alert-success', form1);

		form1.validate({
			errorElement : 'span', //default input error message container
			errorClass : 'help-block help-block-error', // default input error message class
			focusInvalid : false, // do not focus the last invalid input
			ignore : "", // validate all fields including form hidden input
			messages : {
				accountName: '请输入 Aamzon 帐号名称',
				amAccessKey: '请输入 Aamzon AccessKey',
				amSecretAccessKey:'请输入 Aamzon SecretAccessKey',
				amMerchantId:'请输入 Aamzon MerchantId',
				amMarketplaceId:'请输入 Aamzon MarketplaceId'
			},
			rules : {
				accountName : {
					required : true
				},
				amAccessKey : {
					required : true
				},
				amSecretAccessKey : {
					required : true
				},
				amMerchantId : {
					required : true
				},
				amMarketplaceId : {
					required : true
				}
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
  });
</script>
	