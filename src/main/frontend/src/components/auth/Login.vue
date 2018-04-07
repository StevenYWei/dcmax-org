<template>
  <div class="login">
    <!-- <h2>{{'auth.welcome' | translate}}</h2> -->
    <h2>Welcome</h2>
    <form method="post" name="login">
      <div class="form-group">
        <div class="input-group">
          <input type="text" id="username" v-model="loginForm.username" required="required"/>
          <label class="control-label" for="username">Username</label><i class="bar"></i>
        </div>
      </div>
      <div class="form-group">
        <div class="input-group">
          <input type="password" id="password" v-model="loginForm.password" required="required"/>
          <label class="control-label" for="password">Passwrod</label><i class="bar"></i>
        </div>
      </div>
      <div class="d-flex flex-column flex-lg-row align-items-center justify-content-between down-container">
        <button class="btn btn-primary" type="submit" v-on:click.prevent="handleLogin">
          Login
        </button>
        <router-link class='link' :to="{name: 'Register'}">Register</router-link>
      </div>
    </form>
  </div>
</template>

<script>
export default {
  name: "login",
  data() {
    return {
      loginForm: {
        username: "dcmax20",
        password: "dcmax"
      },
      loginSuccess: ""
    };
  },
  methods: {
    handleLogin() {
      console.log(this.loginForm);
      this.$http
        .post("http://localhost:8080/users/login", this.loginForm)
        .then(function(data) {
          console.log(data);
          this.loginSuccess = data.bodyText;
        });
    }
  }
};
</script>

<style lang="scss">
@import "../../assets/sass/variables";
@import "~bootstrap/scss/mixins/breakpoints";
@import "~bootstrap/scss/functions";
@import "~bootstrap/scss/variables";
.login {
  @include media-breakpoint-down(md) {
    width: 100%;
    padding-right: 2rem;
    padding-left: 2rem;
    .down-container {
      .link {
        margin-top: 2rem;
      }
    }
  }
  h2 {
    text-align: center;
  }
  width: 21.375rem;
  .down-container {
    margin-top: 3.125rem;
  }
}
</style>
