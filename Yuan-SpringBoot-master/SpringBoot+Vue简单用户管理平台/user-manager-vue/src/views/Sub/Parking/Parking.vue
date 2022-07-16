<template>
    <div class="block">
        <div style="width: 400px;height: 50px">
            <el-form ref="form" :model="form" label-width="80px">
                <el-form-item label="停车场" prop="name">
                    <el-select v-model="region" placeholder="请选择需要查看的停车场" @change="select">
                        <el-option
                                v-for="item in options"
                                :key="item.value"
                                :label="item.label"
                                :value="item.value">
                        </el-option>
                    </el-select>
                </el-form-item>
            </el-form>
        </div>
        <div class="parking"></div>
        <div class="guodao"></div>
        <div class="parking"></div>

        <div v-for="item in Parkingcar"
             :key="item"
             :class="item.classes">
            <!--前盖竖线白车-->
            <el-image style="width: 250px; height: 300px;margin-left: -80px;margin-top: -30px"
                      v-if="item.havacar=='true'&&item.baiche1=='true'"
                      :src="baiche1url"
                      :fit="fit"></el-image>
            <!--前盖弧线白车-->
            <el-image style="width: 250px; height: 300px;margin-left: -80px;margin-top: -30px"
                      v-else-if="item.havacar=='true'&&item.baiche2=='true'"
                      :src="baiche2url"
                      :fit="fit"></el-image>
            <!--前盖没有线白车-->
            <el-image style="width: 250px; height: 300px;margin-left: -80px;margin-top: -30px"
                      v-else-if="item.havacar=='true'&&item.baiche3=='true'"
                      :src="baiche3url"
                      :fit="fit"></el-image>
            <!--红车-->
            <el-image style="width: 250px; height: 300px;margin-left: -80px;margin-top: -30px"
                      v-else-if="item.havacar=='true'&&item.hongche=='true'"
                      :src="hongcheurl"
                      :fit="fit"></el-image>
            <!--黄车-->
            <el-image style="width: 250px; height: 300px;margin-left: -80px;margin-top: -30px"
                      v-else-if="item.havacar=='true'&&item.huangche=='true'"
                      :src="huangcheurl"
                      :fit="fit"></el-image>
            <!--车位没有车-->
            <el-image style="width: 250px; height: 300px;margin-left: -80px;margin-top: -30px;z-index:-10"
                      v-else
                      :src="kongcheurl"
                      :fit="fit" ></el-image>
        </div>

    </div>
</template>

<script>
    import axios from "axios";

    export default {
        name: "Parking",
        components: {},
        data() {
            return {
                form: {},
                options: [
                    {label: '西岸村停车场', value: '西岸村停车场'},
                    {label: '花江停车场', value: '花江停车场'},
                    {label: 'F区停车场', value: 'F区停车场'},
                    {label: 'D区停车场', value: 'D区停车场'},
                    {label: '中央停车场', value: '中央停车场'}
                ],
                region: '',
                // 选中的选项
                item: {},
                fits: ['fill', 'contain', 'cover', 'none', 'scale-down'],
                baiche1url: 'https://img0.baidu.com/it/u=424690351,3991943667&fm=253&fmt=auto&app=138&f=PNG?w=500&h=500',
                baiche2url: 'https://img0.baidu.com/it/u=233373258,3919362190&fm=253&fmt=auto&app=138&f=PNG?w=500&h=500',
                baiche3url:'https://pic.90sheji.com/design/03/31/15/02/5cc04cfd6beca.png!/fw/280/quality/90/unsharp/true/compress/true/canvas/280x280a0a0/cvscolor/FFFFFFFF',
                // hongscheurl1: require('../../img/红车.jpg'),
                kongcheurl:'',//车位没有车
                hongcheurl: 'https://pic.90sheji.com/design/03/31/15/02/5cc04d8f8c9d8.png!/fw/280/quality/90/unsharp/true/compress/true/canvas/280x280a0a0/cvscolor/FFFFFFFF',
                heischeurl: 'https://pic.90sheji.com/design/03/31/15/02/5cc04d8f8c9d8.png!/fw/280/quality/90/unsharp/true/compress/true/canvas/280x280a0a0/cvscolor/FFFFFFFF',
                huangcheurl:'https://pic.90sheji.com/design/03/31/15/02/5cb8f45f19ce3.png!/fw/280/quality/90/unsharp/true/compress/true/canvas/280x280a0a0/cvscolor/FFFFFFFF',
                Parkingcar: [],

            }
        },
        mounted() {
            //默认显示
            this.getparkingcar('西岸村停车场')
        },
        methods: {
            select(value) {
                //value为选择的值
                //调用函数
                this.getparkingcar(value)
            },
            getparkingcar(value){
                //获取停车列表信息
                axios({
                    //向后端发送数据
                    method: "post",
                    url: '/api/ParkingServlet/parkingcarlist',
                    data: {parkingname:value},
                }).then(res => {
                    this.Parkingcar=res.data
                }).catch(erroe => {
                    //失败后
                    alert("登录信息获取失败");
                })
            }
        },
        computed: {},
    }
</script>

<style src="./Parking.css"></style>
<style scoped>
    .block {
        width: 100%;
        height: 100%;
    }

    .parking {
        width: 2210px;
        /*height: 696px;*/
        height: 348px;
        background-image: url("../../img/车位1.png");
    }

    .guodao {
        background-color: RGB(132, 129, 130);
        height: 100px;
        width: 2210px;
    }

</style>