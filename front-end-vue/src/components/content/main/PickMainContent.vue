<template>
  <div>
    <section class="main-content">
      <div class="container-xl">
        <div class="row gy-4">
          <div class="col-lg-8">
            <!-- section header -->
            <div class="section-header">
              <h3 class="section-title">Editor’s Pick</h3>
              <img src="~assets/img/wave.svg" class="wave" alt="wave"/>
            </div>

            <div class="padding-30 rounded bordered">
              <div class="row gy-5">
                <div class="col-sm-6">
                  <PickCecommended :pick-data="pickData"/>
                </div>
                <div class="col-sm-6">
                  <PickCecommendedItem v-for="(item,index) in pickDataList" :key="index" :item="item"/>
                </div>
              </div>
            </div>

            <div class="space"></div>

            <!-- horizontal ads -->
            <!-- 广告位 -->
<!--            <div class="ads-horizontal text-md-center bottom-space-30">-->
<!--              <span class="ads-title">- Sponsored Ad -</span>-->
<!--              <a href="#">-->
<!--                <img src="~assets/img/ads/ad-750.png" alt="Advertisement"/>-->
<!--              </a>-->
<!--            </div>-->

            <div class="spacer" data-height="50"></div>
            <div class="spacer" data-height="50"></div>
            <div class="spacer" data-height="50"></div>

            <!-- section header -->
            <div class="section-header">
              <h3 class="section-title">Latest Posts</h3>
              <img src="~assets/img/wave.svg" class="wave" alt="wave"/>
            </div>

            <LatestPostBox></LatestPostBox>

          </div>
          <div class="col-lg-4">
            <!-- sidebar -->
            <div class="sidebar">

              <!-- widget popular posts -->
              <PopularPosts></PopularPosts>

              <!-- widget categories -->
              <ExploreTopics></ExploreTopics>

              <!-- widget post carousel -->
              <Celebration></Celebration>

              <!-- widget advertisement -->
              <div class="widget no-container rounded text-md-center">
                <span class="ads-title">- Sponsored Ad -</span>
                <a href="#" class="widget-ads">
                  <img src="~assets/img/map-bg.png" alt="Advertisement"/>
                </a>
              </div>

            </div>
          </div>

        </div>
      </div>
    </section>
    <div class="space"></div>
  </div>
</template>

<script>
  import PickCecommended from "./PickCecommended";
  import PickCecommendedItem from "./PickCecommendedItem"

  import PostLarge from "components/common/post/PostLarge";

  import PopularPosts from "../popularPosts/PopularPosts";
  import ExploreTopics from "../exploreTopics/ExploreTopics";
  import Celebration from "../celebration/Celebration";
  import InspirationBox from "components/common/inspirationBox/InspirationBox";
  import LatestPostBox from "components/content/latestPosts/LatestPostBox";

  import WidgetTags from "components/common/sidebar/WidgetTags";
  import {getPopular, getPopularPaging, getMostPageViews} from "network/article";

  export default {
    name: "PickMainContent",
    components: {
      PickCecommended,
      PickCecommendedItem,
      PopularPosts,
      ExploreTopics,
      Celebration,

      PostLarge,
      InspirationBox,
      LatestPostBox,

      WidgetTags
    },
    data() {
      return {
        editorPickLagerData: {},
        pickData: {},
        pickDataList: [],
      }
    },
    created() {
      this.getEditorPick();
    },
    methods: {
      getEditorPick() {
        getMostPageViews(1, 5).then(res => {
          // console.log(res);
          this.pickData = res.data.data[0];
          this.pickDataList = res.data.data;
          this.pickDataList.shift();
        })
        // getPopularPaging(2,1).then(res => {
        //   this.pickData = res.data.data[0];
        //   // console.log(this.pickData);
        // })
      }
    }
  }
</script>

<style scoped>
  .bottom-space-30 {
    margin-bottom: 30px;
  }
  .space{
    height: 30px;
  }
</style>