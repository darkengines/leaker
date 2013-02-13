CREATE  TABLE `comment` (

  `id` INT NOT NULL AUTO_INCREMENT ,

  `content` TEXT NULL ,

  `user_id` INT NULL ,

  `message_id` INT NULL ,

  PRIMARY KEY (`id`) ,

  UNIQUE INDEX `id_UNIQUE` (`id` ASC) );