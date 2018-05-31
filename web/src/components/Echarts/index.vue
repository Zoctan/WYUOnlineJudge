
<template>
    <div class="content">
        <div id="main"></div>
    </div>
</template>
<script>
// 导入echarts
import echarts from "echarts";

// 皮肤引入 import theme-name from theme-folder

// 以饼图为例
// 其他种类图表配置项参见百度echarts官网

export default {
  props: ["opinionData"],
  data() {
    return {
      // 初始化空对象
      chart: null
    };
  },
  methods: {
    // 绘图
    drawGraph(id) {
      // 绘图方法
      this.chart = echarts.init(document.getElementById(id));
      // 皮肤添加同一般使用方式
      this.chart.showLoading();
      let opinionData = this.opinionData;
      console.log(opinionData);
      let color = ["#006699", "#80d5ff", "#1ab3ff"];
      let opinion = [];
      for (let i in opinionData) {
        opinion.push(opinionData[i].name);
        let itemStyle = { normal: { color: color[i] } };
        opinionData[i].itemStyle = itemStyle;
      }

      // 返回到data中
      // set
      this.chart.setOption({
        title: {
          text: "",
          subtext: "",
          x: "center"
        },
        tooltip: {
          trigger: "item",
          formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
          x: "center",
          y: "bottom",
          data: opinion
        },
        toolbox: {
          show: true,
          feature: {
            mark: {
              show: true
            },
            dataView: {
              show: true,
              readOnly: false
            },
            magicType: {
              show: true,
              type: ["pie"]
            },
            restore: {
              show: true
            },
            saveAsImage: {
              show: true
            }
          }
        },
        calculable: true,
        series: [
          {
            name: "种类",
            type: "pie",
            // 内圆半径，外圆半径
            radius: "50%",
            data: opinionData
          }
        ]
      });
      this.chart.hideLoading();
    }
  },
  // keypoint：执行方法
  // “将回调延迟到下次 DOM 更新循环之后执行。在修改数据之后立即使用它，然后等待 DOM 更新。”
  mounted() {
    this.$nextTick(function() {
      this.drawGraph("main");
    });
  }
};
</script>
<style scoped>
#main {
  /*需要制定具体高度，以px为单位*/
  height: 250px;
}
</style>