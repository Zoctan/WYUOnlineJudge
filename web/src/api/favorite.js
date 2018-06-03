import request from '@/utils/request'

export function listProblem(params) {
  return request({
    url: '/favorite/problem',
    method: 'get',
    params
  })
}

export function listNote(params) {
  return request({
    url: '/favorite/note',
    method: 'get',
    params
  })
}

export function update(form) {
  return request({
    url: '/favorite',
    method: 'put',
    data: form
  })
}

export function remove(id) {
  return request({
    url: '/favorite/' + id,
    method: 'delete'
  })
}

export function add(form) {
  return request({
    url: '/favorite',
    method: 'post',
    data: form
  })
}

export function addProblem(form) {
  return request({
    url: '/favorite/problem',
    method: 'post',
    data: form
  })
}

export function removeProblem(form) {
  return request({
    url: '/favorite/problem',
    method: 'delete',
    data: form
  })
}
