import Vue from 'vue'
import App from './App.vue'
import router from './router'
import cookie from './cookies'

Vue.config.productionTip = false
new  Vue({router, cookie,
render:  h  =>  h(App)
}).$mount('#app')
