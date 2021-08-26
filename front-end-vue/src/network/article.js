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

export function releaseArticle(params){
    return request({
        url: requestUrl+'/release',
        method: 'post',
        data: params
    })
}
export function updateArticle(params){
    return request({
        url: requestUrl+'/update',
        method: 'post',
        data: params
    })
}
