<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<c:set scope="request" var="sysModule" value="personal_center" />
<c:set scope="request" var="sysPage" value="personal_center_view_personal_profile" />

<tiles:insertDefinition name="metronicTemplate">
	<tiles:putAttribute name="title">查看个人基本资料</tiles:putAttribute>
	<tiles:putAttribute name="page-content">
		<div class="row">
			<div class="col-md-12">
				<div class="portlet light">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-cogs font-green-sharp"></i>
							<span class="caption-subject font-green-sharp bold uppercase">查看个人基本资料</span>
						</div>
						<div class="tools">
							<a href="javascript:;" class="collapse"></a>
						</div>
					</div>
					<div class="portlet-body">
						<table class="table table-striped table-bordered table-hover" id="users_info_table">
							<thead>个人基本资料</thead>
								<tr>
									<td>姓名：</td>
									<td>${user.name}</td>
								</tr>
								<tr>
									<td>用户名：</td>
									<td>${user.username }</td>
								</tr>
								<tr>
									<td>身份证号码：</td>
									<td>${user.idCardNo }</td>
								</tr>
								<tr>
									<td>居住地址：</td>
									<td>${user.address }</td>
								</tr>
								<tr>
									<td>电子邮箱：</td>
									<td>${user.email }</td>
								</tr>
								<tr>
									<td>手机号码：</td>
									<td>${user.mobile }</td>
								</tr>
								<tr>
									<td><h4>我的余额：</h4></td>
									<td><h4><span style="color:red;">￥ ${sellerDeposit == null ? 0 : sellerDeposit.deposit }</span></h4></td>
								</tr>
						</table>
					</div>
				</div>
			</div>	
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>