<template>
  <div id="footer" class="container-xl main-content">
    <hr/>
    <div class="space"></div>
    <div class="space"></div>
    <Row class-name="row">
      <Col span="6" class="copyRInfo">
        <a href="mailto:fang_jia_xin@qq.com">
          <Icon type="ios-mail"/>
          联系作者
        </a>
      </Col>
      <Col span="12" class="text">
        本网站由Bernie_fang制作，版权归Bernie_fang所有 <br/>
        本网站为作者的毕设项目，开源代码
        <a href="https://gitee.com/bernie__gitee/forum--blog" target="_blank">
          <svg t="1634196049924" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg"
               p-id="1775" width="20" height="20">
            <path
                d="M512 1024C229.248 1024 0 794.752 0 512S229.248 0 512 0s512 229.248 512 512-229.248 512-512 512z m259.168-568.896h-290.752a25.28 25.28 0 0 0-25.28 25.28l-0.032 63.232c0 13.952 11.296 25.28 25.28 25.28h177.024a25.28 25.28 0 0 1 25.28 25.28v12.64a75.84 75.84 0 0 1-75.84 75.84h-240.224a25.28 25.28 0 0 1-25.28-25.28v-240.192a75.84 75.84 0 0 1 75.84-75.84h353.92a25.28 25.28 0 0 0 25.28-25.28l0.064-63.2a25.312 25.312 0 0 0-25.28-25.312H417.184a189.632 189.632 0 0 0-189.632 189.6v353.952c0 13.952 11.328 25.28 25.28 25.28h372.928a170.656 170.656 0 0 0 170.656-170.656v-145.376a25.28 25.28 0 0 0-25.28-25.28z"
                p-id="1776" fill="#fe5471">
            </path>
          </svg>
        </a>

      </Col>
      <Col span="6" class="copyRInfo">
        <el-button size="medium" round @click="backToTop"><i class="el-icon-arrow-up"/> Back to Top</el-button>
      </Col>
    </Row>
  </div>
</template>

<script>
export default {
  name: "Footer",
  data() {
    return {
      scrollY: 0
    }
  },
  mounted() {
    // 添加监听
    window.addEventListener('scroll', this.handleScroll);
  },
  methods: {
    backToTop() {
      scrollTo(0, 0);
    },
    handleScroll() {
      let scrollTopY = window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop;
      this.scrollY = scrollTopY;
    }
  }
}
</script>

<style scoped>
#footer {
  height: 120px;
  width: 100%;
  background-color: #fbfbfb;
}

.space {
  height: 15px;
}

/*.row {*/
/*  height: 80px;*/
/*}*/

.copyRInfo {
  text-align: center;
}

.copyRInfo a {
  text-decoration: none;
  color: #515a6e;
}

.copyRInfo a:hover {
  color: #fe5471;
}

.text {
  text-align: center;
  vertical-align: middle;
  cursor: pointer;
}

.copyRInfo .el-button:hover {
  color: #fe5471;
  background-color: #f8d5cc;
  border-color: #f8d5cc;
}
</style>