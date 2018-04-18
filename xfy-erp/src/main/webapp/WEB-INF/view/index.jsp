<%-- 主页 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://tags.xuanfeiyang.com/tags" prefix="x" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<tiles:insertDefinition name="metronicTemplate">
	<tiles:putAttribute name="title">主页</tiles:putAttribute>
	
	<tiles:putAttribute name="css-page">
		<link href="${pageContext.request.contextPath }/resources/assets/global/plugins/jquery-circliful/jquery.circliful.css" rel="stylesheet" type="text/css" />
	</tiles:putAttribute>

	<tiles:putAttribute name="page-content">
		<div class="row">
			<div class="col-md-12">
				<%@include file="/WEB-INF/view/include/message.jsp"%>
				
				<c:if test="${userInfo.type == 3 }">
					<div class="alert alert-info alert-dismissable">
						<button type="button" class="close" data-dismiss="alert" aria-hidden="true"></button>
						尊敬的用户，您可以点击<a href="${pageContext.request.contextPath }/agent-index">代理商主页</a>，查看代理商功能。
					</div>
				</c:if>
				
				<c:if test="${seller.id > 1 && platformAccountSize == 0 && seller.status == 0}">
					<div class="alert alert-info alert-dismissable">
						<button type="button" class="close" data-dismiss="alert" aria-hidden="true"></button>
							尊敬的用户，还未绑定平台账户，请先绑定
							<a href="${x:getConfig('kandeng.base.url')}/ebay/Profile/AccountManage.aspx">[eBay账号]</a>
							<a href="${x:getConfig('kandeng.base.url')}/Amazon/Amazon_account.aspx">[amazon账号]</a>
							<a href="${x:getConfig('kandeng.base.url')}/smt/SMTAccountManage.aspx">[速卖通账号]</a>。<br>
							<a href="${x:getConfig('kandeng.base.url')}/wish/Account_list.aspx">[Wish账号]</a>。<br>
							您可以在 <a href="${x:getConfig('kandeng.base.url')}/ui/hotProduct.aspx">产品查询 </a>中查看到部分热销产品。
					</div>
				</c:if>
				
				<c:if test="${seller.id > 1 && platformAccountSize > 0  && seller.status == 0}">
						<div class="alert alert-info alert-dismissable">
						<button type="button" class="close" data-dismiss="alert" aria-hidden="true"></button>
							尊敬的用户，您还未提交认证资料，不能体验所有功能，请先 <a href="${pageContext.request.contextPath }/my/audit-info">提交审核资料</a>。
							您可以在 <a href="http://sale.chinasalestore.com:8083/ui/hotProduct.aspx">产品查询 </a>中查看到部分热销产品。
						</div>
				</c:if>
				

				<c:if test="${seller.id > 1 && seller.status == 1 }">
					<div class="alert alert-info alert-dismissable">
						<button type="button" class="close" data-dismiss="alert" aria-hidden="true"></button>
						尊敬的用户，在我们的工作人员会在一个工作日内完成资料的审核，审核完成后会有短信通知到您，请留意手机短信。如果已经收到短信通知，请重新登录系统进行体验，谢谢。
					</div>
				</c:if>

				<c:if test="${seller.id > 1 && seller.status == 3 }">
					<div class="alert alert-warning alert-dismissable">
						<button type="button" class="close" data-dismiss="alert" aria-hidden="true"></button>
						尊敬的用户，您的资料没有通过我们的审核，原因：${seller.statusNote }。 您可以 <a href="${pageContext.request.contextPath }/my/audit-info">重新提交审核资料</a>。
					</div>
				</c:if>
				
				<c:if test="${pendingSellers > 0 }">
					<div class="alert alert-warning alert-dismissable">
						<button type="button" class="close" data-dismiss="alert" aria-hidden="true"></button>
						目前有 ${pendingSellers } 个待审核的卖家，请尽快处理，<a href="${pageContext.request.contextPath }/seller"> 立即进入卖家管理</a>。
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
							<div class="col-md-3 text-center">
								<div class="goods-stat-chart" data-text="${goodsCount }" data-percent="100" data-fgcolor="#92a3c1"></div>
								<h4>
									<c:choose>
										<c:when test="${seller == null || seller.id == 1 || seller.status == 2 }">
											<a href="${x:getConfig('kandeng.base.url')}/ui/ProductList.aspx?tab=1">商品总数</a>
										</c:when>
										<c:otherwise>
											商品总数
										</c:otherwise>
									</c:choose>
								</h4>
							</div>
							<div class="col-md-3 text-center">
								<div class="goods-stat-chart" data-text="${hotGoods}" data-percent="${hotGoods/goodsCount *100}" data-fgcolor="#92a3c1"></div>
								<h4>
									<c:choose>
										<c:when test="${seller == null || seller.id == 1 || seller.status == 2 }">
											<a href="${x:getConfig('kandeng.base.url')}/ui/ProductList.aspx?tab=4">热销商品数</a>
										</c:when>
										<c:otherwise>
											热销商品数
										</c:otherwise>
									</c:choose>
								</h4>
							</div>
							<div class="col-md-3 text-center">
								<div class="goods-stat-chart" data-text="${newGoodsCount }" data-percent="${newGoodsCount/goodsCount *100}" data-fgcolor="#92a3c1"></div>
								<h4>
									<c:choose>
										<c:when test="${seller == null || seller.id == 1 || seller.status == 2 }">
											<a href="${x:getConfig('kandeng.base.url')}/ui/ProductList.aspx?tab=3">新品推荐数</a>
										</c:when>
										<c:otherwise>
											新品推荐数
										</c:otherwise>
									</c:choose>
								</h4>
							</div>
							<div class="col-md-3 text-center">
								<div class="goods-stat-chart" data-text="${promotionGoods }" data-percent="${promotionGoods/goodsCount *100}" data-fgcolor="#92a3c1"></div>
								<h4>促销商品数</h4>
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
							<i class="fa fa-cogs font-green-sharp"></i> <span class="caption-subject font-green-sharp bold uppercase">订单统计</span>
						</div>
						<div class="tools">
							<a href="javascript:;" class="collapse"></a>
						</div>
					</div>
					<div class="portlet-body">
						<div class="row">
							<div class="col-md-6">
								<div class="well well-sm text-center">
									<span>${orderStat.pendingCount }</span>
									<h4>待审核订单</h4>
								</div>
							</div>
							<div class="col-md-6">
								<div class="well well-sm text-center">
									<span>${orderStat.errorCount }</span>
									<h4>待处理问题件</h4>
								</div>
							</div>
							<div class="col-md-6">
								<div class="well well-sm text-center">
									<span>${orderStat.shippingCount }</span>
									<h4>待发货订单</h4>
								</div>
							</div>
							<div class="col-md-6">
								<div class="well well-sm text-center">
									<span>${orderStat.shippedCount }</span>
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
							<i class="fa fa-cogs font-green-sharp"></i> <span class="caption-subject font-green-sharp bold uppercase">费用统计</span>
						</div>
						<div class="tools">
							<a href="javascript:;" class="collapse"></a>
						</div>
					</div>
					<div class="portlet-body">
						<div class="row">
							<div class="col-md-6">
								<div class="well well-sm text-center">
									<span>￥0</span>
									<h4>待支付费用</h4>
								</div>
							</div>
							<div class="col-md-6">
								<div class="well well-sm text-center">
									<span>￥${balance}</span>
									<h4>可用余额</h4>
								</div>
							</div>
							<div class="col-md-6">
								<div class="well well-sm text-center">
									<span>￥${sumAmountDay}</span>
									<h4>当日费用</h4>
								</div>
							</div>
							<div class="col-md-6">
								<div class="well well-sm text-center">
									<span>￥${sumAmountMonth}</span>
									<h4>月统计费用</h4>
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
							<i class="fa fa-cogs font-green-sharp"></i> <span class="caption-subject font-green-sharp bold uppercase">刊登统计</span>
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
									<h4>今日访问数</h4>
								</div>
							</div>
							<div class="col-md-6">
								<div class="well well-sm text-center">
									<span>0</span>
									<h4>需下架商品</h4>
								</div>
							</div>
							<div class="col-md-6">
								<div class="well well-sm text-center">
									<span>0</span>
									<h4>待上架商品</h4>
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
							<i class="fa fa-cogs font-green-sharp"></i> <span class="caption-subject font-green-sharp bold uppercase">系统资讯</span>
						</div>
						<div class="tools">
							<a href="javascript:;" class="collapse"></a>
						</div>
					</div>
					<div class="portlet-body">
						<div class="row">
							<div class="col-md-12">
								<c:choose>
									<c:when test="${!empty sysInfos }">
										<div align="right"><a target="_blank" href="${pageContext.request.contextPath }/sysinfo/view?type=${sessionUser.sellerId==1?1:0}">more</a><br></div>
										<ol class="info-list">
											<c:forEach items="${sysInfos }" var="info" varStatus="status">
												<li>${info.title }</li>
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
		
		<c:if test="${seller.status == 2 and seller.selfFlag == 0}">
		<div class="modal fade" id="index-tips-modal" role="basic" aria-hidden="true">
			<div class="modal-dialog" style="width:700px;">
				<div class="modal-content">
				
					<div class="modal-header" style="border-bottom: 0;">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
					</div>
					
					<div class="modal-body" style="padding:0 25px;">
						<c:if test="${notice != null }">
						<div style="border-bottom:1px solid #ccc;padding-bottom: 50px;">
							<h4 style="color:rgb(77, 179, 162);"><strong>${notice.title }</strong></h4>
							<div style="padding-left: 25px;font-size:14px;">
							${notice.content }
							</div>
						</div>
						</c:if>
						<div class="row" style="font-size:16px;padding-top:20px;padding-bottom: 50px;">
							<div class="col-md-5 text-left">余额：<strong style="color:red;">${balance}</strong> 元</div>
							<div class="col-md-7 text-right">最近7天每日平均消费：<strong style="color:red;">${avg7 }</strong> 元</div>
						</div>
					</div>
				
				</div>
			</div>
		</div>
		</c:if>
		
	</tiles:putAttribute>
	
	<tiles:putAttribute name="js-page">
		<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/jquery-circliful/jquery.circliful.min.js"></script>
		<script>
			$('.goods-stat-chart').circliful({
				dimension : 200,
				width: 30
			});
			<c:if test="${fn:contains(header.referer, '/login')}">
			<c:if test="${seller.status == 2 and seller.selfFlag == 0}">
			$('#index-tips-modal').modal({backdrop:'static'});
			</c:if>
			</c:if>
		</script>
	</tiles:putAttribute>
</tiles:insertDefinition>