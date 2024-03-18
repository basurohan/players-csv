CREATE TABLE players (
    player_id VARCHAR(50) PRIMARY KEY,
    birth_year INT,
    birth_month INT,
    birth_day INT,
    birth_country VARCHAR(50),
    birth_state VARCHAR(50),
    birth_city VARCHAR(50),
    death_year INT,
    death_month INT,
    death_day INT,
    death_country VARCHAR(50),
    death_state VARCHAR(50),
    death_city VARCHAR(50),
    name_first VARCHAR(50),
    name_last VARCHAR(50),
    name_given VARCHAR(50),
    weight INT,
    height INT,
    bats VARCHAR(50),
    throws VARCHAR(50),
    debut VARCHAR(50),
    final_game VARCHAR(50),
    retro_id VARCHAR(50),
    bbref_id VARCHAR(50)
);