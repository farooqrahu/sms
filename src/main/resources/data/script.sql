INSERT INTO `roles` (`id`, `name`) VALUES
	(1, 'ROLE_SMS_OWNER'),
	(2, 'ROLE_PRINCIPLE'),
	(3, 'ROLE_DATA_ENTRY'),
	(4, 'ROLE_TEACHER'),
	(5, 'ROLE_MANAGER');

INSERT INTO users  VALUES ('1', 'owner', 'owner@sms.com', '1', b'1', 'ifg','$2a$10$npxwaJc/i.y6SB.E73eNl..N.YPFsm4fWFPinEgaG36gKCb9mWQMW', '123', b'0', 'SMS_OWNER');
INSERT INTO user_roles (user_id, roles_id) VALUES ('1', '4');

