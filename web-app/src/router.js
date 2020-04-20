import Vue from 'vue'
import VueRouter from 'vue-router'
import Cart from './views/Cart'
import Checkout from './views/Checkout'
import Account from './views/Account'
import Home from './views/Home'
import Login from './views/Login'
import Signup from './views/Signup'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    component: Home
  },
  {
    path: '/cart',
    name: 'cart',
    component: Cart
  },
  {
    path: '/checkout',
    name: 'checkout',
    component: Checkout
  },
  {
    path: '/account',
    name: 'account',
    component: Account
  },
  {
    path: '/home',
    name: 'home',
    component: Home
  },
  {
    path: '/login',
    name: 'login',
    component: Login
  },
  {
    path: '/signup',
    name: 'signup',
    component: Signup
  },
]

const  router  =  new  VueRouter({
  routes
})

export default router;
