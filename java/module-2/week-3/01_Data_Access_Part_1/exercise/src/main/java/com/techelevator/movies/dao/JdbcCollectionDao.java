package com.techelevator.movies.dao;

import com.techelevator.movies.model.Collection;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JdbcCollectionDao implements CollectionDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcCollectionDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Collection> getCollections() {
        List<Collection> collections = new ArrayList<>();
        String sql = "SELECT collection_id, collection_name FROM collection;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        while (results.next()) {
            Collection collection = mapRowToCollection(results);
            collections.add(collection);
        }
        return collections;
    }

    @Override
    public Collection getCollectionById(int id) {
        Collection collection = null;
        String sql = "SELECT collection_id, collection_name " + // Added space before FROM
                "FROM collection " + // Added space before WHERE
                "WHERE collection_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        if (results.next()) { // Changed 'result' to 'results'
            collection = mapRowToCollection(results);
        }
        return collection;
    }

    @Override
    public List<Collection> getCollectionsByName(String name, boolean useWildCard) {
        List<Collection> collections = new ArrayList<>();
        String wildCard = useWildCard ? "%" : ""; // Adjust the wildcard usage
        String sql = "SELECT collection_id, collection_name FROM collection " + // Added space before WHERE
                "WHERE collection_name ILIKE ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, wildCard + name + wildCard); // Use the wildCard variable

        while (results.next()) {
            Collection collection = mapRowToCollection(results);
            collections.add(collection); // Changed 'collection.add' to 'collections.add'
        }
        return collections;
    }

    private Collection mapRowToCollection(SqlRowSet results) {
        Collection collection = new Collection();
        collection.setId(results.getInt("collection_id"));
        collection.setName(results.getString("collection_name"));
        return collection;
    }

}
