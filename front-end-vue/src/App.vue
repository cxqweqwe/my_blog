<template>
	<div id="app">
		<div id="nav">
			<!--			<router-link to="/"></router-link>-->
			<!--			<Index/>-->
		</div>
		<router-view/>
    <Footer></Footer>
	</div>
</template>

<script>
  import Footer from "components/content/footer/Footer";
  export default {
    name: 'app',
    components: {
      Footer
    }
  }
</script>

<style>
	@import "assets/css/bootstrap.min.css";
	@import "assets/css/all.min.css";
	@import "assets/css/slick.css";
	@import "assets/css/simple-line-icons.css";
	@import "assets/css/style.css";
</style>
