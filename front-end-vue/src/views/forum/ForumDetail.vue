<template>
  <div class="">
    {{this.postId}}
  </div>
</template>

<script>
  export default {
    name: "ForumDetail",
    data() {
      return {
        postId: ''
      }
    },
    created() {
      this.postId = this.$route.params.postId;
      console.log(this.postId);
    },
    methods: {}
  }
</script>

<style scoped>

</style>