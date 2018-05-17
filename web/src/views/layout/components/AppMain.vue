<template>
  <section class="app-main">
    <transition name="fade" mode="out-in">
      <router-view :key="key" />
    </transition>
    <el-footer class="footer">
      <div class="top">Copyright @ WYUOJ</div>
      <div class="bottom">Code by ZhuTianQiang</div>
    </el-footer>
  </section>
</template>

<script>
  export default {
    name: 'AppMain',
    computed: {
      key() {
        return this.$route.name !== undefined ? this.$route.name + +new Date() : this.$route + +new Date()
      }
    }
  }
</script>
