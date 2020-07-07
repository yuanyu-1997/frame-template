<template>
    <div class="login-container">
        <el-form class="login-form"
                 ref="loginForm"
                 :model="loginForm"
                 :rules="loginRules"
                 auto-complete="on"
                 label-position="left">
            <div class="title-container">
                <h3 class="title">管理员登录</h3>
            </div>
            <el-form-item prop="username">
                <span class="svg-container">
                  <svg t="1594034005028" class="icon" viewBox="0 0 1024 1024" version="1.1"
                       xmlns="http://www.w3.org/2000/svg" p-id="2379" width="16" height="16"><path
                          d="M642.481791 482.725972c84.945902-44.994536 131.595229-128.916045 131.595229-225.918276C774.15582 115.204925 656.508109 0 511.910957 0 367.235004 0 249.587294 115.204925 249.587294 256.807695c0 97.081031 42.551751 180.92374 127.497653 225.918276C184.104877 536.782455 40.21692 724.167757 40.21692 933.380531c0 43.418546 3.073182 89.673875 97.711427 90.38307 84.473105 0.630396 573.739438-0.236399 748.280416 0 77.459946 0.0788 97.159831-45.073336 97.553828-90.38307C985.81138 726.058946 835.777059 536.782455 642.481791 482.725972z"
                          p-id="2380" fill="#889AA4"></path></svg>
                </span>
                <el-input v-model="loginForm.username" name="username" type="text" tabindex="1" auto-complete="on" placeholder="管理员账户"/>
            </el-form-item>
            <el-form-item prop="password">
                <span class="svg-container">
                  <svg t="1594034233474" class="icon" viewBox="0 0 1024 1024" version="1.1"
                       xmlns="http://www.w3.org/2000/svg" p-id="3771" width="16" height="16"><path
                          d="M785.7 360.1H724v-75.4c0-58.4-22.7-113.3-64-154.5-41.3-41.3-96.2-64-154.5-64s-113.3 22.7-154.5 64c-41.3 41.3-64 96.2-64 154.5v75.4h-61.8c-56.1 0-101.8 45.7-101.8 101.8v396.6c0 56.1 45.7 101.8 101.8 101.8h560.7c56.1 0 101.8-45.7 101.8-101.8V461.9c-0.1-56.2-45.8-101.8-102-101.8z m-434.8-75.4c0-85.2 69.3-154.6 154.6-154.6S660 199.5 660 284.7v75.4H350.9v-75.4z m472.7 573.8c0 20.9-17 37.8-37.8 37.8H225.1c-20.9 0-37.8-17-37.8-37.8V461.9c0-20.9 17-37.8 37.8-37.8h560.7c20.9 0 37.8 17 37.8 37.8v396.6z"
                          p-id="3772" fill="#889AA4"></path><path
                          d="M505.4 566.3c-17.7 0-32 14.3-32 32v123.8c0 17.7 14.3 32 32 32s32-14.3 32-32V598.3c0-17.7-14.3-32-32-32z"
                          p-id="3773" fill="#889AA4"></path></svg>
                </span>
                <el-input v-model="loginForm.password" :type="passwordType" name="password" auto-complete="on" tabindex="2" show-password placeholder="管理员密码" @keyup.enter.native="handleLogin"/>
            </el-form-item>
            <el-button :loading="loading" type="primary" style="width:100%;margin-bottom:30px;" @click.native.prevent="handleLogin">登录
            </el-button>
        </el-form>
    </div>
</template>

<script>
    // 图标 889AA4
    // https://element.eleme.cn/#/zh-CN/component/form
    // https://gitee.com/linlinjava/litemall
    // http://122.51.199.160:8080/#/login
    export default {
        name: 'Login',
        data() {
            /**
             * 密码格式校验
             * @param rule
             * @param value 用户输入的值
             * @param callback
             */
            const validatePassword = (rule, value, callback) => {
                if (value.length < 3) {
                    callback(new Error('管理员密码长度应大于3'))
                } else {
                    callback()
                }
            }
            return {
                loginForm: {
                    username: 'admin',
                    password: 'admin'
                },
                loginRules: {
                    username: [{required: true, message: '管理员账户不允许为空', trigger: 'blur'}],
                    password: [
                        {required: true, message: '管理员密码不允许为空', trigger: 'blur'},
                        {validator: validatePassword, trigger: 'blur'}
                    ]
                },
                passwordType: 'password',
                loading: false
            }
        },
        methods: {
            handleLogin() {
                alert()
            }
        }
    }
</script>

<style lang="scss">
    $bg: #283443;
    $light_gray: #fff;
    $cursor: #fff;

    /* 重置 element-ui 的 css */
    .login-container {
        .el-input {
            display: inline-block;
            height: 47px;
            width: 85%;

            input {
                background: transparent;
                border: 0;
                -webkit-appearance: none;
                border-radius: 0;
                padding: 12px 5px 12px 0;
                color: $light_gray;
                height: 47px;
                caret-color: $cursor;

                &:-webkit-autofill {
                    box-shadow: 0 0 0 1000px $bg inset !important;
                    -webkit-text-fill-color: $cursor !important;
                }
            }
        }

        .el-form-item {
            border: 1px solid rgba(255, 255, 255, 0.1);
            background: rgba(0, 0, 0, 0.1);
            border-radius: 5px;
            color: #454545;
        }
    }
</style>
<style lang="scss" scoped>
    $bg: #2d3a4b;
    $dark_gray: #889aa4;
    $light_gray: #eee;

    .login-container {
        position: absolute;
        width: 100%;
        height: 100%;
        background-color: $bg;
        overflow: hidden;

        .login-form {
            width: 520px;
            padding: 160px 35px 0;
            margin: 0 auto;
            overflow: hidden;

            .title-container {
                .title {
                    font-size: 26px;
                    color: $light_gray;
                    margin: 0 auto 40px auto;
                    text-align: center;
                    font-weight: bold;
                }
            }

            .svg-container {
                padding: 6px 5px 6px 15px;
                color: $dark_gray;
                vertical-align: middle;
                width: 30px;
                display: inline-block;
                /*svg {*/
                /*    width: 15px;*/
                /*    height: 15px;*/
                /*}*/
            }
        }
    }

</style>

