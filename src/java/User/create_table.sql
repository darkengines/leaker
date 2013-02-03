CREATE  TABLE `user` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `email` VARCHAR(64) NOT NULL ,
  `password` VARCHAR(64) NOT NULL ,
  `display_name` VARCHAR(32) NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `iduser_UNIQUE` (`id` ASC) ,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) );