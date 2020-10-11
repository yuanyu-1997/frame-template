<template>
  <div>
    当前用户ID为：{{ id }}
  </div>
</template>

<script>
export default {
  data() {
    return {
      id: this.$route.params.id
    }
  },
  watch: {
    // 方式一
    // $route(to, from){
    //   this.id = to.params.id
    //   console.log(from)
    // }
  },
  // 方式二
  beforeRouteUpdate (to, from,next) {
    this.id = to.params.id
    console.log(from)
    next();
  },
  created() {
    console.log('created...')
  }
}
</script>

<style scoped>

</style>
