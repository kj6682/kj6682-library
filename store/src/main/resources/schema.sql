drop table items if exists;
create table items(id serial, catalogId varchar(255), status varchar(255));
drop table lendings if exists;
create table lendings(id serial, item decimal, user decimal, startdate date, enddate date, status varchar(255));