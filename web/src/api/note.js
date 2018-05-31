import request from '@/utils/request'

export function list() {
  return request({
    url: '/note',
    method: 'get'
  })
}

export function detail(id) {
  return request({
    url: '/note/' + id,
    method: 'get'
  })
}
