<template>


		<div class="col-lg-4">
			<!-- post tabs -->
			<div class="post-tabs rounded bordered">
				<!-- tab navs -->
				<ul class="nav nav-tabs nav-pills nav-fill" id="postsTab" role="tablist">
					<li class="nav-item" role="presentation">
						<button aria-controls="popular" aria-selected="true" class="nav-link active" data-bs-target="#popular"
										data-bs-toggle="tab" id="popular-tab" role="tab" type="button">Popular
						</button>
					</li>
					<li class="nav-item" role="presentation">
						<button aria-controls="recent" aria-selected="false" class="nav-link" data-bs-target="#recent"
										data-bs-toggle="tab" id="recent-tab" role="tab" type="button">Recent
						</button>
					</li>
				</ul>
				<!-- tab contents -->
				<div class="tab-content" id="postsTabContent">
					<!-- loader -->
					<div class="lds-dual-ring"></div>
					<!-- popular posts -->
					<div aria-labelledby="popular-tab" class="tab-pane fade show active" id="popular" role="tabpanel">
						<!-- post -->
						<div class="post post-list-sm circle">
							<div class="thumb circle">
								<a href="blog-single.html">
									<div class="inner">
										<img src="images/posts/tabs-1.jpg" alt="post-title"/>
									</div>
								</a>
							</div>
							<div class="details clearfix">
								<h6 class="post-title my-0"><a href="blog-single.html">3 Easy Ways To Make Your iPhone Faster</a>
								</h6>
								<ul class="meta list-inline mt-1 mb-0">
									<li class="list-inline-item">29 March 2021</li>
								</ul>
							</div>
						</div>
						<!-- post -->
						<div class="post post-list-sm circle">
							<div class="thumb circle">
								<a href="blog-single.html">
									<div class="inner">
										<img src="images/posts/tabs-2.jpg" alt="post-title"/>
									</div>
								</a>
							</div>
							<div class="details clearfix">
								<h6 class="post-title my-0"><a href="blog-single.html">An Incredibly Easy Method That Works For
									All</a></h6>
								<ul class="meta list-inline mt-1 mb-0">
									<li class="list-inline-item">29 March 2021</li>
								</ul>
							</div>
						</div>
						<!-- post -->
						<div class="post post-list-sm circle">
							<div class="thumb circle">
								<a href="blog-single.html">
									<div class="inner">
										<img src="images/posts/tabs-3.jpg" alt="post-title"/>
									</div>
								</a>
							</div>
							<div class="details clearfix">
								<h6 class="post-title my-0"><a href="blog-single.html">10 Ways To Immediately Start Selling
									Furniture</a></h6>
								<ul class="meta list-inline mt-1 mb-0">
									<li class="list-inline-item">29 March 2021</li>
								</ul>
							</div>
						</div>
						<!-- post -->
						<div class="post post-list-sm circle">
							<div class="thumb circle">
								<a href="blog-single.html">
									<div class="inner">
										<img src="images/posts/tabs-4.jpg" alt="post-title"/>
									</div>
								</a>
							</div>
							<div class="details clearfix">
								<h6 class="post-title my-0"><a href="blog-single.html">15 Unheard Ways To Achieve Greater
									Walker</a>
								</h6>
								<ul class="meta list-inline mt-1 mb-0">
									<li class="list-inline-item">29 March 2021</li>
								</ul>
							</div>
						</div>
					</div>
					<!-- recent posts -->
					<div aria-labelledby="recent-tab" class="tab-pane fade" id="recent" role="tabpanel">
						<!-- post -->
						<div class="post post-list-sm circle">
							<div class="thumb circle">
								<a href="blog-single.html">
									<div class="inner">
										<img src="images/posts/tabs-2.jpg" alt="post-title"/>
									</div>
								</a>
							</div>
							<div class="details clearfix">
								<h6 class="post-title my-0"><a href="blog-single.html">An Incredibly Easy Method That Works For
									All</a></h6>
								<ul class="meta list-inline mt-1 mb-0">
									<li class="list-inline-item">29 March 2021</li>
								</ul>
							</div>
						</div>
						<!-- post -->
						<div class="post post-list-sm circle">
							<div class="thumb circle">
								<a href="blog-single.html">
									<div class="inner">
										<img src="images/posts/tabs-1.jpg" alt="post-title"/>
									</div>
								</a>
							</div>
							<div class="details clearfix">
								<h6 class="post-title my-0"><a href="blog-single.html">3 Easy Ways To Make Your iPhone Faster</a>
								</h6>
								<ul class="meta list-inline mt-1 mb-0">
									<li class="list-inline-item">29 March 2021</li>
								</ul>
							</div>
						</div>
						<!-- post -->
						<div class="post post-list-sm circle">
							<div class="thumb circle">
								<a href="blog-single.html">
									<div class="inner">
										<img src="images/posts/tabs-4.jpg" alt="post-title"/>
									</div>
								</a>
							</div>
							<div class="details clearfix">
								<h6 class="post-title my-0"><a href="blog-single.html">15 Unheard Ways To Achieve Greater
									Walker</a>
								</h6>
								<ul class="meta list-inline mt-1 mb-0">
									<li class="list-inline-item">29 March 2021</li>
								</ul>
							</div>
						</div>
						<!-- post -->
						<div class="post post-list-sm circle">
							<div class="thumb circle">
								<a href="blog-single.html">
									<div class="inner">
										<img src="images/posts/tabs-3.jpg" alt="post-title"/>
									</div>
								</a>
							</div>
							<div class="details clearfix">
								<h6 class="post-title my-0"><a href="blog-single.html">10 Ways To Immediately Start Selling
									Furniture</a></h6>
								<ul class="meta list-inline mt-1 mb-0">
									<li class="list-inline-item">29 March 2021</li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>


</template>

<script>
	export default {
		name: "PostTabs"
	}
</script>

<style scoped>

</style>