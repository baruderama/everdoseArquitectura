<template lang="html">
  <div class="product">
    <div class="ui card">
      <div class="image">
        <img :src="image" style="height:250px">
      </div>
      <div class="content">
        <div class="header">{{name}}</div>
        <div class="description">
          {{description}}
        </div>
      </div>
      <div class="extra content">
        <div>
          <i class="dollar sign icon"></i>
          {{price}}
        </div>
      </div>
      <div class="extra content">
        <div class="ui button circular teal everdose" @click="add({id, name, image, description, price})" >
          <i class="cart icon"></i>
          Add to cart</div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props:['id','name','image','description','price', 'add']
}
</script>

<style lang="css" scoped>
.product{
  display: inline-block;
  margin: 10px;
  border-style: solid;
  border-width: 1px;
  border-radius: 20px;
}
.product .ui.card{
  border-color: yellow !important;
}
</style>
