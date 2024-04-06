<template>
  <div id="cart-app">
    <section id="header-nav">
      <header id="header">
        <img
          src="img/logo_400x70.png"
          alt="Project Logo"
          class="logo"
          id="headerLogo"
        />
      </header>
      <nav class="navBar">
        <div><router-link class="navBarLink" to="/">Home</router-link></div>
        <div><router-link class="navBarLink" to="/cart">Cart</router-link></div>
        
        <div class="nav-right">
          <router-link v-if="isLoggedIn" class="navBarLink" to="/logout">Logout</router-link>
          <router-link v-else class="navBarLink" to="/login">Login</router-link>
          
        </div>
      </nav>
    </section>

    <main id="main-content">
      <router-view />
    </main>
    <footer>@ 2024 | Solar Geek Online Store ❤ | All Rights Reserved.</footer>
  </div>
</template>

<script>

export default {
  computed: {
    isLoggedIn() {
      // We use this.$store?.state?.token to safely access the token.
      // The optional chaining (?) prevents errors if $store is not defined.
      // We check that the token is a string and has a length greater than 0.
      return typeof this.$store?.state?.token === 'string' && this.$store.state.token.length > 0;
    }
  }
};
</script>

<style scoped>
header {
  display: flex;
  justify-content: center;
  align-items: center;
}

</style>
