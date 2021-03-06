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

export function settingCancelAdmin(authorId, admin) {
  return request({
    url: requestUrl + "/setting_cancel/admin",
    params: {
      authorId,
      admin
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

export function queryArticle(curPage, size) {
  return request({
    url: requestUrl + "/query/article",
    params: {
      curPage,
      size
    }
  })
}

export function trialArticle(articleId, status) {
  return request({
    url: requestUrl + "/trial/article",
    params: {
      articleId,
      status
    }
  })
}

export function queryComment(curPage, size) {
  return request({
    url: requestUrl + "/query/comment",
    params: {
      curPage,
      size
    }
  })
}

export function trialComment(type, id, status) {
  return request({
    url: requestUrl + "/trial/comment",
    params: {
      type,
      id,
      status
    }
  })
}

export function queryReport(curPage, size) {
  return request({
    url: requestUrl + "/query/report",
    params: {
      curPage,
      size
    }
  })
}

export function trialReport(type, id, status) {
  return request({
    url: requestUrl + "/trial/report",
    params: {
      type,
      id,
      status
    }
  })
}

export function queryForum(curPage, size) {
  return request({
    url: requestUrl + "/query/forum",
    params: {
      curPage,
      size
    }
  })
}

export function trialForum(postId, status) {
  return request({
    url: requestUrl + "/trial/forum",
    params: {
      postId,
      status
    }
  })
}
