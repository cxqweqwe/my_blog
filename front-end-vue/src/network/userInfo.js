import {request} from "./request";

const requestUrl = '/userInfo';

export function getAuthorInfo(param){
    return request({
        url: requestUrl + "/inquire/" + param
    })
}