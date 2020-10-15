// TODO 创建VueI18n对象
import Vue from 'vue'
import VueI18n from 'vue-i18n'

Vue.use(VueI18n)

// 判断游览器环境
let locale;
const language = navigator.language.toLocaleLowerCase();
if (language === "zh-cn") {
  locale = "zh_cn"
}else if(language === "en-us"){
  locale = "en_us"
}else {
  locale = "zh_cn"
}
alert(locale)
const i18n = new VueI18n({
  locale:  locale,
  messages: {
    'zh_cn': require('@/assets/languages/zh_cn.json'),
    'en_us': require('@/assets/languages/en_us.json')
  }
})
export default i18n
