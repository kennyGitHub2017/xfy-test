<%-- 代理商主页 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://tags.xuanfeiyang.com/tags" prefix="x" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<tiles:insertDefinition name="metronicTemplate">
	<tiles:putAttribute name="title">代理商主页</tiles:putAttribute>
	
	<tiles:putAttribute name="css-page">
		<link href="${pageContext.request.contextPath }/resources/assets/global/plugins/jquery-circliful/jquery.circliful.css" rel="stylesheet" type="text/css" />
	</tiles:putAttribute>

	<tiles:putAttribute name="page-content">
		<div class="row">
			<div class="col-md-12">
				<%@include file="/WEB-INF/view/include/message.jsp"%>
				<c:if test="${userInfo.type == 3 && userInfo.sellerId > 1}">
					<div class="alert alert-info alert-dismissable">
						<button type="button" class="close" data-dismiss="alert" aria-hidden="true"></button>
						尊敬的用户，您可以点击<a href="${pageContext.request.contextPath }/">主页</a>，查看卖家功能。
					</div>
				</c:if>
			</div>
		</div>

		<div class="row">
			<div class="col-md-12">
				<div class="portlet light ">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-cogs font-green-sharp"></i> <span class="caption-subject font-green-sharp bold uppercase">最新动态</span>
						</div>
						<div class="tools">
							<a href="javascript:;" class="collapse"></a>
						</div>
					</div>
					<div class="portlet-body">
						<div class="row">
							<div class="well well-sm col-md-3 text-center">
								<h4>
									<label class="control-label col-md-4"><h4><a href="${pageContext.request.contextPath }/withdraw/my-apply?status=0">账号余额</a></h4></label>
										<span class="col-md-8" style="font-weight:bold;font-size:20px;">
											${agentInfo.deposit }￥
										</span>
								</h4>
							</div>
							<div class="well well-sm col-md-3 text-center">
								<h4>
									<label class="control-label col-md-4"><h4><a href="${pageContext.request.contextPath }/withdraw/my-apply?status=0">可提取金额</a></h4></label>
									<span class="col-md-8" style="font-weight:bold;font-size:20px;">
										${agentInfo.deposit>agentInfo.bond?agentInfo.deposit-agentInfo.bond-applyAmount:0}￥
									</span>
								</h4>
							</div>
							<div class="well well-sm col-md-3 text-center">
								<h4>
									<label class="control-label col-md-4"><h4><a href="${pageContext.request.contextPath }/agent-rebate/rebate-all">累积总额</a></h4></label>
										<span class="col-md-8" style="font-weight:bold;font-size:20px;">
											${totalInCome }￥
										</span>
								</h4>
							</div>
							<div class="well well-sm col-md-3 text-center">
								<h4>
									<label class="control-label col-md-4"><h4><a href="${pageContext.request.contextPath }/agent-rebate/rebate-all">近1月收入</a></h4></label>
										<span class="col-md-8" style="font-weight:bold;font-size:20px;">
											${inCome }￥
										</span>
								</h4>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-md-6">
				<div class="portlet light ">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-cogs font-green-sharp"></i> <a href="${pageContext.request.contextPath }/order/agent-order">
							<span class="caption-subject font-green-sharp bold uppercase">订单统计</span></a>
						</div>
						<div class="tools">
							<a href="javascript:;" class="collapse"></a>
						</div>
					</div>
					<div class="portlet-body">
						<div class="row">
							<div class="col-md-6">
								<div class="well well-sm text-center">
									<span>${sellers == null ? 0 : notAudit }</span>
									<h4>待审核订单</h4>
								</div>
							</div>
							<div class="col-md-6">
								<div class="well well-sm text-center">
									<span>${sellers == null ? 0 : suspend }</span>
									<h4>暂停订单</h4>
								</div>
							</div>
							<div class="col-md-6">
								<div class="well well-sm text-center">
									<span>${sellers == null ? 0 : pending }</span>
									<h4>待发货订单</h4>
								</div>
							</div>
							<div class="col-md-6">
								<div class="well well-sm text-center">
									<span>${sellers == null ? 0 : shipped }</span>
									<h4>已发货订单</h4>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="portlet light ">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-cogs font-green-sharp"></i> <a href="${pageContext.request.contextPath }/seller/agent-sellers">
							<span class="caption-subject font-green-sharp bold uppercase">卖家管理</span></a>
						</div>
						<div class="tools">
							<a href="javascript:;" class="collapse"></a>
						</div>
					</div>
					<div class="portlet-body">
						<div class="row">
							<div class="col-md-6">
								<div class="well well-sm text-center">
									<span>${sellerNumber}</span>
									<h4><a href="${pageContext.request.contextPath }/seller/agent-sellers">卖家数量</a></h4>
								</div>
							</div>
							<div class="col-md-6">
								<div class="well well-sm text-center">
									<span>${accountNumber}</span>
									<h4><a href="${pageContext.request.contextPath }/seller/agent-sellers">账号绑定数量</a></h4>
								</div>
							</div>
							<div class="col-md-6">
								<div class="well well-sm text-center">
									<span>${total}</span>
									<h4><a href="${pageContext.request.contextPath }/order/agent-order">订单总量</a></h4>
								</div>
							</div>
							<div class="col-md-6">
								<div class="well well-sm text-center">
									<span>￥${totalAmount}</span>
									<h4><a href="${pageContext.request.contextPath }/seller/agentDeposit-log">流水总金额</a></h4>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="portlet light ">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-cogs font-green-sharp"></i><span class="caption-subject font-green-sharp bold uppercase">卖家在线统计</span>
						</div>
						<div class="tools">
							<a href="javascript:;" class="collapse"></a>
						</div>
					</div>
					<div class="portlet-body">
						<div class="row">
							<div class="col-md-6">
								<div class="well well-sm text-center">
									<span>0</span>
									<h4>在线商品</h4>
								</div>
							</div>
							<div class="col-md-6">
								<div class="well well-sm text-center">
									<span>0</span>
									<h4>上架数量</h4>
								</div>
							</div>
							<div class="col-md-6">
								<div class="well well-sm text-center">
									<span>0</span>
									<h4>下架数量</h4>
								</div>
							</div>
							<div class="col-md-6">
								<div class="well well-sm text-center">
									<span>0</span>
									<h4>刊登数量</h4>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="portlet light ">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-cogs font-green-sharp"></i> <span class="caption-subject font-green-sharp bold uppercase">资金消息日志</span>
						</div>
						<div class="tools">
							<a href="javascript:;" class="collapse"></a>
						</div>
					</div>
					<div class="portlet-body">
						<div class="row">
							<div class="col-md-12">
								<c:choose>
									<c:when test="${!empty logList }">
										<div align="right"><a target="_blank" href="${pageContext.request.contextPath }/agent-rebate/rebate-all"><span style="font-size:15px;">更多</span></a><br></div>
										<ol class="info-list">
											<c:forEach items="${logList }" var="log" varStatus="status">
												<c:if test="${log.amount > 0 }">
												<div class="row">
													<li style="font-size:15px;"><span style="float:right;"><fmt:formatDate value="${log.createdTime }"/></span>您成功收到一笔收入：${log.amount }￥</li>
												</div>
												</c:if>
											</c:forEach>
										</ol>
									</c:when>
									<c:otherwise>
										<p>暂无...</p>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		
	</tiles:putAttribute>
</tiles:insertDefinition>