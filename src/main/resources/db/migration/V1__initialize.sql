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
    customer_id             bigint references customers(id),
    product_id              bigint references products(id),
    current_price           int
);

insert into customers (name)
values
('Bob'),
('John'),
('Jack');

insert into products (title, price)
values
('Bread1', 21),
('Bread2', 22),
('Bread3', 23),
('Bread4', 24),
('Bread5', 25),
('Bread6', 26),
('Bread7', 27),
('Bread8', 28),
('Bread9', 29),
('Bread10', 31),
('Bread11', 32),
('Bread12', 33),
('Bread13', 34),
('Bread14', 35),
('Bread15', 36),
('Bread16', 37),
('Bread17', 38),
('Bread18', 39),
('Bread19', 40);


insert into orders (customer_id, product_id, current_price)
values
(1, 1, 24);