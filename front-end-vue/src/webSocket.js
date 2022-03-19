
import {BASE_WS_URL} from "common/common_variable";
import {getCookieAuthorId} from "./common/cookieUtils";
import th from "element-ui/src/locale/lang/th";

console.log(BASE_WS_URL+getCookieAuthorId());

const webSocketUtil = {
  webSocket: null,
  initWebSocket() {
    const authorId = getCookieAuthorId();
    if (!authorId){
      console.log('未登录，无法进行连接');
      return;
    }
    this.webSocket = new WebSocket(BASE_WS_URL + authorId);
    this.webSocket.onmessage = function(e){
      console.log(e);
    }
    this.webSocket.onclose = function(e){
      console.log(e);
    }
    this.webSocket.onopen = function () {
      console.log("连接成功");
    }
  }

}
webSocketUtil.initWebSocket();
export default webSocketUtil;




