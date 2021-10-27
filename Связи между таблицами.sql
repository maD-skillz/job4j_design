-----------------------ONE_TO_ONE------------------------------------
create table vin_number(
    id serial primary key,
    name varchar(255),
	serial_number int
);

create table car(
    id serial primary key,
    name varchar(255),
	vin_number char,
    position_id int references vin_number(id) unique
);

insert into vin_number(name) values ('GNEK10T00001');
insert into car(name, vin_number) VALUES ('Ford', 1);

select * from car;

select * from vin_number where id in (select id from car);

---------------------MANY_TO_ONE------------------------------------

create table bank(
    id serial primary key,
    name varchar(255)
);

create table clients(
    id serial primary key,
    name varchar(255),
    bank_id int references bank(id)
);

insert into bank(name) values ('National Bank');
insert into clients(name, bank_id) VALUES ('Client', 1);

select * from clients;

select * from bank where id in (select id from clients);

-------------------MANY_TO_MANY--------------------------

create table cars(
    id serial primary key,
    name varchar(255)
);

create table colors(
    id serial primary key,
    name varchar(255)
);

create table cars_colors(
    id serial primary key,
    cars_id int references cars(id),
    colors_id int references colors(id)
);

insert into cars(name) values ('BMW');
insert into cars(name) values ('AUDI');
insert into cars(name) values ('WV');

insert into colors(name) values ('BLACK');
insert into colors(name) values ('RED');
insert into colors(name) values ('BLUE');

insert into cars_colors(cars_id, colors_id) values (1, 1);
insert into cars_colors(cars_id, colors_id) values (1, 2);
insert into cars_colors(cars_id, colors_id) values (1, 3);
insert into cars_colors(cars_id, colors_id) values (2, 1);
insert into cars_colors(cars_id, colors_id) values (2, 2);
insert into cars_colors(cars_id, colors_id) values (3, 3);
