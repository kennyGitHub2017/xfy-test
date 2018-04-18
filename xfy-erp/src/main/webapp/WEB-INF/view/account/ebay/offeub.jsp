<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<c:set scope="request" var="sysModule" value="system" />
<c:set scope="request" var="sysPage" value="ebay_account" />

<tiles:insertDefinition name="metronicTemplate">
	<tiles:putAttribute name="title">线下Eub设置</tiles:putAttribute>
	
	<tiles:putAttribute name="page-content">
	
		<div class="portlet-body form">
													<!-- BEGIN FORM-->
				<form  class="form-horizontal" id="smtform"
					action="${pageContext.request.contextPath}/account/ebay/eubconfig" method="post">
					<input type="hidden" name="id" value="${eubconfig.id}">
					<input type="hidden" name="accountId" value="${eubconfig.accountId}">
					<input type="hidden" name="eubType" value="${eubconfig.eubType}">
					<div class="form-body">
						<div class="form-group">
							<label class="col-md-3 control-label">易贝账号:</label>
							<div class="col-md-4">
								<input type="text" class="form-control" placeholder="请输入易贝账号" name="accountName" value="${eubconfig.accountName}">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">API AUTHTOKEN :</label>
							<div class="col-md-4">
								<input type="text" class="form-control" placeholder="请输入API AUTHTOKEN" name="authToken" value="${eubconfig.authToken}">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">默认交运:</label>
							<div class="col-md-4">
								<select name="shipMent">
									<option value="0" <c:if test="${eubconfig.shipMent==0}">selected="selected"</c:if>>上门揽件</option>
									<option value="1" <c:if test="${eubconfig.shipMent==1}">selected="selected"</c:if>>卖家自送</option>
								</select>
							</div>
						</div>
						<h2>默认揽收信息</h2>
						<input type="hidden" value="0" name="consigneeAdd.addressType">
						<div class="form-group">
							<label class="col-md-3 control-label">联系人 ：</label>
							<div class="col-md-4">
								<input type="text" class="form-control" placeholder="请输入联系人" name="consigneeAdd.name" value="${eubconfig.consigneeAdd.name}">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">公司名称 ：</label>
							<div class="col-md-4">
								<input type="text" class="form-control" placeholder="请输入公司名称 " name="consigneeAdd.company" value="${eubconfig.consigneeAdd.company}">
								<br>省市区，请填写对应代码，参考如下:<a href="http://www.ebay.cn/uploadfile/2011/0304/eBay_ePacket_API_V2_Readme.pdf">http://www.ebay.cn/uploadfile/2011/0304/eBay_ePacket_API_V2_Readme.pdf</a> 4.5 节揽收地址代码
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">联系地址 ：</label>
							<div class="col-md-4">
								国家:<input type="text" class="form-control" placeholder="请输入国家编码" name="consigneeAdd.country" value="${eubconfig.consigneeAdd.country}"><br>
								省:<input type="text" class="form-control" placeholder="请输入省编码" name="consigneeAdd.province" value="${eubconfig.consigneeAdd.province}"><br>
								市:<input type="text" class="form-control" placeholder="请输入市编码" name="consigneeAdd.city" value="${eubconfig.consigneeAdd.city}"><br>
								区:<input type="text" class="form-control" placeholder="请输入区编码" name="consigneeAdd.district" value="${eubconfig.consigneeAdd.district}"><br>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">街道地址： </label>
							<div class="col-md-4">
								<input type="text" class="form-control" placeholder="请输入街道地址" name="consigneeAdd.street" value="${eubconfig.consigneeAdd.street}">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">邮政编码 ：  </label>
							<div class="col-md-4">
								<input type="text" class="form-control" placeholder="请输入邮政编码 " name="consigneeAdd.zipCode" value="${eubconfig.consigneeAdd.zipCode}">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">手机号码 ：</label>
							<div class="col-md-4">
								<input type="text" class="form-control" placeholder="请输入手机号码 " name="consigneeAdd.mobile" value="${eubconfig.consigneeAdd.mobile}">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">固定电话 ： </label>
							<div class="col-md-4">
								<input type="text" class="form-control" placeholder="请输入固定电话" name="consigneeAdd.telphone" value="${eubconfig.consigneeAdd.telphone}">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">电子邮件 ：  </label>
							<div class="col-md-4">
								<input type="text" class="form-control" placeholder="请输入电子邮件" name="consigneeAdd.email" value="${eubconfig.consigneeAdd.email}">
							</div>
						</div>
						
						<!-- 默认英文退货地址 begin -->
						<h2>默认英文退货地址</h2>
						<input type="hidden" value="1" name="rejectedenAdd.addressType">
						<div class="form-group">
							<label class="col-md-3 control-label">Name ：</label>
							<div class="col-md-4">
								<input type="text" class="form-control" placeholder="Please input Name" name="rejectedenAdd.name" value="${eubconfig.rejectedenAdd.name}">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">Company：</label>
							<div class="col-md-4">
								<input type="text" class="form-control" placeholder="Please input Company Name " name="rejectedenAdd.company" value="${eubconfig.rejectedenAdd.company}">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">Address: ：</label>
							<div class="col-md-4">
								Country:<input type="text" class="form-control" placeholder="Please input Country Code" name="rejectedenAdd.country" value="${eubconfig.rejectedenAdd.country}"><br>
								Province:<input type="text" class="form-control" placeholder="Please input Province Code" name="rejectedenAdd.province" value="${eubconfig.rejectedenAdd.province}"><br>
								City:<input type="text" class="form-control" placeholder="Please input City Code" name="rejectedenAdd.city" value="${eubconfig.rejectedenAdd.city}"><br>
								District:<input type="text" class="form-control" placeholder="Please input District Code" name="rejectedenAdd.district" value="${eubconfig.rejectedenAdd.district}"><br>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">Street： </label>
							<div class="col-md-4">
								<input type="text" class="form-control" placeholder="Please input Street " name="rejectedenAdd.street" value="${eubconfig.rejectedenAdd.street}">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">Zip Code ：  </label>
							<div class="col-md-4">
								<input type="text" class="form-control" placeholder="Please input Zip Code " name="rejectedenAdd.zipCode" value="${eubconfig.rejectedenAdd.zipCode}">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">Mobile ：</label>
							<div class="col-md-4">
								<input type="text" class="form-control" placeholder="Please input Mobile " name="rejectedenAdd.mobile" value="${eubconfig.rejectedenAdd.mobile}">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">Telphone ： </label>
							<div class="col-md-4">
								<input type="text" class="form-control" placeholder="Please input Telphone " name="rejectedenAdd.telphone" value="${eubconfig.rejectedenAdd.telphone}">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">Email：  </label>
							<div class="col-md-4">
								<input type="text" class="form-control" placeholder="Please input Email " name="rejectedenAdd.email" value="${eubconfig.rejectedenAdd.email}">
							</div>
						</div>
						<!-- 默认英文退货地址 end -->
						
						<!-- 默认中文退货地址 begin -->
						<h2>默认中文退货地址</h2>
						<input type="hidden" value="2" name="rejectedcnAdd.addressType">
						<div class="form-group">
							<label class="col-md-3 control-label">联系人 : </label>
							<div class="col-md-4">
								<input type="text" class="form-control" placeholder="请输入联系人" name="rejectedcnAdd.name" value="${eubconfig.rejectedcnAdd.name}">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">公司名称: </label>
							<div class="col-md-4">
								<input type="text" class="form-control" placeholder="请输入公司名称" name="rejectedcnAdd.company" value="${eubconfig.rejectedenAdd.company}">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">联系地址: </label>
							<div class="col-md-4">
								国家:<input type="text" class="form-control" placeholder="请输入国家编码" name="rejectedcnAdd.country" value="${eubconfig.rejectedcnAdd.country}"><br>
								省:<input type="text" class="form-control" placeholder="请输入省编码" name="rejectedcnAdd.province" value="${eubconfig.rejectedcnAdd.province}"><br>
								市 :<input type="text" class="form-control" placeholder="请输入市编码" name="rejectedcnAdd.city" value="${eubconfig.rejectedcnAdd.city}"><br>
								区:<input type="text" class="form-control" placeholder="请输入区编码" name="rejectedcnAdd.district" value="${eubconfig.rejectedcnAdd.district}"><br>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">街道地址： </label>
							<div class="col-md-4">
								<input type="text" class="form-control" placeholder="请输入 街道地址 " name="rejectedcnAdd.street" value="${eubconfig.rejectedcnAdd.street}">
							</div>
						</div>
						<!-- 默认中文退货地址 end -->
					</div>
					<div class="form-actions">
						<div class="row">
							<div class="col-md-offset-3 col-md-9">
								<button type="submit" class="btn green">保存数据</button>
							</div>
						</div>
					</div>
				</form>
			</div>
	</tiles:putAttribute>
	
	<tiles:putAttribute name="js-page">
		<script>
		//form validate
		  var form1 = $('#smtform');
			var error1 = $('.alert-danger', form1);
			var success1 = $('.alert-success', form1);

			form1.validate({
				errorElement : 'span', //default input error message container
				errorClass : 'help-block help-block-error', // default input error message class
				focusInvalid : false, // do not focus the last invalid input
				ignore : "", // validate all fields including form hidden input
				messages : {
					'accountName': '请输入 Ebay 帐号',
					'authToken': '请输入API AuthToken',
					'consigneeAdd.name': '请输入联系人',
					'consigneeAdd.company': '请输入公司名称',
					'consigneeAdd.country': '请输入国家编码',
					'consigneeAdd.province': '请输入省编码',
					'consigneeAdd.city': '请输入市编码',
					'consigneeAdd.district': '请输入区编码',
					'consigneeAdd.street': '请输入街道地址',
					'consigneeAdd.zipCode': '请输入邮证编码',
					'consigneeAdd.mobile': '请输入手机号码',
					'consigneeAdd.telphone': '请输入固定电话',
					'consigneeAdd.email': '请输入Email',
				},
				rules : {
					'accountName' : {
						required : true
					},
					'authToken' : {
						required : true
					},
					'consigneeAdd.name' : {
						required : true
					},
					'consigneeAdd.company' : {
						required : true
					},
					'consigneeAdd.country' : {
						required : true,
						digits:true
					},
					'consigneeAdd.province' : {
						required : true,
						digits:true
					},
					'consigneeAdd.city' : {
						required : true,
						digits:true
					},
					'consigneeAdd.district' : {
						required : true,
						digits:true
					},
					'consigneeAdd.street' : {
						required : true,
					},
					'consigneeAdd.zipCode' : {
						required : true,
						digits:true
					},
					'consigneeAdd.mobile' : {
						required : true,
					},
					'consigneeAdd.telphone' : {
						required : true,
					},
					'consigneeAdd.email' : {
						required : true,
						email:true
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
		</script>
	</tiles:putAttribute>
</tiles:insertDefinition>