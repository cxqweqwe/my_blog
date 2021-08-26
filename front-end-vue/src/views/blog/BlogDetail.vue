<template>
  <div id="blogDetail">
    <section class="main-content">
      <div class="container-xl">
        <div class="row gy-4">
          <div class="col-lg-8 blog">
            <div class="blog-info">
              <h1>&nbsp;&nbsp;&nbsp;{{article.title}}</h1>
              <div class="line-1">
                <div class="avatar">
                  &nbsp;&nbsp;<Avatar style="color: #ffffff;background-color: #ea7f7f">U</Avatar>
                </div>
                <div class="nick-name">
                  你牛皮你来砍我啊！憨批
                </div>
                <div class="time">
                  <span class="time-text">发布时间&nbsp;</span> {{article.modifiedTime}}
                </div>
                <div class="view-collect">
                  <i class="el-icon-view"></i>&nbsp;99999 &nbsp;&nbsp;
                  <Icon type="md-star" />99999
                </div>
              </div>
              <div class="line-2">
                <div class="label">
                  <span class="label-text">文章标签：</span>
                  <Tag color="volcano" v-for="(item,index) in labels" :key="index" v-text="item"></Tag>
                </div>
                <div class="like">
                  <Icon type="ios-thumbs-up-outline" v-if="!likeOrNot" @click="likeClick" title="喜欢就点个赞吧~"/>
                  <Icon type="ios-thumbs-up" v-else @click="likeClick" title="已喜欢"/>
                </div>
              </div>
            </div>
            <div v-html="article.html" class="markdown-body" v-highlight>
            </div>

          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
// import demo from '../../static/lib/release.md';
import 'github-markdown-css/github-markdown.css';
import {articleDetail} from 'network/article';

export default {
  name: "BlogDetail",
  data() {
    return {
      // md: demo,
      articleId: '',
      article: {
        articleId: "",
        attributes: 1,
        authorId: "",
        briefIntroduction: "",
        content: "",
        createTime: "",
        deleted: 0,
        html: '',
        label: "",
        modifiedTime: "2021-08-26T07:35:02.000+00:00",
        releaseOrNot: 1,
        title: ""
      },
      likeOrNot: true,
      labels:['测试1','测试2','测试3','测试4','测试5'],
    }
  },
  created() {
    this.articleId = this.$route.params.articleId

    // 获取文章
    this.getDetail(this.articleId);
  },
  computed: {

  },
  methods: {
    // 获取文章方法
    getDetail(id) {
      articleDetail(id).then(res => {
        console.log(res);
        this.article = res.data;

        console.log(this.article);
      }).catch(res => {
        this.$message.error(res.msg);
      })
    },

    //喜欢博客点赞
    likeClick(){
      this.likeOrNot = !this.likeOrNot;
      if (this.likeOrNot){
        this.$notify({
          // title: '',
          message: '收到您的喜欢啦！',
          type: 'success'
        });
      }else {
        this.$notify({
          // title: '',
          message: '已取消喜欢',
          type: 'success'
        });
      }
    }

  }

}
</script>

<style scoped>
.blog {
  padding: 20px 10px;
  border: 1px solid #cb8888;
  border-radius: 15px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.3);
  background: #f8f3e8;
}

.space {
  height: 20px;
}

.blog-info{
  /*border: 1px solid #f8f3e8;*/
  border-radius: 15px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}

.blog-info .line-1{
  height: 38px;
}

.blog-info .line-1 .avatar{
  display: inline-block;
  width: 6%;
  line-height: 38px;
}
.blog-info .line-1 .nick-name{
  display: inline-block;
  width: 40%;
  line-height: 38px;
}
.blog-info .line-1 .time{
  display: inline-block;
  width: 30%;
  line-height: 38px;
}
.blog-info .line-1 .time .time-text{
  color: #939395;
  line-height: 38px;
}
.blog-info .line-1 .view-collect{
  display: inline-block;
  width: 24%;
  line-height: 38px;
  text-align: center;
}

.line-2{
  padding-bottom: 5px;
  padding-top: 5px;
}
.line-2 .like{
  display: inline-block;
  width: 40%;
  padding-right: 30px;
  text-align:right;
}
.line-2 .label{
  display: inline-block;
  width: 60%;
  padding-left: 45px;
}
.line-2 .label .label{
  color: #939395;
  line-height: 38px;
}

.markdown-body {
  box-sizing: border-box;
  min-width: 200px;
  max-width: 980px;
  margin: 0 auto;
  padding: 45px;
}

@media (max-width: 767px) {
  .markdown-body {
    padding: 15px;
  }
}
</style>