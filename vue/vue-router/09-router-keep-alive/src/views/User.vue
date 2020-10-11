<template>
  <div class="about">
    <h1>User.vue</h1>
    <button @click="handClick">点我</button>
  </div>
</template>

<script>
export default {
  created() {
    console.log('用户组件被创建...')
  },
  destroyed() {
    console.log('用户组件被销毁...')
  },
  methods: {
    handClick(e) {
      e.target.style.backgroundColor = 'orange'
    }
  }
}
</script>

<style scoped>
button {
  display: block;
  width: 200px;
  height: 100px;
  margin: 0 auto;
}
</style>
