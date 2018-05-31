import Vue from 'vue'

import 'normalize.css/normalize.css' // A modern alternative to CSS resets

import ElementUI from 'element-ui'
import './styles/element-variables.scss'
import './styles/index.scss' // global css

import App from './App'
import router from './router'
import store from './store'

import './icons' // icon
import './permission' // 权限
import { default as request } from './utils/request'
import { hasPermission } from './utils/hasPermission'
import { Tip } from './utils/Tip'

import lang from 'element-ui/lib/locale/lang/zh-CN'
import locale from 'element-ui/lib/locale'
// 日历插件
import 'vue-event-calendar/dist/style.css' // 1.1.10之后的版本，css被放在了单独的文件中，方便替换
import vueEventCalendar from 'vue-event-calendar'
Vue.use(vueEventCalendar, { locale: 'en' }) // 可以设置语言，支持中文和英文
// 设置语言
locale.use(lang)

Vue.use(ElementUI, {
  size: 'medium'
})

// 全局的常量
Vue.prototype.request = request
Vue.prototype.hasPermission = hasPermission
Vue.prototype.Tip = Tip

// 生产环境时自动设置为 false 以阻止 vue 在启动时生成生产提示
Vue.config.productionTip = false

new Vue({
  el: '#app',
  router,
  store,
  template: '<App/>',
  components: {
    App
  }
})
