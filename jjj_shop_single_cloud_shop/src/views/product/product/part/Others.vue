<template>
  <div class="buy-set-content pl16 pr16">
    <!--其他设置-->
    <div class="common-form">其他设置</div>
    <el-form-item label="商品属性：">
      <el-radio-group v-model="form.model.isVirtual">
        <el-radio :value="0">实物商品</el-radio>
        <el-radio :value="1">虚拟商品(无需发货)</el-radio>
      </el-radio-group>
    </el-form-item>
    <el-form-item
      label="运费模板："
      :rules="[{ required: true, message: '选择运费模板' }]"
      prop="model.deliveryId"
      v-if="form.model.isVirtual == 0"
    >
      <el-select v-model="form.model.deliveryId" style="width: 460px">
        <el-option
          v-for="item in form.delivery"
          :value="item.deliveryId"
          :key="item.deliveryId"
          :label="item.name"
        ></el-option>
      </el-select>
    </el-form-item>
    <el-form-item label="初始销量：">
      <el-input
        type="number"
        min="0"
        v-model="form.model.salesInitial"
        class="max-w460"
      ></el-input>
    </el-form-item>
    <el-form-item
      label="商品排序："
      :rules="[{ required: true, message: ' ' }]"
      prop="model.productSort"
    >
      <el-input
        type="number"
        min="0"
        v-model="form.model.productSort"
        class="max-w460"
      ></el-input>
    </el-form-item>
    <el-form-item
      label="限购数量："
      :rules="[{ required: true, message: ' ' }]"
      prop="model.limitNum"
    >
      <el-input
        type="number"
        min="0"
        v-model="form.model.limitNum"
        class="max-w460"
      ></el-input>
      <div class="gray9">每个会员购买的最大数量，0为不限购</div>
    </el-form-item>
    <el-form-item label="发货类型：" v-if="form.model.isVirtual == 1">
      <el-radio-group v-model="form.model.virtualAuto">
        <el-radio :value="1">自动</el-radio>
        <el-radio :value="0">手动</el-radio>
      </el-radio-group>
    </el-form-item>
    <el-form-item
      label="虚拟内容："
      :rules="[{ required: true, message: '请填写虚拟内容' }]"
      prop="model.virtualContent"
      v-if="form.model.isVirtual == 1"
    >
      <el-input
        type="text"
        v-model="form.model.virtualContent"
        class="max-w460"
      ></el-input>
      <div class="gray9">虚拟物品内容</div>
    </el-form-item>
    <el-form-item label="会员等级限制：">
      <el-select
        v-model="form.model.gradeIds"
        multiple
        placeholder="请选择"
        style="width: 460px"
      >
        <el-option
          v-for="item in form.gradeList"
          :key="item.gradeId"
          :label="item.name"
          :value="item.gradeId"
        >
        </el-option>
      </el-select>
      <div class="gray9">仅设置的等级会员可购买，不设置则都可以购买</div>
    </el-form-item>
    <el-form-item label="自定义表单：">
      <el-switch
        :active-value="true"
        :inactive-value="false"
        v-model="customBtn"
        @change="customMessBtn"
        size="large"
      >
        <span slot="open">开启</span>
        <span slot="close">关闭</span>
      </el-switch>
      <div class="addCustom_content" v-if="customBtn">
        <div
          v-for="(item, index) in form.model.customForm"
          :key="index"
          class="custom_box"
        >
          <el-input
            v-model.trim="item.title"
            :placeholder="'表单标题' + (index + 1)"
            style="width: 150px; margin-right: 10px"
            :maxlength="10"
          />
          <el-select
            v-model="item.label"
            style="width: 200px; margin-left: 6px; margin-right: 10px"
          >
            <el-option
              v-for="items in CustomList"
              :value="items.value"
              :key="items.value"
              :label="items.label"
            ></el-option>
          </el-select>
          <el-checkbox v-model="item.status">必填</el-checkbox>
          <div class="addfont" @click="delcustom(index)">
            <!-- <el-icon>
              <Delete />
            </el-icon> -->删除
          </div>
        </div>
      </div>
      <div class="addCustomBox" v-if="customBtn">
        <el-button class="btn" @click="addcustom">+ 添加表单</el-button>
        <div class="titTip">
          用户下单时需填写的信息，最多可设置10条，设置了自定义表单的商品不能加入购物车
        </div>
      </div>
    </el-form-item>
  </div>
</template>

<script>
export default {
  data() {
    return {
      unit: "%",
      gradeUnit: "%",
      customBtn: false,
      CustomList: [
        {
          value: "text",
          label: "文本框",
        },
        {
          value: "number",
          label: "数字",
        },
        {
          value: "email",
          label: "邮件",
        },
        {
          value: "data",
          label: "日期",
        },
        {
          value: "time",
          label: "时间",
        },
        {
          value: "id",
          label: "身份证",
        },
        {
          value: "phone",
          label: "手机号",
        },
        // {
        //   value: "img",
        //   label: "图片",
        // },
      ],
    };
  },
  created() {
    if (this.form.model.aloneGradeType == 20) {
      this.gradeUnit = "元";
    }
    if (this.form.model.agentMoneyType == 20) {
      this.unit = "元";
    }
    if (this.form.model.customFormStr) {
      this.form.model.customForm = JSON.parse(this.form.model.customFormStr);
    }
    if (this.form.model.customForm && this.form.model.customForm.length != 0) {
      this.customBtn = true;
    }
  },
  inject: ["form"],
  methods: {
    customMessBtn(e) {
      if (!e) {
        this.form.model.customForm = [];
      }
    },
    addcustom() {
      if (!this.form.model.customForm) {
        this.form.model.customForm = [];
      }
      if (this.form.model.customForm && this.form.model.customForm.length > 9) {
        ElMessage.warning("最多添加10条");
      } else {
        this.form.model.customForm.push({
          title: "",
          label: "text",
          value: "",
          status: false,
        });
      }
    },
    delcustom(index) {
      console.log(index, "index");
      this.form.model.customForm.splice(index, 1);
    },
    /*换算单位*/
    changeMoneyType: function (val) {
      if (val == "10") {
        this.unit = "%";
      } else {
        this.unit = "元";
      }
    },
    /*换算单位*/
    changeGradeType: function (val) {
      if (val == "10") {
        this.gradeUnit = "%";
      } else {
        this.gradeUnit = "元";
      }
    },
  },
};
</script>

<style scoped>
.addCustom_content {
  margin-top: 20px;

  .custom_box {
    margin-bottom: 10px;
  }
}

.addCustomBox {
  margin-top: 12px;
  font-size: 13px;
  font-weight: 400;
  color: var(--prev-color-primary);

  .btn {
    cursor: pointer;
    width: max-content;
    background-color: rgba(64, 149, 229, 1);
    color: rgba(255, 255, 255, 1);
  }
}

.titTip {
  display: inline-bolck;
  font-size: 12px;
  line-height: 24px;
  font-weight: 400;
  color: #999999;
}
.addfont {
  display: inline-block;
  font-size: 12px;
  font-weight: 400;
  color: #4095e5;
  margin-left: 14px;
  cursor: pointer;
}
</style>
