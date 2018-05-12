import request from '@/utils/request'

export function feedback(form) {
  return request({
    url: '/feedback',
    method: 'post',
    data: form
  })
}
