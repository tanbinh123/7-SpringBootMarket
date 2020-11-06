create table profile
(
    id       bigserial,
    name     varchar(80),
    surname  varchar(80),
    phone    varchar(30),
    email    varchar(50) unique,
    birthday varchar(50),
    gender   varchar(20),
    city     varchar(80),
    primary key (id)
);

insert into profile (name, surname, phone, email, birthday, gender, city)
values ('Bob', 'Smith', '+79214450101', 'user@gmail.com', 1984, 'male', 'SPb'),
       ('Adam', 'Smith', '+79214450102', 'admin@gmail.com', 1983, 'male', 'Paris');

create table users
(
    id         bigserial,
    username   varchar(30) not null,
    password   varchar(80) not null,
    profile_id bigint references profile (id),
    primary key (id)
);

insert into users (username, password, profile_id)
values ('user', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 1),
       ('admin', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 2);

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

insert into users_roles (user_id, role_id)
values (1, 1),
       (2, 1),
       (2, 2);

create table categories
(
    id    bigserial primary key,
    title varchar(255)
);

create table products
(
    id          bigserial primary key,
    title       varchar(255),
    category_id bigint references categories (id),
    price       int
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

insert into categories (title)
values ('grocery'),
       ('industrial'),
       ('electronics'),
       ('digital');

insert into products (title, price, category_id)
values ('loaf', 21, 1),
       ('hamburger', 22, 1),
       ('iphone 12', 23, 4),
       ('tv', 24, 3),
       ('tin', 25, 1),
       ('brick', 26, 2),
       ('marmalade', 27, 1),
       ('butter', 28, 1),
       ('honey', 29, 1),
       ('milk', 31, 1),
       ('olives', 32, 1),
       ('pepper', 33, 1),
       ('biscuit', 34, 1),
       ('sugar', 35, 1),
       ('cream', 36, 1),
       ('salt', 37, 1),
       ('bread', 38, 1),
       ('eggs', 39, 1),
       ('chocolate', 40, 1),
       ('loaf1', 121, 1),
       ('hamburger1', 122, 1),
       ('yoghurt1', 123, 1),
       ('pastry1', 124, 1),
       ('tin1', 125, 1),
       ('candy1', 126, 1),
       ('marmalade1', 127, 1),
       ('butter1', 128, 1),
       ('honey1', 129, 1),
       ('milk1', 131, 1),
       ('olives1', 132, 1),
       ('pepper1', 133, 1),
       ('biscuit1', 134, 1),
       ('sugar1', 135, 1),
       ('cream1', 136, 1),
       ('salt1', 137, 1),
       ('bread1', 138, 1),
       ('eggs1', 139, 1),
       ('chocolate1', 140, 1);