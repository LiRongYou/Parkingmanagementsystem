<template>
  <div class="app">
    <el-container style="height: 700px; border: 1px solid #eee">
        <el-aside width="200px"
                  style="background-color: rgb(238, 241, 246)">
            <el-menu :unique-opened="true"  router="router" :default-openeds="[0]">
<!--                子菜单-->
                <el-submenu v-for="(item,index) in $router.options.routes"
                            :key="index"
                            :index="String(index)" v-if="item.meta.menu">
                    <template slot="title"><i :class="item.class"></i>&nbsp;&nbsp;{{item.name}}</template>

                    <el-menu-item v-for="(item1,index1) in item.children"
                                  :key="String(index1)"
                                  :index="item1.path"
                                  v-if="!(item1.hidden)"
                                  :class="$router.path==item1.path?'is-active':''">
                        <i :class="item1.icon"></i>{{item1.name}}</el-menu-item>

                </el-submenu>
            </el-menu>
        </el-aside>

        <el-container>
            <el-header style="text-align: right; font-size: 12px">

                <!--            面包屑-->
                <el-breadcrumb class="bread" style="font-size: 20px" separator-class="el-icon-arrow-right">
                    <el-breadcrumb-item :to="{path:item.path}"  v-for="(item,index) in $route.matched" :key="index">{{item.name}}</el-breadcrumb-item>
                </el-breadcrumb>

            <span style="margin-right: 40%;font-size: 24px;font-weight:700;color: crimson;">&nbsp停&nbsp车&nbsp管&nbsp理&nbsp系&nbsp统</span>
                <el-dropdown>
                    <i class="el-icon-setting"
                       style="margin-right: 15px;"></i>
                    <el-dropdown-menu slot="dropdown">
                        <el-dropdown-item>功能一</el-dropdown-item>
                        <el-dropdown-item>功能二</el-dropdown-item>
                        <el-dropdown-item>功能三</el-dropdown-item>
                    </el-dropdown-menu>
                </el-dropdown>
                <span STYLE="color: #0b5137;font-family: 'DejaVu Math TeX Gyre'">
                    管理员: {{name}}</span>
            </el-header>

            <el-main>
                <router-view />
            </el-main>
        </el-container>
    </el-container>
  </div>
</template>

<script>
import axios from "axios";

export default {
    data() {
        return {
            name:'',
        }
    },
    mounted () {
        axios({
            //向后端发送数据
            method: "post",
            url:'/api/LoginServlet/loginsuername',
        }).then(res=>{
            this.name=res.data;
        }).catch(erroe=>{
            //失败后
            alert("登录信息获取失败");})
    },
}
</script>
<style>
    .bread{
        position: absolute;
        /*background-color: red;*/
        margin: 40px 0;
    }
</style>
