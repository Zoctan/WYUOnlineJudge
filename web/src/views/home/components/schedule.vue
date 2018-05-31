<template>
  <el-card class="box-card">
    <fullcalendar class="fullcalendar" :events="fcEvents" locale="en"></fullcalendar>
  </el-card>
</template>
<script>
  import fullcalendar from 'vue-fullcalendar'

  export default {
    components: {
      fullcalendar
    },
    data() {
      return {
        fcEvents: [
          {
            title: 'event1',
            start: '2018-05-01',
            YOUR_DATA: {}
          },
          {
            title: 'event2',
            start: '2018-05-02',
            end: '2018-05-03',
            YOUR_DATA: {}
          }
        ]
      }
    },
    methods: {}
  }
</script>
<style lang="scss" scoped>
  .full-calendar-body {
    height: 400px;
  }
</style>
