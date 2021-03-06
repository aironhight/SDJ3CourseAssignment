DROP TABLE IF EXISTS order CASCADE;
DROP TABLE IF EXISTS car CASCADE;
DROP TABLE IF EXISTS part CASCADE;
DROP TABLE IF EXISTS pallet CASCADE;
DROP TABLE IF EXISTS pick CASCADE;

CREATE TABLE car
(
	weight decimal NOT NULL CHECK(weight > 0),
	vin varchar UNIQUE CHECK(char_length(VIN) between 8 and 20),
	model varchar NOT NULL CHECK(char_length(model) > 1),
	
)
;

CREATE TABLE part
(
	id serial PRIMARY KEY,
	weight decimal NOT NULL CHECK(weight > 0),
	part_type varchar NOT NULL,
	pallet_id varchar NOT NULL,
	car_vin varchar NOT NULL
)
;

CREATE TABLE pallet
(
	id int PRIMARY KEY,
	part_type varchar NOT NULL,
	maximum_weight decimal NOT NULL
)
;

CREATE TABLE pick
(
	id serial PRIMARY KEY,
	part_id int NOT NULL,
	order_id int NOT NULL,
)
;

CREATE TABLE order
(
	id int NOT NULL,
	receiver_address varchar NOT NULL
	receiver_country NOT NULL
)
;
	

alter table part
	add foreign key (car_vin)
	references car (vin)
	on delete restrict
	on update restrict
;

alter table part
	add foreign key (pallet_id)
	references pallet (id)
	on delete restrict
	on update restrict
;


alter table pick
	add foreign key (part_id)
	references part (id)
	on delete restrict
	on update restrict
;

alter table pick
	add foreign key (order_id)
	references order(id)
	on delete restrict
	on update restrict
;

alter table order
	add foreign key(pick_id)
	references pick(id)
	on delete restrict
	on update restrict
;

/*	findPartsFromACar(inputVIN);
	select * from parts where vin = inputVIN;
	
	--parts list--
	
	select * from pick where partId = part(0).getId();
	
	--picks list--
	
	select * from order where pickId = pick.getId();  join
	
	--list of orders--
	--job done--
	*/

	

	
	