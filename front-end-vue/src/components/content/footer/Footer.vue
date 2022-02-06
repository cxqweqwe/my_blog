<template>
  <footer>
    <div class="container-xl">
      <div class="footer-inner">
        <div class="row d-flex align-items-center gy-4">
          <!-- copyright text -->
          <div class="col-md-4">
            <span class="copyright">© 2021 Bernie_fang. All rights reserved.</span>
            <br/>
            <span class="copyright"><a href="http://beian.miit.gov.cn/" target="_blank" title="备案号">© 粤ICP备2021147694号</a></span>
          </div>

          <!-- social icons -->
          <div class="col-md-4 text-center">
            <ul class="social-icons list-unstyled list-inline mb-0">
              <li class="list-inline-item">
                <a href="https://gitee.com/bernie__gitee/forum--blog" target="_blank">
                  <svg t="1634196049924" class="icon" viewBox="0 0 1024 1024" version="1.1"
                       xmlns="http://www.w3.org/2000/svg"
                       p-id="1775" width="16" height="16">
                    <path
                        d="M512 1024C229.248 1024 0 794.752 0 512S229.248 0 512 0s512 229.248 512 512-229.248 512-512 512z m259.168-568.896h-290.752a25.28 25.28 0 0 0-25.28 25.28l-0.032 63.232c0 13.952 11.296 25.28 25.28 25.28h177.024a25.28 25.28 0 0 1 25.28 25.28v12.64a75.84 75.84 0 0 1-75.84 75.84h-240.224a25.28 25.28 0 0 1-25.28-25.28v-240.192a75.84 75.84 0 0 1 75.84-75.84h353.92a25.28 25.28 0 0 0 25.28-25.28l0.064-63.2a25.312 25.312 0 0 0-25.28-25.312H417.184a189.632 189.632 0 0 0-189.632 189.6v353.952c0 13.952 11.328 25.28 25.28 25.28h372.928a170.656 170.656 0 0 0 170.656-170.656v-145.376a25.28 25.28 0 0 0-25.28-25.28z"
                        p-id="1776" fill="#fe5471">
                    </path>
                  </svg>
                </a>
              </li>
              <li class="list-inline-item"><a href="https://gitee.com/bernie__gitee/forum--blog" target="_blank"><Icon type="logo-github"/></a></li>
              <li class="list-inline-item"><a href="mailto:bernie_fang@163.com" title="email me"><Icon type="md-mail"/></a></li>
            </ul>
          </div>

          <!-- go to top button -->
          <div class="col-md-4">
            <a href="#" id="return-to-top" class="float-md-end"><i class="icon-arrow-up"></i>Back to Top</a>
          </div>
        </div>
      </div>
    </div>
  </footer>
</template>

<script>
export default {
  name: "Footer"
}
</script>

<style scoped>
.copyright a{
  color: #9faabb;
}

.copyright a:hover {
  color: #9faabb;
  background: linear-gradient(to bottom right, #FFA387, #FE4F70);
  -webkit-background-clip:text;
  -webkit-text-fill-color:transparent;
}

</style>