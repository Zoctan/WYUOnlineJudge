<template>
  <el-card class="box-card">
    <div class="link" v-for="(mes,index) in messages" :key="index">
      <a :href="mes.src" target="_blank">{{mes.title}}</a>
    </div>
  </el-card>
</template>
<script>
export default {
  data() {
    return {
      messages: [
        {
          title: "Mybatis学习笔记之二（动态mapper开发和spring-mybatis整合）",
          src:
            "http://www.cnblogs.com/Kevin-ZhangCG/p/9112912.html?utm_source=gold_browser_extension"
        },
        {
          title: "从构建分布式秒杀系统聊聊Lock锁使用中的坑",
          src:
            "https://juejin.im/entry/5b0e79d65188253bdb1b3f6a?utm_source=gold_browser_extension"
        }
      ]
    };
  }
};
</script>
<style lang="scss" scoped>
.link {
  color: blue;
  padding: 10px 0;
  a:hover {
    text-decoration: underline;
  }
}
</style>
