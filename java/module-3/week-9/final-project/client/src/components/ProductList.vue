<template>
  <div>
    <div class="cart-icon">
      <i class="fa fa-shopping-cart" @click="goToCart"></i>
    </div>
    <div v-if="isCardView" class="products-grid">
      <product-card
        v-for="product in products"
        :key="product.productId"
        :product="product"
      />
    </div>

    <table v-else class="products-table">
      <thead>
        <tr>
          <th>SKU</th>
          <th>Name</th>
          <th>Price</th>
          <th>Add to Cart</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="product in products" :key="product.productId">
          <td>{{ product.productSku }}</td>
          <td>{{ product.name }}</td>
          <td>{{ product.price }}</td>
          <td>
            <i v-if="isUserAuthenticated" class="fa fa-shopping-cart add-to-cart-icon" @click="addToCart(product.productId)"></i>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import ProductCard from '@/components/ProductCard.vue';
import CartService from '@/services/CartService';

import { mapGetters } from 'vuex';

export default {
  components: {
    ProductCard
  },
  props: {
    products: Array,
    isCardView: Boolean 
  },
  computed: {
    ...mapGetters(['isUserAuthenticated']) 
  },
  methods: {
    goToCart() {
      this.$router.push('/cart');
    },
    async addToCart(productId) {
      
      if (this.isUserAuthenticated) {
        try {
          await CartService.addProductToCart(productId);
          
        } catch (error) {
          // Handle the error case
          console.error('Error adding product to cart:', error);
        }
      } else {
                            //  the case where the user is not authenticate
      }
    }
  }
};
</script>

<style scoped>
  .add-to-cart-icon {
    cursor: pointer;

  }
</style>
