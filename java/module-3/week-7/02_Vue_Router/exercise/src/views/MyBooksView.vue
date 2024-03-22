<template>
    <div>
      <reading-list />
      <router-link to="/addBook" class="add-book-link">Add a New Book</router-link>
    </div>
  </template>
  
  <script>
  import ReadingList from "../components/ReadingList.vue";

  export default {
    components: { ReadingList },
  };
 
  </script>
  
  <style></style>
  