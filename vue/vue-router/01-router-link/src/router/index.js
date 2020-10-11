import Vue from 'vue'
import VueRouter from 'vue-router'
import About from "../views/About"
import Home from "../views/Home"

Vue.use(VueRouter)

export default new VueRouter({
    routes: [
        {
            path: '/',
            redirect: '/about' // 重定向
        }
        ,{
            path: '/home',
            component: Home
        }, {
            path: '/about',
            component: About,
            name:'About',   // 命名路由
            alias: '/a'     // 别名
        }
    ]
})

// https://router.vuejs.org/zh/
