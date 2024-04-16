<template>
  <div class="view-container">
    <header class="view-header">
    </header>
    <div id="view-content">
      <celestial-object-filter v-on:filterValueChange="filterCelestialObjects"/>
      <loading-spinner v-if="isLoading"/>
      <div class="error" v-else-if="error">
        <p>Sorry! Something unexpected happened. {{error}} Please try again later.</p>
      </div>
      <celestial-objects-list v-else v-bind:celestialObjectsList="celestialObjectsList" />

    </div>
  </div>
</template>

<script>
import celestialObjectsService from "../services/CelestialObjectService";
import LoadingSpinner from '../components/LoadingSpinner.vue';
import CelestialObjectsList from '../components/CelestialObjectsList.vue'
import CelestialObjectFilter from '../components/CelestialObjectFilter.vue'

export default {
  components: { LoadingSpinner,  CelestialObjectsList, CelestialObjectFilter },
  data() {
    return {
      error: null,
      isLoading: true,
      celestialObjectsList: []
    }
  },
  created() {
    this.isLoading = true;
      celestialObjectsService.getCelestialObjects()
       .then(response => {
        this.celestialObjectsList = response.data;
        this.isLoading = false;
         })
       .catch((error) => {
         this.isLoading = false;
         this.error = `Could not get the list of celestial objects.`;
         console.log(this.error, error.response);
     });
  },
  methods: {
    filterCelestialObjects(filter) {
      this.isLoading = true;
      this.error='';
      console.log(filter);
      celestialObjectsService.filterCelestialObjects(filter.nameString, filter.categoryList)
     .then(response => {
      this.celestialObjectsList = response.data;
      this.isLoading = false;
     })
    .catch((error) => {
      this.isLoading = false;
      this.error = `Could not get the celestial objects list.`;
      console.log(this.error, error.response);
    });
  }
}
}
</script>

<style>

</style>