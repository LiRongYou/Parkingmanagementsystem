<template>
    <div style="width: 100%;height: 100%">
        <el-form>
            <el-row :gutter="24">
                <el-col :span="4">
                    <el-form-item label="位置经度" prop="lng">
                        <el-input v-model="model.lng" type="number" @blur="getMapPoint" class="input_number"
                                  @mousewheel.native.prevent/>
                    </el-form-item>
                </el-col>
                <el-col :span="4">
                    <el-form-item label="位置维度" prop="lat">
                        <el-input v-model="model.lat" type="number" @blur="getMapPoint" class="input_number"
                                  @mousewheel.native.prevent/>
                    </el-form-item>
                </el-col>
                <el-col :span="4">
                    <el-form-item label="停车场">
                        <el-select v-model="region" placeholder="选择停车场" @change="select">
                            <el-option
                                    v-for="item in options"
                                    :key="item.value"
                                    :label="item.label"
                                    :value="item.value">
                            </el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
            </el-row>
        </el-form>
        <div></div>
        <!--地图-->
        <baidu-map class="bm-view" ak="f6cN3pNUiXtGYvs5jcnDbhUw1l1SkiEq" :center="center" :zoom="zoom"
                   :scroll-wheel-zoom="true" @ready="createMap">
            <!-- 搜索-->
            <bm-local-search :keyword="model.address" :auto-viewport="true" style="display: none"></bm-local-search>
            <!-- 标记-->
            <bm-marker-clusterer>
                <bm-marker :position="{lng: model.lng, lat: model.lat}"></bm-marker>
            </bm-marker-clusterer>
        </baidu-map>
        <el-input v-model="model.address" placeholder="搜索地点"
                  style="margin-left: 10px;width: 200px;position: absolute;top: 25%;opacity: 0.9"
                  prefix-icon="el-icon-search"></el-input>
    </div>
</template>
方法

<script>
    import BaiduMap from 'vue-baidu-map/components/map/Map.vue'
    //标记点
    import BmMarkerClusterer from 'vue-baidu-map/components/extra/MarkerClusterer'
    //搜索
    import BmLocalSearch from 'vue-baidu-map/components/search/LocalSearch.vue'
    import axios from "axios";

    export default {
        name: "coordinate",
        components: {
            BaiduMap,
            BmMarkerClusterer,
            BmLocalSearch
        },
        data() {
            return {
                center: {lng: 0, lat: 0},
                zoom: 16,
                model: {
                    lng: '110.423522',
                    lat: '25.314207',
                    address: '',
                },
                region: '',
                options: [
                    {label: '西岸村停车场', value: '西岸村停车场'},
                    {label: '花江停车场', value: '花江停车场'},
                    {label: 'F区停车场', value: 'F区停车场'},
                    {label: 'D区停车场', value: 'D区停车场'},
                    {label: '中央停车场', value: '中央停车场'}
                ],
        }
        },
        methods: {
            select(value) {
                //value为选择的值 获取对应的经纬度
                axios({
                    url: `/api/PositionServlet/${value}`,
                    method: 'get',
                }).then(res => {
                    this.center.lng = this.model.lng;
                    this.center.lat = this.model.lat;
                    this.findposition(res.data.lng, res.data.lat)
                }).catch(erroe => {
                    //失败后
                    alert("失败");
                })
            },
// 当以及地址input失焦时触发在页面上显示点的事件
            getMapPoint() {
                this.findposition(this.model.lng, this.model.lat)
            },
            //初始化地图
            createMap({BMap, map}) {
                // 百度地图API功能
                // this.center.lng = 110.423522;
                // this.center.lat = 25.314207;
                this.center.lng = this.model.lng;
                this.center.lat = this.model.lat;
                this.zoom = this.zoom
                this.map2 = map
                const viewthis = this
                //地图监听
                map.addEventListener('click', function (e) {
                    viewthis.model.lng = e.point.lng
                    viewthis.model.lat = e.point.lat
                })

                // 区域图
                var datas = new Array('徐州市-#665599')
                var bdary = new BMap.Boundary()
                for (var i = 0; i < datas.length; i++) {
                    getBoundary(datas[i], bdary)
                }

                // 设置区域图
                function getBoundary(data, bdary) {
                    data = data.split('-')
                    bdary.get(data[0], function (rs) {
                        // 获取行政区域
                        var count = rs.boundaries.length // 行政区域的点有多少个
                        // var pointArray = []
                        for (var i = 0; i < count; i++) {
                            var ply = new BMap.Polygon(rs.boundaries[i], {
                                strokeWeight: 2,
                                strokeColor: '#ff0000',
                                fillOpacity: 0.1,
                                fillColor: data[1]
                            }) // 建立多边形覆盖物
                            map.addOverlay(ply) // 添加覆盖物
                        }
                    })
                }
            },
            findposition(lng, lat) {
                this.model.lng = lng;
                this.model.lat = lat;

                this.center.lng = this.model.lng;
                this.center.lat = this.model.lat;
            }
        },

    }
</script>
<style scoped>
    .bm-view {
        width: 100%;
        height: 500px;
    }
</style>

