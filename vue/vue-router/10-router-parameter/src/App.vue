<template>
  <div id="app">
    <h1 class="title">vue路由传参以及接收参数的几种方法</h1>
    <div class="header">
      <ul>
        <li>
          <router-link to="/home">Home.vue</router-link>
        </li>
        <li>
          <router-link to="/about">About.vue</router-link>
        </li>
        <li>
          <router-link :to="{path:'/about?id=123'}">About.vue</router-link>
        </li>
        <li>
          <router-link :to="{path:'/about',query:{id:123,msg:'query'}}">About(query)</router-link>
        </li>
        <li>
          <router-link :to="{name:'About',params:{id:123,msg:'params'}}">About(params)</router-link>
        </li>
        <li>
          <button class="btn" @click="simple">About.vue</button>
        </li>
        <li>
          <button class="btn" @click="splicing">About.vue</button>
        </li>
        <li>
          <button class="btn" @click="query">About(query)</button>
        </li>
        <li>
          <button class="btn" @click="params">About(params)</button>
        </li>
        <!-- TODO params参数理解还有点问题 -->
        <li>
          <router-link :to="{name:'Params',params:{id:123}}">Params</router-link>
        </li>
      </ul>
    </div>
    <router-view></router-view>
  </div>
</template>

<script>

export default {
  methods: {
    simple() {
      this.$router.push('/about')
    },
    splicing() {
      this.$router.push('/about?id=123')
    },
    query() {
      this.$router.push({path: '/about', query: {id: 123, msg: 'query'}})
    },
    params() {
      this.$router.push({name: 'About', params: {id: 123, msg: 'params'}})
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

.btn {
  display: block;
  height: 100%;
  padding-left: 20px;
  padding-right: 20px;
}

.title {
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

  margin: 10px;
  float: left;
}

.header ul li a {
  display: inline-block;
  width: 100px;
  height: 100px;
}
</style>
