import request from '../utils/ManageRequest'

export default {
    updateManage(manage) {
        return request({
            url: `/ManageServlet`,
            method: 'put',
            data: manage
        })
    },
}