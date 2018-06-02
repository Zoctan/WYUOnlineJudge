import axios from 'axios'
import { Message, MessageBox } from 'element-ui'
import store from '../store'
import { getToken } from '@/store/modules/token'

// 创建axios实例
// https://www.kancloud.cn/yunye/axios/234845
const service = axios.create({
  baseURL: process.env.BASE_API, // api地址
  timeout: 15000, // 请求超时时间
  // 所有请求都以Json形式传送
  // 会有预检请求，服务端需要正常通过OPTIONS请求
  // http://www.ruanyifeng.com/blog/2016/04/cors
  headers: {
    'Content-type': 'application/json;charset=UTF-8'
  }
})

// request拦截器
service.interceptors.request.use(config => {
  if (store.getters.token) {
    // 让每个请求携带自定义token 请根据实际情况自行修改
    config.headers['Authorization'] = getToken()
  }
  return config
}, error => {
  // Do something with request error
  console.log(error) // for debug
  Promise.reject(error)
})

// response拦截器
service.interceptors.response.use(
  response => {
    if (response.data.status === 200) {
      return response.data
    } else {
      Message({
        message: response.data.message,
        type: 'error',
        duration: 5 * 1000
      })
      return Promise.reject('error')
    }
  },
  error => {
    // 401:需要认证
    if (error.response.status === 401) {
      MessageBox.confirm('需要登录', '警告', {
        confirmButtonText: '好的',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        // 为了重新实例化vue-router对象 避免bug
        store.dispatch('FedLogout').then(() => {
          router.push('/login/index')
          // location.reload()
        })
      })
    } else {
      Message({
        message: error.response.data.message,
        type: 'error',
        duration: 5 * 1000
      })
    }
    return Promise.reject(error)
  }
)

export default service
