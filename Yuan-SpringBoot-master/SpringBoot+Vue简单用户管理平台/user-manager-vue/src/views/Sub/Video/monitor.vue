<template>
    <!-- playsinline：设置播放器在移动设备上不全屏[ Boolean, default: false ] -->
    <!-- customEventName：自定义状态变更时的事件名[ String, default: 'statechanged' ] -->
    <video-player
            　　　　class="video-player-custom"
            ref="videoPlayer"
            :options="playerOptions"
            :playsinline="true"
            customEventName="customstatechangedeventname"
            @play="onPlayerPlay"
            @pause="onPlayerPause"
            @ended="onPlayerEnded"
            @ready="playerReadied"
            @statechanged="playerStateChanged"
            @playing="onPlayerPlaying"
            @waiting="onPlayerWaiting"
            @loadeddata="onPlayerLoadeddata"
            @timeupdate="timeupdate"
            @canplay="onPlayerCanplay"
            @canplaythrough="onPlayerCanplaythrough">
    </video-player>
</template>
<script>
    import 'videojs-contrib-hls'
    import { videoPlayer } from 'vue-video-player'

    export default {
        name:"monitor",
        components: {
            videoPlayer
        },
        data() {
            return {
                playerOptions: {
                    // 是否静音
                    muted: true,
                    // 默认为英语，设置为中文
                    language: 'zh-CN',
                    // 播放速度，指定后Video.js将显示一个控件(vjs-playback-rate类的控件)，允许用户选择播放速度
                    playbackRates: [0.5, 1.0, 1.5, 2.0],
                    // 将播放器置于流畅模式，并在计算播放器的动态大小时使用该值，表示长宽比例
                    aspectRatio: '4:3',
                    // 兼容顺序，默认值是['html5']，其他已注册的技术将按其注册的顺序在该技术之后添加。
                    techOrder: ['html5'],
                    // 等同于原生<video>标签中的一组<source>子标签，可实现优雅降级；type 属性规定媒体资源的 MIME 类型
                    sources: [
                        //{
                        //type: "video/mp4",
                        //src: ""
                        //},
                        //{
                        //type: "rtmp/flv",
                        //src: ""
                        //},
                        {
                            type: "application/x-mpegURL",
                            src: "http://ivi.bupt.edu.cn/hls/btv2hd.m3u8"
                        },
                    ],
                    // 视频封面
                    poster: require('../../img/userimg.jpg'),
                }
            }
        },
        computed: {
            //插件节点 用于添加自定义按钮事件
            player() {
                return this.$refs.videoPlayer.player
            }
        },
        mounted() {},
        methods: {
            // 播放回调
            onPlayerPlay(player) {
            },
            // 暂停回调
            onPlayerPause(player) {
                //console.log(player)
            },
            // 视频播放结束回调
            onPlayerEnded(player) {
                //console.log(player)
            },
            // DOM元素上的readyState更改导致播放停止
            onPlayerWaiting(player) {
                //console.log(player)
            },
            // 已开始播放回调
            onPlayerPlaying(player) {
                //console.log(player)
            },
            // 当播放器在当前播放位置下载数据时触发
            onPlayerLoadeddata($event) {
                //console.log($event)
            },
            // 当前播放位置发生变化时触发。
            onPlayerTimeupdate($event) {
                //console.log($event)
            },
            //媒体的readyState为HAVE_FUTURE_DATA或更高
            onPlayerCanplay($event) {
                //console.log($event)
            },
            //媒体的readyState为HAVE_ENOUGH_DATA或更高。这意味着可以在不缓冲的情况下播放整个媒体文件。
            onPlayerCanplaythrough($event) {
                //console.log($event)
            },
            //播放状态改变回调
            playerStateChanged($event) {
                //console.log($event)
            },
            //将侦听器绑定到组件的就绪状态。与事件监听器的不同之处在于，如果ready事件已经发生，它将立即触发该函数。。
            playerReadied($event) {
                //console.log($event)
            },
        }
    }
</script>
<style  scoped>

</style>