-----------------------------------------------------
-- Export file for user XFY                        --
-- Created by Administrator on 2015/3/28, 16:34:45 --
-----------------------------------------------------

set define off
spool xfy_ddl_table.log

prompt
prompt Creating table ACCOUNTS
prompt =======================
prompt
create table XFY.ACCOUNTS
(
  id                   NUMBER(9) not null,
  account_name         VARCHAR2(50) not null,
  account_type         VARCHAR2(10) not null,
  seller_id            NUMBER(16) not null,
  ebay_token           VARCHAR2(1000),
  ebay_expiretime      DATE,
  am_access_key        VARCHAR2(255),
  am_secret_access_key VARCHAR2(255),
  am_merchant_id       VARCHAR2(255),
  am_marketplace_id    VARCHAR2(255),
  am_service_url       VARCHAR2(255),
  smt_refresh_token    VARCHAR2(100),
  smt_access_token     VARCHAR2(100),
  smt_appkey           VARCHAR2(100),
  smt_secret           VARCHAR2(100),
  status               NUMBER(1) default 1 not null,
  created_time         DATE default sysdate not null,
  last_updated_time    DATE not null,
  smt_expiretime       DATE,
  mail                 VARCHAR2(50),
  abbreviation         VARCHAR2(50)
)
;
comment on table XFY.ACCOUNTS
  is '平台帐户表';
comment on column XFY.ACCOUNTS.id
  is 'ID';
comment on column XFY.ACCOUNTS.account_name
  is '账号名';
comment on column XFY.ACCOUNTS.account_type
  is '账号类型（可选值：smt,ebay,amazon）';
comment on column XFY.ACCOUNTS.seller_id
  is '卖家ID';
comment on column XFY.ACCOUNTS.ebay_token
  is 'ebay账号 Token';
comment on column XFY.ACCOUNTS.ebay_expiretime
  is 'ebay账号 Token 过期时间';
comment on column XFY.ACCOUNTS.am_access_key
  is 'amazon 相关API配置';
comment on column XFY.ACCOUNTS.am_secret_access_key
  is 'amazon 相关API配置';
comment on column XFY.ACCOUNTS.am_merchant_id
  is 'amazon 相关API配置';
comment on column XFY.ACCOUNTS.am_marketplace_id
  is 'amazon 相关API配置';
comment on column XFY.ACCOUNTS.am_service_url
  is 'amazon 相关API配置';
comment on column XFY.ACCOUNTS.smt_refresh_token
  is 'smt 相关API配置';
comment on column XFY.ACCOUNTS.smt_access_token
  is 'smt 相关API配置';
comment on column XFY.ACCOUNTS.smt_appkey
  is 'smt 相关API配置';
comment on column XFY.ACCOUNTS.smt_secret
  is 'smt 相关API配置';
comment on column XFY.ACCOUNTS.status
  is '删除标识(0-未启用,1-正常,2-已删除)';
comment on column XFY.ACCOUNTS.created_time
  is '记录添加时间';
comment on column XFY.ACCOUNTS.last_updated_time
  is '记录最后更新时间';
comment on column XFY.ACCOUNTS.smt_expiretime
  is 'smt 相关API配置 过期时间';
comment on column XFY.ACCOUNTS.mail
  is 'Mail';
comment on column XFY.ACCOUNTS.abbreviation
  is '简称';
alter table XFY.ACCOUNTS
  add constraint PK_ACCOUNTS primary key (ID);

prompt
prompt Creating table COUNTRIES
prompt ========================
prompt
create table XFY.COUNTRIES
(
  id                NUMBER(9) not null,
  en_name           VARCHAR2(100) not null,
  cn_name           VARCHAR2(100) not null,
  short_name        VARCHAR2(20) not null,
  created_time      DATE default sysdate not null,
  last_updated_time DATE not null
)
;
comment on table XFY.COUNTRIES
  is '国家地区表';
comment on column XFY.COUNTRIES.en_name
  is '英文名';
comment on column XFY.COUNTRIES.cn_name
  is '中文名';
comment on column XFY.COUNTRIES.short_name
  is '缩写';
comment on column XFY.COUNTRIES.created_time
  is '记录添加时间';
comment on column XFY.COUNTRIES.last_updated_time
  is '记录最后更新时间';
alter table XFY.COUNTRIES
  add constraint PK_COUNTRIES primary key (ID);

prompt
prompt Creating table CURRENCY_RATES
prompt =============================
prompt
create table XFY.CURRENCY_RATES
(
  id                NUMBER(9) not null,
  currency          VARCHAR2(100),
  exchange_rate     NUMBER(9,4),
  created_time      DATE default sysdate,
  last_updated_time DATE
)
;
comment on table XFY.CURRENCY_RATES
  is '汇率信息';
comment on column XFY.CURRENCY_RATES.currency
  is '货币';
comment on column XFY.CURRENCY_RATES.exchange_rate
  is '兑换率';
comment on column XFY.CURRENCY_RATES.created_time
  is '记录添加时间';
comment on column XFY.CURRENCY_RATES.last_updated_time
  is '记录最后更新时间';
alter table XFY.CURRENCY_RATES
  add constraint PK_CURRENCY_RATES primary key (ID);

prompt
prompt Creating table DEPARTMENTS
prompt ==========================
prompt
create table XFY.DEPARTMENTS
(
  id                NUMBER(9) not null,
  name              VARCHAR2(100) not null,
  parent_id         NUMBER(9),
  leader_id         NUMBER(9),
  note              VARCHAR2(1000),
  created_time      DATE default sysdate not null,
  last_updated_time DATE not null
)
;
comment on table XFY.DEPARTMENTS
  is '部门表';
comment on column XFY.DEPARTMENTS.id
  is 'ID';
comment on column XFY.DEPARTMENTS.name
  is '部门名称';
comment on column XFY.DEPARTMENTS.parent_id
  is '上级部门';
comment on column XFY.DEPARTMENTS.leader_id
  is '部门领导(对应用户ID)';
comment on column XFY.DEPARTMENTS.note
  is '备注';
comment on column XFY.DEPARTMENTS.created_time
  is '记录添加时间';
comment on column XFY.DEPARTMENTS.last_updated_time
  is '记录最后更新时间';
alter table XFY.DEPARTMENTS
  add constraint PK_DEPARTMENTS primary key (ID);

prompt
prompt Creating table DICT
prompt ===================
prompt
create table XFY.DICT
(
  type NUMBER(9) not null,
  code NUMBER(9) not null,
  name VARCHAR2(300)
)
;
comment on table XFY.DICT
  is '代码字典表';
comment on column XFY.DICT.type
  is '字典类型(0-留作 字典类型)';
comment on column XFY.DICT.code
  is '代码';
comment on column XFY.DICT.name
  is '名称';
alter table XFY.DICT
  add constraint PK_DICT primary key (TYPE, CODE);

prompt
prompt Creating table EUB_ADDRESS
prompt ==========================
prompt
create table XFY.EUB_ADDRESS
(
  id           NUMBER(20) not null,
  address_type NUMBER(1) not null,
  eub_id       NUMBER(9) not null,
  name         VARCHAR2(50),
  company      VARCHAR2(50),
  country      VARCHAR2(10),
  province     VARCHAR2(10),
  city         VARCHAR2(10),
  district     VARCHAR2(10),
  street       VARCHAR2(255),
  zip_code     VARCHAR2(10),
  mobile       VARCHAR2(20),
  email        VARCHAR2(50),
  telphone     VARCHAR2(20)
)
;
comment on column XFY.EUB_ADDRESS.address_type
  is 'eub地址类型  0:默认揽件  1:英文退货 2:中文退货';
comment on column XFY.EUB_ADDRESS.eub_id
  is '外键EUB_CONFIG Id';
comment on column XFY.EUB_ADDRESS.name
  is '联系人';
comment on column XFY.EUB_ADDRESS.company
  is '公司名称';
comment on column XFY.EUB_ADDRESS.country
  is '国家';
comment on column XFY.EUB_ADDRESS.province
  is '省';
comment on column XFY.EUB_ADDRESS.city
  is '市';
comment on column XFY.EUB_ADDRESS.district
  is '区';
comment on column XFY.EUB_ADDRESS.street
  is '街道地址';
comment on column XFY.EUB_ADDRESS.zip_code
  is '邮政编码';
comment on column XFY.EUB_ADDRESS.mobile
  is '手机号码 ';
comment on column XFY.EUB_ADDRESS.email
  is '电子邮件';
comment on column XFY.EUB_ADDRESS.telphone
  is '固定电话';
alter table XFY.EUB_ADDRESS
  add constraint PK_EUBAID primary key (ID);

prompt
prompt Creating table EUB_CONFIG
prompt =========================
prompt
create table XFY.EUB_CONFIG
(
  id                NUMBER(9) not null,
  account_id        NUMBER(9) not null,
  account_name      VARCHAR2(50) not null,
  eub_type          NUMBER(1) not null,
  app_id            VARCHAR2(100),
  app_secret        VARCHAR2(100),
  auth_token        VARCHAR2(1000),
  shipment          NUMBER(1) not null,
  created_time      DATE default sysdate not null,
  last_updated_time DATE not null
)
;
comment on column XFY.EUB_CONFIG.account_id
  is '外键Accounts表Id';
comment on column XFY.EUB_CONFIG.eub_type
  is 'eub类型(可选值：0 线下eub,1 线上eub)';
comment on column XFY.EUB_CONFIG.shipment
  is '交运方式;(可选值：0 上门揽件,1:卖家自送)';
alter table XFY.EUB_CONFIG
  add constraint PK_EUBID primary key (ID);
alter table XFY.EUB_CONFIG
  add constraint FK_ACCOUNTID foreign key (ACCOUNT_ID)
  references XFY.ACCOUNTS (ID);

prompt
prompt Creating table GOODS
prompt ====================
prompt
create table XFY.GOODS
(
  id                  NUMBER(9) not null,
  goods_sku           VARCHAR2(50) not null,
  name                VARCHAR2(300) not null,
  en_name             VARCHAR2(300),
  price               NUMBER(9,2),
  cost                NUMBER(9,2),
  unit                VARCHAR2(300),
  weight              NUMBER(9,4),
  store_id            NUMBER(9),
  store_shelf_id      NUMBER(9),
  length              NUMBER(9,4),
  width               NUMBER(9,4),
  height              NUMBER(9,4),
  category_id         NUMBER(9),
  declaration_name_en VARCHAR2(300),
  customs_code        VARCHAR2(300),
  declaration_name_cn VARCHAR2(300),
  declaration_cost    NUMBER(9,2),
  color               VARCHAR2(300),
  goods_size          VARCHAR2(300),
  materil             VARCHAR2(300),
  brand               VARCHAR2(300),
  supplier_id         NUMBER(9),
  supplier2_id        NUMBER(9),
  supplier3_id        NUMBER(9),
  status              NUMBER(2),
  packing_material_id NUMBER(9),
  packing_capacity    VARCHAR2(300),
  ispacking           NUMBER(1),
  packing_fee         NUMBER(9,4),
  barcode             VARCHAR2(100),
  test_type           NUMBER(1),
  base_code           VARCHAR2(50),
  develop_time        DATE,
  develop_user        NUMBER(9),
  sale_user           NUMBER(9),
  buy_user            NUMBER(9),
  pack_user           NUMBER(9),
  pick_user           NUMBER(9),
  assemble_user       NUMBER(9),
  is_battery          NUMBER(1),
  is_copyright        NUMBER(2),
  is_shipping         NUMBER(3),
  is_liquid           NUMBER(4),
  is_regulated        NUMBER(5),
  note                VARCHAR2(4000),
  note1               VARCHAR2(4000),
  note2               VARCHAR2(4000),
  note3               VARCHAR2(4000),
  note4               VARCHAR2(4000),
  note5               VARCHAR2(4000),
  created_time        DATE default SYSDATE not null,
  last_updated_time   DATE default SYSDATE not null,
  img_url             VARCHAR2(200),
  base_category_id    NUMBER(9),
  mid_category_id     NUMBER(9),
  old_sku             VARCHAR2(50),
  is_magnetic         NUMBER(2),
  rules               VARCHAR2(20),
  model               VARCHAR2(20),
  reference_url       VARCHAR2(500)
)
;
comment on table XFY.GOODS
  is '商品信息表';
comment on column XFY.GOODS.id
  is 'ID(SEQ_GOODS)';
comment on column XFY.GOODS.goods_sku
  is 'SKU编码';
comment on column XFY.GOODS.name
  is '名称';
comment on column XFY.GOODS.en_name
  is '英文名称';
comment on column XFY.GOODS.price
  is '建议价格';
comment on column XFY.GOODS.cost
  is '采购价格';
comment on column XFY.GOODS.unit
  is '单位';
comment on column XFY.GOODS.weight
  is '重量';
comment on column XFY.GOODS.store_id
  is '仓库';
comment on column XFY.GOODS.store_shelf_id
  is '货位号';
comment on column XFY.GOODS.length
  is '长度';
comment on column XFY.GOODS.width
  is '宽度';
comment on column XFY.GOODS.height
  is '高度';
comment on column XFY.GOODS.category_id
  is '类目';
comment on column XFY.GOODS.declaration_name_en
  is '英文申报名称';
comment on column XFY.GOODS.customs_code
  is '海关编码';
comment on column XFY.GOODS.declaration_name_cn
  is '中文申报名称';
comment on column XFY.GOODS.declaration_cost
  is '申报价值USD';
comment on column XFY.GOODS.color
  is '颜色';
comment on column XFY.GOODS.goods_size
  is '尺码';
comment on column XFY.GOODS.materil
  is '材质';
comment on column XFY.GOODS.brand
  is '品牌';
comment on column XFY.GOODS.supplier_id
  is '供应商1';
comment on column XFY.GOODS.supplier2_id
  is '供应商2';
comment on column XFY.GOODS.supplier3_id
  is '供应商3';
comment on column XFY.GOODS.status
  is '物品状态（参见字典配置）';
comment on column XFY.GOODS.packing_material_id
  is '产品包装型号';
comment on column XFY.GOODS.packing_capacity
  is '产品包装容量';
comment on column XFY.GOODS.ispacking
  is '产品是否带包装(0-否，1-是）';
comment on column XFY.GOODS.packing_fee
  is '包装费';
comment on column XFY.GOODS.barcode
  is '条码';
comment on column XFY.GOODS.test_type
  is '检验方式(0-全检、1-抽检)';
comment on column XFY.GOODS.base_code
  is '父类编码（SKU编码的前7位）';
comment on column XFY.GOODS.develop_time
  is '开发时间';
comment on column XFY.GOODS.develop_user
  is '开发人员';
comment on column XFY.GOODS.sale_user
  is '销售人员';
comment on column XFY.GOODS.buy_user
  is '采购人员';
comment on column XFY.GOODS.pack_user
  is '包装人员';
comment on column XFY.GOODS.pick_user
  is '捡货人员';
comment on column XFY.GOODS.assemble_user
  is '配货人员';
comment on column XFY.GOODS.is_battery
  is '是否含电池(0-否,1-是)';
comment on column XFY.GOODS.is_copyright
  is '是否侵权(0-否,1-是)';
comment on column XFY.GOODS.is_shipping
  is '是否指定运输(0-否,1-是)';
comment on column XFY.GOODS.is_liquid
  is '是否液体(0-否,1-是)';
comment on column XFY.GOODS.is_regulated
  is '是否管制品(0-否,1-是)';
comment on column XFY.GOODS.note
  is '备注';
comment on column XFY.GOODS.note1
  is '资料备注1';
comment on column XFY.GOODS.note2
  is '资料备注2';
comment on column XFY.GOODS.note3
  is '资料备注3';
comment on column XFY.GOODS.note4
  is '资料备注4';
comment on column XFY.GOODS.note5
  is '资料备注5';
comment on column XFY.GOODS.created_time
  is '记录添加时间';
comment on column XFY.GOODS.last_updated_time
  is '记录最后更新时间';
comment on column XFY.GOODS.img_url
  is '图片路径';
comment on column XFY.GOODS.base_category_id
  is '类目-大类';
comment on column XFY.GOODS.mid_category_id
  is '类目-中类';
comment on column XFY.GOODS.old_sku
  is '老SKU';
comment on column XFY.GOODS.is_magnetic
  is '是否带磁性';
comment on column XFY.GOODS.rules
  is '规格';
comment on column XFY.GOODS.model
  is '型号';
comment on column XFY.GOODS.reference_url
  is '参考链接';
alter table XFY.GOODS
  add constraint PK_GOODS primary key (ID);
alter table XFY.GOODS
  add constraint UNI_GOODS_GOODSSKU unique (GOODS_SKU);

prompt
prompt Creating table GOODS_CATEGORY
prompt =============================
prompt
create table XFY.GOODS_CATEGORY
(
  id                NUMBER(9) not null,
  name              VARCHAR2(200) not null,
  parent_id         NUMBER(9) not null,
  note              VARCHAR2(2000),
  created_time      DATE not null,
  last_updated_time DATE not null,
  code              VARCHAR2(50) not null
)
;
comment on table XFY.GOODS_CATEGORY
  is '商品类目表';
comment on column XFY.GOODS_CATEGORY.id
  is 'ID';
comment on column XFY.GOODS_CATEGORY.name
  is '分类名';
comment on column XFY.GOODS_CATEGORY.parent_id
  is '父分类ID';
comment on column XFY.GOODS_CATEGORY.note
  is '备注';
comment on column XFY.GOODS_CATEGORY.created_time
  is '记录添加时间';
comment on column XFY.GOODS_CATEGORY.last_updated_time
  is '最后修改时间';
comment on column XFY.GOODS_CATEGORY.code
  is '分类编码';
alter table XFY.GOODS_CATEGORY
  add constraint PK_GOODS_CATEGORY primary key (ID);
alter table XFY.GOODS_CATEGORY
  add constraint UNI_GOODS_CATEGORY_CODE unique (CODE);

prompt
prompt Creating table GOODS_COMBINATION
prompt ================================
prompt
create table XFY.GOODS_COMBINATION
(
  id                NUMBER(9) not null,
  goods_sn          VARCHAR2(50) not null,
  original_goods_sn VARCHAR2(1000) not null,
  note              VARCHAR2(2000),
  seller_id         NUMBER(9) not null,
  created_time      DATE default SYSDATE not null,
  last_updated_time DATE not null
)
;
comment on table XFY.GOODS_COMBINATION
  is '组合商品表';
comment on column XFY.GOODS_COMBINATION.id
  is 'ID';
comment on column XFY.GOODS_COMBINATION.goods_sn
  is '组合商品编码';
comment on column XFY.GOODS_COMBINATION.original_goods_sn
  is '原商品编码(使用","分隔)';
comment on column XFY.GOODS_COMBINATION.note
  is '备注';
comment on column XFY.GOODS_COMBINATION.seller_id
  is '卖家标识(对应卖家表)';
comment on column XFY.GOODS_COMBINATION.created_time
  is '记录添加时间';
comment on column XFY.GOODS_COMBINATION.last_updated_time
  is '记录最后更新时间';

prompt
prompt Creating table GOODS_DESC
prompt =========================
prompt
create table XFY.GOODS_DESC
(
  id                NUMBER(9) not null,
  goods_sku         VARCHAR2(50) not null,
  language          VARCHAR2(5) not null,
  description       VARCHAR2(4000) not null,
  version           NUMBER(9) not null,
  last_updated_time DATE not null
)
;
comment on table XFY.GOODS_DESC
  is '商品描述信息';
comment on column XFY.GOODS_DESC.id
  is 'ID(SEQ_GOODS_DESC)';
comment on column XFY.GOODS_DESC.goods_sku
  is 'SKU';
comment on column XFY.GOODS_DESC.language
  is '语言(en,de,fr,ru,it)';
comment on column XFY.GOODS_DESC.description
  is '描述';
comment on column XFY.GOODS_DESC.version
  is '版本号';
comment on column XFY.GOODS_DESC.last_updated_time
  is '最后更新时间';
alter table XFY.GOODS_DESC
  add constraint PK_GOODS_DESC primary key (ID);

prompt
prompt Creating table GOODS_INVENTORY
prompt ==============================
prompt
create table XFY.GOODS_INVENTORY
(
  id                NUMBER(9) not null,
  goods_sku         VARCHAR2(50) not null,
  store_id          NUMBER(9) not null,
  store_shelf_id    NUMBER(9) not null,
  count             NUMBER(9) not null,
  last_updated_time DATE default sysdate not null
)
;
comment on table XFY.GOODS_INVENTORY
  is '商品库存表';
comment on column XFY.GOODS_INVENTORY.id
  is 'ID';
comment on column XFY.GOODS_INVENTORY.goods_sku
  is '商品SKU';
comment on column XFY.GOODS_INVENTORY.store_id
  is '仓库';
comment on column XFY.GOODS_INVENTORY.store_shelf_id
  is '仓库货位';
comment on column XFY.GOODS_INVENTORY.count
  is '实际库存';
comment on column XFY.GOODS_INVENTORY.last_updated_time
  is '记录最后更新时间';
alter table XFY.GOODS_INVENTORY
  add constraint PK_GOODS_INVENTORY primary key (ID);
alter table XFY.GOODS_INVENTORY
  add constraint IDX_GI_STORE_SHELF unique (GOODS_SKU, STORE_ID, STORE_SHELF_ID);

prompt
prompt Creating table GOODS_INVENTORY_COST
prompt ===================================
prompt
create table XFY.GOODS_INVENTORY_COST
(
  goods_sku         VARCHAR2(50) not null,
  amount            NUMBER(13,2) not null,
  price             NUMBER(13,2) not null,
  count             NUMBER(9) not null,
  last_updated_time DATE not null
)
;
comment on table XFY.GOODS_INVENTORY_COST
  is '商品库存成本';
comment on column XFY.GOODS_INVENTORY_COST.goods_sku
  is 'SKU';
comment on column XFY.GOODS_INVENTORY_COST.amount
  is '总金额';
comment on column XFY.GOODS_INVENTORY_COST.price
  is '平均价格';
comment on column XFY.GOODS_INVENTORY_COST.count
  is '库存数量';
comment on column XFY.GOODS_INVENTORY_COST.last_updated_time
  is '最后更新时间';
alter table XFY.GOODS_INVENTORY_COST
  add constraint PK_GOODS_INVENTORY_COST primary key (GOODS_SKU);

prompt
prompt Creating table GOODS_INVENTORY_LOCK
prompt ===================================
prompt
create table XFY.GOODS_INVENTORY_LOCK
(
  id                NUMBER(9) not null,
  goods_sku         VARCHAR2(50) not null,
  lock_count        NUMBER(9) not null,
  sale_order_id     NUMBER(9) not null,
  last_updated_time DATE default sysdate not null
)
;
comment on table XFY.GOODS_INVENTORY_LOCK
  is '商品库存锁定详细表';
comment on column XFY.GOODS_INVENTORY_LOCK.id
  is 'ID(SEQ_GOODS_INVENTORY_LOCK)';
comment on column XFY.GOODS_INVENTORY_LOCK.goods_sku
  is '商品SKU';
comment on column XFY.GOODS_INVENTORY_LOCK.lock_count
  is '锁定数量';
comment on column XFY.GOODS_INVENTORY_LOCK.sale_order_id
  is '销售订单ID（orders表）';
comment on column XFY.GOODS_INVENTORY_LOCK.last_updated_time
  is '记录最后更新时间（锁定时间）';
alter table XFY.GOODS_INVENTORY_LOCK
  add constraint PK_GOODS_INVENTORY_LOCK primary key (ID);
alter table XFY.GOODS_INVENTORY_LOCK
  add constraint UNI_GIL_SKU_ORDER unique (SALE_ORDER_ID, GOODS_SKU);

prompt
prompt Creating table GOODS_PACKING_MATERIAL
prompt =====================================
prompt
create table XFY.GOODS_PACKING_MATERIAL
(
  id                NUMBER(9) not null,
  model             VARCHAR2(200) not null,
  rules             VARCHAR2(200) not null,
  note              VARCHAR2(500) not null,
  weight            NUMBER(9,4) not null,
  price             NUMBER(9,4) not null,
  created_time      DATE default sysdate not null,
  last_updated_time DATE not null
)
;
comment on table XFY.GOODS_PACKING_MATERIAL
  is '包装材料表';
comment on column XFY.GOODS_PACKING_MATERIAL.id
  is 'ID';
comment on column XFY.GOODS_PACKING_MATERIAL.model
  is '型号';
comment on column XFY.GOODS_PACKING_MATERIAL.rules
  is '包装材料';
comment on column XFY.GOODS_PACKING_MATERIAL.note
  is '备注';
comment on column XFY.GOODS_PACKING_MATERIAL.weight
  is '重量';
comment on column XFY.GOODS_PACKING_MATERIAL.price
  is '价格';
comment on column XFY.GOODS_PACKING_MATERIAL.created_time
  is '记录添加时间';
comment on column XFY.GOODS_PACKING_MATERIAL.last_updated_time
  is '记录最后更新时间';
alter table XFY.GOODS_PACKING_MATERIAL
  add constraint PK_GOODS_PACKING_MATERIAL primary key (ID);
alter table XFY.GOODS_PACKING_MATERIAL
  add constraint UNI_GPM_MODEL unique (MODEL);

prompt
prompt Creating table GOODS_PLATFORM_CATEGORY
prompt ======================================
prompt
create table XFY.GOODS_PLATFORM_CATEGORY
(
  goods_sku         VARCHAR2(50) not null,
  ebay_cat_id       VARCHAR2(50),
  smt_cat_id        VARCHAR2(50),
  am_cat_id         VARCHAR2(50),
  last_updated_time DATE not null
)
;
comment on table XFY.GOODS_PLATFORM_CATEGORY
  is '商品运营分类';
comment on column XFY.GOODS_PLATFORM_CATEGORY.goods_sku
  is 'SKU';
comment on column XFY.GOODS_PLATFORM_CATEGORY.ebay_cat_id
  is 'eBay分类ID';
comment on column XFY.GOODS_PLATFORM_CATEGORY.smt_cat_id
  is 'SMT分类ID';
comment on column XFY.GOODS_PLATFORM_CATEGORY.am_cat_id
  is 'AM分类ID';
comment on column XFY.GOODS_PLATFORM_CATEGORY.last_updated_time
  is '最后更新时间';
alter table XFY.GOODS_PLATFORM_CATEGORY
  add constraint PK_GOODS_PLATFORM_CATEGORY primary key (GOODS_SKU);

prompt
prompt Creating table GOODS_STATUS_DESC
prompt ================================
prompt
create table XFY.GOODS_STATUS_DESC
(
  id                NUMBER(9) not null,
  name              VARCHAR2(100) not null,
  created_time      DATE not null,
  last_updated_time DATE not null
)
;
comment on table XFY.GOODS_STATUS_DESC
  is '商品状态描述';
comment on column XFY.GOODS_STATUS_DESC.id
  is 'ID';
comment on column XFY.GOODS_STATUS_DESC.name
  is '仓库名称';
comment on column XFY.GOODS_STATUS_DESC.created_time
  is '添加时间';
comment on column XFY.GOODS_STATUS_DESC.last_updated_time
  is '最后修改时间';
alter table XFY.GOODS_STATUS_DESC
  add constraint PK_GOODS_STATUS_DESC primary key (ID);

prompt
prompt Creating table GOODS_SUPPLIER
prompt =============================
prompt
create table XFY.GOODS_SUPPLIER
(
  id                NUMBER(9) not null,
  company_name      VARCHAR2(300) not null,
  code              VARCHAR2(300),
  contact_name      VARCHAR2(300),
  contact_tel       VARCHAR2(300),
  contact_mobile    VARCHAR2(300),
  contact_fax       VARCHAR2(300),
  contact_mail      VARCHAR2(300),
  contact_address   VARCHAR2(300),
  city              VARCHAR2(300),
  url               VARCHAR2(300),
  bank_name         VARCHAR2(300),
  bank_account_name VARCHAR2(300),
  bank_account_code VARCHAR2(300),
  note              VARCHAR2(2000),
  status            NUMBER(1) default 0 not null,
  audit_user_id     NUMBER(9),
  audit_time        DATE,
  buyer_id          NUMBER(9),
  developer_id      NUMBER(9),
  type              NUMBER(1) default 1 not null,
  priority          CHAR(1) default 'D' not null,
  short_name        VARCHAR2(300),
  start_date        DATE,
  post_code         VARCHAR2(50),
  created_time      DATE default sysdate not null,
  last_updated_time DATE default sysdate not null
)
;
comment on table XFY.GOODS_SUPPLIER
  is '供应商表';
comment on column XFY.GOODS_SUPPLIER.id
  is 'ID';
comment on column XFY.GOODS_SUPPLIER.company_name
  is '公司名';
comment on column XFY.GOODS_SUPPLIER.code
  is '公司代码';
comment on column XFY.GOODS_SUPPLIER.contact_name
  is '联系人';
comment on column XFY.GOODS_SUPPLIER.contact_tel
  is '联系电话';
comment on column XFY.GOODS_SUPPLIER.contact_mobile
  is '联系手机';
comment on column XFY.GOODS_SUPPLIER.contact_fax
  is '联系传真';
comment on column XFY.GOODS_SUPPLIER.contact_mail
  is '联系邮箱';
comment on column XFY.GOODS_SUPPLIER.contact_address
  is '联系地址';
comment on column XFY.GOODS_SUPPLIER.city
  is '城市';
comment on column XFY.GOODS_SUPPLIER.url
  is '网址';
comment on column XFY.GOODS_SUPPLIER.bank_name
  is '银行开户行';
comment on column XFY.GOODS_SUPPLIER.bank_account_name
  is '银行开户名';
comment on column XFY.GOODS_SUPPLIER.bank_account_code
  is '银行帐号';
comment on column XFY.GOODS_SUPPLIER.note
  is '备注';
comment on column XFY.GOODS_SUPPLIER.status
  is '状态(0-未审核,1-已审核)';
comment on column XFY.GOODS_SUPPLIER.audit_user_id
  is '审核用户ID(关联EMPLOYEE表ID)';
comment on column XFY.GOODS_SUPPLIER.audit_time
  is '审核时间';
comment on column XFY.GOODS_SUPPLIER.buyer_id
  is '采购员ID(关联EMPLOYEE表ID)';
comment on column XFY.GOODS_SUPPLIER.developer_id
  is '开发员ID(关联EMPLOYEE表ID)';
comment on column XFY.GOODS_SUPPLIER.type
  is '类别（1厂商、2贸易商、3电商）';
comment on column XFY.GOODS_SUPPLIER.priority
  is '优先级(ABCD)';
comment on column XFY.GOODS_SUPPLIER.short_name
  is '供应商简称';
comment on column XFY.GOODS_SUPPLIER.start_date
  is '加入日期';
comment on column XFY.GOODS_SUPPLIER.post_code
  is '邮编';
comment on column XFY.GOODS_SUPPLIER.created_time
  is '记录添加时间';
comment on column XFY.GOODS_SUPPLIER.last_updated_time
  is '记录最后更新时间';
alter table XFY.GOODS_SUPPLIER
  add constraint PK_GOODS_SUPPLIER primary key (ID);

prompt
prompt Creating table GOODS_SUPPLIER_PRICE
prompt ===================================
prompt
create table XFY.GOODS_SUPPLIER_PRICE
(
  id                NUMBER(9) not null,
  supplier_id       NUMBER(9) not null,
  goods_sku         VARCHAR2(100) not null,
  goods_name        VARCHAR2(300),
  goods_unit        VARCHAR2(100),
  count_min         NUMBER(9),
  count_max         NUMBER(9),
  price             NUMBER(9,2) not null,
  start_date        DATE,
  end_date          DATE,
  buy_period        NUMBER(4) not null,
  priority          NUMBER(4) not null,
  note              VARCHAR2(2000),
  oper_user_id      NUMBER(9) not null,
  created_time      DATE default sysdate not null,
  last_updated_time DATE default sysdate not null
)
;
comment on table XFY.GOODS_SUPPLIER_PRICE
  is '供应商商品报价表';
comment on column XFY.GOODS_SUPPLIER_PRICE.id
  is 'ID';
comment on column XFY.GOODS_SUPPLIER_PRICE.supplier_id
  is '供应商ID(GOODS_SUPPLIER)';
comment on column XFY.GOODS_SUPPLIER_PRICE.goods_sku
  is 'SKU';
comment on column XFY.GOODS_SUPPLIER_PRICE.goods_name
  is '商品名';
comment on column XFY.GOODS_SUPPLIER_PRICE.goods_unit
  is '商品单位';
comment on column XFY.GOODS_SUPPLIER_PRICE.count_min
  is '采购量从';
comment on column XFY.GOODS_SUPPLIER_PRICE.count_max
  is '采购量到';
comment on column XFY.GOODS_SUPPLIER_PRICE.price
  is '报价';
comment on column XFY.GOODS_SUPPLIER_PRICE.start_date
  is '生效日期';
comment on column XFY.GOODS_SUPPLIER_PRICE.end_date
  is '失效日期';
comment on column XFY.GOODS_SUPPLIER_PRICE.buy_period
  is '采购周期';
comment on column XFY.GOODS_SUPPLIER_PRICE.priority
  is '优先级';
comment on column XFY.GOODS_SUPPLIER_PRICE.note
  is '备注';
comment on column XFY.GOODS_SUPPLIER_PRICE.oper_user_id
  is '维护人';
comment on column XFY.GOODS_SUPPLIER_PRICE.created_time
  is '添加时间';
comment on column XFY.GOODS_SUPPLIER_PRICE.last_updated_time
  is '记录最后更新时间';
alter table XFY.GOODS_SUPPLIER_PRICE
  add constraint PK_GOODS_SUPPLIER_PRICE primary key (ID);

prompt
prompt Creating table GOODS_SUPPLIER_SKU_PRICE
prompt =======================================
prompt
create table XFY.GOODS_SUPPLIER_SKU_PRICE
(
  id                NUMBER(9) not null,
  supplier_id       NUMBER(9) not null,
  sku               VARCHAR2(100) not null,
  goods_name        VARCHAR2(300),
  goods_cost        NUMBER(9,2),
  note              VARCHAR2(2000),
  add_user_id       NUMBER(9) not null,
  created_time      DATE default sysdate not null,
  last_updated_time DATE not null
)
;
comment on table XFY.GOODS_SUPPLIER_SKU_PRICE
  is '供应商SKU价格清单表';
comment on column XFY.GOODS_SUPPLIER_SKU_PRICE.id
  is 'ID';
comment on column XFY.GOODS_SUPPLIER_SKU_PRICE.supplier_id
  is '供应商ID(GOODS_SUPPLIER)';
comment on column XFY.GOODS_SUPPLIER_SKU_PRICE.sku
  is 'SKU';
comment on column XFY.GOODS_SUPPLIER_SKU_PRICE.goods_name
  is '商品名';
comment on column XFY.GOODS_SUPPLIER_SKU_PRICE.goods_cost
  is '商品成本';
comment on column XFY.GOODS_SUPPLIER_SKU_PRICE.note
  is '备注';
comment on column XFY.GOODS_SUPPLIER_SKU_PRICE.add_user_id
  is '添加时间';
comment on column XFY.GOODS_SUPPLIER_SKU_PRICE.created_time
  is '记录最后更新时间';
alter table XFY.GOODS_SUPPLIER_SKU_PRICE
  add constraint PK_GOODS_SUPPLIER_SKU_PRICE primary key (ID);

prompt
prompt Creating table GOODS_TRANSFER_ORDER
prompt ===================================
prompt
create table XFY.GOODS_TRANSFER_ORDER
(
  id                  NUMBER(9) not null,
  order_no            VARCHAR2(50) not null,
  goods_sku           VARCHAR2(50) not null,
  from_store_id       NUMBER(9) not null,
  to_store_id         NUMBER(9) not null,
  operator_id         NUMBER(9) not null,
  created_time        DATE default sysdate not null,
  goods_count         NUMBER(9) not null,
  note                VARCHAR2(100),
  transfer_date       DATE,
  from_store_shelf_id NUMBER(9) not null,
  to_shore_shelf_id   NUMBER(9) not null
)
;
comment on table XFY.GOODS_TRANSFER_ORDER
  is '商品调拨单';
comment on column XFY.GOODS_TRANSFER_ORDER.id
  is 'ID(SEQ_GOODS_TRANSFER_ORDER)';
comment on column XFY.GOODS_TRANSFER_ORDER.order_no
  is '调拨单号';
comment on column XFY.GOODS_TRANSFER_ORDER.goods_sku
  is 'SKU';
comment on column XFY.GOODS_TRANSFER_ORDER.from_store_id
  is '出仓';
comment on column XFY.GOODS_TRANSFER_ORDER.to_store_id
  is '入仓';
comment on column XFY.GOODS_TRANSFER_ORDER.operator_id
  is '操作人ID';
comment on column XFY.GOODS_TRANSFER_ORDER.created_time
  is '记录添加时间';
comment on column XFY.GOODS_TRANSFER_ORDER.goods_count
  is '数量';
comment on column XFY.GOODS_TRANSFER_ORDER.note
  is '备注';
comment on column XFY.GOODS_TRANSFER_ORDER.transfer_date
  is '调拨日期';
comment on column XFY.GOODS_TRANSFER_ORDER.from_store_shelf_id
  is '出仓货位';
comment on column XFY.GOODS_TRANSFER_ORDER.to_shore_shelf_id
  is '入仓货位';

prompt
prompt Creating table IO_BUY_ORDER_LOGS
prompt ================================
prompt
create table XFY.IO_BUY_ORDER_LOGS
(
  id            NUMBER(9) not null,
  order_sn      VARCHAR2(50) not null,
  created_time  DATE not null,
  content       VARCHAR2(3000) not null,
  oper_username VARCHAR2(50) not null,
  oper_user_id  NUMBER(9) not null
)
;
comment on table XFY.IO_BUY_ORDER_LOGS
  is '出入库单、采购单操作日志';
comment on column XFY.IO_BUY_ORDER_LOGS.id
  is 'ID';
comment on column XFY.IO_BUY_ORDER_LOGS.order_sn
  is '出入库单、采购单的号';
comment on column XFY.IO_BUY_ORDER_LOGS.created_time
  is '记录时间';
comment on column XFY.IO_BUY_ORDER_LOGS.content
  is '记录内容';
comment on column XFY.IO_BUY_ORDER_LOGS.oper_username
  is '操作用户名';
comment on column XFY.IO_BUY_ORDER_LOGS.oper_user_id
  is '操作用户id';
alter table XFY.IO_BUY_ORDER_LOGS
  add constraint PK_IO_BUY_ORDER_LOGS primary key (ID);

prompt
prompt Creating table IO_ORDERS
prompt ========================
prompt
create table XFY.IO_ORDERS
(
  id                NUMBER(9) not null,
  order_no          VARCHAR2(50) not null,
  created_user_id   NUMBER(9),
  created_time      DATE default sysdate not null,
  last_updated_time DATE not null,
  audit_status      NUMBER(1) not null,
  audit_user_id     NUMBER(9),
  audit_time        DATE,
  store_id          NUMBER(9),
  note              VARCHAR2(3000),
  type              NUMBER(1) not null,
  type_detail       NUMBER(9) not null,
  sell_order_id     NUMBER(9),
  buy_order_no      VARCHAR2(50),
  io_date           DATE
)
;
comment on table XFY.IO_ORDERS
  is '出入库单';
comment on column XFY.IO_ORDERS.id
  is 'ID';
comment on column XFY.IO_ORDERS.order_no
  is '出入单号';
comment on column XFY.IO_ORDERS.created_user_id
  is '制单人';
comment on column XFY.IO_ORDERS.created_time
  is '制单时间';
comment on column XFY.IO_ORDERS.last_updated_time
  is '记录最后更新时间';
comment on column XFY.IO_ORDERS.audit_status
  is '审核状态（0-未审核,1-已审核）';
comment on column XFY.IO_ORDERS.audit_user_id
  is '审核用户';
comment on column XFY.IO_ORDERS.audit_time
  is '审核时间';
comment on column XFY.IO_ORDERS.store_id
  is '对应仓库';
comment on column XFY.IO_ORDERS.note
  is '备注';
comment on column XFY.IO_ORDERS.type
  is '类型(0-入库，1-出库)';
comment on column XFY.IO_ORDERS.type_detail
  is '出入库小类型（字典表定义：出入库详细类型）';
comment on column XFY.IO_ORDERS.sell_order_id
  is '订单编号（若销售自动出库）';
comment on column XFY.IO_ORDERS.buy_order_no
  is '采购单号(若由采购单生成入库)';
comment on column XFY.IO_ORDERS.io_date
  is '出入库日期（记录业务应该发生的日期）';
alter table XFY.IO_ORDERS
  add constraint PK_IO_ORDERS primary key (ID);
alter table XFY.IO_ORDERS
  add constraint UNI_IO_ORDER_NO unique (ORDER_NO);

prompt
prompt Creating table IO_ORDER_ITEMS
prompt =============================
prompt
create table XFY.IO_ORDER_ITEMS
(
  id                   NUMBER(9) not null,
  order_no             VARCHAR2(50) not null,
  goods_sku            VARCHAR2(50) not null,
  goods_name           VARCHAR2(300),
  buy_count            NUMBER(9),
  test_count           NUMBER(9),
  test_type            NUMBER(1),
  qualified_count      NUMBER(9) default 0 not null,
  store_id             NUMBER(9),
  store_shelf_id       NUMBER(9),
  unqualified_count    NUMBER(9) default 0,
  reason               VARCHAR2(500),
  unqualified_shelf_id NUMBER(9),
  stat_count           NUMBER(9),
  stat_price           NUMBER(13,2),
  stat_amount          NUMBER(13,2),
  last_updated_time    DATE default sysdate not null,
  goods_cost           NUMBER(10,2) not null,
  unqualified_store_id NUMBER(9)
)
;
comment on table XFY.IO_ORDER_ITEMS
  is '出入库单细项';
comment on column XFY.IO_ORDER_ITEMS.id
  is 'ID(SEQ_IO_ORDER_ITEMS)';
comment on column XFY.IO_ORDER_ITEMS.order_no
  is '入库单号';
comment on column XFY.IO_ORDER_ITEMS.goods_sku
  is 'SKU';
comment on column XFY.IO_ORDER_ITEMS.goods_name
  is '名称';
comment on column XFY.IO_ORDER_ITEMS.buy_count
  is '采购数量';
comment on column XFY.IO_ORDER_ITEMS.test_count
  is '检验数量';
comment on column XFY.IO_ORDER_ITEMS.test_type
  is '检验方式(0-全检、1-抽检)';
comment on column XFY.IO_ORDER_ITEMS.qualified_count
  is '合格数量';
comment on column XFY.IO_ORDER_ITEMS.store_id
  is '合格存放仓库';
comment on column XFY.IO_ORDER_ITEMS.store_shelf_id
  is '合格存放货位';
comment on column XFY.IO_ORDER_ITEMS.unqualified_count
  is '不合格数量';
comment on column XFY.IO_ORDER_ITEMS.reason
  is '不合格原因（字典表定义）';
comment on column XFY.IO_ORDER_ITEMS.unqualified_shelf_id
  is '不合格存放仓位';
comment on column XFY.IO_ORDER_ITEMS.stat_count
  is '本次出入库后的总数量';
comment on column XFY.IO_ORDER_ITEMS.stat_price
  is '本次出入库后的平均价格';
comment on column XFY.IO_ORDER_ITEMS.stat_amount
  is '本次出入库后的总金额';
comment on column XFY.IO_ORDER_ITEMS.last_updated_time
  is '记录最后更新时间';
comment on column XFY.IO_ORDER_ITEMS.goods_cost
  is '成本价';
comment on column XFY.IO_ORDER_ITEMS.unqualified_store_id
  is '不合格仓库';
create index XFY.IDX_IOI_GOODS_SKU on XFY.IO_ORDER_ITEMS (GOODS_SKU);
create index XFY.IDX_IOI_ORDER_NO on XFY.IO_ORDER_ITEMS (ORDER_NO);
alter table XFY.IO_ORDER_ITEMS
  add constraint PK_IO_ORDER_ITEMS primary key (ID);

prompt
prompt Creating table IO_REASON
prompt ========================
prompt
create table XFY.IO_REASON
(
  id                NUMBER(9) not null,
  code              VARCHAR2(100) not null,
  name              VARCHAR2(300) not null,
  type              NUMBER(1) not null,
  created_time      DATE default sysdate not null,
  last_updated_time DATE not null
)
;
comment on table XFY.IO_REASON
  is '出入库原由配置表';
comment on column XFY.IO_REASON.id
  is 'ID';
comment on column XFY.IO_REASON.name
  is '名称';
comment on column XFY.IO_REASON.type
  is '类型(0-入库，1-出库）';
comment on column XFY.IO_REASON.created_time
  is '记录添加时间';
comment on column XFY.IO_REASON.last_updated_time
  is '记录最后更新时间';
alter table XFY.IO_REASON
  add constraint PK_IO_REASON primary key (ID);

prompt
prompt Creating table ORDERS
prompt =====================
prompt
create table XFY.ORDERS
(
  id                NUMBER(9) not null,
  order_no          VARCHAR2(50),
  order_platform    VARCHAR2(50) not null,
  order_sn          VARCHAR2(300) not null,
  pay_status        VARCHAR2(50),
  order_sale_time   DATE,
  order_paid_time   DATE,
  order_type        NUMBER(4),
  order_status      NUMBER(4),
  currency          VARCHAR2(50),
  amount            NUMBER(9,2),
  is_send           NUMBER(1),
  send_platform     VARCHAR2(50),
  printed_flag      NUMBER(1),
  account_id        VARCHAR2(50),
  shipping_name     VARCHAR2(100),
  scanned_time      DATE,
  track_number      VARCHAR2(50),
  shipped_time      DATE,
  calc_weight       NUMBER(9,4),
  package_weight    NUMBER(9,4),
  shipping_fee      NUMBER(9,4),
  cost              NUMBER(9,4),
  profit            NUMBER(9,4),
  strike_cost       NUMBER(9,4),
  refund_fee        NUMBER(9,4),
  refund_reason     VARCHAR2(300),
  note              VARCHAR2(3000),
  created_time      DATE default sysdate,
  last_updated_time DATE,
  mixed_flag        NUMBER(1),
  reissued_flag     NUMBER(1),
  site              VARCHAR2(10),
  paypaltransid     VARCHAR2(50),
  srn               VARCHAR2(100),
  combine           NUMBER(1),
  combine_orders    VARCHAR2(3000)
)
;
comment on column XFY.ORDERS.order_no
  is '订单编号';
comment on column XFY.ORDERS.order_platform
  is '订单所属平台（ebay,amazon,smt）
';
comment on column XFY.ORDERS.order_sn
  is '平台对应订单ID
';
comment on column XFY.ORDERS.pay_status
  is '订单支付状态
';
comment on column XFY.ORDERS.order_sale_time
  is '下单时间
';
comment on column XFY.ORDERS.order_paid_time
  is '付款时间
';
comment on column XFY.ORDERS.order_type
  is '订单类型,关联外键DICT表
';
comment on column XFY.ORDERS.order_status
  is '订单状态
';
comment on column XFY.ORDERS.currency
  is '订单币种
';
comment on column XFY.ORDERS.amount
  is '订单金额
';
comment on column XFY.ORDERS.is_send
  is '是否标发
';
comment on column XFY.ORDERS.send_platform
  is '标发平台
';
comment on column XFY.ORDERS.printed_flag
  is '是否打印(0-未打印,1-已打印)
';
comment on column XFY.ORDERS.account_id
  is '平台账号
';
comment on column XFY.ORDERS.shipping_name
  is '发货方式
';
comment on column XFY.ORDERS.scanned_time
  is '扫描时间
';
comment on column XFY.ORDERS.track_number
  is '发货跟踪号
';
comment on column XFY.ORDERS.shipped_time
  is '发货时间
';
comment on column XFY.ORDERS.calc_weight
  is '包裹计算重量
';
comment on column XFY.ORDERS.package_weight
  is '包裹实际重量
';
comment on column XFY.ORDERS.shipping_fee
  is '包裹实际运费';
comment on column XFY.ORDERS.cost
  is '总成本
';
comment on column XFY.ORDERS.profit
  is '总利润
';
comment on column XFY.ORDERS.strike_cost
  is '成交费
';
comment on column XFY.ORDERS.refund_fee
  is '订单退款金额
';
comment on column XFY.ORDERS.refund_reason
  is '退款原因
';
comment on column XFY.ORDERS.note
  is '备注
';
comment on column XFY.ORDERS.created_time
  is '记录添加时间
';
comment on column XFY.ORDERS.last_updated_time
  is '记录最后更新时间
';
comment on column XFY.ORDERS.mixed_flag
  is '是否混合';
comment on column XFY.ORDERS.reissued_flag
  is '是否补发订单';
comment on column XFY.ORDERS.site
  is '站点';
comment on column XFY.ORDERS.paypaltransid
  is 'Paypal交易号';
comment on column XFY.ORDERS.srn
  is 'SELL RECORD NUMBER';
comment on column XFY.ORDERS.combine
  is '是否合并 1/0';
comment on column XFY.ORDERS.combine_orders
  is '合并的订单id列表用,分隔';
create index XFY.IDX_ORDERS_SN on XFY.ORDERS (ORDER_SN);
alter table XFY.ORDERS
  add constraint PK_ORDERS_ID primary key (ID);

prompt
prompt Creating table ORDER_BUYERINFO
prompt ==============================
prompt
create table XFY.ORDER_BUYERINFO
(
  order_platform        VARCHAR2(50),
  buyer_user_id         VARCHAR2(50),
  buyer_email           VARCHAR2(50),
  shipping_name         VARCHAR2(100),
  shipping_street1      VARCHAR2(100),
  shipping_street2      VARCHAR2(100),
  shipping_city         VARCHAR2(50),
  shipping_state        VARCHAR2(150),
  shipping_country      VARCHAR2(50),
  shipping_country_name VARCHAR2(50),
  shipping_postcode     VARCHAR2(20),
  shipping_phone        VARCHAR2(30),
  shipping_mobile       VARCHAR2(30),
  id                    NUMBER(9) not null,
  order_sn              VARCHAR2(300)
)
;
comment on column XFY.ORDER_BUYERINFO.order_platform
  is '订单所属平台（ebay,amazon,smt）
';
comment on column XFY.ORDER_BUYERINFO.buyer_user_id
  is '买家-客户ID
';
comment on column XFY.ORDER_BUYERINFO.buyer_email
  is '买家-email
';
comment on column XFY.ORDER_BUYERINFO.shipping_name
  is '买家-名称
';
comment on column XFY.ORDER_BUYERINFO.shipping_street1
  is '买家-街道地址
';
comment on column XFY.ORDER_BUYERINFO.shipping_street2
  is '买家-街道地址2
';
comment on column XFY.ORDER_BUYERINFO.shipping_city
  is '买家-城市
';
comment on column XFY.ORDER_BUYERINFO.shipping_state
  is '买家-州
';
comment on column XFY.ORDER_BUYERINFO.shipping_country
  is '买家-国家缩写
';
comment on column XFY.ORDER_BUYERINFO.shipping_country_name
  is '买家-国家
';
comment on column XFY.ORDER_BUYERINFO.shipping_postcode
  is '买家-邮编
';
comment on column XFY.ORDER_BUYERINFO.shipping_phone
  is '买家-电话
';
comment on column XFY.ORDER_BUYERINFO.shipping_mobile
  is '买家-移动电话';
comment on column XFY.ORDER_BUYERINFO.order_sn
  is '平台对应订单ID';
create index XFY.IDX_ORDERID on XFY.ORDER_BUYERINFO (ORDER_SN);
alter table XFY.ORDER_BUYERINFO
  add constraint BUYINFO_PK_ID primary key (ID);

prompt
prompt Creating table ORDER_ITEMS
prompt ==========================
prompt
create table XFY.ORDER_ITEMS
(
  id              NUMBER(9) not null,
  order_id        NUMBER(9),
  order_sn        VARCHAR2(300),
  item_title      VARCHAR2(300),
  item_pic        VARCHAR2(300),
  item_url        VARCHAR2(300),
  sku             VARCHAR2(50),
  item_sku        VARCHAR2(50),
  item_price      NUMBER(9,2),
  item_quantity   NUMBER(4) not null,
  package_amount  NUMBER(4) default 0,
  lock_amount     NUMBER(4) default 0,
  cancel_amount   NUMBER(4) default 0,
  need_purchase   NUMBER(4) default 0,
  shipment_amount NUMBER(4),
  created_time    DATE,
  item_id         VARCHAR2(100)
)
;
comment on column XFY.ORDER_ITEMS.order_id
  is '系统订单ID（ORDERS 表 ID）
';
comment on column XFY.ORDER_ITEMS.order_sn
  is '平台订单编号（ORDERS 表 order_sn）
';
comment on column XFY.ORDER_ITEMS.item_title
  is '商品title
';
comment on column XFY.ORDER_ITEMS.item_pic
  is '商品图片
';
comment on column XFY.ORDER_ITEMS.item_url
  is '商品平台URL
';
comment on column XFY.ORDER_ITEMS.sku
  is '系统sku,会由平台SKU修改为系统SKU
';
comment on column XFY.ORDER_ITEMS.item_sku
  is '平台SKU，保留值
';
comment on column XFY.ORDER_ITEMS.item_price
  is '价格
';
comment on column XFY.ORDER_ITEMS.item_quantity
  is '购买数量
';
comment on column XFY.ORDER_ITEMS.package_amount
  is '包裹数量
';
comment on column XFY.ORDER_ITEMS.lock_amount
  is '锁定数量
';
comment on column XFY.ORDER_ITEMS.cancel_amount
  is '取消数量
';
comment on column XFY.ORDER_ITEMS.need_purchase
  is '需采购数量';
comment on column XFY.ORDER_ITEMS.shipment_amount
  is '出货数量总和
';
comment on column XFY.ORDER_ITEMS.created_time
  is '记录添加时间
';
comment on column XFY.ORDER_ITEMS.item_id
  is 'ITEM ID
';
create index XFY.IDX_OI_ORDER__ID on XFY.ORDER_ITEMS (ORDER_ID);
create index XFY.IDX_ORDERID_SKU on XFY.ORDER_ITEMS (ORDER_ID, SKU);
create index XFY.IDX_ORDERSN on XFY.ORDER_ITEMS (ORDER_SN);
alter table XFY.ORDER_ITEMS
  add constraint ORDER_ITEM_PK_ID primary key (ID);

prompt
prompt Creating table ORDER_LOGS
prompt =========================
prompt
create table XFY.ORDER_LOGS
(
  id           NUMBER(9) not null,
  order_id     NUMBER(9) not null,
  log          VARCHAR2(3000) not null,
  oper_user_id VARCHAR2(20) not null,
  oper_time    DATE not null
)
;
comment on column XFY.ORDER_LOGS.order_id
  is '系统订单ID（ORDERS 表 ID）
';
comment on column XFY.ORDER_LOGS.log
  is '日志
';
comment on column XFY.ORDER_LOGS.oper_user_id
  is '操作人ID';
comment on column XFY.ORDER_LOGS.oper_time
  is '记录新增时间';
alter table XFY.ORDER_LOGS
  add constraint ORDER_LOG_PK_ID primary key (ID);

prompt
prompt Creating table ORDER_NOTES
prompt ==========================
prompt
create table XFY.ORDER_NOTES
(
  id                NUMBER(9) not null,
  content           VARCHAR2(3000) not null,
  order_id          NUMBER(9) not null,
  oper_user_id      VARCHAR2(20) not null,
  created_time      DATE not null,
  last_updated_time DATE
)
;
comment on column XFY.ORDER_NOTES.content
  is '内容
';
comment on column XFY.ORDER_NOTES.order_id
  is '系统订单ID（ORDERS 表 ID）
';
comment on column XFY.ORDER_NOTES.oper_user_id
  is '操作人ID
';
alter table XFY.ORDER_NOTES
  add constraint ORDER_NO_PK_ID primary key (ID);

prompt
prompt Creating table ORDER_PACKAGE
prompt ============================
prompt
create table XFY.ORDER_PACKAGE
(
  id              VARCHAR2(30) not null,
  order_id        NUMBER(9) not null,
  is_send         NUMBER(1) not null,
  printed_flag    NUMBER(1) not null,
  package_weight  NUMBER(9,4) not null,
  electron_weight NUMBER(9,4),
  status          NUMBER(4) not null,
  created_time    DATE default sysdate not null,
  track_number    VARCHAR2(100),
  specifications  VARCHAR2(30),
  shipping_name   VARCHAR2(100),
  scanned_time    DATE,
  handover_time   DATE,
  shipping_fee    NUMBER(9,4) not null,
  scan_flag       NUMBER(1),
  is_mix          NUMBER(1)
)
;
comment on table XFY.ORDER_PACKAGE
  is '包裹';
comment on column XFY.ORDER_PACKAGE.id
  is 'YYMMDD00001，YYMMDD年年月月日日，00001为流水号';
comment on column XFY.ORDER_PACKAGE.order_id
  is '系统订单ID（ORDERS 表 ID）';
comment on column XFY.ORDER_PACKAGE.is_send
  is '是否标发';
comment on column XFY.ORDER_PACKAGE.printed_flag
  is '是否打印(0-未打印,1-已打印)';
comment on column XFY.ORDER_PACKAGE.package_weight
  is '包裹重量';
comment on column XFY.ORDER_PACKAGE.electron_weight
  is '电子称重量';
comment on column XFY.ORDER_PACKAGE.status
  is '包裹状态';
comment on column XFY.ORDER_PACKAGE.created_time
  is '记录添加时间';
comment on column XFY.ORDER_PACKAGE.track_number
  is '发货跟踪号';
comment on column XFY.ORDER_PACKAGE.specifications
  is '包装规格';
comment on column XFY.ORDER_PACKAGE.shipping_name
  is '发货方式';
comment on column XFY.ORDER_PACKAGE.scanned_time
  is '扫描时间';
comment on column XFY.ORDER_PACKAGE.handover_time
  is '物流交接时间';
comment on column XFY.ORDER_PACKAGE.shipping_fee
  is '包裹运费';
comment on column XFY.ORDER_PACKAGE.scan_flag
  is '是否扫描';
comment on column XFY.ORDER_PACKAGE.is_mix
  is '是否混合包裹';
alter table XFY.ORDER_PACKAGE
  add constraint PK_ORDER_PACKAGE primary key (ID);

prompt
prompt Creating table ORDER_PACKAGE_ITEM
prompt =================================
prompt
create table XFY.ORDER_PACKAGE_ITEM
(
  id              NUMBER(9) not null,
  package_id      VARCHAR2(30) not null,
  order_id        NUMBER(9) not null,
  sku             VARCHAR2(50) not null,
  order_amount    NUMBER(4) not null,
  package_amount  NUMBER(4) not null,
  shipment_amount NUMBER(4) not null,
  created_time    DATE default sysdate not null,
  price           NUMBER(9,4) not null,
  total_price     NUMBER(9,4) not null,
  specifications  VARCHAR2(30)
)
;
comment on table XFY.ORDER_PACKAGE_ITEM
  is '包裹明细';
comment on column XFY.ORDER_PACKAGE_ITEM.package_id
  is '所属包裹（ORDER_PACKAGE 表 ID）';
comment on column XFY.ORDER_PACKAGE_ITEM.order_id
  is '系统订单ID（ORDERS 表 ID）';
comment on column XFY.ORDER_PACKAGE_ITEM.sku
  is '带订单明细的SKU信息';
comment on column XFY.ORDER_PACKAGE_ITEM.order_amount
  is '带订单明细的SKU购买数量';
comment on column XFY.ORDER_PACKAGE_ITEM.package_amount
  is '本包裹所含的sku数量';
comment on column XFY.ORDER_PACKAGE_ITEM.shipment_amount
  is '出货数量';
comment on column XFY.ORDER_PACKAGE_ITEM.created_time
  is '记录添加时间';
comment on column XFY.ORDER_PACKAGE_ITEM.price
  is '带订单明细的单价';
comment on column XFY.ORDER_PACKAGE_ITEM.total_price
  is '单价*包裹数量';
comment on column XFY.ORDER_PACKAGE_ITEM.specifications
  is '包装规格';
alter table XFY.ORDER_PACKAGE_ITEM
  add constraint PK_ORDER_PACKAGE_ITEM primary key (ID);

prompt
prompt Creating table ORDER_SHIPPING_FEE
prompt =================================
prompt
create table XFY.ORDER_SHIPPING_FEE
(
  id            NUMBER(9),
  order_id      NUMBER(9),
  shipping_fee  NUMBER(9,2),
  shipping_name VARCHAR2(20)
)
;
comment on table XFY.ORDER_SHIPPING_FEE
  is '订单运费选择表';
comment on column XFY.ORDER_SHIPPING_FEE.order_id
  is '订单ID';
comment on column XFY.ORDER_SHIPPING_FEE.shipping_fee
  is '订单运费';
comment on column XFY.ORDER_SHIPPING_FEE.shipping_name
  is '运输方式';

prompt
prompt Creating table PAYPAL_ACCOUNTS
prompt ==============================
prompt
create table XFY.PAYPAL_ACCOUNTS
(
  id                NUMBER(9) not null,
  account           VARCHAR2(300) not null,
  api_username      VARCHAR2(300),
  api_password      VARCHAR2(300),
  api_signature     VARCHAR2(300),
  ebay_account      VARCHAR2(50),
  fees              NUMBER(9,4),
  created_time      DATE default sysdate not null,
  last_updated_time DATE not null
)
;
comment on table XFY.PAYPAL_ACCOUNTS
  is 'paypal账户表';
comment on column XFY.PAYPAL_ACCOUNTS.account
  is 'paypal账号';
comment on column XFY.PAYPAL_ACCOUNTS.api_username
  is 'api用户名';
comment on column XFY.PAYPAL_ACCOUNTS.api_password
  is 'api密码';
comment on column XFY.PAYPAL_ACCOUNTS.api_signature
  is 'api签名';
comment on column XFY.PAYPAL_ACCOUNTS.ebay_account
  is '所属ebay账号';
comment on column XFY.PAYPAL_ACCOUNTS.fees
  is '费率';
comment on column XFY.PAYPAL_ACCOUNTS.created_time
  is '记录添加时间';
comment on column XFY.PAYPAL_ACCOUNTS.last_updated_time
  is '记录最后更新时间';
alter table XFY.PAYPAL_ACCOUNTS
  add constraint PK_PAYPAL_ACCOUNTS primary key (ID);

prompt
prompt Creating table PURCHASE_ORDERS
prompt ==============================
prompt
create table XFY.PURCHASE_ORDERS
(
  id                  NUMBER(9) not null,
  order_no            VARCHAR2(50) not null,
  created_user_id     NUMBER(9),
  created_time        DATE not null,
  last_updated_time   DATE not null,
  audit_user_id       NUMBER(9),
  audit_time          DATE,
  note                VARCHAR2(4000),
  pay_method          VARCHAR2(100),
  supplier_id         NUMBER(9) not null,
  buy_user_id         NUMBER(9) not null,
  sell_order_id       NUMBER(9),
  io_order_no         VARCHAR2(50),
  waybill_no          VARCHAR2(100),
  logistics_company   VARCHAR2(100),
  status              NUMBER(1) not null,
  purchase_date       DATE,
  delivery_date       DATE,
  type                NUMBER(9),
  purchase_request_id NUMBER(9),
  freight             NUMBER(9),
  receive_date        DATE
)
;
comment on table XFY.PURCHASE_ORDERS
  is '采购单';
comment on column XFY.PURCHASE_ORDERS.id
  is 'ID(SEQ_PURCHASE_ORDERS)';
comment on column XFY.PURCHASE_ORDERS.order_no
  is '单号';
comment on column XFY.PURCHASE_ORDERS.created_user_id
  is '制单人';
comment on column XFY.PURCHASE_ORDERS.created_time
  is '添加时间';
comment on column XFY.PURCHASE_ORDERS.last_updated_time
  is '记录最后更新时间';
comment on column XFY.PURCHASE_ORDERS.audit_user_id
  is '审核用户';
comment on column XFY.PURCHASE_ORDERS.audit_time
  is '审核时间';
comment on column XFY.PURCHASE_ORDERS.note
  is '备注';
comment on column XFY.PURCHASE_ORDERS.pay_method
  is '采购单付款方式';
comment on column XFY.PURCHASE_ORDERS.supplier_id
  is '供应商ID';
comment on column XFY.PURCHASE_ORDERS.buy_user_id
  is '采购单采购员';
comment on column XFY.PURCHASE_ORDERS.sell_order_id
  is '销售订单编号（如果有）';
comment on column XFY.PURCHASE_ORDERS.io_order_no
  is '入库单号';
comment on column XFY.PURCHASE_ORDERS.waybill_no
  is '运单编号';
comment on column XFY.PURCHASE_ORDERS.logistics_company
  is '物流公司';
comment on column XFY.PURCHASE_ORDERS.status
  is '状态(1-待审核、2-已审核未接收、3-正常关闭、4-手工结案，5审核已接收)';
comment on column XFY.PURCHASE_ORDERS.purchase_date
  is '采购日期';
comment on column XFY.PURCHASE_ORDERS.delivery_date
  is '交货日期';
comment on column XFY.PURCHASE_ORDERS.type
  is '源单类型(1:请购转入 2：手工新增)';
comment on column XFY.PURCHASE_ORDERS.purchase_request_id
  is '请购单号';
comment on column XFY.PURCHASE_ORDERS.freight
  is '运费';
comment on column XFY.PURCHASE_ORDERS.receive_date
  is '物品接收时间';
create unique index XFY.IDX_PO_ORDER_NO on XFY.PURCHASE_ORDERS (ORDER_NO);
alter table XFY.PURCHASE_ORDERS
  add constraint PK_PURCHASE_ORDERS primary key (ID);

prompt
prompt Creating table PURCHASE_ORDER_ITEMS
prompt ===================================
prompt
create table XFY.PURCHASE_ORDER_ITEMS
(
  id                NUMBER(9) not null,
  order_no          VARCHAR2(50) not null,
  goods_sku         VARCHAR2(50) not null,
  goods_name        VARCHAR2(300) not null,
  goods_cost        NUMBER(9,2) not null,
  goods_count       NUMBER(9) not null,
  goods_unit        VARCHAR2(300) not null,
  goods_category    NUMBER(9),
  created_time      DATE,
  goods_weight      NUMBER,
  delivery_date     DATE,
  received_count    NUMBER(9) not null,
  qualified_count   NUMBER(9) not null,
  unqualified_count NUMBER(9) not null
)
;
comment on table XFY.PURCHASE_ORDER_ITEMS
  is '采购单详细项';
comment on column XFY.PURCHASE_ORDER_ITEMS.id
  is 'ID(SEQ_PURCHASE_ORDER_ITEMS)';
comment on column XFY.PURCHASE_ORDER_ITEMS.order_no
  is '采购单单号';
comment on column XFY.PURCHASE_ORDER_ITEMS.goods_sku
  is '商品SKU';
comment on column XFY.PURCHASE_ORDER_ITEMS.goods_name
  is '商品名';
comment on column XFY.PURCHASE_ORDER_ITEMS.goods_cost
  is '商品采购价格';
comment on column XFY.PURCHASE_ORDER_ITEMS.goods_count
  is '商品采购数量';
comment on column XFY.PURCHASE_ORDER_ITEMS.goods_unit
  is '商品单位';
comment on column XFY.PURCHASE_ORDER_ITEMS.goods_category
  is '商口分类';
comment on column XFY.PURCHASE_ORDER_ITEMS.created_time
  is '创建日期';
comment on column XFY.PURCHASE_ORDER_ITEMS.goods_weight
  is '重量';
comment on column XFY.PURCHASE_ORDER_ITEMS.delivery_date
  is '交货日期';
comment on column XFY.PURCHASE_ORDER_ITEMS.received_count
  is '已入库数量(合格+不合格）';
comment on column XFY.PURCHASE_ORDER_ITEMS.qualified_count
  is '合格数量';
comment on column XFY.PURCHASE_ORDER_ITEMS.unqualified_count
  is '不合格数量';
create index XFY.IDX_POI_GOODS_SKU on XFY.PURCHASE_ORDER_ITEMS (GOODS_SKU);
create index XFY.IDX_POI_ORDER_NO on XFY.PURCHASE_ORDER_ITEMS (ORDER_NO);
alter table XFY.PURCHASE_ORDER_ITEMS
  add constraint PK_PURCHASE_ORDER_ITEMS primary key (ID);

prompt
prompt Creating table PURCHASE_ORDER_LOGS
prompt ==================================
prompt
create table XFY.PURCHASE_ORDER_LOGS
(
  id           NUMBER(9) not null,
  order_sn     VARCHAR2(50) not null,
  created_time DATE not null,
  old_status   NUMBER(1),
  new_status   NUMBER(1),
  content      VARCHAR2(3000),
  oper_user_id NUMBER(9)
)
;
comment on table XFY.PURCHASE_ORDER_LOGS
  is '采购单操作日志';
comment on column XFY.PURCHASE_ORDER_LOGS.id
  is 'ID';
comment on column XFY.PURCHASE_ORDER_LOGS.order_sn
  is '采购单号';
comment on column XFY.PURCHASE_ORDER_LOGS.created_time
  is '记录时间';
comment on column XFY.PURCHASE_ORDER_LOGS.old_status
  is '老状态';
comment on column XFY.PURCHASE_ORDER_LOGS.new_status
  is '新状态';
comment on column XFY.PURCHASE_ORDER_LOGS.content
  is '记录内容';
comment on column XFY.PURCHASE_ORDER_LOGS.oper_user_id
  is '操作用户id';
create index XFY.IDX_POL_ORDER_NO on XFY.PURCHASE_ORDER_LOGS (ORDER_SN);
alter table XFY.PURCHASE_ORDER_LOGS
  add constraint PK_PURCHASE_ORDER_LOGS primary key (ID);

prompt
prompt Creating table PURCHASE_REQUEST_ITEMS
prompt =====================================
prompt
create table XFY.PURCHASE_REQUEST_ITEMS
(
  id             NUMBER(9) not null,
  order_no       VARCHAR2(50) not null,
  goods_sku      VARCHAR2(50) not null,
  goods_name     VARCHAR2(300),
  goods_cost     NUMBER(9,2) not null,
  goods_count    NUMBER(9) not null,
  goods_unit     VARCHAR2(300),
  goods_category NUMBER(9),
  delivery_date  DATE
)
;
comment on table XFY.PURCHASE_REQUEST_ITEMS
  is '请购单详细项';
comment on column XFY.PURCHASE_REQUEST_ITEMS.id
  is 'ID(SEQ_PURCHASE_REQUEST_ITEMS)';
comment on column XFY.PURCHASE_REQUEST_ITEMS.order_no
  is '采购单单号';
comment on column XFY.PURCHASE_REQUEST_ITEMS.goods_sku
  is '商品SKU';
comment on column XFY.PURCHASE_REQUEST_ITEMS.goods_name
  is '商品名';
comment on column XFY.PURCHASE_REQUEST_ITEMS.goods_cost
  is '商品采购价格';
comment on column XFY.PURCHASE_REQUEST_ITEMS.goods_count
  is '商品采购数量';
comment on column XFY.PURCHASE_REQUEST_ITEMS.goods_unit
  is '商品单位';
comment on column XFY.PURCHASE_REQUEST_ITEMS.goods_category
  is '商品分类';
comment on column XFY.PURCHASE_REQUEST_ITEMS.delivery_date
  is '交货日期';
create index XFY.IDX_PRI_ORDER_NO on XFY.PURCHASE_REQUEST_ITEMS (ORDER_NO);
alter table XFY.PURCHASE_REQUEST_ITEMS
  add constraint PK_PURCHASE_REQUEST_ITEMS primary key (ID);

prompt
prompt Creating table PURCHASE_REQUEST_ORDERS
prompt ======================================
prompt
create table XFY.PURCHASE_REQUEST_ORDERS
(
  id                NUMBER(9) not null,
  order_no          VARCHAR2(50) not null,
  created_user_id   NUMBER(9),
  created_time      DATE not null,
  last_updated_time DATE not null,
  type              NUMBER(1) not null,
  note              VARCHAR2(4000),
  supplier_id       NUMBER(9) not null,
  buy_user_id       NUMBER(9),
  status            NUMBER(1) not null,
  sell_order_id     NUMBER(9),
  delivery_date     DATE,
  purchase_order_id NUMBER(9),
  purchase_date     DATE
)
;
comment on table XFY.PURCHASE_REQUEST_ORDERS
  is '请购单';
comment on column XFY.PURCHASE_REQUEST_ORDERS.id
  is 'ID(SEQ_PURCHASE_REQUEST_ORDERS)';
comment on column XFY.PURCHASE_REQUEST_ORDERS.order_no
  is '请购单号';
comment on column XFY.PURCHASE_REQUEST_ORDERS.created_user_id
  is '制单人';
comment on column XFY.PURCHASE_REQUEST_ORDERS.created_time
  is '添加时间';
comment on column XFY.PURCHASE_REQUEST_ORDERS.last_updated_time
  is '记录最后更新时间';
comment on column XFY.PURCHASE_REQUEST_ORDERS.type
  is '类型(1-手工增加、2-库存预警、3-订单生成)';
comment on column XFY.PURCHASE_REQUEST_ORDERS.note
  is '备注';
comment on column XFY.PURCHASE_REQUEST_ORDERS.supplier_id
  is '供应商ID';
comment on column XFY.PURCHASE_REQUEST_ORDERS.buy_user_id
  is '采购单采购员';
comment on column XFY.PURCHASE_REQUEST_ORDERS.status
  is '状态(1-待转采购单、2-已转采购单)';
comment on column XFY.PURCHASE_REQUEST_ORDERS.sell_order_id
  is '销售订单编号（如果有）';
comment on column XFY.PURCHASE_REQUEST_ORDERS.delivery_date
  is '交货日期';
comment on column XFY.PURCHASE_REQUEST_ORDERS.purchase_order_id
  is '采购单号';
comment on column XFY.PURCHASE_REQUEST_ORDERS.purchase_date
  is '采购日期';
create index XFY.IDX_PRO_ORDER_NO on XFY.PURCHASE_REQUEST_ORDERS (ORDER_NO);
alter table XFY.PURCHASE_REQUEST_ORDERS
  add constraint PK_PURCHASE_REQUEST_ORDERS primary key (ID);

prompt
prompt Creating table ROLES
prompt ====================
prompt
create table XFY.ROLES
(
  id                NUMBER(9) not null,
  name              VARCHAR2(100) not null,
  note              VARCHAR2(1000),
  created_time      DATE default sysdate not null,
  last_updated_time DATE default sysdate not null
)
;
comment on table XFY.ROLES
  is '角色表';
comment on column XFY.ROLES.id
  is '角色ID(SEQ_ROLES生成)';
comment on column XFY.ROLES.name
  is '角色名称';
comment on column XFY.ROLES.note
  is '备注';
comment on column XFY.ROLES.created_time
  is '记录添加时间';
comment on column XFY.ROLES.last_updated_time
  is '记录最后更新时间';
alter table XFY.ROLES
  add constraint PK_ROLES primary key (ID);

prompt
prompt Creating table ROLE_POWERS
prompt ==========================
prompt
create table XFY.ROLE_POWERS
(
  id           NUMBER(9) not null,
  role_id      NUMBER(9) not null,
  power_code   VARCHAR2(50) not null,
  power_type   VARCHAR2(10) not null,
  created_time DATE default sysdate not null
)
;
comment on table XFY.ROLE_POWERS
  is '角色权限表';
comment on column XFY.ROLE_POWERS.id
  is 'ID(ROLE_POWERS生成)';
comment on column XFY.ROLE_POWERS.role_id
  is '角色ID';
comment on column XFY.ROLE_POWERS.power_code
  is '权限标识码';
comment on column XFY.ROLE_POWERS.power_type
  is '类型(module、page、function)';
comment on column XFY.ROLE_POWERS.created_time
  is '记录添加时间';
alter table XFY.ROLE_POWERS
  add constraint PK_ROLE_POWER primary key (ID);

prompt
prompt Creating table SELLERS
prompt ======================
prompt
create table XFY.SELLERS
(
  id                  NUMBER(9) not null,
  contacts            VARCHAR2(200) not null,
  email               VARCHAR2(200),
  phone               VARCHAR2(200),
  mobile              VARCHAR2(200),
  address             VARCHAR2(500),
  com_name            VARCHAR2(200),
  status              NUMBER(1) default 0 not null,
  created_time        DATE default sysdate not null,
  last_updated_time   DATE default sysdate not null,
  type                NUMBER(1) default 0,
  status_time         DATE,
  id_card_no          VARCHAR2(50),
  id_card_url1        VARCHAR2(200),
  id_card_url2        VARCHAR2(200),
  photo_url           VARCHAR2(200),
  com_code            VARCHAR2(50),
  com_legal_person    VARCHAR2(50),
  com_biz_license_url VARCHAR2(200),
  apply_cert_time     DATE,
  status_note         VARCHAR2(1000)
)
;
comment on table XFY.SELLERS
  is '卖家表';
comment on column XFY.SELLERS.id
  is 'ID';
comment on column XFY.SELLERS.contacts
  is '联系人';
comment on column XFY.SELLERS.email
  is '邮箱';
comment on column XFY.SELLERS.phone
  is '电话';
comment on column XFY.SELLERS.mobile
  is '手机';
comment on column XFY.SELLERS.address
  is '地址';
comment on column XFY.SELLERS.com_name
  is '公司名称';
comment on column XFY.SELLERS.status
  is '状态（0-待提交资料，1-已提交资料待审核，2-审核通过，3-审核不通过）';
comment on column XFY.SELLERS.created_time
  is '记录添加时间';
comment on column XFY.SELLERS.last_updated_time
  is '记录最后更新时间';
comment on column XFY.SELLERS.type
  is '类型(0-个人卖家，1-公司卖家)';
comment on column XFY.SELLERS.status_time
  is '记录状态变化的时间';
comment on column XFY.SELLERS.id_card_no
  is '身份证号';
comment on column XFY.SELLERS.id_card_url1
  is '身份证证明URL';
comment on column XFY.SELLERS.id_card_url2
  is '身份证反面URL';
comment on column XFY.SELLERS.photo_url
  is '个人照片URL';
comment on column XFY.SELLERS.com_code
  is '公司代码';
comment on column XFY.SELLERS.com_legal_person
  is '公司法人代表';
comment on column XFY.SELLERS.com_biz_license_url
  is '公司营业执照URL';
comment on column XFY.SELLERS.apply_cert_time
  is '申请认证时间';
comment on column XFY.SELLERS.status_note
  is '记录状态变化的原因(如：审核不过原因）';
alter table XFY.SELLERS
  add constraint PK_SELLERS primary key (ID);

prompt
prompt Creating table SELLER_CONFIG
prompt ============================
prompt
create table XFY.SELLER_CONFIG
(
  seller_id    NUMBER(9) not null,
  ship_type    CHAR(1) not null,
  carrier_no   NUMBER(5),
  carrier_name VARCHAR2(100)
)
;
comment on table XFY.SELLER_CONFIG
  is '卖家设置';
comment on column XFY.SELLER_CONFIG.seller_id
  is '卖家ID';
comment on column XFY.SELLER_CONFIG.ship_type
  is '发货设置类型 0平台 1指定物流公司 2 卖家';
comment on column XFY.SELLER_CONFIG.carrier_no
  is '物流公司编号';
comment on column XFY.SELLER_CONFIG.carrier_name
  is '物流公司名';

prompt
prompt Creating table SELLER_GOODS
prompt ===========================
prompt
create table XFY.SELLER_GOODS
(
  id           NUMBER(9) not null,
  seller_id    NUMBER(9) not null,
  goods_id     NUMBER(9) not null,
  created_time DATE default sysdate not null
)
;
comment on table XFY.SELLER_GOODS
  is '卖家收藏商品表';
comment on column XFY.SELLER_GOODS.id
  is 'ID(SEQ_SELLER_GOODS)';
comment on column XFY.SELLER_GOODS.seller_id
  is '卖家ID(seller表ID)';
comment on column XFY.SELLER_GOODS.goods_id
  is '商品ID(GOODS表ID)';
comment on column XFY.SELLER_GOODS.created_time
  is '创建时间';
alter table XFY.SELLER_GOODS
  add constraint PK_SELLER_GOODS primary key (ID);
alter table XFY.SELLER_GOODS
  add constraint UNI_SG_SELLER_ID_GOODS_ID unique (SELLER_ID, GOODS_ID);

prompt
prompt Creating table SHIPPINGS
prompt ========================
prompt
create table XFY.SHIPPINGS
(
  id                  NUMBER(9) not null,
  shipping_name       VARCHAR2(300) not null,
  ebay_value          VARCHAR2(300),
  smt_value           VARCHAR2(300),
  country             VARCHAR2(300),
  province            VARCHAR2(300),
  city                VARCHAR2(300),
  name                VARCHAR2(300),
  tel                 VARCHAR2(300),
  street              VARCHAR2(300),
  address             VARCHAR2(300),
  amount_max          NUMBER(9,2),
  amount_min          NUMBER(9,2),
  carrier_sn          VARCHAR2(300),
  signature           VARCHAR2(300),
  note                VARCHAR2(4000),
  supported_accounts  VARCHAR2(4000),
  weight_min          NUMBER(9,4),
  weight_max          NUMBER(9,4),
  priority            NUMBER(4),
  supported_countries VARCHAR2(4000),
  supported_skus      VARCHAR2(4000),
  order_category_id   NUMBER(9),
  created_time        DATE default sysdate not null,
  ebay_shippings      VARCHAR2(4000),
  goods_store_id      NUMBER(9),
  last_updated_time   DATE not null,
  is_battery          NUMBER(1) default 0,
  is_regulated        NUMBER(1) default 0,
  is_liquid           NUMBER(1) default 0,
  is_magnetic         NUMBER(1) default 0,
  is_copyright        NUMBER(1) default 0
)
;
comment on table XFY.SHIPPINGS
  is '发货信息';
comment on column XFY.SHIPPINGS.shipping_name
  is '发货方式名称';
comment on column XFY.SHIPPINGS.ebay_value
  is '对应导出值,上传到eBay的运送方式';
comment on column XFY.SHIPPINGS.smt_value
  is '上传到速卖通的方式名称';
comment on column XFY.SHIPPINGS.country
  is '联系人国家';
comment on column XFY.SHIPPINGS.province
  is '联系人省份';
comment on column XFY.SHIPPINGS.city
  is '联系人城市';
comment on column XFY.SHIPPINGS.name
  is '联系人姓名';
comment on column XFY.SHIPPINGS.tel
  is '联系人电话';
comment on column XFY.SHIPPINGS.street
  is '联系人街道';
comment on column XFY.SHIPPINGS.address
  is '回邮地址';
comment on column XFY.SHIPPINGS.amount_max
  is '金额区间 max';
comment on column XFY.SHIPPINGS.amount_min
  is '金额区间 min';
comment on column XFY.SHIPPINGS.carrier_sn
  is '物流公司代号';
comment on column XFY.SHIPPINGS.signature
  is '签名';
comment on column XFY.SHIPPINGS.note
  is '备注';
comment on column XFY.SHIPPINGS.supported_accounts
  is '支持哪些eBay帐号(,分隔、 '',any,''为所有)';
comment on column XFY.SHIPPINGS.weight_min
  is '重量区间 min';
comment on column XFY.SHIPPINGS.weight_max
  is '重量区间 max';
comment on column XFY.SHIPPINGS.priority
  is '优先级';
comment on column XFY.SHIPPINGS.supported_countries
  is '包含国家(,分隔、 '',any,''为所有)';
comment on column XFY.SHIPPINGS.supported_skus
  is '包含SKU(,分隔、 '',any,''为所有)';
comment on column XFY.SHIPPINGS.order_category_id
  is '选择分类';
comment on column XFY.SHIPPINGS.created_time
  is '记录添加时间';
comment on column XFY.SHIPPINGS.ebay_shippings
  is '对应eBay运送方式(,分隔、 '',any,''为所有)';
comment on column XFY.SHIPPINGS.goods_store_id
  is '对应仓库';
comment on column XFY.SHIPPINGS.last_updated_time
  is '记录最后更新时间';
comment on column XFY.SHIPPINGS.is_battery
  is '是否电子';
comment on column XFY.SHIPPINGS.is_regulated
  is '管制';
comment on column XFY.SHIPPINGS.is_liquid
  is '液体';
comment on column XFY.SHIPPINGS.is_magnetic
  is '磁性';
comment on column XFY.SHIPPINGS.is_copyright
  is '侵权';
alter table XFY.SHIPPINGS
  add constraint PK_SHIPPINGS primary key (ID);

prompt
prompt Creating table SHIPPING_FEE
prompt ===========================
prompt
create table XFY.SHIPPING_FEE
(
  id                  NUMBER(9) not null,
  shipping_id         NUMBER(9) not null,
  region              NVARCHAR2(30),
  country             NVARCHAR2(30) not null,
  first_weight        NUMBER(9,4) not null,
  first_weight_amount NUMBER(9,2) not null,
  add_weight          NUMBER(9,4) not null,
  add_weight_amount   NUMBER(9,2) not null,
  registered_fee      NUMBER(9,2) not null,
  service_fee         NUMBER(9,2) not null,
  discount            NUMBER(9,2) not null,
  discount_rate       NUMBER(9,2) not null,
  min_weight          NUMBER(9,4) not null,
  min_weight_amount   NUMBER(9,2) not null
)
;
comment on table XFY.SHIPPING_FEE
  is '运费';
comment on column XFY.SHIPPING_FEE.shipping_id
  is '物流渠道';
comment on column XFY.SHIPPING_FEE.region
  is '区域';
comment on column XFY.SHIPPING_FEE.country
  is '国家';
comment on column XFY.SHIPPING_FEE.first_weight
  is '首重';
comment on column XFY.SHIPPING_FEE.first_weight_amount
  is '首重价格';
comment on column XFY.SHIPPING_FEE.add_weight
  is '续重';
comment on column XFY.SHIPPING_FEE.add_weight_amount
  is '续重金额';
comment on column XFY.SHIPPING_FEE.registered_fee
  is '挂号费';
comment on column XFY.SHIPPING_FEE.service_fee
  is '处理费';
comment on column XFY.SHIPPING_FEE.discount
  is '折扣';
comment on column XFY.SHIPPING_FEE.discount_rate
  is '折扣率';
comment on column XFY.SHIPPING_FEE.min_weight
  is '最小重量';
comment on column XFY.SHIPPING_FEE.min_weight_amount
  is '最小金额';

prompt
prompt Creating table STAT_SKU_SALES
prompt =============================
prompt
create table XFY.STAT_SKU_SALES
(
  goods_sku         VARCHAR2(50) not null,
  sales7            NUMBER(9) default 0 not null,
  sales15           NUMBER(9) default 0 not null,
  sales30           NUMBER(9) default 0 not null,
  sales60           NUMBER(9) default 0 not null,
  sales180          NUMBER(9) default 0 not null,
  last_updated_time DATE default sysdate not null
)
;
comment on table XFY.STAT_SKU_SALES
  is 'SKU销量统计信息';
comment on column XFY.STAT_SKU_SALES.goods_sku
  is 'sku';
comment on column XFY.STAT_SKU_SALES.sales7
  is '近7天销量';
comment on column XFY.STAT_SKU_SALES.sales15
  is '近15天销量';
comment on column XFY.STAT_SKU_SALES.sales30
  is '近30天销量';
comment on column XFY.STAT_SKU_SALES.sales60
  is '近60天销量';
comment on column XFY.STAT_SKU_SALES.sales180
  is '近180天销量';
comment on column XFY.STAT_SKU_SALES.last_updated_time
  is '记录最后更新时间';
alter table XFY.STAT_SKU_SALES
  add constraint PK_STAT_SKU_SALES primary key (GOODS_SKU);

prompt
prompt Creating table STORE
prompt ====================
prompt
create table XFY.STORE
(
  id                NUMBER(9) not null,
  name              VARCHAR2(100) not null,
  code              VARCHAR2(100) not null,
  location          VARCHAR2(500) not null,
  note              VARCHAR2(1000),
  parent_id         NUMBER(9) not null,
  type              NUMBER(1) not null,
  created_time      DATE default sysdate not null,
  last_updated_time DATE not null
)
;
comment on table XFY.STORE
  is '商品仓库';
comment on column XFY.STORE.id
  is 'ID';
comment on column XFY.STORE.name
  is '仓库名称';
comment on column XFY.STORE.code
  is '仓库编码';
comment on column XFY.STORE.location
  is '仓库地址';
comment on column XFY.STORE.note
  is '备注';
comment on column XFY.STORE.parent_id
  is '上级仓库';
comment on column XFY.STORE.type
  is '仓库属性(0-可用仓、1-虚拟仓、2-不合格仓)';
comment on column XFY.STORE.created_time
  is '记录添加时间';
comment on column XFY.STORE.last_updated_time
  is '最后修改时间';
alter table XFY.STORE
  add constraint PK_STORE primary key (ID);

prompt
prompt Creating table STORE_SHELF
prompt ==========================
prompt
create table XFY.STORE_SHELF
(
  id                NUMBER(9) not null,
  code              VARCHAR2(100) not null,
  note              VARCHAR2(2000),
  store_id          NUMBER(9) not null,
  created_time      DATE default SYSDATE not null,
  last_updated_time DATE not null
)
;
comment on table XFY.STORE_SHELF
  is '商品仓库货位';
comment on column XFY.STORE_SHELF.id
  is 'ID';
comment on column XFY.STORE_SHELF.code
  is '仓位编码';
comment on column XFY.STORE_SHELF.note
  is '备注';
comment on column XFY.STORE_SHELF.store_id
  is '所属仓库ID';
comment on column XFY.STORE_SHELF.created_time
  is '记录添加时间';
comment on column XFY.STORE_SHELF.last_updated_time
  is '最后修改时间';
alter table XFY.STORE_SHELF
  add constraint PK_STORE_SHELF primary key (ID);

prompt
prompt Creating table SYS_FUNCTIONS
prompt ============================
prompt
create table XFY.SYS_FUNCTIONS
(
  code      VARCHAR2(50) not null,
  name      VARCHAR2(100) not null,
  page_code VARCHAR2(50) not null
)
;
comment on table XFY.SYS_FUNCTIONS
  is '系统权限 功能，指页面上的具体功能';
comment on column XFY.SYS_FUNCTIONS.code
  is '代码';
comment on column XFY.SYS_FUNCTIONS.name
  is '名称';
comment on column XFY.SYS_FUNCTIONS.page_code
  is '页面代码';
alter table XFY.SYS_FUNCTIONS
  add constraint PK_SYS_FUNCTIONS primary key (CODE);

prompt
prompt Creating table SYS_MODULES
prompt ==========================
prompt
create table XFY.SYS_MODULES
(
  code VARCHAR2(50) not null,
  name VARCHAR2(100) not null,
  url  VARCHAR2(200),
  sort NUMBER(3) default 0 not null
)
;
comment on table XFY.SYS_MODULES
  is '系统权限 模块表';
comment on column XFY.SYS_MODULES.code
  is '代码';
comment on column XFY.SYS_MODULES.name
  is '名称';
comment on column XFY.SYS_MODULES.url
  is 'URL';
comment on column XFY.SYS_MODULES.sort
  is '排序';
alter table XFY.SYS_MODULES
  add constraint PK_SYS_MODULES primary key (CODE);

prompt
prompt Creating table SYS_PAGES
prompt ========================
prompt
create table XFY.SYS_PAGES
(
  code        VARCHAR2(50) not null,
  name        VARCHAR2(100) not null,
  url         VARCHAR2(200),
  module_code VARCHAR2(50) not null,
  sort        NUMBER(9) default 0 not null
)
;
comment on table XFY.SYS_PAGES
  is '系统权限 页面';
comment on column XFY.SYS_PAGES.code
  is '代码';
comment on column XFY.SYS_PAGES.name
  is '名称';
comment on column XFY.SYS_PAGES.url
  is 'URL';
comment on column XFY.SYS_PAGES.module_code
  is '模块编码';
comment on column XFY.SYS_PAGES.sort
  is '排序';
alter table XFY.SYS_PAGES
  add constraint PK_SYS_PAGES primary key (CODE);

prompt
prompt Creating table USER_INFO
prompt ========================
prompt
create table XFY.USER_INFO
(
  user_id           NUMBER(9) not null,
  code              VARCHAR2(50),
  name              VARCHAR2(50) not null,
  sex               VARCHAR2(10),
  en_name           VARCHAR2(50),
  email             VARCHAR2(50),
  education         NUMBER(2),
  department_id     NUMBER(9),
  birth_date        DATE,
  entry_date        DATE,
  resign_date       DATE,
  phone             VARCHAR2(50),
  mobile            VARCHAR2(50),
  position          VARCHAR2(100),
  id_card_no        VARCHAR2(50),
  address           VARCHAR2(500),
  note              VARCHAR2(1000),
  created_time      DATE default sysdate not null,
  last_updated_time DATE default sysdate not null,
  seller_id         NUMBER(9) default 0 not null,
  is_master         NUMBER(1) default 0,
  is_admin          NUMBER(1) default 0
)
;
comment on table XFY.USER_INFO
  is '用户信息表';
comment on column XFY.USER_INFO.user_id
  is '主键（与USER_LOGIN表一一对应）';
comment on column XFY.USER_INFO.code
  is '工号';
comment on column XFY.USER_INFO.name
  is '姓名';
comment on column XFY.USER_INFO.sex
  is '性别(Male, Female)';
comment on column XFY.USER_INFO.en_name
  is '英文名';
comment on column XFY.USER_INFO.email
  is '邮箱';
comment on column XFY.USER_INFO.education
  is '文化程度(高中、大专、本科、研究生、博士、其它)';
comment on column XFY.USER_INFO.department_id
  is '部门';
comment on column XFY.USER_INFO.birth_date
  is '出生日期';
comment on column XFY.USER_INFO.entry_date
  is '入职日期';
comment on column XFY.USER_INFO.resign_date
  is '离职日期';
comment on column XFY.USER_INFO.phone
  is '电话';
comment on column XFY.USER_INFO.mobile
  is '移动电话';
comment on column XFY.USER_INFO.position
  is '职务';
comment on column XFY.USER_INFO.id_card_no
  is '身份证号';
comment on column XFY.USER_INFO.address
  is '地址';
comment on column XFY.USER_INFO.note
  is '备注';
comment on column XFY.USER_INFO.created_time
  is '记录添加时间';
comment on column XFY.USER_INFO.last_updated_time
  is '记录最后更新时间';
comment on column XFY.USER_INFO.seller_id
  is '卖家ID';
comment on column XFY.USER_INFO.is_master
  is '是否主用户（0-否，1-是）';
comment on column XFY.USER_INFO.is_admin
  is '是否管理员（0-否，1-是）
';
alter table XFY.USER_INFO
  add constraint PK_USER_INFO primary key (USER_ID);

prompt
prompt Creating table USER_LOGIN
prompt =========================
prompt
create table XFY.USER_LOGIN
(
  user_id           NUMBER(9) not null,
  username          VARCHAR2(50) not null,
  password          VARCHAR2(50) not null,
  created_time      DATE default sysdate not null,
  last_updated_time DATE default sysdate not null,
  last_log_time     DATE,
  last_log_ip       VARCHAR2(50)
)
;
comment on table XFY.USER_LOGIN
  is '登陆用户表';
comment on column XFY.USER_LOGIN.user_id
  is '主键(由SEQ_USERS 生成)';
comment on column XFY.USER_LOGIN.username
  is '用户名';
comment on column XFY.USER_LOGIN.password
  is '密码';
comment on column XFY.USER_LOGIN.created_time
  is '记录添加时间,注册时间';
comment on column XFY.USER_LOGIN.last_updated_time
  is '记录最后更新时间';
comment on column XFY.USER_LOGIN.last_log_time
  is '最近登陆时间';
comment on column XFY.USER_LOGIN.last_log_ip
  is '最近登陆IP';
create unique index XFY.IDX_USER_LOGIN_USERNAME on XFY.USER_LOGIN (USERNAME);
alter table XFY.USER_LOGIN
  add constraint PK_USER_LOGIN primary key (USER_ID);

prompt
prompt Creating table USER_ROLES
prompt =========================
prompt
create table XFY.USER_ROLES
(
  id                NUMBER(9) not null,
  user_id           NUMBER(9) not null,
  role_id           NUMBER(9) not null,
  created_time      DATE default sysdate not null,
  last_updated_time DATE default sysdate not null
)
;
comment on table XFY.USER_ROLES
  is '用户角色表';
comment on column XFY.USER_ROLES.id
  is 'ID(SEQ_ROLES生成)';
comment on column XFY.USER_ROLES.user_id
  is '用户ID';
comment on column XFY.USER_ROLES.role_id
  is '角色ID';
comment on column XFY.USER_ROLES.created_time
  is '记录添加时间';
comment on column XFY.USER_ROLES.last_updated_time
  is '记录最后更新时间';
alter table XFY.USER_ROLES
  add constraint PK_USER_ROLES primary key (ID);
alter table XFY.USER_ROLES
  add constraint IDX_UR_USER_ID_ROLE_ID unique (USER_ID, ROLE_ID);


spool off
