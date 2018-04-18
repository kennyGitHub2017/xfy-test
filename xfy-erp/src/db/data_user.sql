prompt PL/SQL Developer import file
prompt Created on 2015年3月28日 by Administrator
set feedback off
set define off
prompt Loading USER_INFO...
insert into USER_INFO (user_id, code, name, sex, en_name, email, education, department_id, birth_date, entry_date, resign_date, phone, mobile, position, id_card_no, address, note, created_time, last_updated_time, seller_id, is_master, is_admin)
values (0, null, 'xfy-erp-admin', null, null, null, null, null, null, null, null, null, null, null, null, null, null, to_date('28-03-2015 15:20:36', 'dd-mm-yyyy hh24:mi:ss'), to_date('28-03-2015 15:20:36', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, 1);
commit;
prompt 1 records loaded
prompt Loading USER_LOGIN...
insert into USER_LOGIN (user_id, username, password, created_time, last_updated_time, last_log_time, last_log_ip)
values (0, 'xfy-erp-admin', '1d7c2923c1684726dc23d2901c4d8157', to_date('28-03-2015 15:21:24', 'dd-mm-yyyy hh24:mi:ss'), to_date('28-03-2015 15:21:24', 'dd-mm-yyyy hh24:mi:ss'), null, null);
commit;
prompt 1 records loaded
set feedback on
set define on
prompt Done.
