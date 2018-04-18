<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<tiles:insertDefinition name="metronicTemplate">
	<tiles:putAttribute name="title">系统故障</tiles:putAttribute>
	
		<%-- 页面级别的 CSS --%>
	<tiles:putAttribute name="css-page">

<style type="text/css">
.page-500 {
	text-align: center;
}

.page-500 .number {
	display: inline-block;
	letter-spacing: -10px;
	line-height: 128px;
	font-size: 128px;
	font-weight: 300;
	color: #ec8c8c;
	text-align: right;
}

.page-500 .details {
	margin-left: 40px;
	display: inline-block;
	text-align: left;
}
</style>

	</tiles:putAttribute>
	
	<tiles:putAttribute name="page-content">
			<div class="row">
				<div class="col-md-12 page-500">
					<div class=" number">
						 500
					</div>
					<div class=" details">
						<h3>Oops! 系统貌似有些问题.</h3>
						<p>
							攻城师正在全力抢救中!<br/>
							请稍候再试.<br/><br/>
						</p>
					</div>
				</div>
			</div>
	</tiles:putAttribute>
</tiles:insertDefinition>