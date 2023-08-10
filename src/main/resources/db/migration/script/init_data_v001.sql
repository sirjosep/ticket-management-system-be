INSERT INTO t_role(role_code, role_name, created_by, created_at, updated_by, updated_at, is_active, ver) VALUES
	('ADMIN', 'Super Admin', 1, now(), 1, now(), true, 1),
	('PIC', 'PIC', 1, now(), 1, now(), true, 1),
	('DEV', 'Developer', 1, now(), 1, now(), true, 1),
	('CUST', 'Customer', 1, now(), 1, now(), true, 1);
	
INSERT INTO t_file(files, file_format, created_by, created_at, is_active, ver) VALUES 
	('Profile_pic', '.jpeg', 1, now(), true, 1),
	('Company_pic', '.jpeg', 1, now(), true, 1);
	
INSERT INTO t_company(company_code, company_name, company_phone, company_address, file_id, created_by, created_at, is_active, ver) VALUES 
	('LAW01','PT. Lawencon International', '(021) 28542549', 'Pakuwon Tower, Jl Casablanca No.Kav 88, Menteng Dalam Lt. 22, Kec. Tebet, Kota Jakarta Selatan', 2, 1, now(), true, 1);

INSERT INTO t_profile(profile_name, profile_phone, profile_address, file_id, created_by, created_at, is_active, ver) VALUES
	('Rebecca Meliani Simangunsong', '08231289302', 'Jl. Ancol No. 69, Jakarta', null, 1, now(), true, 1);

INSERT INTO t_user(email, password, company_id, role_id, profile_id, created_by, created_at, is_active, ver) VALUES
	('beccamelianis12@gmail.com', '$2a$12$2iRJqJaKHxXfqmyz.iTMLuC/ts7.Bq5HMSu6OLrv.PE729S.eC/AK', 1, 1, 1, 1, now(), true, 1);
	
INSERT INTO t_ticket_status(ticket_status_code, ticket_status_name, created_by, created_at, is_active, ver) VALUES 
	('OPN','Open', 1, now(), true, 1), 
	('PDA','Pending Agent', 1, now(), true, 1), 
	('ONP','On Progress', 1, now(), true, 1), 
	('PDC','Pending Customer', 1, now(), true, 1), 
	('CLS','Closed', 1, now(), true, 1), 
	('REO','Re-open', 1, now(), true, 1);
	
INSERT INTO t_ticket_priority(ticket_priority_code, ticket_priority_name, ticket_priority_limit, created_by, created_at, is_active, ver) VALUES
	('HI','High', 3, 1, now(), true, 1),
	('MED','Medium', 5, 1, now(), true, 1),
	('LOW','Low', 0, 1, now(), true, 1);