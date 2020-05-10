<template lang="html">
  <div class="home">
    <Nav search="true" :onSearch="updateCatalog" account="true" cart="true" :products_len="products_len"/>
    <div class="catalog">
      <div class="product">
        <Product v-for="product in products" :key="product.id" :id="product.id" :name="product.name" :image="product.image" :description="product.description" :price="product.price" :type="product.type" :add="addToCart"/>
      </div>
    </div>
    <div class="pagination">
      <div class="ui icon buttons">
        <button class="ui button" @click="backPage">
          <i class="left chevron icon"></i>
        </button>
        <button class="ui button">
          {{ page }}
        </button>
        <button class="ui button" @click="nextPage">
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
      keywords_pre: null,
      products:[
      ],
      products_in_cart : [],
      page: 1,
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
    axios.get('http://localhost:8080/stockmicroservice-web-0.0.1-SNAPSHOT/Catalog',{
      withCredentials: true,
    }
      )
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
    },
    updateCatalog(keywords_pre){
      this.keywords_pre = keywords_pre;
      const keywords = keywords_pre.replace(/[' ']/g,",");
      const url = 'http://localhost:8080/stockmicroservice-web-0.0.1-SNAPSHOT/Catalog?keywords='+keywords;
      const thisa = this;
      console.log(url)
      axios.get(url,{
        withCredentials: true,
      })
       .then(function (response) {
         thisa.products = response.data;
       })
       .catch(function (error) {
         // handle error
         console.log(error);
       })
       .then(function () {
         // always executed
       });
    },
    nextPage(){
      var page = this.page;
      page = page + 1;
      this.page = page;
      var url = 'http://localhost:8080/stockmicroservice-web-0.0.1-SNAPSHOT/Catalog?page='+page;
      if (this.keywords_pre != null ){
        const keywords = this.keywords_pre.replace(/[' ']/g,",");
        url = 'http://localhost:8080/stockmicroservice-web-0.0.1-SNAPSHOT/Catalog?keywords='+keywords+'&page='+page;
      }
      const thisa = this;
      console.log(url)
      axios.get(url,{
        withCredentials: true,
      })
       .then(function (response) {
         thisa.products = response.data;
       })
       .catch(function (error) {
         // handle error
         console.log(error);
       })
       .then(function () {
         // always executed
       });
    },
    backPage(){
      var page = this.page;
      if( page > 1){
        page = page - 1;
      }
      this.page = page;
      var url = 'http://localhost:8080/stockmicroservice-web-0.0.1-SNAPSHOT/Catalog?page='+page;
      if (this.keywords_pre != null ){
        const keywords = this.keywords_pre.replace(/[' ']/g,",");
        url = 'http://localhost:8080/stockmicroservice-web-0.0.1-SNAPSHOT/Catalog?keywords='+keywords+'&page='+page;
      }
      const thisa = this;
      console.log(url)
      axios.get(url,{
        withCredentials: true,
      })
       .then(function (response) {
         thisa.products = response.data;
       })
       .catch(function (error) {
         // handle error
         console.log(error);
       })
       .then(function () {
         // always executed
       });
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
