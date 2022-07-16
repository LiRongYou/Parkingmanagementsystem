<template>
    <div>
        <!-- 查询条件 -->
        <div>
            <el-form :inline="true"
                     :model="queryParam"
                     class="demo-form-inline"
                     ref="queryParam">

                <el-form-item label="用户名"
                              prop="username">
                    <el-input v-model="queryParam.username"
                              placeholder="输入用户名"></el-input>
                </el-form-item>

                <el-form-item label="手机号码"
                              prop="phone">
                    <el-input v-model="queryParam.phone"
                              placeholder="请输入手机号码"></el-input>
                </el-form-item>

                <el-form-item label="车牌号"
                              prop="carid">
                    <el-input v-model="queryParam.carid"
                              placeholder="请输入车牌号"></el-input>
                </el-form-item>

                <el-form-item label="车辆颜色"
                              prop="carcolor">
                    <el-select v-model="queryParam.carcolor"
                               placeholder="选择车辆颜色">
                        <el-option label="白色"
                                   value="白色"></el-option>
                        <el-option label="红色"
                                   value="红色"></el-option>
                        <el-option label="黄色"
                                   value="黄色"></el-option>
                    </el-select>
                </el-form-item>

                <el-form-item label="通行权限"
                              prop="allowpassage">
                    <el-select v-model="queryParam.allowpassage"
                               placeholder="选择车辆通行权限">
                        <el-option label="允许"
                                   value="允许"></el-option>
                        <el-option label="不允许"
                                   value="不允许"></el-option>
                    </el-select>
                </el-form-item>

                <el-form-item>
                    <el-button type="primary"
                               icon="el-icon-search"
                               @click="submitSearch()">查询
                    </el-button>
                </el-form-item>
                <el-form-item>
                    <el-button icon="el-icon-refresh"
                               @click="clearForm('queryParam')">重置
                    </el-button>
                </el-form-item>
                <el-button v-if="more"
                           type="danger"
                           icon="el-icon-delete"
                           @click="updatecarallowpassageMore()">一键修改
                </el-button>
            </el-form>
        </div>

        <!-- 车辆信息列表 -->
        <el-table ref="multipleTable"
                  :data="carList"
                  border
                  @selection-change="handleSelectionChange"
                  style="width: 100%;">

            <el-table-column type="selection"
                             align="center"
                             style="width: 5.8%">
            </el-table-column>

            <!-- 车牌号 -->
            <el-table-column label="车牌号"
                             style="width: 16.6%"
                             prop="carid">
            </el-table-column>

            <!-- 车辆颜色 -->
            <el-table-column label="车辆颜色"
                             style="width: 12.5%"
                             prop="carcolor">
            </el-table-column>

            <!-- 用户名 -->
            <el-table-column label="用户"
                             style="width: 16.6%"
                             prop="username">
            </el-table-column>

            <!-- 手机号码 -->
            <el-table-column label="手机号码"
                             style="width: 16.6%"
                             prop="phone">
            </el-table-column>
            <!-- 是否允许通行 -->
            <el-table-column label="是否允许通行"
                             style="width: 16.6%"
                             prop="allowpassage">
            </el-table-column>


            <el-table-column align="center"
                             label="修改权限"
                             style="width: 16.6%">
                <template slot-scope="scope">
                    <el-button style="margin-left: 10px;"
                               size="mini"
                               type="danger"
                               icon="el-icon-edit"
                               @click="updatecarallowpassage(scope.row.carid)"></el-button>
                </template>
            </el-table-column>
        </el-table>

        <!-- 分页 -->
        <div class="block"
             style="text-align:center;margin-top:10px;">
            <el-pagination background
                           @current-change="handleCurrentChange"
                           :current-page.sync="pageParam.index"
                           :page-size="pageParam.size"
                           :total="pageParam.total"
                           layout="prev, pager, next, jumper">
            </el-pagination>
        </div>

    </div>
</template>

<script>
    import axios from "axios";
    import user from "../../../api/user";
    import car from "../../../api/Car";

    export default {
        name: "CarAccess",
        components: {},
        data() {
            return {
                // 分页
                pageParam: {
                    index: 1,
                    size: 7,
                    total: 0,
                },
                // 数据
                tb: [],
                carList: [],
                queryParam: {
                    carid: '',
                    username: '',
                    phone: '',
                    carcolor: '',
                    allowpassage: '',
                },
                // 一键删除
                more: false,
                select: [],
            }
        },
        mounted() {
            this.getcarlist(this.pageParam.index)
        },
        methods: {
            getcarlist(page) {
                //获取初始化列表
                axios({
                    //向后端发送数据
                    method: "post",
                    url: '/api/CarServlet/carlist',
                }).then(res => {
                    this.pageParam.total=res.data.length
                    //分页功能
                    var list = (page-1)*this.pageParam.size;  //每去一组数据的第一个索引
                    this.carList = res.data.slice(list,list+this.pageParam.size);;
                }).catch(erroe => {
                    //失败后
                    alert("登录信息获取失败");
                })
            },
            handleCurrentChange(val) {
                this.pageParam.index=val
                this.getcarlist(val)
            },
            updatecarallowpassage(carid) {
                this.$confirm('此操作将修改该车辆权限, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning',
                })
                    .then(() => {
                        axios({
                            url: `/api/CarServlet/${carid}`,
                            method: 'get',
                        }).then(res => {
                            if (res.data) {
                                this.$message({
                                    type: 'info',
                                    message: '修改成功',
                                })
                                this.getcarlist(this.pageParam.index)
                            } else {
                                alert("错误")
                            }
                        }).catch(erroe => {
                            //失败后
                            alert("失败");
                        })
                    })
                    .catch(() => {
                        this.$message({
                            type: 'info',
                            message: '已取消修改',
                        })
                    })
            },
            // 一次性删除
            handleSelectionChange(val) {
                if (val.length === 0) {
                    this.more = false
                } else {
                    this.more = true
                }
                this.select = val
            },
            // 查询
            submitSearch() {
                axios({
                    url: `/api/CarServlet/findparkingcarlist`,
                    method: 'post',
                    data:this.queryParam,
                }).then(res => {
                    if(res.data.length>0){
                        this.carList=res.data;
                    }else {
                        this.$message({
                            type: 'info',
                            message: '查无此人',
                        })
                    }
                }).catch(erroe => {
                    //失败后
                    alert("失败");
                })
            },
            // 清空
            clearForm(formName) {
                this.$refs[formName].resetFields()
            },
            updatecarallowpassageMore() {
                this.$confirm('此操作将修改多个内容，是否继续？', '提示', {
                    confirmButtonText: '确定',
                    type: 'warning',
                }).then(() => {
                    let ids = []
                    this.select.forEach((item) => {
                        ids.push(item.carid)
                    })
                    //循环选中的信息，循环调用单个修改函数来达到批量修改
                    for(const carid in ids){
                        // this.updatecarallowpassage(ids[carid])
                        axios({
                            url: `/api/CarServlet/${ids[carid]}`,
                            method: 'get',
                        }).then(res => {
                            if (res.data) {
                                this.getcarlist(this.pageParam.index)
                            } else {
                                alert("错误")
                            }
                        }).catch(erroe => {
                            //失败后
                            alert("失败");
                        })
                    }
                    //拿到外面提示，避免循环提示
                    this.$message({
                        type: 'info',
                        message: '修改成功',
                    })
                })
                    .catch(() => {
                        this.$message({
                            type: 'info',
                            message: '已取消修改',
                        })
                    })
            },
        }
    }
</script>

<style scoped>

</style>