<template>
  <div class="dashboard-container">

    <el-carousel :interval="4000" type="card" height="300px">
      <el-carousel-item v-for="item in 4" :key="item">
        <span class="title">{{ item }}</span>
      </el-carousel-item>
    </el-carousel>

    <div class="dashboard-text">
      <h4>Permission</h4>
      <ol>
        <li v-for="code in permissionCodeList" :key="code">{{ code }}</li>
      </ol>
    </div>
  </div>
</template>

<script>
  import { mapGetters } from 'vuex'
  export default {
    name: 'dashboard',
    computed: {
      ...mapGetters([
        'permissionCodeList'
      ])
    },
    data() {
      return {
        message: '',
        response: {},
        ws: null,
        wsurl: 'ws:127.0.0.1:9090/websocket'
      }
    },
    created() {
      this.initWebSocket()
    },
    methods: {
      initWebSocket() {
        this.ws = new WebSocket(this.wsurl)
        this.ws.onopen = () => {
          // Web Socket 已连接上，使用 send() 方法发送数据
          console.log('数据发送中...')
          this.sendMessage('open')
          console.log('数据发送完成')
        }
        this.ws.onmessage = e => this.getMessage(e)
        this.ws.onclose = () => {
          // 关闭 websocket
          console.log('连接已关闭...')
        }
        // 路由跳转时结束websocket链接
        this.$router.afterEach(() => {
          this.ws.close()
        })
      },
      getMessage(e) {
        console.log('数据已接收: ' + e.data)
        // this.response = JSON.parse(e.data)
      },
      sendMessage(content) {
        this.ws.send(content)
      }
    }
  }
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
  .dashboard {
    &-container {
      margin: 30px;
    }
    &-text {
      font-size: 30px;
      line-height: 46px;
    }
  }
  .el-carousel__item .title {
    color: #ffffff;
    background-color: black;
    font-size: 14px;
    opacity: 0.55;
    width: 100%;
    line-height: 1.5;
    position: absolute;
    bottom: -2px;
    padding-left: 15px;
    margin-bottom: 12px;
  }

  .el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
  }

  .el-carousel__item:nth-child(2n+1) {
    background-color: #d3dce6;
  }
</style>
