create table products_tbl (product_id bigserial primary key, title_fld varchar(255), price_fld int);
insert into products_tbl (title_fld, price_fld) values
('Re', 2900),
('StM', 3200),
('LaW', 3500);