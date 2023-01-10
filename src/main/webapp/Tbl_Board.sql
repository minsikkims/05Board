use shopdb;
create table tbl_board
(
	no int not null auto_increment primary key,
	email varchar(100),
	subject varchar(50),
	content text,
	regdate date,
	count int unsigned,
	dirpath varchar(255),
	filename varchar(255),
	filesize varchar(100)
);