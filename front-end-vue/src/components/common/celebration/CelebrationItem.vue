<template>
  <div class="post post-carousel celebration-box">
    <div class="thumb rounded">
      <!--      <a href="category.html" class="category-badge position-absolute">How to</a>-->
      <!--      <a href="blog-single.html">-->
      <div class="inner pointer" @click="toBlog">
        <img src="~assets/img/widgets/widget-carousel-2.jpg" alt="post-title"/>
      </div>
      <!--      </a>-->
    </div>
    <h5 class="post-title mb-0 mt-4"><a @click="toBlog">{{ celebration.title }}</a></h5>
    <ul class="meta list-inline mt-2 mb-0">
      <li class="list-inline-item"><a @click="toAuthorPage">{{ celebration.nickName }}</a></li>
      <li class="list-inline-item">{{ celebration.modifiedTime }}</li>
    </ul>
  </div>
</template>

<script>
export default {
  name: "CelebrationItem",
  props: {
    celebration: {
      imgUrl: '',
      title: '',
      nickName: '',
      modifiedTime: '',
      articleId: '',
      authorId: ''
    }
  },
  created() {
  },
  methods: {
    toBlog() {
      console.log("toBlog")
    },
    toAuthorPage(){

    }
  }

}
</script>

<style scoped>
.pointer {
  cursor: pointer
}
</style>