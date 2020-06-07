<template lang="html">
  <div class="login">
    <div class="card">
      <div class="form_panel">
        <form class="ui form">
          <div class="field ui left icon input fluid">
            <i class="user icon"></i>
            <input v-model="username" type="text" name="username" placeholder="Username">
          </div>
          <div class="field ui left icon input fluid">
            <i class="lock icon"></i>
            <input v-model="password" type="password" name="password" placeholder="Password">
          </div>
          <div class="ui large fluid black button" @click="login">Login</div>
          <div class="ui error message" :class="{visible: errorLogin}">
            Check your credentials
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  data(){
     return {
       username: '',
       password: '',
       errorLogin: false,
     }
  },
  methods:{
    login(){
      var thisa = this;
      const url = 'http://localhost:8080/usersmicroservice-web-0.0.1-SNAPSHOT/GetToken';
      const options = {
        method: 'POST',
        data: {
          username: this.username,
          password: this.password,
        },
        withCredentials: true,
        url,
      };
      axios(options).then(function () {
        thisa.processing_payment = true;
        thisa.$router.push({name: 'home'});
      })
      .catch(function (){
        thisa.errorLogin = true;
      })
    }
  }
}
</script>

<style lang="css" scoped>
.login .form_panel{
  background-color: white;
  border-radius: 20px;
  border-style: solid;
  border-width: 1px;
  border-color: #dadada;
  padding: 50px;
  width: 50%;
  margin: 0 auto;
  margin-top: 80px;
  margin-bottom: 80px;
}
</style>
