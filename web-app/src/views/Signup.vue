<template lang="html">
  <div class="signup">
    <div class="card">
      <div class="form_panel">
        <form class="ui form">
          <div class="ui tiny header">Username</div>
          <div class="field ui left icon input fluid">
            <i class="user icon"></i>
            <input v-model="username" type="text" name="first-name" placeholder="Username">
          </div>
          <div class="ui tiny header">Password</div>
          <div class="field ui left icon input fluid">
            <i class="lock icon"></i>
            <input v-model="password" type="password" name="last-name" placeholder="Password">
          </div>
          <div class="field ui left icon input fluid">
            <i class="lock icon"></i>
            <input v-model="password2" type="password" name="last-name" placeholder="Repeat the password">
          </div>
          <div class="ui divider">

          </div>
          <div class="ui tiny header">Name</div>
          <div class="ui two fields">
            <div class="field">
              <input v-model="firstname" type="text" name="firstname" value="" placeholder="First name">
            </div>
            <div class="field">
              <input v-model="lastname" type="text" name="lastname" value="" placeholder="Last name">
            </div>
          </div>
          <div class="ui tiny header">Email</div>
          <div class="field ui left icon input fluid">
            <i class="at icon"></i>
            <input v-model="email" type="text" name="first-name" placeholder="Email">
          </div>
          <div class="ui tiny header">Phone</div>
          <div class="field ui left icon input fluid">
            <i class="phone icon"></i>
            <input v-model="phone" type="text" name="first-name" placeholder="Phone">
          </div>
          <div class="ui fluid blue button" @click="signup">Sign up</div>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  data(){
    return{
      username: '',
      password: '',
      password2: '',
      firstname: '',
      lastname: '',
      email: '',
      phone:'',
    }
  },
  methods:{
    signup(){
      var thisa = this;
      axios.post('http://localhost:8080/usersmicroservice-web/Signup', {
        username: thisa.username,
        password: thisa.password,
        firstname: thisa.firstname,
        lastname: thisa.lastname,
        email: thisa.email,
        phone: thisa.phone,
      })
      .then(function () {
        console.log('Done')
      })
    }
  }

}
</script>

<style lang="css" scoped>
.signup {
  /* background-color: #323232; */
}
.signup .form_panel{
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
.signup .ui.tiny.header{
  margin: 0px;
  font-weight: 500 !important;
  text-align: left;
}
</style>
