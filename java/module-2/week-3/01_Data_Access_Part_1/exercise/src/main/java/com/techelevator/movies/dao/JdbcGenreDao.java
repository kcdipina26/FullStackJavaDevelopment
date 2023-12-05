package com.techelevator.movies.dao;

import com.techelevator.movies.model.Genre;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JdbcGenreDao implements GenreDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcGenreDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Genre> getGenres() {
        List<Genre> genres = new ArrayList<>();
        String sql = "SELECT genre_id, genre_name FROM genre;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        while (results.next()) {
            genres.add(mapRowToGenre(results));
        }
        return genres;

    }

    @Override
    public Genre getGenreById(int id) {
        Genre genre = null;
        String sql = "SELECT genre_id, genre_name FROM genre WHERE genre_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);

        if (results.next()) {
            genre = mapRowToGenre(results);
        }
        return genre;
    }

    @Override
    public List<Genre> getGenresByName(String name, boolean useWildCard) {
        List<Genre> genres = new ArrayList<>();
        String wildCard = useWildCard ? "%" : "";
        String sql = "SELECT genre_id, genre_name FROM genre WHERE genre_name LIKE ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, wildCard + name + wildCard);

        while (results.next()) {
            genres.add(mapRowToGenre(results));
        }
        return genres;
    }

    private Genre mapRowToGenre(SqlRowSet results) {
        int id = results.getInt("genre_id");
        String name = results.getString("genre_name");
        return new Genre(id, name);
    }
}
