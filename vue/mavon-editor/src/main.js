import Vue from 'vue'
import App from './App.vue'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import httpRequest from './utils/httpRequest' // api: https://github.com/axios/axios
import router from './router'

import mavonEditor from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'

Vue.config.productionTip = false

Vue.use(ElementUI)
Vue.use(mavonEditor)

Vue.prototype.$http = httpRequest // ajax请求方法

new Vue({
    render: h => h(App),
    router: router
}).$mount('#app')
