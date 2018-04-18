<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
	<h4 class="modal-title">采购报价单</h4>
</div>
<form:form id="data_form" modelAttribute="model" action="${pageContext.request.contextPath}/supplier-price/save" cssClass="form-horizontal form-group-sm" role="form" method="post">
<form:hidden path="id" />
<div class="modal-body">
	<div class="row">
		<div class="col-md-6">
			<div class="form-group">
				<label for="dict_code" class="col-md-4 control-label">SKU</label>
				<div class="col-md-8">
					<form:input path="goodsSku" id="goodsSku" cssClass="form-control" maxlength="10" />
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-6">
			<div class="form-group">
				<label for="dict_code" class="col-md-4 control-label">名称</label>
				<div class="col-md-8">
					<form:input path="goodsName" id="goodsName" cssClass="form-control" maxlength="100" />
				</div>
			</div>
		</div>
		<div class="col-md-6">
			<div class="form-group">
				<label for="dict_code" class="col-md-4 control-label">供应商</label>
				<div class="col-md-8">
					<input type="text" class="form-control c-supplier-picker" value="" data-placeholder="${model.supplierName == null ? '请选择...' : model.supplierName }" />
					<form:hidden path="supplierId" id="supplierId2" />
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-6">
			<div class="form-group">
				<label for="dict_code" class="col-md-4 control-label">采购周期</label>
				<div class="col-md-8">
					<form:input path="buyPeriod" cssClass="form-control" maxlength="10" />
				</div>
			</div>
		</div>
		<div class="col-md-6">
			<div class="form-group">
				<label for="dict_code" class="col-md-4 control-label">优先级</label>
				<div class="col-md-8">
					<form:select path="priority" cssClass="form-control">
						<option value="">请选择...</option>
						<form:option value="1">1</form:option>
						<form:option value="2">2</form:option>
						<form:option value="3">3</form:option>
						<form:option value="4">4</form:option>
					</form:select>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-6">
			<div class="form-group">
				<label for="dict_code" class="col-md-4 control-label">采购报价</label>
				<div class="col-md-8">
					<form:input path="price" cssClass="form-control" />
				</div>
			</div>
		</div>
		<div class="col-md-6">
			<div class="form-group">
				<label for="dict_code" class="col-md-4 control-label">单位</label>
				<div class="col-md-8">
					<form:input path="goodsUnit" cssClass="form-control" maxlength="10" />
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-6">
			<div class="form-group">
				<label for="dict_code" class="col-md-4 control-label">采购量从</label>
				<div class="col-md-8">
					<form:input path="countMin" cssClass="form-control" maxlength="10" />
				</div>
			</div>
		</div>
		<div class="col-md-6">
			<div class="form-group">
				<label for="dict_code" class="col-md-4 control-label">采购量到</label>
				<div class="col-md-8">
					<form:input path="countMax" cssClass="form-control" />
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-6">
			<div class="form-group">
				<label for="dict_code" class="col-md-4 control-label">生效日期</label>
				<div class="col-md-8">
					<div class="input-group date date-picker" data-date-format="yyyy-mm-dd">
						<form:input path="startDate" cssClass="form-control" />
						<span class="input-group-btn">
							<button class="btn default btn-sm" type="button"><i class="fa fa-calendar"></i></button>
						</span>
					</div>
				</div>
			</div>
		</div>
		<div class="col-md-6">
			<div class="form-group">
				<label for="dict_code" class="col-md-4 control-label">失效日期</label>
				<div class="col-md-8">
					<div class="input-group date date-picker" data-date-format="yyyy-mm-dd">
						<form:input path="endDate" cssClass="form-control" />
						<span class="input-group-btn">
							<button class="btn default btn-sm" type="button"><i class="fa fa-calendar"></i></button>
						</span>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<div class="form-group">
				<label for="dict_code" class="col-md-2 control-label">备注</label>
				<div class="col-md-10">
					<form:input path="note" cssClass="form-control" maxlength="10" />
				</div>
			</div>
		</div>
	</div>
</div>

<div class="modal-footer">
	<button type="button" class="btn default" data-dismiss="modal"><spring:message code="g.label.cancel"/></button>
	<button type="submit" class="btn blue"><spring:message code="g.label.save"/></button>
</div>

</form:form>


<script>
$('.date-picker').datepicker({
	orientation: "right",
	autoclose: true
});

xfy.SupplierPrice.handleSupplierSelect('supplierId2');

var form1 = $('#data_form');

form1.validate({
	errorElement : 'span', //default input error message container
	errorClass : 'help-block help-block-error', // default input error message class
	focusInvalid : false, // do not focus the last invalid input
	ignore : "", // validate all fields including form hidden input
	messages : {
		'goodsSku':{remote:'此sku不存在,请重新填写'},
	},
	rules : {
		goodsSku: 
		{required: true,
			remote: {
			    url: "${pageContext.request.contextPath }/purchaserequest-order/goodsInfo",
			    type: "post",
			    dataType: "json",
			    data: {
			    	sku: function () {
			            return $("#goodsSku").val();
			        }
			    },
			    dataFilter: function (data) {
			    	var json = eval("("+data+")");
			    	if (json.result){
			    		var nameElement = $("#goodsName");
			    		nameElement.val(json.datas.name);
			    		nameElement.attr("readonly","true");
			    	}
			        return json.result;
			    }
			}
		},
		goodsName: {required: true},
		supplierId: {required: true, digits: true},
		price: {required: true, number: true},
		buyPeriod: {required: true, digits: true},
		priority: {required: true},
		countMin: {digits: true},
		countMax: {digits: true}
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

// 处理checkbox 样式
xfy.initUniformCheckbox();
</script>