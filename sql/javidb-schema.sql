drop database if exists javidb;
create database javidb;

use javidb;

create table users (
	username	varchar(20) not null primary key,
	userpass	char(32) not null
	
);

create table user_roles (
	username			varchar(20) not null,
	rolename			varchar(20) not null,
	
	foreign key(username) references users(username) on delete cascade,
	primary key (username, rolename)
);

create table autores (

	idautor int not null auto_increment primary key,
	autor varchar(20) not null
	foreign key(autor) references users(username)

);

create table stings (
	id						int not null auto_increment primary key,
	titulo					varchar(50) not null,
	autor		 			int not null,
	lengua					varchar(20) not null,
	edicion					varchar(500) not null,
	editorial				varchar(20) not null,
	fecha_edicion			varchar(20) not null,
	fecha_impresion			varchar(20) not null,
	
	foreign key(autor) 	references autores(idautor)
);

create table resenya (
	idresenya		int not null auto_increment primary key,
	idsting			int not null,
	username		varchar(20),
	autor			varchar(20),
	resenya			varchar(500),
	
	foreign key(idresenya) 	references stings(id)
	
);

