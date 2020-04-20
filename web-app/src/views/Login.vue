<template lang="html">
  <div class="login">
    <div class="card">
      <div class="form_panel">
        <form class="ui form">
          <div class="field ui left icon input fluid">
            <i class="user icon"></i>
            <input type="text" name="first-name" placeholder="Username">
          </div>
          <div class="field ui left icon input fluid">
            <i class="lock icon"></i>
            <input type="password" name="password" placeholder="Password">
          </div>
          <div class="ui large fluid black button" type="submit">Login</div>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data(){
     return {

     }
  },
  methods:{
    login(){
      console.log("Login in")
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
