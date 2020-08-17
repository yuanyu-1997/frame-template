

授权页面 

http://127.0.0.1:8000/uaa/oauth2/authorizePage?response_type=code&client_id=QHf5eSFCF8dr0O9gV3mUiiVv&scope=basic&redirect_uri=http://127.0.0.1:2500/xxx-client/user/userIndex



获取Authorization Code 

http://127.0.0.1:8000/uaa/oauth2/authorize?client_id=QHf5eSFCF8dr0O9gV3mUiiVv&scope=basic&response_type=code&state=AB1357&redirect_uri=http://127.0.0.1:2500/xxx-client/login



通过 Authorization Code 获取 Access Token 

http://127.0.0.1:8000/uaa/oauth2/token?grant_type=authorization_code&code=a88c5ead7e09850f5fa8133a50e2954139392065&client_id=QHf5eSFCF8dr0O9gV3mUiiVv&client_secret=OxYIniBPYaoI1b0cHoAHPuwpVQA0daXP&redirect_uri=http://127.0.0.1:2500/xxx-client/login

```json
{
    "access_token": "1.50d0c1e0e4a1ae55a8daf91771ef2f93186e2e44.2592000.1600135736",
    "refresh_token": "2.098693ed64bb31def1b426ed57e950815fb6e704.31536000.1629079736",
    "expires_in": 2592000,
    "scope": "basic"
}
```



通过 Refresh Token 刷新 Access Token 

http://127.0.0.1:8000/uaa/oauth2/refreshToken?refresh_token=2.098693ed64bb31def1b426ed57e950815fb6e704.31536000.1629079736

```json
{
    "access_token": "1.aa41f597b8b41b0e0c6551cc9a2ff8c7d1898446.2592000.1600135815",
    "refresh_token": "2.098693ed64bb31def1b426ed57e950815fb6e704.31536000.1629079736",
    "expires_in": 2592000,
    "scope": "basic"
}
```



通过Access Token获取用户信息 

http://127.0.0.1:8000/uaa/api/users/getInfo?access_token=1.aa41f597b8b41b0e4c6551cc9a2ff8c7d1898446.2592000.1600135815 





