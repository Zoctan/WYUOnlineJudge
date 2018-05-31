import Vue from 'vue'
import Router from 'vue-router'
const _import = require('./_import_' + process.env.NODE_ENV)
// in development env not use Lazy Loading,because Lazy Loading too many pages will cause webpack hot update too slow.so only in production use Lazy Loading

/* layout */
import Layout from '../views/layout/Layout'

Vue.use(Router)

/**
  * icon : the icon show in the sidebar
  * hidden : if `hidden:true` will not show in the sidebar
  * redirect : if `redirect:noRedirect` will not redirect in the levelBar
  * noDropDown : if `noDropDown:true` will not has submenu in the sidebar
  * meta : `{ permission: ['a:xx'] }`  will control the page permission
  **/
export const constantRouterMap = [
  { path: '/404', component: _import('errorPage/404'), hidden: true },
  { path: '/401', component: _import('errorPage/401'), hidden: true },
  {
    path: '',
    component: Layout,
    redirect: 'home',
    icon: 'home',
    children: [{
      component: _import('home/index'),
      path: 'home',
      name: '主页',
      meta: { noCache: true }
    }]
  },
  {
    path: '/login',
    component: Layout,
    redirect: 'noRedirect',
    hidden: true,
    children: [{
      path: 'index',
      name: '登录',
      component: _import('login/index')
    }]
  },
  {
    path: '/user',
    component: Layout,
    redirect: 'noRedirect',
    hidden: true,
    children: [{
      path: 'index',
      name: '用户信息',
      component: _import('user/index')
    }]
  },
  {
    path: '/problem',
    component: Layout,
    redirect: 'noRedirect',
    icon: 'code',
    children: [{
      path: 'index',
      name: '题目',
      component: _import('problem/index')
    }]
  },
  {
    path: '/contest',
    component: Layout,
    redirect: 'noRedirect',
    icon: 'component',
    children: [{
      path: 'index',
      name: '比赛',
      component: _import('contest/index')
    }]
  }
]

export default new Router({
  // mode: 'history', //后端支持可开
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRouterMap
})

export const asyncRouterMap = [
  {
    path: '/problem',
    component: Layout,
    hidden: true,
    children: [{
      path: ':id',
      name: '题目详情',
      component: _import('problem/detail'),
      meta: { permission: ['problem:detail'] }
    }]
  },
  {
    path: '/contest',
    component: Layout,
    hidden: true,
    children: [{
      path: ':id',
      name: '比赛详情',
      component: _import('contest/detail'),
      meta: { permission: ['contest:detail'] }
    }]
  },
  {
    path: '/feedback',
    component: Layout,
    redirect: 'noRedirect',
    icon: 'component',
    meta: { permission: ['problem:detail'] },
    children: [{
      path: 'index',
      name: '反馈',
      component: _import('feedback/index')
    }]
  },
  {
    path: '/chatroom',
    component: Layout,
    redirect: 'noRedirect',
    icon: 'component',
    meta: { permission: ['problem:detail'] },
    children: [{
      path: 'index',
      name: '聊天室',
      component: _import('chatroom/index')
    }]
  }
]
