<template>
  <div id="app">
    {{userInfo}}
  </div>
</template>
<script>
import http from "./util/httpRequest";
export default {
  name: 'App',
  data() {
    return {
      userInfo: {}
    }
  },
  mounted() {
    // 本地集成mockjs实现数据mock
    // 注意控制台看不到发送请求，发请求前就被拦截了
    http.get('/user/details').then(({data}) => {
      this.userInfo = data.data
    })
  }
}
</script>
<style>
</style>
