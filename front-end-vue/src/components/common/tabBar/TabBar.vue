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
              <li class="nav-item dropdown active">
                <a class="nav-link dropdown-toggle" href="index.html">Home</a>
                <ul class="dropdown-menu">
                  <li><a class="dropdown-item" href="index.html">杂志 </a></li>
                  <li><a class="dropdown-item" href="personal.html">个人的 </a></li>
                  <li><a class="dropdown-item" href="personal-alt.html">Personal Alt 个人替代的</a></li>
                  <li><a class="dropdown-item" href="minimal.html">Minimal 最小的</a></li>
                  <li><a class="dropdown-item" href="classic.html">Classic 经典的</a></li>
                </ul>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="category.html">Blog</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="#">Forum</a>
              </li>
              <!--						<li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#">Pages</a>
                            <ul class="dropdown-menu">
                              <li><a class="dropdown-item" href="category.html">Category</a></li>
                              <li><a class="dropdown-item" href="blog-single.html">Blog Single</a></li>
                              <li><a class="dropdown-item" href="blog-single-alt.html">Blog Single Alt</a></li>
                              <li><a class="dropdown-item" href="about.html">About</a></li>
                              <li><a class="dropdown-item" href="contact.html">Contact</a></li>
                            </ul>
                          </li>

                          <li class="nav-item">
                            <a class="nav-link" href="contact.html">Contact</a>
                          </li>-->
            </ul>
          </div>

          <!-- header right section -->
          <div class="header-right">
            <!-- social icons -->
            <ul class="social-icons list-unstyled list-inline mb-0">
              <!--							<li class="list-inline-item"><a href="#"><i class="fab fa-facebook-f"></i></a></li>-->
              <!--							<li class="list-inline-item"><a href="#"><i class="fab fa-twitter"></i></a></li>-->
              <!--							<li class="list-inline-item"><a href="#"><i class="fab fa-instagram"></i></a></li>-->
              <!--							<li class="list-inline-item"><a href="#"><i class="fab fa-pinterest"></i></a></li>-->
              <!--							<li class="list-inline-item"><a href="#"><i class="fab fa-medium"></i></a></li>-->
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
export default {
  name: "TabBar",
  data() {
    return {
      isShowSearch: false,
      isTextShow: false
    }
  },
  created() {

  },
  methods: {
    toSearch() {
      this.isShowSearch = true;
    },
    cLoseShowSearch() {
      this.isShowSearch = false;
      this.isTextShow = false;
    },

  }
}
</script>

<style scoped>
.logo-image {
  width: 200px;
}
</style>