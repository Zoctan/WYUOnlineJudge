<template>
  <div class="app-container">
    <div v-loading.body="loading">
      <div>标题</div>
      <div>用户</div>
      <div>日期</div>
      <div>内容</div>
    </div>
  </div>
</template>

<script>
  import { unix2CurrentTime } from '@/utils'

  export default {
    created() {

    },
    data() {
      return {
        loading: false

      }
    },
    methods: {}
  }
</script>
