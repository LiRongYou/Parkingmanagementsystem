
import axios from "axios";

export default {
    updatecarallowpassage(carid) {
        axios({
            url: `/api/CarServlet/${carid}`,
            method: 'get',
        })
    },
}