<template>
  <div class="about">
    <h1>About.vue</h1>
    <ul>
      <li>params：{{this.$route.params}}</li>
      <li>query：{{this.$route.query}}</li>
    </ul>
  </div>
</template>

<script>
export default {
  name: "About"
}
</script>

<style>

</style>
