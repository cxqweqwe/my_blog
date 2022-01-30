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
                    <el-avatar :size="60"
                               src="data:image/jpg;base64,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">
                      U
                    </el-avatar>
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
                      <Button type="primary" @click="handleSubmit('formValidate')">Submit</Button>
                      <Button @click="handleReset('formValidate')" style="margin-left: 8px">Reset</Button>
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
          console.log(res);
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