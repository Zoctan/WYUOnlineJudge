<template>
  <div class="clock">
    <span class="date">{{ date }}</span>
    <span class="time">{{ time }}</span>
  </div>
</template>

<script>
  export default {
    name: 'clock',
    data() {
      return {
        time: '',
        date: ''
      }
    },
    created() {
      const self = this
      setInterval(() => self.updateTime(), 1000)
    },
    methods: {
      updateTime() {
        const cd = new Date()
        this.time = this.zeroPadding(cd.getHours(), 2) + ':' + this.zeroPadding(cd.getMinutes(), 2) + ':' + this.zeroPadding(cd.getSeconds(), 2)
        this.date = this.zeroPadding(cd.getFullYear(), 4) + '-' + this.zeroPadding(cd.getMonth() + 1, 2) + '-' + this.zeroPadding(cd.getDate(), 2)
      },
      zeroPadding(num, digit) {
        let zero = ''
        for (let i = 0; i < digit; i++) {
          zero += '0'
        }
        return (zero + num).slice(-digit)
      }
    }
  }
</script>

<style lang="scss" scoped>
  .clock {
    .date {
      font-size: 18px;
    }
    .time {
      font-size: 15px;
    }
  }
</style>
