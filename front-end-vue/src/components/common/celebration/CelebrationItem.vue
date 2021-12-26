<template>
  <div class="post post-carousel">
    <div class="thumb rounded">
<!--            <a href="category.html" class="category-badge position-absolute">How to</a>-->
<!--            <a href="blog-single.html">-->
      <div class="inner pointer" @click="toBlog">
        <img src="~assets/img/widgets/widget-carousel-2.jpg" alt="post-title"/>
      </div>
      <!--      </a>-->
    </div>
    <h5 class="post-title mb-0 mt-4 div-text-box"><a @click="toBlog" :title="celebration.title">{{ celebration.title }}</a></h5>
    <ul class="meta list-inline mt-2 mb-0">
      <li class="list-inline-item"><a @click="toAuthorPage">{{ celebration.nickName }}</a></li>
      <li class="list-inline-item">{{ celebration.createTime }}</li>
    </ul>
    <div class="space-20"></div>
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
      createTime: '',
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
.div-text-box{
  height: 56px;
  width: 100%;

  word-break: break-all;
  text-overflow: ellipsis;
  overflow: hidden;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
}
.space-20{
  height: 20px;
}
</style>