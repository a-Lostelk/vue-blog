import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

// 相当于全局变量
export default new Vuex.Store({
  state: {
    token: '',
    userInfo: JSON.parse(sessionStorage.getItem('userInfo'))
  },
  mutations: {
    // setter
    SET_TOKEN: (state, token) => {
      state.token = token
      localStorage.setItem('token', token);
    },
    SET_USER_INFO: (state, userInfo) => {
      state.userInfo = userInfo
      sessionStorage.setItem('userInfo', JSON.stringify(userInfo))
    },
    // 移除方法
    REMOVE_INFO: state => {
      state.token = ''
      state.userInfo = {}
      localStorage.setItem('token', '')
      sessionStorage.setItem('userInfo', JSON.stringify(''))
    }
  },
  getters: {
    //getters
    getUser: state => {
      return state.userInfo
    }
  },
  actions: {
  },
  modules: {
  }
})
