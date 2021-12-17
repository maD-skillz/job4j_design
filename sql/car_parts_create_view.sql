create table body(
	id serial primary key,
	name varchar(255)
);

create table engine(
	id serial primary key,
	name varchar(255)
);

	create table transmission(
	id serial primary key,
	name varchar(255)
);

create table car(
	id serial primary key,
	name varchar(255),
	body_id int references body(id),
	engine_id int references engine(id),
	trans_id int references transmission(id)
);

insert into body(name) values('SUV');
insert into body(name) values('Truck');
insert into body(name) values('Sedan');
insert into body(name) values('Minivan');

insert into engine(name) values('2.0L R4');
insert into engine(name) values('3.5L V6');
insert into engine(name) values('6.0L V8');
insert into engine(name) values('3.5L V6 turbo');
insert into engine(name) values('2.0L R4 turbo');

insert into transmission(name) values('5 speed auto');
insert into transmission(name) values('6 speed auto');
insert into transmission(name) values('6 speed robotic');
insert into transmission(name) values('5 speed manual');
insert into transmission(name) values('6 speed manual');

insert into car(name, body_id, engine_id, trans_id) values('Tahoe', 1, 3, 2);
insert into car(name, body_id, engine_id, trans_id) values('F-150', 2, 4, 1);
insert into car(name, body_id, engine_id, trans_id) values('Camry', 3, 2, 3);
insert into car(name, body_id, engine_id, trans_id) values('Express', 4, 5, 5);

create view show_car_body_engine_on_trans_is_6
as select
c.name as Car,
b.name as Body,
e.name as Engine,
t.name as Trans
from car c
join body b
on c.body_id = b.id
join engine e
on c.engine_id = e.id
join transmission t
on c.trans_id = t.id
group by (c.name, b.name, e.name, t.name) having t.name like '6%';