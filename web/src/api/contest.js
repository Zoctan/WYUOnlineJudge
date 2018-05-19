import request from '@/utils/request'

export function list(params) {
  return request({
    url: '/contest',
    method: 'get',
    params
  })
}

export function detail(id) {
  return request({
    url: '/contest/' + id,
    method: 'get'
  })
}

export function join(id) {
  return request({
    url: '/contest/user/' + id,
    method: 'post'
  })
}

export function out(id) {
  return request({
    url: '/contest/user/' + id,
    method: 'delete'
  })
}

export function validatePassword(form) {
  return request({
    url: '/contest/password',
    method: 'post',
    data: form
  })
}

export function contestProblem(id, params) {
  return request({
    url: '/contest/problem/' + id,
    method: 'get',
    params
  })
}
