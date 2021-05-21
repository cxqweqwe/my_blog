<template>
	<div>
		<Menu mode="horizontal" :theme="theme1" active-name="1">
			<div class="tab-content-left col-12">
				<MenuItem name="1">
					<Icon type="ios-aperture"/>
					Bernie_Fang
				</MenuItem>
				<MenuItem name="2">
					<Icon type="ios-paper"/>
					博客
				</MenuItem>
				<!--<Submenu name="3">
					<template slot="title">
						<Icon type="ios-stats" />
						统计分析
					</template>
					<MenuGroup title="使用">
						<MenuItem name="3-1">新增和启动</MenuItem>
						<MenuItem name="3-2">活跃分析</MenuItem>
						<MenuItem name="3-3">时段分析</MenuItem>
					</MenuGroup>
					<MenuGroup title="留存">
						<MenuItem name="3-4">用户留存</MenuItem>
						<MenuItem name="3-5">流失用户</MenuItem>
					</MenuGroup>
				</Submenu>-->
				<MenuItem name="3">
					<Icon type="ios-people"/>
					论坛
				</MenuItem>
				<MenuItem name="4">
					<Icon type="md-paper"/>
					专栏
				</MenuItem>
			</div>
			<div class="tab-content-right col-12">
				<div class="myMenuItem">
					<Icon type="md-paper" class="myIcon"/>
					我的
				</div>
			</div>
		</Menu>
	</div>
</template>

<script>
  export default {
    name: "TabBar",
    data() {
      return {
        theme1: 'light'
      }
    }
  }
</script>

<style scoped>
  .tab-content-left{

  }

	.myMenuItem {
		float: right;
		padding: 0 20px;
		position: relative;
		cursor: pointer;
		z-index: 3;
		transition: all .2s ease-in-out;
	}

	.myIcon {
		margin-right: 6px;
	}
</style>
