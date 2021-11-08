insert into users(name) values ('User 1');
insert into users(name) values ('User 2');
insert into users(name) values ('User 3');

insert into role(name) values ('Admin');
insert into role(name) values ('Common');
insert into role(name) values ('Uncommon');

insert into role_users(role_id, users_id) values (5, 5);
insert into role_users(role_id, users_id) values (5, 6);
insert into role_users(role_id, users_id) values (5, 7);
insert into role_users(role_id, users_id) values (6, 5);
insert into role_users(role_id, users_id) values (6, 6);
insert into role_users(role_id, users_id) values (7, 7);

insert into rules(name) values ('First role');
insert into rules(name) values ('Second role');

insert into role_rules(role_id, rules_id) values (5, 1);
insert into role_rules(role_id, rules_id) values (5, 2);

insert into item(name) values ('Item #');

insert into comments(name) values ('Commentary');

insert into attachs(name) values ('Atts');

insert into category(name) values ('Category_Name');

insert into state(name) values ('Country_Name');




