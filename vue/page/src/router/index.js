// https://router.vuejs.org/zh/installation.html
import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

// 404页面
import NotFoundOne from '../components/404/1/404.vue'
import NotFoundTwo from '../components/404/2/404.vue'
// 登陆页面
import LoginOne  from '../components/login/1/Login.vue'
import LoginTwo  from '../components/login/2/Login.vue'
import LoginThree  from '../components/login/3/Login.vue'
import LoginFour  from '../components/login/4/Login.vue'

const routes = [
    { path: '/404/1', component: NotFoundOne },
    { path: '/404/2', component: NotFoundTwo },
    { path: '/login/1', component: LoginOne },
    { path: '/login/2', component: LoginTwo },
    { path: '/login/3', component: LoginThree },
    { path: '/login/4', component: LoginFour }
]
const router = new VueRouter({
    routes
})
export default router
