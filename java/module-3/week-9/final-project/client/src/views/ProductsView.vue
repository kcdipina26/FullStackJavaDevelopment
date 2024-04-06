<template>
  <div class="view-container">
    <header class="view-header">
      <h1>Products</h1>
      <font-awesome-icon
  :class="{ 'view-icon': true, active: cardView }"
  @click="toggleView(true)"
  icon="fa-solid fa-grip"
  title="View tiles"
/>
<font-awesome-icon
  :class="{ 'view-icon': true, active: !cardView }"
  @click="toggleView(false)"
  icon="fa-solid fa-table"
  title="View table"
/>
      <search-bar @search-input="onSearch" />
      <loading-spinner :spin="isLoading" />

    </header>
    <p v-if="!isLoggedIn">
      Welcome to Solar Geeks! You may browse anonymously as much as you wish,
      but you must <router-link to="/login">login</router-link> to add items to your shopping cart.
    </p>
    <div v-if="error" class="error">
      <p>Sorry! Something unexpected happened. {{ error }} Please try again later.</p>
    </div>
    <div v-else-if="!isLoading" class="product-container">
      <product-list :products="filteredProducts" :isCardView="cardView" />
    </div>
  </div>
</template>

<script>
import SearchBar from '@/components/SearchBar.vue';
import ProductList from '@/components/ProductList.vue';
import ProductService from '@/services/ProductService.js';
import LoadingSpinner from '@/components/LoadingSpinner.vue';
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'; 

export default {
  components: {
    SearchBar,
    ProductList,
    LoadingSpinner,
    FontAwesomeIcon
  },
  data() {
    return {
      products: [],
      searchTerm: '',
      searchItems: [],
      isLoading: false,
      error: null,
      cardView: true,
    };
  },
  computed: {
    filteredProducts() {
      if (!this.searchTerm) return this.products;
      const lowerCaseSearchTerm = this.searchTerm.toLowerCase();
      return this.products.filter(product =>
        product.name.toLowerCase().includes(lowerCaseSearchTerm) ||
        product.productSku.toLowerCase().includes(lowerCaseSearchTerm)
      );
    },
    isLoggedIn() {
     
      return this.$store.state.token.length > 0;
    },
  },
  methods: {
    onSearch(searchTerm) {
    
      this.searchTerm = searchTerm;
    },
    fetchProducts() {
      this.isLoading = true;
      ProductService.getProducts()
        .then(response => {
          this.products = response.data;
          this.isLoading = false;
        })
        .catch(error => {
          this.error = 'Error fetching products.';
          this.isLoading = false;
          console.error(this.error, error.response);
        });
    },
    toggleView(viewMode) {
      this.cardView = viewMode;
    },
  },
  created() {
    this.fetchProducts();
  }
};
</script>