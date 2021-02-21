--create database api;


drop table if exists user_roles , users, roles;

create table if not exists users (
      id serial,
      email varchar(255),
      login varchar(255),
      nome varchar(255),
      senha varchar(255),
      primary key(id)
);

create table if not exists roles (
   	id serial,
    nome varchar(255),
    primary key (id)
);
 
--drop table user_roles , users , roles ;

create table if not exists user_roles (
   user_id bigint not null,
   role_id bigint not null,

   
   FOREIGN KEY (role_id) REFERENCES roles (id),     
   FOREIGN KEY (user_id) REFERENCES users (id)   
   
);


insert into roles(id,nome) values (1, 'ROLE_USER');
insert into roles(id,nome) values (2, 'ROLE_ADMIN');


insert into users(nome,email,login,senha) 
values ('admin','admin','admin','$2a$10$8zSgyezz49zhbQlch/4OEer5zozuk0o5/P2ZhHz3voa9JsLqpS8tK');

insert into users(nome,email,login,senha)
values ('user','user','user','$2a$10$lyOOmAl2ytE76ogvsKrKMuc/rV/znQrHZqB1fgRwTzXy2p2Sz8.xi');

insert into user_roles(user_id,role_id) values(1, 1);
insert into user_roles(user_id,role_id) values(1, 2);
insert into user_roles(user_id,role_id) values(2, 1);