<template>
    <div class="Dailyprofit" id="Dailyprofit" :style="myChartStyle"></div>
</template>

<script>
    import * as echarts from "echarts";
    export default {
        name: "Dailyprofit",
        data() {
            return {
                xData: ["Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"], //横坐标
                yData: [23, 24, 18, 25, 27, 28, 25], //数据
                myChartStyle: { float: "left", width: "100%", height: "100%" } //图表样式
            };
        },
        mounted() {
            this.initEcharts();
        },
        methods: {
            initEcharts() {
                // 基本柱状图
                const option = {
                    title: {
                        // 设置饼图标题，位置设为顶部居中
                        text: "每月总收益图",
                        top: "0%",
                        left: "left"
                    },
                    //提示
                    tooltip: {
                        trigger: 'axis'
                    },
                    xAxis: {
                        data: this.xData
                    },
                    yAxis: {},
                    series: [
                        {
                            type: "bar", //形状为柱状图
                            data: this.yData
                        }
                    ]
                };
                const myChart = echarts.init(document.getElementById("Dailyprofit"));
                myChart.setOption(option);
                //随着屏幕大小调节图表
                window.addEventListener("resize", () => {
                    myChart.resize();
                });
            }
        }
    }
</script>

<style scoped>

</style>