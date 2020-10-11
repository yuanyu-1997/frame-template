import Mock from 'mockjs'
Mock.mock('/api/user/details',{
    "status": 0,
    "data": {
        "id|1001-11000": 0,
        "username": "@cname",
        "email": "admin@51purse.com",
        "phone": null,
        "role": 0,
        "createTime": 1479048325000,
        "updateTime": 1479048325000
    }
})
