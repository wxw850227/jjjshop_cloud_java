<template>
  <div class="login-bg" :style="'background-image:url(' + bgimg_url + ');'">
    <el-form
      :model="ruleForm"
      :rules="rules"
      ref="ruleForm"
      label-position="left"
      label-width="0px"
      class="demo-ruleForm login-container d-b-c"
    >
      <div class="log_img">
        <img :src="left_url" />
      </div>
      <div class="flex-1 login-box">
        <h3 class="title" style="margin-bottom: 20px">{{ shop_name }}</h3>

        <!--用户名-->
        <el-form-item prop="account">
          <div class="left-img-input">
            <img class="l-img" src="/src/assets/img/user.png" />
            <el-input
              @keyup.enter.native="SubmitFunc"
              type="text"
              v-model="ruleForm.account"
              auto-complete="off"
              placeholder="账号"
            ></el-input>
          </div>
        </el-form-item>
        <!--密码-->
        <el-form-item prop="checkPass">
          <div class="left-img-input">
            <img class="l-img" src="/src/assets/img/password.png" />
            <el-input
              @keyup.enter.native="SubmitFunc"
              show-password
              type="password"
              v-model="ruleForm.checkPass"
              auto-complete="off"
              placeholder="密码"
            ></el-input>
          </div>
        </el-form-item>
        <!--登录-->
        <el-form-item
          ><el-button
            type="primary"
            class="logins"
            @click.native.prevent="SubmitFunc"
            :loading="logining"
            >登录</el-button
          ></el-form-item
        >
      </div>
    </el-form>
  </div>
</template>

<script>
import IndexApi from "@/api/index.js";
import bgimg from "/static/imgs/login_bg.png";
import leftimg from "/static/imgs/login1.png";
import UserApi from "@/api/user.js";
import { useUserStore } from "@/store";
const { afterLogin } = useUserStore();

export default {
  data() {
    return {
      /*是否正在加载*/
      loading: true,
      /*背景图片*/
      bgimg_url: "",
      /*是否正在提交*/
      logining: false,
      /*表单对象*/
      ruleForm: {
        /*用户名*/
        account: "admin",
        /*密码*/
        checkPass: "123456",
      },
      /*验证规则*/
      rules: {
        /*用户名*/
        account: [
          {
            required: true,
            message: "请输入用户名",
            trigger: "blur",
          },
        ],
        /*密码*/
        checkPass: [
          {
            required: true,
            message: "请输入密码",
            trigger: "blur",
          },
        ],
      },
      /*基础配置*/
      baseData: {},
      left_url: "",
    };
  },
  created() {
    this.getData();
    if (this.$route.query.from && this.$route.query.from == "admin") {
      this.saasLogin();
    }
  },
  methods: {
    /*获取基础配置*/
    getData() {
      let self = this;
      IndexApi.base(true)
        .then((res) => {
          self.loading = false;
          const { data } = res;
          self.shop_name = data.shopName;
          if (data.shopLogoImg && data.shopLogoImg != "") {
            self.left_url = data.shopLogoImg;
          } else {
            self.left_url = leftimg;
          }
          if (data.shopBgImg != "") {
            self.bgimg_url = data.shopBgImg;
          } else {
            self.bgimg_url = bgimg;
          }
        })
        .catch((error) => {
          self.loading = false;
        });
    },

    /*登录方法*/
    SubmitFunc(ev) {
      var _this = this;
      this.$refs.ruleForm.validate((valid) => {
        if (valid) {
          this.logining = true;
          var Params = {
            username: this.ruleForm.account,
            password: this.ruleForm.checkPass,
          };
          /*调用登录接口*/
          UserApi.login(Params, true)
            .then(async (res) => {
              this.logining = false;
              if (res.code == 1) {
                await afterLogin(res);
                this.logining = false;
                this.$router.push({
                  path: "/home",
                });
              } else {
                ElMessage({
                  message: "登录失败",
                  type: "error",
                });
              }
            })
            .catch((error) => {
              this.logining = false;
            });
        }
      });
    },
    saasLogin() {
      /*调用登录接口*/
      UserApi.saasLogin({}, true)
        .then((res) => {
          console.log(res);
          if (res.code == 1) {
            delCookie("baseInfo");
            /*保存个人信息*/
            setCookie("userinfo", res.data);
            /*设置一个登录状态*/
            setCookie("isLogin", true);
            /*跳转到首页*/
            this.$router.push({
              path: "/",
            });
          } else {
            ElMessage({
              message: "登录失败",
              type: "error",
            });
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};
</script>

<style lang="scss" scoped>
:deep(.el-input__wrapper) {
  box-shadow: none !important;
}

	.logins {
		width: 100%;
		height: 44px;
		background: #409eff;
		color: #fff;
		border-radius: 5px;
		margin-top: 20px;
	}

.login-bg {
  width: 100%;
  height: 100%;
  background-size: cover;
  display: -webkit-box;
  display: -ms-flexbox;
  display: flex;
  -webkit-box-orient: vertical;
  -webkit-box-direction: normal;
  -ms-flex-direction: column;
  flex-direction: column;
  -webkit-box-align: center;
  -ms-flex-align: center;
  align-items: center;
}

.login-container {
  box-shadow: 0 0px 8px 0 rgba(0, 0, 0, 0.1), 0 1px 0px 0 rgba(0, 0, 0, 0.04);
  -webkit-border-radius: 10px;
  border-radius: 10px;
  -moz-border-radius: 10px;
  background-clip: padding-box;
  position: fixed;
  width: 896px;
  height: 408px;
  left: 0;
  right: 0;
  top: 0;
  bottom: 0;
  margin: auto;
  background-color: #ffffff;

  .title {
    margin: 0px auto 40px auto;
    text-align: center;
    font-size: 28px;
    font-family: Microsoft YaHei;
    font-weight: bold;
    color: #333333;
  }

  .remember {
    margin: 0px 0px 35px 0px;
  }
}

.log_img {
  img {
    width: auto;
    height: 408px;
    border-radius: 10px 0 0 10px;
    display: block;
    /* 避免行内元素特有的空白间隙问题 */
    max-height: 100%;
  }
}

.login-box {
  padding: 45px 39px 31px 39px;
  height: 408px;
  box-sizing: border-box;
}

.left-img-input {
  width: 370px;
  height: 44px;
  line-height: 44px;
  background: #ffffff;
  border-radius: 5px;
  border: 1px solid #eeeeee;
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 0 14px;
  // margin-bottom: 10px;

  .l-img {
    width: 20px;
    height: 20px;
    margin-right: 10px;
    flex-shrink: 0;
  }

  .l-input {
    flex: 1;
    border: none;
    background: none;
    font-size: 14px;
    color: #666666;
  }

  .el-input__inner {
    border: none;
    padding: 0;
  }
}

.codeImage {
  cursor: pointer;
  margin-left: 20px;
}
</style>
