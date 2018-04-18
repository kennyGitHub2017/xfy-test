<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
	<h4 class="modal-title">卖家审核 - ${seller.type == 0 ? '个人卖家' : '公司卖家' }</h4>
</div>

<div class="modal-body">
	<form class="form-horizontal form-group-sm" role="form">
		<c:if test="${seller.type == 1 }">
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label for="dict_code" class="col-md-4 control-label">公司名称</label>
					<div class="col-md-8">
						<input name="comName" class="form-control" value="${seller.contacts }" readonly />
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label for="dict_code" class="col-md-4 control-label">公司法人代表</label>
					<div class="col-md-8">
						<input name="comLegalPerson" class="form-control" value="${seller.idCardNo }" readonly />
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label for="dict_code" class="col-md-4 control-label">组织机构代码</label>
					<div class="col-md-8">
						<input name="comCode" class="form-control" value="${seller.comCode }" readonly />
					</div>
				</div>
			</div>
		</div>
		</c:if>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label for="dict_code" class="col-md-4 control-label">联系人</label>
					<div class="col-md-8">
						<input name="contacts" class="form-control" value="${seller.contacts }" readonly />
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label for="dict_code" class="col-md-4 control-label">身份证号</label>
					<div class="col-md-8">
						<input name="idCardNo" class="form-control" value="${seller.idCardNo }" readonly />
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label for="dict_code" class="col-md-4 control-label">手机</label>
					<div class="col-md-8">
						<input name="mobile" class="form-control" value="${seller.mobile }" readonly />
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label for="dict_code" class="col-md-4 control-label">邮箱</label>
					<div class="col-md-8">
						<input name="email" class="form-control" value="${seller.email }" readonly />
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label for="dict_code" class="col-md-4 control-label">QQ</label>
					<div class="col-md-8">
						<input name="qqNo" class="form-control" value="${seller.qqNo }" readonly />
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<div class="form-group">
					<label for="dict_code" class="col-md-2 control-label">居住地址</label>
					<div class="col-md-10">
						<input name="address" class="form-control" value="${seller.address }" readonly />
					</div>
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-md-12">
				<div class="form-group">
					<c:if test="${seller.type == 0 }">
						<label for="dict_code" class="col-md-2 control-label">身份证</label>
						<div class="col-md-10">
							<a href="${pageContext.request.contextPath }/images/${seller.idCardUrl1 }" target="_blank">
								<img src="${pageContext.request.contextPath }/images/${seller.idCardUrl1 }" style="width:100px; height:100px;"/>
							</a>
							<a href="${pageContext.request.contextPath }/images/${seller.idCardUrl2 }" target="_blank">
								<img src="${pageContext.request.contextPath }/images/${seller.idCardUrl2 }" style="width:100px; height:100px;"/>
							</a>
							<a href="${pageContext.request.contextPath }/images/${seller.photoUrl }" target="_blank">
								<img src="${pageContext.request.contextPath }/images/${seller.photoUrl }" style="width:100px; height:100px;"/>
							</a>
						</div>
					</c:if>
					<c:if test="${seller.type == 1 }">
						<label for="dict_code" class="col-md-2 control-label">营业执照</label>
						<div class="col-md-10">
							<a href="${pageContext.request.contextPath }/images/${seller.comBizLicenseUrl }" target="_blank">
								<img src="${pageContext.request.contextPath }/images/${seller.comBizLicenseUrl }" style="width:100px; height:100px;"/>
							</a>
						</div>
					</c:if>
				</div>
			</div>
		</div>
	</form>
</div>

<div class="modal-footer">
	<button type="button" class="btn default" data-dismiss="modal"><spring:message code="g.label.close"/></button>
	<c:if test="${!(view == '1')}">
		<c:if test="${seller.status != 3 }">
			<button type="button" class="btn red" id="seller_unapprove_btn"><spring:message code="g.label.unapproved"/></button>
		</c:if>
			<c:if test="${seller.status != 2 }">
		<button type="button" class="btn green" id="seller_approve_btn"><spring:message code="g.label.approved"/></button>
		</c:if>
	</c:if>
</div>

<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/bootbox/bootbox.min.js" type="text/javascript"></script>
<script>
$('#seller_approve_btn').click(function() {
	_seller_approve('approve', null);
});

$('#seller_unapprove_btn').click(function() {
	bootbox.prompt({
		title: "请输入审核不通过原因",
		callback: function(result) {
			if (! (result === null)) {
				result = $.trim(result);
				if (result.length > 0) {
					_seller_approve('unapprove', result);
				} else {
					alert('审核不通过原因必须输入');
				}
			}
		}
	});
});

function _seller_approve(action, note) {
	if (confirm("请确认是否继续操作？")) {
		xfy.requestByForm({
			action: '${pageContext.request.contextPath }/seller/' + action,
			method: 'post',
			data: {
				id: ${seller.id},
				note: note
			}
		});
	}
}

</script>
