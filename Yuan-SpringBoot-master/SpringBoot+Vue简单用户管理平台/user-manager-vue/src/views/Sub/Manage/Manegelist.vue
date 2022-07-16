<template>
    <div>
        <!-- 查询条件 -->
        <div>
            <el-form :inline="true"
                     :model="queryParam"
                     class="demo-form-inline"
                     ref="queryParam">

                <el-form-item label="管理员"
                              prop="username">
                    <el-input v-model="queryParam.username"
                              placeholder="输入管理员"></el-input>
                </el-form-item>

                <el-form-item label="手机号码"
                              prop="phone">
                    <el-input v-model="queryParam.phone"
                              placeholder="请输入手机号码"></el-input>
                </el-form-item>

                <el-form-item label="身份证号"
                              prop="phone">
                    <el-input v-model="queryParam.idnumber"
                              placeholder="请输入身份证号"></el-input>
                </el-form-item>

                <el-form-item label="性别"
                              prop="sex">
                    <el-select v-model="queryParam.sex"
                               placeholder="选择性别">
                        <el-option label="男"
                                   value="男"></el-option>
                        <el-option label="女"
                                   value="女"></el-option>
                    </el-select>
                </el-form-item>

                <el-form-item label="注册时间"
                              prop="time">
                    <el-date-picker v-model="queryParam.createtime"
                                    type="date"
                                    value-format="yyyy-MM-dd"
                                    placeholder="选择注册时间">
                    </el-date-picker>
                </el-form-item>

                <el-form-item>
                    <el-button type="primary"
                               icon="el-icon-search"
                               @click="submitSearch()">查询</el-button>
                </el-form-item>
                <el-form-item>
                    <el-button icon="el-icon-refresh"
                               @click="clearForm('queryParam')">重置</el-button>
                </el-form-item>
                <el-button v-if="more"
                           type="danger"
                           icon="el-icon-delete"
                           @click="deleteMore()">一键删除</el-button>
            </el-form>
        </div>

        <!-- 管理员信息列表 -->
        <el-table ref="multipleTable"
                  :data="manegeList"
                  border
                  @selection-change="handleSelectionChange"
                  style="width: 100%">

            <el-table-column type="selection"
                             width="70">
            </el-table-column>

            <!-- 编号 -->
            <el-table-column label="编号"
                             width="78"
                             prop="id">
            </el-table-column>

            <!-- 用户名 -->
            <el-table-column label="管理员"
                             width="160"
                             prop="username"
                             header-align="center">
            </el-table-column>

            <!-- 身份证号码 -->
            <el-table-column label="身份证号码"
                             width="170"
                             prop="idnumber">
            </el-table-column>

            <!-- 密码 -->
            <el-table-column label="密码"
                             width="140"
                             prop="password">
            </el-table-column>


            <!-- 手机号码 -->
            <el-table-column label="手机号码"
                             width="170"
                             prop="phone">
            </el-table-column>

            <!-- 性别 -->
            <el-table-column label="性别"
                             width="70"
                             prop="sex">
            </el-table-column>

            <!-- 注册时间 -->
            <el-table-column label="注册时间"
                             width="170"
                             prop="createtime">
            </el-table-column>


            <el-table-column align="center"
                             label="操作"
                             width="160">
                <template slot-scope="scope">
                    <router-link :to="'/editManage/' + scope.row.id">
                        <el-button size="mini"
                                   type="warning"
                                   icon="el-icon-edit"></el-button>
                    </router-link>
                    <el-button style="margin-left: 10px;"
                               size="mini"
                               type="danger"
                               icon="el-icon-delete"
                               @click="deleteUser(scope.row.id)"></el-button>
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

    export default {
        name: "Manegelist",
        data() {
            return {
                // 分页
                pageParam: {
                    index: 1,
                    size: 9,
                    total: 0,
                },
                // 数据
                tb:[],
                manegeList: [],
                queryParam: {
                    username: '',
                    idnumber: '',
                    phone: '',
                    sex: '',
                    createtime: '',
                },
                // 一件删除
                more: false,
                select: [],
            }
        },
        mounted () {
            //获取列表总数
            axios({
                //向后端发送数据
                method: "post",
                url: '/api/ManageServlet/namagelist',
            }).then(res => {
                this.pageParam.total=res.data.length;
            }).catch(erroe => {
                //失败后
                alert("登录信息获取失败");
            });
            this.getdata(1);
        },
        methods: {
            getdata(currentPage){
        //获取列表
        axios({
            //向后端发送数据
            method: "post",
            url: '/api/ManageServlet/pagenamagelist',
            data:currentPage,
        }).then(res => {
            this.manegeList=res.data;
        }).catch(erroe => {
            //失败后
            alert("信息获取失败");
        })},
            //拿到当前页
            handleCurrentChange(val) {
                this.pageParam.index=val;
                this.getdata(val);
            },


            //删除指定的管理员
            deleteUser(id) {
                axios({
                    //向后端发送数据
                    method: "post",
                    url: '/api/ManageServlet/deletenamage',
                    data:id,
                }).then(res => {
                    if(res.data){
                    this.$message({
                        type: 'success',
                        message: '删除成功!',
                    })}else {this.$message({
                        type: 'info',
                        message: '删除失败!',
                    })}
                    this.getdata(this.pageParam.index);//刷新数据
                }).catch(erroe => {
                    //失败后
                    this.$message({
                        type: 'info',
                        message: '已取消删除',
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
            //查询指定的管理员
            submitSearch() {
                axios({
                    //向后端发送数据
                    method: "post",
                    url: '/api/ManageServlet/findnamage',
                    data:this.queryParam,
                }).then(res => {
                    if(res.data.length>0){
                        this.manegeList=res.data;
                    }else {
                        this.$message({
                            type: 'info',
                            message: '查无此人',
                        })
                    }
                }).catch(erroe => {
                    //失败后
                    this.$message({
                        type: 'info',
                        message: '查询失败',
                    })
                })
            },
            // 清空
            clearForm(formName) {
                this.$refs[formName].resetFields()
            },
            //删除多个
            deleteMore() {
                this.$confirm('此操作将删除多个内容，是否继续？', '提示', {
                    confirmButtonText: '确定',
                    type: 'warning',
                }).then(() => {
                    //获得要删除的所有id
                    let ids = []
                    this.select.forEach((item) => {
                        ids.push(item.id)
                    })
                    axios({
                        //向后端发送数据
                        method: "post",
                        url: '/api/ManageServlet/deletemorenamage',
                        data:ids,
                    }).then(res => {
                        if(res.data){
                            this.$message({
                                type: 'success',
                                message: '删除成功!',
                            })
                            this.getdata(this.pageParam.index);//刷新数据
                        } else {this.$message({
                            type: 'info',
                            message: '删除失败!',
                        })}
                    }).catch(erroe => {
                        //失败后
                        this.$message({
                            type: 'info',
                            message: '删除已取消',
                        })
                    })
                })
            },
        },
    }
</script>

<style scoped>

</style>