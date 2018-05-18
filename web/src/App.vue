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
    display: flex;
    flex-direction: column;
    width: 100%;
    min-height: 100%;
  }

  #app {
    flex: 1;
  }

  .footer {
    flex: 0 0 60px;
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
