package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Cart;
import com.techelevator.model.CartItem;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class JdbcCartDao implements CartDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcCartDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Cart getCartForUser(int userId) {
        String sql = "SELECT cart_item_id, product_id, quantity FROM cart_item WHERE user_id = ?";
        List<CartItem> cartItems = jdbcTemplate.query(sql, new Object[]{userId}, new CartItemRowMapper());
        return new Cart(userId, cartItems);
    }

    @Override
    public boolean addItemToCart(int userId, int productId, int quantity) {
        try {
            String insertSql = "INSERT INTO cart_item (user_id, product_id, quantity) VALUES (?, ?, ?) " +
                    "ON CONFLICT (user_id, product_id) DO UPDATE SET quantity = cart_item.quantity + EXCLUDED.quantity";
            jdbcTemplate.update(insertSql, userId, productId, quantity);
            return true; // Success
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data Integrity Violation", e);
        }
    }

    @Override
    public List<CartItem> addItemToCart(CartItem cartItem, Principal principal) {
        return null;
    }

    @Override
    public boolean removeItemFromCart(int cartItemId) {
        String deleteSql = "DELETE FROM cart_item WHERE cart_item_id = ?";
        return jdbcTemplate.update(deleteSql, cartItemId) > 0;
    }

    @Override
    public boolean clearCart(int userId) {
        String deleteSql = "DELETE FROM cart_item WHERE user_id = ?";
        return jdbcTemplate.update(deleteSql, userId) > 0;
    }

    @Override
    public boolean updateTaxAmount(int userId, String userAddress, String stateCode) {

        return true;
    }

    private static class CartItemRowMapper implements RowMapper<CartItem> {
        @Override
        public CartItem mapRow(ResultSet rs, int rowNum) throws SQLException {
            CartItem cartItem = new CartItem();
            cartItem.setCartItemId(rs.getInt("cart_item_id"));
            cartItem.setProductId(rs.getInt("product_id"));
            cartItem.setQuantity(rs.getInt("quantity"));
            return cartItem;
        }
    }
}