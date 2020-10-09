create table users
(
    id       bigserial,
    username varchar(30) not null,
    password varchar(80) not null,
    email    varchar(50) unique,
    primary key (id)
);

create table roles
(
    id   serial,
    name varchar(50) not null,
    primary key (id)
);

CREATE TABLE users_roles
(
    user_id bigint not null,
    role_id int    not null,
    primary key (user_id, role_id),
    foreign key (user_id) references users (id),
    foreign key (role_id) references roles (id)
);

insert into roles (name)
values ('ROLE_USER'),
       ('ROLE_ADMIN'),
       ('SOMETHING');

insert into users (username, password, email)
values ('user', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user@gmail.com'),
       ('admin', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'admin@gmail.com');

insert into users_roles (user_id, role_id)
values (1, 1),
       (2, 1),
       (2, 2);

create table products
(
    id    bigserial primary key,
    title varchar(255),
    price int
);

create table orders
(
    id            bigserial primary key,
    user_id       bigint references users (id),
    receiver_name varchar(255),
    phone         varchar(255),
    address       varchar(1000),
    price         int
);

create table order_items
(
    id                bigserial primary key,
    product_id        bigint references products (id),
    order_id          bigint references orders (id),
    price             int,
    price_per_product int,
    quantity          int
);

insert into products (title, price)
values ('loaf', 21),
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