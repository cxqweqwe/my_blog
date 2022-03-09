<template>
  <div class="layout">
    <Layout>
      <!--      <Header class="back-color">-->
      <!--        <Menu mode="horizontal" theme="dark" active-name="1" class="back-color">-->
      <!--          <div class="" style="width: 200px; height: 100%; background: white">-->
      <!--            <img src="http://image.fangweb.top/FANG-Logo.png" alt="logo">-->
      <!--          </div>-->
      <!--          <div class="layout-nav">-->
      <!--            <MenuItem name="1">-->
      <!--              <Icon type="ios-navigate"></Icon>-->
      <!--              Item 1-->
      <!--            </MenuItem>-->
      <!--            <MenuItem name="2">-->
      <!--              <Icon type="ios-keypad"></Icon>-->
      <!--              Item 2-->
      <!--            </MenuItem>-->
      <!--            <MenuItem name="3">-->
      <!--              <Icon type="ios-analytics"></Icon>-->
      <!--              Item 3-->
      <!--            </MenuItem>-->
      <!--            <MenuItem name="4">-->
      <!--              <Icon type="ios-paper"></Icon>-->
      <!--              Item 4-->
      <!--            </MenuItem>-->
      <!--          </div>-->
      <!--        </Menu>-->
      <!--      </Header>-->
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
            <h1>{{active}}</h1>
          </Content>
        </Layout>
      </Layout>
    </Layout>
  </div>
</template>

<script>
  export default {
    name: "AdminMain",
    data() {
      return {
        active: 1,
      }
    },
    methods: {
      select(name) {
        this.active = name;
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

  .layout-logo {
    width: 100px;
    height: 30px;
    background: #5b6270;
    border-radius: 3px;
    float: left;
    position: relative;
    top: 15px;
    left: 20px;
  }

  .layout-nav {
    width: 420px;
    margin: 0 auto;
    margin-right: 20px;
  }

  .back-color {
    background: linear-gradient(to right, #FE4F70 0%, #FFA387 100%);
  }
</style>