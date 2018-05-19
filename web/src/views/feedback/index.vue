<template>
  <div class="app-container">
    <el-row :gutter="20">
      <el-col :span="17">
        <el-card class="box-card">
          <el-form label-width="115px"
                   :model="feedbackContent"
                   :rules="sendFeedbackRules"
                   ref="feedbackContent">
            <el-form-item label="邮箱"
                          prop="email">
              <el-input v-model="feedbackContent.email" />
            </el-form-item>
            <el-form-item label="反馈内容"
                          prop="content">
              <el-input type="textarea" v-model="feedbackContent.content" />
            </el-form-item>
            <el-button type="success"
                       :loading="btnLoading"
                       @click="sendFeedback">发送</el-button>
          </el-form>
        </el-card>
      </el-col>
      <el-col :span="7">
        <el-card class="box-card"
                 v-model="helpList"
                 v-loading.body="loading">
          <div slot="header" class="card-clearfix">
            <span><svg-icon icon-class="record" /> 常见帮助</span>
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
  import { isValidateEmail, isValidateString } from '@/utils/validate'
  import EditorSlideUpload from '../../components/Tinymce/components/editorImage'

  export default {
    components: { EditorSlideUpload },
    created() {
      this.getHelpList()
    },
    data() {
      const validateEmail = (rule, value, callback) => {
        if (!isValidateEmail(value)) {
          callback(new Error('邮箱格式不正确'))
        } else {
          callback()
        }
      }
      const validateContent = (rule, value, callback) => {
        if (!isValidateString(value)) {
          callback(new Error('反馈内容不能为空'))
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
        sendFeedbackRules: {
          email: [{ required: true, trigger: 'blur', validator: validateEmail }],
          content: [{ required: true, trigger: 'blur', validator: validateContent }]
        },
        feedbackContent: {
          email: '',
          content: ''
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
        this.btnLoading = true
        feedback(this.feedbackContent).then(() => {
          this.Tip.success('反馈成功')
          this.btnLoading = false
        })
      }
    }
  }
</script>
