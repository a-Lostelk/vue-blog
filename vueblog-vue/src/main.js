import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

/*引入 element.js 文件*/
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

// 富文本编辑器
import mavonEditor from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
Vue.use(mavonEditor)

/*引入Axios*/
import axios from "axios"

import './axios'

Vue.use(ElementUI)
Vue.config.productionTip = false
Vue.prototype.$axios = axios

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
