CREATE  TABLE `session` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `user_id` BIGINT NOT NULL ,
  `token` VARCHAR(64) NOT NULL ,
  `date` TIMESTAMP NOT NULL ,
  `expired` INTEGER(1) NOT NULL, 
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC)
);