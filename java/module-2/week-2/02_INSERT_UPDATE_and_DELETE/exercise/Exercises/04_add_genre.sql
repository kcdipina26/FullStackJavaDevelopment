-- 4. Add a "Sports" genre to the genre table. Add the movie "Coach Carter" to the newly created genre. (1 row each)
-- Insert a new genre called 'Sports'
insert into genre (genre_name)
values ('Sports');

-- Insert a relation between 'Coach Carter' and the 'Sports' genre into movie_genre
insert into movie_genre (movie_id, genre_id)
values (
  (select movie_id from movie where title = 'Coach Carter'),
  (select genre_id from genre where genre_name = 'Sports')
);
