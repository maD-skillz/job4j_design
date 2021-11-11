create table vehicle1(
	id serial primary key,
	vin_num int,
	chars varchar(255)
);

create table cars1(
	id serial primary key,
	name varchar(255),
	vehicle1_id int references vehicle1(id) unique
);

insert into vehicle1(vin_num, chars) values ('000001', 'AAA');
insert into vehicle1(vin_num, chars) values ('000002', 'BBB');
insert into vehicle1(vin_num, chars) values ('000003', 'CCC');

insert into cars1(name, vehicle1_id) values ('Toyota', 1);
insert into cars1(name, vehicle1_id) values ('Ford', 2);
insert into cars1(name, vehicle1_id) values ('Tesla', 3);
insert into cars1(name) values ('Buick');

select c.name, v.vin_num, v.chars from cars1 as c join vehicle1 as v on c.vehicle1_id = v.id;

select * from cars1 inner join vehicle1 v on cars1.vehicle1_id = v.id;

select v.chars, c.name from vehicle1 as v join cars1 as c on c.vehicle1_id = v.id;
