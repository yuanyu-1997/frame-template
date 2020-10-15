import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from "../views/Home.vue"
import Login from "../views/Login"
import CreateProduct from "../views/CreateProduct";
import store from '../store/index'

Vue.use(VueRouter)

const router = new VueRouter({
    routes: [
        {
            path: '/',
            component: Home
        },
        {
            path: '/login',
            name: 'Login',
            component: Login,
            meta: {
                redirectAlreadyLogin: true
            }
        },
        {
            path: '/createProduct',
            component: CreateProduct,
            meta: {
                // 用户登陆以后才能访问的路由
                requiredLogin: true
            }
        }
    ]
})
router.beforeEach((to, from, next) => {
    if (to.meta.requiredLogin && !store.state.user.isLogin) {
        alert('当前页面需要登陆，用户未登录，跳转到登陆页面')
        next({name: 'Login'})
    } else if (to.meta.redirectAlreadyLogin && store.state.user.isLogin) {
        alert('用户已经登陆跳转到首页')
        next('/')
    } else {
        next()
    }
})

export default router

