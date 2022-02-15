<template>
  <div>
    <TabBar :isAction="3"></TabBar>
    <section class="main-content">
      <div class="container-xl">
        <div class="row gy-4">
          <div class="col-lg-8">
            <div class="col-md-12 col-sm-6">
              <div class="details-main">
                <Row>
                  <Col span="12">
                    <div class="div-text">
                      <a>
                        <router-link target="_blank" :to="{path:'/blog/'}"><img
                            src="https://avatars.githubusercontent.com/u/22723262?s=48&v=4"
                            class="author avatar" alt="用户头像"/> 张大炮
                        </router-link>
                      </a>
                    </div>
                  </Col>
                  <Col span="12">
                    <div class="div-text">发布时间</div>
                  </Col>
                </Row>
                <Row>
                  <Col span="24">
                    <div class="div-text wrap">
                      <h4>
                        sdfaklhgjnbfkdahbiufsdhnbiudsganijdsaNViudfaniusdfaklhgjnbfkdahbiufsdhnbiudsganijdsaNViudfaniusdfaklhgjnbfkdahbiufsdhnbiudsganijdsaNViudfaniu</h4>
                    </div>
                  </Col>
                </Row>
                <Row>
                  <Col span="24">
                    <div class="div-text wrap size20">
                      ufsdhnbiudsganijdsaNViudfaniusdfaklhgjnbfkdahbiufsdhnbiuufsdhnbiudsganijdsaNViudfaniusdfaklhgjnbfkdahbiufsdhnbiuufsdhnbiudsganijdsaNViudfaniusdfaklhgjnbfkdahbiufsdhnbiu
                    </div>
                  </Col>
                </Row>
                <div id="details-img">
                  <ul>
                    <li class="li-inline zoom-in" v-for="(imgPath,index) of imageList">
                      <img :src="imgPath" :alt="'描述辅助图片' + index" style="width: 100px; height: 100px">
                    </li>
                  </ul>
                </div>
                <div class="space"></div>
                <div class="div-text">
                  <!--评论区-->
                  <div class="">
                    <Row>
                      <Col flex="45px">
                        <div class="div-text height50 pad-top">
                          <img src="https://avatars.githubusercontent.com/u/22723262?s=48&v=4" class="author avatar"
                               alt="用户头像"/>
                        </div>
                      </Col>
                      <Col flex="auto">
                        <div class="div-text height50">
                          <Input v-model="commentInput" size="large" placeholder="请明智发表评论..."/>
                        </div>
                      </Col>
                    </Row>
                  </div>
                </div>
              </div>
            </div>
            <div class="space"></div>
            <Footer></Footer>
          </div>
          <div class="col-lg-4">
            <div :class="{side:isSide}">
              <Blogger></Blogger>
              <PostTabs></PostTabs>
              <div class="space"></div>
              <Celebration></Celebration>
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
  import Viewer from 'viewerjs';
  import 'viewerjs/dist/viewer.css';

  import TabBar from "components/common/tabBar/TabBar";
  import PostTabs from "components/content/featured/PostTabs";
  import Celebration from "components/content/celebration/Celebration";
  import Blogger from "components/common/ blogger/Blogger";
  import Footer from "components/content/footer/Footer";

  export default {
    name: "ForumDetail",
    components: {
      TabBar,
      PostTabs,
      Celebration,
      Blogger,
      Footer
    },
    data() {
      return {
        postId: '',
        imageList: [
          "http://www.sinaimg.cn/dy/slidenews/21_img/2015_17/2236_4146071_705561.jpg",
          "http://www.sinaimg.cn/dy/slidenews/21_img/2015_17/2236_4146072_346494.jpg",
        ],
        commentInput: '',
        isSide: false,

      }
    },
    created() {
      this.postId = this.$route.params.postId;
      this.sendToGetPostInfo();
    },
    methods: {
      sendToGetPostInfo() {
        
      },
      sendComment() {

      }

    },
    mounted() {
      const ViewerDom = document.getElementById('details-img');
      this.viewer = new Viewer(ViewerDom, {
        // 相关配置项,详情见下面
        rotatable: false,
      });
    }
  }
</script>

<style scoped>
  .space {
    height: 30px;
  }

  .details-main {
    padding: 5px;
    /*min-height: 550px;*/
    /*height: 1000px;*/
    border: 1px solid #ebebeb;
    border-radius: 10px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04)
  }

  .side {
    height: 100vh;
    overflow: auto;
  }

  .side::-webkit-scrollbar {
    /*滚动条整体部分，可以设置宽度啥的*/
    width: 4px;
  }

  .side::-webkit-scrollbar-track {
    /*外层轨道*/
    /*-webkit-box-shadow: inset 0 0 5px rgba(76, 67, 68, 0.2);*/
    /*border-radius: 0;*/
    /*background: rgba(20, 20, 20, 0.2);*/
  }

  .side::-webkit-scrollbar-thumb {
    /*滚动的滑块*/
    border-radius: 10px;
    box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.2);
    background: rgba(26, 26, 26, 0.3);
  }

  .div-text {
    padding: 5px;
  }

  .wrap {
    word-break: break-all;
    word-wrap: break-spaces;
  }

  .size20 {
    font-size: 20px;
  }

  .avatar {
    width: 30px;
    height: 30px;
    border-radius: 30px
  }

  .li-inline {
    display: inline;
    list-style-type: none;
    padding: 5px 5px;
  }

  .zoom-in {
    cursor: zoom-in;
  }

  a {
    color: #515a6e;
  }

  a:hover {
    color: #fe6877;
  }

  .height50 {
    height: 50px;
  }

  .pad-top {
    padding-top: 10px;
  }
</style>