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
