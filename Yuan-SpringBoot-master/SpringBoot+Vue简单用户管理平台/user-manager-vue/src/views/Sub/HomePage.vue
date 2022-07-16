<template>
    <div class="main">
        <el-card class="box-card" shadow="hover">
            <div slot="header" class="clearfix r-header">
                <img class="userimg" :src="userImg"/>
                <div>
                    <p style="font-size:20px;">{{name}}</p>
                    <p class="gray">超级管理员</p>
                </div>
            </div>
            <div style="height: 70px">
                <p class="gray">上次登录时间: <span>{{date}}</span></p>
                <br/>
                <p class="gray">上次登录地点: <span>{{address}}</span></p>
            </div>
        </el-card>
        <el-table
                v-loading="loading"
                :data="tableData"
                style="width: 30%;margin-top: 3%;margin-left: 3%">
            <el-table-column
                    prop="date"
                    label="日期"
                    width="130">
            </el-table-column>
            <el-table-column
                    prop="name"
                    label="姓名"
                    width="130">
            </el-table-column>
            <el-table-column
                    prop="address"
                    label="地址">
            </el-table-column>
        </el-table>
<!--        卡片-->
        <div class="order">
            <div class="main1" v-for="(item, index) in orderData" :key="index">
                <i class="icon" :class="`el-icon-${item.icon}`" :style="{ background: item.color }"></i>
                <div class="detail">
                    <p style="font-size: 20px;
                    margin-bottom: 10px;
                    font-weight: 800;
                    margin-left: 10px;">￥{{ item.value }}</p>
                    <p style="font-size: 13px;
                    color: #bbbbbb;
                    margin-left: 10px;">{{ item.name }}</p>
                </div>
            </div>
        </div>
<!--        折线图-->
        <div class="chart">
            <ECharts></ECharts>
        </div>
    </div>
</template>

<script>
    import axios from "axios";
    import ECharts from "./Charts/ECharts";

    export default {
        name: 'HomePage',
        components:{ ECharts },
        data() {
            return {
                name: '',
                date: '',
                address: '',
                userImg: 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.2qqtouxiang.com%2Fpic%2FTX9829_04.jpg&refer=http%3A%2F%2Fimg.2qqtouxiang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1655687047&t=e53f8e8d7414ff12a97b974c9e0a0772',
                tableData: '',
                loading: false,
                // 订单数据
                orderData: [
                    {
                        name: "今日支付订单",
                        value: 332,
                        icon: "success",
                        color: "#2ec7c9",
                    },
                    {name: "今日收藏订单", value: 64, icon: "star-on", color: "#ffb980"},
                    {
                        name: "今日未支付订单",
                        value: 142,
                        icon: "s-goods",
                        color: "#5ab1ef",
                    },
                    {
                        name: "本月支付订单",
                        value: 4064,
                        icon: "success",
                        color: "#2ec7c9",
                    },
                    {name: "本月收藏订单", value: 1536, icon: "star-on", color: "#ffb980"},
                    {
                        name: "本月未支付订单",
                        value: 1234,
                        icon: "s-goods",
                        color: "#5ab1ef",
                    },
                ],
            }
        },

        mounted() {
            axios({
                //向后端发送数据
                method: "post",
                url: '/api/LoginServlet/loginsuername',
            }).then(res => {
                this.name = res.data;
            }).catch(erroe => {
                //失败后
                alert("登录信息获取失败");
            }),
                //获取列表
                axios({
                    //向后端发送数据
                    method: "post",
                    url: '/api/LogininfoServlet/logininfolist',
                }).then(res => {
                    this.tableData = res.data;
                    this.date = res.data[0].date;
                    this.address = res.data[0].address;
                }).catch(erroe => {
                    //失败后
                    alert("登录信息获取失败");
                })
        },
        methods: {

        }
    }
</script>
<style>
    .box-card {
        border-color: #bbbbbb;
        width: 30%;
        margin-left: 3%;
    }

    .gray {
        text-align: center;
        color: #bbbbbb;
        font-size: 14px;
    }

    .r-header {
        display: flex;
    }

    .userimg {
        border-radius: 50%;
        width: 100px;
        height: 100px;
        margin: 10px;
    }

    /*订单*/
    .order {
        /*background-color: #0b5137;*/
        width: 60%;
        height: 100px;
        margin-top: -580px;
        float: right;
        display: flex;
        flex-wrap: wrap;
        min-width: 65%;
    }

    .main1 {
        display: flex;
        flex-basis: 32%;
        background-color: #fff;
        margin: 0 1% 1% 0;
    }

    .icon {
        padding: 0;

        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        font-size: 25px;
        margin-bottom: 10px;
        color: #fff;
        background: #2ec7c9;
        width: 50px;
        height: 50px;
    }

    .detail {
        margin-top: -10%;
        flex-direction: column;
        align-items: flex-start;
        justify-content: center;
    }

    /*图形*/
    .chart {
        border-color: #bbbbbb;
        float: right;
        margin-top: -430px;
        width: 65%;
        height: 400px;
    }
</style>