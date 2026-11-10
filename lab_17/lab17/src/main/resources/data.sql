
insert into product(name, description, price) values ('3d printer', 'A beginner friendly 3d printer', 1840.25);
insert into product(name, description, price) values ('PC computer', 'A gaming laptop', 2300.01);
insert into product(name, description, price) values ('Mobile phone', 'A 5g smartphone', 850.0);
insert into product(name, description, price) values ('Scanner', 'A WIFI scanner', 260.45);
insert into product(name, description, price) values ('Mouse', 'An optical mouse', 65.99);
insert into product(name, description, price) values ('Keyboard', 'A simple keyboard', 105.99);

-- users (passwords are BCrypt hashes): usera / pwd123 , userb / pwd456
insert into users(username, password) values ('usera', '$2a$10$bp9.Z.dBXoYr7/UY9RRX/.6RbKHFq/T0ZaO6.b6WSiTDCbYR/8JFe');
insert into users(username, password) values ('userb', '$2a$10$iFoMlavGokIB8Jyo6aq3ge1MBMmG.i8369BvNDqvCUy/D3hpBoexO');
