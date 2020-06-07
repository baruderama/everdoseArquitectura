<template lang="html">
  <div class="checkout">

    <div class="panel">
      <div class="delivery_panel" :class="{invisible: processing_payment}">
        <div class="title">
          <h1 class="ui header">Delivery</h1>
          <div class="ui divider"/>
        </div>
        <div class="delivery_form">
          <form class="ui form">
            <div class="field">
              <div class="two fields">
                <div class="field">
                  <label>First name</label>
                  <input v-model="first_name" type="text" name="shipping[first-name]" placeholder="First Name">
                </div>
                <div class="field">
                  <label>Last name</label>
                  <input v-model="last_name" type="text" name="shipping[first-name]" placeholder="First Name">
                </div>
              </div>
            </div>
            <div class="field">
              <label>Address</label>
              <input v-model="address" type="text" name="address" placeholder="Address">
            </div>
            <div class="field">
              <label>More information</label>
              <textarea v-model="more_information" name="name" rows="3" cols="80" placeholder="More information"></textarea>
            </div>
          </form>
        </div>
      </div>

      <div class="payment_method_panel" :class="{invisible: processing_payment}">
        <div class="title">
          <h1 class="ui header">Payment</h1>
          <div class="ui divider"/>
        </div>

        <div class="ui items">
          <div class="item" :class="{ selected : on_delivery_selected}" @click="select('on_delivery')">
            <i class="money bill alternate outline icon huge"></i>
            <div class="middle aligned content">
              <div class="header">On delivery payment</div>
            </div>
          </div>
          <div class="item" :class="{ selected : credit_card_selected}" @click="select('credit_card')">
            <i class="credit card icon huge"></i>
            <div class="middle aligned content">
              <div class="header">Credit card</div>
            </div>
          </div>
        </div>
      </div>

      <div class="credit_card_form invisible" :class="{visible: credit_card_selected && !processing_payment}">
        <form class="ui form">

          <h4 class="ui dividing header">Credit card information</h4>
          <div class="field">
            <input v-model="name_cc" type="text" name="shipping[first-name]" placeholder="Name in card">
          </div>
          <div ref="card"></div>

        </form>

      </div>

      <div class="on_delivery_panel invisible"  :class="{visible: on_delivery_selected}">
        <div class="ui success message">
          You'll have to pay X when you receive your products.
        </div>
      </div>

      <div class="proceed_panel">
        <div @click="buy" class="ui fluid green button" :class="{invisible: processing_payment}">
          Buy
        </div>
      </div>

      <div class="on_delivery_panel invisible"  :class="{visible: succesfulPayment}">
        <div class="ui success message">
          Your payment was succesful.
        </div>
      </div>

      <div class="on_delivery_panel invisible"  :class="{visible: unsuccesfulPayment}">
        <div class="ui error message">
          Your payment was unsuccesful.
        </div>
      </div>

    </div>


    <div class="cart">
      <div class="ui items">
        <div class="item" v-for="product in products" :key="product.id">
          <a class="ui tiny image">
            <img :src="product.image">
          </a>
          <div class="content">
            <a class="header">{{ product.name }}</a>
            <div class="description">
              <i class="dollar sign icon"></i>
              {{ product.price }}
            </div>
            <div class="extra">
              Amount:
              {{ product.amount }}
            </div>
          </div>
        </div>
      </div>
      <h4 class="ui dividing header">Total</h4>
      <div class="">
        <i class="dollar sign icon"></i>
        {{ total }}
      </div>
    </div>

  </div>
</template>

<script>
import cookie from '../cookies'
import axios from 'axios'

/*eslint no-undef: 1*/
let stripe = Stripe(`pk_test_SCFXDSEiX7vyfh3wYzR9aYaD00eIWW9bUD`),
    elements = stripe.elements(),
    card = undefined;

export default {
  data(){
    return {
      name: '',
      address: '',
      more_information: '',

      products: [],
      credit_card_selected: true,
      on_delivery_selected: false,
      processing_payment: false,
      name_cc: '',
      succesfulPayment: false,
      unsuccesfulPayment: false,

    }
  },
  computed:{
    total(){
      var total = 0;
      for (var product of this.products){
        total += product.price * product.amount;
      }
      return total;
    },
    financial_information(){
      return {
        credit_card: true,
        cc: {
          "name" : this.name_cc,
        }
      }
    },
    delivery_information(){
      return {
        "first_name" : this.first_name,
        "last_name" : this.last_name,
        "address" : this.address,
        "more_information" : this.more_information
      }
    },
    productsToCheckout(){
      var temp = []
      for (var product of this.products){
        temp.push({
          "id" : product.id,
          "type" : product.type,
          "amount" : product.amount
        })
      }
      return temp;
    }
  },
  mounted(){

    let style = {
    base: {
      border: '1px solid #D8D8D8',
      borderRadius: '4px',
      color: "#000",
    },

    invalid: {
      // All of the error styles go inside of here.
    }

  };

    card = elements.create('card', style);
    card.mount(this.$refs.card);
    // console.log(Stripe)
    this.products = cookie.getCookie('products');
  },
  methods:{
    select(type){
      if(type == 'credit_card'){
        this.on_delivery_selected = false;
        this.credit_card_selected = true;
      }
      if(type == 'on_delivery'){
        this.credit_card_selected = false;
        this.on_delivery_selected = true;
      }
    },
    buy(){
      var thisa = this;
      // thisa.processing_payment = true;
      stripe.createToken(card).then(function(result) {
        axios.post('http://localhost:8080/buymicroservice-web-0.0.1-SNAPSHOT/Buy', {
          stripeToken: result.token,
          financial_information: thisa.financial_information,
          delivery_information: thisa.delivery_information,
          products: thisa.productsToCheckout,
        },{
          withCredentials: true,
        }
        )
        .then(function () {
          console.log("Done")
          thisa.succesfulPayment = true;
        })
        .catch(function(){
          console.log("Error")
          thisa.unsuccesfulPayment = true;
        })
      }).catch(function (error) {
        console.log(error)
        console.log("Error")
        thisa.unsuccesfulPayment = true;
      });
    }
  }
}
</script>

<style lang="css" scoped>
.checkout {
  position: relative;
  padding-bottom: 80px;
  /* height: 1700px; */
}
.checkout .title{
  margin-top: 40px;
}
.checkout .panel{
  display: inline-block;
  /* float: left; */
  width: 70%;
  margin-left: 0px;
}
.checkout .cart{
  background-color: white;
  /* position: fixed; */
  right: 20px;
  margin-top: 30px;
  padding-top: 40px;
  padding-bottom: 40px;
  padding-left: 20px;
  padding-right: 20px;
  vertical-align: top;
  display: inline-block;
  width: 25%;
  border-style: solid;
  border-width: 1px;
  border-color: #dedede;
  border-radius: 20px;
}
.checkout .cart .item{
  padding-top: 20px;
  padding-bottom: 20px;
  margin-bottom: 10px;
}
.checkout .payment_method_panel{
  display: inline-block;
  margin: 0 auto;
  margin-top: 40px;
  width: 80%;
  border-width: 1px;
  border-radius: 20px;
}
.checkout .on_delivery_panel{
  display: inline-block;
  margin: 0 auto;
  margin-top: 40px;
  width: 80%;
  border-width: 1px;
  border-radius: 20px;
}
.checkout .proceed_panel{
  display: inline-block;
  margin: 0 auto;
  margin-top: 40px;
  width: 80%;
  border-width: 1px;
  border-radius: 20px;
}
.checkout .payment_method_panel .item{
  position: relative;
  padding-left: 20px;
  padding-right: 20px;
  height: 90px;
}
.checkout .payment_method_panel .item:hover{
  background-color: #f5f5f5;
}
.checkout .payment_method_panel .item .content{
  position: absolute;
  top: 40px;
  right: 50px;
  /* padding-top: 20px; */
  /* padding-bottom: 20px; */
}
.checkout .payment_method_panel .item i{
  position: absolute;
  top: 16px;
  /* padding-top: 20px; */
  /* padding-bottom: 20px; */
}
.checkout .credit_card_form{
  width: 80%;
  margin: 0 auto;
  padding: 30px;
  margin-top: 40px;
  margin-bottom: 40px;
  border-style: solid;
  border-width: 1px;
  border-color: #dedede;
  border-radius: 20px;
  background: white;
}
.checkout .item{
  color: #ababab;
  border-width: 1px;
  border-color: #dedede;
  border-radius: 20px;
}
.checkout .item .header{
  color: #ababab !important;
}
.checkout .item.selected{
  /* background-color: #f0f0f0; */
  border-style: solid;
  border-width: 1px;
  border-color: #dedede;
  border-radius: 20px;
  background-color: white;
}
.checkout .item.selected i{
  color: #505050;
}
.checkout .item.selected .header{
  color: #505050 !important;
}
.checkout .delivery_panel{
  width: 80%;
  margin: 0 auto;
}

.checkout .delivery_form{
  padding: 30px;
  width: 100%;
  display: inline-block;
  margin: 0 auto;
  margin-top: 40px;
  border-style: solid;
  border-width: 1px;
  border-color: #dedede;
  border-radius: 20px;
  background: white;
}

.checkout .invisible{
  display: none;
}

.checkout .visible{
  display: block;
}
</style>
