<template>
  <div class="col-sm-6">
    <!-- post -->
    <div class="post">
      <div class="thumb rounded">
      </div>
      <ul class="meta list-inline mt-4 mb-0">
        <li class="list-inline-item">
          <a href="#">
            <img src="~assets/img/posts/trending-lg-1.jpg" class="author" alt="author"/>
          </a>
        </li>
      </ul>

      <ul class="meta list-inline mt-4 mb-0">
        <li class="list-inline-item" @click="toPersonal(pickData.authorId)">
          <a href="#"><img src="images/other/author-sm.png" class="author" alt="author"/>{{ pickData.nickName }}</a>
        </li>
        <li class="list-inline-item">{{ pickData.modifiedTime }}</li>
      </ul>

      <h5 class="post-title mb-3 mt-3"><a><router-link target="_blank" :to="{path:'/blog/' + pickData.articleId}">{{ pickData.title }}</router-link></a></h5>
      <p class="excerpt mb-0">{{ pickData.briefIntroduction }}</p>
    </div>
  </div>
</template>

<script>
import {getPopularPaging} from "network/article";
export default {
  name: "PickCecommended",
  props: {
    pickData: {
      articleId: '',
      authorId: '',
      title: '',
      hrefLink: '',
      imgLink: '',
      avatarPath: '',
      nickName: '',
      modifiedTime: '',
      briefIntroduction: '',
    }
  },
  data() {
    return{
      // pickData: {
      //   articleId: '',
      //   authorId: '',
      //   title: '',
      //   hrefLink: '',
      //   imgLink: '',
      //   authorImg: '',
      //   nickName: '',
      //   modifiedTime: '',
      //   briefIntroduction: '',
      // }
    }
  },
  created() {
    // this.getPickEdict();
  },
  methods: {
    // getPickEdict(){
    //
    // }
    toPersonal(authorId){
      //TODO: 跳转到个人页面

    }
  }
}
</script>

<style scoped>

</style>