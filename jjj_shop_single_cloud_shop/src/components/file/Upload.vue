<template>
  <div class="Modal">
    <el-dialog
      title="素材管理"
      v-model="dialogVisible"
      :close-on-click-modal="false"
      :close-on-press-escape="false"
      @close="cancelFunc"
      :width="dialogWidth"
      :append-to-body="true"
    >
      <div class="colLeft">
        <div class="Nav">
          <div class="trees-coadd">
            <div class="scollhide">
              <div>
                <el-input
                  class="ml20 mb10"
                  v-model="fileData.real_name"
                  placeholder="请输入图片名"
                  size="small"
                  @blur="getFileCategory"
                  style="width: 180px"
                >
                  <template #suffix>
                    <el-icon size="14"><Search /></el-icon>
                  </template>
                </el-input>
              </div>
              <div class="isTree">
                <el-tree
                  :data="typeList"
                  node-key="groupId"
                  default-expand-all
                  highlight-current
                  :expand-on-click-node="false"
                  @node-click="selectTypeFunc"
                  :current-node-key="activeType"
                >
                  <template #default="{ node, data }">
                    <span class="custom-tree-node">
                      <span class="file-name">
                        <img class="icon" src="@/assets/img/file.jpg" />
                        <span class="name line1">{{ data.groupName }}</span>
                      </span>
                      <span>
                        <el-dropdown
                          @command="(command) => clickMenu(data, command)"
                        >
                          <el-icon><MoreFilled /></el-icon>
                          <template #dropdown>
                            <el-dropdown-menu>
                              <el-dropdown-item command="1"
                                >新增分类</el-dropdown-item
                              >
                              <el-dropdown-item v-if="data.groupId" command="2"
                                >编辑分类</el-dropdown-item
                              >
                              <el-dropdown-item v-if="data.groupId" command="3"
                                >删除</el-dropdown-item
                              >
                            </el-dropdown-menu>
                          </template>
                        </el-dropdown>
                      </span>
                      <!-- <div class="operation" @click.stop v-if="data.groupId != null">
                  <p @click="editCategoryFunc(data)">
                    <el-icon><Edit /></el-icon>
                  </p>
                  <p @click="deleteCategoryFunc(data.groupId)">
                    <el-icon><Delete /></el-icon>
                  </p>
                </div> -->
                    </span>
                  </template>
                </el-tree>
              </div>
            </div>
          </div>
        </div>
        <div class="conter">
          <div class="bnt d-e-c d-r">
            <div class="mr10 flex-1">
              <!-- <el-button size="small" icon="Plus" @click="addCategory"
                >添加分类</el-button
              > -->
              <el-button
                size="small"
                type="primary"
                icon="Delete"
                @click="deleteFileFunc(false)"
                >批量删除</el-button
              >
              <el-popover
                placement="bottom"
                width="200"
                trigger="click"
                :value="true"
              >
                <ul class="move-type">
                  <li
                    v-for="(item, index) in typeList"
                    :key="index"
                    @click="moveTypeFunc(item.groupId)"
                  >
                    {{ item.groupName }}
                  </li>
                </ul>
                <template #reference>
                  <el-button size="small" icon="CaretBottom">移动至</el-button>
                </template>
              </el-popover>
            </div>
            <div class="">
              <el-upload
                class=""
                multiple
                ref="upload"
                action="string"
                :accept="accept"
                :before-upload="onBeforeUploadImage"
                :http-request="UploadImage"
                :show-file-list="false"
                :on-change="fileChange"
              >
                <el-button size="small" icon="Upload" type="primary"
                  >点击上传</el-button
                >
              </el-upload>
            </div>
          </div>
          <div class="pictrueList is-modal">
            <div
              class="image-item"
              v-for="(item, index) in fileList"
              :key="index"
              @click="selectFileFunc(item, index)"
            >
              <div v-if="item.selected" class="number">
                <el-badge :value="item.num" type="primary">
                  <a href="#" class="demo-badge"></a>
                </el-badge>
              </div>
              <el-image
                v-if="this_config.fileType === 'image'"
                :src="item.filePath"
                class="img"
                :style="{
                  border: item.selected ? '1px solid #2288FC' : '',
                }"
                fit="cover"
                :preview="false"
              ></el-image>
              <video
                v-if="this_config.fileType === 'video'"
                height="100"
                width="100"
                :src="item.filePath"
                :autoplay="false"
              >
                您的浏览器不支持 video 标签
              </video>
              <div
                class="operate-item"
                @mouseenter="enterLeave(item)"
                @mouseleave="enterLeave(item)"
              >
                <p class="desc">{{ item.realName }}</p>
                <div class="operate-height">
                  <span
                    class="operate mr10"
                    @click="deleteFileFunc(item)"
                    v-if="item.isShowEdit"
                    >删除</span
                  >
                </div>
              </div>
            </div>
          </div>
          <div class="footer row-right">
            <el-pagination
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              :current-page="curPage"
              :page-sizes="[12, 24, 36, 42, 48]"
              :page-size="pageSize"
              layout="total, sizes, prev, pager, next, jumper"
              :total="totalDataNumber"
            ></el-pagination>
          </div>
        </div>

        <!--我的资源库-->
      </div>

      <!--分页-->

      <template #footer>
        <div class="dialog-footer">
          <el-button size="small" @click="cancelFunc">取 消</el-button>
          <el-button size="small" type="primary" @click="confirmFunc"
            >确 定</el-button
          >
        </div>
      </template>
    </el-dialog>
    <!--图片类别-->
    <AddCategory
      v-if="isShowCategory"
      :category="category"
      :fileType="this_config.fileType"
      @closeCategory="closeCategoryFunc"
    ></AddCategory>
  </div>
</template>

<script>
import FileApi from "@/api/file.js";
import AddCategory from "./part/AddCategory.vue";
export default {
  components: {
    AddCategory,
  },
  data() {
    return {
      /*宽度*/
      dialogWidth: "910px",
      /*类别*/
      activeType: null,
      /*图片类别*/
      typeList: [],
      /*图标路径*/
      imageUrl: null,
      /*弹窗显示*/
      dialogVisible: true,
      /*文件列表*/
      fileList: [],
      /*分类添加loading*/
      addLoading: false,
      /*默认值*/
      this_config: {
        /*上传个数*/
        total: 1,
        fileType: "image",
      },
      /*记录选中的个数*/
      record: 0,
      /*是否加载完成*/
      loading: true,
      /*列表数据*/
      tableData: [],
      /*一页多少条*/
      pageSize: 36,
      /*一共多少条数据*/
      totalDataNumber: 0,
      /*当前是第几页*/
      curPage: 1,
      /*是否显示图片类别编辑框*/
      isShowCategory: false,
      /*当前类别model*/
      category: null,
      /*选中图片*/
      fileIds: [],
      accept: "",
      selectedOrder: [],
      isPage: false,
      fileData: {
        real_name: "",
      },
    };
  },
  props: ["config"],
  created() {
    /*覆盖默认值*/
    if (
      Object.prototype.toString.call(this.config).toLowerCase() ===
      "[object object]"
    ) {
      for (let key in this.config) {
        if (this.config[key]) {
          this.this_config[key] = this.config[key];
        }
      }
      if (this.this_config["fileType"] == "image") {
        this.accept = "image/jpeg,image/png,image/jpg";
      } else {
        this.accept = "video/mp4";
      }
    }

    this.getFileCategory();
    this.getData();
  },
  methods: {
    clickMenu(data, name) {
      if (name == 1) {
        this.addCategory();
      } else if (name == 2) {
        this.editCategoryFunc(data);
      } else if (name == 3) {
        this.deleteCategoryFunc(data.groupId);
      }
    },
    enterLeave(item) {
      item.isShowEdit = !item.isShowEdit;
    },
    /*获取图片类别*/
    getFileCategory() {
      let self = this;
      FileApi.fileCategory(
        {
          type: self.this_config.fileType,
        },
        true
      )
        .then((res) => {
          let type = [
            {
              groupId: null,
              groupName: "全部",
            },
          ];
          self.typeList = type.concat(res.data);
        })
        .catch((error) => {
          self.loading = false;
        });
    },

    /*添加图片类别*/
    addCategory() {
      this.isShowCategory = true;
      this.category = null;
    },

    /*修改类别*/
    editCategoryFunc(item) {
      this.isShowCategory = true;
      this.category = item;
    },

    /*关闭类别层*/
    closeCategoryFunc(e) {
      if (e != null) {
        this.getFileCategory();
      }
      this.isShowCategory = false;
    },

    /*删除类别提示*/
    deleteCategoryFunc(e) {
      ElMessageBox.confirm("此操作将永久删除该记录, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          this.deleteCategory(e);
        })
        .catch(() => {
          ElMessage({
            type: "info",
            message: "已取消删除",
          });
        });
    },

    /*删除类别*/
    deleteCategory(e) {
      let self = this;
      FileApi.deleteCategory({
        groupId: e,
      })
        .then((data) => {
          ElMessage({
            message: "删除成功",
            type: "success",
          });
          self.activeType = null;
          self.getFileCategory();
          self.getData();
        })
        .catch((error) => {
          ElMessage.error("删除失败");
        });
    },

    /*选择类别*/
    selectTypeFunc(row) {
      this.activeType = row.groupId;
      this.curPage = 1;
      this.getData();
    },

    /*选择第几页*/
    handleCurrentChange(val) {
      this.curPage = val;
      this.getData();
    },

    /*每页多少条*/
    handleSizeChange(val) {
      this.curPage = 1;
      this.pageSize = val;
      this.getData();
    },

    /*获取图片列表数据*/
    getData: function () {
      let self = this;
      let param = {
        pageIndex: self.curPage,
        pageSize: self.pageSize,
        groupId: self.activeType,
        fileType: self.this_config.fileType,
      };
      FileApi.fileList(param, true)
        .then((res) => {
          self.loading = false;
          res.data.records.forEach((el) => {
            el.isSelect = false;
            el.isEdit = false;
            el.isShowEdit = false;
            // el.realName = false;
            el.num = 0;
            // this.editName(el);
          });
          self.fileList = res.data.records;
          self.totalDataNumber = res.data.total;
        })
        .catch((error) => {
          self.loading = false;
        });
    },
    // editName(item) {
    //   let it = item.realName.split(".");
    //   let it1 = it[1] == undefined ? [] : it[1];
    //   let len = it[0].length + it1.length;
    //   item.editName =
    //     len < 10
    //       ? item.realName
    //       : item.realName.substr(0, 4) + "..." + item.real_name.substr(-5, 5);
    // },
    /*图片移动到某个类别*/
    moveTypeFunc(e) {
      console.log(e);
      let self = this;
      let temp_list = [];
      self.fileList.forEach((item) => {
        if (item.selected) {
          temp_list.push(item.fileId);
        }
      });
      if (temp_list.length == 0) {
        ElMessage({
          message: "请选择文件",
          type: "warning",
        });
        return;
      }
      ElMessageBox.confirm("确定移动选中的文件吗？, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          FileApi.moveFile(
            {
              groupId: e,
              fileIds: temp_list.join(","),
            },
            true
          )
            .then((data) => {
              ElMessage({
                message: "移动成功",
                type: "success",
              });
              self.getFileCategory();
              self.getData();
            })
            .catch((error) => {});
        })
        .catch(() => {});
    },

    /*选择上传图片*/
    fileChange(e) {
      console.log(e);
    },

    /*选择图片之前*/
    onBeforeUploadImage(file) {
      return true;
    },

    /*上传图片*/
    UploadImage(param) {
      let self = this;
      const loading = ElLoading.service({
        lock: true,
        text: "文件上传中,请等待",
        spinner: "el-icon-loading",
        background: "rgba(0, 0, 0, 0.7)",
      });
      const formData = new FormData();
      formData.append("iFile", param.file);
      formData.append("groupId", self.activeType == null ? 0 : self.activeType);
      formData.append("fileType", self.this_config.fileType);
      FileApi.uploadFile(formData)
        .then((response) => {
          loading.close();
          self.getData();
          param.onSuccess(); // 上传成功的图片会显示绿色的对勾
          // 但是我们上传成功了图片， fileList 里面的值却没有改变，还好有on-change指令可以使用
        })
        .catch((response) => {
          loading.close();
          ElMessage({
            message: "本次上传文件失败",
            type: "warning",
          });
          param.onError();
        });
    },

    /*选择图片*/
    selectFileFunc(item, index) {
      if (!item.selected) {
        // 如果未选中，则标记为选中，并将该图片的 fileId 加入顺序数组
        item.selected = true;
        this.selectedOrder.push(item.fileId);
        // 编号就是数组的长度（即最新的下标+1）
        item.num = this.selectedOrder.length;
      } else {
        // 如果已经选中，则取消选中，并将对应的 fileId 从顺序数组中移除
        item.selected = false;
        const idx = this.selectedOrder.indexOf(item.fileId);
        if (idx !== -1) {
          this.selectedOrder.splice(idx, 1);
        }
        item.num = 0;
        // 对于剩余的选中图片，根据 selectedOrder 数组重新分配编号
        this.fileList.forEach((file) => {
          if (file.selected) {
            const pos = this.selectedOrder.indexOf(file.fileId);
            file.num = pos + 1;
          }
        });
      }
      // this.fileList[index] = item;
    },

    /*删除图片*/
    deleteFileFunc(e) {
      let self = this;
      let loading = null;
      ElMessageBox.confirm("此操作将永久删除该记录, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          loading = ElLoading.service({
            lock: true,
            text: "正在删除",
            spinner: "el-icon-loading",
            background: "rgba(0, 0, 0, 0.7)",
          });
          let temp_list = [];
          if (e) {
            temp_list.push(e.fileId);
          } else {
            for (let i = 0; i < self.fileList.length; i++) {
              let item = self.fileList[i];
              if (item.selected) {
                temp_list.push(item.fileId);
              }
            }
          }
          if (temp_list.length == 0) {
            loading.close();
            ElMessage({
              message: "请选择文件",
              type: "warning",
            });
            return;
          }
          FileApi.deleteFiles(
            {
              fileIds: temp_list.join(","),
            },
            true
          )
            .then((data) => {
              loading.close();
              ElMessage({
                message: "删除成功",
                type: "success",
              });
              self.getData();
            })
            .catch((error) => {
              loading && loading.close();
            });
        })
        .catch(() => {
          loading && loading.close();
          ElMessage({
            message: "删除失败",
            type: "warning",
          });
        });
    },

    /*选择图片确认*/
    confirmFunc() {
      let list = [];
      let leng = 0;
      for (let i = 0; i < this.fileList.length; i++) {
        let item = this.fileList[i];
        if (item.selected) {
          let obj = {
            fileId: item.fileId,
            filePath: item.filePath,
          };
          list.push(obj);
          leng++;
        }
        if (leng > this.this_config.total) {
          break;
        }
      }
      this.$emit("returnImgs", list);
    },

    /*取消*/
    cancelFunc() {
      this.$emit("returnImgs", null);
    },
  },
};
</script>

<style scoped lang="scss">
.move-type li {
  padding: 8px 12px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.move-type li:hover {
  background-color: #e6f7ff;
}
.nameStyle {
  position: absolute;
  white-space: nowrap;
  z-index: 9;
  background: #eee;
  height: 20px;
  line-height: 20px;
  color: #555;
  border: 1px solid #ebebeb;
  padding: 0 5px;
  left: 56px;
  bottom: -18px;
}

.iconbianji1 {
  font-size: 13px;
}

.selectTreeClass {
  background: #d5e8fc;
}
.tree_tit {
  padding-top: 7px;
}
.treeBox {
  width: 100%;
  height: 100%;
  max-width: 180px;
}
.is-modal .pictrueList_pic {
  width: 100px;
  margin: 10px 5px !important;
  .img {
    display: flex;
    align-items: center;
    justify-content: center;
    width: 100px;
    height: 100px;
    background-color: rgb(248, 248, 248);
    padding: 2px;
    img {
      max-width: 96px;
      max-height: 96px;
      // object-fit: cover;
    }
    .operate-height {
      bottom: -8px;
    }
  }
}
.Nav {
  width: 100%;
  border-right: 1px solid #eee;
  min-width: 200px;
  max-width: max-content;
}
.trees-coadd {
  width: 100%;
  border-radius: 4px;
  overflow: hidden;
  position: relative;

  .scollhide {
    overflow-x: hidden;
    overflow-y: scroll;
    padding: 0px 0 10px 0;
    box-sizing: border-box;

    .isTree {
      min-height: 374px;
      max-height: 550px;
      ::v-deep .file-name {
        display: flex;
        align-items: center;
        .name {
          white-space: nowrap;
          overflow: hidden;
          text-overflow: ellipsis;
          max-width: 7em;
        }
        .icon {
          width: 12px;
          height: 12px;
          margin-right: 8px;
        }
      }
      ::v-deep .el-tree-node {
        margin-right: 16px;
      }
      ::v-deep .el-tree-node__children .el-tree-node {
        margin-right: 0;
      }
      ::v-deep .el-tree-node__content {
        width: 100%;
        height: 36px;
      }
      ::v-deep .custom-tree-node {
        flex: 1;
        display: flex;
        align-items: center;
        justify-content: space-between;
        padding-right: 20px;
        font-size: 13px;
        font-weight: 400;
        color: rgba(0, 0, 0, 0.6);
        line-height: 13px;
      }
      ::v-deep .is-current {
        background: #f1f9ff !important;
        color: var(--el-color-primary) !important;
      }
      ::v-deep .is-current .custom-tree-node {
        color: var(--el-color-primary) !important;
      }
    }
  }

  .scollhide::-webkit-scrollbar {
    display: none;
  }
}

.treeSel ::v-deep .ivu-select-dropdown-list {
  padding: 0 5px !important;
  box-sizing: border-box;
  width: 200px;
}
.imagesNo {
  display: flex;
  justify-content: center;
  flex-direction: column;
  align-items: center;
  margin: 65px 0;

  .imagesNo_sp {
    font-size: 13px;
    color: #dbdbdb;
    line-height: 3;
  }
}

.Modal {
  width: 100%;
  height: 100%;
  background: #fff !important;
}
.fill-window {
  height: 100vh;
}
.colLeft {
  padding-right: 0 !important;
  height: 100%;
  display: flex;
  flex-wrap: nowrap;
}

.conter {
  width: 100%;
  height: 100%;
  margin-left: 20px !important;
  .iconliebiao {
    font-size: 12px;
  }
}

.conter .bnt {
  width: 100%;
  padding: 0 13px 20px 0px;
  box-sizing: border-box;
}

.pictrueList {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  min-height: 380px;
}

.image-item {
  width: calc(16.66% - 10px);
  margin-bottom: 10px;
  position: relative;
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-left: 10px;
}

.img {
  width: 100px;
  height: 100px;
  background-color: rgb(248, 248, 248);
  padding: 2px;
  display: flex;
  justify-content: center;
  align-items: center;
}

.operate-item {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  margin-top: 5px;
}

.desc {
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis; /* Truncate the text */
  width: 80px; /* Adjust width to fit your layout */
  text-align: center;
}

.operate-height {
  display: flex;
  justify-content: center;
  margin-top: 5px;
}

.number {
  position: absolute;
  top: 15px;
  right: 15px;
  z-index: 10;
}

.demo-badge {
  width: 30px;
  height: 30px;
  background: transparent;
  border-radius: 6px;
  display: inline-block;
}

.operate {
  color: var(--el-color-primary);
  font-size: 12px;
  cursor: pointer;
}

.footer {
  padding: 0 20px 10px 20px;
}

.conter .footer {
  padding: 0 20px 10px 20px;
}
.tabBox_img {
  display: flex;
  align-items: center;
}
.real-name {
  flex: 1;
}
.df-aic {
  display: flex;
  align-items: center;
}

.bnt ::v-deep .ivu-tree-children {
  padding: 5px 0;
}

.card-tree {
  background: #fff;
  height: 72px;
  box-sizing: border-box;
  overflow-x: scroll; /* 设置溢出滚动 */
  white-space: nowrap;
  overflow-y: hidden;
  /* 隐藏滚动条 */
  border-radius: 4px;
  scrollbar-width: none; /* firefox */
  -ms-overflow-style: none; /* IE 10+ */
}
.card-tree::-webkit-scrollbar {
  display: none; /* Chrome Safari */
}
.tabs {
  background: #fff;
  padding-top: 10px;
  border-radius: 5px 5px 0 0;
}
</style>
