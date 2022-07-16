<template>
    <div class="">
        <baidu-map class="map" :center="{lng: 110.362006, lat: 25.321654}" :zoom="11">
            <bm-driving :start="start" :end="end" @searchcomplete="handleSearchComplete" :panel="false" @stop="reset"
                        :autoViewport="true"></bm-driving>
            <bml-lushu :path="path" :icon="icon" :play="play" :rotation="true">
            </bml-lushu>
        </baidu-map>
        <input ref="start" placeholder="请输入起点" value="桂林北站" />
        <input ref="end" placeholder="请输入终点" value="桂林电子科技大学花江校区" />
        <button type="button" @click="confirm">确认</button>
    </div>
</template>

<script>
    import {
        BmlLushu
    } from 'vue-baidu-map'
    export default {
        name:"map",
        computed: {},
        components: {
            BmlLushu
        },
        data() {
            return {
                play: false,
                path: [],
                icon: {
                    url: 'http://api.map.baidu.com/library/LuShu/1.2/examples/car.png',
                    size: {
                        width: 52,
                        height: 26
                    },
                    opts: {
                        anchor: {
                            width: 27,
                            height: 13
                        }
                    }
                },
                start: '桂林北站',
                end: '桂林电子科技大学花江校区',
            }
        },
        methods: {
            reset () {
                this.play = false
            },
            handleSearchComplete(res) {
                this.path = res.getPlan(0).getRoute(0).getPath()
            },
            confirm() {
                this.start = this.$refs.start.value
                this.end = this.$refs.end.value
                this.play = true
            }
        }
    }
</script>

<style scoped>
    .map {
        height: 400px;
        width: 100%;
    }
</style>