<template>
  <div class="col-sm-6">
    <!-- post -->
    <div class="post">
      <div class="thumb rounded">
      </div>
      <ul class="meta list-inline mt-4 mb-0">
        <li class="list-inline-item"><a href="#">
          <img src="~assets/img/posts/trending-lg-1.jpg" class="author" alt="author"/>{{ pickData.nickName }}</a>
        </li>
        <li class="list-inline-item">{{ pickData.modifiedTime }}</li>
      </ul>
      <h5 class="post-title mb-3 mt-3"><a href="blog-single.html">{{ pickData.title }}</a></h5>
      <p class="excerpt mb-0">{{ pickData.briefIntroduction }}</p>
    </div>
  </div>
</template>

<script>
import {getPopularPaging} from "network/article";
export default {
  name: "PickCecommended",
  props: {
    // pickData: {
    //   articleId: 'lkafdjgoljg',
    //   authorId: 'kjdfahgio',
    //   title: 'iogdsfhagioadfHJNioh',
    //   hrefLink: '',
    //   imgLink: '',
    //   authorImg: '',
    //   nickName: 'dghkfjhk',
    //   modifiedTime: 'sfdhgfsh',
    //   briefIntroduction: 'asfdhgfsdh',
    // }
  },
  data() {
    return{
      pickData: {
        articleId: '',
        authorId: '',
        title: '',
        hrefLink: '',
        imgLink: '',
        authorImg: '',
        nickName: '',
        modifiedTime: '',
        briefIntroduction: '',
      }
    }
  },
  created() {
    // 等待连接接口
    this.getPickEdict();
  },
  methods: {
    getPickEdict(){
      getPopularPaging(2,1).then(res => {
        this.pickData = res.data.data[0];
        // console.log(this.pickData);
      })
    }
  }
}
</script>

<style scoped>

</style>