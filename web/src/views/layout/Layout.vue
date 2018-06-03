<template>
  <div class="app-wrapper" :class="{ hideSidebar:!sidebar.opened }">
    <div class="main-container">
      <navbar/>
      <app-main/>
    </div>
  </div>
</template>

<script>
  import { Navbar, AppMain } from './components'
  import App from '../../App'

  export default {
    name: 'layout',
    components: {
      App,
      Navbar,
      AppMain
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
