import Vue from 'vue'
import VueRouter from 'vue-router'

import Home from "../views/Home"
import User from "../views/User";

Vue.use(VueRouter)

const routes = [
    {path: '/', component: Home},
    {path: '/user/:id', component: User},
]

const router = new VueRouter({
    routes
})

export default router

// https://router.vuejs.org/zh/
