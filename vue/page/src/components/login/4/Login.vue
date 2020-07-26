<template>
    <div class="bg">
        <div class="main">
            <div class="bgi">
                <img src="./login_bg.jpg" alt="">
            </div>
<!--            <div class="login">-->
<!--                <div class="switch">-->
<!--                    <span :class='{"active": active === "login"}' @click='go("login")'>登陆</span>-->
<!--                    <span>/</span>-->
<!--                    <span :class='{"active": active === "register"}' @click='go("register")'>注册</span>-->
<!--                </div>-->
<!--                <div class="form">-->
<!--                    <template v-if='active === "register"'>-->
<!--                        <div class="input"><input type="text" name="email" id='email'/><label for="email">邮箱</label>-->
<!--                        </div>-->
<!--                        <div class="input"><input type="text" name="username" id="regUsername"/><label-->
<!--                                for="regUsername">用户名</label></div>-->
<!--                        <div class="input"><input type="password" name="password" id="regPassword"/><label-->
<!--                                for="regPassword">密码</label></div>-->
<!--                        <div class="input"><input type="password" name="repeat" id="passwordRepeat"/><label-->
<!--                                for="passwordRepeat">重复密码</label></div>-->
<!--                    </template>-->
<!--                    <template v-if='active === "login"'>-->
<!--                        <div class="input"><input type="text" name="username" id="logUsername"/><label-->
<!--                                for="logUsername">用户名</label></div>-->
<!--                        <div class="input"><input type="password" name="password" id="logPassword"/><label-->
<!--                                for="logPassword">密码</label></div>-->
<!--                    </template>-->
<!--                    <span class="forget">忘记?</span>-->
<!--                    <button type="submit">登陆</button>-->
<!--                </div>-->
<!--            </div>-->
        </div>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                active: 'login'
            }
        },
        methods: {
            go(type) {
                this.active = type
            }
        }
    }
</script>


<style lang="scss" scoped>
    @font-face {
        font-family: miaowu;
        src: url('./方正-喵呜.TTF');
    }

    .bg {
        position: absolute;
        top: 0;
        bottom: 0;
        left: 0;
        right: 0;
        display: flex;
        justify-content: center;
        align-items: center;
        font-family: miaowu, serif;
        background: linear-gradient(45deg, rgb(181, 154, 254), rgb(245, 189, 253)) fixed;
        .main {
            .bgi {
                img {
                    width: 70rem;
                }
            }
        }
    }
</style>
