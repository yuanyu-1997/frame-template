

```
https://www.cnblogs.com/mafly/p/websocket.html
```




```
location /websocket {
    proxy_pass http://ip:port;
    proxy_http_version 1.1;
    proxy_set_header Upgrade $http_upgrade;
    proxy_set_header Connection "upgrade";
} 
```



nginx.conf

- http://localhost:58443/
- http://localhost:15736/emm-cgi/login
- http://localhost:58443/emm-cgi/login

```
worker_processes  1;
events {
    worker_connections  1024;
}
http {
    include       mime.types;
    default_type  application/octet-stream;
    sendfile        on;
    keepalive_timeout  65;
    server {
        listen       58443;
        server_name  localhost;
        location /emm-cgi {
			proxy_http_version 1.1;
			proxy_read_timeout 3600s;
			proxy_set_header Upgrade $http_upgrade;
			proxy_set_header Connection "upgrade";
			proxy_pass http://127.0.0.1:15736/emm-cgi;
        }
        error_page   500 502 503 504  /50x.html;
        location = /50x.html {
            root   html;
        }
    }
}
```


