-- 12. Create a "Bill Murray Collection" in the collection table. For the movies that have Bill Murray in them, set
--     their collection ID to the "Bill Murray Collection". (1 row, 6 rows)


-- Step 1: Insert the "Bill Murray Collection" into the collection table
INSERT INTO collection (collection_name)
VALUES ('Bill Murray Collection');

-- Step 2: Update the movie table to set the collection_id for Bill Murray's movies
-- Replace 'actor_id' with the actual column name that refers to the actor's ID in the movie_actor table
UPDATE movie
SET collection_id = (SELECT collection_id FROM collection WHERE collection_name = 'Bill Murray Collection')
WHERE movie_id IN (
    SELECT movie_id FROM movie_actor
    WHERE actor_id = (SELECT person_id FROM person WHERE person_name = 'Bill Murray')
);
