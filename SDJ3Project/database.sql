DROP TABLE IF EXISTS orders CASCADE;
DROP TABLE IF EXISTS item CASCADE;
DROP TABLE IF EXISTS review CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS itemlist CASCADE;
DROP TABLE IF EXISTS keyword CASCADE;

CREATE TABLE car
(
	weight decimal NOT NULL CHECK(weight > 0),
	vin varchar UNIQUE CHECK(char_length(VIN) between 8 and 20),
	model varchar NOT NULL CHECK(char_length(model) > 1),
	
)
;

CREATE TABLE PART
(
	weight decimal NOT NULL CHECK(weight > 0),
	type varchar NOT NULL,
	
	