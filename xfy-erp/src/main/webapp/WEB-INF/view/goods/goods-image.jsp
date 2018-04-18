<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tags.xuanfeiyang.com/tags" prefix="x"%>
<%
// 时间戳参数
pageContext.setAttribute("currentTimeMillisParam", "?_" + System.currentTimeMillis());
%>
<tiles:insertDefinition name="emptyMetronicTemplate">
	<tiles:putAttribute name="title">图片维护</tiles:putAttribute>

	<%-- 页面级别的 CSS --%>
	<tiles:putAttribute name="css-page">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/jquery-file-upload/blueimp-gallery/blueimp-gallery.min.css"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/jquery-file-upload/css/jquery.fileupload.css"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/jquery-file-upload/css/jquery.fileupload-ui.css"/>
		<style type="text/css">
			.img-box {
				float: left;
				border: 1px solid #ddd; 
				margin: 10px 10px 10px 0;
				width: 122px;
				height: 150px;
				text-align: center;
				position: relative;
			}
			
			.img-box img {
				max-height: 120px;
			}
			
			.img-box .img-box-btn {
				position: absolute;
				width: 20px;
				height: 20px;
				text-align: center;
				line-height: 20px;
				display: none;
			}
			
			.img-box:hover .img-box-remove,
			.img-box:hover .img-box-move-left,
			.img-box:hover .img-box-move-right {
				display: block;
			}
			
			.img-box .img-box-remove {
				bottom: 0;
				left: 51px;
			}
			
			.img-box .img-box-move-left {
				bottom: 0;
				left: 0;
			}
			.img-box .img-box-move-right {
				bottom: 0;
				right: 0;
			}
			
			.img-box .img-box-no {
				top: 0;
				right : 0;
				display: block;
				background: #FFF;
			}
			
		</style>
	</tiles:putAttribute>

	<tiles:putAttribute name="page-content">
		
		<div class="portlet light">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-cogs font-green-sharp"></i> <span class="caption-subject font-green-sharp bold uppercase">商品图片</span>
					<c:if test="${goods != null }">
						(${goods.goodsSku } - ${goods.name })
					</c:if>
				</div>
			</div>
			<div class="portlet-body">
				<%@include file="../include/message.jsp"%>
				
				<c:if test="${goods != null }">
				<div class="row">
					<div class="col-md-12">
						<c:set var="serverUrl" value="${x:getConfig('image.server.goods.url') }${goods.goodsSku }?_auth=${x:getConfig('image.server.access.key')}" />
						<form id="fileupload" action="${serverUrl}" method="POST" enctype="multipart/form-data">
							<!-- The fileupload-buttonbar contains buttons to add/delete files and start/cancel the upload -->
							<div class="row fileupload-buttonbar">
								<div class="col-lg-7">
									<!-- The fileinput-button span is used to style the file input field as button -->
									<span class="btn green fileinput-button">
									<i class="fa fa-plus"></i>
									<span>添加图片... </span>
									<input type="file" name="file" multiple="">
									</span>
									<button type="submit" class="btn blue start">
									<i class="fa fa-upload"></i>
									<span>开始上传 </span>
									</button>
									<button type="reset" class="btn warning cancel">
									<i class="fa fa-ban-circle"></i>
									<span>取消上传 </span>
									</button>
									<!-- <button type="button" class="btn red delete">
									<i class="fa fa-trash"></i>
									<span>
									Delete </span>
									</button>
									<input type="checkbox" class="toggle"> -->
									<!-- The global file processing state -->
									<span class="fileupload-process">
									</span>
								</div>
								<!-- The global progress information -->
								<div class="col-lg-5 fileupload-progress fade">
									<!-- The global progress bar -->
									<div class="progress progress-striped active" role="progressbar" aria-valuemin="0" aria-valuemax="100">
										<div class="progress-bar progress-bar-success" style="width:0%;">
										</div>
									</div>
									<!-- The extended global progress information -->
									<div class="progress-extended">
										 &nbsp;
									</div>
								</div>
							</div>
							<!-- The table listing the files available for upload/download -->
							<table role="presentation" class="table table-condensed clearfix">
							<tbody class="files">
							</tbody>
							</table>
						</form>
					</div>
				</div>

				<div class="row">
					<c:if test="${goods.imgCount > 0 }">
						<div class="col-md-12">
							<button type="button" data-loading-text="Loading ..." id="save-btn" class="btn btn-primary">以下为现有图片，移动/删除图片后，请点击这里保存</button>
							<button type="button" data-loading-text="Loading ..." id="delete-all-btn" class="btn btn-danger">点击这里删除以下所有图片</button>
						</div>
						<div class="col-md-12">
							<c:forEach begin="1" end="${goods.imgCount }" step="1" var="index">
								<div class="img-box">
									<a href="${x:goodsImageThumbnailPath(goods.goodsSku, index, 'M')}${currentTimeMillisParam}" title="${index }" data-gallery>
										<img src="${x:goodsImageThumbnailPath(goods.goodsSku, index, 'S')}${currentTimeMillisParam}" />
									</a>
									<a href="javascript:;" title="删除" class="img-box-btn img-box-remove"><i class="fa fa-trash-o"></i></a>
									<a href="javascript:;" title="左移" class="img-box-btn img-box-move-left"><i class="fa fa-arrow-left"></i></a>
									<a href="javascript:;" title="右移" class="img-box-btn img-box-move-right"><i class="fa fa-arrow-right"></i></a>
									<span class="img-box-btn img-box-no">${index }</span>
								</div>
							</c:forEach>
						</div>
						
						<div id="blueimp-gallery" class="blueimp-gallery blueimp-gallery-controls">
							<div class="slides"></div>
							<h3 class="title"></h3>
							<a class="prev">‹</a>
							<a class="next">›</a>
							<a class="close">×</a>
							<a class="play-pause"></a>
							<ol class="indicator"></ol>
						</div>
					</c:if>
				</div>
				</c:if>
			</div>
		</div>
		<%@include file="/WEB-INF/view/include/modal-ajax.jsp"%>
	</tiles:putAttribute>
	
	<tiles:putAttribute name="js-page">
<c:if test="${goods != null }">
<script id="template-upload" type="text/x-tmpl">
{% for (var i=0, file; file=o.files[i]; i++) { %}
	<tr class="template-upload fade">
		<td>
			<span class="preview"></span>
		</td>
		<td>
			<p class="name">{%=file.name%}</p>
			<strong class="error label label-danger"></strong>
		</td>
		<td>
			<p class="size">Processing...</p>
			<div class="progress progress-striped active" role="progressbar" aria-valuemin="0" aria-valuemax="100" aria-valuenow="0">
			<div class="progress-bar progress-bar-success" style="width:0%;"></div>
			</div>
		</td>
		<td>
			{% if (!i && !o.options.autoUpload) { %}
				<button class="btn blue start hidden" disabled>
					<i class="fa fa-upload"></i>
					<span>Start</span>
				</button>
			{% } %}
			{% if (!i) { %}
				<button class="btn red cancel">
					<i class="fa fa-ban"></i>
					<span>Cancel</span>
				</button>
			{% } %}
		</td>
	</tr>
{% } %}
</script>
<!-- The template to display files available for download -->
<script id="template-download" type="text/x-tmpl">
{% for (var i=0, file; file=o.files[i]; i++) { %}
	<tr class="template-download fade">
		<td>
			<span class="preview">
				<a href="{%=file.url%}" title="{%=file.filename%}" download="{%=file.filename%}" data-gallery><img src="{%=file.url.replace('.jpg', '-S.jpg')%}" style="height:80px;"></a>
			</span>
		</td>
		<td>
			<p class="name">
				{% if (file.url) { %}
					<a href="{%=file.url%}" title="{%=file.filename%}" download="{%=file.filename%}" {%=file.thumbnailUrl?'data-gallery':''%}>{%=file.filename%}</a>
				{% } else { %}
					<span>{%=file.filename%}</span>
				{% } %}
			</p>
			{% if (file.error) { %}
				<div><span class="label label-danger">Error</span> {%=file.error%}</div>
			{% } %}
		</td>
		<td>
			<span class="size">{%=o.formatFileSize(file.size)%}</span>
		</td>
		<td>
			<%--{% if (file.deleteUrl) { %}
				<button class="btn red delete btn-sm" data-type="{%=file.deleteType%}" data-url="{%=file.deleteUrl%}"{% if (file.deleteWithCredentials) { %} data-xhr-fields='{"withCredentials":true}'{% } %}>
					<i class="fa fa-trash-o"></i>
					<span>Delete</span>
				</button>
				<input type="checkbox" name="delete" value="1" class="toggle">
			{% } else { %}
				<button class="btn yellow cancel btn-sm">
					<i class="fa fa-ban"></i>
					<span>Cancel</span>
				</button>
			{% } %}--%>
		</td>
	</tr>
{% } %}
</script>
</c:if>

		<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/fancybox/source/jquery.fancybox.pack.js"></script>
		<!-- BEGIN:File Upload Plugin JS files-->
		<!-- The jQuery UI widget factory, can be omitted if jQuery UI is already included -->
		<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/jquery-file-upload/js/vendor/jquery.ui.widget.js"></script>
		<!-- The Templates plugin is included to render the upload/download listings -->
		<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/jquery-file-upload/js/vendor/tmpl.min.js"></script>
		<!-- The Load Image plugin is included for the preview images and image resizing functionality -->
		<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/jquery-file-upload/js/vendor/load-image.min.js"></script>
		<!-- The Canvas to Blob plugin is included for image resizing functionality -->
		<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/jquery-file-upload/js/vendor/canvas-to-blob.min.js"></script>
		<!-- blueimp Gallery script -->
		<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/jquery-file-upload/blueimp-gallery/jquery.blueimp-gallery.min.js"></script>
		<!-- The Iframe Transport is required for browsers without support for XHR file uploads -->
		<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/jquery-file-upload/js/jquery.iframe-transport.js"></script>
		<!-- The basic File Upload plugin -->
		<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/jquery-file-upload/js/jquery.fileupload.js"></script>
		<!-- The File Upload processing plugin -->
		<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/jquery-file-upload/js/jquery.fileupload-process.js"></script>
		<!-- The File Upload image preview & resize plugin -->
		<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/jquery-file-upload/js/jquery.fileupload-image.js"></script>
		<!-- The File Upload audio preview plugin -->
		<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/jquery-file-upload/js/jquery.fileupload-audio.js"></script>
		<!-- The File Upload video preview plugin -->
		<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/jquery-file-upload/js/jquery.fileupload-video.js"></script>
		<!-- The File Upload validation plugin -->
		<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/jquery-file-upload/js/jquery.fileupload-validate.js"></script>
		<!-- The File Upload user interface plugin -->
		<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/jquery-file-upload/js/jquery.fileupload-ui.js"></script>
		<!-- The main application script -->
		<!-- The XDomainRequest Transport is included for cross-domain file deletion for IE 8 and IE 9 -->
		<!--[if (gte IE 8)&(lt IE 10)]>
		    <script src="${pageContext.request.contextPath }/resources/assets/global/plugins/jquery-file-upload/js/cors/jquery.xdr-transport.js"></script>
		    <![endif]-->
		<!-- END:File Upload Plugin JS files-->
		
		<c:if test="${goods != null }">
		<script type="text/javascript">
		$(function() {
			// 这里检测图片的数量跟数据库是不是匹配，不匹配的话，则更新
			$.getJSON('${serverUrl}').done(function(result) {
				var imgCount = ${goods == null ? 0 : goods.imgCount};
				if (result && result.files && result.files.length !== imgCount) {
					window.location.href = '${pageContext.request.contextPath }/goods-image/update-img-count?sku=${goods.goodsSku }';
				}
			});
			
			// 所有原始图片名
			var originImgFilenames = getAllImgFilename();
			//console.log(originImgFilenames);
			
			$('.img-box-move-left').click(function() {
				var $this = $(this);
				var $imgBox = $this.closest("div.img-box");
				var $prevImgBox = $imgBox.prev();
				$prevImgBox.before($imgBox);
			});
			
			$('.img-box-move-right').click(function() {
				var $this = $(this);
				var $imgBox = $this.closest("div.img-box");
				var $nextImgBox = $imgBox.next();
				$nextImgBox.after($imgBox);
			});
			
			$('.img-box-remove').click(function() {
				var $this = $(this);
				var $imgBox = $this.closest("div.img-box");
				$imgBox.remove();
			});
			
			$('#save-btn').click(function() {
				var filenames = getAllImgFilename();
				console.log(filenames);
				if (!filenames || filenames.length == 0) {
					alert('不能全部删除');
					return;
				}
				// 修否有修改
				if (originImgFilenames.toString() == filenames.toString()) {
					console.log('未作任何修改....');
					alert('未作任何修改....');
					return;
				}
				
				if (confirm('该操作将导致不可还原的修改，请确认继续操作？')) {
					$(this).button('loading');
					xfy.requestByForm({
						method: 'post',
						action: '${pageContext.request.contextPath }/goods-image/save',
						data: { sku: '${goods.goodsSku }', filenames: filenames.join(',') }
					});
				}
				
			});
			
			$('#delete-all-btn').click(function() {
				if (confirm('危险操作，此操作会删除该SKU的所有图片。\n请确认是否继续？')) {
					$(this).button('loading');
					$.ajax({
						url: '${serverUrl}',
						type: 'delete',
						success: function() {
							$(this).button('reset');
							window.location.href = '${pageContext.request.contextPath }/goods-image/update-img-count?sku=${goods.goodsSku }';
						}
					});
				}
			});
			
			// Initialize the jQuery File Upload widget:
			$('#fileupload').fileupload({
				disableImageResize: false,
				autoUpload: false,
				sequentialUploads: true,
				disableImageResize: /Android(?!.*Chrome)|Opera/.test(window.navigator.userAgent),
				maxFileSize: 5000000,
				acceptFileTypes: /(\.|\/)(jpe?g)$/i,
				// Uncomment the following to send cross-domain cookies:
				//xhrFields: {withCredentials: true},                
				stopped: function(e, data) {
					window.location.href = '${pageContext.request.contextPath }/goods-image/update-img-count?sku=${goods.goodsSku }';
				}
			});
			
			function getImgFilename($imgBox) {
				var $img = $imgBox.find('img');
				var imgUrl = $img.attr('src');
				imgUrl = imgUrl.substring(0, imgUrl.lastIndexOf('?'))
				var imgFilename = imgUrl.substring(imgUrl.lastIndexOf('/') + 1);
				imgFilename = imgFilename.replace('-S', '');
				return imgFilename;
			}
			
			function getAllImgFilename() {
				var _originImgFilenames = [];
				$('.img-box').each(function() {
					var $imgBox = $(this);
					_originImgFilenames.push(getImgFilename($imgBox));
				});
				
				return _originImgFilenames;
			}
		});
		</script>
		</c:if>
	</tiles:putAttribute>
</tiles:insertDefinition>