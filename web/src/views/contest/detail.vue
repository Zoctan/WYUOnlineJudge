<template>
  <div class="app-container">
    <div :data="problem">
      <h2>{{ problem.id + '.' + problem.title }}</h2>
    </div>
  </div>
</template>

<script>
  import { mapGetters } from 'vuex'
  import { Base64 } from 'js-base64'
  import { run as runCode, submit as submitCode, listSubmitCode } from '@/api/code'
  import { list as listUserFavorite, add as addFavorite, addProblem as addProblem2Favorite, removeProblem as removeProblemFromFavorite } from '@/api/favorite'
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
        problem: { // 题目
          id: this.$route.params.id,
          title: null,
          level: null,
          tags: null,
          accepted: null,
          submitted: null,
          description: null
        },
        code: '', // 代码编辑框内容
        submitCodeList: null, // 提交记录列表
        totalSubmitCode: 0, // 提交记录数
        listLoading: false, // 列表加载
        submitCodeListQuery: {
          page: 1, // 页码
          size: 30 // 每页数量
        },
        dialogFormVisible: false, // 显示隐藏收藏夹对话框
        favoriteList: null, // 用户收藏夹
        totalFavorite: 0, // 用户收藏夹数
        favoriteSwitch: [], // 各个收藏夹是否收藏了该题目
        switchDisabled: false, // 其他收藏夹收藏时，禁止切换
        isPrivateOptions: [{ label: '私有', value: true }, { label: '公开', value: false }], // 新收藏夹是否对外公开
        newFavorite: { // 新收藏夹
          title: null,
          userId: null,
          isPrivate: true
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
      listUserFavorite() {
        listUserFavorite().then(response => {
          this.favoriteList = response.data.list
          this.totalFavorite = response.data.total
          this.setFavoriteSwitch()
        })
      },
      setFavoriteSwitch() {
        this.favoriteList.forEach(favorite => {
          this.favoriteSwitch[favorite.id] = favorite.problemIdList.indexOf(this.problem.id) !== -1
        })
        this.favoriteSwitch.forEach((x,index) => console.info(index+'->'+x))
      },
      handleFavoriteSwitch(favoriteId, status) {
        this.switchDisabled = true
        const form = {
          favoriteId: favoriteId,
          problemId: this.problem.id
        }
        if (status) {
          addProblem2Favorite(form).then(() => {
            this.$message.success('收藏成功')
            this.switchDisabled = false
          })
        } else {
          removeProblemFromFavorite(form).then(() => {
            this.$message.success('已取消收藏')
            this.switchDisabled = false
          })
        }
      },
      addFavorite() {
        this.btnLoading = true
        this.newFavorite.userId = this.userId
        const isTitleExist = this.favoriteList.filter(favorite => favorite.title === this.newFavorite.title).length === 1
        if (isTitleExist) {
          this.$message.error('收藏夹名称重复')
          this.btnLoading = false
          return
        }
        addFavorite(this.newFavorite).then(() => {
          this.listUserFavorite()
          this.$message.success('成功添加收藏夹')
          this.btnLoading = false
        })
      },
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
          this.totalSubmitCode = response.data.total
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
      handleFavoriteCurrentChange(page) {
        // 改变页码
        this.favoriteListQuery.page = page
        this.listUserFavorite()
      },
      handleSubmitCodeSizeChange(size) {
        // 改变每页数量
        this.submitCodeListQuery.size = size
        this.handleSubmitCodeFilter()
      },
      handleSubmitCodeCurrentChange(page) {
        // 改变页码
        this.submitCodeListQuery.page = page
        this.listSubmitCode()
      },
      handleSubmitCodeFilter() {
        // 查询事件
        this.submitCodeListQuery.page = 1
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
