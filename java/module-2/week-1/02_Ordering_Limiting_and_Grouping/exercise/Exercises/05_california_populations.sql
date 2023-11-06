-- 5. The sum of the population of all cities in California. Name the column 'california_population'.
-- Expected answer is around 20,000,000
-- (1 row)


SELECT SUM(city.population) AS california_population
FROM city
JOIN state ON city.state_abbreviation = state.state_abbreviation
WHERE state.state_name = 'California';
