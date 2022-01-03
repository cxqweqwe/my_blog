import Cookies from 'js-cookie'

import {COOKIE_KEY,COOKIE_EXPIRES} from "./common_variable";

export function getToken() {
    return Cookies.get(COOKIE_KEY)
}

export function setToken(token) {
    let inFifteenMinutes = new Date(new Date().getTime() + COOKIE_EXPIRES);
    return Cookies.set(COOKIE_KEY, token,{expires: inFifteenMinutes})
}

export function removeToken() {
    return Cookies.remove(COOKIE_KEY)
}
