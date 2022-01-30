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

export function updatePhone(phoneNumber,code) {
    return request({
        url: requestUrl + '/update/phone',
        method: 'post',
        params: {
            phoneNumber: phoneNumber,
            code: code
        }
    })
}

export function updateEmail(email,code) {
    return request({
        url: requestUrl + '/update/email',
        method: 'post',
        params: {
            email: email,
            code: code
        }
    })
}

export function getBlogger(param){
    return request({
        url: requestUrl + "/blogger/" + param
    })
}
