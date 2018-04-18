<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
	<h4 class="modal-title">产品类别信息</h4>
</div>
<form:form id="data_form" modelAttribute="category" action="${pageContext.request.contextPath}/goodscategory/${updateFlag == '1' ? 'categorytype-edit' : 'categorytype-add' }" cssClass="form-horizontal form-group-sm" role="form" method="post">
	<div class="modal-body">
		<%-- <input type="hidden" name="id" id="id" value="${category.id }" /> --%>
		<input type="hidden" name="parentId" id="parent_id" value="${category.parentId }" />
		<%-- <form:hidden path="parent.id" id="parentId" value="${category.parent.id }" /> --%>
		<form:hidden path="id" />
		<div class="form-group">
			<label for="dict_code" class="col-md-3 control-label">产品类别</label>
			<div class="col-md-6">
				<form:input path="name" cssClass="form-control" id="cgyname" />
			</div>
		</div>
		<div class="form-group">
			<label for="dict_code" class="col-md-3 control-label">编码</label>
			<div class="col-md-6">
				<form:input path="code" cssClass="form-control" id="cgycode" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">所属大类:</label>
			<div class="col-md-4">
				<%-- <form:select path="parent.parent.id" cssClass="form-control" id="firstCategory">
					<option value="">请选择</option>
					<c:forEach items="${ firstCategory}" var="item">
						<option value="${item.id}" <c:if test="${item.id==category.parent.id or item.id==category.parent.parent.id}">selected="selected"
						                     </c:if>>${item.name }</option>
					</c:forEach>
				</form:select> --%>
				
				<select name="baseCategoryId" class="form-control" id="base_category_id">
					<option value="">请选择</option>
					<c:forEach items="${firstCategory}" var="item">
						<option value="${item.id }">${item.name }</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<!-- 此处为防止二级类目修改为三级类目 -->
		<%-- <c:if test="${category.level!=2}"> --%>
			<div class="form-group">
				<label class="col-md-3 control-label">所属中类:</label>
				<div class="col-md-4">
					<%-- <form:select path="parent.id" cssClass="form-control" id="secondCategory">
						<option value="">请选择</option>
						<c:if test="${not empty secondCategory }">
							<c:forEach items="${ secondCategory}" var="item">
								<option value="${item.id}" <c:if test="${item.id==category.parent.id}">selected="selected"
								                     </c:if>>${item.name }</option>
							</c:forEach>
						</c:if>
					</form:select> --%>
					<select class="form-control" name="midCategoryId" id="mid_category_id">
						<option value="">请选择...</option>
					</select>
				</div>
			</div>
		<%-- </c:if> --%>
		<div class="form-group">
			<label for="inputPassword12" class="col-md-3 control-label">备注</label>
			<div class="col-md-6">
				<form:input path="note" cssClass="form-control" />
			</div>
		</div>
	</div>

	<div class="modal-footer">
		<button type="button" class="btn default" data-dismiss="modal">
			<spring:message code="g.label.cancel" />
		</button>
		<button type="submit" class="btn blue">
			<spring:message code="g.label.save" />
		</button>
	</div>
</form:form>

<script>
	var form1 = $('#data_form');

	form1.validate({
		errorElement : 'span', //default input error message container
		errorClass : 'help-block help-block-error', // default input error message class
		focusInvalid : false, // do not focus the last invalid input
		ignore : "", // validate all fields including form hidden input
		messages : {
			name : {
				//	remote : '此类别已存在,请重新输入'
			},
			code : {
				remote : '此编码已存在,请重新输入'
			},
			baseCategoryId : {
				notEqualTo: '所属中类不能为当前类目'
			},
			midCategoryId : {
				notEqualTo: '所属中类不能为当前类目'
			}
		},
		rules : {
/* 			name : {
				required : true,
				remote : {
					url : "${pageContext.request.contextPath }/goodscategory/checkCategory",
					type : "post",
					dataType : "json",
					data : {
						name : function() {
							return $("#cgyname").val();
						},
						id : function() {
							return "${category.id }";
						}
					}
				}
			}, */
			code : {
				required : true,
				remote : {
					url : "${pageContext.request.contextPath }/goodscategory/checkCategory",
					type : "post",
					dataType : "json",
					data : {
						code : function() {
							return $("#cgycode").val();
						},
						id : function() {
							return "${category.id }";
						}
					}
				}
			},
			baseCategoryId : {required: true, notEqualTo: 'input[name=id]'},
			midCategoryId : {notEqualTo: function(){
				if ($('input[name=id]').val() == '') {
					return false;
				}
				
				return 'input[name=id]';
			}}
			
		},
		
		highlight : function(element) { // hightlight error inputs
			$(element).closest('.form-group').addClass('has-error'); // set error class to the control group
		},
		unhighlight : function(element) { // revert the change done by hightlight
			$(element).closest('.form-group').removeClass('has-error'); // set error class to the control group
		},
		success : function(label) {
			label.closest('.form-group').removeClass('has-error'); // set success class to the control group
		},
		submitHandler: function(form) {
			var parentId = $('#mid_category_id').val();
			if (parentId == '') {
				parentId = $('#base_category_id').val();
			}
			$('#parent_id').val(parentId);
			form.submit();
		}
	});
	
	// 大类级联显示中类
	$('#base_category_id').change(function() {
		var selectedValue = $(this).val();
		if (selectedValue == '') {
			$('#mid_category_id').html('<option value="">请选择...</option>');
			return;
		}
		
		$.post('${pageContext.request.contextPath }/goodscategory/list-json', {id: selectedValue}, function(data) {
			xfy.fillSelect('#mid_category_id', data, 'id', 'name');
			if (midCategoryId != '') {
				$('#mid_category_id').val(midCategoryId);
			}
		}, 'json');
	});
	
	// 这里初始化大类和中类
	<c:choose>
		<c:when test="${category.parent != null && category.parent.parent != null}">
			var baseCategoryId = '${category.parent.parent.id}';
			var midCategoryId = '${category.parent.id}';
		</c:when>
		<c:when test="${category.parent != null && category.parent.parent == null}">
			var baseCategoryId = '${category.parent.id}';
			var midCategoryId = '';
		</c:when>
		<c:when test="${category.parent == null}">
			var baseCategoryId = '';
			var midCategoryId = '';
		</c:when>
	</c:choose>
	
	$('#base_category_id').val(baseCategoryId);
	$('#base_category_id').change();
	
</script>