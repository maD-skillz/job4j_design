create table teens (
	name varchar (255),
	gender varchar (255)
);

insert into teens(name, gender) values ('Petr', 'Male');
insert into teens(name, gender) values ('Nick', 'Male');
insert into teens(name, gender) values ('Dmitry', 'Male');
insert into teens(name, gender) values ('Mary', 'Female');
insert into teens(name, gender) values ('Kate', 'Female');
insert into teens(name, gender) values ('Darya', 'Female');

select * from teens t1
cross join teens t2
where t1.gender <> t2.gender;