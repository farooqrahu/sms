INSERT INTO roles (id,name) VALUES(1, 'ROLE_SALE_REP'),(2, 'ROLE_DATA_ENTRY'),(3, 'ROLE_WAREHOUSE_MANAGER'),(4, 'ROLE_SMS_OWNER'),(5, 'ROLE_SALE_MANAGER');

INSERT INTO users  VALUES ('1', 'owner', 'ifgowner@ifgowner.com', '1', b'1', 'ifg','$2a$10$npxwaJc/i.y6SB.E73eNl..N.YPFsm4fWFPinEgaG36gKCb9mWQMW', '123', b'0', 'ifgowner'); 
INSERT INTO user_roles (user_id, roles_id) VALUES ('1', '4');

INSERT INTO users  VALUES ('2', 'saleManager', 'saleManager@ifg.com', '2', b'1', 'saleManager','$2a$10$npxwaJc/i.y6SB.E73eNl..N.YPFsm4fWFPinEgaG36gKCb9mWQMW', '123', b'0', 'saleManager'); 
INSERT INTO user_roles (`user_id`, `roles_id`) VALUES ('2', '5');

INSERT INTO warehouse (warehouse_id, code, created_by, creation_date, description, is_active, modification_date, modified_by, name) VALUES
	(1, '001', 'admin', '1463146383178', 'IFGWareHouse', b'1', NULL, NULL, 'SMS'),
	(2, '001', 'admin', '1463146383178', 'TrimuphWareHouse', b'1', NULL, NULL, 'Trimuph'),
	(3, '001', 'admin', '1463146383178', 'CentralWareHouse', b'1', NULL, NULL, 'Central');
	
INSERT INTO brand (brand_id, created_by, creation_date, description, is_active, modification_date, modified_by, name) VALUES
	(1, 'none', '1463146383178', 'SMS', b'1', NULL, NULL, 'SMS'),
	(2, 'none', '1463146383178', 'TRIUMPH', b'1', NULL, NULL, 'TRIUMPH');	
	
INSERT INTO `size` (`size_id`, `created_by`, `creation_date`, `is_active`, `modification_date`, `modified_by`, `product_size`, `brand_id`) VALUES	
	(1, 'admin', '2016-04-25 09:42:28', b'1', NULL, NULL, '30', 1),
	(2, 'admin', '2016-04-25 09:42:28', b'1', NULL, NULL, '32', 1),
	(3, 'admin', '2016-04-25 09:42:28', b'1', NULL, NULL, '34', 1),
	(4, 'admin', '2016-04-25 09:42:28', b'1', NULL, NULL, '36', 1),
	(5, 'admin', '2016-04-25 09:42:28', b'1', NULL, NULL, '38', 1),
	(6, 'admin', '2016-04-25 09:42:28', b'1', NULL, NULL, '40', 1),
	(7, 'admin', '2016-04-25 09:42:28', b'1', NULL, NULL, '42', 1),
	(8, 'admin', '2016-04-25 09:42:28', b'1', NULL, NULL, '44', 1),
	(9, 'admin', '2016-04-25 09:42:28', b'1', NULL, NULL, '46', 1),
	(10, 'admin', '2016-04-25 09:42:28', b'1', NULL, NULL, '30(S)', 2),
	(11, 'admin', '2016-04-25 09:42:28', b'1', NULL, NULL, '32(S)', 2),
	(12, 'admin', '2016-04-25 09:42:28', b'1', NULL, NULL, '34(M)', 2),
	(13, 'admin', '2016-04-25 09:42:28', b'1', NULL, NULL, '36(M)', 2),
	(14, 'admin', '2016-04-25 09:42:28', b'1', NULL, NULL, '38(L)', 2),
	(15, 'admin', '2016-04-25 09:42:28', b'1', NULL, NULL, '40(L)', 2),
	(16, 'admin', '2016-04-25 09:42:28', b'1', NULL, NULL, '42(XL)', 2),
	(17, 'admin', '2016-04-25 09:42:28', b'1', NULL, NULL, '44(XL)', 2),
	(18, 'admin', '2016-04-25 09:42:28', b'1', NULL, NULL, '46(XL)', 2);

INSERT INTO payment_method (payment_method_id, created_by, creation_date, description, is_active, name) VALUES ('1', 'admin', '1463146383178', 'abc', b'1', 'ADVANCE');
INSERT INTO payment_method (payment_method_id, created_by, creation_date, description, is_active, name) VALUES ('2', 'admin', '1463146383178', 'abc', b'1', 'BANK');
INSERT INTO payment_method (payment_method_id, created_by, creation_date, description, is_active, name) VALUES ('3', 'admin', '1463146383178', 'abc', b'1', 'CASH');
INSERT INTO payment_method (payment_method_id, created_by, creation_date, description, is_active, name) VALUES ('4', 'admin', '1463146383178', 'abc', b'1', 'CREDIT');

INSERT INTO list_of_taxes (`tax_id`, `add_gst_tax`, `created_by`, `creation_date`, `ifg_gst_tax`, `is_active`, `tri_gst_tax`, `wht`) VALUES ('1', '0', 'admin', '32423423423423', '15', b'1', '17', '0');