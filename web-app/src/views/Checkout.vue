<template lang="html">
  <div class="checkout">

    <div class="panel">
      <div class="delivery_panel">
        <div class="title">
          <h1 class="ui header">Delivery</h1>
          <div class="ui divider"/>
        </div>
        <div class="delivery_form">
          <form class="ui form">
            <div class="two fields">
              <div class="field">
                <label>First name</label>
                <input type="text" name="shipping[first-name]" placeholder="First Name">
              </div>
              <div class="field">
                <label>Last name</label>
                <input type="text" name="shipping[last-name]" placeholder="Last Name">
              </div>
            </div>
            <div class="field">
              <label>Address</label>
              <input type="text" name="address" placeholder="Address">
            </div>
            <div class="field">
              <label>More information</label>
              <textarea name="name" rows="3" cols="80" placeholder="More information"></textarea>
            </div>
          </form>
        </div>
      </div>

      <div class="payment_method_panel">
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


      <div class="credit_card_form invisible" :class="{visible: credit_card_selected}">
        <form class="ui form">
          <h4 class="ui dividing header">Credit card information</h4>
          <div class="two fields">
            <div class="field">
              <input type="text" name="shipping[first-name]" placeholder="First Name">
            </div>
            <div class="field">
              <input type="text" name="shipping[last-name]" placeholder="Last Name">
            </div>
          </div>
          <div class="field">
            <label>Credit card number</label>
            <input type="text" name="first-name" placeholder="4242 4242 4242 4242">
          </div>
          <div class="two fields">
            <div class="field">
              <label>Date</label>
              <input type="text" name="first-name" placeholder="MM/YY">
            </div>
            <div class="field">
              <label>CCV</label>
              <input type="text" name="last-name" placeholder="123">
            </div>

          </div>
          <!-- <div class="field">
            <div class="ui checkbox">
              <input type="checkbox" tabindex="0" class="hidden">
              <label>I agree to the Terms and Conditions</label>
            </div>
          </div> -->
          <div @click="pay" class="ui fluid green button">Validate information</div>
        </form>

        <div class="ui positive message invisible">
          <div class="header">
            The validation was successful
          </div>
          <p>Go to your <b>special offers</b> page to see now.</p>
        </div>

        <div class="ui negative message invisible">
          <div class="header">
            The validation wasn't succesful
          </div>
            <p>That offer has expired
          </p>
        </div>

      </div>

      <div class="on_delivery_panel invisible"  :class="{visible: on_delivery_selected}">
        <div class="ui success message">
          You'll have to pay X when you receive your products.
        </div>
      </div>

      <div class="proceed_panel">
        <div class="ui fluid disabled green button">
          Pay
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

export default {
  data(){
    return {
      products: [],
      credit_card_selected: true,
      on_delivery_selected: false,
    }
  },
  computed:{
    total(){
      var total = 0;
      for (var product of this.products){
        total += product.price * product.amount;
      }
      return total;
    }
  },
  mounted(){
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
    pay(){

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
