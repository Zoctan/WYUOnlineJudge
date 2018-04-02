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
          <button @click="setCodeEditorTheme">xx</button>
          <div class="editor-container">
            <el-dropdown split-button type="primary">
              默认尺寸
              <el-dropdown-menu slot="dropdown" >
                <el-dropdown-item>黄金糕</el-dropdown-item>
                <el-dropdown-item>狮子头</el-dropdown-item>
                <el-dropdown-item>螺蛳粉</el-dropdown-item>
                <el-dropdown-item>双皮奶</el-dropdown-item>
                <el-dropdown-item>蚵仔煎</el-dropdown-item>
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
      setCodeEditorTheme() {
        this.$refs.codeEditor.setTheme('material')
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
