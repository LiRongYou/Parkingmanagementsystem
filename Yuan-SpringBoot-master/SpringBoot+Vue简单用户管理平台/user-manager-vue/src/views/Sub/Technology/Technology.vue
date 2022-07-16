<template>
    <div class="maxbox">
        <div style="text-align: center;font-family: 黑体;font-size: 20px;">系统所用技术</div>
        <div style="width: 30%;margin-top: 10px">
        <img src="https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic4.zhimg.com%2Fv2-62ca41ce8d98f8513be1e28b8afe78ed_1440w.jpg%3Fsource%3D172ae18b&refer=http%3A%2F%2Fpic4.zhimg.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1655737389&t=00f42c42bb720c68e8edad0ef6c323ff">
            <span> SpringBoot是为了简化在项目开发过程中的大量而繁琐的配置而开发出来的一套框架,是对Spring框架技术的一次提升。</span>
        </div>
        <div style="width: 30%;margin-left: 32%;margin-top: -265px">
            <img src="https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.thosefree.com%2Fwp-content%2Fuploads%2F2020%2F05%2Felement.jpg%26720x405.jpg&refer=http%3A%2F%2Fimg.thosefree.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1655738204&t=8e8133473ce45e22e0fcecd8f6533b5c">
            <span> 网站快速成型工具Element，一套为开发者、设计师和产品经理准备的基于 Vue 2.0 的桌面端组件库</span>
        </div>
        <div style="width: 30%;margin-left: 64%;margin-top: -245px">
            <img src="https://img1.baidu.com/it/u=235958363,3369853384&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=212">
            <span> Vue.js是一个轻巧、高性能、可组件化的MVVM库,同时拥有非常容易上手的API</span>
        </div>
        <div style="width: 30%;margin-left: 0%;margin-top: 40px">
            <img src="https://img0.baidu.com/it/u=1895808013,1270057977&fm=253&fmt=auto&app=138&f=JPEG?w=900&h=500">
            <span> 是Java语言中用来规范客户端程序如何来访问数据库的应用程序接口，提供了诸如查询和更新数据库中数据的方法。</span>
        </div>
        <div style="width: 30%;margin-left: 32%;margin-top: -265px">
            <img src="https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fwww.pianshen.com%2Fimages%2F392%2Fc8f82846ea9401c8fbcb4428a7269870.png&refer=http%3A%2F%2Fwww.pianshen.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1656335549&t=6e77d74e6f7882fd4f47b5cb56088de8">
            <span>Axios，是一个基于promise的网络请求库，作用于node.js和浏览器中，它是 isomorphic 的(即同一套代码可以运行在浏览器和node.js中)。</span>
        </div>
    </div>
</template>

<script>
    export default {
        name: "Technology",
        data() {
            return {
                currentDate: new Date()
            };
        }
    }
</script>

<style scoped>
.maxbox{
    width: 100%;
    height: 100%;
}
img{
    width: 100%;
    height: 200px;
}

</style>