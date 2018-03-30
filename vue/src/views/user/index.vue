<template>
  <div class="app-container">
    <el-form v-loading.body="loading"
             :model="tmpUser"
             :rules="updateInfoRules"
             ref="tmpUser"
             label-width="115px">
      <div style="text-align: center; width: 65vw; margin-bottom: 12px">
        <img :width="size" :height="size" :src="user.avatar" style="border-radius: 10px; margin: 0 auto;" />
      </div>
      <div v-if="token === null" style="text-align: center; width: 65vw; margin-bottom: 12px">
        <span>{{ user.username }}</span>
      </div>
      <div v-else>
        <el-row :gutter="18">
          <el-col :span="9">
            <el-form-item label="用户名"
                          prop="username">
              <el-input v-model="tmpUser.username" />
            </el-form-item>
          </el-col>
          <el-col :span="9">
            <el-form-item label="邮箱"
                          prop="email">
              <el-input v-model="tmpUser.email" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="18">
          <el-col :span="9">
            <el-form-item label="注册时间">
              <el-input :value="unix2CurrentTime(user.registerTime)" readonly="readonly" />
            </el-form-item>
          </el-col>
          <el-col :span="9">
            <el-form-item label="最后登陆时间">
              <el-input :value="unix2CurrentTime(user.lastLoginTime)" readonly="readonly" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="18">
          <el-col :span="18">
            <el-form-item label="简介"
                          prop="resume">
              <el-input type="textarea"
                        :autosize="{ minRows: 3, maxRows: 6}"
                        v-model="tmpUser.resume" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item>
          <el-row :gutter="18">
            <el-col :span="6">
              <el-button type="success"
                         :loading="btnLoading"
                         @click="regainUserInfo">重新获取信息</el-button>
            </el-col>
            <el-col :span="6">
              <el-button type="primary"
                         :loading="btnLoading"
                         @click="updateInfo">修改信息</el-button>
            </el-col>
            <el-col :span="6">
              <el-button type="danger"
                         @click="showUpdatePassword">修改密码</el-button>
            </el-col>
          </el-row>
        </el-form-item>
      </div>
    </el-form>

    <el-dialog title="修改密码"
               :visible.sync="dialogFormVisible">
      <el-form class="small-space"
               :model="tmpPassword"
               :rules="updatePasswordRules"
               ref="tmpPassword"
               status-icon
               label-position="left"
               label-width="115px"
               style='width: 400px; margin-left:50px;'>
        <el-form-item label="旧密码"
                      prop="oldPassword" required>
          <el-input type="password"
                    prefix-icon="el-icon-edit"
                    auto-complete="off"
                    placeholder="请输入旧密码"
                    v-model="tmpPassword.oldPassword" />
        </el-form-item>
        <el-form-item label="新密码"
                      prop="newPassword" required>
          <el-input type="password"
                    prefix-icon="el-icon-edit"
                    auto-complete="off"
                    placeholder="请输入新密码"
                    v-model="tmpPassword.newPassword" />
        </el-form-item>
        <el-form-item label="新密码"
                      prop="newPassword2" required>
          <el-input type="password"
                    prefix-icon="el-icon-edit"
                    auto-complete="off"
                    placeholder="请再次输入新密码"
                    v-model="tmpPassword.newPassword2" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button type="danger"
                   @click="resetForm('tmpPassword')">重置</el-button>
        <el-button type="primary"
                   :loading="btnLoading"
                   @click.native.prevent="updatePassword">修改</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import store from '@/store'
  import { update as updateUser, validatePassword } from '@/api/user'
  import { unix2CurrentTime } from '@/utils/date'
  import { isValidateEmail } from '@/utils/validate'
  import { setToken } from '@/utils/token'
  import { mapGetters, mapState } from 'vuex'

  export default {
    created() {
      this.setInfo()
    },
    data() {
      const validateOldPassword = (rule, value, callback) => {
        if (value.length < 6) {
          callback(new Error('旧密码长度必须大于等于6'))
        }
        // promise异步查询后端密码
        this.validateOldPassword(value).then(isValidate => {
          if (!isValidate) {
            callback(new Error('旧密码错误'))
          } else {
            callback()
          }
        })
      }
      const validateNewPassword = (rule, value, callback) => {
        if (value.length < 6) {
          callback(new Error('新密码长度必须大于等于6'))
        } else if (this.isOldNewPasswordSame()) {
          callback(new Error('新旧密码不能相同'))
        } else {
          callback()
        }
      }
      const validateNewPassword2 = (rule, value, callback) => {
        if (value.length < 6) {
          callback(new Error('新密码长度必须大于等于6'))
        } else if (!this.isNewPasswordSame()) {
          callback(new Error('两次密码不相同'))
        } else {
          callback()
        }
      }
      const validateUsername = (rule, value, callback) => {
        if (value.length < 3) {
          callback(new Error('用户名长度必须大于等于3'))
        } else {
          callback()
        }
      }
      const validateEmail = (rule, value, callback) => {
        if (!isValidateEmail(value)) {
          callback(new Error('邮箱格式不正确'))
        } else {
          callback()
        }
      }
      return {
        loading: false,
        btnLoading: false,
        dialogFormVisible: false,
        size: 170,
        tmpPassword: {
          oldPassword: '',
          newPassword: '',
          newPassword2: ''
        },
        updatePasswordRules: {
          oldPassword: [{ required: true, trigger: 'blur', validator: validateOldPassword }],
          newPassword: [{ required: true, trigger: 'blur', validator: validateNewPassword }],
          newPassword2: [{ required: true, trigger: 'blur', validator: validateNewPassword2 }]
        },
        tmpUser: {
          id: '',
          username: '',
          email: '',
          resume: ''
        },
        updateInfoRules: {
          username: [{ required: true, trigger: 'blur', validator: validateUsername }],
          email: [{ required: true, trigger: 'blur', validator: validateEmail }]
        }
      }
    },
    computed: {
      ...mapGetters([
        'token'
      ]),
      ...mapState({
        user: state => state.user
      })
    },
    methods: {
      setInfo() {
        this.tmpUser.username = this.user.username
        this.tmpUser.email = this.user.email
        this.tmpUser.resume = this.user.resume
      },
      validateOldPassword(oldPassword) {
        const user = {}
        user.id = this.user.userId
        user.password = oldPassword
        return validatePassword(user).then(response => response.data)
      },
      isOldNewPasswordSame() {
        return this.tmpPassword.oldPassword === this.tmpPassword.newPassword
      },
      isNewPasswordSame() {
        return this.tmpPassword.newPassword === this.tmpPassword.newPassword2
      },
      resetToken(token) {
        setToken(token)
        this.user.token = token
      },
      regainUserInfo() {
        // 重新获取用户信息
        this.loading = true
        this.btnLoading = true
        store.dispatch('Info').then(() => {
          this.loading = false
          this.btnLoading = false
        })
      },
      unix2CurrentTime(timestamp) {
        return unix2CurrentTime(timestamp)
      },
      resetForm(formName) {
        this.$refs[formName].resetFields()
      },
      update(user) {
        this.btnLoading = true
        updateUser(user).then(response => {
          this.$message.success('修改成功')
          this.resetToken(response.data)
          this.regainUserInfo()
          this.btnLoading = false
        })
      },
      updateInfo() {
        this.$refs.tmpUser.validate(valid => {
          if (valid) {
            this.tmpUser.id = this.user.userId
            this.update(this.tmpUser)
          } else {
            return false
          }
        })
      },
      showUpdatePassword() {
        this.dialogFormVisible = true
        this.tmpPassword.oldPassword = ''
        this.tmpPassword.newPassword = ''
        this.tmpPassword.newPassword2 = ''
      },
      updatePassword() {
        this.$refs.tmpPassword.validate(valid => {
          if (valid) {
            const user = {}
            user.id = this.user.userId
            user.password = this.tmpPassword.newPassword
            this.update(user)
            this.dialogFormVisible = false
          } else {
            return false
          }
        })
      }
    }
  }
</script>
