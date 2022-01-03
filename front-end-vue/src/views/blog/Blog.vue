<template>
  <div id="blog">
    <TabBar :isAction=isAction></TabBar>

    <section class="main-content">
      <div class="container-xl">
        <div class="row gy-4">
          <div class="col-lg-8">
            <BlogDetail></BlogDetail>
          </div>
          <div class="col-lg-4">
            <PostTabs></PostTabs>
            <div class="space"></div>
            <Celebration></Celebration>

          </div>
        </div>
      </div>
    </section>


  </div>
</template>

<script>
import BlogDetail from "components/content/blog/BlogDetail";
import TabBar from "components/common/tabBar/TabBar";
import PostTabs from "components/content/featured/PostTabs";
import Celebration from "components/content/celebration/Celebration";

export default {
  name: "Blog",
  components: {
    TabBar,
    BlogDetail,
    PostTabs,
    Celebration
  },
  data() {
    return {
      articleId: '',
      isAction: 2
    }
  },
  created() {
    this.articleId = this.$route.params.articleId;
  },

  methods: {}
}
</script>

<style scoped>
.space{
  height: 15px;
}
</style>