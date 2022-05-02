import {request} from "./request";

const requestUrl = '/userInfo';

export function getAuthorInfo(param){
    return request({
        url: requestUrl + "/inquire/" + param
    })
}

export function updateUserInfo(userInfoDTO) {
    return request({
        url: requestUrl + '/updateUserInfo',
        method: 'post',
        data: userInfoDTO
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

export function updateAvatar(avatarPath) {
    return request({
        url: requestUrl + '/update/avatar',
        method: 'post',
        params: {
            avatar: avatarPath
        }
    })
}

export function getBlogger(param){
    return request({
        url: requestUrl + "/blogger/" + param,
    })
}
