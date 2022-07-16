<template>
    <div class="video">
        <div class="video1">
            <video autoplay="autoplay" name="media" loop="loop"
                   style="width:100%; height:12.88rem; object-fit: fill;border:1px solid #3b72ee;">
                <source src="../../../assets/video/video11.mp4" type="video/mp4" >
            </video>
            <p><span>摄像头：A </span><span>位置：北门入口 </span></p>
        </div>
        <div class="video2">
            <video autoplay="autoplay" name="media" loop="loop"
                   style="width:100%; height:12.88rem; object-fit: fill;border:1px solid #3b72ee;">
                <source src="../../../assets/video/video11.mp4" type="video/mp4">
            </video>
            <p><span>摄像头：B </span><span>位置：南门入口 </span></p>
        </div>
        <div class="video3">
            <video autoplay="autoplay" name="media" loop="loop"
                   style="width:100%; height:12.88rem; object-fit: fill;border:1px solid #3b72ee;">
                <source src="../../../assets/video/video11.mp4" type="video/mp4">
            </video>
            <p><span>摄像头：C </span><span>位置：车库A区 </span></p>
        </div>
        <div class="video1">
            <video autoplay="autoplay" name="media" loop="loop"
                   style="width:100%; height:12.88rem; object-fit: fill;border:1px solid #3b72ee;">
                <source src="../../../assets/video/video11.mp4" type="video/mp4">
            </video>
            <p><span>摄像头：D </span><span>位置：北门出口 </span></p>
        </div>
        <div class="video2">
            <video autoplay="autoplay" name="media" loop="loop"
                   style="width:100%; height:12.88rem; object-fit: fill;border:1px solid #3b72ee;">
                <source src="../../../assets/video/video11.mp4" type="video/mp4">
            </video>
            <p><span>摄像头：E </span><span>位置：南门出口 </span></p>
        </div>
        <div class="video3">
            <video autoplay="autoplay" name="media" loop="loop"
                   style="width:100%; height:12.88rem; object-fit: fill;border:1px solid #3b72ee;">
                <source src="../../../assets/video/video11.mp4" type="video/mp4">
            </video>
            <p><span>摄像头：F </span><span>位置：车库B区 </span></p>
        </div>
<!--        <div class="video1">-->
<!--            <monitor></monitor>-->
<!--        </div>-->
    </div>

</template>

<script>
    import monitor from "../Video/monitor";
    export default {
        name: 'videoShow',
        data() {
            return {
            }
        },
        components: {monitor}
    }

</script>

<style scoped>
.video1{
    width: 32%;
}
.video2{
    width: 30%;
    margin-left: 34%;
    margin-top: -265px;
}
.video3{
    margin-left: 68%;
    margin-top: -265px;
    width: 30%;
}

p{
    text-align: center;
}
</style>
