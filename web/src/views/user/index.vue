<template>
  <div class="app-container">
    <el-row :gutter="20">
      <el-col :span="6">
        <el-card class="box-card">
          <el-form v-loading.body="loading"
                   :model="tmpUser"
                   :rules="updateInfoRules"
                   ref="tmpUser">
            <div>
              <img width="170" height="170" :src="user.avatar"/>
            </div>
            <div v-if="token === null">
              <span>{{ user.username }}</span>
            </div>
            <div v-else>
              <el-form-item label="用户名"
                            prop="username">
                <div v-if="!revise" class="tmpUser_message">{{tmpUser.username}}</div>
                <el-input v-else v-model="tmpUser.username"/>
              </el-form-item>


              <el-form-item label="邮箱"
                            prop="email">
                <div v-if="!revise" class="tmpUser_message">{{tmpUser.email}}</div>
                <el-input v-else v-model="tmpUser.email"/>
              </el-form-item>

              <el-form-item label="注册时间">
                <div v-if="!revise" class="tmpUser_message">{{user.registerTime}}</div>
                <el-input v-else :value="unix2CurrentTime(user.registerTime)" readonly="readonly"/>
              </el-form-item>

              <el-form-item label="最后登陆时间">
                <div v-if="!revise" class="tmpUser_message">{{user.lastLoginTime}}</div>
                <el-input v-else :value="unix2CurrentTime(user.lastLoginTime)" readonly="readonly"/>
              </el-form-item>

              <el-form-item label="简介"
                            prop="resume">
                <div v-if="!revise" class="tmpUser_message">{{tmpUser.resume}}</div>
                <el-input v-else type="textarea"
                          :autosize="{ minRows: 3, maxRows: 6}"
                          v-model="tmpUser.resume"/>
              </el-form-item>

              <el-button type="success"
                         :loading="btnLoading"
                         @click="regainUserInfo">重新获取信息
              </el-button>

              <el-button v-if="!revise" type="primary"
                         :loading="btnLoading"
                         @click="changeRevise">修改信息
              </el-button>

              <el-button v-else type="primary"
                         :loading="btnLoading"
                         @click="updateInfo">确认修改
              </el-button>

                <el-button type="danger"
                           @click="showUpdatePassword">修改密码
                </el-button>
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
                          v-model="tmpPassword.oldPassword"/>
              </el-form-item>
              <el-form-item label="新密码"
                            prop="newPassword" required>
                <el-input type="password"
                          prefix-icon="el-icon-edit"
                          auto-complete="off"
                          placeholder="请输入新密码"
                          v-model="tmpPassword.newPassword"/>
              </el-form-item>
              <el-form-item label="新密码"
                            prop="newPassword2" required>
                <el-input type="password"
                          prefix-icon="el-icon-edit"
                          auto-complete="off"
                          placeholder="请再次输入新密码"
                          v-model="tmpPassword.newPassword2"/>
              </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
              <el-button @click="dialogFormVisible = false">取消</el-button>
              <el-button type="danger"
                         @click="resetForm('tmpPassword')">重置
              </el-button>
              <el-button type="primary"
                         :loading="btnLoading"
                         @click.native.prevent="updatePassword">修改
              </el-button>
            </div>
          </el-dialog>
        </el-card>
      </el-col>

      <el-col :span="18">
        <el-card class="box-card">
          <div slot="header" class="card-clearfix">
            我的收藏
          </div>
          <el-tabs type="border-card">
            <el-tab-pane label="题目">
              <el-table :data="problemList"
                        v-loading.body="listLoading"
                        stripe>
                <el-table-column label="#"
                                 align="center"
                                 width="50">
                  <template slot-scope="scope">
                    <span>{{ getIndex(scope.$index) }}</span>
                  </template>
                </el-table-column>
                <el-table-column show-overflow-tooltip
                                 label="题目">
                  <template slot-scope="scope">
                    <router-link v-if="hasPermission('problem:detail')"
                                 :to="{name: '题目详情', params: {id: scope.row.id}}">
                      <span class="hover">{{ scope.row.title }}</span>
                    </router-link>
                    <span class="hover" v-else @click="noLoginTip">{{ scope.row.title }}</span>
                  </template>
                </el-table-column>
              </el-table>
              <el-pagination
                @current-change="handleProblemCurrentChange"
                :current-page="problemListQuery.page"
                :page-size="problemListQuery.size"
                :total="total"
                :pager-count="10"
                layout="prev, pager, next"/>
            </el-tab-pane>
            <el-tab-pane label="文章">

            </el-tab-pane>
          </el-tabs>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  import { update as updateUser, validatePassword } from '@/api/user'
  import { unix2CurrentTime } from '@/utils'
  import { validateEmail as correctEmail } from '@/utils/validate'
  import { setToken } from '@/store/modules/token'
  import { mapGetters, mapState } from 'vuex'
  import { listProblem as listFavoriteProblem, listNote as listFavoriteNote } from '@/api/favorite'

  export default {
    created() {
      this.setInfo()
      this.listFavoriteProblem()
      this.listFavoriteNote()
    },
    data() {
      const validateOldPassword = (rule, value, callback) => {
        if (value.length < 6) {
          callback(new Error('旧密码长度必须大于等于6'))
        }
        // promise异步查询后端密码
        this.validateOldPassword(value).then(validate => {
          if (!validate) {
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
        if (!correctEmail(value)) {
          callback(new Error('邮箱格式不正确'))
        } else {
          callback()
        }
      }
      return {
        loading: false,
        btnLoading: false,
        dialogFormVisible: false,
        revise: false,
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
        },
        problemLoading: false,
        problemList: [],
        totalProblem: 0,
        problemListQuery: {
          page: 1,
          size: 20
        },
        noteLoading: false,
        noteList: [],
        totalNote: 0,
        noteListQuery: {
          page: 1,
          size: 20
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
      unix2CurrentTime,
      setInfo() {
        this.tmpUser.username = this.user.username
        this.tmpUser.email = this.user.email
        this.tmpUser.resume = this.user.resume
      },
      listFavoriteProblem() {
        this.problemLoading = true
        listFavoriteProblem().then(response => {
          this.problemList = response.data.list
          this.totalProblem = response.data.total
          this.problemLoading = false
        })
      },
      listFavoriteNote() {
        this.noteLoading = true
        listFavoriteNote().then(response => {
          this.noteList = response.data.list
          this.totalNote = response.data.total
          this.noteLoading = false
        })
      },
      handleProblemCurrentChange(page) {
        this.problemListQuery.page = page
        this.listFavoriteProblem()
      },
      handleNoteCurrentChange(page) {
        this.noteListQuery.page = page
        this.listFavoriteNote()
      },
      getIndex(index) {
        // 表格序号
        return (this.listQuery.page - 1) * this.listQuery.size + index + 1
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
        this.$store.dispatch('Info').then(() => {
          this.loading = false
          this.btnLoading = false
        })
      },
      resetForm(formName) {
        this.$refs[formName].resetFields()
      },
      update(user) {
        this.btnLoading = true
        updateUser(user).then(response => {
          this.Tip.defaultSuccess('修改成功')
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
        this.revise = !this.revise
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
      },
      changeRevise() {
        this.revise = !this.revise
      }
    }
  }
</script>
<style scoped>
  .tmpUser_message {
    text-align: left;
    color: #707070;
  }

  .el-form > div {
    margin: auto;
  }

  .el-button {
    display: block;
    text-align: left;
    margin: 5px auto;
  }
</style>
