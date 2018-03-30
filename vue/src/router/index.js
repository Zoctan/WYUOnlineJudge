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
  { path: '/login', component: _import('login/index'), hidden: true },
  { path: '/404', component: _import('errorPage/404'), hidden: true },
  { path: '/401', component: _import('errorPage/401'), hidden: true },
  {
    path: '',
    component: Layout,
    redirect: 'dashboard',
    icon: 'dashboard',
    noDropDown: true,
    children: [{
      path: 'dashboard',
      name: '主页',
      component: _import('dashboard/index'),
      meta: { title: 'dashboard', noCache: true }
    }]
  },
  {
    path: '/problem',
    component: Layout,
    redirect: '/problem/index',
    icon: 'permission',
    name: '题目',
    children: [{
      path: 'index',
      name: '题目列表',
      component: _import('problem/index')
    }]
  },
  {
    path: '/contest',
    component: Layout,
    redirect: '/contest/index',
    icon: 'permission',
    name: '比赛',
    children: [{
      path: 'index',
      name: '比赛列表',
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
    path: '/user',
    component: Layout,
    redirect: '/user/index',
    hidden: true,
    children: [{
      path: 'index',
      name: '用户信息',
      component: _import('user/index')
    }]
  }
]
