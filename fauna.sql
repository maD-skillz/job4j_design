create table fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);

insert into fauna(name, avg_age, discovery_date)
values ('Cat', 12, '01.05.0150');

insert into fauna(name, avg_age, discovery_date)
values ('Dog', 16, '02.05.1050');

insert into fauna(name, avg_age, discovery_date)
values ('Elephant', 70, '25.10.1600');

insert into fauna(name, avg_age, discovery_date)
values ('Hamster', 5, '31.07.1600');

insert into fauna(name, avg_age, discovery_date)
values ('Frog', 3, '30.05.1300');