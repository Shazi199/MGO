ALTER TABLE `user`
	ADD COLUMN `stone` INT NOT NULL DEFAULT '0' AFTER `nickname`;

CREATE TABLE `item` (
	`sid` INT(11) NOT NULL,
	`userid` BIGINT(20) NOT NULL,
	`amount` INT(11) NOT NULL DEFAULT '0',
	PRIMARY KEY (`sid`, `userid`)
)
ENGINE=InnoDB
;

ALTER TABLE `servent`
	ADD COLUMN `skill1level` INT(11) NOT NULL DEFAULT '0' AFTER `skill3`,
	ADD COLUMN `skill2level` INT(11) NOT NULL DEFAULT '0' AFTER `skill1level`,
	ADD COLUMN `skill3level` INT(11) NOT NULL DEFAULT '0' AFTER `skill2level`;
