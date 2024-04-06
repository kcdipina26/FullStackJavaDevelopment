<template>
    <div class="cart">
      <h1>Shopping Cart</h1>
      <loading-spinner v-bind:spin="isLoading" />
      <button id="delete-all-button" v-on:click="removeAllItemsFromCart">Clear All</button>
      <table>
        <thead>
          <tr>
            <th>Qty</th>
            <th>Product</th>
            <th>Price</th>
            <th>Amount</th>
            <th></th> 
          </tr>
        </thead>
        <tbody>
          <tr v-for="item in cart.items" v-bind:key="item.cartItemId">
            <td>{{ item.quantity }}</td>
            <td>{{ item.product.name }}</td>
            <td>{{ item.product.price }}</td>
            <td>{{ item.quantity * item.product.price }}</td>
            <td>
              <button id="delete-button" v-on:click="removeItemFromCart(item.cartItemId)">Remove</button>
            </td>
          </tr>
        </tbody>
        <tfoot>
          <tr id='border'>
            <td colspan="3">Subtotal:</td>
            <td>{{ cart.itemSubtotal }}</td>
          </tr>
          <tr>
            <td colspan="3">Tax:</td>
            <td>{{ cart.tax }}</td>
          </tr>
          <tr>
            <td colspan="3">Total:</td>
            <td>{{ cart.total }}</td>
          </tr>
        </tfoot>
      </table>
    </div>
  </template>
  
  <script>
  import LoadingSpinner from '../components/LoadingSpinner.vue';
  import CartService from '../services/CartService'; 
  
  export default {
    components: { LoadingSpinner },
    data() {
      return {
        cart: {
          items: [],
          itemSubtotal: 0,
          tax: 0,
          total: 0,
        },
        isLoading: false,
      };
    },
    methods: {
      getCurrentCart() {
        this.isLoading = true;
        CartService.getItemsInCart().then(response => {
          this.cart = response.data;
          this.isLoading = false;
        }).catch(error => {
          this.isLoading = false;
          console.error("Error in getCurrentCart: ", error);
        });
      },
      removeItemFromCart(cartItemId) {
        this.isLoading = true;
        CartService.removeProductFromCart(cartItemId).then(() => {
          this.getCurrentCart();
        }).catch(error => {
          this.isLoading = false;
          console.error("Error in removeItemFromCart: ", error);
        });
      },
      removeAllItemsFromCart() {
        this.isLoading = true;
        CartService.removeAllProductsFromCart().then(() => {
          this.getCurrentCart();
        }).catch(error => {
          this.isLoading = false;
          console.error("Error in removeAllItemsFromCart: ", error);
        });
      },
    },
    created() {
      this.getCurrentCart();
    },
  };
  </script>
  
  <style scoped>
 .cart-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  min-height: 300vh; 
  padding: 2em;
}

.cart-header {
  margin-bottom: 1em;
}

.cart-content {
  width: 100%; 
  max-width: 600px; 
  margin: auto;
  border-collapse: collapse;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1); 
}

.cart-table {
  width: 100%;
  margin-bottom: 2em;
  border-collapse: collapse;
}

.cart-table th,
.cart-table td {
  text-align: left;
  padding: 0.5em;
  border-bottom: 1px solid #ddd;
}

.cart-footer {
  text-align: right;
}



.footer {
  margin-top: auto; 
}
</style>
  
  