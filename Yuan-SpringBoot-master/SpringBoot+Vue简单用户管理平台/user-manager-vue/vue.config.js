const path = require('path')
function resolve(dir) {
    return path.join(__dirname, dir)
}
module.exports = {
    publicPath: './', //这个路径根据自己的情况定，默认就填"./",因为我静态资源的路径必须要在static文件夹下，所以才修改的。
    devServer: {  //配置服务器的，添加之后使用npm run dev会自动打开页面
        open: true,
        //转发请求，在main.js里配置了Vue.prototype.$http="http://localhost:8080"就不用配置这里
        proxy: {
            // 这里监听一个别名，下面替换掉
            '/api': {     //这里最好有一个 /
                target: 'http://localhost:8080',  // 后台接口域名
                ws: true,        //如果要代理 websockets，配置这个参数
                secure: false,  // 如果是https接口，需要配置这个参数
                changeOrigin: true,  //是否跨域
                pathRewrite:{
                    '^/api':''  //替换成
                }
            },
        }
    },
    configureWebpack: {
        resolve: {
            alias: {
                '@': resolve('src')
            }
        }
    }
}