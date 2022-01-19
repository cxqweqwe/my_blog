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
    redirect: '/index',
    meta: {
      hidden: true,
      title:"fangweb的首页"
    }
  },
  {
    path: '/index',
    component: Index,
    meta: {
      hidden: true,
      title:"fangweb的首页"
    }
  },
  {
    path: '/blogEdit/:md',
    component: BlogEdit,
    meta: {
      hidden: true,
      title:"撰写博客"
    }
  },
  {
    path: '/blog/:articleId',
    component: Blog
  },
  {
    path: '/login',
    component: Login,
    meta: {
      hidden: true,
      title:"请您登录"
    }
  },
  {
    path: '/searchResult/:keyWord',
    component: SearchResult,
    meta: {
      hidden: true,
      title:"查找博客"
    }
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
