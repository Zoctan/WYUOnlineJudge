<template>
  <div class="app-container">
    <el-row :gutter="20">
      <el-col :span="10">
        <el-card class="box-card">
          <el-form>
            <el-form-item>
              <el-input type="textarea"
                        v-model="content"
                        :autosize="{ minRows: 5, maxRows: 50}"
                        placeholder="请输入消息"/>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" icon="el-icon-message" style="width: 100%"
                         @click="sendMessage">发送
              </el-button>
            </el-form-item>
          </el-form>
        </el-card>
      </el-col>
      <el-col :span="14">
        <el-card class="box-card">
          <el-input type="textarea"
                    v-model="message"
                    :autosize="{ minRows: 10, maxRows: 25}"/>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  import { mapGetters } from 'vuex'

  export default {
    data() {
      return {
        ws: null,
        wsurl: (process.env.BASE_API + '/websocket').replace('https', 'wss'),
        message: null,
        content: null
      }
    },
    created() {
      if (typeof WebSocket !== 'undefined') {
        this.initWebSocket()
      } else {
        this.Tip.defaultError('浏览器不支持WebSocket')
      }
    },
    computed: {
      ...mapGetters([
        'username'
      ])
    },
    methods: {
      initWebSocket() {
        this.ws = new WebSocket(this.wsurl)
        this.ws.onopen = () => {
          // Web Socket 已连接上，使用 send() 方法发送数据
          /*
          console.log('数据发送中...')
          this.ws.send('open')
          console.log('数据发送完成')
          */
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
        // console.log('数据已接收: ' + e.data)
        if (this.message === null) {
          this.message = e.data
        } else {
          this.message += '\n' + e.data
        }
      },
      sendMessage() {
        if (this.content !== null) {
          this.ws.send(this.username + '：' + this.content)
        } else {
          this.Tip.defaultError('空消息')
        }
      }
    }
  }
</script>
