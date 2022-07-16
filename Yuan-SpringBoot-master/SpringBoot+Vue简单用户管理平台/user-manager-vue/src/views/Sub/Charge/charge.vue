<template>
    <div class="charge">
    <div class="form" >
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="停车场" prop="name">
            <el-select v-model="ruleForm.region" placeholder="请选择设置折扣的停车场">
                <el-option label="西岸村停车场" value="西岸村停车场"></el-option>
                <el-option label="花江停车场" value="花江停车场"></el-option>
                <el-option label="F区停车场" value="F区停车场"></el-option>
                <el-option label="D区停车场" value="D区停车场"></el-option>
                <el-option label="中央停车场" value="中央停车场"></el-option>
            </el-select>
        </el-form-item>
        <el-form-item label="价格/小时" prop="price">
            <el-input v-model="ruleForm.price" STYLE="width: 220px"></el-input>
        </el-form-item>
        <el-form-item label="折扣" prop="discount">
            <el-select v-model="ruleForm.discount" placeholder="请选择设置折扣">
                <el-option label="不打折" value="1"></el-option>
                <el-option label="七折" value="0.7"></el-option>
                <el-option label="七五折" value="0.75"></el-option>
                <el-option label="八折" value="0.8"></el-option>
                <el-option label="八五折" value="0.85"></el-option>
                <el-option label="九折" value="0.9"></el-option>
                <el-option label="九五折" value="0.95"></el-option>
            </el-select>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="submitForm('ruleForm')">提交设置</el-button>
            <el-button @click="resetForm('ruleForm')">重置</el-button>
        </el-form-item>
    </el-form>
    </div>
    <div class="ridership">
        <ridership style="border: 1px solid darkblue;border-radius: 4px;"></ridership>
    </div>
    <div class="profitbar">
        <profitpie style="border: 1px solid darkblue;border-radius: 4px;"></profitpie>
    </div>
    <div class="dailyprofit">
        <dailyprofit style="border: 1px solid darkblue;border-radius: 4px;"></dailyprofit>
    </div>
    <div class="operating">
        <operating></operating>
    </div>
    </div>
</template>

<script>
    import ridership from "../Charge/ridership";
    import profitpie from "./ProfitPie";
    import dailyprofit from "./Dailyprofit";
    import operating from "./operating";

    export default {
        name: "charge",
        components: {ridership,profitpie,dailyprofit,operating},
        data() {
            return {
                ruleForm: {
                    region: '',
                    price:'',
                    discount:'',

                },
                rules: {
                    region: [
                        { required: true, message: '请选择设置折扣的停车场', trigger: 'change' }
                    ],
                    price: [
                        { required: true, message: '请设置价格', trigger: 'blur' }
                    ],
                    discount: [
                        { required: true, message: '请设置折扣', trigger: 'change' }
                    ]
                }
            };
        },
        methods: {
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        alert('submit!');
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            }
        }

    }
</script>

<style scoped>
.form{
    width: 40%;
}
.ridership{
    width: 55%;
    height: 300px;
    margin-left: 43%;
    margin-top: -260px;
}
.profitbar{
    margin-top: 10px;
    width:40%;
    height: 300px;
}
.dailyprofit{
    margin-top: -300px;
    margin-left: 60%;
    width:40%;
    height: 300px;
}
.operating{
    margin-top: -300px;
    /*margin-left: 33.5%;*/
    /*width:20%;*/
    margin-left: 27.6%;
    width: 45%;
    height: 300px;
 }
</style>