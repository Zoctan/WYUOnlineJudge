<template>
  <section class="app-main">
    <transition name="fade" mode="out-in">
      <router-view :key="key"/>
    </transition>
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
<style lang="scss" scoped>
.app-main{
  margin: auto;
  max-width: 1200px;
}
</style>
