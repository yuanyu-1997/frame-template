<template>
  <div id="app">
    <h1 class="title">路由守卫</h1>
    <div class="header">
      <ul>
        <li>
          <router-link to="/bar">Bar.vue</router-link>
        </li>
        <li>
          <router-link to="/foo">Foo.vue</router-link>
        </li>
        <li>
          <router-link to="/user">User.vue</router-link>
        </li>
        <li>
          <router-link to="/leave">Leave.vue</router-link>
        </li>
        <li>
          <button @click="goBar">Bar.vue</button>
        </li>
        <li>
          <button @click="goFoo">Foo.vue</button>
        </li>
      </ul>
    </div>
    <router-view></router-view>
  </div>
</template>

<script>

export default {
  methods: {
    goBar() {
      this.$router.push('/bar')
    },
    goFoo() {
      this.$router.push('/foo')
    }
  }
}
</script>

<style>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

a {
  text-decoration: none;
}
.title{
  text-align: center;
}
.header {
  overflow: hidden;
  border-bottom: 1px solid red;
  border-top: 1px solid red;
}

.header ul {
  list-style: none;
}

.header ul li {
  height: 25px;
  width: 100px;
  margin: 10px;
  float: left;
}

.header ul li a {
  display: inline-block;
  width: 100px;
  height: 100px;
}
</style>
