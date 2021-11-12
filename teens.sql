create table gender(
id serial primary key,
name varchar(255)
);

create table names(
id serial primary key,
name varchar(255),
gndr_id int references gender(id)
);

insert into gender(name) values('male'), ('female');

insert into names(name) values('Sasha'), ('Masha'), ('Dmitry'), ('Stas');

select * from
gender g
cross join
names s;