-- 3. Did you know Eric Stoltz was originally cast as Marty McFly in "Back to the Future"? Add Eric Stoltz to the list of actors for "Back to the Future" (1 row)

Insert Into movie_actor(movie_id, actor_id)
Values ((Select movie_id From movie
        Where title = 'Back to the Future'), (Select person_id From person Where person_name = 'Eric Stoltz'))
