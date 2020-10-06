<template>
  <div>
    <h1>News.vue</h1>
    <el-input v-model="input" placeholder="请输入内容"></el-input>
  </div>
</template>

<script>
export default {
  name: "News",
  data() {
    return {
      input: ''
    }
  }
}
</script>

<style scoped>

</style>
