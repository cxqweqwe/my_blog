import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    // 登录人信息
    authorInfo: {
      authorId: '',
      nickName: '',
      avatarPath: '',
      token: ''
    }
  },
  mutations: {
    //更改 Vuex 的 store 中的状态的唯一方法是提交 mutation
    set(authorInfo){
      console.log(authorInfo);
      if (authorInfo.authorId != null && authorInfo.authorId != undefined && authorInfo.authorId != ''){
        this.authorInfo.authorId = authorInfo.authorId;
      }
      if (authorInfo.nickName != null && authorInfo.nickName != undefined && authorInfo.nickName != ''){
        this.authorInfo.nickName = authorInfo.nickName;
      }
      if (authorInfo.avatarPath != null && authorInfo.avatarPath != undefined && authorInfo.avatarPath != ''){
        this.authorInfo.avatarPath = authorInfo.avatarPath;
      }
      if (authorInfo.token != null && authorInfo.token != undefined && authorInfo.token != ''){
        this.authorInfo.token = authorInfo.token;
      }
    },
    cleanAll(){
      this.authorInfo.authorId = '';
      this.authorInfo.nickName = '';
      this.authorInfo.avatarPath = '';
      this.authorInfo.token = '';
    }
  },
  actions: {
    //最主要是做异步操作,暂时用不到
  }
})
