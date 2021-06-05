<template>


	<div class="col-lg-4">
		<!-- post tabs -->
		<div class="post-tabs rounded bordered">
			<!-- tab navs -->
			<ul class="nav nav-tabs nav-pills nav-fill" id="postsTab" role="tablist">
				<li class="nav-item" role="presentation">
					<button aria-controls="popular" aria-selected="true" class="nav-link" :class="{active:isActive=='popular'}"
									data-bs-target="#popular"
									data-bs-toggle="tab" id="popular-tab" role="tab" type="button" @click="tabNavsClick('popular')">
						Popular
					</button>
				</li>
				<li class="nav-item" role="presentation">
					<button aria-controls="recent" aria-selected="false" class="nav-link" :class="{active:isActive=='recent'}"
									data-bs-target="#recent"
									data-bs-toggle="tab" id="recent-tab" role="tab" type="button" @click="tabNavsClick('recent')">Recent
					</button>
				</li>
			</ul>
			<!-- tab contents -->
			<div class="tab-content" id="postsTabContent">
				<!-- loader -->
				<div class="lds-dual-ring"></div>
				<!-- popular posts -->
				<div aria-labelledby="popular-tab" class="tab-pane fade show active" id="popular" role="tabpanel">
					<!-- post Popular 已做封装-->
					<PostItem v-for="(item,index) in itemData" :active="format" :key="index" :itemData="item"/>
				</div>
				<!-- recent posts -->
				<!--					<div aria-labelledby="recent-tab" class="tab-pane fade" id="recent" role="tabpanel">-->
				<!--						&lt;!&ndash;// TODO：Recent点击无反应&ndash;&gt;-->
				<!--						&lt;!&ndash; post Recent 已做封装 &ndash;&gt;-->
				<!--						<PostItem v-for="i in 4" :active="format"/>-->
				<!--					</div>-->
			</div>
		</div>
	</div>


</template>

<script>
  import PostItem from "components/common/post/PostItem";

  import {test} from "../../../network/test"

  export default {
    name: "PostTabs",
    components: {
      PostItem
    },
    data() {
      return {
        format: "smallPicture",
        isActive: "popular",

        itemData: []
      }
    },
    methods: {
      tabNavsClick(select) {
        this.isActive = select;
      },
      test(){
        test().then(res => {
          // console.log(res);
          this.itemData = res;
          // console.log(this.itemData);
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