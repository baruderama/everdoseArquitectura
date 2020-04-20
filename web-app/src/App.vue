<template>
  <div id="app">
    <Header/>
    <div class="container">
      <router-view />
    </div>
    <Footer/>
  </div>
</template>

<script>
import Header from './components/Header.vue'
import Footer from './components/Footer.vue'

export default {
  name: 'App',
  components: {
    Header,
    Footer
  }
}
</script>

<style>
#app {
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 0px;
  padding: 0px;
  /* background-image: url("./assets/geometric.jpg"); */
  background-color: #fdfdfd;
  background-repeat: repeat;
}

* {
  font-family: 'Archivo', sans-serif;
  font-weight: 100 !important;
}
div{
  font-family: 'Archivo', sans-serif !important;
  font-weight: 100 !important;
}
h1{
  font-family: 'Archivo', sans-serif !important;
  font-weight: 100 !important;
}
h2{
  font-family: 'Archivo', sans-serif !important;
  font-weight: 100 !important;
}
h3{
  font-family: 'Archivo', sans-serif !important;
  font-weight: 100 !important;
}
h4{
  font-family: 'Archivo', sans-serif !important;
  font-weight: 100 !important;
  font-size: 20px !important;
}
h5{
  font-family: 'Archivo', sans-serif !important;
  font-weight: 100 !important;
  font-size: 20px !important;
}
</style>
