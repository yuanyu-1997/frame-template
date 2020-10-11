<template>
  <div id="app">
    <h1 class="title">动态添加路由</h1>
    <div class="header">
      <ul>
        <li>
          <router-link to="/">Home.vue</router-link>
        </li>
        <li>
          <button @click="login">登陆</button>
        </li>
        <li v-if="$store.state.isLogin">
          <router-link to="/dashboard">Dashboard.vue</router-link>
        </li>
      </ul>
    </div>

    <router-view></router-view>
  </div>
</template>

<script>
import {asyncRoutes} from './router/index'

export default {

  methods: {
    login() {
      alert('登陆成功，动态添加路由')

      this.$store.commit('login')
      // 动态添加路由
      this.$router.addRoutes(asyncRoutes)
    }
  }
}
</script>

<style>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

a {
  text-decoration: none;
}

.title{
  text-align: center;
}

.header {
  overflow: hidden;
  border-bottom: 1px solid red;
  border-top: 1px solid red;
}

.header ul {
  list-style: none;
}

.header ul li {
  height: 25px;
  width: 100px;
  margin: 10px;
  float: left;
}

.header ul li a {
  display: inline-block;
  width: 100px;
  height: 100px;
}
</style>
