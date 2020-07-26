<template>
    <div class="main">
        <div class="content">
            <div class="info">
                <h2 class="title">后台管理系统</h2>
                <p class="desc">该系统基于vue、element-ui构建开发。实现了权限管理、按钮的动态显示、SQL监控等...</p>
            </div>
            <div class="login">
                <h3 class="title">管理员登录</h3>
                <el-form :model="loginForm"
                         :rules="dataRule"
                         ref="dataForm"
                         @keyup.enter.native="login()"
                         status-icon>
                    <el-form-item prop="username">
                        <el-input v-model="loginForm.username" placeholder="帐号"></el-input>
                    </el-form-item>
                    <el-form-item prop="password">
                        <el-input v-model="loginForm.password" type="password" placeholder="密码"></el-input>
                    </el-form-item>
                    <el-form-item prop="captcha">
                        <el-row :gutter="20">
                            <el-col :span="14">
                                <el-input v-model="loginForm.captcha" placeholder="验证码"></el-input>
                            </el-col>
                            <el-col class="captcha" :span="10">
                                <img :src="captchaPath" @click="getCaptcha()" alt="">
                            </el-col>
                        </el-row>
                    </el-form-item>
                    <el-form-item>
                        <el-button class="loginBtn" type="primary" @click="login()">登录</el-button>
                    </el-form-item>
                </el-form>
            </div>
        </div>
    </div>
</template>

<script>
    // https://element.eleme.cn/#/zh-CN
    // https://gitee.com/renrenio/renren-fast-vue
    // https://yuanyu.blog.csdn.net/article/details/107156398
    export default {
        data() {
            return {
                loginForm: {
                    username: '',
                    password: '',
                    uuid: '',
                    captcha: ''
                },
                dataRule: {
                    username: [
                        {required: true, message: '帐号不能为空', trigger: 'blur'}
                    ],
                    password: [
                        {required: true, message: '密码不能为空', trigger: 'blur'}
                    ],
                    captcha: [
                        {required: true, message: '验证码不能为空', trigger: 'blur'}
                    ]
                },
                captchaPath: ''
            }
        },
        created() {
            this.getCaptcha()
        },
        methods: {
            login() {
                alert()
            },
            //
            getCaptcha() {
                this.loginForm.uuid = this.getUUID()
                //const url =  'http://localhost:1000/renren-fast/'
                const url = 'http://demo.open.renren.io/renren-fast-server/'
                this.captchaPath = url + `/captcha.jpg?uuid=${this.loginForm.uuid}`
            },
            getUUID() {
                return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, c => {
                    return (c === 'x' ? (Math.random() * 16 | 0) : ('r&0x3' | '0x8')).toString(16)
                })
            }
        }
    }
</script>


<style lang="scss" scoped>
    .main {
        position: absolute;
        top: 0;
        right: 0;
        bottom: 0;
        left: 0;
        background-color: rgba(38, 50, 56, .6);
        overflow: hidden;

        &:before {
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            z-index: -1;
            content: "";
            background-image: url(./login_bg.jpg);
            background-size: cover;
        }

        .content {
            min-height: 100%;
            min-width: 500px;
            padding: 30px 500px 30px 30px;

            .info {
                margin: 220px 100px 0 90px;
                color: #fff;

                > .title {
                    margin: 0 0 22px 0;
                    font-size: 48px;
                    font-weight: 400;
                    text-transform: uppercase;
                }

                > .desc {
                    margin: 10px 0;
                    font-size: 16px;
                    line-height: 1.58;
                    opacity: .6;
                }
            }

            .login {
                position: absolute;
                top: 0;
                right: 0;
                padding: 150px 60px 180px;
                width: 350px;
                min-height: 100%;
                background-color: #fff;

                > .title {
                    margin-bottom: 20px;
                    font-size: 16px;
                }

                .captcha {
                    overflow: hidden;

                    > img {
                        width: 100%;
                        vertical-align: middle;
                        cursor: pointer;
                    }
                }

                .loginBtn {
                    width: 100%;
                    margin-top: 38px;
                }
            }
        }

    }
</style>
