<template>
  <div style="width: 100%">
    <div class="widget rounded">
      <div class="widget-header text-center">
        <h3 class="widget-title">Celebration</h3>
        <img src="~assets/img/wave.svg" class="wave" alt="wave"/>
      </div>
      <div class="widget-content">
        <div class="post-carousel-widget">
          <!-- post -->
          <CelebrationItem v-show="show == 1"></CelebrationItem>
          <!-- post -->
          <div class="post post-carousel celebration-item" v-show="show == 2">
            <div class="thumb rounded">
              <a href="category.html" class="category-badge position-absolute">Trending</a>
              <a href="blog-single.html">
                <div class="inner">
                  <img src="~assets/img/widgets/widget-carousel-2.jpg" alt="post-title"/>
                </div>
              </a>
            </div>
            <h5 class="post-title mb-0 mt-4"><a href="blog-single.html">Master The Art Of Nature With These 7 Tips</a>
            </h5>
            <ul class="meta list-inline mt-2 mb-0">
              <li class="list-inline-item"><a href="#">Katen Doe</a></li>
              <li class="list-inline-item">29 March 2021</li>
            </ul>
          </div>
          <!-- post -->
          <div class="post post-carousel celebration-item" v-show="show == 3">
            <div class="thumb rounded">
              <a href="category.html" class="category-badge position-absolute">How to</a>
              <a href="blog-single.html">
                <div class="inner">
                  <img src="~assets/img/widgets/widget-carousel-1.jpg" alt="post-title"/>
                </div>
              </a>
            </div>
            <h5 class="post-title mb-0 mt-4"><a href="blog-single.html">5 Easy Ways You Can Turn Future Into Success</a>
            </h5>
            <ul class="meta list-inline mt-2 mb-0">
              <li class="list-inline-item"><a href="#">Katen Doe</a></li>
              <li class="list-inline-item">29 March 2021</li>
            </ul>
          </div>
        </div>
      </div>
      <!-- carousel arrows -->
      <div class="slick-arrows-bot">
        <button type="button" data-role="none" class="carousel-botNav-prev slick-custom-buttons" aria-label="Previous"
                @click="Previous"><i class="icon-arrow-left"></i></button>
        <button type="button" data-role="none" class="carousel-botNav-next slick-custom-buttons" aria-label="Next"
                @click="Next"><i class="icon-arrow-right"></i></button>
      </div>
    </div>
  </div>
</template>

<script>
import CelebrationItem from "./CelebrationItem";

export default {
  name: "Celebration",
  components: {
    CelebrationItem
  },
  data() {
    return {
      show: 3,
    }
  },
  created() {
  },
  methods: {
    Previous() {
      if (this.show - 1 < 1) {
        this.show = 3;
      } else {
        this.show--;
      }
    },
    Next() {
      if (this.show + 1 > 3) {
        this.show = 1;
      } else {
        this.show++;
      }
    }
  }
}
</script>

<style scoped>
.celebration-box {
  width: 298px;
  height: 310px;
  overflow: hidden;
  display: inline-block;
}

.celebration-item {
  display: inline-block;
  width: 298px;
  height: auto;
  box-sizing: border-box;
}
</style>