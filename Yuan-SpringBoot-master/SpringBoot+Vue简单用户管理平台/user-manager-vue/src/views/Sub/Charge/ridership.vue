<template>
  <div class="hygrometer" ref="hygrometer" style="width: 100%;height: 100%">

  </div>
</template>

<script>
  import * as echarts from 'echarts';
  export default {
    name: "ridership",
    mounted() {
      // vue中获取到dom节点并对echarts图表初始化，vue中的$refs相当于原生js中的id
      let hygrometer = echarts.init(this.$refs.hygrometer);//初始化图形
      //图形数据
      const option = {
        title: {
          // 设置饼图标题，位置设为顶部居中
          text: "价格变动",
          top: "0%",
          left: "left"
        },
        //图例
        legend: {
          icon: 'rect',//形状  类型包括 circle，rect,line，roundRect，triangle，diamond，pin，arrow，none
          itemWidth: 10,  // 设置宽度
          itemHeight: 4, // 设置高度
          itemGap: 24, // 设置间距
          data: ['西岸村停车场', '花江停车场','F区停车场','D区停车场','中央停车场'],
          textStyle: {
            //文字样式
            color: '#192d52',
            fontSize: '12'
          },
          right: '30%',
          // selectedMode: 'single'//注销这里所有折线显示
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
            name: '价格(小时)',
            min: 0,
            max: 15,
            interval: 2,//间隔
            axisLabel: {
              formatter: '{value}'
            }
          }
        ],
        series: [
          {
            name: '西岸村停车场',
            type: 'line',
            itemStyle : {//这里设置的拐点颜色
              normal : {
                lineStyle:{
                  color:'#b1de6a'
                }
              }
            },
            data: [5, 4.9, 3, 4, 5, 5, 5, 6, 8, 7, 5, 6, 5,]
          },
          {
            name: '花江停车场',
            type: 'line',
            itemStyle : {
              normal : {
                lineStyle:{
                  color:'#7000c1'
                }
              }
            },
            data: [6, 6, 8, 8, 7, 5, 7, 7, 6, 8,6,6]
          }
        ]
      };
      //绘制图形
      hygrometer.setOption(option);}
  }
</script>
