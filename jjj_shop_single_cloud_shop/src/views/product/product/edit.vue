<template>
  <div class="product-add" v-loading="loading">
    <el-tabs v-model="activeName" type="card">
      <el-tab-pane label="基础设置" name="basic"></el-tab-pane>
      <el-tab-pane label="规格库存" name="spec"></el-tab-pane>
      <el-tab-pane label="商品详情" name="content"></el-tab-pane>
      <el-tab-pane label="高级设置" name="buyset"></el-tab-pane>
    </el-tabs>
    <!--form表单-->
    <el-form size="small" ref="form" :model="form" label-width="180px" v-if="!loading">
      <!--基础信息-->
      <Basic v-show="activeName == 'basic'"></Basic>
      <!--规格设置-->
      <Spec v-show="activeName == 'spec'"></Spec>
      <!--商品详情-->
      <Content v-show="activeName == 'content'"></Content>
      <!--高级设置-->
      <Buyset v-show="activeName == 'buyset'"></Buyset>
      <!--提交-->
      <div class="common-button-wrapper">
        <el-button size="small" type="info" @click="cancelFunc">取消</el-button>
        <el-button size="small" type="primary" @click="onSubmit" :disabled="save_loading">发布</el-button>
      </div>
    </el-form>
  </div>
</template>

<script>
import PorductApi from '@/api/product.js';
import Basic from './part/Basic.vue';
import Spec from './part/Spec.vue';
import Content from './part/Content.vue';
import Buyset from './part/Buyset.vue';
import { formatModel } from '@/utils/base.js';
export default {
  components: {
    /*基础信息*/
    Basic,
    /*规格信息*/
    Spec,
    /*商品详情*/
    Content,
    /*高级设置*/
    Buyset
  },
  data() {
    return {
      activeName: 'basic',
      /*商品ID*/
      productId: 0,
      /*判断是编辑*/
      scene: 'edit',
      /*是否正在加载*/
      loading: true,
      /*是否正在提交保存*/
      save_loading: false,
      /*form表单数据*/
      form: {
        model: {},
        /*商品分类*/
        category: [],
        /*运费模板*/
        delivery: [],
        /*会员等级*/
        gradeList: [],
        /*规格数据*/
        specData: null,
        /*是否锁住*/
        isSpecLocked: false,
        /*万能表单*/
        tableList: []
      },
      /*模型数据*/
      model: {
        /*商品名称*/
        productName: '',
        productNo: '',
        videoId: 0,
        videoFilePath: '',
        posterId: 0,
        posterFilePath: '',
        /*商品分类*/
        categoryId: null,
        /*商品图片*/
        image: [],
        isPicture: 0,
        contentImage: [],
        /*商品卖点*/
        sellingPoint: '',
        /*规格类别,默认10单规格，20多规格*/
        specType: 10,
        /*库存计算方式,默认20付款减库存，10下单减库存*/
        deductStockType: 20,
        /*检查用户等级*/
        isAloneGrade: 0,
        /*单规格*/
        sku: {
          /*商品价格*/
          productPrice: 0,
          /*商品划线价*/
          linePrice: 0,
          /*库存数量*/
          stockNum: 0,
          /*商品重量*/
          productWeight: 0,
          /*商品编码*/
          productNo: ''
        },
        /*多规格类别*/
        specMany: {
          /*多规格类别*/
          specAttr: [],
          /*多规格表格数据*/
          specList: []
        },
        /*商品详情内容*/
        content: '',
        /*运费模板ID*/
        deliveryId: '',
        /*商品状态*/
        productStatus: 10,
        /*初始销量*/
        salesInitial: 0,
        /*商品排序，默认100*/
        productSort: 100,
        /*是否开启积分赠送,默认1为开启，0为关闭*/
        isPointsGift: 1,
        /*是否允许使用积分抵扣,默认1为允许，0为不允许*/
        isPointsDiscount: 1,
        /*最大积分抵扣数量*/
        maxPointsDiscount: 0,
        /*会员折扣设置,默认1为单独设置折扣,0为默认折扣*/
        isEnableGrade: 1,
        /*等级金额类型,默认10为百分比，20为固定金额*/
        aloneGradeType: 10,
        /*限购会员*/
        gradeIds: [],
        /*是否虚拟商品*/
        isVirtual: 0,
        /*限购数量*/
        limitNum: 0,
        /*虚拟商品发货方式*/
        virtualAuto: 0,
        /*虚拟商品发货内容*/
        virtualContent: '',
        /*关联表单*/
        tableId: ''
      }
    };
  },
  provide: function() {
    return {
      form: this.form
    };
  },
  created() {
    /*获取列表*/
    this.productId = this.$route.query.productId;
    this.scene = this.$route.query.scene;
    this.getData();
  },
  methods: {
    /**
     * 获取基础数据
     */
    getData: function() {
      let self = this;
      PorductApi.getEditData(
        {
          productId: self.productId,
          scene: self.scene
        },
        true
      )
        .then(res => {
          self.loading = false;
          /*单独商品折扣*/
          if(res.data.model.aloneGradeEquityJson != null && typeof res.data.model.aloneGradeEquityJson != 'undefined'){
            let equitys = res.data.model.aloneGradeEquityJson;
            res.data.gradeList.forEach(item => {
              item.productEquity = equitys[item.gradeId];
            });
          }
          Object.assign(self.form, res.data);
          if (res.data.specData) {
            self.form.model.specMany = res.data.specData;
          } else {
            self.form.model.specMany = {
              specAttr: [],
              specList: []
            }
            self.form.model.sku = res.data.model.sku[0];
          }
          // 等级id转换成数组
          if(self.form.model.gradeIds != ''){
            self.form.model.gradeIds = self.form.model.gradeIds.split(",");
            for(let i=0;i<self.form.model.gradeIds.length;i++){
              self.form.model.gradeIds[i] = parseInt(self.form.model.gradeIds[i]);
            }
          }
          if(self.form.model.tableId == 0){
            self.form.model.tableId = '';
          }
        })
        .catch(error => {
          self.loading = false;
          console.log(error);
        });
    },

    /*转JSON字符串*/
    convertJson(list){
      let obj={};
      list.forEach(item=>{
        obj[item.gradeId]=item.productEquity;
      });
      return JSON.stringify(obj);
    },

    /*提交*/
    onSubmit: function() {
      let self = this;
      self.$refs.form.validate(valid => {
        if (valid) {
          let params = formatModel(self.model, self.form.model);
          params.scene = self.scene;
          params.productId = self.productId;
          params.aloneGradeEquity = self.convertJson(self.form.gradeList);
          if(params.gradeIds.length > 0){
            params.gradeIds = params.gradeIds.join(',');
          }else{
            params.gradeIds = '';
          }
          params.isSpecLocked = self.form.isSpecLocked;
          self.save_loading = true;
          PorductApi.editProduct(params, true)
            .then(data => {
              self.save_loading = false;
              ElMessage ({
                message: '保存成功',
                type: 'success'
              });
              self.cancelFunc();
            })
            .catch(error => {
              self.save_loading = false;
            });
        }
      });
    },

    /*保存为草稿*/
    Draft() {
      let self = this;
      self.form.model.productStatus = 30;
      self.onSubmit();
    },

    /*取消*/
    cancelFunc() {
      this.$router.back(-1);
    }
  }
};
</script>

<style lang="scss" scoped>
.basic-setting-content {
}

.product-add {
  padding-bottom: 50px;
}
</style>
