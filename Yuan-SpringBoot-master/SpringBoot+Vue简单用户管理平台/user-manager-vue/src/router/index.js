import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Login from "../views/Login" //登陆
import Register from "../views/Register"; //注册

Vue.use(VueRouter);
const routes = [
    {
        //第一个进入的页面
        path: '/',
        name: 'Login',
        component: Login,
        meta:{
            title:'登录',
            menu:false//表识是否为菜单
        },
    },
    {
        path: '/Register',
        name: 'Register',
        component: Register,
        meta:{
            title:'注册',
            menu:false
        },
    },

    {
        path: "/home",
        name: "系统介绍",
        component: Home,
        class: 'iconfont icon-parking_syetem',
        meta:{
            menu:true
        },
        // 第一个菜单的子菜单路由
        children: [
            {
                path: "/home",
                name: "首页",
                // icon: 'el-icon-s-custom',//添加子菜单图标
                meta: {
                    menu: true,
                },
                component: () => import("../views/Sub/HomePage")
            },
            {
                path: "/technology",
                name: "技术介绍",
                // icon: 'el-icon-s-custom',//添加子菜单图标
                meta: {
                    menu: true,
                },
                component: () => import("../views/Sub/Technology/Technology")
            },
        ]
    },
    // 第一个菜单
    {
        path: "/home",
        name: "用户管理",
        component: Home,
        class: ' iconfont icon-yonghuguanli-yonghuguanli',
        meta:{
            menu:true
        },
        // 第一个菜单的子菜单路由
        children: [
            {
                path: "/userlist",
                name: "用户列表",
                // icon: 'el-icon-s-custom',//添加子菜单图标
                meta: {
                    menu: true,
                },
                component: () => import("../views/UserInfoList.vue")
            },
            {
                path: "/addUser",
                name: "添加用户",
                component: () => import("../views/addUser.vue")
            },
            {
                path: "/editUser/:id",
                name: "编辑用户",
                component: () => import("../views/addUser.vue"),
                hidden: true //隐式路由
            },
            {
                path: "/userInfo",
                name: "用户信息",
                component: () => import("../views/UserInfo.vue")
            }
        ],
    },
    {
        path: "/home",
        name: "管理员管理",
        component: Home,
        class: ' iconfont icon-guanliyuan',
        meta:{
            menu:true
        },
        // 第一个菜单的子菜单路由
        children: [
            {
                path: "/manegelist",
                name: "管理员列表",
                // icon: 'el-icon-s-custom',//添加子菜单图标
                meta: {
                    menu: true,
                },
                component: () => import("../views/Sub/Manage/Manegelist")
            },
            {
                path: "/addmanage",
                name: "添加管理员",
                // icon: 'el-icon-s-custom',//添加子菜单图标
                meta: {
                    menu: true,
                },
                component: () => import("../views/Sub/Manage/AddManage")
            },{
                path: "/editManage/:id",
                name: "编辑管理员",
                component: () => import("../views/Sub/Manage/AddManage"),
                hidden: true //隐式路由
            },
        ]
    },
    {
        path: "/home",
        name: "优惠券管理",
        component: Home,
        class: ' iconfont icon-a-bochetingchetingchechang',
        meta:{
            menu:true
        },
        // 第一个菜单的子菜单路由
        children: [
            {
                path: "/publishcoupons",
                name: "发布优惠券",
                // icon: 'el-icon-s-custom',//添加子菜单图标
                meta: {
                    menu: true,
                },
                component: () => import("../views/Sub/Coupon/PublishCoupons")
            },
            {
                path: "/couponlist",
                name: "优惠券管理",
                component: () => import("../views/Sub/Coupon/CouponList")
            },{
                path: "/editCoupon/:id",
                name: "修改优惠券",
                component: () => import("../views/Sub/Coupon/PublishCoupons"),
                hidden: true //隐式路由
            },
        ],
    },
    // 第二个菜单
    {
        path: "/home",
        name: "停车场管理",
        component: Home,
        class: 'iconfont icon-tingchechangguanli',
        meta:{
            menu:true
        },
        // 第二个菜单的子菜单路由
        children: [
            {
                path: "/Parking",
                name: "车位管理",
                component: () => import("../views/Sub/Parking/Parking")
            },
            {
                path: "/Charge",
                name: "收费管理",
                component: () => import("../views/Sub/Charge/charge")
            },{
                path: "/video",
                name: "监控管理",
                component: () => import("../views/Sub/Video/videoShow")
            },
        ],
    },
    {
        path: "/home",
        name: "车辆管理",
        component: Home,
        class: 'iconfont icon-cheliang-',
        meta:{
            menu:true
        },
        // 第二个菜单的子菜单路由
        children: [
            {
                path: "/caraccess",
                name: "车辆出入",
                component: () => import("../views/Sub/CarManage/CarAccess")
            },
            {
                path: "/Map",
                name: "自动定位",
                component: () => import("../views/Sub/AutoPosition/coordinate")
            },{
                path: "/Testapi",
                name: "自动引导",
                component: () => import("../views/Sub/AutoPosition/map")
            },
        ],
    },
    {
        path: "/home",
        name: "系统管理",
        component: Home,
        class: 'iconfont icon-Vector-1',
        meta:{
            menu:true
        },
        // 第二个菜单的子菜单路由
        children: [
            {
                path: "/",
                name: "退出登录",
            },
            {
                path: "/Register",
                name: "注册新账号",
            },
        ],
    },
]

const router = new VueRouter({
    mode: 'history', // 去掉url中的#
    base: process.env.BASE_URL,
    modules:{},
    routes
})
//动态设置浏览器标题  路由守卫
router.beforeEach((to, form, next) => {
    if (to.meta.title) {
        document.title = to.meta.title
    } else {
        document.title = '停车管理系统' //此处写默认的title
    }
    next()
})


export default router

