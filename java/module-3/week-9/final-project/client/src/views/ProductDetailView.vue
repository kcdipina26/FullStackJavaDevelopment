<template>
  <div v-if="!isLoading" class="product-detail">
    <div class="product-detail-header">
      <span class="product-detail-sku">{{ product.productSku }}</span>
      <!-- Use the computed property directly without calling it as a function -->
      <span class="product-detail-price">{{ formattedPrice }}</span>
    </div>
    <h1 class="product-detail-name">{{ product.name }}</h1>
    <div class="product-detail-image-container">
      <img :src="product.imageName" alt="Product Image" class="product-detail-image">
    </div>
    <p class="product-detail-description">{{ product.description }}</p>
    <button v-if="isUserAuthenticated" @click="addProductToCart" class="add-to-cart-btn">
      Add to Cart
    </button>
    <!-- Make sure to bind the v-if to a property or condition -->
    <p v-if="message" class="user-message">{{ message }}</p>
  </div>
  <div v-else>
    <p>Loading...</p>
  </div>
</template>
  
  <script>
  import { mapGetters } from 'vuex';
import ProductService from '@/services/ProductService';
import CartService from '@/services/CartService';
  
  export default {
  data() {
    return {
      product: null,
      isLoading: true,
      message: ''
    };
  },
  computed: {
    ...mapGetters(['isUserAuthenticated']),

    // Computed property for formatted price
    formattedPrice() {
      
      return this.product && this.product.price
        ? `$${this.product.price.toFixed(2)}`
        : '';
    },
  },
  methods: {
    async getProduct() {
      try {
        const response = await ProductService.getProductById(this.$route.params.id);
        this.product = response.data;
      } catch (error) {
        this.message = 'Error fetching product details.';
        console.error(error);
      } finally {
        this.isLoading = false;
      }
    },
    async addProductToCart() {
      if (!this.isUserAuthenticated) {
        this.message = 'Please log in to add items to your shopping cart.';
        return;
      }
      try {
        const response = await CartService.addProductToCart(this.product.productId);
        if (response.status === 200) {
          this.message = 'Item added to cart successfully.';
          
        }
      } catch (error) {
        this.message = 'Error adding product to cart.';
        console.error(error);
      }
    }
  },
  created() {
    this.getProduct();
  }
};
</script>

  <style scoped>
.product-detail {
  max-width: 500px; 
  margin: 0 auto; 
  padding: 20px;
  box-shadow: 0 2px 4px rgba(0,0,0,0.1); 
}

.product-detail-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100%;
  max-width: 800px; 
  margin-bottom: 190px;
}

.product-detail-sku {
  font-size: 0.9rem;
  color: #666;
}

.product-detail-price {
  font-size: 1.4rem;
  font-weight: bold;
  color: #333;
}

.product-detail-name {
  font-size: 2rem;
  color: #333;
  margin-bottom: 10px;
}

.product-detail-image-container {
  text-align: center; 
  margin-bottom: 40px;
}

.product-detail-image {
  max-width: 60%; 
  height: auto;
}

.product-detail-description {
  font-size: 2rem;
  color: #666;
  margin-bottom: 20px;
  line-height: 1.5;
}

.add-to-cart-btn {
  background-color: gold; 
  color: white;
  padding: 10px 20px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-size: 1rem;
  transition: background-color 0.3s ease;
}

.add-to-cart-btn:hover {
  background-color: black; 
}
.user-message {
  color: blueviolet; 
  font-size: 1rem;
  margin-top: 20px;
}
</style>
