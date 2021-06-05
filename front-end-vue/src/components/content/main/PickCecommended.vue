<template>
	<div class="col-sm-6">
		<!-- post -->
		<div class="post">
			<div class="thumb rounded">
				<!--				<a href="category.html" class="category-badge position-absolute">Lifestyle</a>
								<span class="post-format">
															<i class="icon-picture"></i>
														</span>-->
<!--				<a :href="hrefLink">
					<div class="inner">
						<img src="~assets/img/wave.svg" alt="post-title"/>
					</div>
				</a>-->
			</div>
			<ul class="meta list-inline mt-4 mb-0">
				<li class="list-inline-item"><a href="#"><img src="~assets/img/posts/trending-lg-1.jpg" class="author" alt="author"/>{{authorName}}</a>
				</li>
				<li class="list-inline-item">{{time}}</li>
			</ul>
			<h5 class="post-title mb-3 mt-3"><a href="blog-single.html">15 Unheard Ways To Achieve Greater Walker</a></h5>
			<p class="excerpt mb-0">{{introduction}}</p>
		</div>
	</div>
</template>

<script>
  export default {
    name: "PickCecommended",
    props: {
      hrefLink: {
        type: String,
        default: "blog-single.html"
      },
      imgLink: {
        type: String,
        default: "~assets/img/posts/editor-lg.jpg"
      },
      authorImg: {
        type: String,
				default: "~assets/img/other/author-sm.png"
			},
      authorName:{
        type: String,
				default: "Katen Doe"
			},
      time: {
        type: String,
        default: "29 March 2021"
      },
      introduction: {
        type: String,
        default: "A wonderful serenity has taken possession of my entire soul, like these sweet mornings of" +
          " spring which I enjoy"
      }
    }
  }
</script>

<style scoped>

</style>