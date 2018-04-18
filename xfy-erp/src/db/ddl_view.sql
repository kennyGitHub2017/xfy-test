-----------------------------------------------------
-- Export file for user XFY                        --
-- Created by Administrator on 2015/3/28, 16:14:26 --
-----------------------------------------------------

set define off
spool xfy_ddl_view.log

prompt
prompt Creating view V_GOODS_INVENTORY_STAT
prompt ====================================
prompt
create or replace view xfy.v_goods_inventory_stat as
select t1.goods_sku,
       t1.all_count,
       nvl(t2.lock_count, 0) lock_count,
       (t1.all_count + nvl(t3.purchase_count, 0) - nvl(t2.lock_count, 0)) available_count,
       nvl(t3.purchase_count, 0) purchase_count
  from (select g.goods_sku, sum(g.count) all_count
          from goods_inventory g
         group by g.goods_sku) t1

  left join (select l.goods_sku, count(l.lock_count) lock_count
               from goods_inventory_lock l
              group by l.goods_sku) t2
    on t1.goods_sku = t2.goods_sku

  left join (select poi.goods_sku, sum(poi.goods_count - poi.received_count) purchase_count
               from purchase_order_items poi, purchase_orders po
              where poi.order_no = po.order_no
                and po.status = 2
              group by poi.goods_sku) t3
    on t1.goods_sku = t3.goods_sku;


spool off
