import Vue from 'vue'
import VueRouter from 'vue-router'
import login from '../views/login/login.vue'
import blogDetail from '../views/blog/blogDetail.vue'
import blogEdit from '../views/blog/blogEdit.vue'
import blogs from '../views/blog/blogs.vue'
import register from "../views/register/register";

Vue.use(VueRouter)

  //路由相关配置
const routes = [
  {
    //主页面
    path: '/',
    name: 'index',
    redirect: {name: 'blogs'},
  },
  {
    //主页面
    path: '/blogs',
    name: 'blogs',
    component: blogs
  },
  {
    path: '/login',
    name: 'login',
    component: login
  },
  {
    path: '/blog/add',
    name: 'blogEdit',
    component: blogEdit
  },
  {
    path: '/blog/:blogId/edit',
    name: 'blogEdit',
    component: blogEdit
  },
  {
    path: '/blog/:blogId',
    name: 'blogDetail',
    component: blogDetail
  },
  {
    path: '/user/save',
    name: 'register',
    component: register
  },
];

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
