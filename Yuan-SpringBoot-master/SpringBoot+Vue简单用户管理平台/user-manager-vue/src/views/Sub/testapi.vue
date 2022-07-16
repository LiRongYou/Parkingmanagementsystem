
<template>
    <div>测试{{name}}
        <el-button type="success">成功按钮</el-button></div>
</template>

<script>

    import axios from "axios";

    export default {
        name: "testapi",
        data() {
            return {
                name:'admin',
            }
        },
        mounted () {
            axios({
                //向后端发送数据
                method: "post",
                url:'/api/TestApiServlet/list',
                data:{
                    name:this.name,
                    age:"20"
                }
            }).then(res=>{
                //成功得到后端返回的数据
                this.name=res.data.name;
                console.log(res);
            }).catch(erroe=>{
                //失败后
                alert("error");})
        },
        mothons: {

            },
    }
</script>

<style >

</style>