import Vue from 'vue'
import App from './App.vue'


Vue.config.productionTip = false
import router from './router'


// 4. 创建和挂载根实例。
// 记得要通过 router 配置参数注入路由，从而让整个应用都有路由功能

new Vue({
    render: h => h(App),
    router: router //
}).$mount('#app')
