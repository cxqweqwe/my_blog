<template>
  <Modal
      v-model="show"
      title="点击选择选择收藏目录"
      @footer-hide="false"
      @on-ok="ok"
      @on-cancel="cancel">
    <CheckboxGroup v-model="checkList">
      <Checkbox border class="checkbox" v-for="(item,index) in favoritesList" :key="index"
                :label="item.favoritesId">{{item.favoritesName}}</Checkbox>
    </CheckboxGroup>
    <el-button type="text" class="checkbox button" v-if="favoritesList.length < 10">添加收藏夹 {{favoritesList.length}}/10</el-button>
  </Modal>
</template>

<script>
  import {query} from "network/favoritesBar";
  import {getCookieAuthorId} from "common/cookieUtils";

  export default {
    name: "Favorite",
    props: {
      showFavoritesPanel: Boolean,

    },
    data() {
      return {
        show: false,
        authorId: String,
        favoritesList: [],
        checkList: [],//被选中的
      }
    },
    created() {
      this.authorId = getCookieAuthorId();
      this.getFavoritesBar();
    },
    methods: {
      getFavoritesBar() {
        query(this.authorId).then(res => {
          console.log(res);
          this.favoritesList = res.data;
        })
      },
      ok() {
        for (let item of this.checkList){
          console.log(item);
        }
        this.$emit("closeFavoritesPanel");
      },
      cancel() {
        this.$emit("closeFavoritesPanel");
      }
    },
    watch: {
      showFavoritesPanel(value) {
        // console.log('变量改变');
        this.show = this.showFavoritesPanel;
      }

    }
  }
</script>

<style scoped>
  .checkbox {
    margin: 5px;
    width: 100%;
  }
  .button{
    color: rgb(177, 170, 170);
  }
</style>