import Vue from 'vue'
import App from './App.vue'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import httpRequest from './utils/httpRequest' // api: https://github.com/axios/axios
import router from './router'

Vue.config.productionTip = false
Vue.use(ElementUI);


Vue.prototype.$http = httpRequest // ajax请求方法

new Vue({
    render: h => h(App),
    router: router
}).$mount('#app')
