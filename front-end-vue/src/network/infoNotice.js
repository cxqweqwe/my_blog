import {request} from "./request";

const requestUrl = '/infoNotice';

export function queryNotice(param) {
  return request({
    url: requestUrl + "/query/" + param
  })
}

export function getUnReadCount(param) {
  return request({
    url: requestUrl + "/unRead/" + param
  })
}
