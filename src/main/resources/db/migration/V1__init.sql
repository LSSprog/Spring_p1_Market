CREATE TABLE users_tbl (
    user_id                 bigserial PRIMARY KEY,
    username_fld            VARCHAR(30) NOT NULL UNIQUE,
    password_fld            VARCHAR(80) NOT NULL,
    email_fld               VARCHAR(255) UNIQUE,
    created_at              TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at              TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE roles_tbl (
    role_id                 bigserial PRIMARY KEY,
    name_fld                VARCHAR(255) NOT NULL UNIQUE,
    created_at              TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at              TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE users_roles_tbl (
    user_id                 bigint NOT NULL REFERENCES users_tbl (user_id),
    role_id                 bigint NOT NULL REFERENCES roles_tbl (role_id),
    PRIMARY KEY (user_id, role_id)
);

INSERT INTO roles_tbl (name_fld)
VALUES
('ROLE_USER'),
('ROLE_ADMIN');

INSERT INTO users_tbl (username_fld, password_fld, email_fld)
VALUES
('user01', '$2y$12$j642UVCOijNFYX7FiKw1yuMBFcrybKrDvitngkK/7Uo1ayKpzQ/TK', 'user01@mail.com'),
('user02', '$2y$12$v97HVmcQeomvHP37l7A2AeYe0oRivmuWdMRsL87xzuDs4w0ioqaJ6', 'user02@mail.com');

INSERT INTO users_roles_tbl (user_id, role_id)
VALUES
(1, 2),
(2, 1);

CREATE TABLE products_tbl (
    product_id       bigserial PRIMARY KEY,
    title_fld        VARCHAR(255),
    price_fld        INT,
    created_at       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at       TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );
INSERT INTO products_tbl (title_fld, price_fld)
VALUES
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

CREATE TABLE orders_tbl (
    order_id         bigserial PRIMARY KEY,
    user_id          bigint REFERENCES users_tbl (user_id),
    total_quan_fld   INT,
    total_cost_fld   INT,
    address_fld      VARCHAR(255),
    created_at       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at       TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE order_items_tbl (
    order_item_id   bigserial PRIMARY KEY,
    order_id        bigint REFERENCES orders_tbl (order_id),
    product_id      bigint REFERENCES products_tbl (product_id),
    title_fld       VARCHAR(255),
    quantity_fld    INT,
    price_fld       INT,
    cost_fld        INT,
    created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

