create index IX_E58B963C on ip_group (group_id);

create index IX_DD44897C on ip_login (login_id);
create index IX_A6C59B2C on ip_login (login_name);
create index IX_1C33D0AB on ip_login (login_name, login_pwd);
create index IX_5E88D0E1 on ip_login (login_name, login_sec_a);

create index IX_8B87B117 on ip_secq_list (isl_id);

create index IX_8A463CB6 on ip_user (user_id);
create index IX_DE53ED27 on ip_user (user_screen_name);