<template>
  <div class="app-wrapper" :class="{ hideSidebar:!sidebar.opened }">
    <sidebar class="sidebar-container" />
    <div class="main-container">
      <navbar />
      <app-main />

    </div>
  </div>
</template>

<script>
  import { Navbar, Sidebar, AppMain, AppFooter } from './components'
  import App from '../../App'

  export default {
    name: 'layout',
    components: {
      App,
      Navbar,
      Sidebar,
      AppMain,
      AppFooter
    },
    computed: {
      sidebar() {
        return this.$store.state.app.sidebar
      }
    }
  }
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
  @import "src/styles/mixin.scss";
  .app-wrapper {
    @include clearfix;
    position: relative;
    height: 100%;
    width: 100%;
  }
</style>
