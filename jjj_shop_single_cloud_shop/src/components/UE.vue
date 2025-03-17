<template>
  <vue-ueditor-wrap
    v-model="msg"
    :config="this_config"
    editor-id="editor-demo-01"
    @ready="ready"
  ></vue-ueditor-wrap>
  <Upload
    v-if="isupload"
    :config="{ total: 9 }"
    :isupload="isupload"
    @returnImgs="returnImgsFunc"
    >上传图片</Upload
  >
</template>
<script>
import { reactive, toRefs, ref, watch } from "vue";
import Upload from "@/components/file/Upload.vue";
export default {
  components: {
    Upload,
  },
  props: ["text"],
  setup(props, { emit }) {
    const state = reactive({
      msg: props.text,
      editor: null,
      isupload: false,
      hasCallback: false,
      callback: null,
      this_config: {
        //不需要工具栏漂浮
        autoFloatEnabled: false,
      },
    });
    watch(
      () => state.msg,
      (v) => {
        emit("contentChange", v);
      }
    );
    return {
      ...toRefs(state),
    };
  },
  methods: {
    ready() {
      window.openUpload = this.openUpload;
    },
    /*打开选择图片*/
    openUpload: function (callback) {
      this.isupload = true;
      if (callback) {
        this.hasCallback = true;
        this.callback = callback;
      }
    },

    /*获取图片*/
    returnImgsFunc(e) {
      if (e != null) {
        this.hasCallback && this.callback(e);
      }
      this.isupload = false;
    },
  },
};
</script>
