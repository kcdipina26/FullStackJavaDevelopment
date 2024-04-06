import axios from 'axios';

const API_URL = 'http://localhost:9000';

export default {
  getItemsInCart() {
    return axios.get(`${API_URL}/cart`);
  },
  addProductToCart(productId, quantity = 1) { 
    const cartItem = {
      productId: productId,
      quantity: quantity
    };
    return axios.post(`${API_URL}/cart/item`, cartItem);
  },
  removeProductFromCart(cartItemId) {
    return axios.delete(`${API_URL}/cart/item/${cartItemId}`);
  },
  removeAllProductsFromCart() {
    return axios.delete(`${API_URL}/cart`);
  }
};
