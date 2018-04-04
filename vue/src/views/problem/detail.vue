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
  import { detail as getProblemDetail } from '@/api/problem'
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
        code: 'String a = null;'
      }
    },
    methods: {
      getProblemDetail() {
        // 查询列表
        this.loading = true
        getProblemDetail(this.problem.id).then(response => {
          this.problem = response.data
          this.loading = false
        })
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
  }
</style>
