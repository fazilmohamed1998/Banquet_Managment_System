INSERT INTO sch_entity.entity_status(id, name) VALUES (1, 'Active'),(2, 'Inactive'),(3, 'Blocked'),(4, 'Deleted');

INSERT INTO sch_entity.role(id, name) VALUES (1, 'Admin'),(2, 'Accountant'),(3, 'Receptionist');

INSERT INTO sch_entity.permission (code, description)VALUES ('APP', 'App Permission'),('BANQUET_HALL_MGMT', 'Banquet Hall Management'),('CUSTOMER_MGMT', 'Customer Management'),('EVENT_MGMT', 'Event Management'),('FOOD_MGMT', 'Food Management'),('PAYMENT', 'Payment'),('RESERVATION', 'Reservation'),('RESTAURANT_MGMT', 'Restaurant Management'),('USER_MGMT', 'User Management');

INSERT INTO sch_entity.role_permission(role_id, permission_id) SELECT id,(SELECT id FROM sch_entity.permission WHERE code = 'APP') FROM sch_entity.role;

INSERT INTO sch_entity.role_permission(role_id, permission_id) SELECT (SELECT id FROM sch_entity.role WHERE name = 'Admin'),id FROM sch_entity.permission WHERE code <> 'APP';

INSERT INTO sch_entity.entity (nic, name, email, address, user_name, password, status)VALUES ('952903683V', 'HaShaN', 'hashans95@gmail.com', 'Nugegoda', 'hashan','$2a$10$b/EswFOoLX81i/zcgRmvHOR//oUX4fTBz9lgnefxqAP0gbSVCSeNG', 1);

INSERT INTO sch_user.user_role(role_id) VALUES ((SELECT id FROM sch_entity.role WHERE name = 'Admin')),((SELECT id FROM sch_entity.role WHERE name = 'Accountant')),((SELECT id FROM sch_entity.role WHERE name = 'Receptionist'));

INSERT INTO sch_user.user(contact_no, epf_no, join_date, entity_id, user_role_id) VALUE ('0718761179', '1002411', CURRENT_DATE, (SELECT id FROM sch_entity.entity WHERE nic = '952903683V'),(SELECT role_id FROM sch_user.user_role WHERE role_id = (SELECT id FROM sch_entity.role WHERE name = 'Admin')));

INSERT INTO sch_payment.payment_method(id, type) VALUES (1, 'Cash'), (2, 'Credit Card'), (3, 'Debit Card'), (4, 'Cheque'), (5, 'Fund Transfer');

INSERT INTO sch_payment.payment_status(id, name) VALUES (1, 'Success'), (2, 'Refund');

INSERT INTO sch_banquet_hall_mgmt.banquet_hall_status(id, name) VALUES (1, 'Available'), (2, 'In Repair');

INSERT INTO sch_banquet_hall_mgmt.banquet_hall_type(id, type) VALUES (1, 'Conference Hall'), (2,'Reception Hall'), (3, 'Other');

INSERT INTO sch_food_mgmt.food_category(id, name) VALUES (1, 'Rice'), (2, 'Noodles'), (3, 'Pasta');

INSERT INTO sch_food_mgmt.food_cuisine(id, type) VALUES (1, 'American'), (2,'Chinese'), (3, 'Italian'), (4, 'Japanese');

INSERT INTO sch_reservation.reservation_status(id, name) VALUES (1, 'Reserved'), (2,'Confirm'), (3, 'Canceled');

INSERT INTO sch_reservation.reservation_type(id, type) VALUES (1, 'Banquet Hall'), (2,'Restaurant');

INSERT INTO sch_event_mgmt.event_type(id, type) VALUES (1, 'Wedding'), (2, 'Party'), (3, 'Cooperate Event');

INSERT INTO sch_event_mgmt.event_status(id, name) VALUES (1, 'Pending'), (2, 'Done'), (3, 'Canceled'), (4, 'Deleted');

INSERT INTO sch_customer_mgmt.customer_status(id, name) VALUES (1, 'Active'), (2,'Inactive');

INSERT INTO sch_customer_mgmt.customer_type(id, type) VALUES (1, 'Private Customer'), (2,'Co-Operative Customer');

INSERT INTO sch_restaurant_mgmt.restaurant_status(id, name) VALUES (1, 'Available'), (2, 'Reserved');

INSERT INTO sch_restaurant_mgmt.restaurant_type(id, type) VALUES (1, 'Table'), (2, 'Dining Room');