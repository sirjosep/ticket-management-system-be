CREATE TABLE t_file(
	id serial,
	files text NOT NULL,
	file_format varchar(5) NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);

ALTER TABLE t_file ADD CONSTRAINT file_pk
	PRIMARY KEY(id);

CREATE TABLE t_profile (
	id serial, 
	profile_name varchar(50),
	profile_phone varchar(13),
	profile_address text,
	file_id int,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);

ALTER TABLE t_profile ADD CONSTRAINT profile_pk
	PRIMARY KEY(id);

ALTER TABLE t_profile ADD CONSTRAINT profile_file_fk
	FOREIGN KEY(file_id)
	REFERENCES t_file(id);

CREATE TABLE t_role(
	id serial,
	role_code varchar(5) NOT NULL,
	role_name varchar(12) NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);

ALTER TABLE t_role ADD CONSTRAINT role_pk
	PRIMARY KEY(id);

ALTER TABLE t_role ADD UNIQUE (role_code);

CREATE TABLE t_company (
	id serial,
	company_code varchar(5) NOT NULL,
	company_name varchar(50) NOT NULL,
	company_phone varchar(20) NOT NULL,
	company_address text NOT NULL,
	file_id int,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);

ALTER TABLE t_company ADD CONSTRAINT company_pk
	PRIMARY KEY(id);

ALTER TABLE t_company ADD CONSTRAINT company_file_fk
	FOREIGN KEY(file_id)
	REFERENCES t_file(id);

ALTER TABLE t_company ADD UNIQUE (company_code);

CREATE TABLE t_user (
	id serial,
	email varchar(50) NOT NULL,
	password text NOT NULL,
	company_id int NOT NULL,
	role_id int NOT NULL,
	profile_id int NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);

ALTER TABLE t_user ADD CONSTRAINT user_pk
	PRIMARY KEY(id);

ALTER TABLE t_user ADD CONSTRAINT user_role_fk
	FOREIGN KEY(role_id)
	REFERENCES t_role(id);
	
ALTER TABLE t_user ADD CONSTRAINT user_profile_fk
	FOREIGN KEY(profile_id)
	REFERENCES t_profile(id);

ALTER TABLE t_user ADD CONSTRAINT user_company_fk
	FOREIGN KEY(company_id)
	REFERENCES t_company(id);

ALTER TABLE t_user ADD UNIQUE (email);
	
CREATE TABLE t_pic_assignment(
	id serial,
	pic_id int NOT NULL,
	costumer_id int NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);

ALTER TABLE t_pic_assignment ADD CONSTRAINT pic_assignment_pk
	PRIMARY KEY(id);

ALTER TABLE t_pic_assignment ADD CONSTRAINT pic_assignment_fk
	FOREIGN KEY(pic_id)
	REFERENCES t_user(id);

ALTER TABLE t_pic_assignment ADD CONSTRAINT pc_assignment_costumer_fk
	FOREIGN KEY(costumer_id)
	REFERENCES t_user(id);

CREATE TABLE t_product(
	id serial,
	product_code varchar(5) NOT NULL,
	product_name varchar(50) NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);

ALTER TABLE t_product ADD CONSTRAINT product_pk
	PRIMARY KEY(id);

ALTER TABLE t_product ADD UNIQUE (product_code);
	
CREATE TABLE t_subscribed_product_detail(
	id serial,
	user_id int NOT NULL,
	product_id int NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);

ALTER TABLE t_subscribed_product_detail ADD CONSTRAINT subscribed_product_detail_pk
	PRIMARY KEY(id);

ALTER TABLE t_subscribed_product_detail ADD CONSTRAINT subscribed_product_detail_user_fk
	FOREIGN KEY(user_id)
	REFERENCES t_user(id);

ALTER TABLE t_subscribed_product_detail ADD CONSTRAINT subscribed_product_detail_product_fk
	FOREIGN KEY(product_id)
	REFERENCES t_product(id);

CREATE TABLE t_ticket_priority(
	id serial,
	ticket_priority_code varchar(5) NOT NULL,
	ticket_priority_name varchar (7) NOT NULL,
	ticket_priority_limit int NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);

ALTER TABLE t_ticket_priority ADD CONSTRAINT ticket_priority_pk
	PRIMARY KEY(id);

CREATE TABLE t_ticket_status(
	id serial,
	ticket_status_code varchar(5) NOT NULL,
	ticket_status_name varchar(20) NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);

ALTER TABLE t_ticket_status ADD CONSTRAINT ticket_status_pk
	PRIMARY KEY(id);

CREATE TABLE t_ticket(
	id serial,
	ticket_code varchar(5) NOT NULL,
	ticket_title text NOT NULL,
	ticket_body text NOT NULL,
	user_id int NOT NULL,
	product_id int NOT NULL,
	ticket_status_id int NOT NULL,
	ticket_priority_id int NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);

ALTER TABLE t_ticket ADD CONSTRAINT ticket_pk
	PRIMARY KEY(id);

ALTER TABLE t_ticket ADD CONSTRAINT ticket_user_fk
	FOREIGN KEY(user_id)
	REFERENCES t_user(id);

ALTER TABLE t_ticket ADD CONSTRAINT ticket_status_fk
	FOREIGN KEY(ticket_status_id)
	REFERENCES t_ticket_status(id);

ALTER TABLE t_ticket ADD CONSTRAINT ticket_priority_fk
	FOREIGN KEY(ticket_priority_id)
	REFERENCES t_ticket_priority(id);

ALTER TABLE t_ticket ADD CONSTRAINT ticket_product_fk
	FOREIGN KEY(product_id)
	REFERENCES t_product(id);

CREATE TABLE t_file_ticket(
	id serial,
	ticket_id int NOT NULL,
	file_id int NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);

ALTER TABLE t_file_ticket ADD CONSTRAINT file_ticket_pk
	PRIMARY KEY(id);

ALTER TABLE t_file_ticket ADD CONSTRAINT ticket_file_fk
	FOREIGN KEY(ticket_id)
	REFERENCES t_ticket(id);

ALTER TABLE t_file_ticket ADD CONSTRAINT file_ticket_fk
	FOREIGN KEY(file_id)
	REFERENCES t_file(id);

CREATE TABLE t_developer_assignment(
	id serial,
	ticket_id int NOT NULL,
	developer_id int NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);

ALTER TABLE t_developer_assignment ADD CONSTRAINT dev_assignment_pk
	PRIMARY KEY(id);

ALTER TABLE t_developer_assignment ADD CONSTRAINT dev_assignment_ticket_id
	FOREIGN KEY(ticket_id)
	REFERENCES t_ticket(id);

ALTER TABLE t_developer_assignment ADD CONSTRAINT dev_assignment_dev_id
	FOREIGN KEY(developer_id)
	REFERENCES t_user(id);

CREATE TABLE t_ticket_comment(
	id serial,
	user_id int NOT NULL,
	forum_comment text,
	ticket_id int NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);

ALTER TABLE t_ticket_comment ADD CONSTRAINT ticket_comment_pk
	PRIMARY KEY(id);

ALTER TABLE t_ticket_comment ADD CONSTRAINT ticket_comment_ticket_fk
	FOREIGN KEY(ticket_id)
	REFERENCES t_ticket(id);

ALTER TABLE t_ticket_comment ADD CONSTRAINT ticket_comment_user_fk
	FOREIGN KEY(user_id)
	REFERENCES t_user(id);

CREATE TABLE t_file_ticket_comment(
	id serial,
	file_id int NOT NULL,
	ticket_comment_id int NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);

ALTER TABLE t_file_ticket_comment ADD CONSTRAINT file_ticket_comment_pk
	PRIMARY KEY(id);

ALTER TABLE t_file_ticket_comment ADD CONSTRAINT file_ticket_comment_fk
	FOREIGN KEY(file_id)
	REFERENCES t_file(id);

ALTER TABLE t_file_ticket_comment ADD CONSTRAINT file_ticket_comment_id_fk
	FOREIGN KEY(ticket_comment_id)
	REFERENCES t_ticket_comment(id);