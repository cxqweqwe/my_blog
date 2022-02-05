<template>
  <div class="widget rounded" v-loading="loading">
    <div class="a">
      <div class="info">
        <div class="block center">
          <el-avatar :size="80" :src="avatarPath">U</el-avatar>
          <div>这里是名字</div>
        </div>
        <div class="personal-info">
          <el-row>
            <el-col :span="12">
              <div class="center"><span>{{ blogger.beenViews }}</span>被访问</div>
            </el-col>
            <el-col :span="12">
              <div class="center">原创<span>{{ blogger.original }}</span></div>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <div class="center"><span>4</span>被订阅</div>
            </el-col>
            <el-col :span="12">
              <div class="center">入驻<span>{{ blogger.settled }}</span>月</div>
            </el-col>
          </el-row>

          <div class="space-10"></div>
          <el-collapse accordion @change="handleChange">
            <el-collapse-item>
              <template slot="title">
                {{foldName}}
              </template>
              <el-row>
                <el-col :span="5">
                  <div class="center">个人简介:</div>
                </el-col>
                <el-col :span="19">
                  <div class="center">{{ blogger.personalProfile }}</div>
                </el-col>
              </el-row>
              <el-row>
                <el-col :span="5">
                  <div class="center">毕业院校:</div>
                </el-col>
                <el-col :span="19">
                  <div class="center">{{ blogger.school }}</div>
                </el-col>
              </el-row>
              <el-row>
                <el-col :span="5">
                  <div class="center">目前就职:</div>
                </el-col>
                <el-col :span="19">
                  <div class="center">{{ blogger.companyName }}</div>
                </el-col>
              </el-row>
            </el-collapse-item>
          </el-collapse>

        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import {getBlogger} from "network/userInfo";

  export default {
    name: "Blogger",
    props: {},
    data() {
      return {
        loading: false,
        avatarPath: '',
        blogger: {},
        subscribed: '',//TODO: 被订阅
        showMore: false,
        foldName: '展示更多'
      }
    },
    created() {
      this.loading = true;
      this.authorId = this.$route.params.authorId;
      this.avatarPath = this.$store.state.authorInfo.avatarPath;
      // console.log(this.authorId);
      this.getUserInfo();
    },
    methods: {
      handleChange(activeNames) {
        this.showMore = !this.showMore;
        if (this.showMore) {
          this.foldName = '收起';
          return;
        }
        this.foldName = '展示更多';
      },
      getUserInfo() {
        getBlogger().then(res => {
          this.blogger = res.data;
        }).finally(()=>{
          this.loading = false;
        })
      }
    }
  }
</script>

<style scoped>
  .a {
    width: 100%;
    height: 100%;
    /*height: 890px;*/
    /*background-color: #86b7fe;*/
  }

  .info {
    display: block;
  }

  .center {
    text-align: center;
  }

  .space {
    height: 50px;
  }

  .space-10 {
    height: 10px;
  }

  .personal-info span {
    font-size: 22px;
    font-weight: bold;
    color: black;
  }
</style>