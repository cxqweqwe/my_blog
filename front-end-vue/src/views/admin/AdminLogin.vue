<template>
  <div id="admin-login">
    <div id="app">
      <div class="demo-login">
        <div class="ivu-login">
          <form autocomplete="off" class="ivu-form ivu-form-label-right">
            <div class="ivu-login-username">
              <div class="ivu-form-item ivu-form-item-required"><!---->
                <div class="ivu-form-item-content">
                  <div class="ivu-input-wrapper ivu-input-wrapper-large ivu-input-type-text"><!----> <!----> <i
                      class="ivu-icon ivu-icon-ios-loading ivu-load-loop ivu-input-icon ivu-input-icon-validate"></i>
                    <input autocomplete="off" spellcheck="false" type="text" placeholder="请输入用户名" name="username"
                           v-model="username"
                           class="ivu-input ivu-input-large ivu-input-with-prefix"> <span class="ivu-input-prefix"><i
                        class="ivu-icon ivu-icon-ios-contact-outline"></i></span></div> <!----></div>
              </div>
            </div>
            <div class="ivu-login-password">
              <div class="ivu-form-item ivu-form-item-required"><!---->
                <div class="ivu-form-item-content">
                  <div class="ivu-input-wrapper ivu-input-wrapper-large ivu-input-type-password"><!----> <!----> <i
                      class="ivu-icon ivu-icon-ios-loading ivu-load-loop ivu-input-icon ivu-input-icon-validate"></i>
                    <input autocomplete="off" spellcheck="false" type="password" placeholder="请输入密码" name="password"
                           v-model="password"
                           class="ivu-input ivu-input-large ivu-input-with-prefix"> <span class="ivu-input-prefix"><i
                        class="ivu-icon ivu-icon-ios-lock-outline"></i></span></div> <!----></div>
              </div>
            </div>
            <div class="ivu-login-submit">
              <button type="button" class="ivu-btn ivu-btn-primary ivu-btn-long ivu-btn-large" @click="login"><!---->
                <!---->
                <span>登录</span></button>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import {login} from 'network/login';
  import {SESSION_NICKNAME_KEY, SESSION_AVATAR_KEY} from "common/common_variable";
  import {setCookie, setCookieAuthorId, setCookieAvatarPath, setCookieNickName} from "common/cookieUtils";

  import sha1 from "common/sha1";

  export default {
    name: "AdminLogin",
    data() {
      return {
        username: '',
        password: '',
      }
    },
    created() {
    },
    methods: {
      login() {

        // 为密码加密
        let encodePassword = sha1.sha1(this.password);
        let loginFrom = {
          'username': this.username,
          'password': encodePassword
        };

        login(loginFrom).then(res => {
          // console.log(res);
          if (res.status == 2000) {
            sessionStorage.setItem('Authorization', res.data.token);
            sessionStorage.setItem('authorId', res.data.authorId);
            sessionStorage.setItem(SESSION_NICKNAME_KEY, res.data.nickName);
            sessionStorage.setItem(SESSION_AVATAR_KEY, res.data.avatarPath);
            let info = {
              authorId: res.data.authorId,
              nickName: res.data.nickName,
              avatarPath: res.data.avatarPath,
              token: res.data.token
            }
            this.$store.commit('set', info);
            this.$store.commit('checkLogin', true);
            setCookie(res.data.token);//设置Cookie
            setCookieAuthorId(res.data.authorId);
            setCookieNickName(res.data.nickName);
            setCookieAvatarPath(res.data.avatarPath);
            this.$router.push("/admin/main");
          } else if (res.status == 4000) {
            this.$notify.error({
              title: '错误',
              message: res.msg
            });
            this.password = '';
          } else {
            this.$notify.error({
              title: '错误',
              message: res.msg
            });
            this.password = '';
          }
        })

      }
    }
  }
</script>

<style scoped>
  #admin-login {
    position: absolute;
    width: 20vw;
    top: calc(50vh - 96px);
    left: calc(50vw - 192px);
  }
</style>