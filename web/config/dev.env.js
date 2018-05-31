const merge = require('webpack-merge')
const prodEnv = require('./prod.env')

module.exports = merge(prodEnv, {
  NODE_ENV: '"development"',
  BASE_API: '"https://api2.txdna.cn/api-1.0"',
  // BASE_API: '"http://localhost:9090"',
})
