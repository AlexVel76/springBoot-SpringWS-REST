create sequence hibernate_sequence start with 1 increment by 1;
create table Auditorium (id bigint not null, name varchar(255), numberOfSeats bigint not null, vipSeats varchar(255), primary key (id));
create table Event (id bigint not null, airDate varchar(255), basePrice double not null, name varchar(255), rating integer, auditorium_id bigint, primary key (id));
create table Ticket (id bigint not null, dateTime varchar(255), seat bigint not null, event_id bigint, user_id bigint, price double, primary key (id));
create table User (id bigint not null, birthdate varchar(255), description varchar(255), email varchar(255), firstName varchar(255), lastName varchar(255), luckyUser boolean not null, password varchar(255), roles varchar(255), login varchar(255), primary key (id));
alter table Auditorium add constraint UK_gdsnssfmi4e8cbckxg05kgrom unique (name);
alter table Event add constraint FK86b6uglewc0q16km7up6w0udc foreign key (auditorium_id) references Auditorium;
alter table Ticket add constraint FKsotv3qctce5ggaewd002dd71s foreign key (event_id) references Event;
alter table Ticket add constraint FKm1xvucrvrnk55qhem8xmvju6h foreign key (user_id) references User;

create table UserAccount (id bigint not null, user_id bigint, amount double not null, primary key (id));


