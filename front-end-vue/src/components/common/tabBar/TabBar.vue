<template>
  <div>
    <!-- header -->
    <header class="header-default">
      <nav class="navbar navbar-expand-lg">
        <div class="container-xl">
          <!-- site logo -->
          <a class="navbar-brand" href="index.html">
            <img src="~assets/img/logo/FANG-Logo.svg" alt="logo" class="logo-image"/>
          </a>
          <div class="collapse navbar-collapse">
            <!-- menus -->
            <ul class="navbar-nav mr-auto">
              <li class="nav-item dropdown" :class="{active:isAction==1}">
                <a class="nav-link dropdown-toggle" href="index">Home</a>
                <ul class="dropdown-menu">
                  <li><a class="dropdown-item" @click="goNewPage('/user/')">我的</a></li>
                </ul>
              </li>
<!--              <li class="nav-item">-->
<!--                <a class="nav-link" href="category.html">Lifestyle</a>-->
<!--              </li>-->
<!--              <li class="nav-item">-->
<!--                <a class="nav-link" href="category.html">Inspiration</a>-->
<!--              </li>-->
              <li class="nav-item dropdown" :class="{active:isAction==2}">
                <a class="nav-link dropdown-toggle" href="#">Blog</a>
                <ul class="dropdown-menu">
                  <li><a class="dropdown-item" @click="goNewPage('/blogEdit')">撰写博客</a></li>
                </ul>
              </li>
              <li class="nav-item" :class="{active:isAction==3}">
                <a class="nav-link" href="contact.html">Forum</a>
              </li>
            </ul>
          </div>

          <!-- header right section -->
          <div class="header-right">
            <!-- social icons -->
            <ul class="social-icons list-unstyled list-inline mb-0">
              <li class="list-inline-item"><a href="#"><i class="fab fa-github"></i></a></li>
            </ul>

            <!-- header buttons -->
            <div class="header-buttons">
              <button class="search icon-button" @click="toSearch">
                <i class="icon-magnifier"></i>
              </button>
              <button class="burger-menu icon-button">
                <span class="burger-icon"></span>
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
        <form class="d-flex search-form">
          <input class="form-control me-2" type="search" placeholder="Search and press enter ..." aria-label="Search">
          <button class="btn btn-default btn-lg" type="submit"><i class="icon-magnifier"></i></button>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import {getCookie,getCookieAuthorId} from "common/cookieUtils";

export default {
  name: "TabBar",
  props: {
    isAction: {
      type: Number,
      default: 1
    }
  },
  data() {
    return {
      isShowSearch: false,
      isTextShow: false,
      authorId: '',
    }
  },
  created() {
    this.authorId = getCookieAuthorId();
  },
  methods: {
    toSearch() {
      this.isShowSearch = true;
    },
    cLoseShowSearch() {
      this.isShowSearch = false;
      this.isTextShow = false;
    },

    goNewPage(path){
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
    }
  }
}
</script>

<style scoped>
.logo-image {
  width: 200px;
}
</style>