<template>
  <el-menu class="navbar" mode="horizontal">
    <hamburger class="hamburger-container" :toggleClick="toggleSideBar" :isActive="sidebar.opened" />
    <breadcrumb class="breadcrumb-container" />
    <div class="right">
      <el-tooltip effect="dark" content="全屏" placement="bottom">
        <screenfull class="screenfull right-menu-item" />
      </el-tooltip>

      <el-dropdown class="user-container right-menu-item" trigger="click">
        <div class="avatar-wrapper">
          <img class="user-avatar" :src="avatar + '?imageView2/1/w/80/h/80'">
          <i class="el-icon-caret-bottom"></i>
        </div>
        <el-dropdown-menu slot="dropdown">
          <router-link class="inlineBlock" to="/user/index">
            <el-dropdown-item>{{ username }}</el-dropdown-item>
          </router-link>
          <router-link class="inlineBlock" to="/user/index" v-if="token">
            <el-dropdown-item>我的收藏</el-dropdown-item>
          </router-link>

          <el-dropdown-item divided v-if="token">
            <span @click="logout" style="display:block;">登出</span>
          </el-dropdown-item>

          <router-link class="inlineBlock" v-else to="/login/index">
            <el-dropdown-item divided >
              <span style="display:block;">登录</span>
            </el-dropdown-item>
          </router-link>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
  </el-menu>
</template>

<script>
  import { mapGetters } from 'vuex'
  import Hamburger from '@/components/Hamburger'
  import Screenfull from '@/components/Screenfull'
  import Breadcrumb from '@/components/Breadcrumb'

  export default {
    components: {
      Breadcrumb,
      Hamburger,
      Screenfull
    },
    data() {
      return {
        time: null
      }
    },
    computed: {
      ...mapGetters([
        'token',
        'username',
        'sidebar',
        'avatar'
      ])
    },
    methods: {
      toggleSideBar() {
        this.$store.dispatch('ToggleSideBar')
      },
      logout() {
        this.$store.dispatch('Logout').then(() => {
          this.$store.dispatch('FedLogout').then(() => {
            location.reload() // 为了重新实例化vue-router对象 避免bug
          })
        })
      }
    }
  }
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
  .navbar {
    height: 50px;
    line-height: 50px;
    border-radius: 0 !important;
    .hamburger-container {
      line-height: 58px;
      height: 50px;
      float: left;
      padding: 0 10px;
    }
    .breadcrumb-container {
      float: left;
    }
    .right {
      float: right;
      height: 100%;
      &:focus{
        outline: none;
      }
      .right-menu-item {
        display: inline-block;
        margin: 0 8px;
      }
      .screenfull {
        height: 20px;
      }
      .user-container {
        height: 50px;
        margin-right: 30px;
        .avatar-wrapper {
          cursor: pointer;
          margin-top: 5px;
          position: relative;
          .user-avatar {
            width: 40px;
            height: 40px;
            border-radius: 10px;
          }
          .el-icon-caret-bottom {
            position: absolute;
            right: -20px;
            top: 20px;
            font-size: 12px;
          }
        }
      }
    }
  }
</style>

