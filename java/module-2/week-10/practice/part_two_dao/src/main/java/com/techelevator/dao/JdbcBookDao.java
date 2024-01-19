package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Book;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class JdbcBookDao implements BookDao {

    private final JdbcTemplate jdbcTemplate;


    public JdbcBookDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Book> getBooks() throws DaoException {
            List<Book> books = new ArrayList<>();
            String sql = "SELECT book_id, book_title, star_rating, out_of_print, foreword_by, publisher_id, published_date FROM book";
            try {
                SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
                while(results.next()) {
                    books.add(mapRowToBook(results));
                }
            } catch (DataAccessException e) {
                throw new DaoException("Error getting all books", e);
            }
            return books;
    }

    @Override
    public Book getBookById(int bookId) throws DaoException {
        String sql = "SELECT book_id, book_title, star_rating, out_of_print, foreword_by, publisher_id, published_date FROM book WHERE book_id = ?";
        Book book = null;
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, bookId);
            if (results.next()) {
                book = mapRowToBook(results);

            }
        } catch (DataAccessException e) {
            throw new DaoException("Error getting book with ID " + bookId, e);
        }

        return book;
    }

    @Override
    public Book updateBookRating(int bookId, BigDecimal newRating) {

        // First, check if the book exists
        Book existingBook = getBookById(bookId);
        if (existingBook == null) {
            throw new DaoException("No book found with id " + bookId);
        }

        // Perform the update
        String updateSql = "UPDATE book SET star_rating = ? WHERE book_id = ?";
        jdbcTemplate.update(updateSql, newRating, bookId);

        // Retrieve and return the updated book
        return getBookById(bookId);
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
