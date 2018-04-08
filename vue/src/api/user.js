import request from '@/utils/request'

export function list(params) {
  return request({
    url: '/user',
    method: 'get',
    params
  })
}

export function validatePassword(form) {
  return request({
    url: '/user/password',
    method: 'post',
    data: form
  })
}

export function update(form) {
  return request({
    url: '/user',
    method: 'put',
    data: form
  })
}

export function remove(userId) {
  return request({
    url: '/user/' + userId,
    method: 'delete'
  })
}

export function register(form) {
  return request({
    url: '/user',
    method: 'post',
    data: form
  })
}

export function login(form) {
  return request({
    url: '/user/login',
    method: 'post',
    data: form
  })
}

export function info() {
  return request({
    url: '/user/info',
    method: 'get'
  })
}

export function logout() {
  return request({
    url: '/user/logout',
    method: 'get'
  })
}
