<template>
	<div>
		<TabBar></TabBar>

		<!-- 精选文章及其最近精选 -->
		<FeaturedContent></FeaturedContent>

		<!-- 编辑推荐 -->
		<PickMainContent></PickMainContent>



	</div>
</template>

<script>
	import TabBar from "components/common/tabBar/TabBar";
 import FeaturedContent from "components/content/featured/FeaturedContent";
 import PickMainContent from "components/content/main/PickMainContent";

	export default {
		name: "Index",
		components: {
			TabBar,
			FeaturedContent,
			PickMainContent
		},
		methods: {}
	}
</script>

<style scoped>


</style>
