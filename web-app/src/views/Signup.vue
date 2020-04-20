<template lang="html">
  <div class="signup">
    <div class="card">
      <div class="form_panel">
        <form class="ui form">
          <div class="ui tiny header">Username</div>
          <div class="field ui left icon input fluid">
            <i class="user icon"></i>
            <input type="text" name="first-name" placeholder="Username">
          </div>
          <div class="ui tiny header">Password</div>
          <div class="field ui left icon input fluid">
            <i class="lock icon"></i>
            <input type="password" name="last-name" placeholder="Password">
          </div>
          <div class="field ui left icon input fluid">
            <i class="lock icon"></i>
            <input type="password" name="last-name" placeholder="Repeat the password">
          </div>
          <div class="ui divider">

          </div>
          <div class="ui tiny header">Name</div>
          <div class="ui two fields">
            <div class="field">
              <input type="text" name="firstname" value="" placeholder="First name">
            </div>
            <div class="field">
              <input type="text" name="lastname" value="" placeholder="Last name">
            </div>
          </div>
          <div class="ui tiny header">Email</div>
          <div class="field ui left icon input fluid">
            <i class="at icon"></i>
            <input type="text" name="first-name" placeholder="Email">
          </div>
          <div class="ui tiny header">Phone</div>
          <div class="field ui left icon input fluid">
            <i class="phone icon"></i>
            <input type="text" name="first-name" placeholder="Phone">
          </div>
          <button class="ui fluid blue button" type="submit">Sign up</button>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
export default {
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
