CREATE SCHEMA `library`;

CREATE TABLE `library`.`new_table` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NULL,
  `country` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));

ALTER TABLE `library`.`new_table` 
RENAME TO  `library`.`author`;

CREATE TABLE `library`.`book` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `author_id` INT NULL,
  `section_id` INT NULL,
  `language` INT NULL,
  `year` INT NULL,
  `publisher` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `library`.`section` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `is_fiction` TINYINT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `library`.`reader` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `telephone_number` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));
  
  ALTER TABLE `library`.`book` 
ADD INDEX `fk_book_author_idx` (`author_id` ASC),
ADD INDEX `fk_book_section_idx` (`section_id` ASC);
  
ALTER TABLE `library`.`book` 
ADD CONSTRAINT `fk_book_author`
  FOREIGN KEY (`author_id`)
  REFERENCES `library`.`author` (`id`)
  ON DELETE SET NULL
  ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_book_section`
  FOREIGN KEY (`section_id`)
  REFERENCES `library`.`section` (`id`)
  ON DELETE SET NULL
  ON UPDATE NO ACTION;

INSERT INTO `library`.`author` (`id`, `first_name`, `last_name`, `country`) VALUES ('1', 'Ion', 'Creanga', 'Romania');
INSERT INTO `library`.`author` (`first_name`, `last_name`, `country`) VALUES ('Mihai', 'Eminescu', 'Romania');
INSERT INTO `library`.`section` (`name`, `is_fiction`) VALUES ('Horror', 'true');
INSERT INTO `library`.`reader` (`first_name`, `last_name`, `email`, `telephone_number`) VALUES ('Sebastian', 'Gradinaru', 'seabastia_gradinaru@yahoo.com', '0726544104');
INSERT INTO `library`.`author` (`first_name`, `last_name`, `country`) VALUES ('Stephen', 'King', 'USA');
INSERT INTO `library`.`book` (`name`, `author_id`, `section_id`, `language`, `year`, `publisher`) VALUES ('IT', '4', '1', '1', '1995', 'Editura ENG');
INSERT INTO `library`.`book` (`name`, `author_id`, `section_id`, `language`, `year`, `publisher`) VALUES ('Amintiri din copilarie', '1', '2', '0', '1990', 'Editura RO');
INSERT INTO `library`.`section` (`name`, `is_fiction`) VALUES ('History', 'false');
