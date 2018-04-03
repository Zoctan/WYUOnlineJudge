<template>
  <div class="app-container">
    <div :data="problem">
      <h3 v-text="problem.id + '.' + problem.title"></h3>
      <el-tabs type="card" v-loading.body="loading"
               element-loading-text="loading">
        <el-tab-pane>
          <span slot="label"><svg-icon icon-class="documentation" /> 题目描述</span>
          {{ problem.description }}

          <hr/>
          <div class="editor-container">
            <el-dropdown split-button type="primary" @command="setCodeEditorTheme">
              主题
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item v-for="theme in codeMirrorTheme" v-text="theme" :command="theme" :key="theme" />
              </el-dropdown-menu>
            </el-dropdown>
            <code-editor ref="codeEditor" v-model="code" />
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
        codeMirrorTheme: ['eclipse', 'material', 'ambiance'],
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
      },
      setCodeEditorTheme(theme) {
        this.$refs.codeEditor.setTheme(theme)
      }
    }
  }
</script>

<style scoped>
  .editor-container{
    position: relative;
    height: 100%;
  }
</style>
