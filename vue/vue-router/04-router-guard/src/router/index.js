import Vue from 'vue'
import VueRouter from 'vue-router'

import Foo from "../views/Foo"
import Bar from "../views/Bar"
import Login from "../views/Login"
import User from "../views/User";
import Leave from "../views/Leave";

Vue.use(VueRouter)

// https://router.vuejs.org/zh/

const routes = [
    {path: '/foo', component: Foo},
    {path: '/login', name: 'login', component: Login},
    {path: '/leave', name: 'login', component: Leave},
    {path: '/bar', component: Bar},
    {
        path: '/user', component: User,
        // 2、路由级别的导航守卫
        beforeEnter: (to, from, next) => {
            //next(false)
            next()
        }
    }
]

const router = new VueRouter({
    routes
})

// 1、全局前置守卫
router.beforeEach((to, from, next) => {
    console.log(JSON.stringify(to,null,2))
    //next({...to, replace: true})
    next()
})


export default router
