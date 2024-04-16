<template>
  <article>
    <section class="celestial-object-details">
      <h1 class=celestial-object-name>{{celestialObject.name}}</h1>

      <div class="celestial-object-property">
        <label>Categories:</label><span>{{categoryList}}</span>
      </div>
      <div class="celestial-object-property">
        <label>Distance:</label><p>{{celestialObject.distance}}</p>
      </div>
      <div class="celestial-object-property">
        <p>{{celestialObject.description}}</p>
      </div>
    </section>
    <aside class="large-image">
      <img v-bind:src="imageBaseUrl + celestialObject.image" v-bind:alt="celestialObject.name" />
    </aside>
  </article>
</template>

<script>

export default {
  props: ['celestialObject'],
  data() {
    return {
      imageBaseUrl: import.meta.env.VITE_APP_REMOTE_API
    }
  },
  computed: {

    categoryList() {
      return this.celestialObject.categories.map(g => g.name).join(', ');
    }
  }
}
</script>

<style scoped>
article {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-around;
  gap: 1.5rem;
  padding-bottom: 1.5rem;
}

h1 {
  font-size: 40px;
}

.return-link {
  font: bold 16px Arial;
  text-decoration: none;
  text-align: center;
  display: block;
  width: fit-content;
}

.celestial-object-name {
  font-weight: bold;
  margin-bottom: 2rem;
}

.large-image {
  display: flex;
  align-items: center;
  width: 40%;
  max-width: 500px;
}

img {
  width: 100%;
}

.celestial-object-details {
  background-color: black;
  color: white;
  padding: 2.5rem;
  width: 40%;
  font-size: 20px;
}

.celestial-object-property {
  display: flex;
  align-items: baseline;
  margin-bottom: 1rem;
}

label {
  font-size: 1.5rem;
  font-weight: bold;
  margin-right: 1rem;
}

p {
  max-width: 600px;
}
</style>