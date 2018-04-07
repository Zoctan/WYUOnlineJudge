<template>
  <div class="app-container">
    <div :data="problem">
      <h3 v-text="problem.id + '.' + problem.title"></h3>
      <el-tabs type="card" v-loading.body="loading"
               element-loading-text="loading">
        <el-tab-pane>
          <span slot="label"><svg-icon icon-class="documentation" /> 题目描述</span>

          <el-container>
            <el-main>{{ problem.description }}</el-main>
            <el-aside width="250px">
              <ul class="list">
                <li class="list-item"><span>题目难度：</span>
                  <span class="right">
                    <el-tag v-if="problem.level === 1" type="success">简单</el-tag>
                    <el-tag v-else-if="problem.level === 2" type="warning">中等</el-tag>
                    <el-tag v-else type="danger">困难</el-tag>
                  </span>
                </li>

                <li class="list-item"><span>通过次数：</span>
                  <span class="right">
                  {{ problem.submitted }}
                  </span>
                </li>

                <li class="list-item"><span>通过次数：</span>
                  <span class="right">
                    {{ problem.accepted }}
                  </span>
                </li>
              </ul>
            </el-aside>
          </el-container>

          <div class="code-editor">
            <code-editor class="editor" ref="codeEditor" v-model="code" />
            <div class="code-button">
              <el-row :gutter="20">
                <el-col :span="2" :offset="16">
                  <el-button round
                             :loading="btnLoading"
                             @click.native.prevent="handleRunOrSubmit(false)">执行代码</el-button></el-col>
                <el-col :span="2" :offset="2">
                  <el-button type="primary" round
                             :loading="btnLoading"
                             @click.native.prevent="handleRunOrSubmit(true)">提交解答</el-button></el-col>
              </el-row>
            </div>
          </div>
        </el-tab-pane>

        <el-tab-pane>
          <span slot="label" @click="listSubmitCode"><svg-icon icon-class="code" /> 提交记录</span>
            <el-table :data="submitCodeList"
                      v-loading.body="listLoading"
                      element-loading-text="loading"
                      fit>
              <el-table-column label="提交时间"
                               align="center"
                               sortable
                               prop="submitTime">
                <template slot-scope="scope">
                  <i class="el-icon-time"></i>
                  {{ unix2CurrentTime(scope.row.submitTime) }}
                </template>
              </el-table-column>
              <el-table-column label="通过状态"
                               prop="status"
                               align="center">
                <template slot-scope="scope">
                  <el-tag v-if="scope.row.status === 1" type="success">通过</el-tag>
                  <el-tag v-else-if="scope.row.status === 2" type="warning">xx</el-tag>
                  <el-tag v-else type="danger">xxx</el-tag>
                </template>
              </el-table-column>
              <el-table-column label="消耗时间"
                               prop="timeUsed"
                               sortable
                               align="center" />
              <el-table-column label="消耗内存"
                               prop="memoryUsed"
                               sortable
                               align="center" />
              <el-table-column label="语言"
                               prop="language"
                               align="center" />
            </el-table>
            <el-pagination
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              :current-page="listQuery.page"
              :page-size="listQuery.size"
              :total="total"
              :page-sizes="[10, 30, 50, 100]"
              layout="total, sizes, prev, pager, next, jumper">
            </el-pagination>
        </el-tab-pane>

        <el-tab-pane disabled>
          <span slot="label">
            <el-tooltip content="即将到来" placement="top-start">
              <svg-icon icon-class="chat" />
            </el-tooltip> 社区讨论
          </span>
        </el-tab-pane>
      </el-tabs>
    </div>
  </div>
</template>

<script>
  import { mapGetters } from 'vuex'
  import { Base64 } from 'js-base64'
  import { run as runCode, submit as submitCode, listSubmitCode } from '@/api/code'
  import { detail as getProblemDetail } from '@/api/problem'
  import { unix2CurrentTime } from '@/utils'
  import CodeEditor from '@/components/CodeEditor'

  export default {
    components: { CodeEditor },
    created() {
      this.getProblemDetail()
    },
    data() {
      return {
        loading: false,
        btnLoading: false,
        problem: {
          id: this.$route.params.id,
          title: null,
          level: null,
          tags: null,
          accepted: null,
          submitted: null,
          description: null
        },
        code: '',
        submitCodeList: [], // 提交记录列表
        total: 0,
        listLoading: false,
        listQuery: {
          page: 1, // 页码
          size: 30 // 每页数量
        }
      }
    },
    computed: {
      ...mapGetters([
        'userId',
        'codeMirrorLanguage'
      ])
    },
    methods: {
      unix2CurrentTime,
      getProblemDetail() {
        this.loading = true
        getProblemDetail(this.problem.id).then(response => {
          this.problem = response.data
          this.loading = false
        })
      },
      listSubmitCode() {
        // 查询列表
        this.listLoading = true
        listSubmitCode(this.problem.id, this.listQuery).then(response => {
          this.submitCodeList = response.data.list
          this.total = response.data.total
          this.listLoading = false
        })
      },
      handleRunOrSubmit(status) {
        this.btnLoading = true
        const codeForm = {
          problemId: this.problem.id,
          userId: this.userId,
          code: Base64.encode(this.code),
          language: this.codeMirrorLanguage.name
        }
        if (!status) {
          runCode(codeForm).then(() => {
            this.$message.success('run成功')
            this.btnLoading = false
          })
        } else {
          submitCode(codeForm).then(() => {
            this.$message.success('submit成功')
            this.btnLoading = false
          })
        }
      },
      handleSizeChange(size) {
        // 改变每页数量
        this.listQuery.size = size
        this.handleFilter()
      },
      handleCurrentChange(page) {
        // 改变页码
        this.listQuery.page = page
        this.listSubmitCode()
      },
      handleFilter() {
        // 查询事件
        this.listQuery.page = 1
        this.listSubmitCode()
      }
    }
  }
</script>

<style lang="scss" scoped>
  .list {
    border-left-style: solid;
    border-left-color: #F5F5F5;
    border-left-width: 2px;
    border-right-style: solid;
    border-right-color: #F5F5F5;
    border-right-width: 2px;
    box-sizing: border-box;
  }
  .list-item {
    padding: 10px 0;
    list-style-type: none;
    font-size: 14px;
    .right {
      float: right;
      padding-right: 30px;
    }
  }
  .code-editor {
    border-top-style: solid;
    border-top-color: #F5F5F5;
    border-top-width: 1px;
    box-sizing: border-box;
    .code-button {
      padding: 10px 0;
    }
  }
</style>
