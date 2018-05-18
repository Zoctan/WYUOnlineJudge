<template>
  <div class="app-container">
    <div :data="contest">
      <el-row :gutter="20">
        <el-col :span="8">
          <el-card class="box-card">
            <div slot="header" class="clearfix">
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
            <p v-if="contest.status === 0">
              <el-button type="primary" round style="width: 100%"
                         :loading="btnLoading"
                         v-if="!contest.join"
                         @click="dialogFormVisible = true">报名</el-button>
              <el-button type="info" round style="width: 100%"
                         :loading="btnLoading"
                         v-if="contest.join"
                         @click="outContest">取消报名</el-button>
            </p>
          </el-card>
        </el-col>
        <el-col :span="16">
          <el-card class="box-card">
            <div class="html-description" v-html="contest.description" v-if="contest.description"></div>
          </el-card>
        </el-col>
      </el-row>
    </div>

    <el-dialog title="加入比赛？"
               :visible.sync="dialogFormVisible">
      <div v-if="contest.password">

      </div>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button type="primary"
                   :loading="btnLoading"
                   @click.native.prevent="joinContest">确定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import { detail as getContestDetail, join as joinContest, out as outContest } from '@/api/contest'
  import { unix2CurrentTime, unixDifference, nowDifference } from '@/utils'

  export default {
    created() {
      this.getContestDetail()
      this.setCountDown()
    },
    data() {
      return {
        loading: false,
        contest: { // 比赛
          id: this.$route.params.id,
          title: null,
          description: null,
          startTime: null,
          endTime: null,
          permitted: null,
          status: null,
          password: null,
          joinCount: null,
          join: false
        },
        countDown: null,
        btnLoading: false, // 按钮等待动画
        dialogFormVisible: false,
        password: null
      }
    },
    methods: {
      unix2CurrentTime,
      unixDifference,
      setCountDown() {
        setInterval(() => { this.countDown = nowDifference(this.contest.endTime) }, 1000)
      },
      getContestDetail() {
        this.loading = true
        getContestDetail(this.contest.id).then(response => {
          this.contest = response.data
          this.loading = false
        })
      },
      joinContest() {
        this.btnLoading = true
        joinContest(this.contest.id).then(() => {
          this.$message.success('报名成功')
          this.contest.join = false
          this.dialogFormVisible = false
          this.btnLoading = false
        })
      },
      outContest() {
        this.btnLoading = true
        outContest(this.contest.id).then(() => {
          this.$message.success('取消报名成功')
          this.contest.join = true
          this.btnLoading = false
        })
      }
    }
  }
</script>

<style lang="scss" scoped>
  .clearfix:before,
  .clearfix:after {
    display: table;
    content: '';
  }
  .clearfix:after {
    clear: both
  }
</style>
