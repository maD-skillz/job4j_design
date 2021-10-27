create table example_cars1(
	id serial primary key,
	name varchar(255),
	nameOf text,
	rangeOf int,
	isTruck boolean
);

insert into example_cars1(name, nameOf, rangeOf, isTruck) 
values('Chevy', 'Tahoe', 400, false);

select * from example_cars1;

update example_cars1 set nameOf = 'Suburban';

delete from example_cars1;