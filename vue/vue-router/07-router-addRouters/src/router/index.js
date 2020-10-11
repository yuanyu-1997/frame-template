import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from "../views/Home"
import Register from "../views/Register"
import Dashboard from "../views/Dashboard"

Vue.use(VueRouter)


// 普通路由
export const constantRoutes = [
    {path: '/', component: Home},
    {path: '/register', component: Register}
]


// 带权限的路由
export const asyncRoutes = [
    {path: '/dashboard', name:'Dashboard', component: Dashboard}
]


const router = new VueRouter({
    routes: constantRoutes
    // ,
    // mode: 'history'
})

export default router
// https://router.vuejs.org/zh/
