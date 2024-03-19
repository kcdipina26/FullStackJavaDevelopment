<template>
  <div class="book-container">
    <book-card v-for="(book, index) in books" :key="index" v-bind:book="book" />
  </div>
</template>

<script>
import BookCard from "./BookCard.vue";
import { mapState } from "vuex";

export default {
  computed: {
    books() {
      return this.$store.state.books;
    },
  },
  components: { BookCard },
  name: "reading-list",
};
</script>

<style>
.book-container {
  display: flex;
  justify-content: space-evenly;
  flex-wrap: wrap;
}
</style>
