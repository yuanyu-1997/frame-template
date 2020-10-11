// src/util/httpRequest.js
import axios from 'axios'
// mock开关
const mock = true
if (mock) {
    // 记得不要用import，import是预编译，require是需要执行到这里才会加载，不然永远都会拦截
    require('../mock/api')
}
const http = axios.create({
    timeout: 8000,
})
http.defaults.baseURL = '/api';
http.defaults.timeout = 8000
export default http
