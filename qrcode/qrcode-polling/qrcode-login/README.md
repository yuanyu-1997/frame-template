



```bash
docker run \
--name redis_qrcode \
-p 6300:6379 \
-di redis
```



在二维码登陆页面，打开谷歌控制台，模拟扫码操作 **qrcodeLogin('张三');**

