<template>
  <div id="app-wrapper">
    <div id="app">
      <router-view/>
    </div>
    <el-footer class="footer">
      <div class="top">Copyright @ WYUOJ</div>
      <div class="bottom">Code by ZhuTianQiang</div>
    </el-footer>
  </div>
</template>

<script>
  export default {
    name: 'app'
  }
</script>

<style type="text/css">
  #app-wrapper {
    position: relative;
    width: 100%;
    height: 100%;
  }

  #app {
    padding-bottom: 60px;
    width: 100%;
    height: 100%;
  }

  .footer {
    margin-top: -60px;
    width: 100%;
    height: 60px;
    text-align: center;
    color: white;
    background-color: #24A3EF;
  }

  .top {
    padding-top: 14px;
  }

</style>
