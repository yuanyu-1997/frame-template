import Vue from 'vue'
import VueRouter from 'vue-router'
import About from "../views/About"
import Home from "../views/Home"
import Params from "@/views/Params";

Vue.use(VueRouter)

export default new VueRouter({
    routes: [
        {
            path: '/',
            redirect: '/home'
        }
        , {
            path: '/home',
            component: Home
        }, {
            path: '/about',
            component: About,
            name: 'About'
        }, {
            path: '/params/:id',
            component: Params,
            name: 'Params'
        }
    ]
})

// https://router.vuejs.org/zh/
// https://www.cnblogs.com/piaoyi1997/p/13387526.html
// https://www.jb51.net/article/181818.htm
// https://blog.csdn.net/lovexiaobaby/article/details/93618297
// https://www.jianshu.com/p/9276d4cb5ef5
