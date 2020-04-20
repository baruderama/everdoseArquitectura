<template lang="html">
  <div class="nav">
    <div class="main search">
      <div class="ui search invisible" :class="{visible : search}">
        <div class="ui fluid icon input">
          <input class="prompt" type="text" placeholder="Search products...">
          <i class="search icon"></i>
        </div>
        <div class="results"></div>
      </div>
    </div>
    <div class="cart_button invisible" :class="{visible : cart}" @click="$router.push('cart')">
      <div class="ui circular button">
        <a class="item">
          <i class="shopping cart icon"></i>
          My cart
          <div class="floating ui red circular label left">{{products_len}}</div>
        </a>
      </div>
    </div>
    <div class="account_button invisible" :class="{visible : account}" @click="$router.push('account')">
      <button class="circular ui icon button">
        <i class="user icon"></i>
      </button>
    </div>
  </div>
</template>

<script>
import cookie from '../cookies'

export default {
  props:['search','cart','account','products_len'],
  data(){
    return {
    }
  },
  mounted(){
    this.products = cookie.getCookie('products')
  },
  computed:{
    products(){
      return cookie.getCookie('products')
    },
  },
}
</script>

<style lang="css" scoped>
.nav{
  background-color: #f3f3f3;
  height: 50px;
}
.nav .main.search{
  margin-top: 6px;
  margin-left: 15px;
  width: 70%;
  display: inline-block;
}
.nav .account_button{
  margin-right: 10px;
  margin-top: 6px;
  display: inline-block;
  float: right;
}
.nav .account_button i{
  color: black;
}
.nav .cart_button{
  display: inline-block;
  float: right;
}
.nav .cart_button .ui.circular.button{
  background-color: #101010;
  display: inline-block;
  margin-top: 5px;
  margin-right: 10px;
  position: relative;
  vertical-align: middle;
  color: white;
}
.nav .cart_button .ui.circular.button *{
  color: white;
}
.nav .cart_button .ui.circular.button:hover *{
  color: #e5e5e5;
}
</style>
