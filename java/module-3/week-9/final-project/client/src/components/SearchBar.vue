<template>
    <div class="search-bar">
      <input 
        type="text" 
        v-model="searchTerm" 
        @input="onSearchInput" 
        @keyup.enter="onSearchEnter" 
        placeholder="Search..."
        class="search-input"
      />
      <button @click="onSearchEnter" class="search-button">
        <font-awesome-icon icon="fa-solid fa-magnifying-glass" />
      </button>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        searchTerm: '',
      };
    },
    methods: {
      onSearchInput() {
        
        this.$emit('search-input', this.searchTerm);
      },
      onSearchEnter() {
        
        this.$emit('search-enter', this.searchTerm);
      }
    }
  };
  </script>
  
  <style scoped>
  .search-bar {
    display: flex;
    justify-content: right;
    margin-bottom: 1rem;
  }
  
  .search-input {
    padding: 0.5rem;
    font-size: 1rem;
    border: 1px solid #ccc;
    border-radius: 0.25rem 0 0 0.25rem;
    outline: none;
  }
  
  .search-button {
    padding: 0.5rem 1rem;
    background-color: violet;
    color: white;
    border: none;
    border-radius: 0 0.25rem 0.25rem 0;
    cursor: pointer;
  }
  
  .search-button:hover {
    background-color: pink;
  }
  
  .search-button:focus {
    outline: none;
  }
  
 
  </style>
  