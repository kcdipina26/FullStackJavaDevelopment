<template>
  <div id="celestial-object-app">
    <header id="app-header">
      <h1>Astronomy 101</h1>
    </header>
    <main id="main-content">
      <router-view />
    </main>
    <app-footer/>
  </div>
</template>

<script>
import '@/assets/reset.css'
import AppFooter from './components/AppFooter.vue'
export default {
  components: { AppFooter },
  name: 'App'
}
</script>

<style>
body {
  background-color: #2E2E38;
  font-family: Arial, Helvetica, sans-serif;
  font-size: 14px;
}

h1 {
  font-size: 1.5rem;
  margin-bottom: 1.5rem;
}

h2 {
  font-size: 1.3rem;
  margin-bottom: 1.5rem;
}

h3 {
  font-size: 1.15rem;
  margin-bottom: 1rem;
}

main {
  padding: 2rem;
}

.view-container {
  background-color: black;
  border: 1px solid #FF9000;
  color: #262640;
  margin: auto;
  max-width: 1600px;
  padding: 2rem;
}

.view-header {
  color: #262640;
  font-size: 1.5rem;
  font-weight: bold;
}

#app-header {
  background-color: black;
  border-bottom: 1px solid #d4d4dc;
  color: #F9F6F0;
  font-weight: bold;
  margin-bottom: 1rem;
  padding: 1rem;
  text-align: center;
  text-shadow: 1px 1px yellow;
}

#app-header h1 {
font-size: 2.2rem;
margin-bottom: 0;
}

</style>
