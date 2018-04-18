<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://tags.xuanfeiyang.com/tags" prefix="x"%>

<c:set scope="request" var="sysModule" value="product" />
<c:set scope="request" var="sysPage" value="goods" />

<c:set var="pageTitle" value="产品编辑" />

<%
// 时间戳参数
pageContext.setAttribute("currentTimeMillisParam", "?_" + System.currentTimeMillis());
%>

<tiles:insertDefinition name="metronicTemplate">

	<tiles:putAttribute name="title">${pageTitle }</tiles:putAttribute>

	<%-- 页面级别的 CSS --%>
	<tiles:putAttribute name="css-page">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-datepicker/css/datepicker3.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-select/bootstrap-select.min.css"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/select2/select2.css"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/jquery-file-upload/blueimp-gallery/blueimp-gallery.min.css"/>
	</tiles:putAttribute>

	<tiles:putAttribute name="page-content">
		<div class="row">
			<div class="col-md-12">
				<div class="portlet light ">
					<div class="portlet-title">
						<div class="caption caption-md">
							<i class="icon-note theme-font"></i> <span class="caption-subject theme-font bold uppercase">${pageTitle }</span>
						</div>
					</div>
					<div class="portlet-body">
						<%@include file="../include/message.jsp"%>
						
						<form:form id="edit_goods_form" modelAttribute="goods" action="${pageContext.request.contextPath }/goods/${goods.id == null? 'add' : 'update'}" cssClass="form-horizontal" role="form" method="post" enctype="multipart/form-data">
							<div class="form-body">
								<form:hidden path="id" />

								<div class="row">
									<div class="col-md-3">
										<div class="form-group">
											<label class="control-label col-md-4">SKU编号</label>
											<div class="col-md-8">
												<form:input path="goodsSku" cssClass="form-control" readonly="${goods.id != null }" />
											</div>
										</div>
									</div>
									<div class="col-md-3">
										<div class="form-group">
											<label class="control-label col-md-4">货品名称</label>
											<div class="col-md-8">
												<form:input path="name" cssClass="form-control" />
											</div>
										</div>
									</div>
									<div class="col-md-3">
										<div class="form-group">
											<label class="control-label col-md-4">英文名称</label>
											<div class="col-md-8">
												<form:input path="enName" cssClass="form-control" />
											</div>
										</div>
									</div>
									<div class="col-md-3">
										<div class="form-group">
											<label class="control-label col-md-4">父类编码</label>
											<div class="col-md-8">
												<form:input path="baseCode" cssClass="form-control" />
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-3">
										<div class="form-group">
											<label class="control-label col-md-4">产品状态</label>
											<div class="col-md-8">
												<form:select path="status" cssClass="form-control">
													<option value="">请选择...</option>
													<form:options items="${goodsStatus }" itemValue="code" itemLabel="name" />
												</form:select>
											</div>
										</div>
									</div>
									<div class="col-md-3">
										<div class="form-group">
											<label class="control-label col-md-4">产品长</label>
											<div class="col-md-8">
												<form:input path="length" cssClass="form-control" />
											</div>
										</div>
									</div>
									<div class="col-md-3">
										<div class="form-group">
											<label class="control-label col-md-4">产品宽</label>
											<div class="col-md-8">
												<form:input path="width" cssClass="form-control" />
											</div>
										</div>
									</div>
									<div class="col-md-3">
										<div class="form-group">
											<label class="control-label col-md-4">产品高</label>
											<div class="col-md-8">
												<form:input path="height" cssClass="form-control" />
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-3">
										<div class="form-group">
											<label class="control-label col-md-4">供应商1</label>
											<div class="col-md-8">
												<input type="text" id="supplier_id_select2" class="form-control" data-placeholder="${goods.supplierName == null ? '请选择...' : goods.supplierName}" />
												<form:hidden path="supplierId" id="supplier_id" />
											</div>
										</div>
									</div>
									<div class="col-md-3">
										<div class="form-group">
											<label class="control-label col-md-4">供应商2</label>
											<div class="col-md-8">
												<input type="text" id="supplier2_id_select2" class="form-control" data-placeholder="${goods.supplier2Name == null ? '请选择...' : goods.supplier2Name}" />
												<form:hidden path="supplier2Id" id="supplier2_id" />
											</div>
										</div>
									</div>
									<div class="col-md-3">
										<div class="form-group">
											<label class="control-label col-md-4">供应商3</label>
											<div class="col-md-8">
												<input type="text" id="supplier3_id_select2" class="form-control" data-placeholder="${goods.supplier3Name == null ? '请选择...' : goods.supplier3Name}" />
												<form:hidden path="supplier3Id" id="supplier3_id" />
											</div>
										</div>
									</div>
									<div class="col-md-3">
										<div class="form-group">
											<label class="control-label col-md-4">产品重量</label>
											<div class="col-md-8">
												<form:input path="weight" cssClass="form-control" />
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-3">
										<div class="form-group">
											<label class="control-label col-md-4">产品单位</label>
											<div class="col-md-8">
												<form:input path="unit" cssClass="form-control" />
											</div>
										</div>
									</div>
									<div class="col-md-3">
										<div class="form-group">
											<label class="control-label col-md-4">导入价</label>
											<div class="col-md-8">
												<form:input path="firstCost" cssClass="form-control" />
											</div>
										</div>
									</div>
									<div class="col-md-3">
						<%-- 				<div class="form-group">
											<label class="control-label col-md-4">建议售价</label>
											<div class="col-md-8">
												<form:input path="price" cssClass="form-control" />
											</div>
										</div> --%>
									</div>
									<div class="col-md-3">
										<div class="form-group">
											<label class="control-label col-md-4">条形码</label>
											<div class="col-md-8">
												<form:input path="barcode" cssClass="form-control" />
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-3">
										<div class="form-group">
											<label class="control-label col-md-4">产品颜色</label>
											<div class="col-md-8">
												<form:input path="color" cssClass="form-control" />
											</div>
										</div>
									</div>
									<div class="col-md-3">
										<div class="form-group">
											<label class="control-label col-md-4">产品大小</label>
											<div class="col-md-8">
												<form:input path="goodsSize" cssClass="form-control" />
											</div>
										</div>
									</div>
									<div class="col-md-3">
										<div class="form-group">
											<label class="control-label col-md-4">产品材质</label>
											<div class="col-md-8">
												<form:input path="materil" cssClass="form-control" />
											</div>
										</div>
									</div>
									<div class="col-md-3">
										<div class="form-group">
											<label class="control-label col-md-4">品牌</label>
											<div class="col-md-8">
												<form:input path="brand" cssClass="form-control" />
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-3">
										<div class="form-group">
											<label class="control-label col-md-4">英文申报名</label>
											<div class="col-md-8">
												<form:input path="declarationNameEn" cssClass="form-control" />
											</div>
										</div>
									</div>
									<div class="col-md-3">
										<div class="form-group">
											<label class="control-label col-md-4">中文申报名</label>
											<div class="col-md-8">
												<form:input path="declarationNameCn" cssClass="form-control" />
											</div>
										</div>
									</div>
									<div class="col-md-3">
										<div class="form-group">
											<label class="control-label col-md-4">申报价值USD</label>
											<div class="col-md-8">
												<form:input path="declarationCost" cssClass="form-control" />
											</div>
										</div>
									</div>
									<div class="col-md-3">
										<div class="form-group">
											<label class="control-label col-md-4">海关编号</label>
											<div class="col-md-8">
												<form:input path="customsCode" cssClass="form-control" />
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-3">
										<div class="form-group">
											<label class="control-label col-md-4">产品大类</label>
											<div class="col-md-8">
												<form:select path="baseCategoryId" cssClass="form-control" id="base_category_id">
													<option value="">请选择...</option>
													<form:options items="${baseCategories }" itemValue="id" itemLabel="name" />
												</form:select>
											</div>
										</div>
									</div>
									<div class="col-md-3">
										<div class="form-group">
											<label class="control-label col-md-4">产品中类</label>
											<div class="col-md-8">
												<select class="form-control" name="midCategoryId" id="mid_category_id">
													<option value="">请选择...</option>
												</select>
											</div>
										</div>
									</div>
									<div class="col-md-3">
										<div class="form-group">
											<label class="control-label col-md-4">产品小类</label>
											<div class="col-md-8">
												<select class="form-control" name="categoryId" id="category_id">
													<option value="">请选择...</option>
												</select>
											</div>
										</div>
									</div>
									<div class="col-md-3">
										<div class="form-group">
											<label class="control-label col-md-4">指定运输方式</label>
											<div class="col-md-8">
												<form:select path="isShipping" cssClass="form-control">
													<form:option value="">请选择...</form:option>
													<form:option value="0">否</form:option>
													<form:option value="1">是</form:option>
												</form:select>
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-3">
										<div class="form-group">
											<label class="control-label col-md-4">含电池？</label>
											<div class="col-md-8">
												<form:select path="isBattery" cssClass="form-control">
													<form:option value="">请选择...</form:option>
													<form:option value="0">否</form:option>
													<form:option value="1">是</form:option>
												</form:select>
											</div>
										</div>
									</div>
									<div class="col-md-3">
										<div class="form-group">
											<label class="control-label col-md-4">是液体？</label>
											<div class="col-md-8">
												<form:select path="isLiquid" cssClass="form-control">
													<form:option value="">请选择...</form:option>
													<form:option value="0">否</form:option>
													<form:option value="1">是</form:option>
												</form:select>
											</div>
										</div>
									</div>
									<div class="col-md-3">
										<div class="form-group">
											<label class="control-label col-md-4">管制品？</label>
											<div class="col-md-8">
												<form:select path="isRegulated" cssClass="form-control">
													<form:option value="">请选择...</form:option>
													<form:option value="0">否</form:option>
													<form:option value="1">是</form:option>
												</form:select>
											</div>
										</div>
									</div>
									<div class="col-md-3">
										<div class="form-group">
											<label class="control-label col-md-4">侵权？</label>
											<div class="col-md-8">
												<form:select path="isCopyright" cssClass="form-control">
													<form:option value="">请选择...</form:option>
													<form:option value="0">否</form:option>
													<form:option value="1">是</form:option>
												</form:select>
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-3">
										<div class="form-group">
											<label class="control-label col-md-4">带包装？</label>
											<div class="col-md-8">
												<form:select path="ispacking" cssClass="form-control">
													<form:option value="">请选择...</form:option>
													<form:option value="0">否</form:option>
													<form:option value="1">是</form:option>
												</form:select>
											</div>
										</div>
									</div>
									<div class="col-md-3">
										<div class="form-group">
											<label class="control-label col-md-4">包装规格</label>
											<div class="col-md-8">
												<form:select path="packingMaterialId" cssClass="form-control">
													<option value="">请选择...</option>
													<form:options items="${packingMateriales }" itemValue="id" itemLabel="model" />
												</form:select>
											</div>
										</div>
									</div>
									<div class="col-md-3">
										<div class="form-group">
											<label class="control-label col-md-4">包装费</label>
											<div class="col-md-8">
												<form:input path="packingFee" cssClass="form-control" />
											</div>
										</div>
									</div>
									<div class="col-md-3">
										<div class="form-group">
											<label class="control-label col-md-4">包装材料</label>
											<div class="col-md-8">
												<form:input path="packingCapacity" cssClass="form-control" />
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-3">
										<div class="form-group">
											<label class="control-label col-md-4">开发时间</label>
											<div class="col-md-8">
												<div class="input-group date date-picker" data-date-format="yyyy-mm-dd">
													<form:input path="developTime" cssClass="form-control" />
													<span class="input-group-btn">
														<button class="btn default" type="button">
															<i class="fa fa-calendar"></i>
														</button>
													</span>
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-3">
										<div class="form-group">
											<label class="control-label col-md-4">开发员</label>
											<div class="col-md-8">
												<form:select path="developUser" cssClass="form-control">
													<option value="">请选择...</option>
													<form:options items="${devUser}" itemValue="userId" itemLabel="name" />
												</form:select>
											</div>
										</div>
									</div>
									<div class="col-md-3">
										<div class="form-group">
											<label class="control-label col-md-4">销售人员</label>
											<div class="col-md-8">
												<form:select path="saleUser" cssClass="form-control">
													<option value="">请选择...</option>
													<form:options items="${saleUser}" itemValue="userId" itemLabel="name" />
												</form:select>
											</div>
										</div>
									</div>

									<div class="col-md-3">
										<div class="form-group">
											<label class="control-label col-md-4">采购人员</label>
											<div class="col-md-8">
												<form:select path="buyUser" cssClass="form-control">
													<option value="">请选择...</option>
													<form:options items="${buyUser}" itemValue="userId" itemLabel="name" />
												</form:select>
											</div>
										</div>
									</div>
								</div>
								<!--/row-->

								<!--/row-->
								<div class="row">
									<div class="col-md-3">
										<div class="form-group">
											<label class="control-label col-md-4">拣货人员</label>
											<div class="col-md-8">
												<form:select path="pickUser" cssClass="form-control">
													<option value="">请选择...</option>
													<form:options items="${packUser}" itemValue="userId" itemLabel="name" />
												</form:select>
											</div>
										</div>
									</div>
									<div class="col-md-3">
										<div class="form-group">
											<label class="control-label col-md-4">配货人员</label>
											<div class="col-md-8">
												<form:select path="assembleUser" cssClass="form-control">
													<option value="">请选择...</option>
													<form:options items="${packUser}" itemValue="userId" itemLabel="name" />
												</form:select>
											</div>
										</div>
									</div>

									<div class="col-md-3">
										<div class="form-group">
											<label class="control-label col-md-4">检验方式</label>
											<div class="col-md-8">
												<form:select path="testType" cssClass="form-control">
													<form:option value="">请选择...</form:option>
													<form:option value="0">全检</form:option>
													<form:option value="1">抽检</form:option>
												</form:select>
											</div>
										</div>
									</div>
									
									<div class="col-md-3">
										<div class="form-group">
											<label class="control-label col-md-4">包装人员</label>
											<div class="col-md-8">
												<form:select path="packUser" cssClass="form-control">
													<option value="">请选择...</option>
													<form:options items="${packUser}" itemValue="userId" itemLabel="name" />
												</form:select>
											</div>
										</div>
									</div>
									
								</div>
			
									<div class="row">
									<div class="col-md-3">
										<div class="form-group">
											<label class="control-label col-md-4">老SKU</label>
											<div class="col-md-8">
												<form:input path="oldSku" cssClass="form-control" />
											</div>
										</div>
									</div>
									<div class="col-md-3">
										<div class="form-group">
											<label class="control-label col-md-4">磁性？</label>
											<div class="col-md-8">
												<form:select path="isMagnetic" cssClass="form-control">
													<form:option value="">请选择...</form:option>
													<form:option value="0">否</form:option>
													<form:option value="1">是</form:option>
												</form:select>
											</div>
										</div>
									</div>
									<div class="col-md-3">
										<div class="form-group">
											<label class="control-label col-md-4">规格</label>
											<div class="col-md-8">
												<form:input path="rules" cssClass="form-control" />
											</div>
										</div>
									</div>
									<div class="col-md-3">
										<div class="form-group">
											<label class="control-label col-md-4">型号</label>
											<div class="col-md-8">
												<form:input path="model" cssClass="form-control" />
											</div>
										</div>
									</div>
								</div>
								
								
								<div class="row">
									<div class="col-md-3">
										<div class="form-group">
											<label class="control-label col-md-4">仓库</label>
											<div class="col-md-8">
												<form:select path="storeId" cssClass="form-control" id="store_id_select">
													<option value="">请选择...</option>
													<form:options items="${stores }" itemValue="id" itemLabel="name" />
												</form:select>
												<%-- <select class="form-control" name="storeId" id="store_id_select">
													<option value="">请选择...</option>
													<c:forEach var="item" items="${stores }">
														<option value="${item.id }">${item.name }</option>
													</c:forEach>
												</select> --%>
											</div>
										</div>
									</div>
									<div class="col-md-3">
										<div class="form-group">
											<label class="control-label col-md-4">货位号</label>
											<div class="col-md-8">
												<select class="form-control" name="storeShelfId" id="store_shelf_id_select">
													<option value="">请选择...</option>
												</select>
											</div>
										</div>
									</div>
									
									<div class="col-md-3">
										<div class="form-group">
											<label class="control-label col-md-4">货品所属</label>
											<div class="col-md-8">
												<form:select path="belongSelf" cssClass="form-control">
													<form:option value="">请选择...</form:option>
													<form:option value="1">公司</form:option>
													<form:option value="0">第三方</form:option>
												</form:select>
											</div>
										</div>
									</div>			
								</div>
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label class="control-label col-md-2">备注一</label>
											<div class="col-md-10">
												<form:input path="note" cssClass="form-control" />
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label class="control-label col-md-2">备注二</label>
											<div class="col-md-10">
												<form:input path="note2" cssClass="form-control" />
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label class="control-label col-md-2">备注三</label>
											<div class="col-md-10">
												<form:input path="note3" cssClass="form-control" />
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label class="control-label col-md-2">备注四</label>
											<div class="col-md-10">
												<form:input path="note4" cssClass="form-control" />
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label class="control-label col-md-2">备注五</label>
											<div class="col-md-10">
												<form:input path="note5" cssClass="form-control" />
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label class="control-label col-md-2">参考链接</label>
											<div class="col-md-10">
												<form:input path="referenceUrl" cssClass="form-control" />
											</div>
										</div>
									</div>
								</div>
								
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label class="control-label col-md-2">EBAY参考链接</label>
											<div class="col-md-10">
												<form:input path="ebayReferenceUrl" cssClass="form-control" />
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label class="control-label col-md-2">SMT参考链接</label>
											<div class="col-md-10">
												<form:input path="smtReferenceUrl" cssClass="form-control" />
											</div>
										</div>
									</div>
								</div>
								
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label class="control-label col-md-2">AM参考链接</label>
											<div class="col-md-10">
												<form:input path="amReferenceUrl" cssClass="form-control" />
											</div>
										</div>
									</div>
								</div>
								
								
								
								<div class="row">
									<%-- <div class="col-md-6">
										<div class="form-group">
											<label class="control-label col-md-2">选择图片</label>
											<div class="col-md-10">
												<input type="file" name="file" id="file_id" class="form-control" />
												<c:if test="${goods.imgUrl != null }">
												<img style="max-width: 200px;padding-top:10px;" src="${pageContext.request.contextPath }/images/${goods.imgUrl }" />
												</c:if>
											</div>
										</div>
									</div> --%>
									<div class="col-md-12">
										<div class="form-group">
											<label class="control-label col-md-1">商品图片</label>
											<div class="col-md-10">
												<c:forEach begin="1" end="${goods.imgCount }" step="1" var="index">
													<a href="${x:goodsImageThumbnailPath(goods.goodsSku, index, 'M') }" target="_blank" title="${index }" data-gallery>
														<img style="max-width: 200px;padding-top:10px;" src="${x:goodsImageThumbnailPath(goods.goodsSku, index, 'S') }${currentTimeMillisParam}" />
													</a>
												</c:forEach>
												<div id="blueimp-gallery" class="blueimp-gallery blueimp-gallery-controls">
												<div class="slides"></div>
												<h3 class="title"></h3>
												<a class="prev">‹</a>
												<a class="next">›</a>
												<a class="close">×</a>
												<a class="play-pause"></a>
												<ol class="indicator"></ol>
											</div>
											</div>
										</div>
									</div>
								</div>
								<div class="form-actions">
									<div class="row">
										<div class="col-md-12">
											<div class="row">
												<div class="col-md-offset-5 col-md-9">
													<c:if test="${actionType == '1'}">
													<button type="submit" class="btn green" onclick="subForm();"><spring:message code="g.label.save"/></button>
													</c:if>
												</div>
											</div>
										</div>
										<div class="col-md-6"></div>
									</div>
								</div>
							</div>
							
							<c:if test="${! empty descriptions}">
								<ul class="nav nav-tabs">
									<c:forEach items="${descriptions }" var="gd" varStatus="status">
									<li class="${status.index == 0 ? 'active' : ''}">
										<a href="#tab_desc_${status.index }" data-toggle="tab">${gd.language }</a>
									</li>
									</c:forEach>
								</ul>
								<div class="tab-content">
									<c:forEach items="${descriptions }" var="gd" varStatus="status">
									<div class="tab-pane fade in ${status.index == 0 ? 'active' : ''}" id="tab_desc_${status.index }">
										<textarea class="form-control" rows="10">${gd.description }</textarea>
									</div>
									</c:forEach>
								</div>
							</c:if>
							
						</form:form>
					</div>
				</div>
			</div>
		</div>

	</tiles:putAttribute>
	<%-- 页面级别的 JS --%>
	<tiles:putAttribute name="js-page">
		<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-select/bootstrap-select.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/select2/select2.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/jquery-file-upload/blueimp-gallery/jquery.blueimp-gallery.min.js"></script>
		<script type="text/javascript">
		
			xfy.GoodsEdit = {
				// 供应商即使搜索
				handleSupplierSelect: function(jqSelector) {
					$(jqSelector).select2({
						minimumInputLength: 1,
						ajax: {
							delay: 1000,
							url: '${pageContext.request.contextPath }/supplier/page-json?status=1',
							data: function (params) {
								var data = {
									draw: 1,
									length: 10,
									start: 0,
									search: {value: params}
								};
								return JSON.stringify( data );
							},
							cache: true,
							params: {
								type: 'post',
								contentType:"application/json;charset=UTF-8"
							},
							results: function (result) {
								return {
									results: result.data
								};
							}
						},
						formatResult: function(item) {
							return item.companyName;
						},
						formatSelection: function(item) {
							var selector = jqSelector.replace('_select2', '');
							$(selector).val(item.id);
							return item.companyName;
						}
					});
				},
				
				handleCategorySelect: function(midCatId, catId) {
					// 大类级联显示中类
					$('#base_category_id').change(function() {
						var selectedValue = $(this).val();
						if (selectedValue == '') {
							$('#mid_category_id').html('<option value="">请选择...</option>');
							$('#category_id').html('<option value="">请选择...</option>');
							return;
						}
						
						$.post('${pageContext.request.contextPath }/goodscategory/list-json', {id: selectedValue}, function(data) {
							xfy.fillSelect('#mid_category_id', data, 'id', 'name');
							$('#category_id').html('<option value="">请选择...</option>');
							if (midCatId != '') {
								$('#mid_category_id').val(midCatId);
								$('#mid_category_id').change();
							}
						}, 'json');
					});
					
					// 中类级联显示小类
					$('#mid_category_id').change(function() {
						var selectedValue = $(this).val();
						if (selectedValue == '') {
							$('#category_id').html('<option value="">请选择...</option>');
							return;
						}
						
						$.post('${pageContext.request.contextPath }/goodscategory/list-json', {id: selectedValue}, function(data) {
							xfy.fillSelect('#category_id', data, 'id', 'name');
							if (catId != '') {
								$('#category_id').val(catId);
							}
						}, 'json');
					});
				},
				
				handleStoreSelect: function(storeShelfId) {
					// 仓库级联显示
					$('#store_id_select').change(function() {
						var selectedValue = $(this).val();
						if (selectedValue == '') {
							$('#store_shelf_id_select').html('<option value="">请选择...</option>');
							return;
						}
						
						$.post('${pageContext.request.contextPath }/store/shelf-json', {id: selectedValue}, function(data) {
							xfy.fillSelect('#store_shelf_id_select', data, 'id', 'code');
							if (storeShelfId != '') {
								$('#store_shelf_id_select').val(storeShelfId);
							}
						}, 'json');
					});
				},
				
				handleFormValidate: function() {
					$("#edit_goods_form").validate({
						errorElement : 'span', //default input error message container
						errorClass : 'help-block help-block-error', // default input error message class
						focusInvalid : false, // do not focus the last invalid input
						ignore : "", // validate all fields including form hidden input
						highlight : function(element) { // hightlight error inputs
							$(element).closest('.form-group').addClass('has-error'); // set error class to the control group
						},
						unhighlight : function(element) { // revert the change done by hightlight
							$(element).closest('.form-group').removeClass('has-error'); // set error class to the control group
						},
						success : function(label) {
							label.closest('.form-group').removeClass('has-error'); // set success class to the control group
						},
						rules : {
							name : {required : true},
							goodsSku : {
								required : true, 
								maxlength : 50,
								remote:{
									url:'${pageContext.request.contextPath }/goods/checkSku',
									type:'post',
									dataType: "json",
									data:{
										goodsSku:function(data){
											return $('#goodsSku').val();
										},
										id:function(){
											return $('#id').val();
										}
									}
								}
								},
							status : {required : true},
							storeId : {required : true},
							storeShelfId : {required : true},
							baseCategoryId : {required : true},
							midCategoryId : {required : true},
							categoryId : {required : true},
							price : {number: true},
							/* cost : {number: true}, */
							length : {number: true},
							width : {number: true},
							height : {number: true},
							weight : {number: true},
							declarationCost: {number: true},
							packingFee: {number: true}
						},
						messages:{
							goodsSku: {
								remote: "商品SKU已经存在 !"
							}
						}
					});
				}
			};
		
		$(function(){
			$('.date-picker').datepicker({
				orientation : "right",
				autoclose : true
			});
			
			xfy.GoodsEdit.handleSupplierSelect("#supplier_id_select2");
			xfy.GoodsEdit.handleSupplierSelect("#supplier2_id_select2");
			xfy.GoodsEdit.handleSupplierSelect("#supplier3_id_select2");
			xfy.GoodsEdit.handleFormValidate();
			
			xfy.GoodsEdit.handleStoreSelect('${goods.storeShelfId}');

			xfy.GoodsEdit.handleCategorySelect('${goods.midCategoryId}', '${goods.categoryId}');
			
			$('#store_id_select').change();
			$('#base_category_id').change();

		});
		</script>
	</tiles:putAttribute>
</tiles:insertDefinition>
