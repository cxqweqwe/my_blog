import {request} from "./request";

const requestUrl = '/sys/admin';

export function checkAdmin() {
  return request({
    url: requestUrl + "/check/user"
  })
}

export function queryUser(currentPage, size) {
  return request({
    url: requestUrl + "/query/user",
    params: {
      curPage: currentPage,
      size: size
    }
  })
}

export function trialUser(authorId, status) {
  return request({
    url: requestUrl + "/trial/user",
    params: {
      authorId,
      status
    }
  })
}

export function querySetting(authorId, status) {
  return request({
    url: requestUrl + "/query/setting",

  })
}

export function changeSetting(settingId, isAllow) {
  return request({
    url: requestUrl + "/change/setting",
    params: {
      settingId,
      isAllow
    }
  })
}
