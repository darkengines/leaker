CREATE  TABLE `friend` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `left_user_id` INT NOT NULL ,
  `right_user_id` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) );