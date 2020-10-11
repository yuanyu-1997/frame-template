<template>
  <div>
    <h1>Leave.vue</h1>
    <button @click="leave">保存用户信息</button>
  </div>
</template>

<script>
export default {
  name: "Leave",
  data() {
    return {
      isConfirmed: false
    }
  },
  // 离开路由钩子
  beforeRouteLeave(to, from, next) {
    if (this.isConfirmed) {
      next();
    } else {
      if (confirm('确定要离开吗？')) {
        next();
      } else {
        next(false);
      }
    }
  },
  methods: {
    leave() {
      this.$router.go(-1)
    }
  }
}
</script>

<style scoped>

</style>
