<template>
    <div class="book-details">
      <h1>{{ book.title }}</h1>
      <h2>{{ book.author }}</h2>
      <img :src="'http://covers.openlibrary.org/b/isbn/' + book.isbn + '-L.jpg'" :alt="book.title" />
      <p>Number of pages: {{ book.numPages }}</p>
      <a :href="book.moreInfoLink" target="_blank">More Information</a>
      <p class="book-read">
      {{ book.read ? "I have read this book" : "I have not read this book" }}
    </p>
    </div>
  </template>
  
  <script>
  export default {
    props: {
      isbn: String,
    },
    computed: {
      book() {
        return this.$store.getters.getBookByIsbn(this.isbn);
      }
    }
  };
  </script>
  
  <style scoped>
  .book-details {
    margin: 10px;
  font-size: 1rem;
  }
  </style>
  