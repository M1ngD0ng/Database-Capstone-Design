-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`director`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`director` (
  `Dssn` VARCHAR(20) NOT NULL,
  `Dname` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Dssn`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `mydb`.`producer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`producer` (
  `Pssn` VARCHAR(20) NOT NULL,
  `Pname` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Pssn`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `mydb`.`actor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`actor` (
  `Assn` VARCHAR(20) NOT NULL,
  `Aname` VARCHAR(45) NOT NULL,
  `Director_Dssn` VARCHAR(20) NULL DEFAULT NULL,
  `Producer_Pssn` VARCHAR(20) NULL DEFAULT NULL,
  PRIMARY KEY (`Assn`),
  INDEX `fk_Actor_Director_idx` (`Director_Dssn` ASC) VISIBLE,
  INDEX `fk_Actor_Producer1_idx` (`Producer_Pssn` ASC) VISIBLE
  )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `mydb`.`movie`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`movie` (
  `Mid` INT NOT NULL,
  `Mtitle` VARCHAR(45) NOT NULL,
  `Director_Dssn` VARCHAR(20),
  PRIMARY KEY (`Mid`),
  INDEX `fk_Movie_Director1_idx` (`Director_Dssn` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `mydb`.`lead_role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`lead_role` (
  `actor_Assn1` VARCHAR(20) NOT NULL,
  `movie_Mid` INT NOT NULL,
  `actor_assn2` VARCHAR(20) NULL DEFAULT NULL,
  PRIMARY KEY (`actor_Assn1`, `movie_Mid`),
  INDEX `fk_actor_has_movie_movie1_idx` (`movie_Mid` ASC) VISIBLE,
  INDEX `fk_actor_has_movie_actor1_idx` (`actor_Assn1` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `mydb`.`performs_in`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`performs_in` (
  `Actor_Assn` VARCHAR(20) NOT NULL,
  `Movie_Mid` INT NOT NULL,
  INDEX `fk_performs_in_Movie1_idx` (`Movie_Mid` ASC) VISIBLE,
  INDEX `fk_performs_in_Actor1_idx` (`Actor_Assn` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `mydb`.`produces`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`produces` (
  `Producer_Pssn` VARCHAR(20) NOT NULL,
  `Movie_Mid` INT NOT NULL,
  INDEX `fk_Producer_has_Movie_Movie1_idx` (`Movie_Mid` ASC) VISIBLE,
  INDEX `fk_Producer_has_Movie_Producer1_idx` (`Producer_Pssn` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

USE `mydb` ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

DELIMITER $$
CREATE TRIGGER up_ac1 after insert ON actor FOR EACH ROW 
BEGIN 
if exists (SELECT * from actor where Producer_Pssn is not null)
then if not exists (select pssn from producer,actor where pssn=new.assn) then insert into producer values (new.assn, new.aname);
end if; end if;
END $$
DELIMITER ;


DELIMITER $$
CREATE TRIGGER up_ac2 after insert ON actor FOR EACH ROW 
BEGIN 
if exists (SELECT * from actor where Director_Dssn is not null)
then if not exists (select dssn from director,actor where dssn=new.assn) then insert into Director values (new.assn, new.aname);
end if; end if;
END $$
DELIMITER ;


DELIMITER $$
CREATE TRIGGER up_dr after insert ON director FOR EACH ROW 
BEGIN 
update actor set Director_Dssn=assn where actor.assn=new.dssn;
END $$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER up_pd after insert ON producer FOR EACH ROW 
BEGIN 
update actor set Producer_Pssn=assn where actor.assn=new.pssn;
END $$
DELIMITER ;

create index ind_ac_lead on lead_role (actor_Assn1, actor_Assn2);

create view view1 as
	select Aname, count(*) as count_performs
	from actor, performs_in
	where assn=Actor_Assn group by aname;

create view view2 as
	select Aname as Actor_name, Mtitle as Movie_Title
	from actor, movie, lead_role
    use index (ind_ac_lead)
	where (assn=Actor_Assn1 or assn=actor_Assn2) and movie_Mid=mid;
    
insert into actor values (1, 'Lee min jeong',null,null), (2, 'Hong gil dong',null,null), (3, 'Jeong hyo jin',null,null);
insert into producer values (1, 'Lee min jeong'), (3, 'Jeong hyo jin'), (5, 'Kim su min');
insert into director values (2, 'Hong gil dong'), (4, 'Choi min ji'),  (5, 'Kim su min');

insert into movie values (100, 'life', 4), (200, 'love', 2), (300, 'hate', 3);
insert into produces values (1, 200), (2, 100), (3,100);
insert into performs_in values (1, 100), (2,100), (2,200);
insert into lead_role values (1, 100, null), (2, 200, 1);