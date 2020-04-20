<template lang="html">
<div class="header" @click="$router.push('home')">
  <span>
    EVERDOSE
  </span>
  <div class="line"/>
</div>
</template>

<script>
export default {
}
</script>

<style lang="css" scoped>
.header{
  width: 100%;
  height: 140px;
  background: white;
  color: black;
  font-size: 50px;
  font-weight: 200;
  padding-top: 80px;
  align-items: left;
  text-align: left;
  font-family: 'Archivo', sans-serif;
  position: relative;
  margin: 0px;
}
.header:hover{
  cursor: pointer;
}
.header span{
  padding-left: 50px;
}
.header .line{
  width: 100%;
  height: 2px;
  position: absolute;
  bottom: 0;
  background: rgb(0,5,32);
  background: linear-gradient(90deg, rgba(0,5,32,1) 0%, rgba(131,131,131,1) 100%);
}
</style>
