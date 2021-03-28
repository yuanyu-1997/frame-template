import http from '../../utils/httpRequest.js'

export function policy() {
    return new Promise((resolve, reject) => {
        alert('policy')
        http({
            url: http.adornUrl('/oss/policy'),
            method: 'get',
            params: http.adornParams({})
        }).then(({data}) => {
            resolve(data)
        })
    })
}
