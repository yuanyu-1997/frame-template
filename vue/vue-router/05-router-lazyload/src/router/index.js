import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from "../views/Home";
import Hunger from "../views/Hunger";

Vue.use(VueRouter)

// https://router.vuejs.org/zh/

const routes = [
    {path: '/', component: Home},
    {
        path: '/hunger',
        component: Hunger
    },
    {
        path: '/lazy',
        component: () => import(/* webpackChunkName: "lazy" */ '../views/Lazy.vue')
    }
]

const router = new VueRouter({
    routes
})

export default router

// 查看app.js大小
// https://www.jb51.net/article/185346.htm
// https://blog.csdn.net/sinat_35538827/article/details/87969834
