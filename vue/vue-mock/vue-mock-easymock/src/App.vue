<!--
  src/App.vue
-->
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
    // 通过easy-mock平台实现数据mock
    http.get('/user/details').then(({data}) => {
      this.userInfo = data.data
    })
  }
}
</script>
<style>
</style>
