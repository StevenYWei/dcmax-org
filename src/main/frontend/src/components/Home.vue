<template>
  <div class="Home">
    <div class="content">
      <img src="../assets/dcmax-icon.png">
    </div>
    <h1>{{ msg }}</h1>
    <ul class="list-group ">
      <li class="list-group-item border-0">
        <router-link class="btn btn-secondary btn-lg btn-block" :to="{name: 'Login'}">
          Login
        </router-link>
      </li>
      <li class="list-group-item border-0">
        <router-link class="btn btn-secondary btn-lg btn-block" :to="{name: 'Register'}">Register</router-link>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: 'Home',
  data () {
    return {
      msg: 'This is DCMAX!'
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
