CREATE TABLE `team` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`userid` BIGINT(20) NULL DEFAULT NULL,
	`teamname` VARCHAR(50) NOT NULL,
	PRIMARY KEY (`id`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;
CREATE TABLE `team_member` (
	`teamid` BIGINT(20) NOT NULL,
	`serventid` BIGINT(20) NOT NULL,
	`order` INT(11) NOT NULL,
	PRIMARY KEY (`teamid`, `order`),
	UNIQUE INDEX `teamid_serventid` (`teamid`, `serventid`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;
