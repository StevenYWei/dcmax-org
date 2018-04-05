<template>
  <div class="register">
    <h2>{{ formMsg }}</h2>
    <form>
      <div class="form-group">
        <div class="input-group">
          <input type="text" id="username" v-model="registerForm.username" required="required" />
          <label class="control-label" for="username">Username</label><i class="bar"></i>
        </div>
      </div>
      <div class="form-group">
        <div class="input-group">
          <input type="password" id="password" v-model="registerForm.password" required="required" />
          <label class="control-label" for="password">Password</label><i class="bar"></i>
        </div>
      </div>
      <div class="form-group">
        <div class="input-group">
          <input type="text" id="passwordConfirmation" v-model="registerForm.passwordConfirmation" required="required">
          <label class="control-label" for="passwordConfirmation">Confirm Password</label><i class="bar"></i>
        </div>
      </div>
      <div class="form-group">
        <div class="input-group">
          <input type="email" id="email" v-model="registerForm.email" required="required">
          <label class="control-label" for="email">Email</label><i class="bar"></i>
        </div>
      </div>
      <vuestic-checkbox
        :id="'checkbox'"
        v-model="agreeToTerm">
        <template slot="label">I agree to 
          <router-link class="link" to="">Terms of Use</router-link>
        </template>
      </vuestic-checkbox>
      <div class="d-flex flex-column flex-md-row align-items-center justify-content-between down-container">
        <button class="btn btn-primary btn-sm" type="submit" v-on:click.prevent="handleSubmit">
          Register
        </button>
        <router-link class='link' :to="{name: 'Login'}">Already a member?</router-link>
      </div>
      <!-- <div><input type="button" value="Register" v-on:click.prevent="handleSubmit"/></div> -->
      <div><label ><font color="red">{{ regSuccess }}</font></label></div>
    </form>
  </div>
</template>


<script>
import VuesticCheckbox from "@/components/vuestic-components/vuestic-checkbox/VuesticCheckbox";

export default {
  name: 'Register',
  components: {
    VuesticCheckbox
  },
  data ()  {
    return {
      formMsg: 'Create a new account',
      registerForm: {
        username: 'dcmax1',
        password: 'dcmax',
        passwordConfirmation:'dcmax',
        email:'dc1@dc.org'
      },
      agreeToTerm: true,
      regSuccess: ''
    }
  },
  methods: {
    handleSubmit ()  {
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

<style lang="scss">
  @import '../../assets/sass/variables';
  @import '~bootstrap/scss/mixins/breakpoints';
  @import "~bootstrap/scss/functions";
  @import '~bootstrap/scss/variables';
  .register {
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
      margin-top: 2.6875rem;
    }
  }
</style>
