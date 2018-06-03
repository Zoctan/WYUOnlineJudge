<template>
  <div class="menu-wrapper">
    <template v-for="item in routes" v-if="!item.hidden && item.children">
      <router-link v-if="hasOneShowingChildren(item.children) && !item.children[0].children && !item.alwaysShow" :to="item.path + '/' + item.children[0].path" :key="item.children[0].name">
        <el-menu-item :index="item.path + '/' + item.children[0].path" :class="{'submenu-title-noDropDown' : !isNest}" >
          <!-- <svg-icon v-if="item.icon" :icon-class="item.icon" /> -->
          <span v-if="item.children[0].name">{{ item.children[0].name }}</span>
        </el-menu-item>
      </router-link>  
    </template>
  </div>
</template>

<script>
export default {
  name: 'SidebarItem',
  props: {
    routes: {
      type: Array
    },
    isNest: {
      type: Boolean,
      default: false
    }
  },
  methods: {
    hasOneShowingChildren(children) {
      const showingChildren = children.filter(item => {
        return !item.hidden
      })
      return showingChildren.length === 1
    },
  }
}
</script>
<style lang="scss" scoped>
.el-menu-item{
  white-space: normal;
  display: inline-block;
  width: 100px;
  vertical-align: top;
  text-align: center;
  &:focus{
    color: black;
    background-color: #409EFF;
  }
  &:hover{
    color: black; 
    background-color: #78B7F8;   
  }
}
.is-active{
  border-bottom: 3px solid white;
}
span{
  color: white;
}
</style>

