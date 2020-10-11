let baseURL

console.log('当前环境=' + process.env.NODE_ENV)

switch (process.env.NODE_ENV) {
    case 'development':
        baseURL = 'http://dev.bug.com/api';
        break
    case 'test':
        baseURL = 'http://test.bug.com/api';
        break
    case 'production':
        baseURL = 'http://prod.bug.com/api';
        break
    default:
        baseURL = 'http://www.bug.com/api';
        break
}

export default {
    baseURL
}
