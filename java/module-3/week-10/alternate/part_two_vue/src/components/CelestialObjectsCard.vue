<template>
  <div class="celestial-objects-card" v-on:click="$router.push(`/celestial-objects/${celestialObject.id}`);">
    <div class="small-image">
      <img v-bind:src="imageBaseUrl + celestialObject.image" v-bind:alt="celestialObject.name" />
    </div>
    <div class="celestial-object-info"> 
      <h1 class="celestial-object-name">{{celestialObject.name}}</h1>
      <div class="categories-container">
        <h2>Categories: 
          <span class = "category" v-for="category of celestialObject.categories" v-bind:key="category.id"> 
          {{category.name}}
          </span>
        </h2>
      </div>
      <h2> Distance: {{celestialObject.distance}}</h2>   
    </div>
  </div>
</template>

<script>
export default {
  props: ['celestialObject'],
  data() {
    return {
      imageBaseUrl: import.meta.env.VITE_APP_REMOTE_API
    }
  }

}
</script>

<style scoped>
.categories-container {
  display: flex;
  flex-wrap: wrap;
  gap: 0.5rem;
  justify-content: center;
}

.celestial-objects-card {
  background-color: #000;
  color: white;
  border: 1px solid #feda6a;
  padding: 1.5rem 0.5rem;
  text-align: center;
  width: 450px;
}

.celestial-object-name {
  font-size: 1.3rem;
  font-weight: bold;
  margin-bottom: 1.5rem;
}

.small-image {
  height: 200px;
  margin-bottom: 2rem;
}

img {
    border: 0px;
    height: 100%;
}

</style>