package com.techelevator.dao;

import com.techelevator.model.Cart;
import com.techelevator.model.CartItem;

import java.security.Principal;
import java.util.List;

public interface CartDao {

        Cart getCartForUser(int userId);
        boolean addItemToCart(int userId, int productId, int quantity);

        // Adding a product to the user's cart or updates quantity if it already exists
        List<CartItem> addItemToCart(CartItem cartItem, Principal principal);

        boolean removeItemFromCart(int cartItemId);
        boolean clearCart(int userId);
        boolean updateTaxAmount(int userId, String userAddress, String stateCode);
}

