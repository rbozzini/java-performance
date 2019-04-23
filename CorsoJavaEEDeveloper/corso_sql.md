shell MYSql:
mysqlsh --uri root@localhost

CREATE SCHEMA `corso_java_2` DEFAULT CHARACTER SET utf8mb4

CREATE TABLE `corso_java`.`clienti` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(255) NOT NULL,
  `cognome` VARCHAR(255) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `telefono` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`ID`));

CREATE TABLE `corso_java`.`new_table` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `id_cliente` INT NULL,
  `data_consegna` DATETIME NULL,
  `importo` DOUBLE NULL,
  PRIMARY KEY (`ID`));

ALTER TABLE `corso_java`.`ordini` 
ADD INDEX `id_cliente_idx` (`id_cliente` ASC) VISIBLE;
;
ALTER TABLE `corso_java`.`ordini` 
ADD CONSTRAINT `id_cliente`
  FOREIGN KEY (`id_cliente`)
  REFERENCES `corso_java`.`clienti` (`ID`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;