<template>
  <div>
    <TabBar v-if="reload" :unRead="unReadCount.toString()"></TabBar>
    <section class="main-content">
      <div class="container-xl">
        <div class="row gy-4">
          <div class="col-lg-8">

            <div v-if="isMy">
              <Tabs @on-click="clickHandle" v-loading="loading">
                <TabPane label="我的博客" icon="ios-book" name="myBlog">
                  <div class="span-text" v-if="personalArticleList != null && personalArticleList.length == 0">
                    这个人太懒了,什么都没留下。。。
                  </div>
                  <div v-else>
                    <div class="padding-30 rounded bordered">
                      <div class="row">
                        <LatestPostsItem v-for="(itemData,index) in personalArticleList" :key="index"
                                         :item="itemData" @personal-reload="personalArticleListReload"></LatestPostsItem>
                      </div>
                    </div>
                    <div class="space"></div>
                    <div class="box-line"></div>
                    <div class="space"></div>
                    <div class="text-center">
                      <button class="btn btn-simple" @click="loadMore()">Load More</button>
                    </div>
                  </div>
                </TabPane>
                <TabPane label="论贴" icon="logo-hackernews" name="post">
                  <div v-if="itemDataList != null && itemDataList.length != 0" v-loading="postLoading">
                    <div class="forum-main" v-loading="loading">
                      <ForumItem v-for="(item,index) in itemDataList" :forumItem="item" :key="index"
                                 :idCount="index + ''" @forum-reload="itemDataListReload" />
                    </div>
                    <div class="space10"></div>
                    <div class="space10"></div>
                    <div class="text-center">
                      <Page :total="postTotal" :page-size="6" @on-change="postChange" show-elevator/>
                    </div>
                  </div>
                  <div class="span-text" v-else>
                    您还未发布任何论贴。。。
                  </div>
                </TabPane>
                <TabPane :label="msgLabel"  name="information">
<!--                  <MessageBox />-->
                  <div class="message-box" v-for="(item, index) in informationList" :index="index">
                    <div class="box-head">
                      {{ item.createTime }}
                    </div>
                    <div class="box-body">
                      <router-link target="_blank" :to="{path:'/user/' + item.authorId}">{{ item.nickName }}</router-link>
                      : 嘿，我发布了新的博客<router-link target="_blank" :to="{path:'/blog/' + item.articleId}">{{ item.title }}</router-link>, 快来看看
                    </div>
                  </div>
                </TabPane>
                <TabPane label="个人信息" icon="ios-person" name="personalInfo">

                  <Form ref="formValidate" :model="formValidate" :rules="ruleValidate" :label-width="130">
                    <FormItem label="昵称" prop="nickName">
                      <Input v-model="formValidate.nickName" placeholder="Enter your nick name" :border="false"
                             :clearable="true"></Input>
                    </FormItem>
                    <FormItem label="真实姓名" prop="realName">
                      <Input v-model="formValidate.realName" placeholder="Enter your realName" :border="false"
                             :clearable="true"></Input>
                    </FormItem>
                    <FormItem label="头像" prop="avatarPath">
                      <div @click="avatarModal=true" class="finger">
                        <el-avatar :size="60" :src="formValidate.avatarPath" title="点击更换"></el-avatar>
                      </div>
                    </FormItem>

                    <FormItem label="性别" prop="sex">
                      <RadioGroup v-model="formValidate.sex">
                        <Radio label="1">男</Radio>
                        <Radio label="0">女</Radio>
                      </RadioGroup>
                    </FormItem>
                    <FormItem label="城市" prop="city">
                      <Input v-model="formValidate.city" placeholder="Enter your city" :border="false"
                             :clearable="true"></Input>
                    </FormItem>

                    <FormItem label="简介" prop="personalProfile">
                      <Input v-model="formValidate.personalProfile" type="textarea"
                             :autosize="{minRows: 2,maxRows: 5}"
                             :border="false" :clearable="true"
                             placeholder="Enter something about you..."></Input>
                    </FormItem>

                    <hr/>
                    <div class="space"></div>

                    <FormItem label="毕业学校" prop="school">
                      <Input v-model="formValidate.school" placeholder="Enter your university" :border="false"
                             :clearable="true"></Input>
                    </FormItem>
                    <FormItem label="专业" prop="major">
                      <Input v-model="formValidate.major" placeholder="Enter your college major" :border="false"
                             :clearable="true"></Input>
                    </FormItem>
                    <FormItem label="入学时间" prop="admissionTime">
                      <FormItem prop="date">
                        <DatePicker type="date" placeholder="Select date" v-model="formValidate.admissionTime"
                                    :format="'yyyy-MM-dd'"></DatePicker>
                      </FormItem>
                    </FormItem>
                    <FormItem label="所在公司" prop="companyName">
                      <Input v-model="formValidate.companyName" placeholder="Enter your company name" :border="false"
                             :clearable="true"></Input>
                    </FormItem>
                    <FormItem label="职位" prop="positionName">
                      <Input v-model="formValidate.positionName" placeholder="Enter your position name" :border="false"
                             :clearable="true"></Input>
                    </FormItem>
                    <FormItem label="行业" prop="industry">
                      <Input v-model="formValidate.industry" placeholder="Enter your industry" :border="false"
                             :clearable="true"></Input>
                    </FormItem>
                    <FormItem label="开始工作日期" prop="workDate">
                      <FormItem prop="workDate">
                        <DatePicker type="date" placeholder="Select date" v-model="formValidate.workDate"
                                    :format="'yyyy-MM-dd'"></DatePicker>
                      </FormItem>
                    </FormItem>


                    <FormItem label="手机号" prop="phoneNumber" title="点击修改">
                      <div @click="phoneModal=true" class="finger">
                        <Input v-model="formValidate.phoneNumber" placeholder="phone number, readonly" :border="false"
                               :readonly="true" style="width: 100px"></Input>
                      </div>
                    </FormItem>

                    <FormItem label="邮箱" prop="email" title="点击修改">
                      <div @click="emailModal=true" class="finger">
                        <Input v-model="formValidate.email" placeholder="Enter your e-mail" :border="false"
                               :readonly="true" style="width: 210px"></Input>
                      </div>
                    </FormItem>

                    <FormItem>
                      <Button type="primary" @click="handleSubmit('formValidate')">提交修改</Button>
                      <Button @click="handleReset('formValidate')" style="margin-left: 8px">清空</Button>
                    </FormItem>
                  </Form>
                  <Modal
                      v-model="avatarModal"
                      title="更换头像"
                      @on-ok="avatarOk"
                      @on-cancel="avatarCancel">
                    <el-upload
                        ref="upload"
                        class="upload-demo"
                        :limit="1"
                        :action="uploadUrl"
                        :headers="addHeader"
                        :before-upload="beforeUpload"
                        :on-success="uploadSuccess"
                        :on-error="uploadError"
                        list-type="picture">
                      <el-button size="small" type="primary">点击上传</el-button>
                      <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过2MB，限制一张</div>
                    </el-upload>
                  </Modal>

                  <Modal
                      v-model="phoneModal"
                      title="绑定/更换手机号"
                      :footer-hide=true>
                    <Row>
                      <Col span="4" class="line32">更换手机号</Col>
                      <Col span="15"><Input v-model="newPhone" placeholder="Enter phone" clearable/></Col>
                      <Col span="5">
                        <Button type="dashed" @click="getCode('phone')">获取验证码</Button>
                      </Col>
                    </Row>

                    <Row>
                      <Col span="4" class="line32">手机验证码</Col>
                      <Col span="15"><Input v-model="code" placeholder="Enter code" clearable/></Col>
                      <Col span="5">
                        <Button type="dashed" @click="update('email')"> 更 新 信 息</Button>
                      </Col>
                    </Row>
                  </Modal>
                  <Modal
                      v-model="emailModal"
                      title="绑定/更换邮箱"
                      :footer-hide=true>
                    <Row>
                      <Col span="5" class="line32">更换邮箱地址</Col>
                      <Col span="14"><Input v-model="newEmail" placeholder="Enter email" clearable/></Col>
                      <Col span="5">
                        <Button type="dashed" @click="getCode('email')">获取验证码</Button>
                      </Col>
                    </Row>
                    <Row>
                      <Col span="5" class="line32">邮箱验证码</Col>
                      <Col span="14"><Input v-model="code" placeholder="Enter code" clearable/></Col>
                      <Col span="5">
                        <Button type="dashed" @click="update('email')"> 更 新 信 息</Button>
                      </Col>
                    </Row>
                  </Modal>


                </TabPane>
                <!-- 暂时不写 -->
<!--                <TabPane label="设置" icon="ios-build" name="setting">-->
<!--                  设置-->
<!--                </TabPane>-->
              </Tabs>
            </div>
            <div v-else class="">
              <div v-loading="loading">
                <div class="padding-30 rounded bordered">
                  <div class="row">
                    <LatestPostsItem v-for="(itemData,index) in personalArticleList" :key="index"
                                     :item="itemData"></LatestPostsItem>
                  </div>
                  <div class="span-text" v-if="personalArticleList != null && personalArticleList.length == 0">
                    这个人太懒了,什么都没留下。。。
                  </div>
                </div>
              </div>
              <div class="space"></div>
              <div class="box-line"></div>
              <div class="space"></div>
              <div class="text-center">
                <Page :total="total" :page-size="6" @on-change="change" show-elevator/>
              </div>
            </div>

          </div>
          <div class="col-lg-4">
            <Blogger :authorId="authorId" v-if="reload"></Blogger>
            <PostTabs></PostTabs>
            <div class="space"></div>
            <Celebration></Celebration>

          </div>
        </div>
      </div>
    </section>

    <div class="space"></div>
    <Footer></Footer>
  </div>
</template>

<script>
  import TabBar from "components/common/tabBar/TabBar";
  import PostTabs from "components/content/featured/PostTabs";
  import Celebration from "components/content/celebration/Celebration";
  import Blogger from "components/common/ blogger/Blogger";
  import PickCecommended from "components/content/main/PickCecommended";

  import {getPersonalArticle} from "network/article";
  import {getCookie, getCookieAuthorId, setCookieAvatarPath} from "common/cookieUtils";
  import {getAuthorInfo, updateUserInfo, updatePhone, updateEmail, updateAvatar} from "network/userInfo";
  import {getPhoneCode, getEmailCode} from "network/login";
  import LatestPostsItem from "components/common/latestPosts/LatestPostsItem";
  import ImageUpload from "components/common/loading/ImageUpload";
  import {IMAGE_UPLOAD_URL} from "common/common_variable";
  import {SESSION_AVATAR_KEY} from "../../common/common_variable";
  import Footer from "components/content/footer/Footer";
  import ForumItem from "components/common/forum/ForumItem";
  import {getPersonal} from "network/postInfo";
  import MessageBox from "components/content/message/MessageBox";
  import {getUnReadCount, queryNotice} from "network/infoNotice";

  export default {
    name: "Personal",
    components: {
      TabBar,
      PostTabs,
      Celebration,
      Blogger,
      PickCecommended,
      LatestPostsItem,
      ImageUpload,
      Footer,
      ForumItem,
      MessageBox
    },
    data() {
      return {
        curPage: 1,
        size: 6,
        total: 0,
        authorId: '',
        personalArticleList: [],
        isMy: false,//用于判断是否是自己的页面。默认不是
        personalInfoLoad: false,
        settingLoad: false,
        // 个人信息页面数据
        formValidate: {
          authorId: '',
          nickName: '',
          realName: '',
          avatarPath: '',
          sex: '',
          city: '',
          personalProfile: '',

          school: '',
          major: '',  //专业
          admissionTime: '',
          companyName: '',
          positionName: '',
          industry: '',//行业
          workDate: '',//开始工作日期

          phoneNumber: '',
          email: '',
        },
        // 头像对话框
        avatarModal: false,
        // 手机号码-对话框
        phoneModal: false,
        // 邮箱-对话框
        emailModal: false,
        newPhone: '',
        newEmail: '',
        code: '',
        name: '',
        loading: false,

        reload: true,
        uploadUrl: '',

        ruleValidate: {},
        // 论贴 和 留言信息的数据
        itemDataList: [],   // 论贴数组
        postTotal: 100,
        isPostLoad: false,
        postLoading: true,

        unReadCount: 0,
        msgLabel: (h) => {
          return h('div', [
            h('span', '信息'),
            h('Badge', {
              props: {
                count: this.unReadCount
              }
            })
          ])
        },
        informationLoad: false,
        informationList: [],

      }
    },
    created() {
      this.loading = true;
      const authorId = getCookieAuthorId();                     //session存放的id,登录id
      const paramsId = this.$route.params.authorId;             //传送的id，一般是别人的id
      if (paramsId === authorId) {
        this.isMy = true;
      }
      this.authorId = paramsId;
      this.sendPersonalBlog();
      this.uploadUrl = IMAGE_UPLOAD_URL;
      this.getUnRead();
    },
    methods: {
      sendPersonalBlog() {
        getPersonalArticle(this.curPage, this.size, this.authorId).then(res => {
          // console.log(res);
          this.personalArticleList = res.data.data;
          this.total = res.data.total;
        }).finally(() => {
          this.loading = false;
        })
      },
      personalArticleListReload() {
        console.log("reload");
        this.sendPersonalBlog();
      },
      loadMore() {
        if (this.curPage * this.size >= this.total) {
          this.$notify({
            title: '消息提醒',
            message: '没有更多的数据了',
            type: 'success'
          });
          return;
        }
        this.curPage++;
        getPersonalArticle(this.curPage, this.size, this.authorId).then(res => {
          // console.log(res);
          this.personalArticleList.push(...res.data.data);
          this.total = res.data.total;
        })
      },
      clickHandle(name) {
        // console.log(name);
        if (name == 'personalInfo') {
          if (!this.personalInfoLoad) {
            //还没加载
            getAuthorInfo(this.authorId).then(res => {
              // console.log(res);
              this.formValidate = res.data;
              // console.log(this.formValidate);
            })
            // 加载好了，第二次点击不再发送请求
            this.personalInfoLoad = true;
          }
        } else if (name == 'post') {
          if (!this.isPostLoad) {
            //还没加载
            getPersonal(1,6,this.authorId).then(res => {
              this.itemDataList = res.data.data;
              this.postTotal = res.data.total;
            }).finally(() => {
              this.postLoading = false;
            })

            // 加载好了，第二次点击不再发送请求
            this.isPostLoad = true;
          }
        }else if (name == 'information') {
          if (!this.informationLoad) {
            //还没加载
            queryNotice(this.authorId).then(res => {
              this.informationList = res.data;
              this.unReadCount = 0;
            })
            // 加载好了，第二次点击不再发送请求
            this.informationLoad = true;
          }
        }
      },
      // 个人信息页面方法
      handleSubmit(name) {
        this.$refs[name].validate((valid) => {
          if (valid) {
            // this.$Message.success('Success!');
            // 发送请求更改信息
            updateUserInfo(this.formValidate).then(res => {
              this.$notify({
                message: res.msg,
                type: 'success'
              });
            })
          } else {
            this.$Message.error('Fail!');
          }
        })
      },
      handleReset(name) {
        this.$refs[name].resetFields();
      },
      // 点击头像
      itemClick(message) {

      },
      // 对话框请求
      avatarOk() {
      },
      avatarCancel() {
      },
      // 获取验证码, 手机/邮箱
      getCode(path) {
        if ('phone' == path) {
          if (this.newPhone == '') {
            this.$notify({
              message: '请输入新号码',
              type: "warning"
            })
            return;
          }
          getPhoneCode(this.newPhone).then(res => {
            this.commonMethod(res);
          })
        } else if ('email' == path) {
          if (this.newEmail == '') {
            this.$notify({
              message: '请输入新邮箱',
              type: "warning"
            })
            return;
          }
          getEmailCode(this.newEmail).then(res => {
            this.commonMethod(res);
          })
        }
      },
      update(path) {
        if ('phone' == path) {
          if (this.newPhone == '' || this.code == '') {
            this.$notify({
              message: '新号码和验证码可一个都不能少',
              type: "warning"
            })
            return;
          }
          updatePhone(this.newPhone, this.code).then(res => {
            this.commonMethod(res);
            // this.formValidate.phoneNumber = ''; //更换
            this.newPhone = '';
            this.phoneModal = false;
          })
        } else if ('email' == path) {
          if (this.newEmail == '' || this.code == '') {
            this.$notify({
              message: '新邮箱和验证码可一个都不能少',
              type: "warning"
            })
            return;
          }
          updateEmail(this.newEmail, this.code).then(res => {
            console.log(res);
            this.commonMethod(res);
            // this.formValidate.email = ''; //更换
            this.newEmail = '';
            this.emailModal = false;
          })
        }
        this.code = '';
      },
      commonMethod(res) {
        if (res.status == 2000) {
          this.$notify({
            title: '成功',
            message: res.data,
            type: 'success'
          });
        } else if (res.status == 400) {
          this.$notify.error({
            title: '错误',
            message: res.msg
          });
        } else if (res.status == 5003) {
          this.$notify.error({
            title: '错误',
            message: res.msg + res.data
          });
        }
      },
      change(current) {
        getPersonalArticle(current, 6, this.authorId).then(res => {
          this.personalArticleList = res.data.data;
          this.total = res.data.total;
        })
      },
      closeUpload() {
        this.avatarModal = false;
      },
      // 上传文件之前的钩子 图片大小检验
      beforeUpload(file) {
        const isLt2M = file.size / 1024 / 1024 < 2;
        if (!isLt2M) {
          this.$notify({
            title: '警告',
            message: '图片大小不允许超过2M',
            type: 'warning'
          });
        }
        return isLt2M;
      },
      uploadSuccess(response, file, fileList) {
        if (response.status == '2000') {
          const newAvatarPath = response.data;
          updateAvatar(newAvatarPath).then(res => {
            this.$notify({
              message: res.msg,
              type: 'success'
            });
          })
          sessionStorage.setItem(SESSION_AVATAR_KEY, newAvatarPath);
          let info = {
            avatarPath: newAvatarPath
          }
          this.$store.commit('set', info);
          setCookieAvatarPath(newAvatarPath);
          this.formValidate.avatarPath = newAvatarPath;
          this.reloadAvatar();
          this.closeUpload();
        } else {
          this.$notify.info({
            title: '封面图片上传失败',
            message: response.msg
          });
          fileList.splice(1, 1);//fileList的失败文件
        }
      },
      uploadError(err, file, fileList) {
        this.$notify.error({
          message: '出错了，请联系系统管理员'
        });
      },
      closeUpload(select) {
        // this.$parent.centerDialogVisible = false;// 直接改会报错
        if (select === 2) {
          this.returnData.isRelease = true;//必须设置为true，才会上传
        }
        this.$emit('item-click', this.returnData);
      },
      async reloadAvatar() {
        this.reload = false;
        await this.$nextTick();
        this.reload = true;
      },
      itemDataListReload() {
        this.postChange(1);
      },
      postChange(curPage){
        this.postLoading = true;
        getPersonal(curPage,6,this.authorId).then(res => {
          this.itemDataList = res.data.data;
          this.postTotal = res.data.total;
        }).finally(() => {
          this.postLoading = false;
        })
      },
      getUnRead(){
        getUnReadCount(this.authorId).then(res => {
          this.unReadCount = Number.parseInt(res.data);
        })
      }
    },
    computed: {
      addHeader() {
        return {
          'Authorization': getCookie()
        }
      }
    }
  }
</script>

<style scoped>
  .space {
    height: 30px;
  }

  .box-line {
    border-bottom: 1px solid #f3f3f3;
  }

  .finger {
    cursor: pointer;
  }

  .line32 {
    padding-right: 10px;
    text-align: right;
    line-height: 32px;
  }

  .span-text {
    color: #fe5070;
    width: 100%;
    text-align: center;
    margin: 0 auto;
  }

  .forum-main {
    /*padding: 5px;*/
    border: 1px solid #ebebeb;
    border-radius: 10px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04)
  }

  .space10{
    height: 10px;
  }

  .message-box{
    width: 100%;
    height: 50px;
    padding: 5px;
    margin-top: 15px;
    border: 1px solid rgba(207, 204, 204, 0.5);
    border-radius: 10px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04)
  }

  .box-head{
    width: 100%;
    text-align: left;
  }

  .box-body{

  }
</style>