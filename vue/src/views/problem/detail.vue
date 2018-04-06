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
          <span slot="label"><svg-icon icon-class="code" /> 提交记录</span>
          即将到来
        </el-tab-pane>

        <el-tab-pane>
          <span slot="label"><svg-icon icon-class="chat" /> 社区讨论</span>
          即将到来
        </el-tab-pane>
      </el-tabs>
    </div>
  </div>
</template>

<script>
  import { Base64 } from 'js-base64'
  import { run as runCode, submit as submitCode } from '@/api/code'
  import { detail as getProblemDetail } from '@/api/problem'
  import { mapGetters } from 'vuex'
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
        code: ''
      }
    },
    computed: {
      ...mapGetters([
        'userId',
        'codeMirrorLanguage'
      ])
    },
    methods: {
      getProblemDetail() {
        // 查询列表
        this.loading = true
        getProblemDetail(this.problem.id).then(response => {
          this.problem = response.data
          this.loading = false
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
