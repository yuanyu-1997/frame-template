<template>
  <div>
    <h1>noi18n</h1>
    <form action="" method="post">
      <div>
        <label for="username">{{$t("form.login.username.desc")}}</label>
        <input type="text" id="username" name="username" :placeholder="$t('form.login.username.placeholder')">
      </div>
      <div>
        <label for="password">{{$t("form.login.password.desc")}}</label>
        <input type="text" id="password" name="password" :placeholder="$t('form.login.password.placeholder')">
      </div>
      <div>
        <input type="submit" :value="$t('form.login.submit')">
      </div>
    </form>
  </div>
</template>

<script>
  export default {
    name: 'HelloWorld',
    data() {
      return {
        msg: 'Welcome to Your Vue.js App'
      }
    }
  }
</script>
<style scoped>
</style>
