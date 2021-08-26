<template>
  <div class="markdown-container">
    <div class="container">
      <div class="space"></div>
      <Row>
        <Col :sm="3">
          <div class="title-text">请输入标题:</div>
        </Col>
        <Col :sm="20" :xs="21">
          <div class="title">
            <el-input
                placeholder="请输入标题"
                v-model="article.title"
                clearable>
            </el-input>
          </div>
        </Col>
      </Row>
      <div class="space"></div>
      <Row>
        <Col :sm="3">
          <div class="title-text">请输入简介:</div>
        </Col>
        <Col :sm="20" :xs="21">
          <div class="title">
            <el-input
                placeholder="请输入简介"
                v-model="article.briefIntroduction"
                clearable>
            </el-input>
          </div>
        </Col>
      </Row>

      <!--      <div>-->
      <!--        <el-button type="primary" plain @click="save">保存草稿</el-button>-->
      <!--        <el-button class="editor-btn" type="primary" @click="submit">提交</el-button>-->
      <!--      </div>-->

      <div class="box">
        <div class="left">
          是否原创: &nbsp;
          <el-radio v-model="article.attributes" label="1">原创</el-radio>
          <el-radio v-model="article.attributes" label="0">转载</el-radio>
        </div>
        <div class="middle">
          <el-tag
              :key="tag"
              v-for="tag in dynamicTags"
              closable
              :disable-transitions="false"
              @close="handleClose(tag)">
            {{ tag }}
          </el-tag>
          <el-input
              class="input-new-tag"
              v-if="inputVisible"
              v-model="inputValue"
              ref="saveTagInput"
              size="small"
              @keyup.enter.native="handleInputConfirm"
              @blur="handleInputConfirm"
          >
          </el-input>
          <el-button v-else class="button-new-tag" size="small" @click="showInput">+ 标签</el-button>
        </div>
        <div class="right">
          <el-button type="primary" plain @click="save">保存草稿</el-button>
          <el-button class="editor-btn" type="primary" @click="submit">提交</el-button>
        </div>
      </div>
      <div class="space"></div>


      <div class="space"></div>
      <mavon-editor
          v-model="content"
          ref="md"
          @imgAdd="imgAdd"
          @change="change"
          @imgDel="imgDel"
          codeStyle="'atom-one-dark'"
          style="min-height: 600px"
      />

    </div>
    <div class="space"></div>
  </div>
</template>

<script>
//该组件中注释掉的代码为局部注册的方式。
// import { mavonEditor } from "mavon-editor";
// import "mavon-editor/dist/css/index.css";
import {getBase64} from 'common/imageUtils';
import {uploadBase64} from 'network/base64-request';
import {base64_request_url} from 'common/common_variable';
import {releaseArticle} from 'network/article';
import 'mavon-editor/dist/markdown/github-markdown.min.css'


export default {
  name: "BlogEdit",
  data: function () {
    return {
      content: "",
      html: "",
      configs: {},
      //标题
      input: '',
      updateBase: {
        id: '',
        base64: '',
        imageName: '',
        fileExtName: ''
      },
      article: {
        articleId: '',
        attributes: -1,
        authorId: '',     // 需要特殊处理
        briefIntroduction: '',
        content: '',
        html: '',
        label: '',
        releaseOrNot: 0,
        title: ''
      },
      dynamicTags: [],
      inputVisible: false,
      inputValue: ''
    };
  },
  // components: {
  //   mavonEditor
  // },
  methods: {
    // 将图片上传到服务器，返回地址替换到md中
    imgAdd(pos, $file) {
      // console.log(pos);
      // console.log($file);
      // this.updateBase.fileExtName = $file.type;
      // this.updateBase.base64 = $file.miniurl;
      // this.updateBase.imageName = $file.name;
      // uploadBase64(this.updateBase).then(res =>{
      //   console.log(res.data);
      //   // 第二步.将返回的url替换到文本原位置![...](0) -> ![...](url)
      //   this.$refs.md.$img2Url(pos, base64_request_url + res.data);
      // }).catch(res => {
      //   console.log(res);
      //
      // })
    },
    change(value, render) {
      // render 为 markdown 解析后的结果
      this.html = render;
    },
    imgDel() {

    },
    // 保存草稿
    save() {
      console.log(this.html);
      console.log('dsgdfg');
    },
    submit() {

      if (this.article.title == '') {
        loading.close();
        this.$notify({
          title: '警告',
          message: '文章标题必须填写',
          type: 'warning'
        });
        return;
      }
      if (this.article.attributes == -1) {
        loading.close();
        this.$notify({
          title: '警告',
          message: '必须选择文章是否为原创',
          type: 'warning'
        });
        return;
      }
      this.article.releaseOrNot = 1;
      this.article.content = this.content;
      this.article.html = this.html;
      this.article.title = 'test';
      releaseArticle(this.article).then(res => {
        this.$message.success(res.msg);
        this.$notify({
          title: '成功',
          message: res.msg,
          type: 'success'
        });
      }).catch(res => {
        console.log(res);
        this.$notify.error({
          title: '发布失败',
          message: res.msg
        });
      }).finally(() => {
        loading.close();
      })

    },


    // 添加标签的三个方法
    handleClose(tag) {
      this.dynamicTags.splice(this.dynamicTags.indexOf(tag), 1);
    },
    showInput() {
      if (this.dynamicTags.length >= 5) {
        this.$notify.error({
          title: '错误',
          message: '标签只允许添加5个'
        });
        return;
      }

      this.inputVisible = true;
      this.$nextTick(_ => {
        this.$refs.saveTagInput.$refs.input.focus();
      });
    },
    handleInputConfirm() {
      let inputValue = this.inputValue;
      if (inputValue) {
        this.dynamicTags.push(inputValue);
      }
      this.inputVisible = false;
      this.inputValue = '';
    }
  }

}
</script>

<style scoped>
.title-text {
  height: 40px;
  line-height: 40px;
  text-align: center;
  font-size: inherit;
}

.markdown-container {
}

.editor-btn {
  margin-top: 20px;
}

.title {
  text-align: center;
  font-size: 20px;
  letter-spacing: 1px;
  color: #333;
  font-weight: 600;

  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}

.space {
  height: 20px;
}

.box {

}

.left {
  display: inline-block;
  width: 23%;
  height: 40px;
  line-height: 40px;
  vertical-align: middle;
  text-align: center;
  border-radius: 30px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}

.middle {
  display: inline-block;
  width: 57%;
  padding-left: 10px;
  height: 40px;
  line-height: 40px;
  vertical-align: middle;
  text-align: center;
  border-radius: 30px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}

.right {
  display: inline-block;

}
</style>