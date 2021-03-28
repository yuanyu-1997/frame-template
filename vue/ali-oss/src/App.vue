<template>
  <div id="app">
    <el-dialog
        :visible="true">
      <el-form :model="dataForm" ref="dataForm" @keyup.enter.native="dataFormSubmit()"
               label-width="140px">
        <el-form-item label="品牌logo地址">
          <single-upload v-model="dataForm.logo"></single-upload>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
    </el-dialog>
  </div>
</template>


<script>
import SingleUpload from './components/upload/singleUpload'

export default {
  components: {SingleUpload},
  data() {
    return {
      visible: false,
      dataForm: {
        logo: ''
      }
    }
  },
  methods: {
    /**
     * 表单提交
     */
    dataFormSubmit() {
      alert(this.dataForm.logo)
    }
  }
}
</script>

<style>

</style>
