<template>
  <div class="app-container">
    <el-row :gutter="20">
      <el-col :span="17">
        <el-card class="box-card">
          <div class="title">欢迎反馈问题，您的意见与建议就是我们的动力！</div>
          <div class="tips">我们会认真查阅您反馈的每一个问题，并尽快给您答复，在这里您可以提出遇到的问题，也可以发表自己的建议和想法。</div>
          <el-form label-width="60px"
                   :model="feedback"
                   :rules="feedbackRules"
                   ref="feedback">
            <el-form-item label="邮箱"
                          prop="email">
              <el-input v-model="feedback.email"/>
            </el-form-item>
            <el-form-item label="反馈"
                          prop="content">
              <markdown-editor id="contentEditor"
                               ref="contentEditor"
                               v-model="markdownContent"
                               :height="300"></markdown-editor>
            </el-form-item>
            <div class="button">
              <el-button type="success"
                         :loading="btnLoading"
                         @click="sendFeedback">发送
              </el-button>
            </div>
          </el-form>
        </el-card>
      </el-col>
      <el-col :span="7">
        <el-card class="box-card"
                 v-model="helpList"
                 v-loading.body="loading">
          <div slot="header" class="card-clearfix">
            <span><svg-icon icon-class="record"/> 常见帮助</span>
          </div>
          <div v-for="help in helpList" class="hover card-row" @click="seeHelp(help)">{{ help.title }}</div>
        </el-card>
      </el-col>
    </el-row>
    <el-dialog :title="help.title"
               :visible.sync="dialogFormVisible">
      <div class="html-description" v-html="help.description" v-if="help.description"></div>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">关闭</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import { feedback } from '@/api/feedback'
  import { list as getHelpList } from '@/api/help'
  import { validateEmail as correctEmail } from '@/utils/validate'
  import MarkdownEditor from '@/components/MarkdownEditor'

  export default {
    components: { MarkdownEditor },
    created() {
      this.getHelpList()
    },
    data() {
      const validateContent = (rule, value, callback) => {
        if (value === null) {
          callback(new Error('反馈内容为空'))
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
        helpList: [],
        dialogFormVisible: false,
        help: {
          title: null,
          description: null
        },
        feedbackRules: {
          email: [{ required: true, trigger: 'blur', validator: validateEmail }],
          markdownContent: [{ required: true, trigger: 'blur', validator: validateContent }]
        },
        feedback: {
          email: null,
          content: null,
          markdownContent: null
        }
      }
    },
    methods: {
      getHelpList() {
        getHelpList().then(response => {
          this.helpList = response.data.list
        })
      },
      seeHelp(help) {
        this.help = help
        this.dialogFormVisible = true
      },
      sendFeedback() {
        this.$refs.feedback.validate(valid => {
          if (valid) {
            if (this.markdownContent === null) {
              this.Tip.error('反馈内容为空')
            }
            this.btnLoading = true
            import('showdown').then(showdown => {
              const converter = new showdown.Converter()
              this.feedback.content = converter.makeHtml(this.markdownContent)
              feedback(this.feedback).then(() => {
                this.Tip.success('反馈成功')
                this.btnLoading = false
              })
            })
          }
        })
      }
    }
  }
</script>
<style lang="scss" scoped>
  .title {
    text-align: center;
    font-size: 25px;
    font-weight: 600;
    padding-bottom: 10px;
  }

  .tips {
    font-size: 14px;
    padding-bottom: 20px;
    color: #707070;
  }

  .button {
    text-align: right;
  }

</style>
