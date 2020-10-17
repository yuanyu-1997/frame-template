<!--
  父组件
-->
<template>
  <div>
    <Son @son-msg="handMsg"/>
  </div>
</template>
<script>
import Son from './Son.vue'

export default {
  components: {
    Son
  },
  // 父组件通过provide将自己的教据以对象形式传出去
  provide() {
    return {
      handMsg(data) {
        alert(data)
      },
      value: this.value
    }
  },
  data() {
    return {
      value: '我是...'
    }
  },
  methods: {}
}
</script>
