INSERT INTO roles (id, name) VALUES
(1, 'ROLE_SMS_OWNER'),
(2, 'ROLE_PRINCIPLE'),
(3, 'ROLE_DATA_ENTRY'),
(4, 'ROLE_TEACHER'),
(5, 'ROLE_MANAGER');
INSERT INTO users (id, version, created_at, created_by, updated_at, updated_by, designation, email, employeeid, enabled, name, password, phone, temppassword, username) VALUES (1, 0, '2017-10-27 11:35:44.122000', '1', '2017-10-27 11:35:57.420000', '1', 'owner', 'owner@sms.com', '1', true, 'owner', '$2a$10$npxwaJc/i.y6SB.E73eNl..N.YPFsm4fWFPinEgaG36gKCb9mWQMW', '3322', false, 'owner');
INSERT INTO user_roles (user_id, roles_id) VALUES ('1', '4');

