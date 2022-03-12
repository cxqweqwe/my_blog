<template>
  <div class="layout">
    <Layout>
      <Layout>
        <Sider hide-trigger :style="{background: '#fff'}" class="height">
          <Menu active-name="1" width="auto" @on-select="select">
            <MenuGroup title="内容管理">
              <MenuItem name="1">
                <Icon type="md-settings"/>
                系统设置
              </MenuItem>
              <MenuItem name="2">
                <Icon type="md-document"/>
                文章管理
              </MenuItem>
              <MenuItem name="3">
                <Icon type="md-chatbubbles"/>
                评论管理
              </MenuItem>
              <MenuItem name="4">
                <Icon type="md-heart"/>
                用户管理
              </MenuItem>
              <MenuItem name="5">
                <Icon type="md-alert"/>
                举报管理
              </MenuItem>
            </MenuGroup>
          </Menu>
        </Sider>
        <Layout :style="{padding: '24px 24px 24px 24px', height: '100vh'}">
          <Content :style="{padding: '24px', minHeight: '280px', background: '#fff'}">
            <div v-if="authority === 'admin' || authority === 'root'">
              <div v-if="active == 1">
                <Table stripe :columns="settingColumns" :data="settingList">
                  <template slot-scope="{ row, index }" slot="settingName">
                    <span>{{ row.name }}</span>
                  </template>
                  <template slot-scope="{ row, index }" slot="action">
                    <el-switch
                        v-model="row.allowOrNot==1?true:false"
                        active-color="#13ce66"
                        inactive-color="#ff4949"
                        @change="changeSetting(row)">
                    </el-switch>
                  </template>
                </Table>
              </div>
              <div v-if="active == 2">
                <Table stripe :columns="articleColumns" :data="articleList"></Table>
                <div class="center top-space">
                  <Page :total="articleTotal" show-elevator @on-change="articleChange"/>
                </div>
              </div>
              <div v-if="active == 3">
                <Table stripe :columns="commentColumns" :data="commentList" ></Table>
                <div class="center top-space">
                  <Page :total="commentTotal" show-elevator @on-change="commentChange"/>
                </div>
              </div>
              <div v-if="active == 4">
                <Table stripe :columns="userColumns" :data="usersList"></Table>
                <div class="center top-space">
                  <Page :total="usersTotal" show-elevator @on-change="userChange"/>
                </div>
              </div>
              <div v-if="active == 5">
                是的感觉好难的疯狂过后
              </div>
            </div>
          </Content>
        </Layout>
      </Layout>
    </Layout>
  </div>
</template>

<script>
  import {checkAdmin, queryUser, trialUser, changeSetting, querySetting, queryArticle, trialArticle,
    queryComment, trialComment} from "network/admin";

  export default {
    name: "AdminMain",
    data() {
      return {
        active: 1,
        authority: '',

        selectArr: [],  // 存放已经发送数据的标题数字
        // 设置管理
        settingList: [],
        settingColumns: [{
          title: '设置项',
          key: 'settingName'
        }, {
          title: '操作',
          slot: 'action'
        }],
        // 文章管理
        articleColumns: [{
            title: '文章ID',
            key: 'articleId'
          }, {
            title: '文章标题',
            key: 'title'
          }, {
            title: '文章作者昵称',
            key: 'nickName'
          }, {
            title: '文章状态',
            key: 'deleted',
            render: (h, params) => {
              let deleted = params.row.deleted;
              if (deleted == 0) {
                return h('div', [
                  h('Tag', {
                    style: {
                      background: '#b4f60e'
                    },
                    on: {}
                  }, '正常'),
                ])
              } else if (deleted == 2) {
                return h('div', [
                  h('Tag', {
                    style: {
                      background: '#f90'
                    },
                    on: {}
                  }, '封禁'),
                ])
              } else if (deleted == 1) {
                return h('div', [
                  h('Tag', {
                    style: {
                      background: '#ed4014'
                    },
                    on: {}
                  }, '删除'),
                ])
              }
            }
          }, {
            title: 'Action',
            key: 'action',
            width: 250,
            align: 'center',
            render: (h, params) => {
              return h('div', [
                h('Button', {
                  props: {
                    type: 'primary',
                    size: 'small'
                  },
                  style: {
                    marginRight: '5px'
                  },
                  on: {
                    click: () => {
                      this.articleActivation(params.row)
                    }
                  }
                }, '激活'),
                h('Button', {
                  props: {
                    type: 'warning',
                    size: 'small'
                  },
                  style: {
                    marginRight: '5px'
                  },
                  on: {
                    click: () => {
                      this.articleBan(params.row)
                    }
                  }
                }, '封禁'),
                h('Button', {
                  props: {
                    type: 'error',
                    size: 'small'
                  },
                  on: {
                    click: () => {
                      this.ArticleRemove(params.row)
                    }
                  }
                }, '删除')
              ]);
            }
          }
        ],
        articleList: [],
        articleTotal: 1,
        // 评论管理
        commentColumns: [{
          title: '评论来源',
          key: 'type'
        }, {
          title: '评论对象',
          key: 'commentObject'
        }, {
          title: '评论者',
          key: 'nickName'
        },{
          title: '评论者昵称',
          key: 'nickName'
        },{
          title: '评论内容',
          key: 'content'
        },{
          title: '时间',
          key: 'createTime'
        }, {
          title: '评论状态',
          key: 'deleted',
          render: (h, params) => {
            let deleted = params.row.deleted;
            if (deleted == 0) {
              return h('div', [
                h('Tag', {
                  style: {
                    background: '#b4f60e'
                  },
                  on: {}
                }, '正常'),
              ])
            } else if (deleted == 2) {
              return h('div', [
                h('Tag', {
                  style: {
                    background: '#f90'
                  },
                  on: {}
                }, '封禁'),
              ])
            } else if (deleted == 1) {
              return h('div', [
                h('Tag', {
                  style: {
                    background: '#ed4014'
                  },
                  on: {}
                }, '删除'),
              ])
            }
          }
        }, {
          title: 'Action',
          key: 'action',
          width: 250,
          align: 'center',
          render: (h, params) => {
            return h('div', [
              h('Button', {
                props: {
                  type: 'primary',
                  size: 'small'
                },
                style: {
                  marginRight: '5px'
                },
                on: {
                  click: () => {
                    this.commentActivation(params.row)
                  }
                }
              }, '激活'),
              h('Button', {
                props: {
                  type: 'warning',
                  size: 'small'
                },
                style: {
                  marginRight: '5px'
                },
                on: {
                  click: () => {
                    this.commentBan(params.row)
                  }
                }
              }, '封禁'),
              h('Button', {
                props: {
                  type: 'error',
                  size: 'small'
                },
                on: {
                  click: () => {
                    this.commentRemove(params.row)
                  }
                }
              }, '删除')
            ]);
          }
        }
        ],
        commentList: [],
        commentTotal: 1,

        // 用户管理
        userColumns: [{
          title: '会员ID',
          key: 'authorId'
        }, {
          title: '会员昵称',
          key: 'nickName'
        }, {
          title: '账号状态',
          key: 'deleted',
          render: (h, params) => {
            let deleted = params.row.deleted;
            if (deleted == 0) {
              return h('div', [
                h('Tag', {
                  style: {
                    background: '#b4f60e'
                  },
                  on: {}
                }, '正常'),
              ])
            } else if (deleted == 2) {
              return h('div', [
                h('Tag', {
                  style: {
                    background: '#f90'
                  },
                  on: {}
                }, '封禁'),
              ])
            } else if (deleted == 1) {
              return h('div', [
                h('Tag', {
                  style: {
                    background: '#ed4014'
                  },
                  on: {}
                }, '删除'),
              ])
            }
          }
        }, {
          title: 'Action',
          key: 'action',
          width: 250,
          align: 'center',
          render: (h, params) => {
            return h('div', [
              h('Button', {
                props: {
                  type: 'primary',
                  size: 'small'
                },
                style: {
                  marginRight: '5px'
                },
                on: {
                  click: () => {
                    this.userActivation(params.row)
                  }
                }
              }, '激活'),
              h('Button', {
                props: {
                  type: 'warning',
                  size: 'small'
                },
                style: {
                  marginRight: '5px'
                },
                on: {
                  click: () => {
                    this.userBan(params.row)
                  }
                }
              }, '封禁'),
              h('Button', {
                props: {
                  type: 'error',
                  size: 'small'
                },
                on: {
                  click: () => {
                    this.userRemove(params.row)
                  }
                }
              }, '删除')
            ]);
          }
        }
        ],
        usersList: [],
        usersTotal: 0,
      }
    },
    created() {
      this.check();
      this.getSetting();
    },
    methods: {
      check() {
        checkAdmin().then(res => {
          this.authority = res.data[0];
          if (!(this.authority === 'root' || this.authority === 'admin')) {
            this.$router.push("/sysAdmin");
          }
        })
      },
      select(name) {
        this.active = name;
        if (-1 !== this.selectArr.indexOf(name)) {
          return;
        }
        this.selectArr.push(name);
        switch (name) {
          case '1':
            this.getSetting();
            break;
          case '2':
            this.getArticle(1);
            break;
          case '3':
            this.getComment(1);
            break;
          case '4':
            this.getUser(1);
            break;
          case '5':
            break;
          default:
            break;
        }

      },
      // 设置管理
      getSetting() {
        querySetting().then(res => {
          this.settingList = res.data;
        })
      },
      changeSetting(row) {
        changeSetting(row.id, row.allowOrNot == 1 ? 0 : 1).then(res => {
          if (res.status==2000){
            this.$notify.success({
              message: res.msg
            })
          }
        })
        row.allowOrNot = row.allowOrNot == 1 ? 0 : 1;
      },
      // 文章管理
      getArticle(curPage){
        queryArticle(curPage,10).then(res => {
          this.articleList = res.data.data;
          this.articleTotal = res.data.total;
        })
      },
      articleActivation(row){
        this.toTrialArticle(row.articleId, 0);
        row.deleted = 0;
      },
      articleBan(row){
        this.toTrialArticle(row.articleId, 2);
        row.deleted = 2;
      },
      ArticleRemove(row){
        this.toTrialArticle(row.articleId, 1);
        row.deleted = 1;
      },
      toTrialArticle(id, status) {
        trialArticle(id, status).then(res => {
          if (res.status === 2000) {
            this.$notify.success({
              message: res.msg
            })
          } else {
            this.$notify.error({
              message: res.msg
            })
          }
        })
      },
      articleChange(curPage){
        this.getArticle(curPage);
      },

      // 评论管理
      getComment(curPage) {
        queryComment(curPage, 10).then(res => {
          this.commentList = res.data.data;
          this.commentTotal = res.data.total;
        })
      },
      commentChange(curPage){
        this.getComment(curPage);
      },
      commentActivation(row){
        this.toTrialComment(row.type, row.id, 0);
        row.deleted = 0;
      },
      commentBan(row){
        this.toTrialComment(row.type, row.id, 2);
        row.deleted = 2;
      },
      commentRemove(row){
        this.toTrialComment(row.type, row.id, 1);
        row.deleted = 1;
      },
      toTrialComment(type, id, status){
        if (type == '博客'){
          trialComment(0 ,id, status).then(res => {
            if (res.status === 2000) {
              this.$notify.success({
                message: res.msg
              })
            } else {
              this.$notify.error({
                message: res.msg
              })
            }
          })
        }else {
          trialComment(1 ,id, status).then(res => {
            if (res.status === 2000) {
              this.$notify.success({
                message: res.msg
              })
            } else {
              this.$notify.error({
                message: res.msg
              })
            }
          })
        }
      },

      // 用户管理
      getUser(curPage) {
        queryUser(curPage, 10).then(res => {
          this.usersTotal = res.data.total;
          this.usersList = res.data.data;
        })

      },
      userActivation(row) {
        this.toTrialUser(row.authorId, 0);
        row.deleted = 0;
      },
      userBan(row) {
        this.toTrialUser(row.authorId, 2);
        row.deleted = 2;
      },
      userRemove(row) {
        this.toTrialUser(row.authorId, 1);
        row.deleted = 1;
      },
      toTrialUser(id, status) {
        trialUser(id, status).then(res => {
          if (res.status === 2000) {
            this.$notify.success({
              message: res.msg
            })
          } else {
            this.$notify.error({
              message: res.msg
            })
          }
        })
      },
      userChange(curPage){
        this.getUser(curPage);
      }


    }
  }
</script>

<style scoped>
  .layout {
    border: 1px solid #d7dde4;
    background: #f5f7f9;
    height: 100vh;
    position: relative;
    border-radius: 4px;
    overflow: hidden;
  }

  .center {
    text-align: center;
  }

  .top-space {
    margin: 15px 0;
  }
</style>