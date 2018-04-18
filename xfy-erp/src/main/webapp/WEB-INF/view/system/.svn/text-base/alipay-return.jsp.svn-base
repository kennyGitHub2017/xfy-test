<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:insertDefinition name="metronicTemplate">
	<tiles:putAttribute name="title">支付宝返回通知</tiles:putAttribute>
	<tiles:putAttribute name="page-content">
		<div class="row">
			<div class="col-md-12">
				<div class="portlet light">
					<div style="text-align:center;">
						<h2 class="glyphicon glyphicon-ok" style="color:red; font-size:40px;"></h2><span>支付结果：<span style="color:red;">${tradeStatus }</span></span>
					</div>
					<div class="portlet-body" style="text-align:center;">
						<table class="table table-striped table-bordered table-hover" id="users_info_table">
							<tr>
								<td>订单号： </td>
								<td>${tradeNo }</td>
							</tr>
							<tr>
								<td>充值金额：</td>
								<td> ${totalFee } </td>
							</tr>
							<tr>
								<td>账户余额： </td>
								<td> ${sellerDeposit == null ? 0 : sellerDeposit.deposit } </td>
							</tr>
							<tr>
								<td>日期： </td>
								<td> ${notifyTime }</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>