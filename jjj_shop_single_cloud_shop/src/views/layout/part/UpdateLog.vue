<template>
  <el-dialog
    title="商品日志"
    v-model="dialogVisible"
    @close="dialogFormVisible"
    :close-on-click-modal="false"
    :close-on-press-escape="false"
    :fullscreen="fullscreen"
    :show-close="false"
    align-center
  >
    <template #header="{ close, titleId, titleClass }">
      <div class="my-header d-b-c">
        <div :id="titleId" :class="titleClass" class="flex-1">商品日志</div>
        <el-icon @click="fullscreen = !fullscreen">
          <Plus />
        </el-icon>
        <el-icon @click="close">
          <Close />
        </el-icon>
      </div>
    </template>
    <div class="common-seach-wrap column-dialog">
      <div>
        <el-form
          size="small"
          :inline="true"
          :model="searchForm"
          class="demo-form-inline"
        >
          <el-row>
            <el-col :span="4">
              <el-form-item label="商品编码">
                <el-input
                  size="small"
                  v-model="searchForm.productCode"
                  placeholder=""
                >
                </el-input> </el-form-item
            ></el-col>
            <el-col :span="4">
              <el-form-item label="操作人">
                <el-select
                  v-model="searchForm.shopUserId"
                  placeholder="请选择"
                >
                  <el-option
                    v-for="(item, index) in userList"
                    :key="index"
                    :label="item.userName"
                    :value="item.shopUserId"
                  />
                </el-select> </el-form-item
            ></el-col>
            <el-col :span="8">
              <el-form-item label="业务日期">
                <el-date-picker
                  style="width: 200px"
                  v-model="creationTime"
                  @change="changeTime"
                  type="daterange"
                  clearable
                  range-separator="至"
                  start-placeholder="开始时间"
                  end-placeholder="结束时间"
                >
                </el-date-picker>
              </el-form-item>
            </el-col>
            <el-col :span="4">
              <el-form-item label="操作对象">
                <el-select v-model="searchForm.optType" placeholder="请选择">
                  <el-option
                    v-for="(item, index) in optTypeList"
                    :key="index"
                    :label="item.name"
                    :value="item.val"
                  />
                </el-select> </el-form-item
            ></el-col>
          </el-row>
          <el-row>
            <el-col :span="4">
              <el-form-item>
                <el-button size="small" type="primary" @click="onSubmit"
                  >查询</el-button
                >
                <el-button @click="reset" size="small">重置</el-button>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
      </div>
      <div class="common-level-rail d-b-c">
        <div class="d-s-c flex-1"></div>
        <!-- 分页 -->
        <el-pagination
          size="small"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          background
          :page-sizes="[20, 50, 100]"
          :current-page="curPage"
          :page-size="pageSize"
          layout="total, prev, next, jumper,sizes"
          :total="totalDataNumber"
        >
        </el-pagination>
      </div>
      <el-table
        size="small"
        class="flex-1"
        :data="productData"
        border
        v-loading="loading"
      >
        <el-table-column
          prop="productCode"
          label="商品编码"
          show-overflow-tooltip
        ></el-table-column>
        <el-table-column
          prop="content"
          label="操作内容"
          show-overflow-tooltip
        ></el-table-column>
        <el-table-column
          prop="title"
          label="操作"
          width="100"
        ></el-table-column>
        <el-table-column
          prop="userName"
          label="操作人"
          width="100"
        ></el-table-column>
        <el-table-column prop="createTime" label="操作时间" width="130">
        </el-table-column>
      </el-table>
    </div>
  </el-dialog>
</template>

<script>
import ProductApi from "@/api/product.js";
import { formatDate } from "@/utils/DateTime.js";
export default {
  components: {},
  data() {
    return {
      searchForm: {
        pageSize: 20,
        pageIndex: 1,
        productCode: "",
        shopUserId: "",
        optType: "",
        startDate: "",
        endDate: "",
      },
      productData: [],
      accountList: [],
      creationTime: [],
      formRules: {},
      /*左边长度*/
      formLabelWidth: "100px",
      fullscreen: false,
      /*是否显示*/
      dialogVisible: true,
      totalDataNumber: 0,
      pageSize: 20,
      curPage: 1,
      loading: false,
      userList: [],
      optTypeList: [
        {
          name: "商品",
          val: 1,
        },
        {
          name: "规格",
          val: 2,
        },
        // {
        //   name: "订单",
        //   val: 3,
        // },
      ],
    };
  },
  created() {
    this.getselect();
  },
  methods: {
    changeTime(val) {
      this.searchForm.startDate = val ? val[0] : "";
      this.searchForm.endDate = val ? val[1] : "";
    },
    onSubmit() {
      this.loading = true;
      this.getselect();
    },
    reset() {
      this.searchForm = {};
      this.creationTime = [];
      this.getselect();
    },
    /*选择第几页*/
    handleCurrentChange(val) {
      let self = this;
      self.curPage = val;
      self.loading = true;
      self.getselect();
    },
    /*每页多少条*/
    handleSizeChange(val) {
      this.curPage = 1;
      this.pageSize = val;
      this.getselect();
    },
    getselect() {
      let self = this;
      self.loading = true;
      let params = self.searchForm;
      params.pageIndex = self.curPage;
      params.pageSize = self.pageSize;
      params.startDate = formatDate(self.searchForm.startDate);
      params.endDate = formatDate(self.searchForm.endDate);
      console.log(params, "params");
      ProductApi.productLogIndex(params, true)
        .then((res) => {
          self.loading = false;
          self.productData = res.data.log.records;
          self.totalDataNumber = res.data.log.total;
          self.userList = res.data.user;
        })
        .catch((e) => {
          self.loading = false;
          console.log(e);
        });
    },
    /*关闭弹窗*/
    dialogFormVisible() {
      this.$emit("close", false);
    },
  },
};
</script>

<style scoped>
.img {
  margin-top: 10px;
}

.fdw {
  display: flex;
  align-items: center;
}

.tip {
  width: 190px;
  height: 21px;
  color: rgba(153, 153, 153, 1);
  font-size: 14px;
  text-align: left;
  margin-left: 100px;
  margin-bottom: 20px;
}

.titles {
  left: 424px;
  top: 418px;
  width: 262px;
  height: 21px;
  color: rgba(16, 16, 16, 1);
  font-size: 14px;
  text-align: left;
  margin-left: 40px;
  margin-bottom: 20px;
}

.btns {
  display: flex;
  flex-direction: row;
  width: 109px;
  height: 36px;
  font-size: 14px;
  text-align: center;

  .thebtn1 {
    color: #ffffff;
    background: #43aa8b;
  }
}

.select {
  padding-right: 40px;
}
</style>
