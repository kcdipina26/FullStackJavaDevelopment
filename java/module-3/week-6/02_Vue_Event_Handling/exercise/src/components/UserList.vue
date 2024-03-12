<template>
  <div class="container">
    <table id="tblUsers">
      <thead>
        <tr>
          <th>&nbsp;</th>
          <th>First Name</th>
          <th>Last Name</th>
          <th>Username</th>
          <th>Email Address</th>
          <th>Status</th>
          <th>Actions</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>
            <input
              type="checkbox"
              id="selectAll"
              v-on:change="toggleCheckboxes"
              v-bind:checked="selectAllChecked"
            />
          </td>
          <td>
            <input
              type="text"
              id="firstNameFilter"
              v-model="filter.firstName"
            />
          </td>
          <td>
            <input type="text" id="lastNameFilter" v-model="filter.lastName" />
          </td>
          <td>
            <input type="text" id="usernameFilter" v-model="filter.username" />
          </td>
          <td>
            <input type="text" id="emailFilter" v-model="filter.emailAddress" />
          </td>
          <td>
            <select id="statusFilter" v-model="filter.status">
              <option value>Show All</option>
              <option value="Active">Active</option>
              <option value="Disabled">Inactive</option>
            </select>
          </td>
          <td>&nbsp;</td>
        </tr>
        <tr
          v-for="user in filteredList"
          v-bind:key="user.id"
          v-bind:class="{ deactivated: user.status === 'Inactive' }"
        >
          <td>
            <input
              type="checkbox"
              v-on:click="toggleSelectUserId(user.id)"
              v-bind:id="user.id"
              v-bind:value="user.id"
              v-bind:checked="selectedUserIDs.includes(user.id)"
            />
          </td>
          <td>{{ user.firstName }}</td>
          <td>{{ user.lastName }}</td>
          <td>{{ user.username }}</td>
          <td>{{ user.emailAddress }}</td>
          <td>{{ user.status }}</td>
          <td>
            <button class="btnActivateDeactivate" v-on:click="flipStatus(user.id)">
              {{ user.status === 'Active' ? 'Deactivate' : 'Activate' }}
            </button>
          </td>
        </tr>
      </tbody>
    </table>

    <div class="all-actions">
      <button :disabled="actionButtonActive" v-on:click="activateSelectedUsers">
        Activate Users
      </button>
      <button
        :disabled="actionButtonActive"
        v-on:click="deactivateSelectedUsers"
      >
        Deactivate Users
      </button>
      <button :disabled="actionButtonActive" v-on:click="deleteSelectedUsers">
        Delete Users
      </button>
    </div>

    <button
      v-on:click="
        () => {
          showForm = !showForm;
          clearUser();
        }
      "
    >
      Add New User
    </button>

    <form v-on:submit.prevent="saveUser" id="frmAddNewUser" v-show="showForm">
      <div class="field">
        <label for="firstName">First Name:</label>
        <input type="text" name="firstName" v-model="newUser.firstName" />
      </div>
      <div class="field">
        <label for="lastName">Last Name:</label>
        <input type="text" name="lastName" v-model="newUser.lastName" />
      </div>
      <div class="field">
        <label for="username">Username:</label>
        <input type="text" name="username" v-model="newUser.username" />
      </div>
      <div class="field">
        <label for="emailAddress">Email Address:</label>
        <input type="text" name="emailAddress" v-model="newUser.emailAddress" />
      </div>
      <button type="submit" class="btn save">
        Save User
      </button>
    </form>
  </div>
</template>

<script>
export default {
  name: "user-list",
  data() {
    return {
      showForm: false,
      selectAllChecked: false,
      selectedUserIDs: [],
      filter: {
        firstName: "",
        lastName: "",
        username: "",
        emailAddress: "",
        status: "",
      },
      newUser: {
        firstName: "",
        lastName: "",
        username: "",
        emailAddress: "",
        status: "Active",
      },
      users: [
        {
          id: 1,
          firstName: "John",
          lastName: "Smith",
          username: "jsmith",
          emailAddress: "jsmith@gmail.com",
          status: "Active",
        },
        {
          id: 2,
          firstName: "Anna",
          lastName: "Bell",
          username: "abell",
          emailAddress: "abell@yahoo.com",
          status: "Active",
        },
        {
          id: 3,
          firstName: "George",
          lastName: "Best",
          username: "gbest",
          emailAddress: "gbest@gmail.com",
          status: "Inactive",
        },
        {
          id: 4,
          firstName: "Ben",
          lastName: "Carter",
          username: "bcarter",
          emailAddress: "bcarter@gmail.com",
          status: "Active",
        },
        {
          id: 5,
          firstName: "Katie",
          lastName: "Jackson",
          username: "kjackson",
          emailAddress: "kjackson@yahoo.com",
          status: "Active",
        },
        {
          id: 6,
          firstName: "Mark",
          lastName: "Smith",
          username: "msmith",
          emailAddress: "msmith@foo.com",
          status: "Inactive",
        },
      ],
    };
  },
  methods: {
    saveUser() {
      this.newUser.id =
        this.users.reduce((acc, user) => (acc < user.id ? user.id : acc), 0) +
        1;
      this.users.push(this.newUser);
      this.clearUser();
    },
    flipStatus(id) {
    this.users = this.users.map(user => {
      if (user.id === id) {
        user.status = user.status === 'Active' ? 'Inactive' : 'Active';
      }
      return user;
    });
  },
    flipStatusString(status, action = false) {
      if (action) {
        return status === "Active" ? "Inactive" : "Activate";
      }
      return status === "Active" ? "Inactive" : "Active";
    },
    toggleSelectUserId(id) {
      if (!this.selectedUserIDs.includes(id)) {
        this.selectedUserIDs.push(id);
        return;
      }
      this.selectedUserIDs.splice(this.selectedUserIDs.indexOf(id), 1); // Delete from arrray
      this.selectAllChecked = this.selectedUserIDs.length === this.users.length;
    },
    activateSelectedUsers() {
      // For tests to pass
      this.setStatusOfSelectedUsers(true);
    },
    deactivateSelectedUsers() {
      this.setStatusOfSelectedUsers(false);
    },
    setStatusOfSelectedUsers(active) {
      for (const user of this.users) {
        if (this.selectedUserIDs.includes(user.id)) {
          user.status = active ? "Active" : "Inactive";
        }
      }
      this.selectedUserIDs = [];
    },
    deleteSelectedUsers() {
      this.users = this.users.filter((user) => {
        return !this.selectedUserIDs.includes(user.id);
      });
    },
    toggleCheckboxes() {
      this.selectAllChecked = !this.selectAllChecked;
      console.log(this.selectAllChecked);
      this.selectedUserIDs = this.selectAllChecked
        ? this.users.map((u) => {
            return u.id;
          })
        : [];
      return this.selectedUserIDs.length === this.users.length;
    },
    clearUser() {
      this.newUser = {
        firstName: "",
        lastName: "",
        username: "",
        emailAddress: "",
        status: "Active",
      };
    },
  },
  computed: {
    
    actionButtonActive() {
      return this.selectedUserIDs.length === 0;
    },
    filteredList() {
      const compare = (string, compare) =>
        compare === "" || string.toLowerCase().includes(compare.toLowerCase());
      const filter = this.filter;
      return this.users.filter((u) => {
        return (
          compare(u.firstName, filter.firstName) &&
          compare(u.lastName, filter.lastName) &&
          compare(u.username, filter.username) &&
          compare(u.emailAddress, filter.emailAddress) &&
          compare(u.status, filter.status)
        );
      });
    },
  },
};
</script>

<style scoped>
table {
  margin-top: 20px;
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen,
    Ubuntu, Cantarell, "Open Sans", "Helvetica Neue", sans-serif;
  margin-bottom: 20px;
}
th {
  text-transform: uppercase;
}
td {
  padding: 10px;
}
tr.deactivated {
  color: red;
}
input,
select {
  font-size: 16px;
}

form {
  margin: 20px;
  width: 350px;
}
.field {
  padding: 10px 0px;
}
label {
  width: 140px;
  display: inline-block;
}
button {
  margin-right: 5px;
}
.all-actions {
  margin-bottom: 40px;
}
.btn.save {
  margin: 20px;
  float: right;
}
</style>