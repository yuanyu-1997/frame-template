<template>
  <div id="app">
    <schedule></schedule>
  </div>
</template>

<script>
import Schedule from './views/modules/job/schedule'

export default {
  name: 'App',
  components: {
    Schedule
  }
}
</script>

<style>
#app{
  padding: 20px;
}
</style>
