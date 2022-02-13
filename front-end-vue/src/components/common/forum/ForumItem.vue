<template>
  <div class="item-main">
    <Row>
      <Col span="6">
        <div class="span-text cursor">{{ forumItem.nickName }}</div>
      </Col>
      <Col span="6">
        <div class="span-text">{{forumItem.createTime}}</div>
      </Col>
      <Col span="6">
      </Col>
      <Col span="3">
        <!--        <span class="span-text">查看人数</span>-->
      </Col>
      <Col span="3">
        <div class="span-text">{{forumItem.commonCount}}</div>
      </Col>
    </Row>
    <Row>
      <Col span="24">
        <div class="span-text title a-line">{{forumItem.postName}}</div>
      </Col>
    </Row>
    <Row>
      <Col span="24">
        <div class="span-text text-description">{{forumItem.postDescription}}</div>
      </Col>
    </Row>
    <div :id="'images' + idCount">
      <ul>
        <li class="li-inline cursor" v-for="(imgPath,index) of imageList">
          <img :src="imgPath" :alt="'描述辅助图片' + index" style="width: 100px; height: 100px">
        </li>
      </ul>
    </div>
    <div class="space10"></div>
    <div class="space10"></div>
    <div class="line"></div>
  </div>
</template>

<script>
  import Viewer from 'viewerjs';
  import 'viewerjs/dist/viewer.css';

  export default {
    name: "ForumItem",
    props: {
      idCount: String,
      forumItem: {
        postId: String,
        authorId: String,
        nickName: String,
        postName: String,
        postDescription: String,
        imagePath: String,
        createTime: String,
        viewsCount: String,   //暂时不用
        commonCount: String
      }
    },
    data() {
      return {
        imageList: [],
        imageList2: [
          'http://image.fangweb.top/FjSynGvJiBrvDVpQKGVtnKMBP6o-',
          'https://img-blog.csdnimg.cn/a61273f5aa754224b6f4bb8956b78a37.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBA5YeJ55m95byAwro=,size_20,color_FFFFFF,t_70,g_se,x_16',
          // 'https://img-blog.csdnimg.cn/20210327145250464.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L0Jlcm5pZV83,size_16,color_FFFFFF,t_70',
          // 'https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3472263623,506218584&fm=26&gp=0.jpg',
          // 'http://www.sinaimg.cn/dy/slidenews/21_img/2015_17/2236_4146071_705561.jpg',
          // 'http://www.sinaimg.cn/dy/slidenews/21_img/2015_17/2236_4146072_346494.jpg'
        ],
        viewer: undefined,
      }
    },
    created() {
      // 处理图片分裂问题
      if (this.forumItem.imagePath != null && this.forumItem.imagePath != '' && this.forumItem.imagePath != undefined) {
        this.imageList = this.forumItem.imagePath.split("-*-");
        for (let item of this.imageList) {
          // console.log(item);
          if (item == '' || item == undefined) {      // 删除空行
            this.imageList.splice(this.imageList.indexOf(item), 1);
          }
        }
      }
    },
    methods: {

    },
    mounted() {
      // const id = 'images' + this.idCount;
      // console.log(id);
      const ViewerDom = document.getElementById('images' + this.idCount);
      // console.log(ViewerDom);
      this.viewer = new Viewer(ViewerDom, {
        // 相关配置项,详情见下面
        rotatable: false,
      });
    }
  }
</script>

<style scoped>
  .space10 {
    height: 10px;
  }

  .item-main {
    padding: 10px 5px 10px 5px;
  }

  .span-text {
    padding: 5px;
  }

  .cursor {
    cursor: pointer;
  }

  .title {
    font-size: 20px;
    font-weight: bold;
  }

  .line {
    height: 1px;
    border-bottom: 1px solid #ebebeb;
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.3)
  }

  .a-line:hover {
    color: #fe4f70;
    cursor: pointer;
  }

  .text-description {
    max-width: 724px;
    height: 50px;
    word-break: break-all;
    text-overflow: ellipsis;
    overflow: hidden;
    display: -webkit-box;
    -webkit-line-clamp: 2; /* 正常换行显示 */
    -webkit-box-orient: vertical;
  }

  .li-inline {
    display: inline;
    list-style-type: none;
    padding: 5px 5px;
  }
</style>