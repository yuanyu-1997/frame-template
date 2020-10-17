<template>
  <div>
    <button @click="sendMsg">grandson</button>
  </div>
</template>
<script>
export default {
  inject: ['handMsg', 'value'],
  methods: {
    sendMsg() {
      this.handMsg('Grandson.vue');
    }
  }
}
</script>
