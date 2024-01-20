package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Book;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;

public class JdbcBookDao implements BookDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcBookDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Book getBookById(int bookId) {
        try {
            //here initializing to hold our book data
            Book book = null;
            // SQL queries to select a book by its ID
            String sql = "SELECT book_id, book_title, star_rating, out_of_print, foreword_by, publisher_id, published_date" +
                    " FROM book WHERE book_id = ?;";

            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, bookId);
            if (results.next()) {
                book = mapRowToBook(results);
            }

            return book;
        } catch (DataAccessException e) {
            // any issue accessing the database need to throw an exception
            throw new DaoException("Error getting book by ID", e);
        }

    }

    @Override
    public Book createBook(Book newBook) {
        try {
            // Need to insert the sql query for a new book and return its generated ID
            String sql = "INSERT INTO book (book_title, star_rating, out_of_print, foreword_by, publisher_id, published_date) " +
                    "VALUES (?, ?, ?, ?, ?, ?) RETURNING book_id;";
            //Executing the insert and getting the new books ID
            Integer newId = jdbcTemplate.queryForObject(sql, Integer.class, newBook.getBookTitle(), newBook.getStarRating(), newBook.isOutOfPrint(), newBook.getForewordBy(), newBook.getPublisherId(), newBook.getPublishedDate());
             //Receive and return newly made book
            return getBookById(newId);
         } catch (DataAccessException e) {
            throw new DaoException("Error creating new book", e);
        }
    }

    @Override
    public int deleteBookById(int bookId) {
        try {
            // Sql query to delete then make it related enteries in the book_author table
            String deleteBookAuthorSql = "DELETE FROM book_author WHERE book_id = ?;";
            jdbcTemplate.update(deleteBookAuthorSql, bookId);

            // SQL query to delete the book
            String deleteBookSql = "DELETE FROM book WHERE book_id = ?;";

            // First we need to excetute the delete operation and return the number of rows affected
            return jdbcTemplate.update(deleteBookSql, bookId);
        } catch (DataAccessException e) {
            // If a problem is present accessing the database or a data integrity issue, we need to throw a DaoException
            throw new DaoException("Error deleting book with ID " + bookId, e);
        }
    }


    private Book mapRowToBook(SqlRowSet results) {
        Book book = new Book();
        book.setBookId(results.getInt("book_id"));
        book.setBookTitle(results.getString("book_title"));
        book.setStarRating(results.getBigDecimal("star_rating"));
        book.setOutOfPrint(results.getBoolean("out_of_print"));
        book.setForewordBy((Integer) results.getObject("foreword_by"));
        book.setPublisherId(results.getInt("publisher_id"));
        book.setPublishedDate(results.getDate("published_date").toLocalDate());
        return book;
    }
}



