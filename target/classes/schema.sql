DROP TABLE IF EXISTS contestant_season;
DROP TABLE IF EXISTS tribe_contestants;
DROP TABLE IF EXISTS tribe;
DROP TABLE IF EXISTS contestant;
DROP TABLE IF EXISTS season;


CREATE TABLE season (
	season_id INT NOT NULL AUTO_INCREMENT,
	season_location varchar(128) NOT NULL,
	season_tagline varchar(256) NOT NULL,
	PRIMARY KEY(season_id)
);

CREATE TABLE contestant (
	contestant_id INT NOT NULL AUTO_INCREMENT,
	first_name varchar(60) NOT NULL,
	last_name varchar(60) NOT NULL,
	season_id INT NOT NULL,
	PRIMARY KEY(contestant_id),
	FOREIGN KEY(season_id) REFERENCES season(season_id) ON DELETE CASCADE
);

CREATE TABLE tribe (
	tribe_id INT NOT NULL AUTO_INCREMENT,
	tribe_name varchar(128),
	PRIMARY KEY(tribe_id)
);

CREATE TABLE contestant_season (
	contestant_id INT NOT NULL,
	season_id INT NOT NULL,
	FOREIGN KEY (contestant_id) REFERENCES contestant (contestant_id) ON DELETE CASCADE,
	FOREIGN KEY (season_id) REFERENCES season (season_id) ON DELETE CASCADE
);