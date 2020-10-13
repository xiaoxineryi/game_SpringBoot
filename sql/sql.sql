CREATE SCHEMA `Game` DEFAULT CHARACTER SET utf8 ;
use game
CREATE TABLE `Game`.`new_table` (
  `userID` INT NOT NULL AUTO_INCREMENT,
  `userName` VARCHAR(45) NOT NULL,
  `userEmail` VARCHAR(45) NOT NULL,
  `userPwd` VARCHAR(45) CHARACTER SET 'utf8' NOT NULL,
  PRIMARY KEY (`userID`),
  UNIQUE INDEX `userName_UNIQUE` (`userName` ASC) VISIBLE,
  UNIQUE INDEX `userEmail_UNIQUE` (`userEmail` ASC) VISIBLE);
ALTER TABLE `Game`.`new_table` 
RENAME TO  `Game`.`user` ;

CREATE TABLE `Game`.`friends` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `userAID` INT NOT NULL,
  `userBID` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `userA_idx` (`userAID` ASC) VISIBLE,
  INDEX `userB_idx` (`userBID` ASC) VISIBLE,
  CONSTRAINT `userA`
    FOREIGN KEY (`userAID`)
    REFERENCES `Game`.`user` (`userID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `userB`
    FOREIGN KEY (`userBID`)
    REFERENCES `Game`.`user` (`userID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

    CREATE TABLE `Game`.`game` (
  `gameID` INT NOT NULL AUTO_INCREMENT,
  `gameName` VARCHAR(45) CHARACTER SET 'utf8' NOT NULL,
  PRIMARY KEY (`gameID`),
  UNIQUE INDEX `gameName_UNIQUE` (`gameName` ASC) VISIBLE);
  
  ALTER TABLE `Game`.`user` 
ADD COLUMN `userToken` VARCHAR(60) NULL AFTER `userPwd`;

