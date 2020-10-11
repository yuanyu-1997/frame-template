import Vue from 'vue'
import App from './App.vue'

import env from '../config/env'
// 根据环境变量获取不同的请求地址
console.log('baseURL=' + env.baseURL)

Vue.config.productionTip = false

new Vue({
  render: h => h(App),
}).$mount('#app')
