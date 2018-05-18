/**
 * Unix时间戳转换成日期格式  unix2CurrentTime("1497232433000")
 * @param unixTime Unix时间戳
 * @return string yyyy-MM-dd HH:mm:ss
 */
export function unix2CurrentTime(unixTime) {
  const date = new Date(parseInt(unixTime))
  const y = date.getFullYear()
  let m = date.getMonth() + 1
  m = m < 10 ? ('0' + m) : m
  let d = date.getDate()
  d = d < 10 ? ('0' + d) : d
  let h = date.getHours()
  h = h < 10 ? ('0' + h) : h
  let minute = date.getMinutes()
  let second = date.getSeconds()
  minute = minute < 10 ? ('0' + minute) : minute
  second = second < 10 ? ('0' + second) : second
  return y + '-' + m + '-' + d + ' ' + h + ':' + minute + ':' + second
}

/**
 * 两个Unix时间戳差值
 * @param unixTimeStart Unix时间戳1
 * @param unixTimeEnd Unix时间戳2
 * @return string xx 小时 | xx 天
 */
export function unixDifference(unixTimeStart, unixTimeEnd) {
  const difference = (unixTimeEnd - unixTimeStart) / 3600 / 1000
  if (difference >= 24) {
    return difference / 24 + '天'
  } else {
    return difference + '小时'
  }
}
