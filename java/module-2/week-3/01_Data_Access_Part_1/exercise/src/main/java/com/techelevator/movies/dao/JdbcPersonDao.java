package com.techelevator.movies.dao;

import com.techelevator.movies.model.Person;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class JdbcPersonDao implements PersonDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcPersonDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Person> getPersons() {
        String sql = "SELECT * FROM person";
        List<Person> persons = new ArrayList<>();
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql);
        while (result.next()) {
            Person person = mapRowToPerson(result);
            persons.add(person);
        }
        return  persons;
    }

    @Override
    public Person getPersonById(int id) {
        Person person = null;
        String sql = "SELECT * FROM person WHERE person_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);
        if(result.next()) {
            person = mapRowToPerson(result);


        }
        return person;

    }

    @Override
    public List<Person> getPersonsByName(String name, boolean useWildCard) {
        List<Person> persons = new ArrayList<>();
        String wildCard = useWildCard ? "%" : "";
        String sql = "SELECT * FROM person" +
                " WHERE person_name ILIKE ?;";
       if(useWildCard) {
           name = "%" + name + "%";

       }

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, name);
        while (results.next()) {
            Person person = mapRowToPerson(results);
            persons.add(person);
        }
        return persons;
    }

    @Override
    public List<Person> getPersonsByCollectionName(String collectionName, boolean useWildCard) {
        List<Person> persons = new ArrayList<>();

        String sql = "SELECT DISTINCT person.person_id, person.person_name, person.birthday, person.deathday, person.biography, person.profile_path, person.home_page " +
                "FROM person " +
                "JOIN movie_actor ON person.person_id = movie_actor.actor_id " +
                "JOIN movie ON movie.movie_id = movie_actor.movie_id " +
                "JOIN collection c ON movie.collection_id = c.collection_id " +
                "WHERE c.collection_name ILIKE ? " +
                "ORDER BY person.person_name";


        if(useWildCard) {
            collectionName = "%" + collectionName + "%";

        }

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, collectionName);
        while (results.next()) {
            persons.add(mapRowToPerson(results));

        }
        return persons;
    }
  private Person mapRowToPerson(SqlRowSet result) {
        Person person = new Person();
        person.setId(result.getInt("person_id"));
        person.setName(result.getString("person_name"));
        person.setBirthday(result.getDate("birthday") != null
          ? result.getDate("birthday").toLocalDate()
          : null);
        person.setDeathDate(result.getDate("deathday")!= null
          ? result.getDate("deathday").toLocalDate()
          : null);
        person.setBiography(result.getString("biography"));
        person.setProfilePath(result.getString("profile_path"));
        person.setHomePage(result.getString("home_page"));
        return person;
}


}





