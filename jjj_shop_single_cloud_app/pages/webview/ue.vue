<template>
  <view>
    <view v-html="content"></view>
  </view>
</template>

<script>
export default {
  data() {
    return {
      type: "",
      content: "",
    };
  },
  onLoad(e) {
    this.type = e.type;
    let title = "";
    if (this.type == "service") {
      title = "用户协议";
    } else {
      title = "隐私协议";
    }
    uni.setNavigationBarTitle({
      title: title,
    });
    this.getData();
  },
  methods: {
    getData() {
      let self = this;
      self._get("user/userApple/policy", {}, function (res) {
        if (self.type == "service") {
          self.content = res.data.service;
        } else {
          self.content = res.data.privacy;
        }
      });
    },
  },
};
</script>

<style></style>
