CREATE  TABLE `user` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `login` VARCHAR(32) NOT NULL ,
  `password` VARCHAR(64) NOT NULL ,
  `lastname` VARCHAR(32) NULL ,
  `firstname` VARCHAR(32) NULL ,
  `date_in` TIMESTAMP NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  UNIQUE INDEX `login_UNIQUE` (`login` ASC) );