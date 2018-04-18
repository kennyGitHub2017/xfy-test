prompt PL/SQL Developer import file
prompt Created on 2015年3月28日 by Administrator
set feedback off
set define off
prompt Loading SYS_FUNCTIONS...
insert into SYS_FUNCTIONS (code, name, page_code)
values ('supplier_add', '添加供应商', 'supplier');
insert into SYS_FUNCTIONS (code, name, page_code)
values ('supplier_price_add', '添加采购报价', 'supplier_price');
insert into SYS_FUNCTIONS (code, name, page_code)
values ('supplier_price_edit', '修改采购报价', 'supplier_price');
insert into SYS_FUNCTIONS (code, name, page_code)
values ('supplier_price_delete', '删除采购报价', 'supplier_price');
insert into SYS_FUNCTIONS (code, name, page_code)
values ('supplier_edit', '修改供应商', 'supplier');
insert into SYS_FUNCTIONS (code, name, page_code)
values ('supplier_approved', '审核供应商', 'supplier');
insert into SYS_FUNCTIONS (code, name, page_code)
values ('goods_add', '添加产品资料', 'goods');
insert into SYS_FUNCTIONS (code, name, page_code)
values ('goods_edit', '修改产品资料', 'goods');
insert into SYS_FUNCTIONS (code, name, page_code)
values ('goods_import', '导入产品资料', 'goods');
insert into SYS_FUNCTIONS (code, name, page_code)
values ('goods_note_import', '商品描述导入', 'goods');
insert into SYS_FUNCTIONS (code, name, page_code)
values ('store_add', '添加仓库', 'store');
insert into SYS_FUNCTIONS (code, name, page_code)
values ('store_edit', '修改仓库', 'store');
insert into SYS_FUNCTIONS (code, name, page_code)
values ('store_shelf_add', '添加仓位', 'store');
insert into SYS_FUNCTIONS (code, name, page_code)
values ('store_shelf_edit', '修改仓位', 'store');
insert into SYS_FUNCTIONS (code, name, page_code)
values ('store_delete', '删除仓库', 'store');
insert into SYS_FUNCTIONS (code, name, page_code)
values ('store_shelf_delete', '删除仓位', 'store');
insert into SYS_FUNCTIONS (code, name, page_code)
values ('packing_material_add', '添加包装材料', 'packing_material');
insert into SYS_FUNCTIONS (code, name, page_code)
values ('packing_material_edit', '修改包装材料', 'packing_material');
insert into SYS_FUNCTIONS (code, name, page_code)
values ('packing_material_delete', '删除包装材料', 'packing_material');
insert into SYS_FUNCTIONS (code, name, page_code)
values ('io_order_0_add', '添加入库单', 'io_order_0');
insert into SYS_FUNCTIONS (code, name, page_code)
values ('io_order_0_delete', '删除入库单', 'io_order_0');
insert into SYS_FUNCTIONS (code, name, page_code)
values ('io_order_0_edit', '编辑入库单', 'io_order_0');
insert into SYS_FUNCTIONS (code, name, page_code)
values ('allocate_order_add', '添加调拨单', 'allocate_order');
insert into SYS_FUNCTIONS (code, name, page_code)
values ('purchase_order_in_audit', '采购待入库审核', 'purchase_order_in');
insert into SYS_FUNCTIONS (code, name, page_code)
values ('deparment_add', '添加部门', 'department');
insert into SYS_FUNCTIONS (code, name, page_code)
values ('deparment_edit', '修改部门', 'department');
insert into SYS_FUNCTIONS (code, name, page_code)
values ('deparment_delete', '删除部门', 'department');
insert into SYS_FUNCTIONS (code, name, page_code)
values ('user_add', '添加职员', 'user');
insert into SYS_FUNCTIONS (code, name, page_code)
values ('user_edit', '修改职员', 'user');
insert into SYS_FUNCTIONS (code, name, page_code)
values ('order-package_markship', '包裹标发', 'order-package');
insert into SYS_FUNCTIONS (code, name, page_code)
values ('role_add', '添加角色', 'role');
insert into SYS_FUNCTIONS (code, name, page_code)
values ('role_edit', '修改角色', 'role');
insert into SYS_FUNCTIONS (code, name, page_code)
values ('role_delete', '删除角色', 'role');
insert into SYS_FUNCTIONS (code, name, page_code)
values ('role_power', '设置角色权限', 'role');
insert into SYS_FUNCTIONS (code, name, page_code)
values ('order-package_editship', '修改发货方式', 'order-package');
insert into SYS_FUNCTIONS (code, name, page_code)
values ('order-package_marktrack', '设置跟踪号', 'order-package');
insert into SYS_FUNCTIONS (code, name, page_code)
values ('order-package_matchingship', '匹配物流方式', 'order-package');
insert into SYS_FUNCTIONS (code, name, page_code)
values ('purchaserequest_order_add', '添加请购单', 'purchaserequest_order');
insert into SYS_FUNCTIONS (code, name, page_code)
values ('user_set_power', '修改登录信息', 'user');
insert into SYS_FUNCTIONS (code, name, page_code)
values ('shipping_add', '添加发货方式', 'shipping');
insert into SYS_FUNCTIONS (code, name, page_code)
values ('shipping_edit', '修改发货方式', 'shipping');
insert into SYS_FUNCTIONS (code, name, page_code)
values ('shipping_delete', '删除发货方式', 'shipping');
insert into SYS_FUNCTIONS (code, name, page_code)
values ('countries_add', '添加国家地区', 'countries');
insert into SYS_FUNCTIONS (code, name, page_code)
values ('countries_edit', '修改国家地区', 'countries');
insert into SYS_FUNCTIONS (code, name, page_code)
values ('countries_delete', '删除国家地区', 'countries');
insert into SYS_FUNCTIONS (code, name, page_code)
values ('currency_rate_add', '添加汇率', 'currency_rate');
insert into SYS_FUNCTIONS (code, name, page_code)
values ('currency_rate_edit', '修改汇率', 'currency_rate');
insert into SYS_FUNCTIONS (code, name, page_code)
values ('currency_rate_delete', '删除汇率', 'currency_rate');
insert into SYS_FUNCTIONS (code, name, page_code)
values ('paypal_account_add', '添加paypal账号', 'paypal_account');
insert into SYS_FUNCTIONS (code, name, page_code)
values ('paypal_account_edit', '修改paypal账号', 'paypal_account');
insert into SYS_FUNCTIONS (code, name, page_code)
values ('paypal_account_delete', '删除paypal账号', 'paypal_account');
insert into SYS_FUNCTIONS (code, name, page_code)
values ('purchase_order_add', '添加采购单', 'purchase_order');
insert into SYS_FUNCTIONS (code, name, page_code)
values ('purchase_order_audit', '审核采购单', 'purchase_order');
insert into SYS_FUNCTIONS (code, name, page_code)
values ('purchaserequest_order_edit', '修改请购单', 'purchaserequest_order');
insert into SYS_FUNCTIONS (code, name, page_code)
values ('purchaserequest_order_delete', '删除请购单', 'purchaserequest_order');
insert into SYS_FUNCTIONS (code, name, page_code)
values ('purchaserequest_order_purchase', '请购转采购', 'purchaserequest_order');
commit;
prompt 56 records loaded
prompt Loading SYS_MODULES...
insert into SYS_MODULES (code, name, url, sort)
values ('system', '系统管理', null, 4);
insert into SYS_MODULES (code, name, url, sort)
values ('product', '库存管理', null, 2);
insert into SYS_MODULES (code, name, url, sort)
values ('purchase', '采购管理', null, 3);
insert into SYS_MODULES (code, name, url, sort)
values ('order', '订单管理', null, 1);
commit;
prompt 4 records loaded
prompt Loading SYS_PAGES...
insert into SYS_PAGES (code, name, url, module_code, sort)
values ('login_users', '卖家账户管理', '/user/login-users', 'system', 0);
insert into SYS_PAGES (code, name, url, module_code, sort)
values ('department', '部门管理', '/department', 'system', 0);
insert into SYS_PAGES (code, name, url, module_code, sort)
values ('user', '职员管理', '/user', 'system', 0);
insert into SYS_PAGES (code, name, url, module_code, sort)
values ('role', '角色管理', '/role', 'system', 0);
insert into SYS_PAGES (code, name, url, module_code, sort)
values ('seller', '卖家管理', '/seller', 'system', 0);
insert into SYS_PAGES (code, name, url, module_code, sort)
values ('seller-order', '卖家订单', '/order/seller-order', 'order', 0);
insert into SYS_PAGES (code, name, url, module_code, sort)
values ('seller_goods', '卖家产品管理', '/seller-goods', 'product', 0);
insert into SYS_PAGES (code, name, url, module_code, sort)
values ('order-list', '订单列表', '/order/list', 'order', 0);
insert into SYS_PAGES (code, name, url, module_code, sort)
values ('package_printlist', '打印工作台', '/order-package/printList', 'order', 3);
insert into SYS_PAGES (code, name, url, module_code, sort)
values ('report-page', '采购报表查询', '/purchaseorder/report-page', 'purchase', 0);
insert into SYS_PAGES (code, name, url, module_code, sort)
values ('order-package', '订单包裹', '/order-package', 'order', 0);
insert into SYS_PAGES (code, name, url, module_code, sort)
values ('package-scanning', '扫描工作台', '/order-package/scanning', 'order', 4);
insert into SYS_PAGES (code, name, url, module_code, sort)
values ('package-shipping', '物流公司交接', '/order-package/shipping', 'order', 5);
insert into SYS_PAGES (code, name, url, module_code, sort)
values ('purchase_warn', '库存预警', '/purchase-warn/list', 'purchase', 0);
insert into SYS_PAGES (code, name, url, module_code, sort)
values ('ebay_account', 'eBay账号', '/account/ebay/list', 'system', 0);
insert into SYS_PAGES (code, name, url, module_code, sort)
values ('amazon_account', 'amazon账号', '/account/am/list', 'system', 0);
insert into SYS_PAGES (code, name, url, module_code, sort)
values ('smt_account', '速卖通账号', '/account/smt/list', 'system', 0);
insert into SYS_PAGES (code, name, url, module_code, sort)
values ('paypal_account', 'Paypal账号', '/paypalAccount', 'system', 0);
insert into SYS_PAGES (code, name, url, module_code, sort)
values ('currency_rate', '汇率信息', '/currencyRates', 'system', 0);
insert into SYS_PAGES (code, name, url, module_code, sort)
values ('countries', '国家地区', '/country', 'system', 0);
insert into SYS_PAGES (code, name, url, module_code, sort)
values ('shipping', '发货方式', '/shipping', 'system', 0);
insert into SYS_PAGES (code, name, url, module_code, sort)
values ('dict_code', '代码字典维护', '/dict', 'system', 0);
insert into SYS_PAGES (code, name, url, module_code, sort)
values ('goods', '产品资料', '/goods', 'product', 0);
insert into SYS_PAGES (code, name, url, module_code, sort)
values ('store', '仓库货位', '/store', 'product', 0);
insert into SYS_PAGES (code, name, url, module_code, sort)
values ('goods_category', '产品类目维护', '/goodscategory/firstcgy-list', 'product', 0);
insert into SYS_PAGES (code, name, url, module_code, sort)
values ('packing_material', '包装材料', '/packingMaterial', 'product', 0);
insert into SYS_PAGES (code, name, url, module_code, sort)
values ('io_order_0', '入库单', '/io-order?type=0', 'product', 0);
insert into SYS_PAGES (code, name, url, module_code, sort)
values ('io_order_1', '出库单', '/io-order?type=1', 'product', 0);
insert into SYS_PAGES (code, name, url, module_code, sort)
values ('allocate_order', '调拨单', '/allocateOrder/list', 'product', 0);
insert into SYS_PAGES (code, name, url, module_code, sort)
values ('io-listing', '商品收发明细', '/io-order/io-listing', 'product', 0);
insert into SYS_PAGES (code, name, url, module_code, sort)
values ('inventory_stat', '库存状态查询', '/inventory/stat', 'product', 0);
insert into SYS_PAGES (code, name, url, module_code, sort)
values ('inventory', '即时库存查询', '/inventory', 'product', 0);
insert into SYS_PAGES (code, name, url, module_code, sort)
values ('supplier', '供应商管理', '/supplier', 'purchase', 0);
insert into SYS_PAGES (code, name, url, module_code, sort)
values ('supplier_price', '采购报价管理', '/supplier-price', 'purchase', 0);
insert into SYS_PAGES (code, name, url, module_code, sort)
values ('purchaserequest_order', '请购单', '/purchaserequest-order/list', 'purchase', 0);
insert into SYS_PAGES (code, name, url, module_code, sort)
values ('purchase_order', '采购单', '/purchaseorder/list', 'purchase', 0);
insert into SYS_PAGES (code, name, url, module_code, sort)
values ('purchase_order_in', '采购待入库', '/purchaseorder/to-stockin', 'product', 0);
insert into SYS_PAGES (code, name, url, module_code, sort)
values ('purchase_order_in_list', '采购入库单查询', '/stockin/list', 'product', 0);
insert into SYS_PAGES (code, name, url, module_code, sort)
values ('supplier_score', '供应商评分', '/supplier-score/list', 'purchase', 0);
commit;
prompt 39 records loaded
set feedback on
set define on
prompt Done.
