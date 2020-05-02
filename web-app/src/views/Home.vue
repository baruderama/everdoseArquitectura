<template lang="html">
  <div class="home">
    <Nav search="true" account="true" cart="true" :products_len="products_len" />
    <div class="catalog">
      <div class="product">
        <Product v-for="product in products" :key="product.id" :id="product.id" :name="product.name" :image="product.image" :description="product.description" :price="product.price" :add="addToCart"/>
      </div>
    </div>
    <div class="pagination">
      <div class="ui icon buttons">
        <button class="ui button">
          <i class="left chevron icon"></i>
        </button>
        <button class="ui button">
          Current page
        </button>
        <button class="ui button">
          <i class="right chevron icon"></i>
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import Product from '../components/home/Product'
import Nav from '../components/Nav'
import cookie from '../cookies'
import axios from 'axios'

export default {
  components: {
    Product,
    Nav
  },
  data(){
    return {
      products:[
      ],
      products_in_cart : []
    }
  },
  computed:{
    products_len(){
      var total = 0;
      for( var product of this.products_in_cart ){
        total += product.amount;
      }
      return total
    }
  },
  mounted(){
    console.log("Mounting")
    this.products_in_cart = cookie.getCookie('products')
    var thisa = this;
    axios.get('http://localhost:8080/stockmicroservice-web-0.0.1-SNAPSHOT/Catalog')
     .then(function (response) {
       // handle success
       thisa.products = response.data;
     })
     .catch(function (error) {
       // handle error
       console.log(error);
     })
     .then(function () {
       // always executed
     });

    console.log(this.products_in_cart)
  },
  methods:{
    addToCart( product ){
      var added = false;
      for (var global_product of this.products_in_cart ){
        console.log(product.id)
        if (global_product.id == product.id) {
          console.log("Exists:")
          console.log(product.id)
             global_product.amount += 1;
             product.amount += 1;
             cookie.setCookie('products',JSON.stringify(this.products_in_cart))
            console.log(cookie.getCookie('products'))
             added = true;
        }
      }
      if (!added){
        product.amount = 1;
        this.products_in_cart.push( product )
        console.log("Adding product")
        cookie.setCookie('products',JSON.stringify(this.products_in_cart))
        console.log(cookie.getCookie('products'))
      }
    }
  }
}
</script>

<style lang="css" scoped>
.home .catalog{
  padding-top: 40px;
  padding-bottom: 60px;
}
.home .pagination{
  margin: 0 auto;
  margin-bottom: 60px;
}

</style>
