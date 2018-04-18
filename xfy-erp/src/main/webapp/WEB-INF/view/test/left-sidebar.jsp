<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%
request.setAttribute("module", "order");
%>
<tiles:insertDefinition name="metronicTemplate">
	<tiles:putAttribute name="title">测试页面：一列，只有主要内容</tiles:putAttribute>
	
	<tiles:putAttribute name="page-content">
			<div class="row">
				<div class="col-md-12">
					<div class="portlet light ">
						<div class="portlet-title">
							<div class="caption caption-md">
								<i class="icon-note theme-font"></i>
								<span class="caption-subject theme-font bold uppercase">Page Content</span>
								<span class="caption-helper">weekly stats...</span>
							</div>
						</div>
						<div class="portlet-body">
							<p>
								 Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat.
							</p>
							<p>
								 Lorem ipsum dolor sit amet, consectetuer adipiscing elit, ut laoreet dolore magna aliquam erat volutpat. Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat.
							</p>
							<p>
								 Lorem ipsum dolor sit amet, sit amet, consectetuer adipiscing elit, ut laoreet dolore magna aliquam erat volutpat consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat.
							</p>
						</div>
					</div>
				</div>
			</div>
	</tiles:putAttribute>
</tiles:insertDefinition>