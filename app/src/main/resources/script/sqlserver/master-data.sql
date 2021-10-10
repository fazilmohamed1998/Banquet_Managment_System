SET IDENTITY_INSERT sch_entity.entity_status ON;
INSERT INTO sch_entity.entity_status(id, name) VALUES (1, 'Active'),(2, 'Inactive'),(3, 'Blocked'),(4, 'Deleted');
SET IDENTITY_INSERT sch_entity.entity_status OFF;

SET IDENTITY_INSERT sch_entity.role ON;
INSERT INTO sch_entity.role(id, name) VALUES (1, 'Admin'),(2, 'Accountant'),(3, 'Receptionist');
SET IDENTITY_INSERT sch_entity.role OFF;

INSERT INTO sch_entity.permission (code, description)VALUES ('APP', 'App Permission'),('BANQUET_HALL_MGMT', 'Banquet Hall Management'),('CUSTOMER_MGMT', 'Customer Management'),('EVENT_MGMT', 'Event Management'),('FOOD_MGMT', 'Food Management'),('PAYMENT', 'Payment'),('RESERVATION', 'Reservation'),('RESTAURANT_MGMT', 'Restaurant Management'),('USER_MGMT', 'User Management');

INSERT INTO sch_entity.role_permission(role_id, permission_id) SELECT id,(SELECT id FROM sch_entity.permission WHERE code = 'APP') FROM sch_entity.role;

INSERT INTO sch_entity.role_permission(role_id, permission_id) SELECT (SELECT id FROM sch_entity.role WHERE name = 'Admin'),id FROM sch_entity.permission WHERE code <> 'APP';

INSERT INTO sch_entity.entity (nic, name, email, address, user_name, password, status)VALUES ('952903683V', 'HaShaN', 'hashans95@gmail.com', 'Nugegoda', 'hashan','$2a$10$b/EswFOoLX81i/zcgRmvHOR//oUX4fTBz9lgnefxqAP0gbSVCSeNG', 1);

INSERT INTO sch_user.user_role(role_id) VALUES ((SELECT id FROM sch_entity.role WHERE name = 'Admin')),((SELECT id FROM sch_entity.role WHERE name = 'Accountant')),((SELECT id FROM sch_entity.role WHERE name = 'Receptionist'));

INSERT INTO sch_user.[user](contact_no, epf_no, join_date, entity_id, user_role_id) VALUES ('0718761179', '1002411', GETDATE(), (SELECT id FROM sch_entity.entity WHERE nic = '952903683V'),(SELECT role_id FROM sch_user.user_role WHERE role_id = (SELECT id FROM sch_entity.role WHERE name = 'Admin')));

SET IDENTITY_INSERT sch_payment.payment_method ON;
INSERT INTO sch_payment.payment_method(id, type) VALUES (1, 'Cash'), (2, 'Credit Card'), (3, 'Debit Card'), (4, 'Cheque'), (5, 'Fund Transfer');
SET IDENTITY_INSERT sch_payment.payment_method OFF;

SET IDENTITY_INSERT sch_payment.payment_status ON;
INSERT INTO sch_payment.payment_status(id, name) VALUES (1, 'Success'), (2, 'Refund');
SET IDENTITY_INSERT sch_payment.payment_status OFF;

SET IDENTITY_INSERT sch_banquet_hall_mgmt.banquet_hall_status ON;
INSERT INTO sch_banquet_hall_mgmt.banquet_hall_status(id, name) VALUES (1, 'Available'), (2, 'In Repair');
SET IDENTITY_INSERT sch_banquet_hall_mgmt.banquet_hall_status OFF;

SET IDENTITY_INSERT sch_banquet_hall_mgmt.banquet_hall_type ON;
INSERT INTO sch_banquet_hall_mgmt.banquet_hall_type(id, type) VALUES (1, 'Conference Hall'), (2,'Reception Hall'), (3, 'Other');
SET IDENTITY_INSERT sch_banquet_hall_mgmt.banquet_hall_type OFF;

SET IDENTITY_INSERT sch_food_mgmt.food_category ON;
INSERT INTO sch_food_mgmt.food_category(id, name) VALUES (1, 'Rice'), (2, 'Noodles'), (3, 'Pasta');
SET IDENTITY_INSERT sch_food_mgmt.food_category OFF;

SET IDENTITY_INSERT sch_food_mgmt.food_cuisine ON;
INSERT INTO sch_food_mgmt.food_cuisine(id, type) VALUES (1, 'American'), (2,'Chinese'), (3, 'Italian'), (4, 'Japanese');
SET IDENTITY_INSERT sch_food_mgmt.food_cuisine OFF;

SET IDENTITY_INSERT sch_reservation.reservation_status ON;
INSERT INTO sch_reservation.reservation_status(id, name) VALUES (1, 'Reserved'), (2,'Confirm'), (3, 'Canceled');
SET IDENTITY_INSERT sch_reservation.reservation_status OFF;

SET IDENTITY_INSERT sch_reservation.reservation_type ON;
INSERT INTO sch_reservation.reservation_type(id, type) VALUES (1, 'Banquet Hall'), (2,'Restaurant');
SET IDENTITY_INSERT sch_reservation.reservation_type OFF;

SET IDENTITY_INSERT sch_event_mgmt.event_type ON;
INSERT INTO sch_event_mgmt.event_type(id, type) VALUES (1, 'Wedding'), (2, 'Party'), (3, 'Cooperate Event');
SET IDENTITY_INSERT sch_event_mgmt.event_type OFF;

SET IDENTITY_INSERT sch_event_mgmt.event_status ON;
INSERT INTO sch_event_mgmt.event_status(id, name) VALUES (1, 'Pending'), (2, 'Done'), (3, 'Canceled'), (4, 'Deleted');
SET IDENTITY_INSERT sch_event_mgmt.event_status OFF;

SET IDENTITY_INSERT sch_customer_mgmt.customer_status ON;
INSERT INTO sch_customer_mgmt.customer_status(id, name) VALUES (1, 'Active'), (2,'Inactive');
SET IDENTITY_INSERT sch_customer_mgmt.customer_status OFF;

SET IDENTITY_INSERT sch_customer_mgmt.customer_type ON;
INSERT INTO sch_customer_mgmt.customer_type(id, type) VALUES (1, 'Private Customer'), (2,'Co-Operative Customer');
SET IDENTITY_INSERT sch_customer_mgmt.customer_type OFF;

SET IDENTITY_INSERT sch_restaurant_mgmt.restaurant_status ON;
INSERT INTO sch_restaurant_mgmt.restaurant_status(id, name) VALUES (1, 'Available'), (2, 'Reserved');
SET IDENTITY_INSERT sch_restaurant_mgmt.restaurant_status OFF;

SET IDENTITY_INSERT sch_restaurant_mgmt.restaurant_type ON;
INSERT INTO sch_restaurant_mgmt.restaurant_type(id, type) VALUES (1, 'Table'), (2, 'Dining Room');
SET IDENTITY_INSERT sch_restaurant_mgmt.restaurant_type OFF;