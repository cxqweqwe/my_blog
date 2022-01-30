<template>
  <div>
    <TabBar></TabBar>

    <section class="main-content">
      <div class="container-xl">
        <div class="row gy-4">
          <div class="col-lg-8">

            <div v-if="isMy">
              <Tabs @on-click="clickHandle">
                <TabPane label="我的博客" icon="ios-book" name="myBlog">
<!--                  <div class="row ">-->
<!--                    <PickCecommended v-for="(item,index) in personalArticleList" :key="index"-->
<!--                                     :pickData="item"></PickCecommended>-->
<!--                  </div>-->
                  <div class="padding-30 rounded bordered">
                    <div class="row">
                      <LatestPostsItem v-for="(itemData,index) in personalArticleList" :key="index" :item="itemData" ></LatestPostsItem>
                    </div>
                  </div>
                  <div class="space"></div>
                  <div class="box-line"></div>
                  <div class="space"></div>
                  <div class="text-center">
                    <button class="btn btn-simple" @click="loadMore()">Load More</button>
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
                    <!--// TODO: 上传文件的组件-->
                  </Modal>

                  <Modal
                      v-model="phoneModal"
                      title="绑定/更换手机号"
                      style="height:300px"
                      :footer-hide=true>
                    <Row>
                      <Col span="4" class="line32">更换手机号码</Col>
                      <Col span="15"><Input v-model="newPhone" placeholder="Enter phone" clearable /></Col>
                      <Col span="5"><Button type="dashed" @click="getCode('phone')">获取验证码</Button></Col>
                    </Row>
                    <Row>
                      <Col span="4" class="line32">手机验证码</Col>
                      <Col span="15"><Input v-model="code" placeholder="Enter code" clearable /></Col>
                      <Col span="5"><Button type="dashed" @click="update('email')"> 更 新 信 息 </Button></Col>
                    </Row>
                  </Modal>
                  <Modal
                      v-model="emailModal"
                      title="绑定/更换邮箱"
                      :footer-hide=true>
                    <Row>
                      <Col span="5" class="line32">更换邮箱地址</Col>
                      <Col span="14"><Input v-model="newEmail" placeholder="Enter email" clearable /></Col>
                      <Col span="5"><Button type="dashed" @click="getCode('email')">获取验证码</Button></Col>
                    </Row>
                    <Row>
                      <Col span="5" class="line32">邮箱验证码</Col>
                      <Col span="14"><Input v-model="code" placeholder="Enter code" clearable /></Col>
                      <Col span="5"><Button type="dashed" @click="update('email')"> 更 新 信 息 </Button></Col>
                    </Row>
                  </Modal>


                </TabPane>
                <TabPane label="设置" icon="ios-build" name="setting">
                  设置
                </TabPane>
              </Tabs>
            </div>
            <div v-else class="a">
              asdb
            </div>

          </div>
          <div class="col-lg-4">
            <Blogger></Blogger>
            <PostTabs></PostTabs>
            <div class="space"></div>
            <Celebration></Celebration>

          </div>
        </div>
      </div>
    </section>

  </div>
</template>

<script>
  import TabBar from "components/common/tabBar/TabBar";
  import PostTabs from "components/content/featured/PostTabs";
  import Celebration from "components/content/celebration/Celebration";
  import Blogger from "components/common/ blogger/Blogger";
  import PickCecommended from "components/content/main/PickCecommended";

  import {getPersonalArticle} from "network/article";
  import {getCookie, getCookieAuthorId} from "common/cookieUtils";
  import {getAuthorInfo, updateUserInfo, updatePhone, updateEmail} from "network/userInfo";
  import {getBase64} from "common/imageUtils";
  import {getPhoneCode, getEmailCode} from "network/login";
  import LatestPostsItem from "components/common/latestPosts/LatestPostsItem";

  export default {
    name: "Personal",
    components: {
      TabBar,
      PostTabs,
      Celebration,
      Blogger,
      PickCecommended,
      LatestPostsItem,
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

        ruleValidate: {
          //   name: [
          //     {required: true, message: 'The name cannot be empty', trigger: 'blur'}
          //   ],
          //   mail: [
          //     {required: true, message: 'Mailbox cannot be empty', trigger: 'blur'},
          //     {type: 'email', message: 'Incorrect email format', trigger: 'blur'}
          //   ],
          //   city: [
          //     {required: true, message: 'Please select the city', trigger: 'change'}
          //   ],
          //   gender: [
          //     {required: true, message: 'Please select gender', trigger: 'change'}
          //   ],
          //   interest: [
          //     {required: true, type: 'array', min: 1, message: 'Choose at least one hobby', trigger: 'change'},
          //     {type: 'array', max: 2, message: 'Choose two hobbies at best', trigger: 'change'}
          //   ],
          //   date: [
          //     {required: true, type: 'date', message: 'Please select the date', trigger: 'change'}
          //   ],
          //   time: [
          //     {required: true, type: 'string', message: 'Please select time', trigger: 'change'}
          //   ],
          //   desc: [
          //     {required: true, message: 'Please enter a personal introduction', trigger: 'blur'},
          //     {type: 'string', min: 20, message: 'Introduce no less than 20 words', trigger: 'blur'}
          //   ]
        }
      }
    },
    created() {
      const authorId = getCookieAuthorId();                     //session存放的id,登录id
      const paramsId = this.$route.params.authorId;             //传送的id，一般是别人的id
      if (paramsId === authorId) {
        this.isMy = true;
      }
      this.authorId = paramsId;
      this.sendPersonalBlog();
    },
    methods: {
      sendPersonalBlog() {
        getPersonalArticle(this.curPage, this.size, this.authorId).then(res => {
          // console.log(res);
          this.personalArticleList = res.data.data;
          this.total = res.data.total;
        })
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
        } else if (name = 'setting') {
          if (!this.settingLoad) {
            //还没加载


            // 加载好了，第二次点击不再发送请求
            this.settingLoad = true;
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
        this.$Message.info('Clicked ok');
      },
      avatarCancel() {

      },

      // 获取验证码, 手机/邮箱
      getCode(path){
        if ('phone'==path){
          getPhoneCode(this.newPhone).then(res => {
            this.commonMethod(res);
          })
        }else if ('email'==path){
          console.log(this.newEmail);
          getEmailCode(this.newEmail).then(res => {
            this.commonMethod(res);
          })
        }
      },
      update(path){
        if ('phone'==path){
          updatePhone(this.newPhone,this.code).then(res => {
            this.commonMethod(res);
            // this.formValidate.phoneNumber = ''; //更换
            this.newPhone = '';
            this.phoneModal = false;
          })
        }else if ('email'==path){
          updateEmail(this.newEmail,this.code).then(res => {
            console.log(res);
            this.commonMethod(res);
            // this.formValidate.email = ''; //更换
            this.newEmail = '';
            this.emailModal = false;
          })
        }
        this.code = '';
      },


      commonMethod(res){
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
      }
    }
  }
</script>

<style scoped>
  .a {
    height: 600px;
    width: 100%;
    background-color: #0a53be;
  }

  .space {
    height: 30px;
  }

  .box-line {
    border-bottom: 1px solid #f3f3f3;
  }

  .tabber-bottom {
    color: #1c0202;
  }

  .finger {
    cursor: pointer;
  }

  .line32{
    padding-right: 10px;
    text-align: right;
    line-height: 32px;
  }

</style>