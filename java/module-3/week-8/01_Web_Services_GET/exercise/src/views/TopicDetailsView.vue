<template>
  <div class="loading" v-if="isLoading">
    <p>Loading...</p>
  </div>
  <div v-else>
    <nav>
      <router-link v-bind:to="{ name: 'HomeView' }">Back to Topic List</router-link>
    </nav>
    <topic-details v-bind:topic="topic" />
  </div>
</template>

<script>
import TopicDetails from '../components/TopicDetails.vue';
import { getTopic } from '../services/TopicService.js';
export default {
  components: {
    TopicDetails
  },
  data() {
    return {
      topic: {},
      isLoading: true
    }
  },
  methods: {
    getTopicDetails(id) {
    // TODO - Get data from API and set `topics` property
    getTopic(id)
        .then(topicDetails => {
          this.topic = topicDetails;
          this.isLoading = false;
        })
        .catch(error => {
          console.error('Failed to load topic details:', error);
          this.isLoading = false;
          // Handle the error, such as showing an error message
        });
    },
  },
  created() {
    this.getTopicDetails(this.$route.params.topicId);
  }
};
</script>

<style scoped>
</style>