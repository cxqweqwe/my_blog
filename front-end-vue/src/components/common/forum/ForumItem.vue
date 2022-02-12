<template>
  <div class="forum-main">

  </div>
</template>

<script>
  export default {
    name: "ForumItem",
    data() {
      return {
      }
    },
    created() {
    },
    methods: {
      releasePost() {
        this.$message({
          showClose: true,
          message: '建议发布之前先搜索一下关键词，或许就能找到答案了',
          type: 'success'
        });
      }
    }
  }
</script>

<style scoped>

</style>