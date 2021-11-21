create table departments(
id serial primary key,
name varchar(255)
);

create table employe(
id serial primary key,
name varchar(255),
dep_id int references departments(id)
);

insert into departments(name) values('Main Department');
insert into departments(name) values('Middle Department');
insert into departments(name) values('Lower Department');

insert into employe(name, dep_id) values('Jack', 1);
insert into employe(name, dep_id) values('Nick', 2);
insert into employe(name, dep_id) values('Tom', 3);
insert into employe(name, dep_id) values('Stan', 1);
insert into employe(name, dep_id) values('Sam', 3);
insert into employe(name, dep_id) values('John', 2);

select * from
departments d 
left join employe e 
on e.dep_id = d.id;

select * from
employe e
right join departments d
on d.id = e.dep_id;

select * from
departments d 
left join employe e 
on e.dep_id = d.id
where e.dep_id is null;

select * from
employe e
full join departments d
on e.dep_id = d.id;

select * from
employe e
cross join departments d;



