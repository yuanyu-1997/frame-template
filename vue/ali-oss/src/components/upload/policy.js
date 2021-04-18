import http from '../../utils/httpRequest.js'

export function policy(request) {
    return new Promise((resolve, reject) => {
        http({
            url: http.adornUrl('thirdParty/aliOss/policy'),
            data: http.adornData(request),
            method: 'post',
        }).then(({data}) => {
            resolve(data)
        })
    })
}
