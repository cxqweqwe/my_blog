import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);


const Index = () => import('../views/index/Index');
const Main = () => import('../components/content/main/Main');

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
    path: '/main',
    name: 'main',
    component: Main
  }
];

export default new Router({
  mode: 'history',
  routes
})
