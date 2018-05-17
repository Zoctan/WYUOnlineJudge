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
        <el-card class="box-card">
          <span class="card-title"><svg-icon icon-class="record" /> 常见帮助</span>
          <el-row type="flex" class="card-row" justify="space-between">
            <el-col :span="16"><svg-icon icon-class="right" /> 已解决</el-col>
            <el-col :span="8"><el-tag type="info" size="mini">123/456</el-tag></el-col>
          </el-row>

          <el-row type="flex" class="card-row" justify="space-between">
            <el-col :span="16"><svg-icon icon-class="easy" /> 简单</el-col>
            <el-col :span="8"><el-tag type="success" size="mini">1</el-tag></el-col>
          </el-row>

          <el-row type="flex" class="card-row" justify="space-between">
            <el-col :span="16"><svg-icon icon-class="medium" /> 中等</el-col>
            <el-col :span="8"><el-tag type="warning" size="mini">2</el-tag></el-col>
          </el-row>

          <el-row type="flex" class="card-row" justify="space-between">
            <el-col :span="16"><svg-icon icon-class="hard" /> 困难</el-col>
            <el-col :span="8"><el-tag type="danger" size="mini">3</el-tag></el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  import { feedback } from '@/api/feedback'
  import { isValidateEmail, isValidateString } from '@/utils/validate'
  import EditorSlideUpload from '../../components/Tinymce/components/editorImage'

  export default {
    components: { EditorSlideUpload },
    created() {
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
        btnLoading: false,
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
      sendFeedback() {
        this.btnLoading = true
        feedback(this.feedbackContent).then(() => {
          this.$message.success('反馈成功')
          this.btnLoading = false
        })
      }
    }
  }
</script>
