-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Drop tables
drop table if exists mydb.planet_stats;
drop table if exists mydb.battle_stats;
drop table if exists mydb.battle_log;
drop table if exists mydb.planet_battle_defense;
drop table if exists mydb.planet_battle_army;
drop table if exists mydb.enemy_army;
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS mydb DEFAULT CHARACTER SET utf8 ;
USE mydb ;

-- -----------------------------------------------------
-- Table mydb.planet_stats
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS mydb.planet_stats (
  planet_id INT NOT NULL,
  resource_metal_amount INT NULL DEFAULT 0,
  resource_deuterion_amount INT NULL DEFAULT 0,
  technology_defense_level INT NULL DEFAULT 0,
  technology_attack_level INT NULL DEFAULT 0,
  battles_counter INT NULL DEFAULT 0,
  missile_launcher_remaining INT NULL DEFAULT 0,
  ion_cannon_remaining INT NULL DEFAULT 0,
  plasma_canon_remaining INT NULL DEFAULT 0,
  light_hunter_remaining INT NULL DEFAULT 0,
  heavy_hunter_remaining INT NULL DEFAULT 0,
  battleship_remaining INT NULL DEFAULT 0,
  armored_ship_remaining INT NULL DEFAULT 0,
  PRIMARY KEY (planet_id))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table mydb.Battle_stats
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS mydb.battle_stats (
  planet_id INT NOT NULL,
  num_battle INT NOT NULL,
  resource_metal_acquired INT NULL DEFAULT 0,
  resource_deuterion_acquired INT NULL DEFAULT 0,
  planet_stats_planet_id VARCHAR(100) NOT NULL,
  PRIMARY KEY (planet_id, num_battle),
  CONSTRAINT fk_battle_stats_planet_id
    FOREIGN KEY (planet_id)
    REFERENCES mydb.planet_stats (planet_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table mydb.Battle_log
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS mydb.battle_log (
  planet_id INT NOT NULL,
  num_battle INT NOT NULL,
  num_line INT NOT NULL DEFAULT 0,
  log_entry VARCHAR(50) NULL DEFAULT 0,
  PRIMARY KEY (planet_id, num_battle, num_line),
  CONSTRAINT fk_battle_log_battle_stats
    FOREIGN KEY (planet_id , num_battle)
    REFERENCES mydb.battle_stats (planet_id , num_battle)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table mydb.Planet_battle_defense
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS mydb.planet_battle_defense (
  planet_id INT NOT NULL,
  num_battle INT NOT NULL,
  missile_launcher_built INT NULL DEFAULT 0,
  missile_launcher_destroyed INT NULL DEFAULT 0,
  ion_cannon_built INT NULL DEFAULT 0,
  ion_cannon_destroyed INT NULL DEFAULT 0,
  plasma_cannon_built INT NULL DEFAULT 0,
  plasma_cannon_destroyed INT NULL DEFAULT 0,
  PRIMARY KEY (planet_id, num_battle),
  CONSTRAINT fk_planet_battle_defense_battle_stats
    FOREIGN KEY (planet_id , num_battle)
    REFERENCES mydb.battle_stats (planet_id , num_battle)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table mydb.Planet_battle_army
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS mydb.planet_battle_army (
  planet_id INT NOT NULL,
  num_battle INT NOT NULL,
  light_hunter_built INT NULL DEFAULT 0,
  light_hunter_destroyed INT NULL DEFAULT 0,
  heavy_hunter_built INT NULL DEFAULT 0,
  heavy_hunter_destroyed INT NULL DEFAULT 0,
  battleship_built INT NULL DEFAULT 0,
  battleship_destroyed INT NULL DEFAULT 0,
  armored_ship_built INT NULL DEFAULT 0,
  armored_ship_destroyed INT NULL DEFAULT 0,
  PRIMARY KEY (planet_id, num_battle),
  CONSTRAINT fk_planet_battle_army_battle_stats
    FOREIGN KEY (planet_id , num_battle)
    REFERENCES mydb.battle_stats (planet_id , num_battle)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table mydb.Enemy_army
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS mydb.enemy_army (
  planet_id INT NOT NULL,
  num_battle INT NOT NULL,
  light_hunter_threat INT NULL DEFAULT 0,
  light_hunter_destroyed INT NULL DEFAULT 0,
  heavy_hunter_threat INT NULL DEFAULT 0,
  heavy_hunter_destroyed INT NULL DEFAULT 0,
  battleship_threat INT NULL DEFAULT 0,
  battleship_destroyed INT NULL DEFAULT 0,
  armored_ship_threat INT NULL DEFAULT 0,
  armored_ship_destroyed INT NULL DEFAULT 0,
  PRIMARY KEY (planet_id, num_battle),
  CONSTRAINT fk_planet_battle_army_battle_stats0
    FOREIGN KEY (planet_id , num_battle)
    REFERENCES mydb.battle_stats (planet_id , num_battle)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;