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
              <li class="list-inline-item"><a href="https://gitee.com/bernie__gitee/forum--blog" target="_blank"><Icon type="logo-github" /></a></li>
              <li class="list-inline-item"><a href="mailto:bernie_fang@163.com" title="email me"><Icon type="md-mail" /></a></li>
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