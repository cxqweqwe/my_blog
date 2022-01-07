<template>
  <div class="widget rounded">
    <div class="a">
      <div class="info">
        <div class="block center">
          <el-avatar :size="80" :src="avatarUrl">U</el-avatar>
          <div>这里是名字</div>
        </div>
        <div class="personal-info">
          <el-row>
            <el-col :span="12">
              <div class="center"><span>3,690</span>被访问</div>
            </el-col>
            <el-col :span="12">
              <div class="center">原创<span>18</span></div>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <div class="center"><span>4</span>被订阅</div>
            </el-col>
            <el-col :span="12">
              <div class="center">入驻<span>3</span>年</div>
            </el-col>
          </el-row>

          <div class="space-10"></div>
          <el-collapse accordion  @change="handleChange">
            <el-collapse-item>
              <template slot="title">
                {{foldName}}
              </template>
              <el-row>
                <el-col :span="24">
                  <div class="center">个人简介：我是一阵风：我是一阵风：我是一阵风：我是一阵风：我是一阵风：我是一阵风：我是一阵风：我是一阵风：</div>
                </el-col>
              </el-row>
              <el-row>
                <el-col :span="5">
                  <div class="center">毕业院校:</div>
                </el-col>
                <el-col :span="19">
                  <div class="center">电子科技大学中山学院</div>
                </el-col>
              </el-row>
              <el-row>
                <el-col :span="5">
                  <div class="center">目前就职:</div>
                </el-col>
                <el-col :span="19">
                  <div class="center">电子科技大学中山学院,电子科技大学中山学院</div>
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
  export default {
    name: "Blogger",
    props: {},
    data() {
      return {
        avatarUrl: '',
        foldName: '展示更多'
      }
    },
    created() {
    },
    methods: {
      handleChange(activeNames ){
        if (activeNames == 15){
          this.foldName = '收起';
          return;
        }
        this.foldName = '展示更多';
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

  .info{
    display: block;
  }

  .center {
    text-align: center;
  }

  .space{
    height: 50px;
  }
  .space-10{
    height: 10px;
  }

  .personal-info span {
    font-size: 22px;
    font-weight: bold;
    color: black;
  }
</style>