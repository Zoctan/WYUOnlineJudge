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
