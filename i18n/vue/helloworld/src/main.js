import Vue from 'vue'
import App from './App'

//TODO 创建Vue对象需要传入i18n
import i18n from './i18n'

Vue.config.productionTip = false


new Vue({
  el: '#app',
  i18n,
  components: {App},
  template: '<App/>'
})
