import request from '@/utils/request'

export function run(code) {
  return request({
    url: '/code?run=true',
    method: 'post',
    data: code
  })
}

export function submit(code) {
  return request({
    url: '/code?run=false',
    method: 'post',
    data: code
  })
}

export function listSubmitCode(problemId, params) {
  return request({
    url: '/code/' + problemId,
    method: 'get',
    params
  })
}
