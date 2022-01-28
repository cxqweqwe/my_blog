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
    set(state,authorInfo){
      // state默认自带
      console.log(authorInfo);
      if (authorInfo.authorId != null && authorInfo.authorId != undefined && authorInfo.authorId != ''){
        state.authorInfo.authorId = authorInfo.authorId;
      }
      if (authorInfo.nickName != null && authorInfo.nickName != undefined && authorInfo.nickName != ''){
        state.authorInfo.nickName = authorInfo.nickName;
      }
      if (authorInfo.avatarPath != null && authorInfo.avatarPath != undefined && authorInfo.avatarPath != ''){
        state.authorInfo.avatarPath = authorInfo.avatarPath;
      }
      if (authorInfo.token != null && authorInfo.token != undefined && authorInfo.token != ''){
        state.authorInfo.token = authorInfo.token;
      }
      console.log(state.authorInfo);
    },
    cleanAll(state){
      state.authorInfo.authorId = '';
      state.authorInfo.nickName = '';
      state.authorInfo.avatarPath = '';
      state.authorInfo.token = '';
    }
  },
  actions: {
    //最主要是做异步操作,暂时用不到
  }
})
