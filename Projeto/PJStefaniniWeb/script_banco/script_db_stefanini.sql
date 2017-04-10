-- MySQL Workbench Synchronization
-- Generated: 2017-04-08 18:32
-- Model: New Model
-- Version: 1.0
-- Project: Name of the project
-- Author: lenok

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `db_stefanini` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE IF NOT EXISTS `db_stefanini`.`tbf_funcionario` (
  `tbf_id` INT(11) NOT NULL AUTO_INCREMENT,
  `tbf_nome` VARCHAR(100) NOT NULL,
  `tbf_cpf` MEDIUMTEXT NOT NULL,
  `tbf_endereco` VARCHAR(455) NOT NULL,
  `tbf_telefone` MEDIUMTEXT NULL DEFAULT NULL,
  `tbf_idcargo` INT(11) NOT NULL,
  `tbf_idempresa` INT(11) NOT NULL,
  PRIMARY KEY (`tbf_id`),
  INDEX `fk_tbf_funcionario_tbc_cargo_idx` (`tbf_idcargo` ASC),
  INDEX `fk_tbf_funcionario_tbe_empresa1_idx` (`tbf_idempresa` ASC),
  UNIQUE INDEX `tbf_cpf_UNIQUE` (`tbf_cpf`(11) ASC),
  CONSTRAINT `fk_tbf_funcionario_tbc_cargo`
    FOREIGN KEY (`tbf_idcargo`)
    REFERENCES `db_stefanini`.`tbc_cargo` (`tbc_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tbf_funcionario_tbe_empresa1`
    FOREIGN KEY (`tbf_idempresa`)
    REFERENCES `db_stefanini`.`tbe_empresa` (`tbe_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `db_stefanini`.`tbc_cargo` (
  `tbc_id` INT(11) NOT NULL AUTO_INCREMENT,
  `tbc_cargo` VARCHAR(100) NOT NULL,
  `tbc_descricao` VARCHAR(455) NULL DEFAULT NULL,
  `tbc_valorHora` DOUBLE NOT NULL,
  PRIMARY KEY (`tbc_id`),
  UNIQUE INDEX `tbc_cargo_UNIQUE` (`tbc_cargo` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `db_stefanini`.`tbe_empresa` (
  `tbe_id` INT(11) NOT NULL AUTO_INCREMENT,
  `tbe_nome_fantasia` VARCHAR(255) NOT NULL,
  `tbe_nome_empresarial` VARCHAR(255) NOT NULL,
  `tbe_cnpj` MEDIUMTEXT NOT NULL,
  `tbe_endereco` VARCHAR(255) NOT NULL,
  `tbe_telefone` MEDIUMTEXT NULL DEFAULT NULL,
  PRIMARY KEY (`tbe_id`),
  UNIQUE INDEX `tbe_cnpj_UNIQUE` (`tbe_cnpj`(15) ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
