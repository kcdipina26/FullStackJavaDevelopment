import { createStore as _createStore } from 'vuex';
import axios from 'axios';
import ProductService from '../services/ProductService';

export function createStore(currentToken, currentUser) {
  let store = _createStore({
    state: {
      token: currentToken || '',
      user: currentUser || {},
      products: [],
      message: '',
    },
    mutations: {
      SET_AUTH_TOKEN(state, token) {
        state.token = token;
        localStorage.setItem('token', token);
        axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
      },
      SET_USER(state, user) {
        state.user = user;
        localStorage.setItem('user', JSON.stringify(user));
      },
      LOGOUT(state) {
        localStorage.removeItem('token');
        localStorage.removeItem('user');
        state.token = '';
        state.user = {};
        axios.defaults.headers.common = {};
      },
      SET_PRODUCTS(state, products) {
        state.products = products;
    },
    CLEAR_MESSAGE(state) {
      state.message = '';
    },
  },
  actions: {
    fetchProducts({ commit }) {
      ProductService.getAllProducts()
        .then(response => {
          commit('SET_PRODUCTS', response.data);
        })
        .catch(error => {
          console.error('Error fetching products:', error);
        });
      },
      addProductToCart({ commit }, product) {
        
        if (this.state.token) {
          commit('ADD_TO_CART', product);
        } else {
          // Handle the case for when a user is not authenticated
          console.error('User must be authenticated to add items to the cart.');
        }
      }, // will add other action like remove from cart and clear cart and so on.. //

    },
    getters: {
      allProducts: (state) => state.products,
      cartItems: (state) => state.cart,
      isUserAuthenticated: (state) => !!state.token,
    }, 
  });
  return store;
}
