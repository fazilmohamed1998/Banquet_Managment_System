INSERT INTO sch_customer_mgmt.customer (id, name, email, address, nic, status, type_id)VALUES (1, 'Mr.Hashan Sandeepa', 'hashans95@gmail.com', 'colombo', '9820368310V', 1, 1);

INSERT INTO sch_customer_mgmt.customer_contact(customer_id, contact_number)VALUES (1, '0778761179');

INSERT INTO sch_banquet_hall_mgmt.banquet_hall (id, capacity, name, status, type_id) VALUES (1, 300, 'Grand Ballroom', 1, 1), (2, 120, 'Royal Ballroom', 1, 2), (3, 250, 'Tuscany Ballroom', 2, 1);

INSERT INTO sch_event_mgmt.event(id, type_id, description, event_from, event_to, customer_id, location_id, status)VALUES (1, 1, 'Gayan\'s Wedding', '2019-09-04 08:00:00', '2019-09-04 17:00:00', 1, 1, 1);

INSERT INTO sch_reservation.reservation (id, reservation_no, reserved_on, customer_id, event_id, status, type_id,user_id)VALUES (1, 'RES6460208577', '2019-08-26 13:00:52', 1, 1, 1, 1, 1);

INSERT INTO sch_payment.payment(id,amount, paid_on, reference, pay_method, reservation_id,status, accept_by)VALUES (1, 50000, '2019-08-26 13:00:52', 'INV6460208577', 1, 1, 2, 1),(2,100993, '2019-08-26 13:00:52', 'INV9607139158', 1, 1, 1, 1),(3,296483, '2019-08-26 13:00:52', 'INV8079201389', 1, 1, 1, 1),(4,133254, '2019-08-26 13:00:52', 'INV8334475647', 1, 1, 2, 1),(5,468712, '2019-08-26 13:00:52', 'INV5411748857', 1, 1, 1, 1),(6,492440, '2019-08-26 13:00:52', 'INV3184974069', 1, 1, 1, 1),(7,216773, '2019-08-26 13:00:52', 'INV6799557173', 1, 1, 2, 1),(8,239882, '2019-08-26 13:00:52', 'INV4781339293', 1, 1, 1, 1),(9,214087, '2019-08-26 13:00:52', 'INV2631996650', 1, 1, 1, 1),(10,165481, '2019-08-26 13:00:52', 'INV0183061916', 1, 1, 1, 1),(11,19442, '2019-08-26 13:00:52', 'INV0957778837', 1, 1, 1, 1),(12,444773, '2019-08-26 13:00:52', 'INV5954247557', 1, 1, 1, 1),(13,405470, '2019-08-26 13:00:52', 'INV4230352541', 1, 1, 1, 1),(14,258005, '2019-08-26 13:00:52', 'INV5871854272', 1, 1, 1, 1),(15,420986, '2019-08-26 13:00:52', 'INV8306276336', 1, 1, 1, 1),(16,432067, '2019-08-26 13:00:52', 'INV2801130671', 1, 1, 1, 1),(17,288159, '2019-08-26 13:00:52', 'INV8896731728', 1, 1, 1, 1),(18,186200, '2019-08-26 13:00:52', 'INV4251214183', 1, 1, 1, 1);

INSERT INTO sch_payment.payment_refund(refund_on, payment_id, refund_by) VALUES ('2019-08-31 11:00:52', 1, 1), ('2019-08-31 11:00:52', 4, 1), ('2019-08-31 11:00:52', 7, 1);

INSERT INTO sch_food_mgmt.food (id, name, price,category_id, cuisine_id) VALUES (1, 'Yangzhou Fried Rice', 560.00, 1, 1), (2, 'Sweet Corn Chicken Soup', 235.00, 1, 2), (3, 'Cheese and Chicken Carbonara', 650.00, 2, 3), (4, 'Crab Rice Noodles', 890.00, 3, 4);

INSERT INTO sch_reservation.reservation(id, reservation_no, reserved_on, customer_id, event_id, status, type_id, user_id)VALUES (2, 'RES6460208578', '2019-08-27 14:30:52', 1, 1, 1, 1, 1),(3, 'RES6460208579', '2019-08-28 14:00:52', 1, 1, 1, 1, 1),(4, 'RES6460208580', '2019-08-29 14:00:52', 1, 1, 2, 1, 1),(5, 'RES6460208581', '2019-08-30 14:00:52', 1, 1, 1, 1, 1),(6, 'RES6460208582', '2019-08-31 14:00:52', 1, 1, 2, 1, 1),(7, 'RES6460208583', '2019-08-31 14:00:52', 1, 1, 3, 1, 1),(8, 'RES6460208584', '2019-09-01 14:00:52', 1, 1, 3, 1, 1),(9, 'RES6460208585', '2019-09-05 14:00:52', 1, 1, 2, 1, 1),(10, 'RES6460208586', '2019-09-06 14:00:52', 1, 1, 2, 1, 1),(11, 'RES6460208587', '2019-09-08 14:00:52', 1, 1, 1, 1, 1),(12, 'RES6460208588', '2019-09-10 14:00:52', 1, 1, 1, 1, 1),(13, 'RES6460208589', '2019-09-10 14:00:52', 1, 1, 1, 1, 1),(14, 'RES6460208590', '2019-09-11 14:00:52', 1, 1, 3, 1, 1),(15, 'RES6460208591', '2019-09-28 14:00:52', 1, 1, 1, 1, 1),(16, 'RES6460208592', '2019-09-28 14:00:52', 1, 1, 2, 1, 1),(17, 'RES6460208593', '2019-10-01 14:00:52', 1, 1, 2, 1, 1),(18, 'RES6460208594', '2019-10-02 14:00:52', 1, 1, 1, 1, 1);