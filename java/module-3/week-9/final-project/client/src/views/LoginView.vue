<template>
  <div id="login">
    <form v-on:submit.prevent="login">
      <h1>Please Sign In</h1>
      <div id="fields">
        <label for="username">Username</label>
        <input
          type="text"
          id="username"
          placeholder="Username"
          v-model="user.username"
          required
          autofocus
        />
        <label for="password">Password</label>
        <input
          type="password"
          id="password"
          placeholder="Password"
          v-model="user.password"
          required
        />
        <div><button type="submit">Sign in</button></div>
      </div>
      <hr/>
      Need an account? <router-link v-bind:to="{ name: 'register' }">Register!</router-link>
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  data() {
    return {
      user: {
        username: "",
        password: "",
      },
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then((response) => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch((error) => {
          const response = error.response;
          if (!response) {
            alert(error);
          } else if (response.status === 401) {
            alert("Invalid username and password!");
          } else {
            alert(response.message);
          }
        });
    },
  },
};
</script>

<style scoped>



#login {
  max-width: 300px;
  margin: 50px auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 40px;
  box-shadow: 0 2px 5px rgba(0,0,0,0.2);
  background-color: lightpink; 
}

#login h1 {
  color: #333;
  font-size: 24px;
  text-align: center;
  margin-bottom: 20px;
}

#login label {
  display: block;
  font-weight: bold;
  margin-top: 30px;
}

#login input[type='text'],
#login input[type='password'] {
  width: 100%;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
  box-sizing: border-box; 
}

#login button {
  width: 100%;
  padding: 10px 0;
  border: none;
  border-radius: 5px;
  background-color: blueviolet; 
  color: white;
  font-size: 16px;
  cursor: pointer;
  margin-top: 40px;
}

#login button:hover {
  background-color: #9acfe7;
}

#login hr {
  margin-top: 56px;
  margin-bottom: 56px;
}

#login .router-link-active {
  color: #5cb85c;
  text-decoration: none;
}

#footer {
  margin-top: auto; 
  text-align: right; 
}
</style>
