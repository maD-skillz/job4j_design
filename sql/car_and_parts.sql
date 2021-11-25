create table car(
	id serial primary key,
	name varchar(255)
);

create table body(
	id serial primary key,
	name varchar(255),
	body_id int references car(id)
);

create table engine(
	id serial primary key,
	name varchar(255),
	engine_id int references car(id)
);

	create table transmission(
	id serial primary key,
	name varchar(255),
	trans_id int references car(id)
);

insert into car(name) values('Tahoe');
insert into car(name) values('F-150');
insert into car(name) values('Camry');
insert into car(name) values('Express');

insert into body(name, body_id) values('SUV', '13');
insert into body(name, body_id) values('Truck', '14');
insert into body(name, body_id) values('Sedan', '15');
insert into body(name, body_id) values('Minivan', '16');

insert into engine(name) values('2.0L R4');
insert into engine(name, engine_id) values('3.5L V6', '15');
insert into engine(name, engine_id) values('6.0L V8', '13');
insert into engine(name, engine_id) values('3.5L V6 turbo', '14');
insert into engine(name, engine_id) values('2.0L R4 turbo', '16');

insert into transmission(name, trans_id) values('5 speed auto', '14');
insert into transmission(name, trans_id) values('6 speed auto', '13');
insert into transmission(name, trans_id) values('6 speed robotic', '15');
insert into transmission(name) values('5 speed manual');
insert into transmission(name, trans_id) values('6 speed manual', '16'););


select
c.name as Car,
b.name as Body,
e.name as Engine,
t.name as Trans
from car c
join body b
on b.body_id = c.id
join engine e
on e.engine_id = c.id
join transmission t
on t.trans_id = c.id;

select b
from body b
where not exists(select * from car c
where b.body_id = c.id);

select e
from engine e
where not exists(select * from car c
where e.engine_id = c.id);

select t
from transmission t
where not exists(select * from car c
where c.transmission = t.id);

select t
from transmission t
where not exists(select * from car c
where t.trans_id = c.id);