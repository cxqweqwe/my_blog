<template>
  <div>
    <TabBar></TabBar>

    <section class="main-content">
      <div class="container-xl">
        <div class="row gy-4">
          <div class="col-lg-8">

            <div v-if="isMy">
              <Tabs>
                <TabPane class="tabber-bottom" label="我的博客" icon="ios-book">
                  <div class="row ">
                    <PickCecommended v-for="(item,index) in personalArticleList" :key="index"
                                     :pickData="item"></PickCecommended>
                  </div>
                  <div class="space"></div>
                  <div class="box-line"></div>
                  <div class="space"></div>
                  <div class="text-center">
                    <button class="btn btn-simple" @click="loadMore()">Load More</button>
                  </div>
                </TabPane>
                <TabPane label="个人信息" icon="ios-person">
                  个人信息
                </TabPane>
                <TabPane label="设置" icon="ios-build">
                  设置
                </TabPane>
              </Tabs>
            </div>
            <div v-else class="a">
              asdb
            </div>

          </div>
          <div class="col-lg-4">
            <Blogger></Blogger>
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
  import TabBar from "components/common/tabBar/TabBar";
  import PostTabs from "components/content/featured/PostTabs";
  import Celebration from "components/content/celebration/Celebration";
  import Blogger from "components/common/ blogger/Blogger";
  import PickCecommended from "components/content/main/PickCecommended";

  import {getPersonalArticle} from "network/article";
  import {getCookie,getCookieAuthorId} from "common/cookieUtils";

  export default {
    name: "Personal",
    components: {
      TabBar,
      PostTabs,
      Celebration,
      Blogger,
      PickCecommended,
    },
    data() {
      return {
        curPage: 1,
        size: 6,
        total: 0,
        authorId: '',
        personalArticleList: [],
        isMy: false,//用于判断是否是自己的页面。默认不是
      }
    },
    created() {
      const authorId = getCookieAuthorId();                     //session存放的id,登录id
      const paramsId = this.$route.params.authorId;             //传送的id，一般是别人的id
      if (paramsId === authorId) {
        this.isMy = true;
      }
      this.authorId = paramsId;
      this.sendPersonalBlog();
    },
    methods: {
      sendPersonalBlog() {
        getPersonalArticle(this.curPage, this.size, this.authorId).then(res => {
          this.personalArticleList = res.data.data;
          this.total = res.data.total;
        })
      },
      loadMore() {
        if (this.curPage * this.size >= this.total) {
          this.$notify({
            title: '消息提醒',
            message: '没有更多的数据了',
            type: 'success'
          });
          return;
        }
        this.curPage++;
        getPersonalArticle(this.curPage, this.size, this.authorId).then(res => {
          // console.log(res);
          this.personalArticleList.push(...res.data.data);
          this.total = res.data.total;
        })
      }
    }
  }
</script>

<style scoped>
  .a {
    height: 600px;
    width: 100%;
    background-color: #0a53be;
  }

  .space {
    height: 30px;
  }

  .box-line {
    border-bottom: 1px solid #f3f3f3;
  }

  .tabber-bottom {
    color: #1c0202;
  }


</style>