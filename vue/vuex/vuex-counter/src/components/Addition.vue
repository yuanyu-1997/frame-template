<template>
  <div>
    <!--
        <h3>当前最新的数量是【{{this.$store.state.count}}】</h3>
        <h3>当前最新的数量是【{{$store.state.count}}】</h3>
    -->
    <h3>{{$store.getters.showNum}}</h3>
    <button @click="addOne">+1</button>
    <button @click="addN">+N</button>
    <button @click="addOneAsync">+1 Async</button>
    <button @click="addNAsync">+N Async</button>
  </div>
</template>

<script>
export default {
  data() {
    return {}
  },
  methods: {
    addOne() {
      // this.$store.state.count++ // 可以实习效果，但是不能这样做，只有mutations里面的函数才有权力去修改state中的数据
      // commit的作用，就是调用某个mutation函数
      this.$store.commit('add')
    },
    addN() {
      this.$store.commit('addN', 3)
    },
    // 异步地让 count 自增 +1
    addOneAsync() {
      // 这里的 dispatch 函数，专门用来触发 action
      this.$store.dispatch('addAsync')
    },
    addNAsync() {
      this.$store.dispatch('addNAsync', 5)
    }
  }
}
</script>
