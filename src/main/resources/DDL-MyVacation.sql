DROP TABLE IF EXISTS `myvacation`.`ed_user`;
CREATE TABLE `myvacation`.`ed_user` (
  `UserID` int(11) NOT NULL,
  `FirstName` varchar(45) NOT NULL,
  `LastName` varchar(45) NOT NULL,
  `EmailAddress` varchar(45) NOT NULL,
  `Password` varchar(15) NOT NULL,
  PRIMARY KEY (`UserID`)
 ) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `myvacation`.`users_vacation_entitlement`;
CREATE TABLE  `myvacation`.`users_vacation_entitlement` (
  `iduserentitlement` bigint(10) unsigned NOT NULL AUTO_INCREMENT,
  `userid` bigint(10) unsigned NOT NULL,
  `daysentitle` int(10) unsigned NOT NULL,
  `carryover` float NOT NULL,
  `lastupdated` datetime NOT NULL,
  PRIMARY KEY (`iduserentitlement`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `myvacation`.`users_vacation_history`;
CREATE TABLE  `myvacation`.`users_vacation_history` (
  `iduservacation` bigint(10) unsigned NOT NULL AUTO_INCREMENT,
  `userid` bigint(10) unsigned NOT NULL,
  `fromdate` datetime NOT NULL,
  `todate` datetime NOT NULL,
  `daystaken` float NOT NULL,
  `daysnottaken` float NOT NULL,
  `comments` varchar(45) NOT NULL,
  PRIMARY KEY (`iduservacation`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;