<template>
  <div class="app-container">
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
  </div>
</template>

<script>
  import { feedback } from '@/api/feedback'
  import { isValidateEmail, isValidateString } from '@/utils/validate'
  import EditorSlideUpload from '../../components/Tinymce/components/editorImage'

  export default {
    components: {EditorSlideUpload},
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
