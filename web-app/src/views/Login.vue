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
     }
  },
  methods:{
    login(){
      var thisa = this;
      axios.post('http://localhost:8080/usersmicroservice-web/Login', {
        username: this.username,
        password: this.password,
      }
      )
      .then(function () {
        thisa.processing_payment = true;
        console.log('Done')
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
