import {request} from "./request";

const requestUrl = '/userInfo';

export function getAuthorInfo(param){
    return request({
        url: requestUrl + "/inquire/" + param
    })
}

export function updateUserInfo(params) {
    return request({
        url: requestUrl + '/updateUserInfo',
        method: 'post',
        data: params
    })
}