<template>
  <div>
<!--    <h1>This is Main page</h1>-->
    <TabBar></TabBar>
  </div>
</template>

<script>
  import TabBar from "components/common/tabBar/TabBar";

  export default {
    name: "Main",
    components: {
      TabBar
    },
    data () {
      return {
        theme1: 'light'
      }
    }
  }
</script>

<style scoped>

</style>
