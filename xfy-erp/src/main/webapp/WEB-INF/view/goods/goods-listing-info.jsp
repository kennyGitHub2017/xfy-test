<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- 页面级别的 CSS --%>
<tiles:putAttribute name="css-page">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-select/bootstrap-select.min.css"/>
</tiles:putAttribute>
	
	<tiles:putAttribute name="page-content">
	
	<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal">
		<span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>
	</button>
   </div>
   
			<div class="row">
			
				<div class="col-md-12">
					<div class="portlet light">
					
						<div class="portlet-body">
							<table class="table table-striped table-bordered table-hover" id="listing-table">
								<thead>
									<tr>
									<th>平台</th>
									<th>账号</th>
									<th>所属公司？</th>
									<th>itemID</th>
									<th>图片</th>
									<th>平台价格</th>
									<th>标题</th>
									<th>可用数量</th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
				
			</div>
	</tiles:putAttribute>
	
	<%-- 页面级别的 JS --%>
	<tiles:putAttribute name="js-page">
		<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/datatables/media/js/jquery.dataTables.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-select/bootstrap-select.min.js" type="text/javascript"></script>
		<script>

		$(function(){
			xfy.initDataTable();
			loadDate();
		});
		
		//ajax load tabledata
		function loadDate(){
			return $("#listing-table").dataTable({
				
				serverSide: true,
				ordering:false,
				ajax: {
					url:"${pageContext.request.contextPath }/goods/listing-info?goodsSku="+${goodsSku}+"&count="+${count},
					contentType:"application/json;charset=UTF-8",	
					type: 'post',
					data: function (d) {
						return JSON.stringify( d );
					}
				},
				
				columns: [
					
					{data: "platform"},
					{data:'accountName'},
					
					{data:'belongSelf',
						render:function(data){
						if(data == 1){
							return '是';
						}else{
							return '否';
						}
					}},
			
					{
						data:function(row){
							var html = '';
							
							var url = IMessages(row.platform,row.siteId,row.itemId);
							
							html += '<a target="_blank" href='+ url +' >'+ row.itemId +'</a>';
							return html;
						}

					},
					{
						data:function(row){
						var img = '<img class="small-img" src="'+ row.galleryUrl +'" />';
						return img;
						},"sortable":false},
						
					{data: "price"},
					{data: "title"},
					{data: "occupy"}
					
				]
			});
		}
		
		
		
		function IMessages(PlatID, SiteID, ItemID){
			var BaseUrl = "";
	        switch (PlatID)
	        {
	            case "ebay":
	                {
	                    switch (SiteID) {
	                        case "US": BaseUrl = "http://www.ebay.com"; break;
	                        case "UK": BaseUrl = "http://www.ebay.co.uk"; break;
	                        case "Australia": BaseUrl = "http://www.ebay.com.au"; break;
	                        case "eBayMotors": BaseUrl = "http://www.ebay.co.uk"; break;
	                    }
	                    BaseUrl = BaseUrl + "/itm/" + ItemID;
	                }
	                break;
	            case "smt":
	                {
	                    BaseUrl = "http://www.aliexpress.com/item/V/" + ItemID + ".html"
	                }
	                break;
	            case "amazon": {
	                switch (SiteID) {
	                    case "美国站":BaseUrl="http://www.amazon.com" ; break;
	                    case "加拿大站": BaseUrl = "https://www.amazon.ca"; break;
	                    case "日本站": BaseUrl = "https://www.amazon.jp"; break;
	                    case "英国站": BaseUrl = "https://www.amazon.co.uk"; break;
	                    case "意大利站": BaseUrl = "https://www.amazon.it"; break;
	                    case "印度站": BaseUrl = "https://www.amazon.in"; break;
	                    case "法国站": BaseUrl = "https://www.amazon.fr"; break;
	                    case "西班牙": BaseUrl = "https://www.amazon.es"; break;
	                    case "德国站": BaseUrl = "https://www.amazon.de"; break;
	                }
	                if (SiteID == "美国站") {
	                    BaseUrl = BaseUrl + "/dp";
	                }
	                else {
	                    BaseUrl = BaseUrl + "/gp";
	                }
	                BaseUrl= BaseUrl + "/product/" + ItemID;
	            }
	        }
	        return BaseUrl;
		}
		</script>
	</tiles:putAttribute>



