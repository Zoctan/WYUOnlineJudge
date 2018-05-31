<template>
  <div class="dashboard-container">
    <el-carousel :interval="4000" height="350px">
      <el-carousel-item v-for="item in carousel_images" :key="item">
        <img class="img" :src="item" style="width:100%;">
        <!-- <span class="title">{{ item }}</span> -->
      </el-carousel-item>
    </el-carousel>
    <!-- <div class="dashboard-text">
      <h4>Permission</h4>
      <ol>
        <li v-for="code in permissionCodeList" :key="code">{{ code }}</li>
      </ol>
    </div> -->
    
    
    <el-row>
      <el-col :span="14">
        <div class="title">热门比赛</div>
        <hotContest></hotContest>
        <div class="title">赛程</div>
        <schedule></schedule>
      </el-col>
      <el-col :span="9" class="right">
        <div class="title">最新资讯</div>
        <newMessage></newMessage>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import hotContest from "./components/hotContest";
import newMessage from "./components/newMessage";
import schedule from "./components/schedule";

export default {
  name: "dashboard",
  data() {
    return {
      carousel_images: [
        "http://static.lanqiao.cn/dasai/images/201708/index/banner/20180525_1.jpg",
        "http://static.lanqiao.cn/dasai/images/201708/index/banner/20180525_2.jpg",
        "http://static.lanqiao.cn/dasai/images/201708/index/banner/20180525_3.jpg",
        "http://static.lanqiao.cn/dasai/images/201708/index/banner/20180525_4.jpg"
      ]
    };
  },
  computed: {
    ...mapGetters(["permissionCodeList"]),
  },
  components:{
    hotContest,newMessage,schedule
  }
};
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
  width: 1000px;
  line-height: 1.5;
  position: absolute;
  bottom: -2px;
  padding-left: 15px;
  margin-bottom: 12px;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}
.title{
  font-size: 22px;
  font-weight: 600;
  margin: 30px 0 15px 0;
}
.right{
  margin-left: 30px;
}

</style>
