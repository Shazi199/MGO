CREATE TABLE `stage` (
	`id` INT(11) NOT NULL,
	`parent` INT(11) NOT NULL DEFAULT '0',
	`type` INT(11) NOT NULL,
	`name` VARCHAR(255) NOT NULL DEFAULT '',
	`level` INT(11) NOT NULL DEFAULT '1',
	`config` TEXT NULL,
	PRIMARY KEY (`id`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;
