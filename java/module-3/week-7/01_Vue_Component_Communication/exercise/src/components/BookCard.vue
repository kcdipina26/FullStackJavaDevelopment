<template>
  <div :class="['card', { 'read': book.read }]">
    <h2 class="book-title">{{ book.title }}</h2>

<img
v-if="book.isbn"
v-bind:src="'http://covers.openlibrary.org/b/isbn/' + book.isbn + '-M.jpg'"
      alt="Book Cover"
      class="book-image"
/>

<h3 class="book-author">{{ book.author }}</h3>
<button
v-on:click="toggleBookRead(book)"
:class="book.read ? 'mark-unread' : 'mark-read'"
>
Mark {{ book.read  ? "Unread" : "Read" }}
</button>
  </div>
</template>

<script>
export default {
  name: "book-card",
  props: ['book'],
  methods: {
    toggleBookRead(book) {
      this.$store.commit("toggleBookRead", book);
    },
  },

};
</script>

<style>
.card {
  border: 2px solid black;
  border-radius: 10px;
  width: 250px;
  height: 550px;
  margin: 20px;
  position: relative;
}

.card.read {
  background-color: lightgray;
}

.card .book-title {
  font-size: 1.5rem;
}

.card .book-author {
  font-size: 1rem;
}

.book-image {
  width: 80%;
}

.mark-read, .mark-unread {
  display: block;
  position: absolute;
  bottom: 40px;
  width: 80%;
  left: 10%;
  font-size: 1rem;
}
</style>
