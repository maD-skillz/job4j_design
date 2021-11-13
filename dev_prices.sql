create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    devices_id int references devices(id),
    people_id int references people(id)
);

insert into devices(name, price) values('Watch', '4700');
insert into devices(name, price) values('Phone', '18000');
insert into devices(name, price) values('Notebook', '70000');

insert into people(name) values('Иван');
insert into people(name) values('Игнат');
insert into people(name) values('Сифон');

insert into devices_people(devices_id, people_id) values(1, 1);
insert into devices_people(devices_id, people_id) values(1, 2);
insert into devices_people(devices_id, people_id) values(1, 3);
insert into devices_people(devices_id, people_id) values(2, 1);
insert into devices_people(devices_id, people_id) values(2, 3);
insert into devices_people(devices_id, people_id) values(3, 2);
insert into devices_people(devices_id, people_id) values(3, 3);

select avg(price) from devices;

select
ppl.name,
avg(dev.price)
from people as ppl,
devices as dev
join devices_people as dppl
on dppl.devices_id = dev.id
group by ppl.name;

select
ppl.name,
avg(dev.price)
from people as ppl,
devices as dev
join devices_people as dppl
on dppl.devices_id = dev.id
group by ppl.name;
having avg(dprice.price) > '5000';