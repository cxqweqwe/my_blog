<template>
	<div class="markdown-container">
		<div class="container">
			<div class="space"></div>
			<Row>
				<Col :sm="4" >
					<div class="title-text">请输入标题:</div>
				</Col>
				<Col :sm="20" :xs="20">
					<div class="ttile">
						<el-input
								placeholder="请输入标题"
								v-model="input"
								clearable>
						</el-input>
					</div>
				</Col>
			</Row>

			<div class="space"></div>
			<mavon-editor
					v-model="content"
					ref="md"
					@imgAdd="$imgAdd"
					@change="change"
					style="min-height: 600px"
			/>
			<el-button type="primary" plain @click="save">保存草稿</el-button>
			<el-button class="editor-btn" type="primary" @click="submit">提交</el-button>
		</div>
		<div class="space"></div>
	</div>
</template>

<script>
  //该组件中注释掉的代码为局部注册的方式。
  // import { mavonEditor } from "mavon-editor";
  // import "mavon-editor/dist/css/index.css";

  export default {
    name: "BlogEdit",
    data: function () {
      return {
        content: "",
        html: "",
        configs: {},


        //标题
        input: ''
      };
    },
    // components: {
    //   mavonEditor
    // },
    methods: {
      // 将图片上传到服务器，返回地址替换到md中
      $imgAdd(pos, $file) {
        var formdata = new FormData();
        formdata.append("file", $file);
        //将下面上传接口替换为你自己的服务器接口
        axios({
          url: "/common/upload",
          method: "post",
          data: formdata,
          headers: {"Content-Type": "multipart/form-data"}
        }).then(url => {
          this.$refs.md.$img2Url(pos, url);
        });
      },
      change(value, render) {
        // render 为 markdown 解析后的结果
        this.html = render;
      },
      submit() {
        console.log(this.content);
        console.log(this.html);
        this.$message.success("提交成功！");
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
		padding-bottom: 30px;
		text-align: center;
		font-size: 20px;
		letter-spacing: 1px;
		color: #333;
		font-weight: 600;
	}

	.space {
		height: 20px;
	}

</style>