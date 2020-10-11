<template>
  <h1>User.vue</h1>
</template>

<script>
export default {
  name: "User",
  // 3、组件内部的导航守卫
  beforeRouteEnter(to, from, next) {
    //next(false)
    next()
  }
}
</script>

<style scoped>

</style>
