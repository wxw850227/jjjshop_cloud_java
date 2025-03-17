<template>
  <el-dialog
    title="去发货"
    v-model="dialogVisible"
    @close="dialogFormVisible"
    :close-on-click-modal="false"
    :close-on-press-escape="false"
    width="30%"
  >
    <el-form
      size="small"
      :model="form"
      ref="form"
      :label-width="formLabelWidth"
    >
      <el-form-item label="选择类型">
        <div>
          <el-radio v-model="form.isOpen" :label="1">发货</el-radio>
          <el-radio v-model="form.isOpen" :label="0">无需配送</el-radio>
        </div>
      </el-form-item>
      <el-form-item label="快递公司">
        <el-select v-model="form.expressId" placeholder="请选择">
          <el-option
            class="max-w460"
            :label="item.expressName"
            v-for="(item, index) in expressList"
            :key="index"
            :value="item.expressId"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="物流单号"
        ><el-input v-model="form.expressNo" class="max-w460"></el-input
      ></el-form-item>
    </el-form>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="dialogFormVisible">取 消</el-button>
        <el-button type="primary" :loading="loading" @click="submitFunc"
          >确 定</el-button
        >
      </div>
    </template>
  </el-dialog>
</template>

<script>
import OrderApi from "@/api/order.js";
export default {
  data() {
    return {
      loading: false,
      /*左边长度*/
      formLabelWidth: "100px",
      /*是否显示*/
      dialogVisible: true,
      /*表单*/
      expressList: [],
      form: {
        expressId: null,
        isOpen: 1,
        expressNo: "",
      },
    };
  },
  props: ["open_express", "orderId"],
  created() {
    this.dialogVisible = this.open_express;

    this.getData();
  },
  methods: {
    //            获取数据
    getData() {
      let self = this;
      OrderApi.orderdetail(
        {
          orderId: this.orderId,
        },
        true
      )
        .then((res) => {
          self.loading = false;
          self.expressList = res.data.expressList;
        })
        .catch((error) => {
          self.loading = false;
        });
    },
    submitFunc(e) {
      let self = this;
      let param = self.form;
      if (param.expressId == null) {
        ElMessage.error("请选择物流公司");
        return;
      }
      if (param.expressNo == "") {
        ElMessage.error("请填写物流单号");
        return;
      }
      let orderId = this.orderId;
      OrderApi.delivery(
        {
          expressId: param.expressId,
          expressNo: param.expressNo,
          orderId: orderId,
        },
        true
      )
        .then((data) => {
          self.loading = false;
          ElMessage({
            message: "恭喜你，发货成功",
            type: "success",
          });
          self.dialogFormVisible(true);
        })
        .catch((error) => {
          self.loading = false;
        });
    },

    /*关闭弹窗*/
    dialogFormVisible() {
      this.$emit("closeDialog", {
        type: "success",
        openDialog: false,
      });
    },
  },
};
</script>

<style></style>
