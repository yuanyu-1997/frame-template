const webpath = 'http://127.0.0.1:30000/heihei';
// 获取的二维码内容
let code;

// 二维码过期定时器
let timer;
// 二维码是否过期
let qrCodeTimeOut = false;
// 当前用户标识
let sessionid;
// 和服务端通信的websocket
let websocket;
// 二维码过期时间
const qrCodeExpireTime = 200000;
$(function () {
    // 用户输入错误提示
    const inputs = document.getElementsByTagName('input');
    const tips = document.getElementsByClassName('tips');

    function InputNull(a) {
        tips[a].classList.add('show');
        tips[a].classList.remove('hidden');
    }

    function InputNoNull(a) {
        tips[a].classList.add('hidden');
        tips[a].classList.remove('show');
    }

    const UserName = inputs[0];
    const UserPwd = inputs[1];
    const AuthCode = inputs[2];
    const UserNameTips = tips[0];
    const UserPwdTips = tips[1];
    const AuthCodeTips = tips[2];
    UserName.oninput = function () {
        if (UserName.value === '') {
            InputNull(0);
            UserNameTips.innerHTML = "请检查用户名，用户名不能为空";
        } else {
            InputNoNull(0);
        }
    }
    UserName.onblur = function () {
        if (UserName.value === '') {
            InputNull(0);
            UserNameTips.innerHTML = "请检查用户名，用户名不能为空";

        }
    }

    UserPwd.oninput = function () {
        if (UserPwd.value === '') {
            InputNull(1);
            UserPwdTips.innerHTML = "请检查您的密码，密码不能为空";
        } else {
            InputNoNull(1);
        }
    }
    UserPwd.onblur = function () {
        if (UserPwd.value === '') {
            InputNull(1);
            UserPwdTips.innerHTML = "请检查您的密码，密码不能为空";
        }
    }

    AuthCode.oninput = function () {
        if (AuthCode.value === '' || AuthCode.value !== '666666') {
            InputNull(2);
            AuthCodeTips.innerHTML = "请检查验证码，验证码错误";
        } else if (AuthCode.value === '666666') {
            InputNull(2);
            AuthCodeTips.innerHTML = "验证码正确";
        } else {
            InputNoNull(2);
        }
    }
    AuthCode.onblur = function () {
        if (AuthCode.value === '') {
            InputNull(2);
            AuthCodeTips.innerHTML = "请检查验证码，验证码错误";
        }
    }
});
function passWorldLogin() {
    const login = document.getElementsByClassName("login_con");
    login[0].classList.remove("hidden");
    login[0].classList.add("show");
    login[1].classList.remove("show");
    login[1].classList.add("hidden");
    const tags = document.getElementsByClassName("top_tag");
    tags[0].classList.add("active");
    tags[1].classList.remove("active");
    document.querySelector("#adimg").style.height = "550px";
}

/**
 * 二维码登陆
 */
function qrCodeLogin() {
    const login = document.getElementsByClassName("login_con");
    login[0].classList.remove("show");
    login[0].classList.add("hidden");
    login[1].classList.remove("hidden");
    login[1].classList.add("show");
    const tags = document.getElementsByClassName("top_tag");
    tags[1].classList.add("active");
    tags[0].classList.remove("active");
    document.querySelector("#adimg").style.height = "520px";

    const idx = layer.load();
    // 获取二维码参数
    $.ajax({
        type: 'post',
        url: `${webpath}/qrcodelogin/getloginuuid.do`,
        timeout: 5000,
        dataType: 'json',
        success: function (data) {
            layer.close(idx);
            if (data.code === '2000') {
                console.log("二维码参数 => " + JSON.stringify(data, null, 2));
                code = data;
                document.querySelector("#qrcodeImg").innerHTML = ''
                const qrcode = new QRCode(document.querySelector("#qrcodeImg"), {
                    width: 140,
                    height: 140
                });
                qrcode.makeCode(data.loginurl);
                // 会话标识（后面请求需要带上）
                sessionid = data.sessionid;
                $("#qrcodeId").val(data.sessionid);
                connectWebSocket();
                timer = window.setTimeout(qrCodeTimeOutNotify, qrCodeExpireTime);
            }
        },
        error: function (msg) {
            layer.close(idx);
            layer.msg("网络异常，请稍后重试...", { icon: 5 });
        }
    });
}

//-----------------------------------------------------------------------------//


/**
 * 连接websocket服务
 */
function connectWebSocket() {
    let socketScheme;
    if (document.location.protocol === "http:") {
        socketScheme = "ws://";
    }
    if (document.location.protocol === "https:") {
        socketScheme = "wss://";
    }
    if ('WebSocket' in window) {
        websocket = new WebSocket(socketScheme + '127.0.0.1:30000/heihei' + "/websocket/socketServer.do");    // WebSocket对应的地址
    } else if ('MozWebSocket' in window) {
        websocket = new MozWebSocket(socketScheme + '127.0.0.1:30000/heihei' + "/websocket/socketServer.do");    // WebSocket对应的地址
    } else {
        websocket = new SockJS('http://' + '127.0.0.1/heihei' + "/sockjs/socketServer.do");                   // SockJS对应的地址
    }
    websocket.onopen = onOpen;
    websocket.onmessage = onMessage;
    websocket.onerror = onError;
    websocket.onclose = onClose;
}

function onOpen(openEvt) {
    console.log('和服务器建立连接...');
}

/**
 * 接收服务器的通知
 */
function onMessage(evt) {
    const data = $.parseJSON(evt.data);
    console.log('收到服务器消息 => ', JSON.stringify(data, null, 2));
    if (data.code === '2000') {
        // 1 二维码过期  2 二维码被扫描  3登录成功
        switch (data.msgtype) {
            case 1: {   // 二维码过期通知成功
                layer.msg('二维码过期', { icon: 1 });
                break;
            }
            case 2: {   // 二维码被扫描
                layer.msg('二维码被扫描', { icon: 1 });
                break;
            }
            case 3: {   // 登录成功
                layer.msg('登录成功', { icon: 1 });
                break;
            }
        }
    }
}

function onError() {
    console.log('发送异常...');
}

function onClose() {
    console.log('连接关闭...');
}

/**
 * 二维码过期通知服务器
 */
function qrCodeTimeOutNotify() {
    console.log('二维码已过期...');
    qrCodeTimeOut = true;
    const params = {
        msgtype: 1, // 二维码过期
        sessionid: sessionid
    }
    if (websocket.readyState == websocket.OPEN) {
        const msg = JSON.stringify(params);              // "msttype=1,sessionid=123344545";
        websocket.send(msg);                            // 调用后台handleTextMessage方法
    } else {
        layer.msg("连接失败", { icon: 5 });
    }
}



//  这里是app解析二维码去执行的，我这里手动模拟 //
/**
 * 二维码被扫描，用户未确认登录
 * @param username 用户名
 */
window.scan = function (username) {
    $.ajax({
        type: 'post',
        url: code.loginurl,
        timeout: 5000,
        dataType: 'json',
        data: {
            "type": 1, // 二维码被扫描
            "username": username,
            "code": code.sessionid,
            "state": code.sessionid
        },
        success: function (data) {
            if (data.code === '2000') {
                console.log("res => " + JSON.stringify(data, null, 2));
            }
        }
    });
}
/**
 * 用户确认登录
 * @param username 用户名
 */
window.confirm = function (username) {
    $.ajax({
        type: 'post',
        url: code.loginurl,
        timeout: 5000,
        dataType: 'json',
        data: {
            "type": 2, // 用户确认登录
            "username": username,
            "code": code.sessionid,
            "state": code.sessionid
        },
        success: function (data) {
            if (data.code === '2000') {
                console.log("res => " + JSON.stringify(data, null, 2));
            }
        }
    });
}
// scan('yuanyu');
// confirm('yuanyu');
