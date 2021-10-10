SET IDENTITY_INSERT sch_customer_mgmt.customer ON;
INSERT INTO sch_customer_mgmt.customer (id, name, email, address, nic, status, type_id)VALUES (1, 'Fazil', 'hashans95@gmail.com,dilshanushara16@gmail.com,promodarvinda95@gmail.com,mfazilm98@gmail.com,gayanpj1996@gmail.com,avishkafernanado@gmail.com', 'colombo', '9820368310V', 1, 1);
SET IDENTITY_INSERT sch_customer_mgmt.customer OFF;

INSERT INTO sch_customer_mgmt.customer_contact(customer_id, contact_number)VALUES (1, '0778761179');

SET IDENTITY_INSERT sch_banquet_hall_mgmt.banquet_hall ON;
INSERT INTO sch_banquet_hall_mgmt.banquet_hall (id, capacity, name, status, type_id) VALUES (1, 300, 'Grand Ballroom', 1, 1), (2, 120, 'Royal Ballroom', 1, 2), (3, 250, 'Tuscany Ballroom', 2, 1);
SET IDENTITY_INSERT sch_banquet_hall_mgmt.banquet_hall OFF;

SET IDENTITY_INSERT sch_event_mgmt.event ON;
INSERT INTO sch_event_mgmt.event(id, type_id, description, event_from, event_to, customer_id, location_id, status)VALUES (1, 1, 'Gayan''s Wedding', '2019-09-04 08:00:00', '2019-09-04 17:00:00', 1, 1, 1);
SET IDENTITY_INSERT sch_event_mgmt.event OFF;

SET IDENTITY_INSERT sch_reservation.reservation ON;
INSERT INTO sch_reservation.reservation (id, reservation_no, reserved_on, customer_id, event_id, status, type_id,user_id)VALUES (1, 'RES6460208577', '2019-08-26 13:00:52', 1, 1, 1, 1, 1);
SET IDENTITY_INSERT sch_reservation.reservation OFF;

SET IDENTITY_INSERT sch_payment.payment ON;
INSERT INTO sch_payment.payment(id,amount, paid_on, reference, pay_method, reservation_id,status, accept_by)VALUES (1, 50000, '2019-08-26 13:00:52', 'INV6460208577', 1, 1, 2, 1),(2,100993, '2019-08-26 13:00:52', 'INV9607139158', 1, 1, 1, 1),(3,296483, '2019-08-26 13:00:52', 'INV8079201389', 1, 1, 1, 1),(4,133254, '2019-08-26 13:00:52', 'INV8334475647', 1, 1, 2, 1),(5,468712, '2019-08-26 13:00:52', 'INV5411748857', 1, 1, 1, 1),(6,492440, '2019-08-26 13:00:52', 'INV3184974069', 1, 1, 1, 1),(7,216773, '2019-08-26 13:00:52', 'INV6799557173', 1, 1, 2, 1),(8,239882, '2019-08-26 13:00:52', 'INV4781339293', 1, 1, 1, 1),(9,214087, '2019-08-26 13:00:52', 'INV2631996650', 1, 1, 1, 1),(10,165481, '2019-08-26 13:00:52', 'INV0183061916', 1, 1, 1, 1),(11,19442, '2019-08-26 13:00:52', 'INV0957778837', 1, 1, 1, 1),(12,444773, '2019-08-26 13:00:52', 'INV5954247557', 1, 1, 1, 1),(13,405470, '2019-08-26 13:00:52', 'INV4230352541', 1, 1, 1, 1),(14,258005, '2019-08-26 13:00:52', 'INV5871854272', 1, 1, 1, 1),(15,420986, '2019-08-26 13:00:52', 'INV8306276336', 1, 1, 1, 1),(16,432067, '2019-08-26 13:00:52', 'INV2801130671', 1, 1, 1, 1),(17,288159, '2019-08-26 13:00:52', 'INV8896731728', 1, 1, 1, 1),(18,186200, '2019-08-26 13:00:52', 'INV4251214183', 1, 1, 1, 1);
SET IDENTITY_INSERT sch_payment.payment OFF;

INSERT INTO sch_payment.payment_refund(refund_on, payment_id, refund_by) VALUES ('2019-08-31 11:00:52', 1, 1), ('2019-08-31 11:00:52', 4, 1), ('2019-08-31 11:00:52', 7, 1);

SET IDENTITY_INSERT sch_food_mgmt.food ON;
INSERT INTO sch_food_mgmt.food (id, name, price,category_id, cuisine_id) VALUES (1, 'Yangzhou Fried Rice', 560.00, 1, 1), (2, 'Sweet Corn Chicken Soup', 235.00, 1, 2), (3, 'Cheese and Chicken Carbonara', 650.00, 2, 3), (4, 'Crab Rice Noodles', 890.00, 3, 4);
SET IDENTITY_INSERT sch_food_mgmt.food OFF;