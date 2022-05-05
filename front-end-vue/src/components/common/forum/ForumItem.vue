<template>
  <div class="item-main cursor">
    <Row>
      <Col span="6">
        <div class="span-text cursor" @click="goNewPage1('/user/')">{{ forumItem.nickName }}</div>
      </Col>
      <Col span="6">
        <div class="span-text" @click="lookDetail">{{forumItem.createTime}}</div>
      </Col>
      <Col span="6">
      </Col>
      <Col span="3">
        <!--        <span class="span-text">查看人数</span>-->
      </Col>
      <Col span="3">
        <div class="span-text">{{forumItem.commonCount}}</div>
        <a @click="deleteForum" v-if="authorId === forumItem.authorId"><span class="el-icon-delete-solid"></span></a>
        &nbsp;
        <a @click="editForum" v-if="authorId === forumItem.authorId"><span class="el-icon-edit"></span></a>
      </Col>
    </Row>
    <div @click="lookDetail">
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
    </div>
    <div :id="'images' + idCount">
      <ul>
        <li class="li-inline zoom-in" v-for="(imgPath,index) of imageList">
          <img :src="imgPath" :alt="'描述辅助图片' + index" style="width: 100px; height: 100px">
        </li>
      </ul>
    </div>
    <div class="space10"></div>
    <div class="space10"></div>
    <div class="line"></div>
    <Drawer title="修改论贴" placement="left" :closable="false" v-model="showDrawer" :width="50">
      <div class="space"></div>
      <Form :model="updatePost" label-position="top">
        <FormItem label="标题">
          <Input v-model="updatePost.postName" placeholder="建议发布前先搜索，或许就能找到答案"></Input>
        </FormItem>
        <FormItem label="详情">
          <Input v-model="updatePost.postDescription" type="textarea" :autosize="{minRows: 3,maxRows: 10}"
                 placeholder="请输入详情描述信息."></Input>
        </FormItem>
      </Form>

      <!-- 图片显示区域 -->
      <ul>
        <li class="demo-upload-list" v-for="(item,index) in defaultList">
          <div>
            <template>
              <img :src="item.url" style="width: 100px">
              <div class="demo-upload-list-cover">
                <Icon type="ios-trash-outline" @click.native="handleRemove(item)"></Icon>
              </div>
            </template>
          </div>
        </li>
        <li class="demo-upload-list">
          <Upload
              ref="upload"
              :show-upload-list="false"
              :default-file-list="defaultList"
              :on-success="handleSuccess"
              :headers="addHeader"
              :format="['jpg','jpeg','png']"
              :max-size="2048"
              :on-format-error="handleFormatError"
              :on-exceeded-size="handleMaxSize"
              :before-upload="handleBeforeUpload"
              :multiple="false"
              type="drag"
              :action="action"
              style="display: inline-block;width:58px;">
            <div style="width: 58px;height:58px;line-height: 58px;">
              <Icon type="ios-camera" size="20"></Icon>
            </div>
          </Upload>
        </li>
      </ul>
      <div class="space"></div>
      <Button type="success" long @click="updateToRelease">更改后发布</Button>
    </Drawer>
  </div>
</template>

<script>
  import Viewer from 'viewerjs';
  import 'viewerjs/dist/viewer.css';

  import {getCookieAuthorId, getCookie} from "common/cookieUtils";
  import {deletePostInfo} from "network/postInfo";
  import {IMAGE_UPLOAD_URL} from "common/common_variable";
  import {updatePostInfo} from "network/postInfo";

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
        viewer: undefined,
        authorId: getCookieAuthorId(),
        showDrawer: false,
        updatePost: {
          postName: String,
          postDescription: String,
          imagePath: Array,
        },
        defaultList: [],
        action: '',

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
      lookDetail() {
        const {href} = this.$router.resolve({
          path: '/forumDetail/' + this.forumItem.postId,
          query: {},
        });
        window.open(href, "_blank");
      },
      goNewPage1(path) {
        const {href} = this.$router.resolve({
          path: path + this.forumItem.authorId,
          query: {},
        });
        window.open(href, "_blank");
      },
      editForum: function () {
        // 直接拿原有的信息，不过后台查询
        this.updatePost = this.forumItem;
        let url =  this.updatePost.imagePath.split('-*-');
        url.pop();//删除最后一个空的
        this.defaultList.length = 0;
        url.map((path,index) => {
          this.defaultList.push({
            name: '图片' + index,
            url: path
          })
        })
        // this.defaultList = url;

        this.showDrawer = true;
      },
      handleRemove(file) {
        const fileList = this.$refs.upload.fileList;
        this.$refs.upload.fileList.splice(fileList.indexOf(file), 1);
        this.defaultList.splice(this.defaultList.indexOf(file),1);
      },
      handleSuccess(res, file) {
        console.log(res);
        if (res.status == 2000){
          this.defaultList.push({
            name: '图片' + (this.defaultList.length + 1),
            url: res.data
          })
          // console.log(this.defaultList);
          return;
        }
        this.$notify({
          message: res.msg,
          type: "warning"
        })
      },
      handleFormatError(file) {
        this.$Notice.warning({
          title: 'The file format is incorrect',
          desc: 'File format of ' + file.name + ' is incorrect, please select jpg or png.'
        });
      },
      handleMaxSize(file) {
        this.$Notice.warning({
          title: 'Exceeding file size limit',
          desc: 'File  ' + file.name + ' 太大了,不允许超过2M.'
        });
      },
      handleBeforeUpload() {
        const check = this.uploadList.length < 3;
        if (!check) {
          this.$Notice.warning({
            title: '只允许上传3张以内的照片.'
          });
        }
        return check;
      },
      updateToRelease() {
        // console.log('修改');
        // 校验
        if (this.updatePost.postName == '' || this.updatePost.postDescription == ''){
          this.$Notice.warning({
            title: '新帖标题和描述均不能为空.'
          });
          return;
        }
        if (this.defaultList.length > 0){
          let urls = '';
          for (let item of this.defaultList) {
            // console.log(item);
            urls = urls.concat(item.url,'-*-');
          }
          this.updatePost.imagePath = urls;
        }

        updatePostInfo(this.updatePost).then(res => {
          // console.log(res);
          this.$notify.success({
            title: res.msg
          });
          this.showDrawer = false;
          this.updatePost = {};
        })
      },

      deleteForum() {
        deletePostInfo(this.forumItem.postId).then(res => {
          this.$notify.success({
            title: res.msg
          });
          this.$emit("forum-reload");
        })
      }
    },
    computed: {
      addHeader(){
        return {
          'Authorization': getCookie()
        }
      }
    },
    mounted() {
      this.uploadList = this.$refs.upload.fileList;
      this.action = IMAGE_UPLOAD_URL;

      const ViewerDom = document.getElementById('images' + this.idCount);
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

  .zoom-in{
    cursor: zoom-in;
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


  .demo-upload-list {
    display: inline-block;
    width: 60px;
    height: 60px;
    text-align: center;
    line-height: 60px;
    border: 1px solid transparent;
    border-radius: 4px;
    overflow: hidden;
    background: #fff;
    position: relative;
    box-shadow: 0 1px 1px rgba(0, 0, 0, .2);
    margin-right: 4px;
  }

  .demo-upload-list img {
    width: 100%;
    height: 100%;
  }

  .demo-upload-list-cover {
    display: none;
    position: absolute;
    top: 0;
    bottom: 0;
    left: 0;
    right: 0;
    background: rgba(0, 0, 0, .6);
  }

  .demo-upload-list:hover .demo-upload-list-cover {
    display: block;
  }

  .demo-upload-list-cover i {
    color: #fff;
    font-size: 20px;
    cursor: pointer;
    margin: 0 2px;
  }

</style>