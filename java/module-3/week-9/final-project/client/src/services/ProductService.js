// This service handles API calls related to products, including retrieving all products,
//  retrieving a product by its ID, and searching for products by name.
//  These functions match the requirements for interacting with the product data.


import axios from 'axios';

const API_URL = 'http://localhost:9000'; 

export default {
  getProducts() {
    return axios.get(`${API_URL}/products`); 
  },

  getProductById(productId) {
    return axios.get(`${API_URL}/products/${productId}`);
  },

  searchProducts(searchTerm) {
    // Implemented search functionality or filter results here
    return axios.get(`${API_URL}/products/search/${searchTerm}`);
  }
};