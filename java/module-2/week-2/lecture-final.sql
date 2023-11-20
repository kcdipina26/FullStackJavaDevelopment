-- INNER JOIN

-- Write a query to retrieve the name and state abbreviation for the 2 cities named "Columbus" in the database

SELECT city_name, state_abbreviation
FROM city
WHERE city_name = 'Columbus';

-- Modify the previous query to retrieve the names of the states (rather than their abbreviations).

SELECT city_name, state_name
FROM city
         JOIN state ON city.state_abbreviation = state.state_abbreviation
WHERE city_name = 'Columbus';

-- Write a query to retrieve the names of all the national parks with their state abbreviations.
-- (Some parks will appear more than once in the results, because they cross state boundaries.)

SELECT park_name, state_abbreviation
FROM park
         JOIN park_state ON park.park_id = park_state.park_id;

-- The park_state table is an associative table that can be used to connect the park and state tables.
-- Modify the previous query to retrieve the names of the states rather than their abbreviations.

SELECT park_name, state_name
FROM park
         JOIN park_state ON park.park_id = park_state.park_id
         JOIN state ON park_state.state_abbreviation = state.state_abbreviation;

-- Modify the previous query to include the name of the state's capital city.

SELECT park_name, state_name, city_name AS capital_name
FROM park
         JOIN park_state ON park.park_id = park_state.park_id
         JOIN state ON park_state.state_abbreviation = state.state_abbreviation
         JOIN city ON state.capital = city.city_id;

-- Modify the previous query to include the area of each park.

SELECT park_name, state_name, city_name AS capital_name, park.area AS park_area
FROM park
         JOIN park_state ON park.park_id = park_state.park_id
         JOIN state ON park_state.state_abbreviation = state.state_abbreviation
         JOIN city ON state.capital = city.city_id;

-- Write a query to retrieve the names and populations of all the cities in the Midwest census region.

SELECT city_name, city.population
FROM city
         JOIN state ON city.state_abbreviation = state.state_abbreviation
WHERE census_region = 'Midwest';

-- Write a query to retrieve the number of cities in the city table for each state in the Midwest census region.

SELECT state_name, COUNT(*) AS number_of_cities
FROM city
         JOIN state ON city.state_abbreviation = state.state_abbreviation
WHERE census_region = 'Midwest'
GROUP BY state_name;

-- Modify the previous query to sort the results by the number of cities in descending order.

SELECT state_name, COUNT(*) AS number_of_cities
FROM city
         JOIN state ON city.state_abbreviation = state.state_abbreviation
WHERE census_region = 'Midwest'
GROUP BY state_name
ORDER BY number_of_cities DESC;


-- LEFT JOIN

-- Write a query to retrieve the state name and the earliest date a park was established in that state (or territory) for every record in the state table that has park records associated with it.

SELECT state_name, MIN(date_established) AS earliest_park
FROM state
         JOIN park_state ON state.state_abbreviation = park_state.state_abbreviation
         JOIN park ON park_state.park_id = park.park_id
GROUP BY state_name;

-- Modify the previous query so the results include entries for all the records in the state table, even if they have no park records associated with them.

SELECT state_name, MIN(date_established) AS earliest_park
FROM state
         LEFT JOIN park_state ON state.state_abbreviation = park_state.state_abbreviation
         LEFT JOIN park ON park_state.park_id = park.park_id
GROUP BY state_name;


-- UNION

-- Write a query to retrieve all the place names in the city and state tables that begin with "W" sorted alphabetically. (Washington is the name of a city and a state--how many times does it appear in the results?)

SELECT city_name AS place_name
FROM city
WHERE city_name LIKE 'W%'
UNION
SELECT state_name AS place_name
FROM state
WHERE state_name LIKE 'W%'
ORDER BY place_name;

-- Modify the previous query to include a column that indicates whether the place is a city or state.

SELECT city_name AS place_name, 'City' AS kind_of_place
FROM city
WHERE city_name LIKE 'W%'
UNION
SELECT state_name AS place_name, 'State' AS kind_of_place
FROM state
WHERE state_name LIKE 'W%'
ORDER BY place_name;


-- MovieDB
-- After creating the MovieDB database and running the setup script, make sure it is selected in pgAdmin and confirm it is working correctly by writing queries to retrieve...

-- The names of all the movie genres

SELECT genre_name
FROM genre;

-- The titles of all the Comedy movies

SELECT title
FROM movie
         JOIN movie_genre ON movie.movie_id = movie_genre.movie_id
         JOIN genre ON movie_genre.genre_id = genre.genre_id
WHERE genre_name = 'Comedy';



-- INSERT

-- Add Disneyland to the park table. (It was established on 7/17/1955, has an area of 2.1 square kilometers and does not offer camping.)

INSERT INTO park (park_name, date_established, area, has_camping)
VALUES ('Disneyland', '7/17/1955', 2.1, false);

-- Add Hawkins, IN (with a population of 30,000 and an area of 38.1 square kilometers) and Cicely, AK (with a popuation of 839 and an area of 11.4 square kilometers) to the city table.

INSERT INTO city (city_name, state_abbreviation, population, area)
VALUES ('Hawkins', 'IN', 30000, 38.1), ('Cicely', 'AK', 839, 11.4);

-- Since Disneyland is in California (CA), add a record representing that to the park_state table.

INSERT INTO park_state (park_id, state_abbreviation)
SELECT park_id, 'CA'
FROM park
WHERE park_name = 'Disneyland';


-- UPDATE

-- Change the state nickname of California to "The Happiest Place on Earth."

UPDATE state
SET state_nickname = 'The Happiest Place on Earth'
WHERE state_abbreviation = 'CA';

-- Increase the population of California by 1,000,000.

UPDATE state
SET population = population + 1000000
WHERE state_abbreviation = 'CA';

-- Change the capital of California to Anaheim.

UPDATE state
SET capital = (SELECT city_id FROM city WHERE city_name = 'Anaheim' AND state_abbreviation = 'CA')
WHERE state_abbreviation = 'CA';

-- Change California's nickname back to "The Golden State", reduce the population by 1,000,000, and change the capital back to Sacramento.

UPDATE state
SET state_nickname = 'The Golden State',
    population = population - 1000000,
    capital = (SELECT city_id FROM city WHERE city_name = 'Sacramento' AND state_abbreviation = 'CA')
WHERE state_abbreviation = 'CA';


-- DELETE

-- Delete Hawkins, IN from the city table.

DELETE FROM city WHERE city_name = 'Hawkins' AND state_abbreviation = 'IN';

-- Delete all cities with a population of less than 1,000 people from the city table.

DELETE FROM city WHERE population < 1000;


-- REFERENTIAL INTEGRITY

-- Try adding a city to the city table with "XX" as the state abbreviation.

INSERT INTO city (city_name, state_abbreviation, population, area)
VALUES ('Springfield', 'XX', 32000, 50);

-- Try deleting California from the state table.

DELETE FROM state
WHERE state_abbreviation = 'CA';

-- Try deleting Disneyland from the park table. Try again after deleting its record from the park_state table.

DELETE FROM park
WHERE park_name = 'Disneyland';

DELETE FROM park_state
WHERE park_id = (SELECT park_id FROM park WHERE park_name = 'Disneyland');


-- CONSTRAINTS

-- NOT NULL constraint
-- Try adding Smallville, KS to the city table without specifying its population or area.

INSERT INTO city (city_name, state_abbreviation)
VALUES ('Smallville', 'KS');

-- DEFAULT constraint
-- Try adding Smallville, KS again, specifying an area but not a population.

INSERT INTO city (city_name, state_abbreviation, area)
VALUES ('Smallville', 'KS', 0.5);

-- Retrieve the new record to confirm it has been given a default, non-null value for population.

SELECT *
FROM city
WHERE city_name = 'Smallville';

-- UNIQUE constraint
-- Try changing California's nickname to "Vacationland" (which is already the nickname of Maine).

UPDATE state
SET state_nickname = 'Vacationland'
WHERE state_abbreviation = 'CA';

-- CHECK constraint
-- Try changing the census region of Florida (FL) to "Southeast" (which is not a valid census region).

UPDATE state
SET census_region = 'Southeast'
WHERE state_abbreviation = 'FL';


-- TRANSACTIONS

-- Delete the record for Smallville, KS within a transaction.

START TRANSACTION;
DELETE FROM city
WHERE city_name = 'Smallville' AND state_abbreviation = 'KS';
COMMIT;

-- Delete all of the records from the park_state table, but then "undo" the deletion by rolling back the transaction.

START TRANSACTION;
DELETE FROM park_state;
SELECT COUNT(*) FROM park_state;
ROLLBACK;
SELECT COUNT(*) FROM park_state;

-- Update all of the cities to be in the state of Texas (TX), but then roll back the transaction.

START TRANSACTION;
UPDATE city SET state_abbreviation = 'TX';
SELECT city_name, state_abbreviation FROM city LIMIT 5;
ROLLBACK;
SELECT city_name, state_abbreviation FROM city LIMIT 5;


-- Demonstrate two different SQL connections trying to access the same table where one is inside of a transaction but hasn't committed yet.

-- Open a terminal and run psql -U postgres -d UnitedStates
-- Enter the following lines in psql:
START TRANSACTION;
INSERT INTO city (city_name, state_abbreviation, population, area)
VALUES ('Schrodinger''s City', 'NJ', 0, 0);
-- (Notice there's no COMMIT or ROLLBACK.)

-- This query will find the inserted record if run in psql, but not in pgAdmin (unless you commit):
SELECT * FROM city WHERE city_name = 'Schrodinger''s City'

