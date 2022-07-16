<template>
    <div class="app">
        <div class="form">
            <el-form :model="coupon"
                     :rules="rules"
                     ref="coupon"
                     label-width="100px"
                     class="demo-ruleForm">

                <el-form-item label="使用限制"
                              prop="tag">
                    <el-select v-model="coupon.tag"
                               placeholder="请选择">
                        <el-option label="一次性"
                                   value="一次性"></el-option>
                        <el-option label="福利券"
                                   value="福利券"></el-option>
                        <el-option label="无限制"
                                   value="无限制"></el-option>
                    </el-select>
                </el-form-item>

                <el-form-item label="优惠券面额"
                              prop="discount">
                    <el-input v-model="coupon.discount" placeholder="请输入优惠券面额"></el-input>
                </el-form-item>

                <el-form-item label="使用门槛"
                              prop="min">
                    <el-input v-model="coupon.min" placeholder="请输入优惠券使用门槛"></el-input>
                </el-form-item>

                <el-form-item label="优惠券名称"
                              prop="name">
                    <el-input v-model="coupon.coupon_name" placeholder="请输入优惠券名称"></el-input>
                </el-form-item>


                <el-form-item label="优惠券描述"
                              prop="desc">
                    <el-input type="textarea"
                              v-model="coupon.coupon_desc" placeholder="请输入对优惠券的描述(如：全场通用)"></el-input>
                </el-form-item>

                <el-form-item label="优惠券期限"
                              prop="days">
                    <el-input type="number" v-model="coupon.days" placeholder="请输入优惠券期限"></el-input>
                </el-form-item>

                <el-form-item>
                    <el-button type="primary"
                               @click="submitForm('coupon')">提交</el-button>
                    <el-button @click="resetForm('coupon')">重置</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<script>
    import axios from "axios";

    export default {
        name: "PublishCoupons",
        data() {
            return {
                status: false,
                coupon: {
                    tag: '',
                    discount: '',
                    min: '',
                    coupon_name: '',
                    coupon_desc: '',
                    days: '',
                    receive:'领取'
                },
                rules: {
                    tag: [
                        { required: true, message: '请选择使用限制', trigger: 'blur' },
                    ],
                    discount: [{ required: true, message: '请输入优惠券面额', trigger: 'blur' }],
                    min: [{ required: true, message: '请输入优惠券使用门槛', trigger: 'blur' }],
                    // password: [{ required: true, message: '请输入密码', trigger: 'blur' }],
                    coupon_name: [{ required: true, message: '请输入优惠券名称', trigger: 'blur' }],
                    coupon_desc: [{ required: true, message: '请输入优惠券描述', trigger: 'blur' }],
                    days: [{ required: true, message: '请输入优惠券期限(只能是数字)', trigger: 'blur' }],
                },
            }
        },
        methods:{
            submitForm(formName) {
                console.log("状态",this.status)
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        if(this.status){
                            axios({
                                //向后端发送数据
                                method: "post",
                                url:'/api/CouponServlet/updateCoupon',
                                data:this.coupon
                            }).then(res=>{
                                if(res.data){
                                    //成功得到后端返回的数据
                                    this.$message({
                                        type: 'info',
                                        message: '修改成功',
                                    })
                                }else {
                                    this.$message({
                                        type: 'info',
                                        message: '修改失败',
                                    })
                                }

                            }).catch(erroe=>{
                                //失败后
                                this.$message({
                                    type: 'info',
                                    message: '修改失败',
                                })
                            })
                        }else {
                        axios({
                            //向后端发送数据
                            method: "post",
                            url:'/api/CouponServlet/publiccoupon',
                            data:this.coupon
                        }).then(res=>{
                            //成功得到后端返回的数据
                            if(res.data){
                                this.$message({
                                    type: 'info',
                                    message: '发布成功',
                                })
                                this.resetForm(formName);
                            }else {
                                this.$message({
                                    type: 'info',
                                    message: '发布失败',
                                })
                            }

                        }).catch(erroe=>{
                            //失败后
                            this.$message({
                                type: 'info',
                                message: '发布失败',
                            })
                        })
                        }
                    } else {
                        alert("请填写完整信息")
                        return false;
                    }
                });
            },
            resetForm(formName) {
                this.$refs[formName].resetFields()
            },
            //检查该页面是否是从列表跳转过来的
            check() {
                if(this.$route.params && this.$route.params.id) {
                    this.status = true;
                    axios({
                        //向后端发送数据
                        method: "post",
                        url: `/api/CouponServlet/GetCouponById`,
                        data:this.$route.params.id,
                    }).then(res => {
                        this.coupon=res.data
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
            this.check();
        },
    }
</script>

<style scoped>
    .form {
        width: 50%;
    }
</style>