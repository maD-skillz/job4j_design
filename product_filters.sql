create table product(
	id serial primary key,
	name varchar(255),
        type int,
	expired_date date,
	price int);
	
create table type(
	id serial primary key,
	name varchar(255),
	type_id int references type(id));


insert into type(name, type_id) values('СЫР', '1');
insert into type(name, type_id) values('МОЛОКО', '2');
insert into type(name, type_id) values('МОРОЖЕНОЕ', '3');

insert into product(name, type, expired_date, price) values('пармезан', '1', '29.11.2021', '500');
insert into product(name, type, expired_date, price) values('плавленный', '1', '05.12.2022', '400');
insert into product(name, type, expired_date, price) values('топлёное', '2', '15.11.2021', '80');
insert into product(name, type, expired_date, price) values('натуральное', '2', '01.11.2021', '100');
insert into product(name, type, expired_date, price) values('мороженое пломбир', '3', '14.01.2022', '120');
insert into product(name, type, expired_date, price) values('мороженое рожок', '3', '12.02.2022', '35');

select * from 
product
where type = '1';

select * from 
product
where name LIKE '%мороженое%';

select * from 
product
where current_date > expired_date;

select MAX(price)
from product;

select
count(t.name),
t.name
from product as p
join type as t
on p.type = t.id
where t.name = 'СЫР' or t.name = 'МОЛОКО' or t.name = 'МОРОЖЕНОЕ'
group by t.name;

select 
p.name
from
product as p
join type as t
on p.type = t.id
where t.name = 'СЫР' or t.name = 'МОЛОКО';

select
count(t.name) < 10,
t.name
from product as p
join type as t
on p.type = t.id
where t.name = 'СЫР' or t.name = 'МОЛОКО' or t.name = 'МОРОЖЕНОЕ'
group by t.name;

select 
p.name,
t.name
from
product as p
join type as t
on p.type = t.id;