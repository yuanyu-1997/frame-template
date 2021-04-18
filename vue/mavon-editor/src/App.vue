<template>
  <div id="app">

    <el-container>
      <el-header>Header</el-header>
      <el-container>
        <el-aside width="200px">Aside</el-aside>
        <el-main>
          <div id="mavon-editor">
            <mavon-editor
                v-model="editForm.content"
                ref=md
                @imgAdd="imgAdd"
                @imgDel="imgDel"
            />
          </div>
        </el-main>
      </el-container>
    </el-container>

  </div>
</template>


<script>

export default {
  data() {
    return {
      editForm: {
        content: ''
      }
    }
  },
  methods: {
    imgAdd(pos, file) {

      // 第一步 将图片上传到服务器

      // 第二步 将返回的url替换到文本原位置
      this.$refs.md.$img2Url(pos, 'https://img.jpg');

    },
    imgDel() {

    }
  }
}
</script>

<style>

#app {

}
* {
  margin: 0;
  padding: 0;
}


.el-header {
  background-color: gray;
  color: #333;
  text-align: center;
  line-height: 60px;
}

.el-aside {
  background-color: pink;
  color: #333;
  text-align: center;
  line-height: 500px;
}

.el-main {
  padding: 2px;
  background-color: aliceblue;
  color: #333;
  text-align: center;
  line-height: 100%;
}


</style>
