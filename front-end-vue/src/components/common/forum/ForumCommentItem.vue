<template>
  <div class="comment-list">
    <Row>
      <Col span="8">
        <div>
          <a>
            <router-link target="_blank" :to="{path:'/blog/'}">
              <img src="http://image.fangweb.top/Fi3tTQ29raaHdyajBN9Es8HkvzC_" class="author avatar" alt="用户头像"/> sdkfgndskg
            </router-link>
          </a>
        </div>
      </Col>
      <Col span="8">
        <div>发布时间</div>
      </Col>
      <Col span="8">
        <div>楼层</div>
      </Col>
    </Row>
    <div class="space10"></div>
    <Row>
      <Col span="24">
        <div class="wrap size16">发大水包括灭佛ladsf'd'j'sna'b'hi'o'g'f'd'j'n'bois'r'jv'oSJmvoifdjvdfkajngbfido给你v哦啊和如果i哦按热后发大水包括灭佛ladsf'd'j'sna'b'hi'o'g'f'd'j'n'bois'r'jv'oSJmvoifdjvdfkajngbfido给你v哦啊和如果i哦按热后发大水包括灭佛ladsf'd'j'sna'b'hi'o'g'f'd'j'n'bois'r'jv'oSJmvoifdjvdfkajngbfido给你v哦啊和如果i哦按热后发大水包括灭佛ladsf'd'j'sna'b'hi'o'g'f'd'j'n'bois'r'jv'oSJmvoifdjvdfkajngbfido给你v哦啊和如果i哦按热后</div>
      </Col>
    </Row>
    <div class="space10"></div>
    <hr/>
  </div>
</template>

<script>
  export default {
    name: "ForumCommentItem"
  }
</script>

<style scoped>
  .comment-list{
    padding: 5px;
  }

  .avatar {
    width: 30px;
    height: 30px;
    border-radius: 30px
  }

  .space10{
    height: 10px;
  }

  .wrap {
    word-break: break-all;
    word-wrap: break-spaces;
  }
  .size16{
    font-size: 16px;
  }

</style>