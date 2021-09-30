<template>
  <!-- featured post large -->
  <div>
    <div class="post featured-post-lg">
      <div class="details clearfix">
        <a href="#" class="category-badge">Inspiration</a>
        <h2 class="post-title">
          <div class="title mouse-hand" href="blog-single.html">{{articleInfo.title}}</div>
        </h2>
        <ul class="meta list-inline mb-0">
          <li class="list-inline-item"><a href="#">{{articleInfo.articleId}}</a></li><!-- 这里的链接再处理 -->
          <li class="list-inline-item">{{articleInfo.modifiedTime}}</li>
        </ul>
      </div>
      <div class="mouse-hand" @click="toDetail">
        <div class="thumb rounded">
          <div class="inner data-bg-image">
            <img src="~assets/img/posts/featured-lg.jpg" alt="">
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {getPopular} from 'network/article'

export default {
  name: "FeaturedPostLarge",
  props: {},
  data() {
    return {
      articleInfo: {
        articleId: '',
        authorId: '',
        briefIntroduction: '',
        modifiedTime: '',
        title: '',
      }
    }
  },
  created() {
    this.getPopularFromServer();
  },
  methods: {
    getPopularFromServer() {
      // console.log('asdsadfas');
      getPopular(1).then(res => {
        this.articleInfo = res.data[0];
        // console.log(this.articleInfo);
      })
    },
    toDetail() {
      console.log('ques');
      this.$router.push('/blog/' + this.articleInfo.articleId);
    }
  }
}
</script>

<style scoped>
.post-title .title {
  color: white;
}
.mouse-hand{
  cursor:pointer;
}
</style>