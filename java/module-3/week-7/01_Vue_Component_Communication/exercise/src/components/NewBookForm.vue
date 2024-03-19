<template>

    <form @submit.prevent="submitNewBook" class="new-book-form">
      <input
        class="title-input"
        type="text"
        placeholder="Title"
        v-model="newBook.title"
        required
      />
      <input
        class="author-input"
        type="text"
        placeholder="Author"
        v-model="newBook.author"
        required
      />
      <input
        class="isbn-input"
        type="text"
        placeholder="ISBN"
        v-model="newBook.isbn"
        required
      />
      <button type="submit">Add Book</button>
    </form>
  </template>
  
  <script>
  export default {
    name: "new-book-form",
    data() {
      return {
        newBook: {
          title: '',
          author: '',
          isbn: '',
          read: false, 
        },
      };
    },
    methods: {
      submitNewBook() {
        if (this.newBook.title && this.newBook.author && this.newBook.isbn) {
          this.$store.commit("addBook", this.newBook);
          this.resetForm();
        } else {
          alert("The book you're adding is missing data.");
        }
      },
      resetForm() {
        this.newBook = {
          title: '',
          author: '',
          isbn: '',
          read: false,
        };
      },
    },
  };
  </script>
  
  <style scoped>
  form > * {
    display: block;
    margin-bottom: 8px;
  }
  
 
  .title-input, .author-input, .isbn-input {
    padding: 8px;
  }
  
  button {
    padding: 8px 16px;
    background-color: #42b983;
    color: white;
    border: none;
    cursor: pointer;
  }
  </style>
  