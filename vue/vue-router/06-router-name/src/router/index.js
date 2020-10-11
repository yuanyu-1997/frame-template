import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from "../views/Home";
import About from "../views/About";

Vue.use(VueRouter)

// https://router.vuejs.org/zh/

const routes = [
    {path: '/', component: Home},
    {
        // <router-link :to="{name:'About'}">About.vue</router-link>
        // this.$router.push({ name: 'About'})
        path: '/my-about', // 我把这里的path改了，只要name没改动，就不用该其他代码
        name: 'About', //
        component: About
    }
]

const router = new VueRouter({
    routes
})

export default router
