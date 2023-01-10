CREATE TABLE `shopdb`.`tbl_member` (
  `email` VARCHAR(50) NOT NULL,
  `pwd` VARCHAR(255) NULL,
  `phone` VARCHAR(45) NULL,
  `zipcode` VARCHAR(45) NULL,
  `addr1` VARCHAR(45) NULL,
  `addr2` VARCHAR(45) NULL,
  `grade` VARCHAR(45) NULL,
  PRIMARY KEY (`email`));