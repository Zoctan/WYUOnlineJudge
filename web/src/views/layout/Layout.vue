<template>
  <div class="app-wrapper" :class="{ hideSidebar:!sidebar.opened }">
    <div class="main-container">
      <navbar/>
      <app-main/>
      <transition name="login-fade" mode="in-out">
        <div v-if="showlogin" class="login">
          <Login/>
        </div>
      </transition>
    </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import { Navbar, AppMain } from "./components";
import Login from "@/components/login/index";
import App from "../../App";

export default {
  name: "layout",
  components: {
    App,
    Navbar,
    AppMain,
    Login
  },
  computed: {
    ...mapGetters(["showlogin"]),
    sidebar() {
      return this.$store.state.app.sidebar;
    }
  }
};
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
@import "src/styles/mixin.scss";

.app-wrapper {
  @include clearfix;
  position: relative;
  height: 100%;
  width: 100%;
}
.login {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  z-index: 9998;
}
</style>
