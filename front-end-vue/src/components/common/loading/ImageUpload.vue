<template>
  <el-dialog
      :title="uploadTitle"
      :visible="centerDialogVisible"
      width="30%"
      :show-close=false
      center>

    <el-upload
        ref="upload"
        class="upload-demo"
        :limit="1"
        :action="UPLOAD_URL"
        :headers="addHeader"
        :data="params"
        :on-success="uploadSuccess"
        :on-error="uploadError"
        list-type="picture">
      <el-button size="small" type="primary">点击上传</el-button>
      <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
      <div slot="tip" class="el-upload__tip">只能上传一张，多张无效</div>
    </el-upload>

    <span slot="footer" class="dialog-footer">
        <el-button @click="closeUpload()">取 消</el-button>
        <el-button type="primary" @click="uploadImage()">确定上传</el-button>
        <el-button type="primary" @click="closeUpload()">确 定</el-button>
      </span>
  </el-dialog>
</template>

<script>

import {uploadImage} from "network/imageUpload";
import {base_url} from "common/common_variable";

export default {
  name: "ImageUpload",
  props: {
    centerDialogVisible: {
      type: Boolean,
      default: false
    },
    uploadTitle: {
      type: String,
      default: "上传"
    },
    uploadUrl: {
      type: String,
      default: ""
    },
    articleId: {
      type: String,
      default: ''
    }
  },
  data() {
    return {
      UPLOAD_URL: base_url + '/image/upload',
      imageAddress: '',
      params: {}
    }
  },
  created() {

  },
  computed:{
    addHeader(){
      return {
        'Authorization': sessionStorage.Authorization
      }
    }
  },
  methods: {
    uploadSuccess(response, file, fileList){
      if (response.status == '2000'){
        this.$notify({
          message: response.msg,
          type: 'success'
        });


      }else {
        this.$notify.info({
          title: '封面图片上传失败',
          message: response.msg
        });
        fileList.splice(1,1);//fileList的失败文件
      }

    },

    uploadError(err, file, fileList){
      this.$notify.error({
        message: '出错了，请联系系统管理员'
      });
    },
    closeUpload() {
      // this.$parent.centerDialogVisible = false;// 直接改会报错
      this.$emit('item-click');
    }

  },
  watch: {
    articleId (newValue, oldValue) {
      this.articleId = newValue;
      console.log(this.articleId);
      this.params['articleOrPostId'] = this.articleId;
    }
  }
}
</script>

<style scoped>

</style>