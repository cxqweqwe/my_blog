import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);


const Index = () => import('views/index/Index');
const BlogEdit = () => import('views/blog/BlogEdit');
const BlogDetail = () => import('views/blog/BlogDetail');

const routes = [
  {
    path: '/',
    name: 'index',
    redirect: '/index'
  },
  {
    path: '/index',
    name: 'index',
    component: Index
  },
  {
    path: '/blogEdit',
    component: BlogEdit
  },
  {
    path: '/blogDetail',
    component: BlogDetail
  }
];

export default new Router({
  mode: 'history',
  routes
})
