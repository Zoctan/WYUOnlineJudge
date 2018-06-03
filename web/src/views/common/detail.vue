<template>
  <div class="app-container">
    <el-card class="box-card"
              v-loading.body="loading">
      <div v-loading.body="loading">
        <div class="title">{{detail.title}}</div>
        <!-- 收藏控件 -->
        <el-dialog title="收藏题目" width="35%" center
                 :visible.sync="dialogFormVisible">
          <el-table :data="favoriteList" height="250">
            <el-table-column prop="title" label="收藏夹" align="center"/>
            <el-table-column prop="id" label="操作" align="center">
              <template slot-scope="scope">
                <el-switch active-text="收藏" active-color="#13ce66"
                          inactive-text="移除" inactive-color="#ff4949"
                          :disabled="switchDisabled"
                          @change="handleFavoriteSwitch(scope.row.id, favoriteSwitch[scope.row.id])"
                          v-model="favoriteSwitch[scope.row.id]"/>
              </template>
            </el-table-column>
          </el-table>
          <div slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible = false">完成</el-button>
          </div>
        </el-dialog>
        <div class="date">{{unix2CurrentTime(detail.createTime)}}<span class="user">{{detail.username}}</span></div>
        <div class="description">
          <div class="main" v-html="detail.description"></div> 
        </div>
      </div>
    </el-card>
  </div>
</template>

<script>
import { unix2CurrentTime } from '@/utils'
import { detail as getNoteDetail } from '@/api/note'
import { detail as getCarouselDetail } from '@/api/carousel'
export default {
  created() {
    if(this.type === 'note'){
      this.noteDetail()      
    }
    else if(this.type === 'Carousel'){
      this.carouselDetail()
    }
  },
  data() {
    return {
      loading: false,
      dialogFormVisible: false,
      favoriteList: null,
      type: this.$route.params.type,
      detail:{
        id: this.$route.params.id,
      }
    }
  },
  methods: {
    unix2CurrentTime,
    noteDetail(){
      this.loading = true
        getNoteDetail(this.detail.id).then(response => {
          this.detail = response.data
          console.log(this.detail)
          this.loading = false
      })
    },
    carouselDetail(){
      this.loading = true
        getCarouselDetail(this.detail.id).then(response => {
          this.detail = response.data
          console.log(this.detail)
          this.loading = false
      })
    }
  }
}
</script>
<style lang="scss" scoped>
.title{
  padding-bottom: 15px;
  font-size: 30px;
  font-weight: 600;
}
.date{
  font-size: 15px;
  color: rgb(119, 119, 119);
}
.user{
  padding-left: 20px;
  font-size: 13px;
  color: rgb(119, 119, 119);
}
.description{
  .main{
    font-size: 15px;
    padding: 20px;
    p{
      text-indent: 30px;
    }
  }
}
.box-card{
  position: relative;
}
.el-dialog{
  position: absolute;
  right: 30px;
  top: 30px;
}
</style>

