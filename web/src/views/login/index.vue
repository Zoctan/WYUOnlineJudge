<template>
  <div class="login-container">
    <el-form class="card-box login-form"
             autoComplete="on"
             :model="loginForm"
             :rules="loginRules"
             ref="loginForm"
             v-loading.body="loading"
             status-icon>
      <h2 class="title text-center">WYU-OnlineJudge 登录</h2>
       <el-form-item prop="usernameOrEmail">
        <span class="svg-container svg-container_login">
          <svg-icon icon-class="username" />
        </span>
        <el-input type="text"
                  autoComplete="on"
                  v-model="loginForm.usernameOrEmail"
                  placeholder="请输入用户名或邮箱"
                  @keyup.enter.native="handleLogin" />
      </el-form-item>
      <el-form-item prop="password">
        <span class="svg-container">
          <svg-icon icon-class="password" />
        </span>
        <el-input :type="passwordType"
                  autoComplete="on"
                  v-model="loginForm.password"
                  placeholder="请输入密码"
                  @keyup.enter.native="handleLogin" />
        <span class="show-pwd" @click="showPwd">
          <svg-icon icon-class="eye" />
        </span>
      </el-form-item>
      <el-form-item>
        <el-button type="primary"
                   style="width:100%;"
                   :loading="btnLoading"
                   @click.native.prevent="handleLogin">登录</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
  import { isValidateEmail } from '@/utils/validate'

  export default {
    name: 'login',
    data() {
      const validateUsernameOrEmail = (rule, value, callback) => {
        if (value.length < 3) {
          callback(new Error('username must be 3 or more characters'))
        } else {
          callback()
        }
      }
      const validatePassword = (rule, value, callback) => {
        if (value.length < 6) {
          callback(new Error('password must be 6 or more characters'))
        } else {
          callback()
        }
      }
      return {
        loading: false,
        loginForm: {
          usernameOrEmail: 'admin',
          password: 'admin123'
        },
        loginRules: {
          usernameOrEmail: [{ required: true, trigger: 'blur', validator: validateUsernameOrEmail }],
          password: [{ required: true, trigger: 'blur', validator: validatePassword }]
        },
        passwordType: 'password',
        btnLoading: false
      }
    },
    methods: {
      showPwd() {
        this.passwordType = this.passwordType === 'password' ? '' : 'password'
      },
      handleLogin() {
        this.$refs.loginForm.validate(valid => {
          if (valid) {
            const user = {}
            if (isValidateEmail(this.loginForm.usernameOrEmail)) {
              user.email = this.loginForm.usernameOrEmail
            } else {
              user.username = this.loginForm.usernameOrEmail
            }
            user.password = this.loginForm.password
            this.loading = true
            this.$store.dispatch('Login', user).then(() => {
              // 获取用户信息
              this.$store.dispatch('Info').then(response => {
                // 生成路由
                this.$store.dispatch('GenerateRoutes', response.data).then(() => {
                  this.loading = false
                  this.Tip.defaultSuccess('登陆成功')
                  this.$router.addRoutes(this.$store.getters.addRouters)
                  this.$router.push({ path: '/' })
                  location.reload()
                })
              })
            }).catch(() => {
              this.loading = false
            })
          } else {
            this.Tip.defaultSuccess('登陆失败')
            return false
          }
        })
      }
    }
  }
</script>

<style rel="stylesheet/scss" lang="scss">
  $bg:#2d3a4b;
  $light_gray:#eee;

  /* reset element-ui css */
  .login-container {
    .el-input {
      display: inline-block;
      height: 47px;
      width: 85%;
      input {
        background: transparent;
        border: 0;
        -webkit-appearance: none;
        border-radius: 0;
        padding: 12px 5px 12px 15px;
        height: 47px;
        &:-webkit-autofill {
          -webkit-box-shadow: 0 0 0\ 1000px $bg inset !important;
          -webkit-text-fill-color: #fff !important;
        }
      }
    }
    .el-form-item {
      border: 1px solid rgba(255, 255, 255, 0.1);
      background: rgba(0, 0, 0, 0.1);
      border-radius: 5px;
      color: #454545;
    }
  }
</style>

<style rel="stylesheet/scss" lang="scss" scoped>
  $dark_gray:#889aa4;
  $light_gray:#eee;

  .login-container {
    position: fixed;
    height: 100%;
    width: 85%;
    .login-form {
      position: absolute;
      left: 0;
      right: 0;
      width: 420px;
      padding: 35px 35px 15px 35px;
      margin: 70px auto;
    }
    .tips {
      font-size: 14px;
      color: #fff;
      margin-bottom: 10px;
      span {
        &:first-of-type {
          margin-right: 16px;
        }
      }
    }
    .svg-container {
      padding: 6px 5px 6px 15px;
      color: $dark_gray;
      vertical-align: middle;
      width: 30px;
      display: inline-block;
      &_login {
        font-size: 20px;
      }
    }
    .title-container {
      position: relative;
      .title {
        font-size: 26px;
        font-weight: 400;
        color: $light_gray;
        margin: 0 auto 40px auto;
        text-align: center;
      }
    }
    .show-pwd {
      position: absolute;
      right: 10px;
      top: 7px;
      font-size: 16px;
      color: $dark_gray;
      cursor: pointer;
      user-select: none;
    }
  }
</style>
