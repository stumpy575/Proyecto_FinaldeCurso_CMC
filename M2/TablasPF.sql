CREATE DATABASE proyecto_final;
USE proyecto_final;

CREATE TABLE Planet_stats (
    planet_id INT NOT NULL PRIMARY KEY,
    name VARCHAR(100),
    resource_metal_amount INT DEFAULT 0,
    resource_deuterion_amount INT DEFAULT 0,
    technology_defense_level INT DEFAULT 0,
    technology_attack_level INT DEFAULT 0,
    battles_counter INT DEFAULT 0,
    missile_launcher_remaining INT DEFAULT 0,
    ion_cannon_remaining INT DEFAULT 0,
    plasma_canon_remaining INT DEFAULT 0,
    light_hunter_remaining INT DEFAULT 0,
    heavy_hunter_remaining INT DEFAULT 0,
    battleship_remaining INT DEFAULT 0,
    armored_ship_remaining INT DEFAULT 0
);

CREATE TABLE Battle_stats (
    planet_id INT NOT NULL,
    num_battle INT NOT NULL,
    resource_metal_acquired INT DEFAULT 0,
    resource_deuterion_acquired INT DEFAULT 0,
    PRIMARY KEY (planet_id, num_battle),
    FOREIGN KEY (planet_id) REFERENCES Planet_stats(planet_id)
);

CREATE TABLE Battle_log (
    planet_id INT NOT NULL,
    num_battle INT NOT NULL,
    num_line INT NOT NULL,
    log_entry TEXT,
    PRIMARY KEY (planet_id, num_battle, num_line),
    FOREIGN KEY (planet_id, num_battle) REFERENCES Battle_stats(planet_id, num_battle)
);

CREATE TABLE Planet_battle_defense (
    planet_id INT NOT NULL,
    num_battle INT NOT NULL,
    missile_launcher_built INT DEFAULT 0,
    missile_launcher_destroyed INT DEFAULT 0,
    ion_cannon_built INT DEFAULT 0,
    ion_cannon_destroyed INT DEFAULT 0,
    plasma_canon_built INT DEFAULT 0,
    plasma_canon_destroyed INT DEFAULT 0,
    PRIMARY KEY (planet_id, num_battle),
    FOREIGN KEY (planet_id, num_battle) REFERENCES Battle_stats(planet_id, num_battle)
);

CREATE TABLE Planet_battle_army (
    planet_id INT NOT NULL,
    num_battle INT NOT NULL,
	light_hunter_built INT DEFAULT 0,
    light_hunter_destroyed INT DEFAULT 0,
    heavy_hunter_built INT DEFAULT 0,
    heavy_hunter_destroyed INT DEFAULT 0,
    battleship_built INT DEFAULT 0,
    battleship_destroyed INT DEFAULT 0,
    armored_ship_built INT DEFAULT 0,
    armored_ship_destroyed INT DEFAULT 0,
    PRIMARY KEY (planet_id, num_battle),
    FOREIGN KEY (planet_id, num_battle) REFERENCES Battle_stats(planet_id, num_battle)
);

CREATE TABLE Enemy_army (
    planet_id INT NOT NULL,
    num_battle INT NOT NULL,
    light_hunter_threat INT DEFAULT 0,
    light_hunter_destroyed INT DEFAULT 0,
    heavy_hunter_threat INT DEFAULT 0,
    heavy_hunter_destroyed INT DEFAULT 0,
    battleship_threat INT DEFAULT 0,
    battleship_destroyed INT DEFAULT 0,
    armored_ship_threat INT DEFAULT 0,
    armored_ship_destroyed INT DEFAULT 0,
    PRIMARY KEY (planet_id, num_battle),
    FOREIGN KEY (planet_id, num_battle) REFERENCES Battle_stats(planet_id, num_battle)
);
