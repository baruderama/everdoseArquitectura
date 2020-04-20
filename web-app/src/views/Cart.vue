<template lang="html">
<div class="cart">
  <div class="cart_items">
    <div class="ui items">
      <div class="item" v-for="product in products" :key="product.id">
        <div class="ui medium image">
          <img :src="product.image">
        </div>
        <div class="content">
          <div class="header">{{ product.name }}</div>
          <div class="meta">
          </div>
          <div class="description">
            <p>{{product.description}}</p>
            <p> <b>Amount:</b> {{product.amount}} </p>
          </div>
          <div class="extra">
            <i class="dollar sign icon"></i>
            <span>{{product.price}}</span>
          </div>
        </div>
      </div>
    </div>
  </div>
  <div class="information">
    <table class="ui very basic table">
  <thead>
    <tr>
      <th>Product</th>
      <th class="right aligned">Total</th>
    </tr>
  </thead>
  <tbody>
    <tr v-for="product in products" :key="product.id" class="left aligned">
      <td>{{ product.name }}</td>
      <td class="right aligned">{{ product.price * product.amount }}</td>
    </tr>
  </tbody>
  <tfoot>
    <tr>
      <td>TOTAL</td>
      <td class="right aligned">{{ total }}</td>
    </tr>
  </tfoot>
</table>
  </div>
  <div class="extra" @click="$router.push('checkout')">
    <div class="ui fluid green button">
      Checkout
    </div>
  </div>
</div>
</template>

<script>
import cookie from '../cookies'
export default {
    data(){
      return {
        products: []
      }
    },
    computed:{
      total(){
        var var_total = 0;
        for(var product of this.products){
          var_total += product.price * product.amount;
        }
        return var_total;
      }
    },
    mounted(){
      this.products = cookie.getCookie('products');
    }
}
</script>

<style lang="css" scoped>
.cart .cart_items{
  width: 90%;
  margin: 0 auto;
  padding: 20px;
}
.cart .information{
  padding-left: 70px;
  padding-right: 70px;
}
.cart .items .item{
  background-color: white;
  position: relative;
  margin-bottom: 30px;
  border-style: solid;
  border-width: 1px;
  border-color: #dedede;
  border-radius: 20px;
}
.cart .items .item img{
  position: absolute;
  top: 20px;
  left: 30px;
  vertical-align: middle;
  height: 100px;
  width: 100px;
}
.cart .items .item .content{
  margin: 20px;
}
.cart .extra{
  padding-left: 70px;
  padding-right: 70px;
  margin-top: 40px;
  margin-bottom: 40px;
}
</style>
