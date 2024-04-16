<template>
  <footer id="app-footer">
      <h4>Images courtesy
        <span id="nasa-link"><a href="https://images.nasa.gov/"> NASA</a></span>
      </h4>
    </footer>
</template>

<script>
export default {

}
</script>

<style scoped>
#app-footer {
  color: #F9F6F0;
  font-weight: bold;
  margin-bottom: 1rem;
  padding: 1rem;
  text-align: center;
  text-shadow: 2px 2px #0962f1;
}

#nasa-link, :link, :visited, :focus, :active {
  text-shadow: none;
  color: #F9F6F0;
}
</style>