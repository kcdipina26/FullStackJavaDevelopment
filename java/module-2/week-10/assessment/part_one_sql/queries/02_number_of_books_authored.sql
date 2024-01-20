-- 2. List the first and last name of all authors (name the column 'author') and the number of books they've written (name the column 'num_books').
-- Order the results by the number of books in descending order (highest first), then by alphabetical order of the author's first name.
-- Tip: make sure to add a space between the author's first and last names.
-- (16 rows)
SELECT person.first_name || ' ' || person.last_name AS author,
 COUNT(book_author.book_id) AS num_books
FROM person
INNER JOIN book_author ON person.person_id = book_author.author_id
GROUP BY person.person_id
ORDER BY num_books DESC,person.first_name ASC;
