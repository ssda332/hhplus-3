INSERT INTO product (id, name, price, create_date) VALUES (1, '옷1', 1000, NOW());
INSERT INTO product_option (id, product_id, name, create_date) VALUES (1, 1, '레드', NOW());
INSERT INTO product_option (id, product_id, name, create_date) VALUES (2, 1, '블랙', NOW());
INSERT INTO product_option (id, product_id, name, create_date) VALUES (3, 1, '그레이', NOW());
INSERT INTO product_option_stock (id, stock, product_option_id) VALUES (1, 100, 1);
INSERT INTO product_option_stock (id, stock, product_option_id) VALUES (2, 100, 2);
INSERT INTO product_option_stock (id, stock, product_option_id) VALUES (3, 100, 3);
INSERT INTO product (id, name, price, create_date) VALUES (2, '옷2', 2000, NOW());
INSERT INTO product_option (id, product_id, name, create_date) VALUES (4, 2, '레드', NOW());
INSERT INTO product_option (id, product_id, name, create_date) VALUES (5, 2, '블랙', NOW());
INSERT INTO product_option (id, product_id, name, create_date) VALUES (6, 2, '그레이', NOW());
INSERT INTO product_option_stock (id, stock, product_option_id) VALUES (4, 100, 4);
INSERT INTO product_option_stock (id, stock, product_option_id) VALUES (5, 100, 5);
INSERT INTO product_option_stock (id, stock, product_option_id) VALUES (6, 100, 6);

INSERT INTO balance (id, member_id, amount) VALUES (1, 1, 10000);
INSERT INTO balance (id, member_id, amount) VALUES (2, 2, 20000);
INSERT INTO balance (id, member_id, amount) VALUES (3, 3, 30000);
INSERT INTO balance (id, member_id, amount) VALUES (4, 4, 40000);
INSERT INTO balance (id, member_id, amount) VALUES (5, 5, 50000);
INSERT INTO balance (id, member_id, amount) VALUES (6, 6, 60000);
INSERT INTO balance (id, member_id, amount) VALUES (7, 7, 70000);
INSERT INTO balance (id, member_id, amount) VALUES (8, 8, 80000);
INSERT INTO balance (id, member_id, amount) VALUES (9, 9, 90000);
INSERT INTO balance (id, member_id, amount) VALUES (10, 10, 1000000);

INSERT INTO order_sheet (id, member_id, address, phone, comment, payment_status, create_date) VALUES (1, 1, '서울시 송파구 백제고분로', '010-1234-5678', '부재시 문앞', 'PENDING', NOW());
INSERT INTO order_sheet (id, member_id, address, phone, comment, payment_status, create_date) VALUES (2, 2, '서울시 송파구 백제고분로', '010-1234-5678', '부재시 문앞', 'PENDING', NOW());
INSERT INTO order_sheet (id, member_id, address, phone, comment, payment_status, create_date) VALUES (3, 3, '서울시 송파구 백제고분로', '010-1234-5678', '부재시 문앞', 'PENDING', NOW());
INSERT INTO order_sheet (id, member_id, address, phone, comment, payment_status, create_date) VALUES (4, 4, '서울시 송파구 백제고분로', '010-1234-5678', '부재시 문앞', 'PENDING', NOW());
INSERT INTO order_sheet (id, member_id, address, phone, comment, payment_status, create_date) VALUES (5, 5, '서울시 송파구 백제고분로', '010-1234-5678', '부재시 문앞', 'PENDING', NOW());
INSERT INTO order_sheet (id, member_id, address, phone, comment, payment_status, create_date) VALUES (6, 6, '서울시 송파구 백제고분로', '010-1234-5678', '부재시 문앞', 'PENDING', NOW());
INSERT INTO order_sheet (id, member_id, address, phone, comment, payment_status, create_date) VALUES (7, 7, '서울시 송파구 백제고분로', '010-1234-5678', '부재시 문앞', 'PENDING', NOW());
INSERT INTO order_sheet (id, member_id, address, phone, comment, payment_status, create_date) VALUES (8, 8, '서울시 송파구 백제고분로', '010-1234-5678', '부재시 문앞', 'PENDING', NOW());
INSERT INTO order_sheet (id, member_id, address, phone, comment, payment_status, create_date) VALUES (9, 9, '서울시 송파구 백제고분로', '010-1234-5678', '부재시 문앞', 'PENDING', NOW());
INSERT INTO order_sheet (id, member_id, address, phone, comment, payment_status, create_date) VALUES (10, 10, '서울시 송파구 백제고분로', '010-1234-5678', '부재시 문앞', 'PENDING', NOW());

INSERT INTO order_sheet_item (id, order_sheet_id, product_id, product_name, product_option_id, product_option_name, product_count, product_price, create_date) VALUES (1, 1, 1, '옷1', 1, '레드', 10, 1000, NOW());
INSERT INTO order_sheet_item (id, order_sheet_id, product_id, product_name, product_option_id, product_option_name, product_count, product_price, create_date) VALUES (2, 2, 1, '옷1', 1, '레드', 10, 1000, NOW());
INSERT INTO order_sheet_item (id, order_sheet_id, product_id, product_name, product_option_id, product_option_name, product_count, product_price, create_date) VALUES (3, 3, 1, '옷1', 1, '레드', 10, 1000, NOW());
INSERT INTO order_sheet_item (id, order_sheet_id, product_id, product_name, product_option_id, product_option_name, product_count, product_price, create_date) VALUES (4, 4, 1, '옷1', 1, '레드', 10, 1000, NOW());
INSERT INTO order_sheet_item (id, order_sheet_id, product_id, product_name, product_option_id, product_option_name, product_count, product_price, create_date) VALUES (5, 5, 1, '옷1', 1, '레드', 10, 1000, NOW());
INSERT INTO order_sheet_item (id, order_sheet_id, product_id, product_name, product_option_id, product_option_name, product_count, product_price, create_date) VALUES (6, 6, 1, '옷1', 1, '레드', 10, 1000, NOW());
INSERT INTO order_sheet_item (id, order_sheet_id, product_id, product_name, product_option_id, product_option_name, product_count, product_price, create_date) VALUES (7, 7, 1, '옷1', 1, '레드', 10, 1000, NOW());
INSERT INTO order_sheet_item (id, order_sheet_id, product_id, product_name, product_option_id, product_option_name, product_count, product_price, create_date) VALUES (8, 8, 1, '옷1', 1, '레드', 10, 1000, NOW());
INSERT INTO order_sheet_item (id, order_sheet_id, product_id, product_name, product_option_id, product_option_name, product_count, product_price, create_date) VALUES (9, 9, 1, '옷1', 1, '레드', 10, 1000, NOW());
INSERT INTO order_sheet_item (id, order_sheet_id, product_id, product_name, product_option_id, product_option_name, product_count, product_price, create_date) VALUES (10, 10, 1, '옷1', 1, '레드', 10, 1000, NOW());

