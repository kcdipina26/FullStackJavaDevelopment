<template>
  <loading-spinner v-if="isLoading"/>
  <div v-else class="view-container">
    <header class="view-header">
    </header>
    <div id="view-content">
      <div class="error" v-show="error">
        <p>Sorry! Something unexpected happened. {{error}} Please try again later.</p>
      </div>
      <celestial-object-details v-show="!error" v-bind:celestialObject="celestialObjectData" />
    </div>
  </div>
</template>

<script>
import CelestialObjectService from "../services/CelestialObjectService";
import CelestialObjectDetails from "../components/CelestialObjectDetails.vue";
import LoadingSpinner from '../components/LoadingSpinner.vue';

export default {
  components: { LoadingSpinner, CelestialObjectDetails },
  data() {
    return {
      error: null,
      isLoading: true,
      celestialObjectId: 1005,
      celestialObjectData: {}
    }
  },
  created() {
    this.isLoading = true;
    CelestialObjectService.getCelestialObjectById(this.celestialObjectId)
    .then(response => {
     this.celestialObjectData = response.data;
        this.isLoading = false;
    })
    .catch((error) => {
      this.isLoading = false;
      this.error = `Could not get celestial object for for id ${this.celestialObjectId}.`;
      console.log(this.error, error.response);
     });
   }
}
</script>

<style>

</style>