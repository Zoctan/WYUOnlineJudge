<template>
  <div class="app-container">
    <div :data="contest"
         v-loading.body="loading">
      <el-row :gutter="20">
        <el-col :span="8">
          <el-card class="box-card">
            <div slot="header" class="card-clearfix">
              <span>{{ contest.title }}</span>
            </div>
            <p>允许报名：
              <span v-if="contest.permitted">是</span>
              <span v-else>否</span>
            </p>
            <p>报名人数：{{ contest.joinCount }}</p>
            <p>比赛时长：{{ unixDifference(contest.startTime, contest.endTime) }}</p>
            <p>开始时间：{{ unix2CurrentTime(contest.startTime) }}</p>
            <p>结束时间：{{ unix2CurrentTime(contest.endTime) }}</p>
            <p v-if="contest.status === 0">距开始还有：{{ countDown }}</p>
            <p v-else>比赛状态：
              <span v-if="contest.status === 1">已开始</span>
              <span v-else>已结束</span>
            </p>
            <p v-if="contest.permitted">
              <span v-if="contest.status === 0">
                <el-button type="primary" round style="width: 100%"
                           :loading="btnLoading"
                           v-show="!contest.join"
                           @click="dialogFormVisible = true">报名</el-button>
                <el-button type="info" round style="width: 100%"
                           :loading="btnLoading"
                           v-show="contest.join"
                           @click="outContest">取消报名</el-button>
              </span>
              <span v-else>
                <el-button type="info" round style="width: 100%" diabled
                           @click="outContest">截止报名</el-button>
            </span>
            </p>
          </el-card>
        </el-col>
        <el-col :span="16">
          <el-card class="box-card">
            <div class="html-description" v-html="contest.description" v-if="contest.description"></div>
          </el-card>
        </el-col>
      </el-row>

      <!-- 比赛开始并且参加 || 比赛结束并且设置了赛后可看 -->
      <div v-if="(contest.status === 1 && contest.join)
                  || (contest.status === 2 && contest.afterShow)">
        <el-card class="box-card" style="margin-top: 16px">
          <div slot="header" class="clearfix">
            <span>题目</span>
          </div>
            <el-collapse v-model="activeProblemItem">
              <el-collapse-item v-for="(problem, index) in problemList" :name="index" :key="problem.id">
                <template slot="title">
                  <el-row style="font-size: 15px">
                    <el-col :span="1">
                      <i v-if="problem.currentUserSubmitStatus" class="header-icon el-icon-check" style="color: green"></i>
                      <i v-else class="header-icon el-icon-close" style="color: red"></i>
                    </el-col>
                    <el-col :span="17">
                      <span>{{ problem.title }}</span>
                    </el-col>
                    <el-col :span="2">
                      <span>提交数：{{ problem.submitted }}</span>
                    </el-col>
                    <el-col :span="2">
                      <span>通过数：{{ problem.accepted }}</span>
                    </el-col>
                  </el-row>
                </template>
                <div class="html-description" v-html="problem.description" v-if="problem.description" style="font-size: 15px"></div>

                <el-row style="margin-bottom: 12px">
                  <el-col :span="12">
                    <el-select v-model="language" filterable placeholder="请选择代码语言">
                      <el-option
                        v-for="item in languageOption"
                        :key="item"
                        :label="item"
                        :value="item">
                      </el-option>
                    </el-select>
                  </el-col>
                  <el-col :span="12">
                    <el-button type="primary" style="width: 40%"
                               :loading="btnLoading"
                               @click.native.prevent="submitCode(problem.id)">提交</el-button>
                  </el-col>
                </el-row>
                <el-input type="textarea"
                          v-model="code"
                          :autosize="{ minRows: 4, maxRows: 24}"
                          placeholder="请输入代码" />
              </el-collapse-item>
            </el-collapse>
        </el-card>
      </div>

    </div>

    <el-dialog title="加入比赛？"
               :visible.sync="dialogFormVisible">
      <div v-if="contest.password">
        <el-form :model="passwordForm"
                 :rules="passwordFormRules"
                 ref="passwordForm"
                 status-icon>
          <el-form-item prop="password">
            <el-input
              placeholder="请输入密码"
              prefix-icon="el-icon-tickets"
              v-model="passwordForm.password" />
          </el-form-item>
        </el-form>
      </div>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button type="primary"
                   :disabled="btnDisabled"
                   :loading="btnLoading"
                   @click.native.prevent="joinContest">确定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import { mapGetters } from 'vuex'
  import { detail as getContestDetail, join as joinContest, out as outContest, validatePassword as validateContestPassword, contestProblem as getContestProblem } from '@/api/contest'
  import { unix2CurrentTime, unixDifference, nowDifference } from '@/utils'
  import { Base64 } from 'js-base64'
  import { submit as submitCode } from '@/api/code'

  export default {
    created() {
      this.getContestDetail()
      this.setCountDown()
    },
    data() {
      const validatePassword = (rule, value, callback) => {
        if (value === null) {
          this.btnDisabled = true
          callback(new Error('密码不能为空'))
          return
        }
        this.validateContestPassword(value).then(validate => {
          if (!validate) {
            this.btnDisabled = true
            callback(new Error('密码错误'))
          } else {
            this.btnDisabled = false
            callback()
          }
        })
      }
      return {
        loading: false,
        contest: { // 比赛
          id: this.$route.params.id,
          title: null,
          description: null,
          startTime: null,
          endTime: null,
          permitted: true,
          status: null,
          password: null,
          afterShow: false,
          joinCount: null,
          join: false
        },
        countDown: null,
        btnLoading: false,
        dialogFormVisible: false,
        btnDisabled: true,
        passwordForm: {
          id: this.$route.params.id,
          password: null
        },
        passwordFormRules: {
          password: [{ required: true, trigger: 'change', validator: validatePassword }]
        },
        problemList: [],
        activeProblemItem: null,
        code: null,
        language: 'C',
        languageOption: ['C', 'C++', 'C#', 'Java', 'JavaScript', 'PHP', 'Python3', 'Ruby', 'Go']
      }
    },
    computed: {
      ...mapGetters([
        'userId'
      ])
    },
    methods: {
      unix2CurrentTime,
      unixDifference,
      validateContestPassword(password) {
        this.passwordForm.password = password
        return validateContestPassword(this.passwordForm).then(response => response.data)
      },
      setCountDown() {
        setInterval(() => { this.countDown = nowDifference(this.contest.endTime) }, 1000)
      },
      getContestDetail() {
        this.loading = true
        getContestDetail(this.contest.id).then(response => {
          this.contest = response.data
          if (this.contest.status === 0) {
            this.loading = false
            return
          }
          getContestProblem(this.contest.id).then(response => {
            this.problemList = response.data.list
            this.loading = false
          })
        })
      },
      joinContest() {
        this.btnLoading = true
        if (this.contest.password) {
          this.$refs.passwordForm.validate(valid => {
            if (valid) {
              joinContest(this.contest.id).then(() => {
                this.Tip.defaultSuccess('报名成功')
                this.contest.join = true
                this.dialogFormVisible = false
                this.btnLoading = false
              })
            }
          })
        }
      },
      outContest() {
        this.btnLoading = true
        outContest(this.contest.id).then(() => {
          this.Tip.defaultSuccess('取消报名成功')
          this.contest.join = false
          this.btnLoading = false
        })
      },
      submitCode(problemId) {
        if (this.code === null) {
          this.Tip.defaultError('代码为空！')
          return
        }
        this.btnLoading = true
        const codeForm = {
          contestId: this.contest.id,
          problemId: problemId,
          userId: this.userId,
          code: Base64.encode(this.code),
          language: this.language
        }
        submitCode(codeForm).then(() => {
          this.Tip.defaultSuccess('提交成功')
          this.btnLoading = false
        })
      }
    }
  }
</script>
