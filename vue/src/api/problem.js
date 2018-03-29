import request from '@/utils/request'

export function list(params) {
  return request({
    url: '/problem',
    method: 'get',
    params
  })
}
