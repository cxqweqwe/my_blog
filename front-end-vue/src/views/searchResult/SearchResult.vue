<template>
  <div>
    <TabBar></TabBar>
    <section class="main-content">
      <div class="container-xl">
        <div class="row gy-4">
          <div class="col-lg-8">
            <ResultContent :tags="tags"></ResultContent>
          </div>
          <div class="col-lg-4">
            <PostTabs></PostTabs>
            <Celebration></Celebration>
          </div>
        </div>
      </div>
    </section>

  </div>
</template>

<script>
import TabBar from "components/common/tabBar/TabBar";
import PostTabs from "components/content/featured/PostTabs";
import Celebration from "components/common/celebration/Celebration";
import ResultContent from "components/content/result/ResultContent";

export default {
  name: "SearchResult",
  components: {
    TabBar,
    PostTabs,
    Celebration,
    ResultContent
  },
  data() {
    return {
      keyword: '',
      tags: [],
    }
  },
  created() {
    // this.keyword = this.$route.params.keyWord;
    this.splitKeyWord();
    this.getSearch();
  },
  methods: {
    getSearch() {
      //发送网络请求
    },
    splitKeyWord() {
      //拆分keyword,后台也是按照这个格式，显示在前端做
      let keyWords = this.keyword.trim().split(/\s+/); // 按空格拆分
      this.tags.push(...keyWords);
    }
  }
}
</script>

<style scoped>
.location {
  display: block;
  float: right;
}
</style>