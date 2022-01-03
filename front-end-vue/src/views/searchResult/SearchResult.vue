<template>
  <div>
<!--    <TabBar></TabBar>-->
    <section class="main-content">
      <div class="container-xl">
        <div class="row gy-4">
          <div class="col-lg-8">
            <ResultContent :tags="tags" :searchDataList="searchDataList"></ResultContent>
            <div class="block">
              <el-pagination
                  @size-change="handleSizeChange"
                  @current-change="handleCurrentChange"
                  :current-page.sync="currentPage"
                  :page-size="10"
                  layout="prev, pager, next, jumper"
                  :total="total">
              </el-pagination>
            </div>
          </div>
          <div class="col-lg-4">
            <PostTabs></PostTabs>
            <div class="space"></div>
            <Celebration></Celebration>
          </div>
        </div>
      </div>
    </section>
    <Footer></Footer>
  </div>
</template>

<script>
// import TabBar from "components/common/tabBar/TabBar";
import PostTabs from "components/content/featured/PostTabs";
import Celebration from "components/content/celebration/Celebration";
import ResultContent from "components/content/result/ResultContent";
import {getSearchBlog} from "network/article";
import Footer from "components/common/footer/Footer";

export default {
  name: "SearchResult",
  components: {
    // TabBar,
    PostTabs,
    Celebration,
    ResultContent,
    Footer
  },
  data() {
    return {
      keyword: '',
      tags: [],
      searchDataList: [],

      // 分页插件
      currentPage: 1,
      total: 100,
    }
  },
  created() {
    this.keyword = this.$route.params.keyWord;
    this.splitKeyWord();
    this.getSearch();
  },
  methods: {
    getSearch() {
      //发送网络请求
      getSearchBlog(this.keyword,this.currentPage,10).then(res => {
        console.log(res);
        this.searchDataList = res.data.data;
        this.total = res.data.total
      })
    },
    splitKeyWord() {
      //拆分keyword,后台也是按照这个格式，显示在前端做
      let keyWords = this.keyword.trim().split(/\s+/); // 按空格拆分
      this.tags.push(...keyWords);
    },

    // 分页插件
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
    }
  }
}
</script>

<style scoped>
.location {
  display: block;
  float: right;
}

.space{
  height: 15px;
}

.el-pagination{
  text-align: center;
}
</style>