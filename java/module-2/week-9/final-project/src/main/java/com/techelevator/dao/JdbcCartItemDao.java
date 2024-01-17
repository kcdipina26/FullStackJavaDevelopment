package com.techelevator.dao;

import com.techelevator.model.CartItem;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JdbcCartItemDao implements CartItemDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcCartItemDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public CartItem getCartItemById(int cartItemId) {
        String sql = "SELECT cart_item_id, product_id, quantity FROM cart_item WHERE cart_item_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, cartItemId);
        if (results.next()) {
            return mapRowToCartItem(results);
        } else {
            return null; // Cart item not found
        }
    }

    @Override
    public List<CartItem> getCartItemsByCartId(int cartId) {
        return null;
    }

    @Override
    public boolean updateCartItemQuantity(int cartItemId, int quantity) {
        String sql = "UPDATE cart_item SET quantity = ? WHERE cart_item_id = ?";
        return jdbcTemplate.update(sql, quantity, cartItemId) == 1;
    }

    @Override
    public boolean deleteCartItem(int cartItemId) {
        String sql = "DELETE FROM cart_item WHERE cart_item_id = ?";
        return jdbcTemplate.update(sql, cartItemId) == 1;
    }

    private CartItem mapRowToCartItem(SqlRowSet rs) {
        CartItem cartItem = new CartItem();
        cartItem.setCartItemId(rs.getInt("cart_item_id"));
        cartItem.setProductId(rs.getInt("product_id"));
        cartItem.setQuantity(rs.getInt("quantity"));
        return cartItem;
    }
    @Override
    public int getUserIdByCartItemId(int cartItemId) {
        String sql = "SELECT user_id FROM cart_item WHERE cart_item_id = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, cartItemId);
    }

}

