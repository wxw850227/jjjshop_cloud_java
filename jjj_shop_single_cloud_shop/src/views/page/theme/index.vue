<template>
  <div class="product-add">
    <div class="border-b p10">
      <span class="text-1">主题设置</span>
    </div>
    <el-card :bordered="false" shadow="never" class="p20 h100">
      <div class="acea-row">
        <div class="text-1">选择配色方案:</div>
        <div
          class="tab_color"
          v-for="(item, index) in tabList"
          :key="index"
          :class="current === index + 1 ? 'active' : ''"
          @click="selected(index)"
        >
          <div class="color_cont flex align-center">
            <div style="line-height: 24px">{{ item.tit }}</div>
            <!-- <div class="mr-2" :class="item.class">
              <span
                class="iconfont iconxuanzhong6"
                v-show="current == index + 1"
              ></span>
            </div> -->
          </div>
        </div>
      </div>

      <div class="acea-row row-top">
        <div class="text-1">选择配色方案:</div>
        <div class="pictrue" v-for="(item, index) in picList" :key="index">
          <img :src="item.image" />
        </div>
      </div>
    </el-card>
    <div class="common-button-wrapper">
      <el-button type="primary" @click="onSubmit" :loading="loading"
        >保存</el-button
      >
    </div>
  </div>
</template>

<script>
import PageApi from "@/api/page.js";
import Upload from "@/components/file/Upload.vue";
import { formatModel } from "@/utils/base.js";
import theme1 from "@/assets/img/theme1.jpg";
import theme1c from "@/assets/img/theme1_c.jpg";
import theme2 from "@/assets/img/theme2.jpg";
import theme3 from "@/assets/img/theme3.jpg";
import theme4 from "@/assets/img/theme4.jpg";
import theme5 from "@/assets/img/theme5.jpg";
import theme6 from "@/assets/img/theme6.jpg";
import theme7 from "@/assets/img/theme7.jpg";
export default {
  data() {
    return {
      tabList: [
        {
          tit: "主题一",
        },
        {
          tit: "主题二",
        },
        {
          tit: "主题三",
        },
        {
          tit: "主题四",
        },
        {
          tit: "主题五",
        },
        {
          tit: "主题六",
        },
        {
          tit: "主题七",
        },
      ],
      /*是否正在加载*/
      loading: false,
      /*form表单数据*/
      form: {
        theme: "red",
      },
      all_type: [],
      type: [],
      picList: [],
      /*是否打开图片选择*/
      isupload: false,
      theme7_url: [
        {
          image: theme7,
        },
      ],
      theme6_url: [
        {
          image: theme6,
        },
      ],
      theme5_url: [
        {
          image: theme5,
        },
      ],
      theme4_url: [
        {
          image: theme4,
        },
      ],
      theme3_url: [
        {
          image: theme3,
        },
      ],
      theme2_url: [
        {
          image: theme2,
        },
      ],
      theme1_url: [
        {
          image: theme1,
        },
      ],

      current: 1,
    };
  },
  created() {
    this.getParams();
  },

  methods: {
    selected(index) {
      this.current = index + 1;
      this.picList = this[`theme${index + 1}_url`];
    },
    /*获取配置数据*/
    getParams() {
      let self = this;
      PageApi.themeDetail({}, true)
        .then((res) => {
          self.current = res.data.theme;
          self.selected(Number(res.data.theme) - 1);
          self.loading = false;
        })
        .catch((error) => {});
    },

    /*提交*/
    onSubmit() {
      let self = this;
      let params = {};
      params.theme = this.current;
      self.loading = true;
      PageApi.editTheme(params, true)
        .then((data) => {
          self.loading = false;
          ElMessage({
            message: "恭喜你，设置成功",
            type: "success",
          });
          self.$router.push("/page/theme/index");
        })
        .catch((error) => {
          self.loading = false;
        });
    },
  },
};
</script>
<style lang="scss" scoped>
.text-1 {
  color: rgb(16, 16, 16);
  font-size: 12px;
  text-align: center;
  margin-right: 20px;
  margin-top: 10px;
}

.mb10 {
  margin-bottom: 10px;
}

.mr10 {
  margin-right: 10px;
}

.phone-theme > img {
  width: 250px;
}

.d-c-s {
  display: flex;
  justify-content: center;
  align-items: flex-start;
}

.box {
  width: 100px;
  text-align: center;
}

.pictrue {
  width: 250px;
  height: 100%;
  margin: 10px 24px 0 0;

  img {
    width: 100%;
    height: 100%;
  }
}

.footer {
  width: 100%;
  height: 70px;
  box-shadow: 0px -2px 4px rgba(0, 0, 0, 0.03);
  background-color: #fff;
  position: fixed;
  bottom: 0;
  left: 0;
  z-index: 9;
}

.main_c {
  width: 25px;
  height: 25px;
  border-radius: 5px;
  text-align: center;
  line-height: 25px;
  font-size: 14px;
}

.tab_color {
  width: 114px;
  height: 45px;
  border: 1px solid #e5e5e5;
  margin-bottom: 10px;
  margin-right: 20px;
  border-radius: 5px;
  display: flex;
  justify-content: center;
  align-items: center;
  cursor: pointer;
}

.color_cont {
  font-size: 14px;
}

.mr-2 {
  margin-right: 10px;
}

.color_bdg {
  display: block;
  width: 18px;
  height: 18px;
  border-top: 1px solid #fff;
  border-bottom: 1px solid #fff;
}

.blue {
  background-color: #1ca5e9;
}

.green {
  background-color: #42ca4d;
}

.red {
  background-color: #e93323;
}

.pink {
  background-color: #ff448f;
}

.orange {
  background-color: #fe5c2d;
}

.active {
  border: 1px solid #1ca5e9;
}

:deep(.ivu-radio-border) {
  position: relative;
}

.iconfont {
  font-size: 12px;
  color: #fff;
}

:deep(.ivu-radio-inner:after) {
  background-color: unset;
  transform: unset;
}

:deep(.i-layout-page-header) {
  display: flex;
  align-items: center;
  justify-content: space-between;
}
</style>
