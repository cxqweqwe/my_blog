import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);


const Index = () => import('../views/index/Index');

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
  }
];

export default new Router({
  mode: 'history',
  routes
})
