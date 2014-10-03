create table ip_group (
	group_id LONG not null primary key,
	group_admin_id LONG,
	group_parent_id LONG,
	group_name VARCHAR(75) null,
	group_status VARCHAR(75) null,
	group_email VARCHAR(75) null,
	group_is_core VARCHAR(75) null
);

create table ip_login (
	login_id LONG not null primary key,
	login_user_id LONG,
	login_name VARCHAR(75) null,
	login_pwd VARCHAR(75) null,
	login_last_dt DATE null,
	login_sec_q INTEGER,
	login_sec_a VARCHAR(75) null
);

create table ip_secq_list (
	isl_id LONG not null primary key,
	isl_desc VARCHAR(75) null
);

create table ip_user (
	user_id LONG not null primary key,
	user_f_name VARCHAR(75) null,
	user_l_name VARCHAR(75) null,
	user_m_name VARCHAR(75) null,
	user_id_num LONG,
	user_screen_name VARCHAR(75) null,
	user_email VARCHAR(75) null,
	user_contact VARCHAR(75) null,
	user_skills VARCHAR(75) null,
	user_bio VARCHAR(75) null,
	user_fb_handle VARCHAR(75) null,
	user_tw_handle VARCHAR(75) null,
	user_status VARCHAR(75) null,
	user_employeeId VARCHAR(75) null,
	user_pri_grp_id LONG
);