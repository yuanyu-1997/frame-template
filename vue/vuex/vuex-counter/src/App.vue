<template>
    <div>
        <addition></addition>
        <hr>
        <subtraction></subtraction>
    </div>
</template>

<script>
    // https://cn.vuejs.org/
    // https://vuex.vuejs.org/zh/
    // 加法组件
    import Addition from './components/Addition.vue'
    // 减法组件
    import Subtraction from './components/Subtraction.vue'

    export default {
        data() {
            return {}
        },
        components: {
            'addition': Addition,
            'subtraction': Subtraction
        }
    }
</script>

<style>

</style>
