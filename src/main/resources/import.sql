insert into Auditorium (id, name, numberOfSeats, vipSeats) values (1, 'RED', 30, '20,21,22');
insert into Auditorium (id, name, numberOfSeats, vipSeats) values (2, 'GREEN', 30, '20,21,22');
insert into Event (id, airDate, basePrice, name, rating, auditorium_id) values (3, '2020-06-15 19:30:00', 31, 'Terminator 6', 1, 1);

--all password is 'password'
insert into User (id, birthdate, description, email, firstName, lastName, luckyUser, password, roles, login) values (5, '2007-12-03', 'descr', 'a@a.com', 'Foo', 'Bar', true, '{bcrypt}$2a$10$UhrAive4eAYPxq/ALXXgR.zWE5uBXbBgf1rCf7EOLSUON/Yev6BJC', 'REGISTERED_USER,BOOKING_MANAGER', 'user1');
insert into User (id, birthdate, description, email, firstName, lastName, luckyUser, password, roles, login) values (6, '2009-12-03', 'descr1', 'adf@a.com', 'Fertt', 'Err', true, '{bcrypt}$2a$10$UhrAive4eAYPxq/ALXXgR.zWE5uBXbBgf1rCf7EOLSUON/Yev6BJC', 'REGISTERED_USER', 'user2');
insert into User (id, birthdate, description, email, firstName, lastName, luckyUser, password, roles, login) values (7, '2010-12-03', 'descr2', 'qqa@a.com', 'Fqqq', 'Tar', true, '{bcrypt}$2a$10$UhrAive4eAYPxq/ALXXgR.zWE5uBXbBgf1rCf7EOLSUON/Yev6BJC', 'REGISTERED_USER', 'user3');
--all password is 'password'

insert into Ticket (id, dateTime, seat, event_id, user_id, price) values (8, '2020-06-15 19:30:00', 4, 3, 5, 22);
insert into Ticket (id, dateTime, seat, event_id, user_id, price) values (9, '2020-03-15 20:30:00', 3, 3, 6, 30);

insert into UserAccount (id, user_id, amount) values (8, 5, 100);