-- 3. List the publisher name and the highest star rating for each publisher (name the column 'highest_rating'). 
-- Order the results by rating, highest first.
-- (4 rows, starting with T&E Publishing)
SELECT publisher.publisher_name,
MAX(book.star_rating)AS highest_rating
FROM publisher
JOIN book ON publisher.publisher_id = book.publisher_id
GROUP BY publisher_name
ORDER BY highest_rating DESC;

