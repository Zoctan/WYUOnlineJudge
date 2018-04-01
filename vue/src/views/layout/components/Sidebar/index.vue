<template>
  <scroll-bar>
  <!-- http://element-cn.eleme.io/#/zh-CN/component/menu -->
  <el-menu :default-active="$route.path" mode="vertical" :collapse="isCollapse"
           background-color="#304156"
           text-color="#bfcbd9"
           active-text-color="#409EFF">
    <sidebar-item :routes="permissionRouters" />
  </el-menu>
  </scroll-bar>
</template>

<script>
  import { mapGetters } from 'vuex'
  import SidebarItem from './SidebarItem'
  import ScrollBar from '@/components/ScrollBar'

  export default {
    components: { SidebarItem, ScrollBar },
    computed: {
      ...mapGetters([
        'permissionRouters',
        'sidebar'
      ]),
      isCollapse() {
        return !this.sidebar.opened
      }
    }
  }
</script>
