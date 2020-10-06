// 路由器模块

import Vue from 'vue'
import VueRouter from 'vue-router'
import About from "../views/About"
import Home from "../views/Home"
import News from "../views/News"
import Message from "../views/Message"

Vue.use(VueRouter)

export default new VueRouter({
    // n个路由
    routes: [
        {
            path: '/about',
            component: About,
            children: [
                {
                    path: '/about/news', // path最左侧的 "/" 永远代表根路由
                    component: News
                },
                {
                    path: 'message', // 简化写法
                    component: Message
                },
                {
                    path: '',
                    component: Message
                }
            ]
        }, {
            path: '/home',
            component: Home
        }, {
            path: '/',
            redirect: '/about'
        }
    ]
})
