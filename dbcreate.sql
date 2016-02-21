
CREATE SCHEMA IF NOT EXISTS `mydb`;

USE `mydb` ;



CREATE TABLE IF NOT EXISTS `mydb`.`Power_Plant` (
  `ID` INT NOT NULL ,
  `Name` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`ID`)  );




CREATE TABLE IF NOT EXISTS `mydb`.`Transformer` (
  `ID` INT NOT NULL ,
  `Name` VARCHAR(45) NOT NULL ,
  `Power_Plant_ID` INT NOT NULL ,
  PRIMARY KEY (`ID`, `Power_Plant_ID`)  ,
  CONSTRAINT `fk_Transformer_Power_Plant1`
    FOREIGN KEY (`Power_Plant_ID`)
    REFERENCES `mydb`.`Power_Plant` (`ID`)
    ON DELETE NO ACTION
 ON UPDATE NO ACTION);






CREATE TABLE IF NOT EXISTS `mydb`.`Breaker` (
  `ID` INT NOT NULL ,`Name` VARCHAR(45) NOT NULL,
  `Transformer_ID` INT NOT NULL ,
  PRIMARY KEY (`ID`, `Transformer_ID`) ,
  CONSTRAINT `fk_Breaker_Transformer1`
    FOREIGN KEY (`Transformer_ID`)
    REFERENCES `mydb`.`Transformer` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);



CREATE TABLE IF NOT EXISTS `mydb`.`Breaker_Data` (
  `DateTime` DATETIME NOT NULL ,
  `Current` FLOAT(16) NOT NULL ,
  `Breaker_ID` INT NOT NULL ,
  PRIMARY KEY (`DateTime`, `Breaker_ID`)  ,
  CONSTRAINT `fk_Breaker_Data_Breaker1`
    FOREIGN KEY (`Breaker_ID`)
    REFERENCES `mydb`.`Breaker` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);





CREATE TABLE IF NOT EXISTS `mydb`.`Transformer_Data` (
  `DateTime` DATETIME NOT NULL ,
  `Current` FLOAT(16) NOT NULL,
  `Transformer_ID` INT NOT NULL ,
  PRIMARY KEY (`DateTime`, `Transformer_ID`) ,
  CONSTRAINT `fk_Transformer_Data_Transformer`
    FOREIGN KEY (`Transformer_ID`)
    REFERENCES `mydb`.`Transformer` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
CREATE INDEX IF NOT EXISTS `fk_Breaker_Transformer1_idx` ON `mydb`.`Breaker` (`Transformer_ID` ASC) ;
	
CREATE INDEX IF NOT EXISTS `fk_Transformer_Power_Plant1_idx` ON `mydb`.`Transformer` (`Power_Plant_ID` ASC) ;
	
CREATE INDEX IF NOT EXISTS `fk_Breaker_Data_Breaker1_idx` ON `mydb`.`Breaker_Data` (`Breaker_ID` ASC)  ;
	
CREATE INDEX IF NOT EXISTS `fk_Transformer_Data_Transformer_idx` ON `mydb`.`Transformer_Data` (`Transformer_ID` ASC) ;


