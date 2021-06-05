<template>

	<div v-if="active !== 'smallPicture'" class="post post-list-sm square">
		<div class="thumb rounded">
			<a :href="itemData.hrefLink">
				<div class="inner">
					<img :src="itemData.imgLink" alt="post-title"/>
				</div>
			</a>
		</div>
		<div class="details clearfix">
			<h6 class="post-title my-0"><a href="blog-single.html">{{itemData.introduction}}</a></h6>
			<ul class="meta list-inline mt-1 mb-0">
				<li class="list-inline-item">{{itemData.time}}</li>
			</ul>
		</div>
	</div>
	<div v-else class="post post-list-sm circle">
		<div class="thumb rounded">
			<a :href="itemData.hrefLink">
				<div class="inner">
					<img :src="itemData.authorImg" alt="post-title"/>
				</div>
			</a>
		</div>
		<div class="details clearfix">
			<h6 class="post-title my-0"><a href="blog-single.html">{{itemData.title}}</a></h6>
			<ul class="meta list-inline mt-1 mb-0">
				<li class="list-inline-item">{{itemData.time}}</li>
			</ul>
		</div>
	</div>

</template>

<script>
  export default {
    name: "PostItem",
    props: {
      active:{
        type: String,
				default: "smallPicture"
			},
      itemData: {
        type: Object,
        default: () => {
          return []
        }
      }
    },
    data() {
      return {
        // hrefLink: "",
      	// imgLink: "~/assets/img/posts/tabs-1.jpg",
        // title: "",
				// time: ""
    	}
    },
		created() {
      // console.log(this.itemData);

    },
    mounted() {
      this.init();
		},
    methods: {
      init() {
        console.log(this.itemData);
      }
		}
  }
</script>

<style scoped>

</style>