<template>
  <el-menu class="navbar" mode="horizontal" :default-active="activeIndex">
    <sidebar :routes="permissionRouters"/>
    <div class="right">
      <el-tooltip effect="dark" content="全屏" placement="bottom">
        <screenfull class="screenfull right-menu-item"/>
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

          <el-dropdown-item divided v-if="token">
            <span @click="logout" style="display:block;">登出</span>
          </el-dropdown-item>

          <!-- <router-link class="inlineBlock" v-else to="/login/index"> -->
            <el-dropdown-item v-else divided>
              <span @click="loginWindow" style="display:block;">登录</span>
            </el-dropdown-item>
          <!-- </router-link> -->
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
  import Sidebar from './Sidebar'

  export default {
    components: {
      Breadcrumb,
      Hamburger,
      Screenfull,
      Sidebar,
    },
    data() {
      return {
        time: null,
        activeIndex: this.$route.path
      }
    },
    computed: {
      ...mapGetters([
        'token',
        'username',
        'sidebar',
        'permissionRouters',
        'avatar',
        'showlogin'
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
      },
      ListenactiveIndex(e) {
        console.log(e)
        this.activeIndex = e
      },
      loginWindow(){
        this.$store.commit('change_showLogin',true)
      }
    }
  }
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
  .navbar {
    height: 57px;
    line-height: 57px;
    border-radius: 0 !important;
    background-color: #409EFF;
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
      &:focus {
        outline: none;
      }
      .right-menu-item {
        display: inline-block;
        margin: 0 8px;
      }
      .screenfull {
        color: #fff;
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
            color: #fff;
            position: absolute;
            right: -20px;
            top: 18px;
            font-size: 12px;
          }
        }
      }
    }
  }

  .menu-wrapper {
    display: inline-block;
    margin-left: 50px;
  }
</style>

