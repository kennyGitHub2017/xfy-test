<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://tags.xuanfeiyang.com/tags" prefix="x"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<tiles:putAttribute name="page-content">
	<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal">
		<span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>
	</button>
	<h4 class="modal-title" id="">卖家订单</h4>
	</div>

	<div class="row">

		<div class="col-md-12">

			<div class="portlet light">
				<div class="portlet-body">
					
					<div class="table-scrollable">
						<table class="table table-bordered table-hover">
							<thead>
								<tr>
									<th>卖家名</th>
									<th>账号名</th>
									<th>订单数</th>
								</tr>
							</thead>

							<tbody>
							<c:forEach var="item" items="${countOrder}">
								<tr>
								<th>${item.contacts}</th>
								<td>${item.accountName}</td>
								<td>${item.count}</td>
								</tr>
							</c:forEach>
							</tbody>

						</table>
					</div>
				</div>
			</div>
			
		</div>
	</div>
</tiles:putAttribute>


<tiles:putAttribute name="js-page">

</tiles:putAttribute>


	