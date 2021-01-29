create table products_tbl (
    product_id      bigserial primary key,
    title_fld       varchar(255),
    price_fld       int,
    created_at       timestamp default current_timestamp,
    updated_at       timestamp default current_timestamp
    );
insert into products_tbl (title_fld, price_fld) values
('Re', 2900),
('StM', 3200),
('LaW1', 3500),
('LaW2', 3700),
('LaM1', 6500),
('Re2', 4600),
('StW3', 5900),
('StM2', 6000),
('LaW', 6200),
('StW1', 3500),
('StW2', 6500),
('LaM', 3500);