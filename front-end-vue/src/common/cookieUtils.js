import Cookies from 'js-cookie'

import {COOKIE_KEY,COOKIE_EXPIRES,COOKIE_AUTHORID_KEY} from "./common_variable";

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

export function getCookieAuthorId() {
    return Cookies.get(COOKIE_AUTHORID_KEY)
}

export function setCookieAuthorId(authorId) {
    let inFifteenMinutes = new Date(new Date().getTime() + COOKIE_EXPIRES);
    return Cookies.set(COOKIE_AUTHORID_KEY, authorId,{expires: inFifteenMinutes})
}

export function removeCookieAuthorId() {
    return Cookies.remove(COOKIE_AUTHORID_KEY)
}
