<template>
    <div class="operating">
        <div class="one">
            <div style="position:absolute;margin-top: 3px;margin-left: 9%">满意指数</div>
            <div style="position: absolute;margin-left: 6.5%;margin-top: 40px"><el-rate
                    v-model="value1"
                    disabled
                    show-score
                    text-color="#ff9900"
                    score-template="{value}">
            </el-rate></div>
        </div>
        <div class="two">
            <div style="position:absolute;margin-top: 3px;margin-left: 9%">收益指数</div>
            <div style="position: absolute;margin-left: 6.5%;margin-top: 40px"><el-rate
                    v-model="value2"
                    disabled
                    show-score
                    text-color="#ff9900"
                    score-template="{value}">
            </el-rate></div>
        </div>
        <div class="there">
            <div style="position:absolute;margin-top: 3px;margin-left: 9%">车位使用率</div>
            <div style="position: absolute;margin-left: 10%;margin-top: 40px">74.5%</div>
        </div>

    </div>

</template>

<script>
    export default {
        name: 'operating',
        components: {},
        data() {
            return {
                value1: 3.7,
                value2: 4.3,
            }
        },
        mounted() {

        },
        methods: {

        },
    }

</script>

<style scoped>
   .operating{
       width: 100%;
       height: 100%;
   }
    .one{
        background-color: #3b72ee;
        background-image: url("./1.png");
        background-repeat: no-repeat;
        background-size: 40% 100%;
        width: 100%;
        height: 32%;
    }
   .two{
       background-color: #2ec7c9;
       background-image: url("./2.png");
       background-repeat: no-repeat;
       background-size: 40% 100%;
       width: 100%;
       height: 32%;
       margin-top: 6px;
   }
   .there{
       background-color: #4E655D;
       background-image: url("./3.png");
       background-repeat: no-repeat;
       background-size: 40% 100%;
       border-radius: 4px;
       width: 100%;
       height: 32%;
       margin-top: 6px;
   }
</style>
