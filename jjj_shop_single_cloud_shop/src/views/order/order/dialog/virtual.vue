<template>
  <el-dialog
    title="虚拟发货"
    v-model="dialogVisible"
    @close="dialogFormVisible"
    :close-on-click-modal="false"
    :close-on-press-escape="false"
    width="30%"
  >
    <el-form
      size="small"
      ref="virtualForm"
      :model="virtualForm"
      label-width="100px"
    >
      <el-form-item label="发货信息">
        <el-input
          v-model="virtualForm.virtualContent"
          class="max-w460"
        ></el-input>
      </el-form-item>
    </el-form>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="dialogFormVisible">取 消</el-button>
        <el-button type="primary" :loading="loading" @click="submitFunc"
          >确认发货</el-button
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
      virtualForm: {
        virtualContent: "",
      },
    };
  },
  props: ["open_virtual", "orderId"],
  created() {
    this.dialogVisible = this.open_virtual;

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
      let virtualForm = self.virtualForm;
      if (virtualForm.virtualContent == "") {
        ElMessage.error("请填写发货信息");
        return;
      }
      OrderApi.virtual(
        {
          orderId: this.orderId,
          virtualContent: virtualForm.virtualContent,
        },
        true
      )
        .then((data) => {
          self.loading = false;
          ElMessage({
            message: "恭喜你，操作成功",
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
