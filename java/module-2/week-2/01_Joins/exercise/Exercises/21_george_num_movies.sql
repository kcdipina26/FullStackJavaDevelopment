-- 21. For every person in the person table with the first name of "George", list the number of movies they've been in. Name the count column 'num_of_movies'.
-- Include all Georges, even those that have not appeared in any movies. Display the names in alphabetical order. 
-- (59 rows)


SELECT p.person_name, COUNT(DISTINCT ma.movie_id) AS num_of_movies
FROM person p
LEFT JOIN movie_actor ma ON p.person_id = ma.actor_id
WHERE p.person_name LIKE 'George %' OR p.person_name = 'George'
GROUP BY p.person_name
ORDER BY p.person_name;



