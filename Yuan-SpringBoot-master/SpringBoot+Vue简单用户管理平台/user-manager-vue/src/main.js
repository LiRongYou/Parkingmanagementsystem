import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementUI from 'element-ui';
import axios from 'axios';
import 'element-ui/lib/theme-chalk/index.css';
// iconfont 引入阿里矢量图标库
import './views/font/停车管理系统矢量图标库/iconfont.css'
import VueVideoPlayer from 'vue-video-player' // 视频播放器
import 'video.js/dist/video-js.css'
import BaiduMap from 'vue-baidu-map'


Vue.use(VueVideoPlayer)

Vue.use(ElementUI);
Vue.prototype.$axios = axios
Vue.config.productionTip = false
Vue.use(BaiduMap, {
  // ak 是在百度地图开发者平台申请的密钥 详见 http://lbsyun.baidu.com/apiconsole/key */
  ak: 'f6cN3pNUiXtGYvs5jcnDbhUw1l1SkiEq'})


new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
