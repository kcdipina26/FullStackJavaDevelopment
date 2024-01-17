package com.techelevator.dao;

import com.techelevator.model.CartItem;
import java.util.List;

public interface CartItemDao {
    CartItem getCartItemById(int cartItemId);
    List<CartItem> getCartItemsByCartId(int cartId);
    boolean updateCartItemQuantity(int cartItemId, int quantity);
    boolean deleteCartItem(int cartItemId);

    int getUserIdByCartItemId(int cartItemId);
}
