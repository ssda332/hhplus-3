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

INSERT INTO `order` (id, member_id, price_sum, address, phone, comment, create_date) VALUES (1, 1, 10000, '서울시 송파구 백제고분로', '010-1234-5678', '부재시 문앞', NOW());
INSERT INTO `order` (id, member_id, price_sum, address, phone, comment, create_date) VALUES (2, 2, 20000, '서울시 송파구 백제고분로', '010-1234-5678', '부재시 문앞', NOW());
INSERT INTO `order` (id, member_id, price_sum, address, phone, comment, create_date) VALUES (3, 3, 30000, '서울시 송파구 백제고분로', '010-1234-5678', '부재시 문앞', NOW());
INSERT INTO `order` (id, member_id, price_sum, address, phone, comment, create_date) VALUES (4, 4, 40000, '서울시 송파구 백제고분로', '010-1234-5678', '부재시 문앞', NOW());
INSERT INTO `order` (id, member_id, price_sum, address, phone, comment, create_date) VALUES (5, 5, 50000, '서울시 송파구 백제고분로', '010-1234-5678', '부재시 문앞', NOW());
INSERT INTO `order` (id, member_id, price_sum, address, phone, comment, create_date) VALUES (6, 6, 60000, '서울시 송파구 백제고분로', '010-1234-5678', '부재시 문앞', NOW());
INSERT INTO `order` (id, member_id, price_sum, address, phone, comment, create_date) VALUES (7, 7, 70000, '서울시 송파구 백제고분로', '010-1234-5678', '부재시 문앞', NOW());
INSERT INTO `order` (id, member_id, price_sum, address, phone, comment, create_date) VALUES (8, 8, 80000, '서울시 송파구 백제고분로', '010-1234-5678', '부재시 문앞', NOW());
INSERT INTO `order` (id, member_id, price_sum, address, phone, comment, create_date) VALUES (9, 9, 90000, '서울시 송파구 백제고분로', '010-1234-5678', '부재시 문앞', NOW());
INSERT INTO `order` (id, member_id, price_sum, address, phone, comment, create_date) VALUES (10, 10, 100000, '서울시 송파구 백제고분로', '010-1234-5678', '부재시 문앞', NOW());

INSERT INTO order_item (id, order_id, product_id, product_name, product_option_id, product_option_name, product_count, product_price, create_date) VALUES (4, 2, 1, '옷1', 1, '레드', 1, 1000, NOW());
INSERT INTO order_item (id, order_id, product_id, product_name, product_option_id, product_option_name, product_count, product_price, create_date) VALUES (5, 2, 2, '옷2', 5, '블랙', 2, 2000, NOW());

INSERT INTO order_item (id, order_id, product_id, product_name, product_option_id, product_option_name, product_count, product_price, create_date) VALUES (6, 3, 1, '옷1', 3, '그레이', 4, 1000, NOW());
INSERT INTO order_item (id, order_id, product_id, product_name, product_option_id, product_option_name, product_count, product_price, create_date) VALUES (7, 3, 2, '옷2', 6, '그레이', 6, 2000, NOW());

INSERT INTO order_item (id, order_id, product_id, product_name, product_option_id, product_option_name, product_count, product_price, create_date) VALUES (8, 4, 1, '옷1', 1, '레드', 7, 1000, NOW());
INSERT INTO order_item (id, order_id, product_id, product_name, product_option_id, product_option_name, product_count, product_price, create_date) VALUES (9, 4, 2, '옷2', 4, '레드', 3, 2000, NOW());

INSERT INTO order_item (id, order_id, product_id, product_name, product_option_id, product_option_name, product_count, product_price, create_date) VALUES (10, 5, 1, '옷1', 2, '블랙', 9, 1000, NOW());
INSERT INTO order_item (id, order_id, product_id, product_name, product_option_id, product_option_name, product_count, product_price, create_date) VALUES (11, 5, 2, '옷2', 5, '블랙', 4, 2000, NOW());

INSERT INTO order_item (id, order_id, product_id, product_name, product_option_id, product_option_name, product_count, product_price, create_date) VALUES (12, 6, 1, '옷1', 3, '그레이', 10, 1000, NOW());
INSERT INTO order_item (id, order_id, product_id, product_name, product_option_id, product_option_name, product_count, product_price, create_date) VALUES (13, 6, 2, '옷2', 6, '그레이', 2, 2000, NOW());

INSERT INTO order_item (id, order_id, product_id, product_name, product_option_id, product_option_name, product_count, product_price, create_date) VALUES (14, 7, 1, '옷1', 1, '레드', 3, 1000, NOW());
INSERT INTO order_item (id, order_id, product_id, product_name, product_option_id, product_option_name, product_count, product_price, create_date) VALUES (15, 7, 2, '옷2', 4, '레드', 5, 2000, NOW());

INSERT INTO order_item (id, order_id, product_id, product_name, product_option_id, product_option_name, product_count, product_price, create_date) VALUES (16, 8, 1, '옷1', 2, '블랙', 6, 1000, NOW());
INSERT INTO order_item (id, order_id, product_id, product_name, product_option_id, product_option_name, product_count, product_price, create_date) VALUES (17, 8, 2, '옷2', 5, '블랙', 7, 2000, NOW());

INSERT INTO order_item (id, order_id, product_id, product_name, product_option_id, product_option_name, product_count, product_price, create_date) VALUES (18, 9, 1, '옷1', 3, '그레이', 8, 1000, NOW());
INSERT INTO order_item (id, order_id, product_id, product_name, product_option_id, product_option_name, product_count, product_price, create_date) VALUES (19, 9, 2, '옷2', 6, '그레이', 9, 2000, NOW());

INSERT INTO order_item (id, order_id, product_id, product_name, product_option_id, product_option_name, product_count, product_price, create_date) VALUES (20, 10, 1, '옷1', 1, '레드', 10, 1000, NOW());
INSERT INTO order_item (id, order_id, product_id, product_name, product_option_id, product_option_name, product_count, product_price, create_date) VALUES (21, 10, 2, '옷2', 4, '레드', 5, 2000, NOW());