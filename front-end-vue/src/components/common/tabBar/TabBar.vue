<template>
  <div>
    <!-- header -->
    <header class="header-default">
      <nav class="navbar navbar-expand-lg">
        <div class="container-xl">
          <!-- site logo -->
          <a class="navbar-brand" href="">
            <router-link target="_blank" :to="{path:'/index'}"><img src="~assets/img/logo/FANG-Logo.svg" alt="logo" class="logo-image"/></router-link>
          </a>
          <div class="collapse navbar-collapse">
            <!-- menus -->
            <ul class="navbar-nav mr-auto">
              <li class="nav-item dropdown" :class="{active:isAction==1}">
                <a class="nav-link dropdown-toggle" @click="goNewPage2('/')">Home</a>
                <ul class="dropdown-menu">
                  <li><a class="dropdown-item" @click="goNewPage1('/user/')">我的</a></li>
                </ul>
              </li>
              <li class="nav-item dropdown" :class="{active:isAction==2}">
                <router-link target="_blank" :to="{path:'/article'}"><a class="nav-link dropdown-toggle">Blog</a></router-link>
                <ul class="dropdown-menu">
                  <li>
                    <router-link target="_blank" :to="{path:'/blogEdit/not_checkout'}"><a class="dropdown-item">撰写博客</a></router-link>
                  </li>
                </ul>
              </li>
              <li class="nav-item" :class="{active:isAction==3}">
                <router-link target="_blank" :to="{path:'/forum'}"><a class="nav-link">Forum</a></router-link>
              </li>
            </ul>
          </div>

          <!-- header right section -->
          <div class="header-right">
            <!-- social icons -->
            <ul class="social-icons list-unstyled list-inline mb-0">
<!--              <li class="list-inline-item"><a href="#"><i class="fab fa-github"></i></a></li>-->
              <li class="list-inline-item" v-if="!(this.avatarPath != null && this.avatarPath != '')">
                <a href=""><router-link :to="{path:'/login'}">登录/注册</router-link></a>
              </li>
              <li class="list-inline-item" v-else>
                <el-badge :value="unReadCount" class="item">
                  <a href=""><router-link :to="{path:'/user/' + authorId}"><Avatar :src="avatarPath" class="avatar"/></router-link></a>
                </el-badge>
              </li>
            </ul>

            <!-- header buttons -->
            <div class="header-buttons">
              <button class="search icon-button" @click="toSearch">
                <i class="icon-magnifier"></i>
              </button>
              <button class="burger-menu icon-button" @click="logout">
<!--                <span class="log-out-icon"></span>-->
                <Icon type="md-log-out" />
              </button>
            </div>
          </div>
        </div>
      </nav>
    </header>
    <!--  搜索框  -->
    <div class="search-popup" :class="{visible:isShowSearch}" @keyup.esc="cLoseShowSearch">
      <!-- close button -->
      <button type="button" class="btn-close" aria-label="Close" @click="cLoseShowSearch"></button>
      <!-- content -->
      <div class="search-content" @keyup.esc="cLoseShowSearch">
        <div class="text-center">
          <h3 class="mb-4 mt-0">Press ESC to close</h3>
        </div>
        <!-- form -->
        <div class="d-flex search-form">
          <input class="form-control me-2" v-model="searchInput" placeholder="Search and press enter ..." @keyup.enter="search"/>
          <button class="btn btn-default btn-lg" @click="search"><i class="icon-magnifier"></i></button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {getCookie, getCookieAuthorId, getCookieAvatarPath} from "common/cookieUtils";
import {BASE_WS_URL} from "common/common_variable";

export default {
  name: "TabBar",
  props: {
    isAction: {
      type: Number,
      default: 1
    },
    unRead: {
      type: String,
      default: '0'
    }
  },
  data() {
    return {
      isShowSearch: false,
      isTextShow: false,
      authorId: '',
      avatarPath: '',
      searchInput: '',
      webSocket: '',
      unReadCount: '0',
    }
  },
  created() {
    this.authorId = getCookieAuthorId();
    this.avatarPath = getCookieAvatarPath();
    this.initWebSocket();
  },
  methods: {
    toSearch() {
      this.isShowSearch = true;
    },
    cLoseShowSearch() {
      this.isShowSearch = false;
      this.isTextShow = false;
    },

    goNewPage1(path){
      if (!getCookie() || this.authorId == undefined || this.authorId == ''){
        this.$router.push("/login");
        return;
      }
      const { href } = this.$router.resolve({
        path: path + this.authorId,
        query: {
        },
      });
      window.open(href, "_blank");
    },
    goNewPage2(path){
      if (!getCookie() || this.authorId == undefined || this.authorId == ''){
        this.$router.push("/login");
        return;
      }
      const { href } = this.$router.resolve({
        path: path,
        query: {
        },
      });
      window.open(href, "_blank");
    },
    logout(){
      console.log("logout");
      if (this.authorId == undefined || this.authorId == ''){
        this.$notify({
          message: '请先登录',
          type: 'warning'
        });
        return ;
      }

      sessionStorage.clear();
      let keys = document.cookie.match(/[^ =;]+(?=\=)/g);
      if(keys) {
        for(let i = keys.length; i--;)
          document.cookie = keys[i] + '=0;expires=' + new Date(0).toUTCString();
      }
      this.$store.commit('checkLogin', false);
    },
    search(){
      const {href} = this.$router.resolve({
        path: "/searchResult/" + this.searchInput,
        query: {},
      });
      window.open(href, "_blank");
    },

    initWebSocket(){
      // console.log(this.authorId);
      if (!this.authorId){
        // console.log(this.authorId);
        return;
      }
      const wsuri = BASE_WS_URL + this.authorId;
      this.webSocket = new WebSocket(wsuri);
      this.webSocket.onmessage = this.websocketOnmessage;
      // this.webSocket.onclose = this.websocketClose;// 断开连接，不处理
      this.webSocket.onopen = this.websocketOpen;
      this.webSocket.onerror = this.websocketError;
    },
    websocketOpen(e){
      console.log("打开连接");
    },
    websocketOnmessage(e){ //数据接收
      this.unReadCount = e.data;
    },
    websocketSend(agentData){//数据发送
      this.webSocket.send(agentData);
    },
    websocketClose(e){  //关闭
      console.log("connection closed (" + e.code + ")");
    },
    websocketError(e){
      this.initWebSocket();
    },
    // threadPoxi(){  // 实际调用的方法
    //   //参数
    //   const agentData = "mymessage";
    //   //若是ws开启状态
    //   if (this.webSocket.readyState === this.websock.OPEN) {
    //     this.websocketSend(agentData)
    //   }
    //   // 若是 正在开启状态，则等待300毫秒
    //   else if (this.webSocket.readyState === this.websock.CONNECTING) {
    //     let that = this;//保存当前对象this
    //     setTimeout(function () {
    //       that.websocketSend(agentData)
    //     }, 300);
    //   }
    //   // 若未开启 ，则等待500毫秒
    //   else {
    //     this.initWebSocket();
    //     let that = this;//保存当前对象this
    //     setTimeout(function () {
    //       that.websocketSend(agentData)
    //     }, 500);
    //   }
    // },
  },
  watch: {
    unRead(n){
      this.unReadCount = n;
    }
  },
  mounted() {
    const chat = this;
    document.addEventListener("visibilitychange", function () {
      if (!document.hidden) {
        //处于当前页面 do something
        chat.initWebSocket();
      }
    });
  }
}
</script>

<style scoped>
.logo-image {
  width: 200px;
}

.avatar:hover {
  width: 50px;
  height: 50px;
  transition: all 500ms;
}
</style>