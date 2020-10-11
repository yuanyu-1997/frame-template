// src/util/httpRequest.js
import axios from 'axios'
const http = axios.create({
    timeout: 8000,
})
http.defaults.baseURL = 'https://easy-mock.com/mock/5f7e075546477f19b2848a6d/api';
http.defaults.timeout = 8000
export default http
