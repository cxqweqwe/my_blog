import {request} from "./request";

const requestUrl = '/postInfo';

export function releasePostInfo(param) {
  return request({
    url: requestUrl + "/release",
    method: 'post',
    data: param
  })
}
