<template>
    <div class="app">
        <div class="form">
            <el-form :model="manageList"
                     :rules="rules"
                     ref="manageList"
                     label-width="100px"
                     class="demo-ruleForm">

                <el-form-item label="管理员"
                              prop="username">
                    <el-input v-model="manageList.username"></el-input>
                </el-form-item>

                <el-form-item label="身份证号"
                              prop="idnumber">
                    <el-input v-model="manageList.idnumber"></el-input>
                </el-form-item>

                <el-form-item label="密码"
                              prop="password">
                    <el-input v-model="manageList.password"></el-input>
                </el-form-item>

                <el-form-item label="电话"
                              prop="phone">
                    <el-input v-model="manageList.phone"></el-input>
                </el-form-item>

                <el-form-item label="性别"
                              prop="sex">
                    <el-select v-model="manageList.sex"
                               placeholder="请选择性别">
                        <el-option label="男"
                                   value="男"></el-option>
                        <el-option label="女"
                                   value="女"></el-option>
                    </el-select>
                </el-form-item>

                <el-form-item>
                    <el-button type="primary"
                               @click="submitForm('manageList')">提交</el-button>
                    <el-button @click="resetForm('manageList')">重置</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<script>
    import manage from "../../../api/manage";
    import axios from "axios";

    export default {
        name: "AddManage",
        data() {
            return {
                status: false,
                manageList: {
                    username: '',
                    idnumber: '',
                    password: '',
                    phone: '',
                    sex: '',
                },
                rules: {
                    username: [{required: true, message: '请输入管理员', trigger: 'blur' }],
                    idnumber: [{ required: true, message: '请输入身份证号', trigger: 'blur' }],
                    password: [{ required: true, message: '请输入密码', trigger: 'blur' }],
                    phone: [{ required: true, message: '请输入电话', trigger: 'blur' }],
                    sex: [{ required: true, message: '请选择性别', trigger: 'blur' }],
                },
            }
        },
        methods: {
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    //valid 提交是否成功
                    if (valid) {
                        if(this.status) {
                            manage.updateManage(this.manageList).then(_ => {
                                this.$router.push('/manegelist');
                                alert("更新成功");
                            }).catch((err) =>{
                                this.$message({
                                    type: 'success',
                                    message: '更新成功!',
                                })
                                this.$router.push('/manegelist');
                            });//没有这个catch会报Uncaught (in promise) error
                        }else{
                            axios({
                                //向后端发送数据
                                method: "post",
                                url: '/api/ManageServlet/addnamage',
                                data:this.manageList,
                            }).then(res => {
                                if(res.data){
                                    this.$message({
                                        type: 'success',
                                        message: '添加成功!',
                                    })
                                    this.resetForm(formName);
                                }else {
                                    this.$message({
                                        type: 'info',
                                        message: '添加失败!',
                                    })
                                }
                            }).catch(erroe => {
                                //失败后
                                this.$message({
                                    type: 'info',
                                    message: '连接失败',
                                })
                            })
                        }
                    }
                })
            },
            resetForm(formName) {
                this.$refs[formName].resetFields()
            },
            check() {
                if(this.$route.params && this.$route.params.id) {
                    this.status = true;
                    axios({
                        //向后端发送数据
                        method: "get",
                        url: `/api/ManageServlet/${this.$route.params.id}`,
                    }).then(res => {
                        this.manageList=res.data;
                    }).catch(erroe => {
                        //失败后
                        this.$message({
                            type: 'info',
                            message: '连接失败',
                        })
                    })
                }
            }
        },
        created() {
            this.check()
        },
        watch: {
            $route(to, from) {
                this.check()
            }
        }
    }
</script>

<style scoped>
    .form {
        width: 50%;
    }
</style>