<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>index.jsp</title>
</head>
<body>

<script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.5.1/jquery.js"></script>
<script src="https://cdn.bootcdn.net/ajax/libs/layer/3.1.1/layer.js"></script>
<script type="text/javascript">
    //
    console.log("虚拟路径 =>" + "<%=request.getContextPath()%>");
    //
    console.log("服务器名字（localhost/ip/域名） =>" + "<%=request.getContextPath()%>");
    //
    console.log("端口 =>" + "<%=request.getServerPort()%>");
    //
    console.log("协议 =>" + "<%=request.getScheme()%>");
    //
    // localhost:8084
    let bashpath = "<%=request.getServerName()+":"+request.getServerPort()+ request.getContextPath()%>";

    console.log('bashpath => ' + bashpath)
</script>

<script type="text/javascript">

    let websocket = null;

    function login() {
        const name = prompt("请输入您的名字");
        const idx = layer.load();
        $.ajax({
            type: 'post',
            url: '/emm-cgi/login/' + name,
            timeout: 5000,
            async: false, // 默认是异步的
            dataType: 'text',
            success: function (data) {
                layer.close(idx);
                console.log('登陆成功 => ' + data)
            },
            error: function (msg) {
                layer.close(idx);
                console.log('err => ' + JSON.stringify(msg, null, 2));
            }
        });
    }

    function conn() {
        // https://www.baidu.com/

        // https:
        const protocol = window.location.protocol;
        // www.xxx.com:port
        const host = window.location.host;

        // ws 协议
        let agreement;
        if (protocol === 'https:') {
            agreement = "wss://";
        } else {
            agreement = "ws://";
        }

        if ('WebSocket' in window) {
            //websocket = new WebSocket("ws://localhost:15736/emm-cgi/websocket/qrcodelogin");
            websocket = new WebSocket(agreement + host + '/emm-cgi/websocket/qrcodelogin');
        } else {
            throw new Error("Not support websocket");
        }
        if (websocket) {
            /**
             * 连接成功建立的回调方法
             * @param event
             */
            websocket.onopen = function (event) {
                layer.msg('和服务端成功建立连接.');
            }
            /**
             * 接收到消息的回调方法
             * @param event
             */
            websocket.onmessage = function (event) {
                window.ev = event;
                console.log("接收到服务端消息 => " + event.data);
            }

            /**
             * 关闭事件
             */
            websocket.onclose = function () {
                layer.msg('websocket已关闭.');
            };
            /**
             * 发生了错误事件
             */
            websocket.onerror = function () {
                layer.msg('websocket发生了错误.');
            }
        }
    }

    login();
    conn();

</script>


</body>
</html>