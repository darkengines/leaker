INSERT INTO `user` (`id`, `email`, `password`, `display_name`, `token`) VALUES (?, ?, ?, ?, ?)
    ON DUPPLICATE UPDATE `id`=?,`email`=?, `password`=?, `display_name`=?;