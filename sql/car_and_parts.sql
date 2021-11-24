create table car(
	id serial primary key,
	name varchar(255),
	body int,
	engine int,
	transmission int
);

create table body(
	id serial primary key,
	name varchar(255),
	body_id int references body(id)
);

create table engine(
	id serial primary key,
	name varchar(255),
	engine_id int references engine(id)
);

	create table transmission(
	id serial primary key,
	name varchar(255),
	trans_id int references transmission(id)
);


insert into body(name, body_id) values('SUV', '1');
insert into body(name, body_id) values('Truck', '2');
insert into body(name, body_id) values('Sedan', '3');
insert into body(name, body_id) values('Minivan', '4');

insert into engine(name, engine_id) values('2.0L R4', '5');
insert into engine(name, engine_id) values('3.5L V6', '6');
insert into engine(name, engine_id) values('6.0L V8', '7');
insert into engine(name, engine_id) values('3.5L V6 turbo', '8');
insert into engine(name, engine_id) values('2.0L R4 turbo', '9');

insert into transmission(name, trans_id) values('5 speed auto', '10');
insert into transmission(name, trans_id) values('6 speed auto', '11');
insert into transmission(name, trans_id) values('6 speed robotic', '12');
insert into transmission(name, trans_id) values('5 speed manual', '13');
insert into transmission(name, trans_id) values('6 speed manual', '14');

insert into car(name, body, engine, transmission) values('Tahoe', '1', '7', '11');
insert into car(name, body, engine, transmission) values('F-150', '2', '8', '10');
insert into car(name, body, engine, transmission) values('Camry', '3', '6', '12');
insert into car(name, body, engine, transmission) values('Express', '4', '9', '14');


select
c.name as Car,
b.name as Body,
e.name as Engine,
t.name as Trans
from car c
join body b
on c.body = b.id
join engine e
on c.engine = e.id
join transmission t
on c.transmission = t.id;

select b
from body b
where not exists(select * from car c
where c.body = b.id);

select e
from engine e
where not exists(select * from car c
where c.engine = e.id);

select t
from transmission t
where not exists(select * from car c
where c.transmission = t.id);