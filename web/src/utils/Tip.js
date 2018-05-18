import { Notification } from 'element-ui'

export function Tip() {
}

Tip.info = (title, message) => Notification({ title, message, type: 'info' })
Tip.defaultInfo = (message) => Tip.info('消息', message)

Tip.warning = (title, message) => Notification({ title, message, type: 'warning' })
Tip.defaultWarning = (message) => Tip.warning('警告', message)

Tip.success = (title, message) => Notification({ title, message, type: 'success' })
Tip.defaultSuccess = (message) => Tip.success('成功', message)

Tip.error = (title, message) => Notification({ title, message, type: 'error' })
Tip.defaultError = (message) => Tip.error('错误', message)

/**
 * 未登录提示
 */
export function noLoginTip() {
  Tip.defaultError('请先登录！')
}
