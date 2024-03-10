<template>
  <table id="tblUsers">
    <thead>
    <tr>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Username</th>
        <th>Email Address</th>
        <th>Status</th>
    </tr>
    </thead>
    <tbody>
      <tr>
        <td><input type="text" id="firstNameFilter" v-model="search.firstName"/>
        </td>
        <td><input type="text" id="lastNameFilter" v-model="search.lastName"/>
        </td>
        <td><input type="text" id="usernameFilter" v-model="search.username"/>
        </td>
        <td><input type="text" id="emailFilter" v-model="search.emailAddress"/>
        </td>
        <td>
          <select id="statusFilter" v-model="search.status">
            <option value="">Show All</option>
            <option value="Active">Active</option>
            <option value="Inactive">Inactive</option>
          </select>
        </td>
      </tr>
      <!-- user listing goes here -->
      <tr 
      v-for="u in filteredList"
      :key="u.username"
      :class="{inactive: u.status === 'Inactive'}"
      >
     <td>{{ u.firstName }}</td>
     <td>{{ u.lastName }}</td>
     <td>{{ u.username }}</td>
     <td>{{ u.emailAddress }}</td>
     <td>{{  u.status }}</td>
     </tr>
    </tbody>
  </table>
</template>

<script>
export default {
  name: "user-list",
  data() {
    return {
      users: [
        { firstName: 'John', lastName: 'Smith', username: 'jsmith', emailAddress: 'jsmith@gmail.com', status: 'Active' },
        { firstName: 'Anna', lastName: 'Bell', username: 'abell', emailAddress: 'abell@yahoo.com', status: 'Active' },
        { firstName: 'George', lastName: 'Best', username: 'gbest', emailAddress: 'gbest@gmail.com', status: 'Inactive' },
        { firstName: 'Ben', lastName: 'Carter', username: 'bcarter', emailAddress: 'bcarter@gmail.com', status: 'Active' },
        { firstName: 'Katie', lastName: 'Jackson', username: 'kjackson', emailAddress: 'kjackson@yahoo.com', status: 'Active' },
        { firstName: 'Mark', lastName: 'Smith', username: 'msmith', emailAddress: 'msmith@foo.com', status: 'Inactive' }
      ],
      search: {
        firstName:"",
        lastName: "",
        username: "",
        emailAddress: "",
        status: "",
      },
    };
  },
  computed: {
  filteredList() {
    return this.users.filter(user => {
      return Object.keys(this.search).every(key => {
        if (key === 'status' && this.search.status) {
          return user[key] === this.search.status;
        }
        const searchValue = this.search[key].toLowerCase();
        return searchValue === "" || user[key].toString().toLowerCase().includes(searchValue);
      });
    });
  },
},

}
</script>

<style scoped>
table {
  margin-top: 20px;
  font-family:-apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif
}
th {
  text-transform: uppercase
}
td {
  padding: 10px;
}
tr.inactive {
  color: red;
}
input, select {
  font-size: 16px;
}
</style>
