<template>
    <div class="ProfitPie" id="ProfitPie" :style="myChartStyle"></div>
</template>

<script>
    import * as echarts from "echarts";

    export default {
        name:"ProfitPie",
        data() {
            return {
                myChart: {},
                pieData: [
                    {
                        value: 463,
                        name: "D区"
                    },
                    {
                        value: 395,
                        name: "F区"
                    },
                    {
                        value: 157,
                        name: "西岸村"
                    },
                    {
                        value: 149,
                        name: "花江"
                    },
                    {
                        value: 147,
                        name: "中央"
                    }
                ],
                pieName: [],
                myChartStyle: { float: "left", width: "100%", height: "100%" } //图表样式
            };
        },
        mounted() {
            this.initDate(); //数据初始化
            this.initEcharts();
        },
        methods: {
            initDate() {
                for (let i = 0; i < this.pieData.length; i++) {
                    // this.xData[i] = i;
                    // this.yData =this.xData[i]*this.xData[i];
                    this.pieName[i] = this.pieData[i].name;
                }
            },
            initEcharts() {
                // 饼图
                const option = {
                    // legend: {
                    //     // 图例
                    //     data: this.pieName,
                    //     right: "1%",
                    //     top: "30%",
                    //     orient: "vertical"
                    // },
                    title: {
                        // 设置饼图标题，位置设为顶部居中
                        text: "利润占比图",
                        top: "0%",
                        left: "center"
                    },
                    series: [
                        {
                            type: "pie",
                            label: {
                                show: true,
                                formatter: "{b} : {c} ({d}%)" // b代表名称，c代表对应值，d代表百分比
                            },
                            radius: "60%", //饼图半径
                            data: this.pieData
                        }
                    ]
                };
                const optionFree = {
                    xAxis: {},
                    yAxis: {},
                    series: [
                        {
                            data: this.seriesData,
                            type: "line",
                            smooth: true
                        }
                    ]
                };
                this.myChart = echarts.init(document.getElementById("ProfitPie"));
                this.myChart.setOption(option);
                //随着屏幕大小调节图表
                window.addEventListener("resize", () => {
                    this.myChart.resize();
                });
            }
        }
    };
</script>

