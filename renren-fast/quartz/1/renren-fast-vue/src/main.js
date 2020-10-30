import Vue from 'vue'
import App from './App.vue'

import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

import httpRequest from './utils/httpRequest'
Vue.prototype.$http = httpRequest // ajax请求方法

Vue.config.productionTip = false
Vue.use(ElementUI);
new Vue({
    render: h => h(App),
}).$mount('#app')
