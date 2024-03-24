<template>
  <div class="loading" v-if="isLoading">
    <p>Loading...</p>
  </div>
  <div v-else>
    <nav>
      <router-link v-bind:to="{ name: 'TopicDetailsView', params: { topicId: topicId } }">Back to Topic Details</router-link>
    </nav>
    <message-details v-bind:message="message" />
  </div>
</template>

<script>
import MessageDetails from '../components/MessageDetails.vue';
import { getMessage} from  '../services/MessageService.js';
export default {
  components: {
    MessageDetails,
  },
  data(){
    return {
      topicId: this.$route.params.topicId,
      message: {},
      isLoading: true
    }
  },
  methods: {
    getMessageDetails(id) {
      
      // TODO - Get data from API and set `topics` property

      getMessage(id)
        .then(messageDetails => {
          this.message = messageDetails;
          this.isLoading = false;
        })
        .catch(error => {
          console.error('Failed to load message details:', error);
          this.isLoading = false;
        });
    },
  },
  created() {
    this.getMessageDetails(this.$route.params.messageId);
  }
};
</script>

<style scoped>
</style>
