create table customers (
    id                      bigserial,
    name                    varchar(255) not null,
    primary key (id)
);

create table products (
    id                      bigserial primary key,
    title                   varchar(255),
    price                   int
);

create table orders (
    id                      bigserial primary key,
    customer_id             bigint references customers(id) ON DELETE CASCADE,
    product_id              bigint references products(id) ON DELETE CASCADE,
    current_price           int
);

insert into customers (name)
values
('Bob'),
('John'),
('Jack');

insert into products (title, price)
values
('loaf', 21),
('hamburger', 22),
('yoghurt', 23),
('pastry', 24),
('tin', 25),
('candy', 26),
('marmalade', 27),
('butter', 28),
('honey', 29),
('milk', 31),
('olives', 32),
('pepper', 33),
('biscuit', 34),
('sugar', 35),
('cream', 36),
('salt', 37),
('bread', 38),
('eggs', 39),
('chocolate', 40),
('loaf1', 121),
('hamburger1', 122),
('yoghurt1', 123),
('pastry1', 124),
('tin1', 125),
('candy1', 126),
('marmalade1', 127),
('butter1', 128),
('honey1', 129),
('milk1', 131),
('olives1', 132),
('pepper1', 133),
('biscuit1', 134),
('sugar1', 135),
('cream1', 136),
('salt1', 137),
('bread1', 138),
('eggs1', 139),
('chocolate1', 140);


insert into orders (customer_id, product_id, current_price)
values
(1, 1, 244),
(1, 2, 243),
(2, 2, 242),
(3, 3, 245);