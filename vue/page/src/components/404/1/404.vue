<template>
    <div class="bg">
        <div class="main">
            <div class="item img">
                <img src="./404.svg" alt="404">
            </div>
            <div class="item desc">
                <div class="code">404</div>
                <div class="msg">你查看的页面貌似丢失了呢...</div>
                <div class="action"><a href="#">返回首页</a> | <a href="#">返回上一页</a></div>
            </div>
        </div>
    </div>
</template>

<script>
    // https://www.w3cplus.com/css/min-max-clamp-function.html
    // https://yuanyu.blog.csdn.net/article/details/107173337
    export default {
        data() {
            return {}
        },
        methods: {}
    }
</script>


<style lang="scss" scoped>

    .bg {
        position: absolute;
        width: 100%;
        height: 100%;
        display: flex;
        justify-content: center;
        align-items: center;
        background-color: rgba(223, 223, 255, 0.39);

        .main {
            width: 80%;
            min-width: 600px;
            max-width: 1000px;
            max-height: 500px;
            background-color: white;
            font-size: 0;
            border-radius: 20px;
            box-shadow: 0 0 50px 0 rgba(146, 146, 146, 0.63);

            .item {
                display: inline-block;
                vertical-align: middle;
                width: 50%;
            }

            .img {
                background-color: rgba(253, 216, 168, 0.692);
                border-top-left-radius: 20px;
                border-bottom-left-radius: 20px;
            }

            .desc {
                .code {
                    font-size: clamp(150px, 20vw, 200px);
                    font-family: 'Arial Narrow',serif;
                    color: rgb(86, 86, 253);
                    font-weight: bolder;
                    text-align: center;
                }

                .msg {
                    font-size: 18px;
                    text-align: center;
                    font-weight: 200;
                    margin-bottom: 20px;
                }

                .action {
                    font-size: 15px;
                    font-weight: 200;
                    text-align: center;
                    cursor: pointer;
                    a {
                        color: red;
                    }
                }
            }
        }

    }
</style>
