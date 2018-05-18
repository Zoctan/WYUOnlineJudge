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
              <span v-if="contest.permitted === 1">是</span>
              <span v-else>否</span>
            </p>
            <p>开始时间：{{ unix2CurrentTime(contest.startTime) }}</p>
            <p>结束时间：{{ unix2CurrentTime(contest.endTime) }}</p>
            <p>比赛时长：{{ unixDifference(contest.startTime, contest.endTime) }}</p>
            <p>比赛密码：
              <span v-if="contest.password">有</span>
              <span v-else>无</span>
            </p>
            <p>比赛状态：
              <span v-if="contest.status === 0">未开始</span>
              <span v-else-if="contest.status === 1">已开始</span>
              <span v-else>已结束</span>
            </p>
          </el-card>
        </el-col>
        <el-col :span="16">
          <el-card class="box-card">
            <div class="html-description" v-html="contest.description" v-if="contest.description"></div>
          </el-card>
        </el-col>
      </el-row>

      <el-dialog title="加入比赛？"
                 :visible.sync="dialogFormVisible">
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取消</el-button>
          <el-button type="primary"
                     :loading="btnLoading"
                     @click.native.prevent="">确定</el-button>
        </div>
      </el-dialog>
    </div>
  </div>
</template>

<script>
  import { detail as getContestDetail } from '@/api/contest'
  import { unix2CurrentTime, unixDifference } from '@/utils'

  export default {
    created() {
      this.getContestDetail()
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
          password: null
        },
        btnLoading: false, // 按钮等待动画
        dialogFormVisible: false,
        password: null
      }
    },
    methods: {
      unix2CurrentTime,
      unixDifference,
      getContestDetail() {
        this.loading = true
        getContestDetail(this.contest.id).then(response => {
          this.contest = response.data
          this.loading = false
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
