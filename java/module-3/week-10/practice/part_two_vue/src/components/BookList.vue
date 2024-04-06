<template>
  <div id="card-container">
    <!-- <div v-for="book of bookList" v-bind:key="book.id" v-bind:book="book"> -->
     
      <book-card v-for="book of bookList" v-bind:key="book.id" v-bind:book="book"/>
    </div>
</template>

<script>
import BookCard from '../components/BookCard.vue'
export default {
  props: ['bookList'],

 components: {
  BookCard
 }
}
</script>

<style scoped>
#card-container {
  display: flex;
  flex-wrap: wrap;
  gap: 1rem;
  justify-content: space-around;
}
</style>