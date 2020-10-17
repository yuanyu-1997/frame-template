<template>
  <div>
    <button @click="sendMsg">son</button>
    <grandson/>
  </div>
</template>
<script>
import Grandson from './Grandson.vue'

export default {
  components: {Grandson},
  inject: ['handMsg', 'value'],
  methods: {
    sendMsg() {
      this.handMsg('Son.vue' + this.value);
    }
  }
}
</script>
