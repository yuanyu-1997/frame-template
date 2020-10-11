<template>
  <div id="app">
    <div class="header">
      <ul>
        <router-link to="/bar">
          <li>Bar.vue</li>
        </router-link>
        <li>
          <router-link to="/foo">Foo.vue</router-link>
        </li>
      </ul>
      <button @click="goBack">goBack</button>
      <button @click="goBar">Bar.vue</button>
      <button @click="goFoo">Foo.vue</button>
    </div>
    <div>
      {{ username }}
    </div>
    <router-view></router-view>
  </div>
</template>

<script>

export default {
  computed: {
    username() {
      // 我们很快就会看到 `params` 是什么
      return this.$route.params.username
    }
  },
  methods: {
    goBack() {
      window.history.length > 1 ? this.$router.go(-1) : this.$router.push('/')
    },
    goBar(){
      this.$router.push('/bar')
    },
    goFoo(){
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

.header {
  height: 50px;
  background: gray;
}

.header ul {
  list-style: none;
}

.header ul li {
  float: left;
  margin-right: 10px;
  line-height: 50px;
  background-color: pink;
}

a {
  text-decoration: none;
  color: red;
}

.router-link-active{
  color: yellow;
}
</style>
