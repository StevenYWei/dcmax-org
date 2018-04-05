<template>
  <div id="app">
    <auth-layout></auth-layout>
  </div>
</template>

<script>
import AuthLayout from '@/components/layout/AuthLayout'

export default {
  name: 'App',
  components: {
    AuthLayout
  }
}
</script>

<style lang="scss">
@import "~normalize.css";
@import "assets/sass/mixins";
@import "assets/sass/variables";
@import "~bootstrap/scss/bootstrap";
@import "assets/sass/_icons-styles.scss";
@import "assets/sass/base";
@import "assets/sass/typography";
@import "assets/sass/icons";
@import "assets/sass/material-forms";
@import "assets/sass/override-bootstrap";
@import "assets/sass/override-custom-libs";

  body {
    height: 100%;
    #app {
      height:100%;
    }
  }
</style>
