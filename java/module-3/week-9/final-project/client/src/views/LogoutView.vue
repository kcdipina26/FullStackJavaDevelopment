<template>
  <h1>Logout</h1>
</template>

<script>
export default {
  created() {
    this.$store.commit("LOGOUT");
    this.$router.push("/login");
  }
};
</script>

<style scoped>
.logout-container {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 100vh; /* Use the full height of the viewport */
  background-color: #ffdab9; /* Peach background */
}

h1 {
  font-size: 2rem;
  color: #333;
  text-align: center;
}
</style>