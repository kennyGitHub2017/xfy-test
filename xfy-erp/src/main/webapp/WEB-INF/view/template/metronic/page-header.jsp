<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="page-header">
	<!-- BEGIN HEADER TOP -->
	<div class="page-header-top">
		<div class="${layoutClass }">
			<%-- Logo --%>
			<div class="page-logo" style="font-size:24px; line-height:75px;">
				CSS - 跨境交易系统
				<%-- <a href="${pageContext.request.contextPath }">
					<img src="<%=request.getContextPath() %>/resources/assets/admin/layout3/img/logo-default.png" alt="logo" class="logo-default">
				</a> --%>
			</div>
			<%-- 顶部导航 --%>
			<div class="top-menu">
				<ul class="nav navbar-nav pull-right">
					<li class="dropdown dropdown-user dropdown-dark">
						<a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
						<img alt="" class="img-circle" src="${pageContext.request.contextPath }/resources/assets/admin/layout3/img/avatar9.jpg">
						<span class="username username-hide-mobile">${sessionUser.username}</span>
						</a>
						<ul class="dropdown-menu dropdown-menu-default">
							<li>
								<a href="#" class=" fun-coming-soon">
								<i class="icon-user"></i> 我的资料  </a>
							</li>
							<li>
								<a href="${pageContext.request.contextPath }/my/password">
								<i class="icon-user"></i> 修改密码  </a>
							</li>
							<li class="divider">
							</li>
							<li>
								<a href="#">
								<i class="icon-lock"></i> Lock Screen </a>
							</li>
							<li>
								<a href="${pageContext.request.contextPath }/logout">
								<i class="icon-key"></i> 退出系统 </a>
							</li>
						</ul>
					</li>
					<li class="dropdown-separator">
						<span class="separator"></span>
					</li>
					<%--
					<li class="btn-theme-panel">
						<a href="javascript:;"
						class="btn dropdown-toggle" data-toggle="dropdown"
						data-hover="dropdown" data-close-others="true"> <i
							class="icon-settings"></i>
						</a>
						<div
							class="dropdown-menu theme-panel pull-right dropdown-custom hold-on-click">
							<div class="row">
								<div class="col-md-6 col-sm-6 col-xs-12">
									<h3>THEME COLORS</h3>
									<div class="row">
										<div class="col-md-6 col-sm-6 col-xs-12">
											<ul class="theme-colors">
												<li class="theme-color theme-color-default"
													data-theme="default"><span class="theme-color-view"></span>
													<span class="theme-color-name">Default</span></li>
												<li class="theme-color theme-color-blue-hoki"
													data-theme="blue-hoki"><span class="theme-color-view"></span>
													<span class="theme-color-name">Blue Hoki</span></li>
												<li class="theme-color theme-color-blue-steel"
													data-theme="blue-steel"><span class="theme-color-view"></span>
													<span class="theme-color-name">Blue Steel</span></li>
												<li class="theme-color theme-color-yellow-orange"
													data-theme="yellow-orange"><span
													class="theme-color-view"></span> <span
													class="theme-color-name">Orange</span></li>
												<li class="theme-color theme-color-yellow-crusta"
													data-theme="yellow-crusta"><span
													class="theme-color-view"></span> <span
													class="theme-color-name">Yellow Crusta</span></li>
											</ul>
										</div>
										<div class="col-md-6 col-sm-6 col-xs-12">
											<ul class="theme-colors">
												<li class="theme-color theme-color-green-haze"
													data-theme="green-haze"><span class="theme-color-view"></span>
													<span class="theme-color-name">Green Haze</span></li>
												<li class="theme-color theme-color-red-sunglo"
													data-theme="red-sunglo"><span class="theme-color-view"></span>
													<span class="theme-color-name">Red Sunglo</span></li>
												<li class="theme-color theme-color-red-intense"
													data-theme="red-intense"><span
													class="theme-color-view"></span> <span
													class="theme-color-name">Red Intense</span></li>
												<li class="theme-color theme-color-purple-plum"
													data-theme="purple-plum"><span
													class="theme-color-view"></span> <span
													class="theme-color-name">Purple Plum</span></li>
												<li class="theme-color theme-color-purple-studio"
													data-theme="purple-studio"><span
													class="theme-color-view"></span> <span
													class="theme-color-name">Purple Studio</span></li>
											</ul>
										</div>
									</div>
								</div>
								<div class="col-md-6 col-sm-6 col-xs-12 seperator">
									<h3>LAYOUT</h3>
									<ul class="theme-settings">
										<li>Theme Style <select
											class="theme-setting theme-setting-style form-control input-sm input-small input-inline tooltips"
											data-original-title="Change theme style"
											data-container="body" data-placement="left">
												<option value="boxed" selected="selected">Square
													corners</option>
												<option value="rounded">Rounded corners</option>
										</select>
										</li>
										<li>Layout <select
											class="theme-setting theme-setting-layout form-control input-sm input-small input-inline tooltips"
											data-original-title="Change layout type"
											data-container="body" data-placement="left">
												<option value="boxed" selected="selected">Boxed</option>
												<option value="fluid">Fluid</option>
										</select>
										</li>
										<li>Top Menu Style <select
											class="theme-setting theme-setting-top-menu-style form-control input-sm input-small input-inline tooltips"
											data-original-title="Change top menu dropdowns style"
											data-container="body" data-placement="left">
												<option value="dark" selected="selected">Dark</option>
												<option value="light">Light</option>
										</select>
										</li>
										<li>Top Menu Mode <select
											class="theme-setting theme-setting-top-menu-mode form-control input-sm input-small input-inline tooltips"
											data-original-title="Enable fixed(sticky) top menu"
											data-container="body" data-placement="left">
												<option value="fixed">Fixed</option>
												<option value="not-fixed" selected="selected">Not
													Fixed</option>
										</select>
										</li>
										<li>Mega Menu Style <select
											class="theme-setting theme-setting-mega-menu-style form-control input-sm input-small input-inline tooltips"
											data-original-title="Change mega menu dropdowns style"
											data-container="body" data-placement="left">
												<option value="dark" selected="selected">Dark</option>
												<option value="light">Light</option>
										</select>
										</li>
										<li>Mega Menu Mode <select
											class="theme-setting theme-setting-mega-menu-mode form-control input-sm input-small input-inline tooltips"
											data-original-title="Enable fixed(sticky) mega menu"
											data-container="body" data-placement="left">
												<option value="fixed" selected="selected">Fixed</option>
												<option value="not-fixed">Not Fixed</option>
										</select>
										</li>
									</ul>
								</div>
							</div>
						</div> <!-- END THEME PANEL -->
					</li> --%>
				</ul>
			</div>
		</div>
	</div>
	<div class="page-header-menu">
		<div class="${layoutClass }">
			<%--<form class="search-form" action="extra_search.html" method="GET">
				<div class="input-group">
					<input type="text" class="form-control" placeholder="Search" name="query">
					<span class="input-group-btn">
					<a href="javascript:;" class="btn submit"><i class="icon-magnifier"></i></a>
					</span>
				</div>
			</form> --%>
			<%-- DOC: Apply "hor-menu-light" class after the "hor-menu" class below to have a horizontal menu with white background --%>
			<%-- DOC: Remove data-hover="dropdown" and data-close-others="true" attributes below to disable the dropdown opening on mouse hover --%>
			<div class="hor-menu ">
				<ul class="nav navbar-nav">
					<li class="active">
						<a href="${pageContext.request.contextPath }">首页</a>
					</li>
					<li>
						<a href="#" class="dropdown-toggle fun-coming-soon">
						订单管理 <i class="fa fa-angle-down"></i>
						</a>
					</li>
					<li class="menu-dropdown mega-menu-dropdown">
						<a data-hover="megamenu-dropdown" data-close-others="true" data-toggle="dropdown" href="javascript:;" class="dropdown-toggle">
						库存管理 <i class="fa fa-angle-down"></i>
						</a>
						<ul class="dropdown-menu" style="min-width: 480px">
							<li>
								<div class="mega-menu-content">
									<div class="row">
										<div class="col-md-4">
											<ul class="mega-menu-submenu">
												<li>
													<a href="${pageContext.request.contextPath }/goods">
														<i class="fa fa-angle-right"></i> 产品资料
													 </a>
												</li>
												<li>
													<a href="${pageContext.request.contextPath }/store">
														<i class="fa fa-angle-right"></i> 仓库货位
													 </a>
												</li>
												<li>
													<a href="${pageContext.request.contextPath }/goodscategory/firstcgy-list">
														<i class="fa fa-angle-right"></i> 产品类目维护
													 </a>
												</li>
												<li>
													<a href="${pageContext.request.contextPath }/packingMaterial">
														<i class="fa fa-angle-right"></i> 包装材料
													 </a>
												</li>
											</ul>
										</div>
										<div class="col-md-4">
											<ul class="mega-menu-submenu">
												<li>
													<a href="${pageContext.request.contextPath }/io-order?type=0">
														<i class="fa fa-angle-right"></i> 入库单
													 </a>
												</li>
												<li>
													<a href="${pageContext.request.contextPath }/io-order?type=1">
														<i class="fa fa-angle-right"></i> 出库单
													 </a>
												</li>
												<li>
													<a href="${pageContext.request.contextPath }/allocateOrder/list">
														<i class="fa fa-angle-right"></i> 调拨单
													 </a>
												</li>
											</ul>
										</div>
										<div class="col-md-4">
											<ul class="mega-menu-submenu">
												<li>
													<a href="${pageContext.request.contextPath }/io-order/io-listing">
														<i class="fa fa-angle-right"></i> 商品收发明细
													 </a>
												</li>
												<li>
													<a href="${pageContext.request.contextPath }/inventory/stat">
														<i class="fa fa-angle-right"></i> 库存状态查询
													 </a>
												</li>
												<li>
													<a href="${pageContext.request.contextPath }/inventory">
														<i class="fa fa-angle-right"></i> 即时库存查询
													 </a>
												</li>
											</ul>
										</div>
									</div>
								</div>
							</li>
						</ul>
					</li>
					<li class="menu-dropdown mega-menu-dropdown">
						<a data-hover="megamenu-dropdown" data-close-others="true" data-toggle="dropdown" href="javascript:;" class="dropdown-toggle">
						采购管理 <i class="fa fa-angle-down"></i>
						</a>
						<ul class="dropdown-menu">
							<li>
								<a href="${pageContext.request.contextPath }/supplier">
									<i class="fa fa-angle-right"></i> 供应商管理
								</a>
							</li>
							<li>
								<a href="${pageContext.request.contextPath }/supplier-price">
									<i class="fa fa-angle-right"></i> 采购报价管理
								</a>
							</li>
							<li>
								<a href="${pageContext.request.contextPath }/purchaserequest-order/list">
									<i class="fa fa-angle-right"></i> 请购单
								</a>
							</li>
							<li>
								<a href="${pageContext.request.contextPath }/purchaseorder/list">
									<i class="fa fa-angle-right"></i> 采购单
								</a>
							</li>
						</ul>
					</li>
					<li class="menu-dropdown mega-menu-dropdown">
						<a data-hover="megamenu-dropdown" data-close-others="true" data-toggle="dropdown" href="javascript:;" class="dropdown-toggle">
						系统管理 <i class="fa fa-angle-down"></i>
						</a>
						<ul class="dropdown-menu" style="min-width: 480px">
							<li>
								<div class="mega-menu-content">
									<div class="row">
										<div class="col-md-4">
											<ul class="mega-menu-submenu">
												<li>
													<h3>账号管理</h3>
												</li>
												<li>
													<a href="${pageContext.request.contextPath }/account/ebay/list">
														<i class="fa fa-angle-right"></i> eBay 账号
													 </a>
												</li>
												<li>
													<a href="${pageContext.request.contextPath }/account/am/list">
														<i class="fa fa-angle-right"></i> Amazon 账号
													 </a>
												</li>
												<li>
													<a href="${pageContext.request.contextPath }/account/smt/list">
														<i class="fa fa-angle-right"></i> 速卖通账号
													 </a>
												</li>
												<li>
													<a href="${pageContext.request.contextPath }/paypalAccount">
														<i class="fa fa-angle-right"></i> Paypal 账号
													 </a>
												</li>
											</ul>
										</div>
										<div class="col-md-4">
											<ul class="mega-menu-submenu">
												<li>
													<h3>用户 / 卖家</h3>
												</li>
												<li>
													<a href="${pageContext.request.contextPath }/department">
														<i class="fa fa-angle-right"></i> 部门管理
													 </a>
												</li>
												<li>
													<a href="${pageContext.request.contextPath }/user">
														<i class="fa fa-angle-right"></i> 职员管理
													 </a>
												</li>
												<li>
													<a href="${pageContext.request.contextPath }/role">
														<i class="fa fa-angle-right"></i> 角色管理
													 </a>
												</li>
												<li>
													<a href="${pageContext.request.contextPath }/seller">
														<i class="fa fa-angle-right"></i> 卖家管理
													 </a>
												</li>
											</ul>
										</div>
										<div class="col-md-4">
											<ul class="mega-menu-submenu">
												<li>
													<h3>其他</h3>
												</li>
												<li>
													<a href="${pageContext.request.contextPath }/currencyRates">
														<i class="fa fa-angle-right"></i> 汇率信息
													 </a>
												</li>
												<li>
													<a href="${pageContext.request.contextPath }/country">
														<i class="fa fa-angle-right"></i> 国家地区
													 </a>
												</li>
												<li>
													<a href="${pageContext.request.contextPath }/shipping">
														<i class="fa fa-angle-right"></i> 发货方式
													 </a>
												</li>
												<li>
													<a href="${pageContext.request.contextPath }/dict">
														<i class="fa fa-angle-right"></i> 代码字典维护
													 </a>
												</li>
											</ul>
										</div>
									</div>
								</div>
							</li>
						</ul>
					</li>
					<%-- <li class="menu-dropdown mega-menu-dropdown active">
						<a data-hover="megamenu-dropdown" data-close-others="true" data-toggle="dropdown" href="javascript:;" class="dropdown-toggle">
						Features <i class="fa fa-angle-down"></i>
						</a>
						<ul class="dropdown-menu" style="min-width: 710px">
							<li>
								<div class="mega-menu-content">
									<div class="row">
										<div class="col-md-4">
											<ul class="mega-menu-submenu">
												<li>
													<h3>eCommerce</h3>
												</li>
												<li>
													<a href="ecommerce_index.html" class="iconify">
													<i class="icon-home"></i>
													eCommerce </a>
												</li>
												<li>
													<a href="ecommerce_orders.html" class="iconify">
													<i class="icon-basket"></i>
													Manage Orders </a>
												</li>
												<li>
													<a href="ecommerce_orders_view.html" class="iconify">
													<i class="icon-tag"></i>
													Order View </a>
												</li>
												<li>
													<a href="ecommerce_products.html" class="iconify">
													<i class="icon-handbag"></i>
													Manage Products </a>
												</li>
												<li>
													<a href="ecommerce_products_edit.html" class="iconify">
													<i class="icon-pencil"></i>
													Product Edit </a>
												</li>
											</ul>
										</div>
										<div class="col-md-4">
											<ul class="mega-menu-submenu">
												<li>
													<h3>Layouts</h3>
												</li>
												<li class="active">
													<a href="layout_fluid.html" class="iconify">
													<i class="icon-cursor-move"></i>
													Fluid Layout </a>
												</li>
												<li>
													<a href="layout_mega_menu_fixed.html" class="iconify">
													<i class="icon-pin"></i>
													Fixed Mega Menu </a>
												</li>
												<li>
													<a href="layout_top_bar_fixed.html" class="iconify">
													<i class="icon-bar-chart"></i>
													Fixed Top Bar </a>
												</li>
												<li>
													<a href="layout_light_header.html" class="iconify">
													<i class="icon-paper-plane"></i>
													Light Header Dropdowns </a>
												</li>
												<li>
													<a href="layout_blank_page.html" class="iconify">
													<i class="icon-doc"></i>
													Blank Page Layout </a>
												</li>
											</ul>
										</div>
										<div class="col-md-4">
											<ul class="mega-menu-submenu">
												<li>
													<h3>More Layouts</h3>
												</li>
												<li>
													<a href="layout_click_dropdowns.html" class="iconify">
													<i class="icon-speech"></i>
													Click vs. Hover Dropdowns </a>
												</li>
												<li>
													<a href="layout_fontawesome_icons.html" class="iconify">
													<i class="icon-link"></i>
													Layout with Fontawesome </a>
												</li>
												<li>
													<a href="layout_glyphicons.html" class="iconify">
													<i class="icon-settings"></i>
													Layout with Glyphicon </a>
												</li>
												<li>
													<a href="layout_language_bar.html" class="iconify">
													<i class="icon-globe"></i>
													Language Switch Bar </a>
												</li>
												<li>
													<a href="layout_disabled_menu.html" class="iconify">
													<i class=" icon-lock"></i>
													Disabled Menu Links </a>
												</li>
											</ul>
										</div>
									</div>
								</div>
							</li>
						</ul>
					</li>
					<li class="menu-dropdown mega-menu-dropdown mega-menu-full ">
						<a data-hover="megamenu-dropdown" data-close-others="true" data-toggle="dropdown" href="javascript:;" class="dropdown-toggle">
						UI Components <i class="fa fa-angle-down"></i>
						</a>
						<ul class="dropdown-menu">
							<li>
								<div class="mega-menu-content">
									<div class="row">
										<div class="col-md-3">
											<ul class="mega-menu-submenu">
												<li>
													<h3>UI Components</h3>
												</li>
												<li>
													<a href="ui_general.html">
													<i class="fa fa-angle-right"></i>
													General </a>
												</li>
												<li>
													<a href="ui_buttons.html">
													<i class="fa fa-angle-right"></i>
													Buttons </a>
												</li>
												<li>
													<a href="ui_confirmations.html">
													<i class="fa fa-angle-right"></i>
													Popover Confirmations </a>
												</li>
												<li>
													<a href="ui_icons.html">
													<i class="fa fa-angle-right"></i>
													Font Icons </a>
												</li>
												<li>
													<a href="ui_colors.html">
													<i class="fa fa-angle-right"></i>
													Flat UI Colors </a>
												</li>
												<li>
													<a href="ui_typography.html">
													<i class="fa fa-angle-right"></i>
													Typography </a>
												</li>
												<li>
													<a href="ui_tabs_accordions_navs.html">
													<i class="fa fa-angle-right"></i>
													Tabs, Accordions & Navs </a>
												</li>
												<li>
													<a href="ui_tree.html">
													<i class="fa fa-angle-right"></i>
													Tree View </a>
												</li>
												<li>
													<a href="ui_page_progress_style_1.html">
													<i class="fa fa-angle-right"></i>
													Page Progress Bar <span class="badge badge-roundless badge-warning">new</span></a>
												</li>
												<li>
													<a href="ui_blockui.html">
													<i class="fa fa-angle-right"></i>
													Block UI </a>
												</li>
												<li>
													<a href="ui_notific8.html">
													<i class="fa fa-angle-right"></i>
													Notific8 Notifications </a>
												</li>
											</ul>
										</div>
										<div class="col-md-3">
											<ul class="mega-menu-submenu">
												<li>
													<h3>More UI Components</h3>
												</li>
												<li>
													<a href="ui_toastr.html">
													<i class="fa fa-angle-right"></i>
													Toastr Notifications </a>
												</li>
												<li>
													<a href="ui_alert_dialog_api.html">
													<i class="fa fa-angle-right"></i>
													Alerts & Dialogs API <span class="badge badge-roundless badge-danger">new</span></a>
												</li>
												<li>
													<a href="ui_session_timeout.html">
													<i class="fa fa-angle-right"></i>
													Session Timeout </a>
												</li>
												<li>
													<a href="ui_idle_timeout.html">
													<i class="fa fa-angle-right"></i>
													User Idle Timeout </a>
												</li>
												<li>
													<a href="ui_modals.html">
													<i class="fa fa-angle-right"></i>
													Modals </a>
												</li>
												<li>
													<a href="ui_extended_modals.html">
													<i class="fa fa-angle-right"></i>
													Extended Modals </a>
												</li>
												<li>
													<a href="ui_tiles.html">
													<i class="fa fa-angle-right"></i>
													Tiles </a>
												</li>
												<li>
													<a href="ui_datepaginator.html">
													<i class="fa fa-angle-right"></i>
													Date Paginator </a>
												</li>
												<li>
													<a href="ui_nestable.html">
													<i class="fa fa-angle-right"></i>
													Nestable List </a>
												</li>
											</ul>
										</div>
										<div class="col-md-3">
											<ul class="mega-menu-submenu">
												<li>
													<h3>Form Stuff</h3>
												</li>
												<li>
													<a href="form_controls.html">
													<i class="fa fa-angle-right"></i>
													Form Controls </a>
												</li>
												<li>
													<a href="form_icheck.html">
													<i class="fa fa-angle-right"></i>
													iCheck Controls </a>
												</li>
												<li>
													<a href="form_layouts.html">
													<i class="fa fa-angle-right"></i>
													Form Layouts </a>
												</li>
												<li>
													<a href="form_editable.html">
													<i class="fa fa-angle-right"></i>
													Form X-editable <span class="badge badge-roundless badge-success">new</span></a>
												</li>
												<li>
													<a href="form_wizard.html">
													<i class="fa fa-angle-right"></i>
													Form Wizard </a>
												</li>
												<li>
													<a href="form_validation.html">
													<i class="fa fa-angle-right"></i>
													Form Validation </a>
												</li>
												<li>
													<a href="form_image_crop.html">
													<i class="fa fa-angle-right"></i>
													Image Cropping </a>
												</li>
												<li>
													<a href="form_fileupload.html">
													<i class="fa fa-angle-right"></i>
													Multiple File Upload </a>
												</li>
												<li>
													<a href="form_dropzone.html">
													<i class="fa fa-angle-right"></i>
													Dropzone File Upload </a>
												</li>
											</ul>
										</div>
										<div class="col-md-3">
											<ul class="mega-menu-submenu">
												<li>
													<h3>Form Components</h3>
												</li>
												<li>
													<a href="components_pickers.html">
													<i class="fa fa-angle-right"></i>
													Pickers </a>
												</li>
												<li>
													<a href="components_dropdowns.html">
													<i class="fa fa-angle-right"></i>
													Custom Dropdowns </a>
												</li>
												<li>
													<a href="components_form_tools.html">
													<i class="fa fa-angle-right"></i>
													Form Tools </a>
												</li>
												<li>
													<a href="components_editors.html">
													<i class="fa fa-angle-right"></i>
													Markdown & WYSIWYG Editors </a>
												</li>
												<li>
													<a href="components_ion_sliders.html">
													<i class="fa fa-angle-right"></i>
													Ion Range Sliders </a>
												</li>
												<li>
													<a href="components_noui_sliders.html">
													<i class="fa fa-angle-right"></i>
													NoUI Range Sliders </a>
												</li>
												<li>
													<a href="components_jqueryui_sliders.html">
													<i class="fa fa-angle-right"></i>
													jQuery UI Sliders </a>
												</li>
												<li>
													<a href="components_knob_dials.html">
													<i class="fa fa-angle-right"></i>
													Knob Circle Dials </a>
												</li>
											</ul>
										</div>
									</div>
								</div>
							</li>
						</ul>
					</li>
					<li class="menu-dropdown classic-menu-dropdown ">
						<a data-hover="megamenu-dropdown" data-close-others="true" data-toggle="dropdown" href="javascript:;">
						Extra <i class="fa fa-angle-down"></i>
						</a>
						<ul class="dropdown-menu pull-left">
							<li class=" dropdown-submenu">
								<a href=javascript:;>
								<i class="icon-briefcase"></i>
								Data Tables </a>
								<ul class="dropdown-menu">
									<li class=" ">
										<a href="table_basic.html">
										Basic Datatables </a>
									</li>
									<li class=" ">
										<a href="table_responsive.html">
										Responsive Datatables </a>
									</li>
									<li class=" ">
										<a href="table_managed.html">
										Managed Datatables </a>
									</li>
									<li class=" ">
										<a href="table_editable.html">
										Editable Datatables </a>
									</li>
									<li class=" ">
										<a href="table_advanced.html">
										Advanced Datatables </a>
									</li>
									<li class=" ">
										<a href="table_ajax.html">
										Ajax Datatables </a>
									</li>
								</ul>
							</li>
							<li class=" dropdown-submenu">
								<a href=javascript:;>
								<i class="icon-wallet"></i>
								Portlets </a>
								<ul class="dropdown-menu">
									<li class=" ">
										<a href="portlet_general.html">
										General Portlets </a>
									</li>
									<li class=" ">
										<a href="portlet_general2.html">
										New Portlets #1 <span class="badge badge-roundless badge-danger">new</span>
										</a>
									</li>
									<li class=" ">
										<a href="portlet_general3.html">
										New Portlets #2 <span class="badge badge-roundless badge-danger">new</span>
										</a>
									</li>
									<li class=" ">
										<a href="portlet_ajax.html">
										Ajax Portlets </a>
									</li>
									<li class=" ">
										<a href="portlet_draggable.html">
										Draggable Portlets </a>
									</li>
								</ul>
							</li>
							<li class=" dropdown-submenu">
								<a href=javascript:;>
								<i class="icon-bar-chart"></i>
								Charts </a>
								<ul class="dropdown-menu">
									<li class=" ">
										<a href="charts_amcharts.html">
										Amchart </a>
									</li>
									<li class=" ">
										<a href="charts_flotcharts.html">
										Flotchart </a>
									</li>
								</ul>
							</li>
							<li class=" dropdown-submenu">
								<a href=javascript:;>
								<i class="icon-pointer"></i>
								Maps </a>
								<ul class="dropdown-menu">
									<li class=" ">
										<a href="maps_google.html">
										Google Maps </a>
									</li>
									<li class=" ">
										<a href="maps_vector.html">
										Vector Maps </a>
									</li>
								</ul>
							</li>
							<li class=" dropdown-submenu">
								<a href=javascript:;>
								<i class="icon-puzzle"></i>
								Multi Level </a>
								<ul class="dropdown-menu">
									<li class=" ">
										<a href="javascript:;">
										<i class="icon-settings"></i>
										Item 1 </a>
									</li>
									<li class=" ">
										<a href="javascript:;">
										<i class="icon-user"></i>
										Item 2 </a>
									</li>
									<li class=" ">
										<a href="javascript:;">
										<i class="icon-globe"></i>
										Item 3 </a>
									</li>
									<li class=" dropdown-submenu">
										<a href="#">
										<i class="icon-folder"></i>
										Sub Items </a>
										<ul class="dropdown-menu">
											<li class=" ">
												<a href="javascript:;">
												Item 1 </a>
											</li>
											<li class=" ">
												<a href="javascript:;">
												Item 2 </a>
											</li>
											<li class=" ">
												<a href="javascript:;">
												Item 3 </a>
											</li>
											<li class=" ">
												<a href="javascript:;">
												Item 4 </a>
											</li>
										</ul>
									</li>
									<li class=" ">
										<a href="javascript:;">
										<i class="icon-share"></i>
										Item 4 </a>
									</li>
									<li class=" ">
										<a href="javascript:;">
										<i class="icon-bar-chart"></i>
										Item 5 </a>
									</li>
								</ul>
							</li>
						</ul>
					</li>
					<li class="menu-dropdown mega-menu-dropdown mega-menu-full ">
						<a data-hover="megamenu-dropdown" data-close-others="true" data-toggle="dropdown" href="javascript:;" class="dropdown-toggle">
						Pages <i class="fa fa-angle-down"></i>
						</a>
						<ul class="dropdown-menu">
							<li>
								<div class="mega-menu-content">
									<div class="row">
										<div class="col-md-3">
											<ul class="mega-menu-submenu">
												<li>
													<h3>User Pages</h3>
												</li>
												<li>
													<a href="page_timeline.html">
														<i class="fa fa-angle-right"></i>
														New Timeline <span class="badge badge-warning">2</span>
													</a>
												</li>
												<li>
													<a href="page_todo.html">
													<i class="fa fa-angle-right"></i>
													Todo & Tasks <span class="badge badge-danger">4</span></a>
												</li>
												<li>
													<a href="extra_profile.html">
													<i class="fa fa-angle-right"></i>
													User Profile <span class="badge badge-success badge-roundless">new</span></a>
												</li>
												<li>
													<a href="inbox.html">
													<i class="fa fa-angle-right"></i>
													User Inbox <span class="badge badge-success">4</span></a>
												</li>
												<li>
													<a href="page_calendar.html">
													<i class="fa fa-angle-right"></i>
													User Calendar <span class="badge badge-warning">14</span></a>
												</li>
												<li>
													<a href="login.html">
													<i class="fa fa-angle-right"></i>
													Login Form 1 </a>
												</li>
												<li>
													<a href="login_2.html">
													<i class="fa fa-angle-right"></i>
													Login Form 2 </a>
												</li>
												<li>
													<a href="login_3.html">
													<i class="fa fa-angle-right"></i>
													Login Form 3 </a>
												</li>
												<li>
													<a href="login_soft.html">
													<i class="fa fa-angle-right"></i>
													Login Form 4 </a>
												</li>
												<li>
													<a href="extra_lock.html">
													<i class="fa fa-angle-right"></i>
													Lock Screen 1 </a>
												</li>
												<li>
													<a href="extra_lock2.html">
													<i class="fa fa-angle-right"></i>
													Lock Screen 2 </a>
												</li>
											</ul>
										</div>
										<div class="col-md-3">
											<ul class="mega-menu-submenu">
												<li>
													<h3>General Pages</h3>
												</li>
												<li>
													<a href="extra_faq.html">
													<i class="fa fa-angle-right"></i>
													FAQ Page </a>
												</li>
												<li>
													<a href="page_portfolio.html">
													<i class="fa fa-angle-right"></i>
													Portfolio </a>
												</li>
												<li>
													<a href="page_timeline.html">
													<i class="fa fa-angle-right"></i>
													Old Timeline <span class="badge badge-info">4</span></a>
												</li>
												<li>
													<a href="page_coming_soon.html">
													<i class="fa fa-angle-right"></i>
													Coming Soon </a>
												</li>
												<li>
													<a href="extra_invoice.html">
													<i class="fa fa-angle-right"></i>
													Invoice </a>
												</li>
												<li>
													<a href="page_blog.html">
													<i class="fa fa-angle-right"></i>
													Blog </a>
												</li>
												<li>
													<a href="page_blog_item.html">
													<i class="fa fa-angle-right"></i>
													Blog Post </a>
												</li>
											</ul>
										</div>
										<div class="col-md-3">
											<ul class="mega-menu-submenu">
												<li>
													<h3>Custom Pages</h3>
												</li>
												<li>
													<a href="page_news.html">
													<i class="fa fa-angle-right"></i>
													News <span class="badge badge-success">9</span></a>
												</li>
												<li>
													<a href="page_news_item.html">
													<i class="fa fa-angle-right"></i>
													News View </a>
												</li>
												<li>
													<a href="page_about.html">
													<i class="fa fa-angle-right"></i>
													About Us </a>
												</li>
												<li>
													<a href="page_contact.html">
													<i class="fa fa-angle-right"></i>
													Contact Us </a>
												</li>
												<li>
													<a href="extra_search.html">
													<i class="fa fa-angle-right"></i>
													Search Results </a>
												</li>
												<li>
													<a href="extra_pricing_table.html">
													<i class="fa fa-angle-right"></i>
													Pricing Tables </a>
												</li>
											</ul>
										</div>
										<div class="col-md-3">
											<ul class="mega-menu-submenu">
												<li>
													<h3>Miscellaneous</h3>
												</li>
												<li>
													<a href="extra_404_option1.html">
													<i class="fa fa-angle-right"></i>
													404 Page Option 1 </a>
												</li>
												<li>
													<a href="extra_404_option2.html">
													<i class="fa fa-angle-right"></i>
													404 Page Option 2 </a>
												</li>
												<li>
													<a href="extra_404_option3.html">
													<i class="fa fa-angle-right"></i>
													404 Page Option 3 </a>
												</li>
												<li>
													<a href="extra_500_option1.html">
													<i class="fa fa-angle-right"></i>
													500 Page Option 1 </a>
												</li>
												<li>
													<a href="extra_500_option2.html">
													<i class="fa fa-angle-right"></i>
													500 Page Option 2 </a>
												</li>
												<li>
													<a href="email_newsletter.html">
													<i class="fa fa-angle-right"></i>
													Newsletter Email Template </a>
												</li>
												<li>
													<a href="email_system.html">
													<i class="fa fa-angle-right"></i>
													System Email Template </a>
												</li>
											</ul>
										</div>
									</div>
								</div>
							</li>
						</ul>
					</li> --%>
					<!-- <li class="menu-dropdown">
						<a href="javascript:if(confirm('angularjs/  \n\n该文件无法用 Teleport Ultra 下载, 因为 不可用, 或放弃了下载, 或项目即将停止。  \n\n你想在服务器上打开它?'))window.location='angularjs/'" target="_blank" class="tooltips" data-container="body" data-placement="bottom" data-html="true" data-original-title="AngularJS version demo">
						AngularJS Version </a>
					</li> -->
				</ul>
			</div>
			<!-- END MEGA MENU -->
		</div>
	</div>
	<!-- END HEADER MENU -->
</div>