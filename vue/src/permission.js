import router from './router'
import store from './store'
import NProgress from 'nprogress' // Progress 进度条
import 'nprogress/nprogress.css'// Progress 进度条样式
import { getToken } from '@/utils/token'

const whiteList = ['/dashboard'] // 白名单,不需要登录的路由

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
    // 如果前往的路径是白名单内的,就可以直接前往
    if (whiteList.indexOf(to.path) !== -1) {
      next()
    }
  }
})

router.afterEach(() => {
  NProgress.done() // 结束Progress
})
