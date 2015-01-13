DELETE From `myvacation`.`ed_user`;

INSERT INTO `myvacation`.`ed_user` (`UserID`, `FirstName`, `LastName`, `EmailAddress`, `Password`) VALUES (1, 'Bilal', 'Ahmed', 'bilal@ellisdon.com', 'bilal');

INSERT INTO `myvacation`.`ed_user` (`UserID`, `FirstName`, `LastName`, `EmailAddress`, `Password`) VALUES (2, 'John', 'Smith', 'john@ellisdon.com', 'john');


DELETE From users_vacation_history;

INSERT INTO users_vacation_history
(userid, fromdate, todate, daystaken, daysnottaken, comments)
VALUES
(1, '2006-11-24', '2007-01-05', 28, 0, "London Vacation");

INSERT INTO users_vacation_history
(userid, fromdate, todate, daystaken, daysnottaken, comments)
VALUES
(1, '2009-06-15', '2009-06-19', 5, 0, "Paris Vacation");

INSERT INTO users_vacation_history
(userid, fromdate, todate, daystaken, daysnottaken, comments)
VALUES
(1, '2009-12-13', '2009-12-17', 5, 0, "US Vacation");

INSERT INTO users_vacation_history
(userid, fromdate, todate, daystaken, daysnottaken, comments)
VALUES
(1, '2010-05-21', '2010-05-21', 1, 0, "Cuba Vacation");

INSERT INTO users_vacation_history
(userid, fromdate, todate, daystaken, daysnottaken, comments)
VALUES
(1, '2010-09-15', '2010-09-15', 0.5, 0, "Doctor Appointment");

INSERT INTO users_vacation_history
(userid, fromdate, todate, daystaken, daysnottaken, comments)
VALUES
(1, '2010-04-21', '2010-04-21', 0.5, 0, "Dentist Visit");


INSERT INTO users_vacation_history
(userid, fromdate, todate, daystaken, daysnottaken, comments)
VALUES
(2, '2010-09-15', '2010-09-15', 0.5, 0, "Doctor Appointment");

INSERT INTO users_vacation_history
(userid, fromdate, todate, daystaken, daysnottaken, comments)
VALUES
(2, '2010-04-21', '2010-04-21', 0.5, 0, "Dentist Visit");

INSERT INTO users_vacation_history
(userid, fromdate, todate, daystaken, daysnottaken, comments)
VALUES
(2, '2009-06-15', '2009-06-19', 5, 0, "Time off");

INSERT INTO users_vacation_history
(userid, fromdate, todate, daystaken, daysnottaken, comments)
VALUES
(2, '2009-12-13', '2009-12-17', 5, 0, "US Vist");

DELETE From users_vacation_entitlement;

INSERT INTO users_vacation_entitlement
(userid, daysentitle, carryover, lastupdated)
VALUES
(1, 20, 12.5, '2010-03-01');

INSERT INTO users_vacation_entitlement
(userid, daysentitle, carryover, lastupdated)
VALUES
(2, 15, 16, '2010-01-01');

