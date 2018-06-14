<template>
<div class="login-container background-img">
  <el-menu
  :default-active="activeIndex"
  class="el-menu-demo"
  mode="horizontal"
  @select="handleSelect"
  background-color="transparent"
  text-color="#96A7BA"
  active-text-color="#fff">
  <el-menu-item index="login">登录</el-menu-item>
  <el-menu-item index="res">注册</el-menu-item>
  </el-menu>
  <div class="close-login" @click="closeLogin">×</div>
    <el-form v-if="activeIndex=='login'" class="card-box login-form ruleForm"
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
                  @keyup.enter.native="handleLogin"
                  auto-complete="off"/>
      </el-form-item>
      <el-form-item prop="password">
        <span class="svg-container">
          <svg-icon icon-class="password" />
        </span>
        <el-input :type="passwordType"
                  autoComplete="on"
                  v-model="loginForm.password"
                  placeholder="请输入密码"
                  @keyup.enter.native="handleLogin"
                  auto-complete="off"/>
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
    <!-- 注册form -->
    <!-- <el-form v-else class="card-box res-form ruleForm"
             autoComplete="on"
             :model="resForm"
             :rules="resRules"
             ref="resForm"
             v-loading.body="loading"
             status-icon>
      <h2 class="title text-center">WYU-OnlineJudge 注册</h2>
       <el-form-item prop="usernameOrEmail">
        <span class="svg-container svg-container_login">
          <svg-icon icon-class="username" />
        </span>
        <el-input type="text"
                  autoComplete="on"
                  v-model="resForm.usernameOrEmail"
                  placeholder="请输入用户名或邮箱"
                  @keyup.enter.native="handle Login"
                  auto-complete="off"/>
      </el-form-item>
      <el-form-item prop="password">
        <span class="svg-container">
          <svg-icon icon-class="password" />
        </span>
        <el-input :type="passwordType"
                  autoComplete="on"
                  v-model="resForm.password"
                  placeholder="请输入密码"
                  @keyup.enter.native="handleLogin"
                  auto-complete="off"/>
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
    </el-form> -->
  </div>
</template>

<script>
import { validateEmail } from "@/utils/validate";
import { mapGetters } from "vuex";

export default {
  name: "login",
  created() {
    console.log(this.showlogin);
  },
  computed: {
    ...mapGetters(["showlogin"])
  },
  data() {
    const validateUsernameOrEmail = (rule, value, callback) => {
      if (value.length < 3) {
        callback(new Error("username must be 3 or more characters"));
      } else {
        callback();
      }
    };
    const validatePassword = (rule, value, callback) => {
      if (value.length < 6) {
        callback(new Error("password must be 6 or more characters"));
      } else {
        callback();
      }
    };
    return {
      loading: false,
      loginForm: {
        usernameOrEmail: "admin",
        password: "admin123"
      },
      loginRules: {
        usernameOrEmail: [
          {
            required: true,
            trigger: "blur",
            validator: validateUsernameOrEmail
          }
        ],
        password: [
          { required: true, trigger: "blur", validator: validatePassword }
        ]
      },
      passwordType: "password",
      btnLoading: false,
      activeIndex: "login"
    };
  },
  methods: {
    showPwd() {
      this.passwordType = this.passwordType === "password" ? "" : "password";
    },
    handleLogin() {
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          const user = {};
          if (validateEmail(this.loginForm.usernameOrEmail)) {
            user.email = this.loginForm.usernameOrEmail;
          } else {
            user.username = this.loginForm.usernameOrEmail;
          }
          user.password = this.loginForm.password;
          this.loading = true;
          this.$store
            .dispatch("Login", user)
            .then(() => {
              // 获取用户信息
              this.$store.dispatch("Info").then(response => {
                // 生成路由
                this.$store
                  .dispatch("GenerateRoutes", response.data)
                  .then(() => {
                    this.loading = false;
                    this.Tip.defaultSuccess("登陆成功");
                    this.$router.addRoutes(this.$store.getters.addRouters);
                    this.$router.push({ path: "/" });
                    location.reload();
                  });
              });
            })
            .catch(() => {
              this.loading = false;
            });
        } else {
          this.Tip.defaultSuccess("登陆失败");
          return false;
        }
      });
    },
    handleSelect(key, keyPath) {
      this.activeIndex = key;
      console.log(key, keyPath);
    },
    closeLogin(){
      this.$store.commit('change_showLogin',false)
    }
  }
};
</script>

<style rel="stylesheet/scss" lang="scss">
$bg: #2d3a4b;
$light_gray: #eee;

/* reset element-ui css */
.login-container {
  .text-center {
    color: #eee;
  }
  .el-input {
    display: inline-block;
    height: 47px;
    width: 70%;
    input {
      // background: transparent;
      border: 0;
      -webkit-appearance: none;
      border-radius: 0;
      padding: 12px 5px 12px 15px;
      height: 47px;
      &:-webkit-autofill {
        background-color: #eee;
        -webkit-box-shadow: 0 0 0\ 1000px $bg inset !important;
        -webkit-text-fill-color: rgb(110, 110, 110) !important;
      }
    }
  }
  .el-form-item {
    border: 1px solid rgba(255, 255, 255, 0.1);
    background: rgba(0, 0, 0, 0.4);
    border-radius: 5px;
    color: #454545;
    margin-bottom: 20px;
  }
}
.el-form {
  display: inline-block;
}
.el-form > div {
  margin: auto;
}
</style>

<style rel="stylesheet/scss" lang="scss" scoped>
$dark_gray: #889aa4;
$light_gray: #eee;

.login-container {
  z-index: 9999;
  position: fixed;
  top: 100px;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  height: 500px;
  width: 400px;
  margin: 0 auto;
  box-shadow: 0px 10px 20px #2d3a4b;
  .login-form {
    position: absolute;
    left: 0;
    right: 0;
    width: 400px;
    padding: 35px 35px 15px 35px;
    margin: 20px auto;
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
    width: 40px;
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
      color: #eee;
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
.background-img {
  background-image: url(http://img.hb.aicdn.com/1bfb55ecedc7d6fde57e2f2b2e8108d0514ac0276ed4-Ik7gBI_fw658);
  background-color: rgba(255, 255, 255, 0.4);
  background-size: 200%;
}
.el-menu--horizontal {
  padding: 20px;
  border-bottom: 0;
  > .el-menu-item {
    height: 40px;
    line-height: 40px;
  }
}
.el-input--medium .el-input__inner {
  height: 46px;
}
.close-login {
  position: absolute;
  top: 30px;
  right: 30px;
  color: #fff;
  font-size: 20px;
}
</style>