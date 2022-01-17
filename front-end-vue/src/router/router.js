import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);


const Index = () => import('views/index/Index');
const BlogEdit = () => import('views/blog/BlogEdit');
const Blog = () => import('views/blog/Blog');
const Login = () => import('views/login/Login');
const SearchResult = () => import('views/searchResult/SearchResult');
const Personal = () => import('views/personal/Personal')

const routes = [
  {
    path: '/',
    redirect: '/index'
  },
  {
    path: '/index',
    component: Index
  },
  {
    path: '/blogEdit/:md',
    component: BlogEdit
  },
  {
    path: '/blog/:articleId',
    component: Blog
  },
  {
    path: '/login',
    component: Login
  },
  {
    path: '/searchResult/:keyWord',
    component: SearchResult
  },
  {
    path: '/user/:authorId',
    component: Personal
  },

];

export default new Router({
  mode: 'history',
  routes
})
