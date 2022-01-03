import Cookies from 'js-cookie'

import {COOKIE_KEY,COOKIE_EXPIRES} from "./common_variable";

export function getCookie() {
    return Cookies.get(COOKIE_KEY)
}

export function setCookie(token) {
    let inFifteenMinutes = new Date(new Date().getTime() + COOKIE_EXPIRES);
    return Cookies.set(COOKIE_KEY, token,{expires: inFifteenMinutes})
}

export function removeCookie() {
    return Cookies.remove(COOKIE_KEY)
}
