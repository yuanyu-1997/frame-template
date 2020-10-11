<template>
  <div id="app">
    {{userInfo}}
  </div>
</template>
<script>
import axios from 'axios'
export default {
  name: 'App',
  data() {
    return {
      userInfo: {}
    }
  },
  mounted() {
    // 本地加载请求静态json文件形式
    // 获取登陆用户的用户信息
    axios.get('/mock/user/userInfo.json').then(({data}) => {
      this.userInfo = data.data
    })
  }
}
</script>

<style>

</style>
