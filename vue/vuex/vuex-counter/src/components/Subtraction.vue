<template>
    <div>
        <!---->
        <h3>当前最新的数量是【{{count}}】</h3>
        <!--
            <h3>{{showNum}}</h3>
        -->
        <!--
            <button @click="subOne">-1</button>
        -->
        <button @click="sub">-1</button>
        <button @click="subN(3)">-N</button>
        <button @click="subAsync">-1 Async</button>
        <button @click="subNAsync(5)">-N Async</button>
    </div>
</template>

<script>
    import {mapState, mapGetters, mapMutations, mapActions} from 'vuex'

    export default {
        data() {
            return {}
        },
        // 计算属性
        computed: {
            ...mapState(['count']),
            ...mapGetters(['showNum'])
        },
        methods: {
            // 把mutations里面定义的方法，映射为当前组件的一个methods
            ...mapMutations(['sub', 'subN']),
            ...mapActions(['subAsync', 'subNAsync']),
            // 这些写是多余的，可以直接调用sub函数
            subOne() {
                this.sub()
            }
        }
    }
</script>
