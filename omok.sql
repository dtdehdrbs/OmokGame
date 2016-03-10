create table oinfo(
   id varchar2(20) constraint oinfo_id_pk primary key,
   pw varchar2(50) constraint oinfo_pw_nn not null,
   name varchar2(20) constraint oinfo_name_nn not null,
   email varchar2(30) constraint oinfo_email_nn not null,
   phone varchar2(30) constraint oinfo_phone_nn not null,
   birth varchar2(20) constraint oinfo_birth_nn not null,
   indate date,
   grade varchar2(30)
);
create table oboard
(
boardseq number(5) primary key,
boardtitle varchar2(100),
boardwriter varchar2(20),
boardcontents varchar2(4000),
boardtime date,
boardviewcount number(5),
boardtype varchar2(50)
);
create table oreply(
seq number(5),
title varchar2(100),
contents varchar2(4000),
writer varchar2(20),
viewcount number(5),
type varchar2(50),
time date
);
create table orecord(
	userid varchar2(20) ,
	score number(10),
	win number(4),
	los number(4)
);
create table oplayroom(
id varchar2(20),
prseq number(3),
prtitle varchar2(50),
prpopular number(3),
prindate date
);
create table oplay(
	id varchar2(20),
	posX number(3),
	posY number(3),
	status number(3)
);
create sequence brd_seq;

//drop table orecord;
//drop table oboard;
//drop table oinfo;
//drop table oreply;
//drop table oplay;
//drop table oplayroom;
