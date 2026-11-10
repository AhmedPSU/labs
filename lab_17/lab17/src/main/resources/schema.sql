drop table if exists product;
drop table if exists users;

CREATE table product (
 id int not null auto_increment PRIMARY KEY,
 name varchar(100) not null,
 description varchar(100),
 price DECIMAL(8, 2)
);

CREATE table users (
 id int not null auto_increment PRIMARY KEY,
 username varchar(50) not null UNIQUE,
 password varchar(100) not null
);
