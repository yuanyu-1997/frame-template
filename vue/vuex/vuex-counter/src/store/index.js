import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  // 全局共享的数据
  state: {
    count: 0
  },
  // 只有 mutations 中定义的函数，才有权利修改 state 中的数据
  mutations: {
    /**
     * +1
     * @param state 全局的数据对象
     */
    add(state) {
      // TODO 不要在 mutations 函数中，执行异步操作
      // setTimeout(() => {
      //   state.count++
      // }, 1000)
      state.count++
    },
    /**
     * +n
     * @param state 全局数据对象
     * @param step +多少
     */
    addN(state, step) {
      state.count += step
    },
    sub(state) {
      state.count--
    },
    subN(state, step) {
      state.count -= step
    }
  },
  // Action用于处理异步任务
  actions: {
    /**
     * 延迟一秒后 +1
     * @param context
     */
    addAsync(context) {
      setTimeout(() => {
        // 在 actions 中，不能直接修改 state 中的数据；
        // 必须通过 context.commit() 触发某个 mutation 才行
        context.commit('add')
      }, 1000)
    },
    /**
     * 延迟一秒后 +n
     * @param context
     * @param step
     */
    addNAsync(context, step) {
      setTimeout(() => {
        context.commit('addN', step)
      }, 1000)
    },
    subAsync(context) {
      setTimeout(() => {
        context.commit('sub')
      }, 1000)
    },
    subNAsync(context, step) {
      setTimeout(() => {
        context.commit('subN', step)
      }, 1000)
    }
  },
  // Getter 用于对 Store 中的数据进行加工处理形成新的数据
  getters: {
    showNum(state) {
      return '当前最新的数量是（' + state.count + '）'
    }
  }
})
