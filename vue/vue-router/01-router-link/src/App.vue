<template>
  <div id="app" style="height: 100%">
    <el-container style="height: 100%">
      <el-aside class="aside">
        <el-menu style="height: 100%"
                 class="el-menu-vertical-demo"
                 background-color="#545c64"
                 text-color="#fff">
          <el-submenu index="1">
            <template slot="title">
              <i class="el-icon-location"></i>
              <span>导航</span>
            </template>
            <el-menu-item index="1-1">
              <!--
                router-link
              -->
              <router-link to="/about">About.vue</router-link>
            </el-menu-item>
            <el-menu-item index="1-2">
              <router-link to="/home">Home.vue</router-link>
            </el-menu-item>
          </el-submenu>
        </el-menu>
      </el-aside>
      <el-container>
        <el-header class="header" style="background-color: pink;">Header</el-header>
        <el-main class="main">
          <!--
            router-view
          -->
          <router-view></router-view>
        </el-main>
        <el-footer class="footer" style="background-color: gray;">Footer</el-footer>
      </el-container>
    </el-container>
  </div>
</template>

<script>

export default {
  name: 'App',
  components: {}
}
</script>

<style>
* {
  margin: 0;
  padding: 0;
}
a {
  text-decoration: none;
  color: white;
}
body, html {
  height: 100%;
}

.aside {
  background-color: gray;
  width: 250px !important;
}
.main{
  background-color: yellow;
}
/*
*/
.router-link-active {
  color: red;
}

</style>
