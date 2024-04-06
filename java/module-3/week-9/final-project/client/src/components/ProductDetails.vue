<template>
  <div v-if="product" class="product-detail-container">
    <div class="product-detail">
      <div class="product-image-container">
        <img :src="product.imageName" alt="Product Image" class="product-image">
      </div>
      <div class="product-info">
        <h2 class="product-name">{{ product.name }}</h2>
        <p class="product-description">{{ product.description }}</p>
        <span class="product-price">{{ '$' + product.price.toFixed(2) }}</span>
        <button v-if="isUserAuthenticated" @click="addProductToCart" class="add-to-cart-btn">
              Add to Cart
                </button>
      </div>
    </div>
  </div>
  <div v-else>
    <p>Product not found.</p>
  </div>
</template>

<script>
import { mapGetters } from 'vuex';  
import ProductService from '@/services/ProductService.js';
import CartService from '@/services/CartService.js';

export default {
  data() {
    return {
      product: null,
      isLoading: false,
    };
  },
  computed: {
    ...mapGetters(['isUserAuthenticated']),
   
  },
  methods: {
    async fetchProduct() {
      this.isLoading = true;
      try {
        const response = await ProductService.getProductById(this.$route.params.id);
        this.product = response.data;
      } catch (error) {
        console.error('Error fetching product:', error);
      } finally {
        this.isLoading = false;
      }
    },
    async addProductToCart() {
      if (!this.isUserAuthenticated) {
        alert('You must log in to add items to your shopping cart.');
        return;
      }
      try {
        const response = await CartService.addProductToCart(this.product.productId);
        if (response.status === 200) {
          this.$emit('updateCart');
          alert('Item Added Successfully');
        }
      } catch (error) {
        console.error('Error in addProductToCart:', error);
        alert('There was a problem adding the item to the cart.');
      }
    },
  },
  created() {
    this.fetchProduct();
  }
};
</script>

<style scoped>
#product-card {
  
  display: flex;
  flex-wrap: wrap;
  gap: 1rem;
  justify-content: space-around;
}
</style>