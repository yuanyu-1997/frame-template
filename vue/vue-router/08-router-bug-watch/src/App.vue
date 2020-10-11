<template>
  <div id="app">
    <h1 class="title">Vue Router中响应路由参数变化</h1>
    <div class="header">
      <ul>
        <li>
          <router-link to="/">Home.vue</router-link>
        </li>
        <li>
          <router-link to="/user/1">User1</router-link>
        </li>
        <li>
          <router-link to="/user/2">User2</router-link>
        </li>
        <li>
          <router-link to="/user/3">User3</router-link>
        </li>
      </ul>
    </div>
    <router-view></router-view>
  </div>
</template>

<script>

export default {}
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
