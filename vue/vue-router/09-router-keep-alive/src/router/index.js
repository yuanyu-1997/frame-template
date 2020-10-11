import Vue from 'vue'
import VueRouter from 'vue-router'
import User from "../views/User"
import Home from "../views/Home"

Vue.use(VueRouter)

export default new VueRouter({
    routes: [
        {
            path: '/user',
            component: User
        }, {
            path: '/home',
            component: Home
        }, {
            path: '/',
            redirect: '/home'
        }
    ]
})
