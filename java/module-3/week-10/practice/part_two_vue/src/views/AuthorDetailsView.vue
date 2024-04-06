<template>
  <loading-spinner v-if="isLoading"/>
  <div v-else class="view-container">
    <header class="view-header">
      <h1>Author Details</h1>
    </header>
    <div id="view-content">
      <div class="error" v-show="error">
        <p>Sorry! Something unexpected happened. {{error}} Please try again later.</p>
      </div>
      <author-details v-show="!error" v-bind:author="authorData" />
    </div>
  </div>
</template>

<script>
import LoadingSpinner from '../components/LoadingSpinner.vue';
import AuthorDetails from '../components/AuthorDetails.vue';
import BookService from '../services/BookService';



export default {
  components: { LoadingSpinner, AuthorDetails },
  data() {
    return {
      error: null,
      isLoading: true,
      authorId: this.$route.params.authorId,
      authorData: {}
        
      }
    
  },
  methods:{                // task 5: adding get author 
    getAuthor() {
      BookService.getAuthorDetails(this.authorId).then(response => {
        this.authorData = response.data;
        this.isLoading = false;
      });
    }
  },
  created() {
    this.getAuthor();
  }
}
</script>

<style>

</style>