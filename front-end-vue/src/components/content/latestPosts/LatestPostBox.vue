<template>
	<div class="padding-30 rounded bordered">
		<div class="row">

			<LatestPostsItem v-for="(item,index) in dataList " :key="index" :item=item></LatestPostsItem>

<!--			<div class="col-md-12 col-sm-6">-->
<!--				&lt;!&ndash; post &ndash;&gt;-->
<!--				<div class="post post-list clearfix">-->
<!--					<div class="thumb rounded">-->
<!--						<a href="blog-single.html">-->
<!--							<div class="inner">-->
<!--								<img src="~assets/img/posts/latest-sm-2.jpg" alt="post-title" />-->
<!--							</div>-->
<!--						</a>-->
<!--					</div>-->
<!--					<div class="details">-->
<!--						<ul class="meta list-inline mb-3">-->
<!--							<li class="list-inline-item"><a href="#"><img src="images/other/author-sm.png" class="author" alt="author"/>Katen Doe</a></li>-->
<!--							<li class="list-inline-item"><a href="#">Lifestyle</a></li>-->
<!--							<li class="list-inline-item">29 March 2021</li>-->
<!--						</ul>-->
<!--						<h5 class="post-title"><a href="blog-single.html">Master The Art Of Nature With These 7 Tips</a></h5>-->
<!--						<p class="excerpt mb-0">A wonderful serenity has taken possession of my entire soul, like these sweet mornings</p>-->
<!--						<div class="post-bottom clearfix d-flex align-items-center">-->
<!--							<div class="social-share me-auto">-->
<!--								<button class="toggle-button icon-share"></button>-->
<!--								<ul class="icons list-unstyled list-inline mb-0">-->
<!--									<li class="list-inline-item"><a href="#"><i class="fab fa-facebook-f"></i></a></li>-->
<!--									<li class="list-inline-item"><a href="#"><i class="fab fa-twitter"></i></a></li>-->
<!--									<li class="list-inline-item"><a href="#"><i class="fab fa-linkedin-in"></i></a></li>-->
<!--									<li class="list-inline-item"><a href="#"><i class="fab fa-pinterest"></i></a></li>-->
<!--									<li class="list-inline-item"><a href="#"><i class="fab fa-telegram-plane"></i></a></li>-->
<!--									<li class="list-inline-item"><a href="#"><i class="far fa-envelope"></i></a></li>-->
<!--								</ul>-->
<!--							</div>-->
<!--							<div class="more-button float-end">-->
<!--								<a href="blog-single.html"><span class="icon-options"></span></a>-->
<!--							</div>-->
<!--						</div>-->
<!--					</div>-->
<!--				</div>-->
<!--			</div>-->

<!--			<div class="col-md-12 col-sm-6">-->
<!--				&lt;!&ndash; post &ndash;&gt;-->
<!--				<div class="post post-list clearfix">-->
<!--					<div class="thumb rounded">-->
<!--										<span class="post-format-sm">-->
<!--											<i class="icon-camrecorder"></i>-->
<!--										</span>-->
<!--						<a href="blog-single.html">-->
<!--							<div class="inner">-->
<!--								<img src="~assets/img/posts/latest-sm-3.jpg" alt="post-title" />-->
<!--							</div>-->
<!--						</a>-->
<!--					</div>-->
<!--					<div class="details">-->
<!--						<ul class="meta list-inline mb-3">-->
<!--							<li class="list-inline-item"><a href="#"><img src="images/other/author-sm.png" class="author" alt="author"/>Katen Doe</a></li>-->
<!--							<li class="list-inline-item"><a href="#">Fashion</a></li>-->
<!--							<li class="list-inline-item">29 March 2021</li>-->
<!--						</ul>-->
<!--						<h5 class="post-title"><a href="blog-single.html">Facts About Business That Will Help You Success</a></h5>-->
<!--						<p class="excerpt mb-0">A wonderful serenity has taken possession of my entire soul, like these sweet mornings</p>-->
<!--						<div class="post-bottom clearfix d-flex align-items-center">-->
<!--							<div class="social-share me-auto">-->
<!--								<button class="toggle-button icon-share"></button>-->
<!--								<ul class="icons list-unstyled list-inline mb-0">-->
<!--									<li class="list-inline-item"><a href="#"><i class="fab fa-facebook-f"></i></a></li>-->
<!--									<li class="list-inline-item"><a href="#"><i class="fab fa-twitter"></i></a></li>-->
<!--									<li class="list-inline-item"><a href="#"><i class="fab fa-linkedin-in"></i></a></li>-->
<!--									<li class="list-inline-item"><a href="#"><i class="fab fa-pinterest"></i></a></li>-->
<!--									<li class="list-inline-item"><a href="#"><i class="fab fa-telegram-plane"></i></a></li>-->
<!--									<li class="list-inline-item"><a href="#"><i class="far fa-envelope"></i></a></li>-->
<!--								</ul>-->
<!--							</div>-->
<!--							<div class="more-button float-end">-->
<!--								<a href="blog-single.html"><span class="icon-options"></span></a>-->
<!--							</div>-->
<!--						</div>-->
<!--					</div>-->
<!--				</div>-->
<!--			</div>-->

<!--			<div class="col-md-12 col-sm-6">-->
<!--				&lt;!&ndash; post &ndash;&gt;-->
<!--				<div class="post post-list clearfix">-->
<!--					<div class="thumb rounded">-->
<!--						<a href="blog-single.html">-->
<!--							<div class="inner">-->
<!--								<img src="~assets/img/posts/latest-sm-4.jpg" alt="post-title" />-->
<!--							</div>-->
<!--						</a>-->
<!--					</div>-->
<!--					<div class="details">-->
<!--						<ul class="meta list-inline mb-3">-->
<!--							<li class="list-inline-item"><a href="#"><img src="images/other/author-sm.png" class="author" alt="author"/>Katen Doe</a></li>-->
<!--							<li class="list-inline-item"><a href="#">Politic</a></li>-->
<!--							<li class="list-inline-item">29 March 2021</li>-->
<!--						</ul>-->
<!--						<h5 class="post-title"><a href="blog-single.html">Your Light Is About To Stop Being Relevant</a></h5>-->
<!--						<p class="excerpt mb-0">A wonderful serenity has taken possession of my entire soul, like these sweet mornings</p>-->
<!--						<div class="post-bottom clearfix d-flex align-items-center">-->
<!--							<div class="social-share me-auto">-->
<!--								<button class="toggle-button icon-share"></button>-->
<!--								<ul class="icons list-unstyled list-inline mb-0">-->
<!--									<li class="list-inline-item"><a href="#"><i class="fab fa-facebook-f"></i></a></li>-->
<!--									<li class="list-inline-item"><a href="#"><i class="fab fa-twitter"></i></a></li>-->
<!--									<li class="list-inline-item"><a href="#"><i class="fab fa-linkedin-in"></i></a></li>-->
<!--									<li class="list-inline-item"><a href="#"><i class="fab fa-pinterest"></i></a></li>-->
<!--									<li class="list-inline-item"><a href="#"><i class="fab fa-telegram-plane"></i></a></li>-->
<!--									<li class="list-inline-item"><a href="#"><i class="far fa-envelope"></i></a></li>-->
<!--								</ul>-->
<!--							</div>-->
<!--							<div class="more-button float-end">-->
<!--								<a href="blog-single.html"><span class="icon-options"></span></a>-->
<!--							</div>-->
<!--						</div>-->
<!--					</div>-->
<!--				</div>-->
<!--			</div>-->

		</div>
		<!-- load more button -->
		<div class="text-center">
			<button class="btn btn-simple" @click="loadMore()">Load More</button>
		</div>
	</div>
</template>

<script>
	import LatestPostsItem from "components/common/latestPosts/LatestPostsItem";
  import {getLatestPosts} from "../../../network/article";

  export default {
    name: "LatestPostBox",
		components: {
      LatestPostsItem
		},
    data() {
      return {
        dataList: [],
        curPage: 1,
        size: 4,
        total: 0
      }
    },
    created() {
      this.sendRequest();
    },
    methods: {
      sendRequest(){
        getLatestPosts(this.curPage,this.size).then(res => {
          console.log(res);
          this.dataList = res.data.data;
          this.total = res.data.total;
        })
      },
      loadMore(){
        if (this.curPage * this.size >= this.total){
          this.$notify({
            title: '消息提醒',
            message: '没有更多的数据了',
            type: 'success'
          });
          return;
        }
        this.curPage++;
        getLatestPosts(this.curPage,this.size).then(res => {
          // console.log(res);
          this.dataList.push(...res.data.data);
        })

      }
    }
  }
</script>

<style scoped>

</style>