import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);


const Index = () => import('views/index/Index');
const BlogEdit = () => import('views/blog/BlogEdit');
const Blog = () => import('views/blog/Blog');
const Login = () => import('views/login/Login');
const SearchResult = () => import('views/searchResult/SearchResult');
const Personal = () => import('views/personal/Personal');
const Article = () => import('views/blog/Article');
const Forum = () => import('views/forum/Forum');
const ForumDetail = () => import('views/forum/ForumDetail');
const NotFount = () => import('views/404/404');
const AdminLogin = () => import('views/admin/AdminLogin');
const AdminMain = () => import('views/admin/AdminMain');

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
    component: Blog,
    meta: {
      hidden: true,
      title:"详情"
    }
  },
  {
    path: '/article',
    component: Article,
    meta: {
      hidden: true,
      title:"博客"
    }
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
    component: Personal,
    meta: {
      hidden: true,
      title:"我的-用户页"
    }
  },
  {
    path: '/forum',
    component: Forum,
    meta: {
      hidden: true,
      title:"论坛"
    }
  },
  {
    path: '/forumDetail/:postId',
    component: ForumDetail,
    meta: {
      hidden: true,
      title:"论坛-查看详情"
    }
  },
  {
    path: '/notFount/404',
    component: NotFount,
    meta: {
      hidden: true,
      title:"404 ~ NotFount！"
    }
  },
  {
    path: '/sysAdmin',
    component: AdminLogin,
    meta: {
      hidden: true,
      title: "管理员登录"
    }
  },
  {
    path: '/admin/main',
    component: AdminMain,
    meta: {
      hidden: true,
      title: "管理界面"
    }
  }

];

export default new Router({
  mode: 'history',
  routes
})
