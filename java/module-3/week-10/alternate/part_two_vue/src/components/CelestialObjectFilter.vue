!<template>
  <div id="celestial-object-filter">
    <h2>Filter</h2>
    <form v-on:submit.prevent="filter">
      <div class="form-group">
        <label for="search">Name:</label>
        <input type="text" placeholder="Search..." v-model="nameString" id="search" />
      </div>
      <div class="form-group" v-if="allCategories.length > 0">
        <label for="categories-list">Categories:</label>
        <select id="categories-list" v-model="categoryList" v-if="allCategories.length > 0" multiple>
          <option
            v-for="category of allCategories"
            v-bind:key="category.id"
            v-bind:value="category.name"
          >
            {{ category.name }}
          </option>
        </select>
      </div>
      <div class="button-group">
        <button type="submit">Filter</button>
        <button v-on:click="clear">Reset</button>
      </div>
    </form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      allCategories: [{"id":2200,"name":"Moon"},{"id":2201,"name":"Planet"},{"id":2202,"name":"Star"}],
      nameString: "",
      categoryList: [],
    };
  },
  methods: {
    filter() {
      console.log(this.nameString);
      console.log(this.categoryList);
      this.$emit("filterValueChange", {
        nameString: this.nameString,
        categoryList: this.categoryList,
      });
    },
    clear() {
      this.nameString = "";
      this.categoryList = [];
    },
  },
};
</script>

<style scoped>
#celestial-object-filter {
  background-color: #d4d4dc;
  color: black;
  margin: auto;
  border: 1px solid #d4d4dc;
  display: flex;
  flex-wrap: nowrap;
  align-items: top;
  gap: 2rem;
  margin-bottom: 2rem;
  padding: 1rem 2rem;
  width: fit-content;
}

form {
  display: flex;
  flex-wrap: wrap;
  gap: 2rem;
  justify-content: center;
  align-items: top;
}

.form-group {
  display: flex;
  align-items: baseline;
}

label {
  margin-right: 0.5rem;
}

h2 {
  font-size: 1.2rem;
  font-weight: bold;
  text-shadow: 1px 1px #fff;
}

input {
  background-color: #f9f6f0;
  border: 1px solid #51010f;
  display: inline-block;
  height: fit-content;
  margin-bottom: 1rem;
  padding: 0.25rem 0.75rem;
}

button {
  background-color: #e9e7e3;
  border: 1px solid #51010f;
  border-radius: 0.25rem;
  display: inline-block;
  height: fit-content;
  padding: 0.25rem 0.75rem;
  margin-bottom: 0.5rem;
  margin-right: 0.5rem;
  width: 100px;
}

button[type="submit"] {
  background-color: #feda6a;
}

select {
  background-color: #f9f6f0;
  border: 1px solid #51010f;
  display: inline-block;
}

option {
  padding: 0.25rem 0.5rem;
}
</style>
