import request from '@/utils/request'

export function list() {
  return request({
    url: '/carousel',
    method: 'get'
  })
}

export function detail(id) {
  return request({
    url: '/carousel/' + id,
    method: 'get'
  })
}
