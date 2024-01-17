package com.techelevator.controller;

import com.techelevator.dao.CartDao;
import com.techelevator.dao.CartItemDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Cart;
import com.techelevator.services.TaxService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/cart")
@PreAuthorize("isAuthenticated()")
public class CartController {

    private final CartDao cartDao;
    private final CartItemDao cartItemDao;
    private final TaxService taxService;
    private final UserDao userDao;

    public CartController(CartDao cartDao, CartItemDao cartItemDao, TaxService taxService, UserDao userDao) {
        this.cartDao = cartDao;
        this.cartItemDao = cartItemDao;
        this.taxService = taxService;
        this.userDao = userDao;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Cart> getCart(@PathVariable int userId) {
        try {
            Cart cart = cartDao.getCartForUser(userId);
            if (cart == null) {
                return ResponseEntity.notFound().build();
            }
            updateCartTax(cart, userId);
            return ResponseEntity.ok(cart);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/items")
    public ResponseEntity<Cart> addItemToCart(@RequestParam int userId, @RequestParam int productId, @RequestParam int quantity) {
        if (cartDao.addItemToCart(userId, productId, quantity)) {
            return getCart(userId);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/items/{cartItemId}")
    public ResponseEntity<Cart> removeItemFromCart(@PathVariable int cartItemId) {
        int userId = cartItemDao.getUserIdByCartItemId(cartItemId);
        if (cartItemDao.deleteCartItem(cartItemId)) {
            return getCart(userId);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Cart> clearCart(@PathVariable int userId) {
        if (cartDao.clearCart(userId)) {
            return getCart(userId);
        }
        return ResponseEntity.badRequest().build();
    }

    private void updateCartTax(Cart cart, int userId) {
        String stateCode = userDao.getStateCodeByUserId(userId);
        BigDecimal taxRate = taxService.getTaxForState(userId);
        BigDecimal taxAmount = cart.getSubtotal().multiply(taxRate);
        cart.setTaxAmount(taxAmount);
    }
}
