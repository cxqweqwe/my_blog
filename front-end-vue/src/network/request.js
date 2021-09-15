import axios from 'axios'
import {base_url} from "../common/common_variable";

export function request(config) {
  // 1.创建axios的实例
  const instance = axios.create({
    // baseURL: 'http://localhost:8081',
    baseURL: base_url,
    timeout: 60000
  });

  // 2.axios的拦截器
  // 2.1.请求拦截的作用
  instance.interceptors.request.use(config => {
    // 如果有token,携带token
    let token = sessionStorage.Authorization
    if(token){
      config.headers.common['Authorization'] = token;
    }

    return config
  }, err => {
    // console.log(err);
  });

  // 2.2.响应拦截
  instance.interceptors.response.use(res => {
    if (res.data.status == 4003){
      this.$notify({
        title: '警告',
        message: res.data.msg,
        type: 'warning'
      });
    }
    return res.data
  }, err => {
    console.log(err);
  });

  // 3.发送真正的网络请求
  return instance(config)
}
