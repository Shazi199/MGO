CREATE TABLE `user` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`username` VARCHAR(64) NOT NULL,
	`userpass` VARCHAR(64) NOT NULL,
	`email` VARCHAR(255) NOT NULL,
	`nickname` VARCHAR(64) NOT NULL,
	PRIMARY KEY (`id`),
	UNIQUE INDEX `username` (`username`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;
CREATE TABLE `servent` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`userid` BIGINT(20) NOT NULL,
	`sid` INT(11) NOT NULL,
	`level` INT(11) NOT NULL DEFAULT '1',
	`phase` INT(11) NOT NULL DEFAULT '1',
	`atk` INT(11) NOT NULL DEFAULT '0',
	`health` INT(11) NOT NULL DEFAULT '0',
	`noble` INT(11) NOT NULL DEFAULT '1',
	`exp` INT(11) NOT NULL DEFAULT '0',
	`skill1` INT(11) NOT NULL DEFAULT '0',
	`skill2` INT(11) NOT NULL DEFAULT '0',
	`skill3` INT(11) NOT NULL DEFAULT '0',
	PRIMARY KEY (`id`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;
CREATE TABLE `craft` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`userid` BIGINT(20) NOT NULL,
	`sid` INT(11) NOT NULL,
	`level` INT(11) NOT NULL DEFAULT '1',
	`phase` INT(11) NOT NULL DEFAULT '0',
	PRIMARY KEY (`id`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;
