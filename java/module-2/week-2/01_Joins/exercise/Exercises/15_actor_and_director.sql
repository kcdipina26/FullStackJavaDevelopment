-- 15. The title of the movie and the name of director for movies where the director was also an actor in the same movie.
-- Order the results by movie title (A-Z)
-- (73 rows)

SELECT m.title, p.person_name
FROM movie m
JOIN person p ON m.director_id = p.person_id
JOIN movie_actor ma ON m.movie_id = ma.movie_id
WHERE ma.actor_id = m.director_id
ORDER BY m.title;
