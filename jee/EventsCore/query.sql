CREATE TABLE event(id identity, name varchar, price decimal, eventdate timestamp);
ALTER TABLE event add column(dtype varchar);
UPDATE event set dtype='Event';
CREATE TABLE team (id identity, name varchar);
ALTER TABLE event add column(team1 bigint, team2 bigint);

insert into team (name) values ('Juventus');
insert into team (name) values ('Milan');
insert into team (name) values ('Roma');
insert into team (name) values ('Napoli');

CREATE TABLE cart(id identity, owner varchar,);
CREATE TABLE cartItem(id identity, quantity int, cartId bigint, eventid bigint);
