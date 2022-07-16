<template>
    <div class="hygrometer" ref="hygrometer" style="width: 100%;height: 100%">

    </div>
</template>

<script>
    import * as echarts from 'echarts';
    export default {
        name: "ECherts",
    mounted() {
    // vue中获取到dom节点并对echarts图表初始化，vue中的$refs相当于原生js中的id
    let hygrometer = echarts.init(this.$refs.hygrometer);//初始化图形
        //图形数据
    const option = {
        //图例
        legend: {
            icon: 'rect',//形状  类型包括 circle，rect,line，roundRect，triangle，diamond，pin，arrow，none
            itemWidth: 10,  // 设置宽度
            itemHeight: 4, // 设置高度
            itemGap: 24, // 设置间距
            data: ['本地停车场', '外地停车场'],
            textStyle: {
                //文字样式
                color: '#192d52',
                fontSize: '12'
            },
            right: '30%',
            selectedMode: 'single'
        },
        //提示
        tooltip: {
            trigger: 'axis'
        },
        //横坐标
        xAxis: {
            type: 'category',
            splitLine: {
                lineStyle: {
                    color: '#29457e'//轴的颜色
                }
            },
            axisLabel:{
                interval: 0
            },
            data: ['5-10', '5-11', '5-12', '5-13', '5-14', '5-15', '5-16', '5-17',
                '5-18', '5-19', '5-20', '5--21']
        },
        //纵坐标
        yAxis: [
            {
                type: 'value',
                axisTick:{       //y轴刻度线
                    show: false
                },
                name: '利润(千元)',
                min: 0,
                max: 50,
                interval: 10,//间隔
                axisLabel: {
                    formatter: '{value}'
                }
            }
        ],
        series: [
            {
                name: '本地停车场',
                type: 'line',
                itemStyle : {
                    normal : {
                        lineStyle:{
                            color:'#b1de6a'
                        }
                    }
                },
                data: [2.0, 4.9, 7.0, 23.2, 25.6, 23, 25, 21, 26, 20, 25, 36, 48,]
            },
            {
                name: '外地停车场',
                type: 'line',
                itemStyle : {
                    normal : {
                        lineStyle:{
                            color:'#7000c1'
                        }
                    }
                },
                data: [2.6, 5.9, 9.0, 26.4, 28.7, 30, 37.6, 30.7, 40.5, 44,39,49]
            }
        ]
    };
    //绘制图形
    hygrometer.setOption(option);}
    }
</script>
