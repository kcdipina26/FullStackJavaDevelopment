package com.techelevator.movies.dao;

import com.techelevator.movies.model.Movie;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import javax.sql.DataSource;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class JdbcMovieDao implements MovieDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcMovieDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Movie> getMovies() {
        List<Movie> movies = new ArrayList<>();
        String sql = "SELECT movie_id, title, overview, tagline, poster_path, home_page, release_date, length_minutes, director_id, collection_id FROM movie;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        while (results.next()) {
            movies.add(mapRowToMovie(results));
        }
        return movies;
    }

    @Override
    public Movie getMovieById(int id) {
        Movie movie = null;
        String sql = "SELECT movie_id, title, overview, tagline, poster_path, home_page, release_date, length_minutes, director_id, collection_id FROM movie WHERE movie_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);

        if (results.next()) {
            movie = mapRowToMovie(results);
        }
        return movie;
    }

    @Override
    public List<Movie> getMoviesByTitle(String title, boolean useWildCard) {
        List<Movie> movies = new ArrayList<>();
        String sql = "SELECT movie_id, title, overview, tagline, poster_path, home_page, release_date, length_minutes, director_id, collection_id FROM movie WHERE title ILIKE ?;";

        if (useWildCard) {
            title = "%" + title + "%";
        }
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, title);

        while (results.next()) {
            movies.add(mapRowToMovie(results));
        }

        return movies;
    }

    @Override
    public List<Movie> getMoviesByDirectorNameAndBetweenYears(String directorName, int startYear, int endYear, boolean useWildCard) {
        List<Movie> movies = new ArrayList<>();
        String sql = "SELECT movie.movie_id, movie.title, movie.overview, movie.tagline, movie.poster_path, movie.home_page, movie.release_date, movie.length_minutes, movie.director_id, movie.collection_id FROM movie JOIN person ON movie.director_id = person.person_id WHERE person.person_name ILIKE ? AND EXTRACT(YEAR FROM movie.release_date) BETWEEN ? AND ? ORDER BY movie.release_date;";


        if (useWildCard) {
            directorName = "%" + directorName + "%";
        }
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, directorName, startYear, endYear);

        while (results.next()) {
            movies.add(mapRowToMovie(results));
        }


        return movies;
    }

    private Movie mapRowToMovie(SqlRowSet results) {
        Movie movie = new Movie();
        movie.setId(results.getInt("movie_id"));
        movie.setTitle(results.getString("title"));
        movie.setOverview(results.getString("overview"));
        movie.setTagline(results.getString("tagline"));
        movie.setPosterPath(results.getString("poster_path"));
        movie.setHomePage(results.getString("home_page"));
        movie.setLengthMinutes(results.getInt("length_minutes"));
        movie.setDirectorId(results.getInt("director_id"));
        movie.setCollectionId(results.getInt("collection_id"));

        Date releaseDate = results.getDate("release_date");
        if (releaseDate != null) {
            movie.setReleaseDate(((Date) releaseDate).toLocalDate());
        }

        return movie;
    }

}





