<template lang="html">
  <div class="home">
    <Nav search="true" account="true" cart="true" :products_len="products_len" />
    <div class="catalog">
      <div class="product">
        <Product v-for="product in products" :key="product.id" :id="product.id" :name="product.name" :image="product.image" :description="product.description" :price="product.price" :add="addToCart"/>
      </div>
    </div>
    <div class="pagination">
      <div class="ui icon buttons">
        <button class="ui button">
          <i class="left chevron icon"></i>
        </button>
        <button class="ui button">
          Current page
        </button>
        <button class="ui button">
          <i class="right chevron icon"></i>
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import Product from '../components/home/Product'
import Nav from '../components/Nav'
import cookie from '../cookies'

export default {
  components: {
    Product,
    Nav
  },
  data(){
    return {
      products:[
        {
            id: 1,
            name: "Jabón",
            price: 8900,
            description: "Una description",
            image:"https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcRp2iGZr56-17Uv2U3GcklWalhMLiuEJ3eAMFDKL0swNdp-NB7P&usqp=CAU"
        },
        {
            id: 2,
            name: "Advil MAX",
            price: 8900,
            description: "Presentación	CAPSULA Marca	ADVIL Dimensiones	8x7x4 cm Principio activo	IBUPROFENO Peso	0.12 Kg Opiniones verificadas	0 PUM - Medida	16 PUM - Unidad de Medida	Capsula",
            image:"https://locatelcolombia.vteximg.com.br/arquivos/ids/198119-495-495/7702132004330-ADVIL-MAX-CAJA-X-16-CAPSULAS.png?v=636324465355700000"
        },
        {
            id: 3,
            name: "Aderrall",
            price: 8900,
            description: "Una description",
            image:"https://prescriptionhope.com/wp-content/uploads/2020/02/How-to-Make-Adderall-Last-Longer-and-Stronger-1024x683.jpg"
        },
        {
            id: 4,
            name: "Jabón",
            price: 8900,
            description: "Una description",
            image:"https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcRp2iGZr56-17Uv2U3GcklWalhMLiuEJ3eAMFDKL0swNdp-NB7P&usqp=CAU"
        },
        {
            id: 5,
            name: "Jabón",
            price: 8900,
            description: "Una description",
            image:"https://static.psycom.net/wp-content/uploads/2016/01/Ritalin-methylphenidate-683x1024.jpg"
        },
        {
            id: 6,
            name: "Dolex",
            price: 8900,
            description: "Una description",
            image:"https://i-cf5.gskstatic.com/content/dam/cf-consumer-healthcare/panadol/es_CO/dolex-products/activgel/Productos_455x455_activgel.png?auto=format"
        },
        {
            id: 7,
            name: "Dolex 2",
            price: 8900,
            description: "Una description",
            image:"https://i-cf5.gskstatic.com/content/dam/cf-consumer-healthcare/panadol/es_CO/dolex-products/activgel/Productos_455x455_activgel.png?auto=format"
        },
        {
            id: 8,
            name: "Dolex 3",
            price: 8900,
            description: "Una description",
            image:"https://i-cf5.gskstatic.com/content/dam/cf-consumer-healthcare/panadol/es_CO/dolex-products/activgel/Productos_455x455_activgel.png?auto=format"
        },
        {
            id: 9,
            name: "Clotrimazol",
            price: 18900,
            description: "Una description",
            image:"https://locatelcolombia.vteximg.com.br/arquivos/ids/208193-495-495/7703153036188_CLOTRIMAZOL-2--COLMED-CREMA-VAGINAL-X-20GR-.png?v=636845639859100000"
        },
        {
            id: 10,
            name: "Dolex 5",
            price: 8900,
            description: "Una description",
            image:"https://i-cf5.gskstatic.com/content/dam/cf-consumer-healthcare/panadol/es_CO/dolex-products/activgel/Productos_455x455_activgel.png?auto=format"
        },
        {
            id: 11,
            name: "Fenalgex",
            price: 8900,
            description: "Una description",
            image:"https://locatelcolombia.vteximg.com.br/arquivos/ids/207674-495-495/7702057014223_FENALGEX-325MG-5MG-X10-TABLETAS.png?v=636806621338700000"
        },
        {
            id: 12,
            name: "Jabón",
            price: 8900,
            description: "Una description",
            image:"https://locatelcolombia.vteximg.com.br/arquivos/ids/221666-495-495/7891010076238_1_GEL-LIMPIADOR-NEUTROGENA-SPOT-PROOFING-X-200ML.jpg?v=637170531503530000"
        },
        {
            id: 13,
            name: "Jabón",
            price: 8900,
            description: "Una description",
            image:"https://locatelcolombia.vteximg.com.br/arquivos/ids/176518-495-495/4104480705151-1.png?v=635950549975800000"
        },
        {
            id: 14,
            name: "Jabón",
            price: 8900,
            description: "Una description",
            image:"https://i-cf5.gskstatic.com/content/dam/cf-consumer-healthcare/panadol/es_CO/dolex-products/activgel/Productos_455x455_activgel.png?auto=format"
        },
      ],
      products_in_cart : []
    }
  },
  computed:{
    products_len(){
      var total = 0;
      for( var product of this.products_in_cart ){
        total += product.amount;
      }
      return total
    }
  },
  mounted(){
    console.log("Mounting")
    this.products_in_cart = cookie.getCookie('products')
    console.log(this.products_in_cart)
  },
  methods:{
    addToCart( product ){
      var added = false;
      for (var global_product of this.products_in_cart ){
        console.log(product.id)
        if (global_product.id == product.id) {
          console.log("Exists:")
          console.log(product.id)
             global_product.amount += 1;
             product.amount += 1;
             cookie.setCookie('products',JSON.stringify(this.products_in_cart))
            console.log(cookie.getCookie('products'))
             added = true;
        }
      }
      if (!added){
        product.amount = 1;
        this.products_in_cart.push( product )
        console.log("Adding product")
        cookie.setCookie('products',JSON.stringify(this.products_in_cart))
        console.log(cookie.getCookie('products'))
      }
    }
  }
}
</script>

<style lang="css" scoped>
.home .catalog{
  padding-top: 40px;
  padding-bottom: 60px;
}
.home .pagination{
  margin: 0 auto;
  margin-bottom: 60px;
}

</style>
