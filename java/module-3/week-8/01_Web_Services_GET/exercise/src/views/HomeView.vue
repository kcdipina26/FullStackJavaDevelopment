<template>
  <div class="loading" v-if="isLoading">
    <p>Loading...</p>
  </div>
  <div v-else>
    <header class="flex">
      <h1>Topics</h1>
    </header>
    <topic-list v-bind:topics="topics"/>
  </div>
</template>

<script>
import { listTopics } from '../services/TopicService.js';
import TopicList from '../components/TopicList.vue';

export default {
  components: {
    TopicList
  },
  data() {
    return {
      topics: [],
      isLoading: true
    };
  },
  methods: {
    getTopics() {

      // TODO - Get data from API and set `topics` property
      listTopics()
        .then(topics => {
          this.topics = topics;
          this.isLoading = false;
        })
        .catch(error => {
          console.error('Failed to load topics:', error);
          // Here you can handle the error, such as showing a message to the user
          this.isLoading = false;
        });
    },
  },
  created() {
    this.getTopics();
  }
}
</script>

<style scoped>
</style>