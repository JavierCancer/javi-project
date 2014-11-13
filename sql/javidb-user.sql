drop user 'libros'@'localhost';
create user 'libros'@'localhost' identified by 'libros';
grant all privileges on javidb.* to 'libros'@'localhost';
flush privileges;