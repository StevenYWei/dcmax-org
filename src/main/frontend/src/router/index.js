import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/Home'
import Register from '@/components/auth/Register'
import Login from '@/components/auth/Login'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Home',
      component: Home
    },
    {
      path: '/auth/register',
      name: 'Register',
      component: Register
    },
    {
      path: '/auth/login',
      name: 'Login',
      component: Login
    }
  ]
})
