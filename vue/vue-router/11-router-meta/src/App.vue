<template>
  <div id="app">
    <h1>使用路由元信息完善路由权限管理</h1>
    <router-link to="/">首页</router-link>|
    <router-link to="/login">登陆页</router-link>|
    <router-link to="/createProduct">新增产品</router-link>
    <router-view></router-view>
  </div>
</template>

<script>

export default {
  name: 'App',
  components: {

  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
