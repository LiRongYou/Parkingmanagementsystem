<template>
    <el-table
            :data="tableData"
            border
            style="width: 100%"
            :row-class-name="tableRowClassName"
            :header-cell-style="headStyle"
            :cell-style="rowStyle">
        <el-table-column
                prop="tag"
                label="类型"
                width="180">
        </el-table-column>
        <el-table-column
                prop="discount"
                label="面额"
                width="180">
        </el-table-column>
        <el-table-column
                prop="min"
                label="门槛"
                width="180">
        </el-table-column>
        <el-table-column
                prop="coupon_name"
                label="名称"
                width="180">
        </el-table-column>
        <el-table-column
                prop="coupon_desc"
                label="描述">
        </el-table-column>
        <el-table-column
                prop="days"
                label="期限">
        </el-table-column>
        <el-table-column align="center"
                         label="操作"
                         width="200">
            <template slot-scope="scope">
                <router-link :to="'/editCoupon/' + scope.row.id">
                <el-button size="mini"
                           type="warning"
                           icon="el-icon-edit">修改</el-button>
                </router-link>
                <el-button style="margin-left: 10px;"
                           size="mini"
                           type="danger"
                           icon="el-icon-delete"
                           @click="deleteUser(scope.row.id)">下架</el-button>
            </template>
        </el-table-column>
    </el-table>
</template>

<script>
    import axios from "axios";

    export default {
        name: "CouponList",
        data() {
            return {
                tableData: [],
            }
        },
        methods: {
            //设置隔行高亮颜色
            tableRowClassName({row, rowIndex}) {
                if (rowIndex % 2 === 1) {
                    return 'warning-row';
                } else {
                    return 'success-row';
                }
                // return '';
            },
            headStyle() {
                return "text-align:center"//表头居中
            },
            rowStyle() {
                return "text-align:center"//表格居中
            },
            getcouponlist() {
                axios({
                    //向后端发送数据
                    method: "post",
                    url: '/api/CouponServlet/getcouponlist',
                }).then(res => {
                    //成功得到后端返回的数据
                    // console.log(res)
                    this.tableData = res.data
                }).catch(erroe => {
                    //失败后
                    this.$message({
                        type: 'info',
                        message: '发布失败',
                    })
                })
            },
            deleteUser(id) {
                this.$confirm('此操作将下架改优惠券, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning',
                })
                    .then(() => {
                        axios({
                            //向后端发送数据
                            method: "post",
                            url: '/api/CouponServlet/DeleteCouponById',
                            data: id
                        }).then(res => {
                            //成功得到后端返回的数据
                            if (res.data) {
                                this.$message({
                                    type: 'info',
                                    message: '下架成功',
                                })
                                this.getcouponlist();
                            } else {
                                this.$message({
                                    type: 'info',
                                    message: '下架失败',
                                })
                            }
                        }).catch(erroe => {
                            //失败后
                            this.$message({
                                type: 'info',
                                message: '下架失败',
                            })
                        })
                    })
                    .catch(() => {
                        this.$message({
                            type: 'info',
                            message: '已取消下架',
                        })
                    })
            },
        },
        created() {
            this.getcouponlist();
        },
    }
</script>

<style>
    .el-table {
        color: black;
    }

    .el-table .warning-row {
        background: beige;
    }

    .el-table .success-row {
        background: bisque;
    }
</style>

<style scoped>

</style>