const baseUrl = 'http://127.0.0.1:30000/heihei';
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
    loadQrcode();
}


// 登录轮询定时器Id
let isLoginTimerId;
// 二维码过期定时器Id
let isExpireTimerId;
// 二维码是否已过期
let qrcodeExpired = false;

/**
 * 加载登录二维码
 */
function loadQrcode() {
    const url = baseUrl + '/qrcode/create';
    const params = {};
    const idx = layer.load();
    $.ajax({
        type: 'post',
        url: url,
        timeout: 5000,
        data: params,
        dataType: "json",
        success: function (response) {
            if ('2000' === response.code) {
                layer.close(idx);
                console.log(JSON.stringify(response, null, 2))
                const qrcodeInfo = response.data;
                const qrcodeId = qrcodeInfo.qrcodeId;
                const qrcodeImgUrl = qrcodeInfo.qrcodeImgUrl;
                $("#qrcodeImg").attr("src", qrcodeImgUrl);
                $("#qrcodeId").val(qrcodeId);
                // 1.启动对应的二维码是否已登录的验证
                isLoginTimerId = setInterval(qrcodeIsLogined, 3000);
                // 2.设置二维码过期时间
                isExpireTimerId = setTimeout(qrcodeIsExpired, 60000);
                qrcodeExpired = false;
            } else {
                layer.close(idx);
                layer.msg(response.msg, {icon: 5});
            }
        },
        error: function (msg) {
            layer.close(idx);
            layer.msg("网络异常，请稍后重试...", {icon: 5});
        }
    });
}

/**
 * 轮询二维码是否已登录
 */
function qrcodeIsLogined() {
    const qrcodeId = $("#qrcodeId").val();
    const url = baseUrl + '/qrcode/isLogined';
    $.ajax({
        type: 'post',
        url: url,
        timeout: 5000,
        data: {
            "qrcodeId": qrcodeId
        },
        dataType: "json",
        success: function (response) {
            // 用户已经登陆
            if ('2000' === response.code) {

                clearInterval(isLoginTimerId);
                clearTimeout(isExpireTimerId);
                window.location.href = baseUrl + `/index?token=${response.data.accessToken}`;
            }
            // 用户未登录
            else {
                console.log(response)
            }
        },
        error: function (msg) {
            layer.msg("网络异常，请稍后重试...", {icon: 5});
            clearInterval(isLoginTimerId);
            clearTimeout(isExpireTimerId);
        }
    });
}

/**
 * 二维码过期设置
 */
function qrcodeIsExpired() {
    $("#qrcodeImg").attr("src", "./images/qrcode-expire.png");
    qrcodeExpired = true;
    clearInterval(isLoginTimerId);
    clearTimeout(isExpireTimerId);
}

// qrcodeLogin('张三');
/**
 * 模拟二维码登录
 */
window.qrcodeLogin = function (userId) {
    const qrcodeId = $("#qrcodeId").val();
    const url = baseUrl + "/qrcode/login";
    $.ajax({
        type: 'post',
        url: url,
        timeout: 5000,
        data: {
            "qrcodeId": qrcodeId,
            "userId": userId
        },
        dataType: "json",
        success: function (response) {
            if ('2000' === response.code) {
                layer.msg(response.msg, {icon: 1});
            } else {
                layer.msg(response.msg, {icon: 5});
            }
        },
        error: function (msg) {
            layer.msg("网络异常，请稍后重试...", {icon: 5});
            clearInterval(isLoginTimerId);
            clearTimeout(isExpireTimerId);
        }
    });
}






















