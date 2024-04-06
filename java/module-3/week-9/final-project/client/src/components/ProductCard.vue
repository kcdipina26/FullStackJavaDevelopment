<template>
  <div class="product-card">
    <div class="product-card-header">
      <span class="product-sku">{{ product.productSku }}</span>
      <span class="product-price">{{ formatPrice(product.price) }}</span>
    </div>
    <h3 class="product-name" @click="goToProductDetails(product.productId)">
      {{ product.name }}
    </h3>
    <div class="product-image-container" @click="goToProductDetails(product.productId)">
      <img :src="product.imageName" alt="Product Image" class="product-image">
    </div>
    <!-- Shows the button only if the user is authenticated -->
    <button v-if="isUserAuthenticated" @click.stop="addProductToCart(product.productId)" class="add-to-cart-btn">
      <i class="fa fa-shopping-cart"></i> Add to Cart
    </button>
  </div>
</template>


<script>
import { mapGetters } from 'vuex';
import CartService from '@/services/CartService';

export default {
  props: {
    product: {
      type: Object,
      required: true
    }
  },
  computed: {
    ...mapGetters(['isUserAuthenticated']),
  },
  methods: {
    formatPrice(price) {
      return new Intl.NumberFormat('en-US', { style: 'currency', currency: 'USD' }).format(price);
    },
    async addProductToCart(productId) {
      if (!this.isUserAuthenticated) {
        alert('You must be logged in to add items to the cart.');
        return;
      }
      try {
        const response = await CartService.addProductToCart(productId);
        if (response && response.status === 200) {
          alert('Item Added Successfully');
          
        }
      } catch (error) {
        console.error('Error in addProductToCart:', error);
        alert('There was an error adding the item to the cart.');
      }
    },
    goToProductDetails(productId) {
      this.$router.push({ name: 'product-detail', params: { id: productId } });
    },
  },
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
