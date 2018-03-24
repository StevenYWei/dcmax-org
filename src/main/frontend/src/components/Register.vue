<template>
  <div id="reg">
    <h1>{{ formMsg }}</h1>
    <form>
      <div>User Name <input type="text" name="username" value="dcmax" v-model="registerForm.username"></div>
      <div>Password <input type="password" name="password" value="dcmax" v-model="registerForm.password"></div>
      <div>Confirm Password <input type="text" name="passwordConfirmation" value="dcmax" v-model="registerForm.passwordConfirmation"></div>
      <div>Email <input type="email" name="email" value="dcmax@dcmax.org" v-model="registerForm.email"></div>
      <div><input type="button" value="Register" v-on:click.prevent="handleSubmit"/></div>
      <div><label ><font color="red">{{ regSuccess }}</font></label></div>

    </form>
  </div>
</template>


<script>
export default {
  name: 'Register',
  data () {
    return {
      formMsg: 'Below is the register form',
      registerForm: {
        "username": 'dcmax1',
        "password": 'dcmax',
        "passwordConfirmation":'dcmax',
        "email":'dc1@dc.org'
      },
      regSuccess: ''
    }
  },
  methods: {
    handleSubmit() {
    console.log(this.registerForm);
      this.$http.post('http://localhost:8080/users/register', this.registerForm)
                .then(function(data) {
                      console.log(data);
                      this.regSuccess = data.bodyText;
                      });
    }
  }
}

</script>
