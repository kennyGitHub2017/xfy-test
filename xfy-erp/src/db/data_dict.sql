prompt PL/SQL Developer import file
prompt Created on 2015年3月28日 by Administrator
set feedback off
set define off
prompt Loading DICT...
insert into DICT (type, code, name)
values (0, 100, '订单类型');
insert into DICT (type, code, name)
values (0, 101, '订单状态');
insert into DICT (type, code, name)
values (0, 102, '物品状态');
insert into DICT (type, code, name)
values (101, 3, '已锁定');
insert into DICT (type, code, name)
values (103, 0, '1');
insert into DICT (type, code, name)
values (201, 15, '盘点盘亏');
insert into DICT (type, code, name)
values (201, 11, '样品借出');
insert into DICT (type, code, name)
values (201, 13, '退货出库');
insert into DICT (type, code, name)
values (201, 55, '其它入库');
insert into DICT (type, code, name)
values (102, 4, '换季');
insert into DICT (type, code, name)
values (102, 6, '暂时下线');
insert into DICT (type, code, name)
values (202, 1, '虚拟仓');
insert into DICT (type, code, name)
values (100, 14, '买家已退货');
insert into DICT (type, code, name)
values (101, 2, '待锁定');
insert into DICT (type, code, name)
values (100, 16, '超期订单');
insert into DICT (type, code, name)
values (0, 201, '出入库详细类型');
insert into DICT (type, code, name)
values (201, 10, '出货');
insert into DICT (type, code, name)
values (201, 12, '不良品退货出库');
insert into DICT (type, code, name)
values (201, 14, '其它出库');
insert into DICT (type, code, name)
values (201, 50, '成品入库');
insert into DICT (type, code, name)
values (201, 52, '借出归还');
insert into DICT (type, code, name)
values (201, 53, '物流退货入库');
insert into DICT (type, code, name)
values (0, 220, '不合格原因');
insert into DICT (type, code, name)
values (102, 1, '上线');
insert into DICT (type, code, name)
values (0, 202, '仓库属性');
insert into DICT (type, code, name)
values (102, 2, '下线');
insert into DICT (type, code, name)
values (101, 4, '部分生成包裹');
insert into DICT (type, code, name)
values (101, 5, '生成包裹');
insert into DICT (type, code, name)
values (202, 0, '可用仓');
insert into DICT (type, code, name)
values (202, 2, '不合格仓');
insert into DICT (type, code, name)
values (101, 6, '取消');
insert into DICT (type, code, name)
values (200, 0, '入库');
insert into DICT (type, code, name)
values (201, 54, '期初入库');
insert into DICT (type, code, name)
values (102, 3, '清仓');
insert into DICT (type, code, name)
values (102, 5, '缺货');
insert into DICT (type, code, name)
values (201, 56, '采购入库');
insert into DICT (type, code, name)
values (101, 1, '未审核');
insert into DICT (type, code, name)
values (201, 51, '不良品换回');
insert into DICT (type, code, name)
values (0, 200, '出入库大类');
insert into DICT (type, code, name)
values (200, 1, '出库');
insert into DICT (type, code, name)
values (220, 1, '外观损坏');
insert into DICT (type, code, name)
values (220, 2, '质量不合格');
insert into DICT (type, code, name)
values (101, 7, '发货');
insert into DICT (type, code, name)
values (0, 500, '订单退款原因');
insert into DICT (type, code, name)
values (500, 1, '货品损坏');
insert into DICT (type, code, name)
values (500, 2, '取消货品');
insert into DICT (type, code, name)
values (100, 15, '质量有缺陷');
commit;
prompt 47 records loaded
set feedback on
set define on
prompt Done.
