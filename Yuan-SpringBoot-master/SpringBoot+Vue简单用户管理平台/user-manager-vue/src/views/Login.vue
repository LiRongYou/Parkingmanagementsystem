<template>
  <div class="container">
    <div class="main">
        <!-- 整个注册盒子 -->
        <h1 style="color:red;margin-left: 40%">欢迎登录倾城伤逝的系统</h1>
      <div class="loginbox">
          <!-- 左侧的注册盒子 -->
          <div class="loginbox-in">
          <div class="userbox"> 
           <span class="iconfont">&#xe817;</span>
           <input  class="user" id="user"   v-model="name" placeholder="用户名">
           </div>
          <br>
          <div class="pwdbox">
            <span class="iconfont">&#xe775;</span>
           <input  class="pwd" id ="password" type="password"  v-model="pwd" placeholder="密码">
           </div>
          <br>
              <div class="pwdbox">
                  <i class="el-icon-key"></i>
                  <input  style="width: 30%" v-model="inputcode" placeholder="验证码">
              </div>
              <div class="code" @click="refreshCode">
                  <!--   ：fresh：父组件向子组件传送刷新验证码的信号；
                     @makedCode：子组件向父组件传送验证码的值-->
                  <s-identify :fresh="flag" @makedCode="getMakedCode"></s-identify>
              </div>
                  <br>

          <div class="log-box">
            <div class="log-box-text"> 忘记密码</div>
           <button type="primary"  class="login_btn" @click="login">Login</button>
           </div>
           
           <br>
           <div class="warn">@倾城伤逝</div>
           <button type="primary"  class="register_btn" @click="register">若无账号请点击注册</button>
     </div>
 
        <!-- 右侧的注册盒子 -->
         <div class="background">
            <div class="title">Welcome to WH System Management Center</div>
        </div>

      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import SIdentify from './Sub/Code/identify'
export default {
  name: "Login",
    data() {
        return {
            name:'',
            pwd:'',
            flag:true,  //该值变化，就会触发刷新
            code:'',  //刷新后的验证码
            inputcode:'',//输入得二维码
        }
    },
    components : {
        SIdentify,
    },
    mounted () {
        this.flag = !this.flag;
    },
  methods:{
    // 切换验证码
      refreshCode() {
          this.flag = !this.flag;
      },
      getMakedCode(code){
          this.code = code
      },

      //注册
    register(){  this.$router.push("Register")},

    login() {
          //检验输入得验证码是否跟图片符合
        if (this.code != this.inputcode) {
            alert("验证码错误！")
        } else {
        axios({
            //向后端发送数据
            method: "post",
            url: '/api/LoginServlet/login',
            data: {
                username: this.name,
                password: this.pwd,
            }
        }).then(res => {
            //成功得到后端返回的数据
            if (res.data) {
                this.$router.push("Home");
            } else {
                alert("用户名或密码错误，请重新输入");
            }
        }).catch(erroe => {
            //失败后
            alert("登录信息传送失败");
        })
    }
    },

  },
   
   
}
</script>

<style scoped>
.loginbox{
    display:flex;
    position:absolute;
    width:800px;
    height:400px;
    top:40%;
    left:50%;
    transform:translate(-50%,-50%);
    box-shadow: 0 12px 16px 0  rgba(0,0,0,0.24), 0 17px 50px 0 #4E655D;
}
.loginbox-in{
     background-color:#89AB9E;
     width:240px;
}
.userbox{
    margin-top:120px ;
    height:30px;
     width:230px;
     display: flex;
     margin-left:25px;
}
.pwdbox{ 
    height:30px;
    width:225px;
    display: flex;
    margin-left:25px;
}

.background{
    width:570px;
    background-image:url('./img/Christmas_Trees.png');
    background-size:cover;
    font-family:sans-serif;
}
.title{
    margin-top:320px;
    font-weight:bold;
    font-size:20px;
    color:#4E655D;
    
}
.title:hover{
     font-size:21px;
     transition: all 0.4s ease-in-out;
     cursor: pointer;
}

input{
    outline-style: none ;
    border: 0;
    border-bottom:1px solid #E9E9E9;
    background-color:transparent;
    height:20px;
     font-family:sans-serif;
    font-size:15px;
    color:#445b53;
    font-weight:bold;
}
 /* input::-webkit-input-placeholder{
    color:#E9E9E9;
 } */
input:focus{
    border-bottom:2px solid #445b53;
    background-color:transparent;
     transition: all 0.2s ease-in;
     font-family:sans-serif;
    font-size:15px;
     color:#445b53;
     font-weight:bold;
     
}
input:hover{
    border-bottom:2px solid #445b53;
    background-color:transparent;
     transition: all 0.2s ease-in;
     font-family:sans-serif;
    font-size:15px;
     color:#445b53;
     font-weight:bold;
     
}
 
input:-webkit-autofill {
  /* 修改默认背景框的颜色 */
 box-shadow: 0 0 0px 1000px  #89AB9E inset !important;
 /* 修改默认字体的颜色 */
 -webkit-text-fill-color: #445b53;
} 

input:-webkit-autofill::first-line {
  /* 修改默认字体的大小 */
 font-size: 15px;
 /* 修改默认字体的样式 */
 font-weight:bold;
 }
.log-box{
    font-size:12px;
    display: flex;
    justify-content: space-between ;
    width:190px;
    margin-left:30px;
    color:#4E655D;
    margin-top:-5px;
    align-items: center;
   
}
.log-box-text{
    color:#4E655D;
    font-size:12px;
      text-decoration:underline;
    }
.login_btn{
    background-color: #5f8276; /* Green */
    border: none;
    color: #FAFAFA;
    padding: 5px 22px;
    text-align: center;
    text-decoration: none;
    font-size: 13px;
    border-radius: 20px;
    outline:none;
}
.login_btn:hover{
    box-shadow: 0 12px 16px 0 rgba(0,0,0,0.24), 0 17px 50px 0 rgba(0,0,0,0.19);
    cursor: pointer;
     background-color: #0b5137;
      transition: all 0.2s ease-in;
}

.warn{
    margin-bottom: 10%;
    /* margin-right:120px; */
    margin-left:40px;
    /*margin-bottom: 5px;*/
     font-weight:bold;
    font-size:17px;
}

.register_btn{
    background-color: transparent; /* Green */
    border: none;
    text-decoration: none;
    font-size: 12px;
    /* border-radius: 20px;   */
     color:#4E655D;
    font-size:12px;
    text-decoration:underline;
    display: flex;
    margin-left:25px;
    outline:none;
    
}
.register_btn:hover{
    font-weight:bold;
    cursor: pointer;
}
@font-face {
    font-family: "iconfont";
    src: url('./font/iconfont.eot');
    src: url('./font/iconfont.eot?#iefix') format('embedded-opentype'), /* IE6-IE8 */
    url('./font/iconfont.woff2') format('woff2'), url('./font/iconfont.woff') format('woff'), url('./font/iconfont.ttf') format('truetype'), /* chrome, firefox, opera, Safari, Android, iOS 4.2+ */
    url('./font/iconfont.svg#iconfont') format('svg');
}

.iconfont {
    font-family: "iconfont" !important;
    font-size: 20px;
    font-style: normal;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    height:22px;
    color:#4E655D;
    margin-right:10px;
    margin-top:3px;
}
.el-icon-key{
    font-size: 20px;
    font-style:normal;
    height:22px;
    color:#4E655D;
    margin-right:10px;
    margin-top:3px;
}


.icon-key:before {
    content: "\e775";
}

.icon-account:before {
    content: "\e817";
}
/*验证码*/
.code {
    /*margin: 400px auto;*/
    /*position: absolute;*/
    margin-top: -16%;
    float: right;
    width: 114px;
    height: 40px;
    /*border: 1px solid red;*/
}


</style>