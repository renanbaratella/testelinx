select * from tb_customer;
select * from tb_phone;
select * from tb_address;
select * from tb_shipping;
select * from tb_order;
select * from tb_itens;
select * from tb_charge;

insert into tb_phone(home_phone, mobile_phone) values ("551123232222", "5511954432311");
insert into tb_shipping(amount, description, estimated_delivery_date, max_delivery_date, recipient_name, recipient_phone, type) values (100, "Express Shipping", "2022-09-22", "2022-10-10", "Tony Stark", "24934332323", 1);
insert into tb_address(city, country, line_1, line_2, state, status, zip_code, shipping_id) values ("sao paulo", "brasil", "22, av. general, Centro", "7° andar, sala 01", "são paulo", "ok", "04433322", 1);
insert into tb_itens (amount, category, code, description, quantity, status) values (22.22, "Calça", "2F", "Calça Jeans", 1, "active");
insert into tb_order(closed, code, currency, status, item_id, shipping_id) values (false, "BRL", 44, 2, 1, 1);
insert into tb_charge (amount, code, due_at, gateway_id, payment_method, status) values (22.22, "2G", "2022-08-03", "5FFF", "Boleto", "paid");
insert into tb_customer(birthdate, code, delinquent, document, document_type, email, fb_id, gender, name, senha, type, address_id, charge_id, order_id, phone_id) values ("1998-02-12", "FF2", false, "33343434434", 0, "renan@gmail.com", 55, "M", "Renan", "12345678", 0, 1, 1, 1, 1);

