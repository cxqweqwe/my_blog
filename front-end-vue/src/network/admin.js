import {request} from "./request";

const requestUrl = '/sys/admin';

export function checkAdmin() {
  return request({
    url: requestUrl + "/check/user"
  })
}

export function queryUser(currentPage,size){
  return request({
    url: requestUrl + "/query/user",
    params: {
      curPage: currentPage,
      size: size
    }
  })
}
