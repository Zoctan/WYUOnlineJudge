import router from './router'
import store from './store'
import NProgress from 'nprogress' // Progress 进度条
import 'nprogress/nprogress.css'// Progress 进度条样式
import { getToken } from '@/store/modules/token'

// 白名单,不需要登录的路由
const whiteList = [
  '/401',
  '/404',
  '/home',
  '/problem/index',
  '/contest/index',
  '/user/index',
  '/login/index'
]

router.beforeEach((to, from, next) => {
  NProgress.start() // 开始Progress
  // 尝试获取cookie中的token
  if (getToken()) {
    // 如果没有角色名
    if (store.getters.roleName === null) {
      // 获取用户信息
      store.dispatch('Info').then(response => {
        // 生成路由
        store.dispatch('GenerateRoutes', response.data).then(() => {
          router.addRoutes(store.getters.addRouters)
          next({ ...to })
        })
      })
    } else {
      next()
    }
  } else {
    // 如果前往的路径是白名单内的，设置匿名用户前往
    if (whiteList.indexOf(to.path) !== -1) {
      store.dispatch('Anonymous').then(user => {
        store.dispatch('GenerateRoutes', user).then(() => {
          router.addRoutes(store.getters.addRouters)
          next()
        })
      })
    } else {
      // 如果没有登录,而且路径又不是白名单内的
      next()
      next({ path: '/401', replace: true, query: { noGoBack: true }})
      NProgress.done()
    }
  }
})

router.afterEach(() => {
  NProgress.done() // 结束Progress
})
