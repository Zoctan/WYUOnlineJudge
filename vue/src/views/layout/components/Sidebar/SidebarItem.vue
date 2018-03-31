<template>
  <div>
    <template v-for="item in routes" v-if="!item.hidden && item.children">
      <router-link v-if="hasOneShowingChildren(item.children) && !item.children[0].children && !item.alwaysShow" :to="item.path + '/' + item.children[0].path" :key="item.children[0].name">
        <el-menu-item :index="item.path + '/' + item.children[0].path" :class="{'submenu-title-noDropDown' : !isNest}">
          <svg-icon :icon-class="item.icon" />
          <span>{{ item.children[0].name }}</span>
        </el-menu-item>
      </router-link>

      <el-submenu v-else :index="item.name || item.path">
        <template slot="title">
          <svg-icon v-if="item.icon" :icon-class="item.icon" />
          <span>{{ item.name }}</span>
        </template>

        <template v-for="child in item.children" v-if="!child.hidden">
          <sidebar-item :is-nest="true" class="nest-menu" v-if="child.children && child.children.length > 0" :routes="[child]" :key="child.path" />

          <router-link v-else :to="item.path + '/' + child.path">
            <el-menu-item :index="item.path + '/' + child.path">
              <svg-icon v-if="child.icon" :icon-class="child.icon" />
              <span>{{ child.name }}</span>
            </el-menu-item>
          </router-link>
        </template>
      </el-submenu>
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
    }
  }
}
</script>

