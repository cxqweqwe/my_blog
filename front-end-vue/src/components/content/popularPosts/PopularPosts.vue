<template>
	<!-- widget popular posts -->
	<div class="widget rounded">
		<div class="widget-header text-center">
			<h3 class="widget-title">Popular Posts</h3>
			<img src="~assets/img/wave.svg" class="wave" alt="wave" />
		</div>
		<div class="widget-content">
      <!-- post -->
			<PostItem v-for="(item,index) in itemData" :active="'smallPicture'" :key="index" :postItem="item"/>
		</div>
	</div>

</template>

<script>
	import PostItem from "components/common/post/PostItem";

  import {test} from "../../../network/test"

  export default {
    name: "PopularPosts",
		components: {
      PostItem
		},
		data() {
      return {
        itemData: []
			}
		},
    methods: {
      tabNavsClick(select) {
        this.isActive = select;
      },
      test(){
        test().then(res => {
          this.itemData = res;
        })
      }
    },
    created() {
      this.test();
    }
  }
</script>

<style scoped>

</style>