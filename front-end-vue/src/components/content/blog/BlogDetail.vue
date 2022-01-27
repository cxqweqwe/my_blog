<template>
  <div>
    <div class="blog blogDetail">
      <div class="info">
        <Row>
          <Col span="24">
            <h1>{{ article.title }}</h1>
          </Col>
        </Row>
        <div class="space"></div>
        <Row>
          <Col span="16">
            <Avatar style="color: #ffffff;background-color: #ea7f7f">U</Avatar>&nbsp;
            <div class="nick-name">
              {{ nickName }}
            </div>
          </Col>
          <Col span="8">
            <div class="time">
              <span class="time-text">发布时间&nbsp; {{ article.modifiedTime }}</span>
            </div>
          </Col>
        </Row>
        <div class="space"></div>
        <Row>
          <Col span="16">
            标签:
            <Tag color="volcano" v-for="(item,index) in labels" :key="index" v-text="item"></Tag>
          </Col>
          <Col span="5">
            <div class="view-collect">
              <i class="el-icon-view"></i>&nbsp;{{ pageviews }}
              <Icon type="md-star"/>
              {{ blogCollection }}
            </div>
          </Col>
          <Col span="3">
            <div class="like" @click="likeClick">
              <Icon type="ios-thumbs-up-outline" v-if="!likeOrNot" title="喜欢就点个赞吧~"/>
              <Icon type="ios-thumbs-up" v-else title="已喜欢"/>
              {{ likes }}
            </div>
          </Col>
        </Row>
      </div>
      <div class="blog-body">
        <div v-html="article.html" class="markdown-body" v-highlight></div>
      </div>
    </div>
    <div class="space"></div>
    <div class="common-div">
      <Comment :articleId="articleId"></Comment>
    </div>
    <div class="space"></div>
  </div>
</template>

<script>
  import 'github-markdown-css/github-markdown.css';
  import {articleDetail} from 'network/article';
  import {getAuthorInfo} from "network/userInfo";
  import {getBlogInfo, likeBlog, unLikeBlog} from "network/blogInfo";

  import Comment from "components/content/comment/Comment";


  export default {
    name: "BlogDetail",
    components: {
      Comment,
    },
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
          label: '',
          modifiedTime: "",
          releaseOrNot: 1,
          title: ""
        },
        likeOrNot: false,
        labels: [],
        nickName: '',
        blogViews: 0,
        pageviews: 0,
        blogCollection: 0,
        likes: 0,
      }
    },
    created() {
      this.articleId = this.$route.params.articleId

      // 获取文章
      this.getDetail(this.articleId);
      this.getBlogInfo(this.articleId);
    },
    computed: {},
    methods: {
      // 获取文章方法
      getDetail(id) {
        articleDetail(id).then(res => {
          this.article = res.data;
          this.labels = this.article.label.split(',');
          this.getAuthorInfo();
        }).catch(res => {
          this.$notify.error({
            title: '错误',
            message: res.msg
          });
        })
      },

      //喜欢博客点赞
      likeClick() {
        //发送网络请求
        console.log(this.likeOrNot);
        if (!this.likeOrNot) {
          // 未点赞
          likeBlog(this.articleId).then(res => {
              if (res.stat == 6000) {
                this.$notify.info({
                  title: '提示',
                  message: res.msg
                });
                return;
              }
              this.likeOrNot = !this.likeOrNot;
              this.likes++;
              this.$notify({
                message: '收到您的喜欢啦！',
                type: 'success'
              });
            }
          ).catch(res => {
            this.$notify.error({
              title: '错误',
              message: '网络开小差了'
            });
          })
        } else {
          unLikeBlog(this.articleId).then(res => {
            if (res.stat == 6000) {
              this.$notify.info({
                title: '提示',
                message: res.msg
              });
              return;
            }
            this.likeOrNot = !this.likeOrNot;
            this.likes--;
            this.$notify({
              message: '已取消喜欢',
              type: 'success'
            });
          }).catch(res => {
            this.$notify.error({
              title: '错误',
              message: '网络开小差了'
            });
          })
        }
      }
      ,
      getAuthorInfo() {
        getAuthorInfo(this.article.authorId).then(res => {
          // console.log(res.data);
          this.nickName = res.data.nickName;// TODO:用户图像还没处理
        }).catch(res => {
          this.$notify.error({
            title: '错误',
            message: res.msg
          });
        })
      },

      getBlogInfo(articleId) {
        getBlogInfo(articleId).then(res => {
          this.blogCollection = res.data.collection;
          this.likes = res.data.likes;
          this.pageviews = res.data.pageviews;
        })
      },


      // 方法结束
    }

  }
</script>

<style scoped>
  .info {
    /*height: 500px;*/
    width: 100%;
    padding: 15px;
    /*background: #88ecae;*/
  }

  .blog {
    width: 100%;
    padding: 20px 10px;
    border: 1px solid #ebebeb;
    border-radius: 15px;
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
    /*background: #f9f5ed;*/
  }

  .space {
    height: 10px;
  }

  .blog-info {
    /*border: 1px solid #f8f3e8;*/
    border-radius: 15px;
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.3);
  }

  .blog-info .line-1 {
    height: 38px;
  }

  .blog-info .line-1 .avatar {
    display: inline-block;
    width: 6%;
    line-height: 38px;
  }

  .nick-name {
    display: inline-block;
    line-height: 38px;
    /*width: 40%;*/
    line-height: 38px;
  }

  .blog-info .line-1 .time {
    display: inline-block;
    width: 30%;
    line-height: 38px;
  }

  .time-text {
    color: #939395;
    line-height: 38px;
  }

  .blog-info .line-1 .view-collect {
    display: inline-block;
    width: 24%;
    line-height: 38px;
    text-align: center;
  }

  .line-2 {
    padding-bottom: 5px;
    padding-top: 5px;
  }

  .like {
    padding-right: 28px;
    text-align: right;
    cursor: pointer;
  }

  .line-2 .label {
    display: inline-block;
    width: 60%;
    padding-left: 45px;
  }

  .line-2 .label .label {
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

  /* 评论部分 */
  .common-div{
    padding: 10px;
    border: 1px solid #ebebeb;
    border-radius: 15px;
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  }
</style>