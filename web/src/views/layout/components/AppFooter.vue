<template>
  <section class="app-footer">
    <transition name="fade" mode="out-in">
      <el-footer>
        <div class="top">Copyright @ WYUOJ</div>
        <div class="bottom">Code by ZhuTianQiang</div>
      </el-footer>
    </transition>
  </section>
</template>

<script>
  export default {
    name: 'AppFooter'
  }
</script>

<style lang="scss" scoped>
  .top {
    padding-top: 12px;
  }
  .bottom {
    padding-bottom: 12px;
  }
</style>
