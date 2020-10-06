<template>
  <div class="about">
    <el-menu
        class="el-menu-demo"
        mode="horizontal"
        background-color="#545c64"
        text-color="#fff"
        active-text-color="#ffd04b">
      <el-menu-item index="1">处理中心</el-menu-item>
      <el-menu-item index="2">我的工作台</el-menu-item>
      <el-menu-item index="3">订单管理</el-menu-item>
      <el-submenu index="4">
        <template slot="title">消息中心</template>
        <el-menu-item index="4-1">
          <router-link to="/about/news">Message.vue</router-link>
        </el-menu-item>
        <el-menu-item index="4-2">
          <router-link to="/about/message">News.vue</router-link>
        </el-menu-item>
      </el-submenu>
    </el-menu>
    <keep-alive>
      <router-view></router-view>
    </keep-alive>
  </div>

</template>

<script>
export default {
  name: "About"
}
</script>

<style>
.about {
  margin: 0;
  padding: 0;
}
</style>
