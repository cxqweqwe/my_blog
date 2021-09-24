<template>
  <div id="blog">
    <TabBar></TabBar>


    <section class="main-content">
      <div class="container-xl">
        <div class="row gy-4">
          <BlogDetail></BlogDetail>
          <PostTabs></PostTabs>


        </div>
      </div>
    </section>


  </div>
</template>

<script>
import BlogDetail from "components/content/blog/BlogDetail";
import TabBar from "components/common/tabBar/TabBar";
import PostTabs from "components/content/featured/PostTabs";

export default {
  name: "Blog",
  components: {
    TabBar,
    BlogDetail,
    PostTabs
  },
  data() {
    return {
      articleId: ''
    }
  },
  created() {
    this.articleId = this.$route.params.articleId;
  },

  methods: {}
}
</script>

<style scoped>

</style>