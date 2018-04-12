/**
 * url地址
 * @param url
 * @returns {boolean}
 */
export function isValidateURL(url) {
  const reg = /^(https?|ftp):\/\/([a-zA-Z0-9.-]+(:[a-zA-Z0-9.&%$-]+)*@)*((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]?)(\.(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9]?[0-9])){3}|([a-zA-Z0-9-]+\.)*[a-zA-Z0-9-]+\.(com|edu|gov|int|mil|net|org|biz|arpa|info|name|pro|aero|coop|museum|[a-zA-Z]{2}))(:[0-9]+)*(\/($|[a-zA-Z0-9.,?'\\+&%$#=~_-]+))*$/
  return reg.test(url)
}

/**
 * 小写字母
 * @param str
 * @returns {boolean}
 */
export function isValidateLowerCase(str) {
  const reg = /^[a-z]+$/
  return reg.test(str)
}

/**
 * 大写字母
 * @param str
 * @returns {boolean}
 */
export function isValidateUpperCase(str) {
  const reg = /^[A-Z]+$/
  return reg.test(str)
}

/**
 * 大小写字母
 * @param str
 * @returns {boolean}
 */
export function isValidateAlphabets(str) {
  const reg = /^[A-Za-z]+$/
  return reg.test(str)
}

/**
 * 邮箱
 * @param email
 * @returns {boolean}
 */
export function isValidateEmail(email) {
  const reg = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
  return reg.test(email)
}

/**
 * 角色名
 * @param roleName
 * @returns {boolean}
 */
export function isValidateRoleName(roleName) {
  const reg = /^ROLE_[A-Z]+$/
  return reg.test(roleName)
}

export function isValidateString(string) {
  const reg = {
    basic: / /, // vb中的简单空白字符过滤
    classic: /\s/, // 传统的js空白字符过滤
    ecmascript5: /[\s\xA0\u1680\u180E\u2000-\u200A\u2028\u2029\u202F\u205F\u3000]/, // ECMAScript 5中对/s的补充定义
    ex: /[\s\xA0\u2028\u2029]/, // 印象中是和js中的标识符或者字符串有关，忘了从哪来的了XD
    control: /[\x00-\x1F\x7F-\x9F]/, // 补充了控制字符的简单过滤
    dotNet: /[\s\xA0\u2000-\u200B\u3000\uFEFF]/, // .NET平台的默认实现
    unicode: /[\s\x85\xA0\u1680\u180E\u2000-\u200A\u2028\u2029\u202F\u205F\u3000]/, // 过滤Unicode标准中定义的空白字符
    complex: /[\x00-\x20\x7F-\xA0\u1680\u180E\u2000-\u200B\u2028\u2029\u202F\u205F\u3000\uFEFF]/ // 复合的过滤，是以上几种的并集
  }
  for (const i in reg) {
    if (reg[i].test(string)) {
      return false
    }
  }
  return true
}
