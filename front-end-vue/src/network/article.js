import {request} from "./request";

const requestUrl = '/article';

export function deleteDetail(param) {
    return request({
        url: requestUrl + "/delete/" + param
    })
}

export function articleDetail(param) {
    return request({
        url: requestUrl + "/detail/" + param
    })
}

export function releaseArticle(params) {
    return request({
        url: requestUrl + '/release',
        method: 'post',
        data: params
    })
}

export function getPopular(param) {
    return request({
        url: requestUrl + '/popular/' + param,
    })
}

export function getRecent() {
    return request({
        url: requestUrl + "/recent"
    })
}