<template>
	<div class="product-add">
		<!--form表单-->
		<el-form size="small" ref="form" :model="form" label-width="150px">
			<!--添加门店-->
			<div class="common-form">商城设置</div>
			<el-form-item label="商城名称" :rules="[{ required: true, message: ' ' }]" prop="name">
				<el-input v-model="form.name" placeholder="商城名称" class="max-w460"></el-input>
			</el-form-item>
			<el-form-item label="配送方式">
				<el-checkbox-group v-model="form.deliveryType">
					<el-checkbox v-for="(item, index) in all_type" :label="item.value"
						:key="index">{{ item.name }}</el-checkbox>
				</el-checkbox-group>
				<div class="tips">注：配送方式至少选择一个</div>
			</el-form-item>
			<div class="common-form">日志记录</div>
			<el-form-item label="是否记录查询日志" prop="customer">
				<el-checkbox v-model="form.isGetLog">是</el-checkbox>
				<div class="tips">如果记录，日志量会有点大</div>
			</el-form-item>
			<div class="common-form">物流查询api</div>
			<el-form-item label="快递100 Customer" :rules="[{ required: true, message: ' ' }]" prop="kuaiDi100.customer">
				<el-input v-model="form.kuaiDi100.customer" placeholder="" class="max-w460"></el-input>
				<div class="tips">
					用于查询物流信息,<el-link :underline="false" href="https://www.kuaidi100.com/openapi/" target="_blank"
						type="primary">快递100申请</el-link>
				</div>
			</el-form-item>
			<el-form-item label="快递100 Key" :rules="[{ required: true, message: ' ' }]" prop="kuaiDi100.key">
				<el-input v-model="form.kuaiDi100.key" placeholder="" class="max-w460"></el-input>
			</el-form-item>
			<div class="common-form">政策协议</div>
			<el-tabs v-model="activeTab" type="card">
				<el-tab-pane label="服务协议" name="service"></el-tab-pane>
				<el-tab-pane label="隐私政策" name="privacy"></el-tab-pane>
			</el-tabs>
			<el-form-item label=" " :rules="[{ required: true, message: ' ' }]" prop="policy.service"
				v-if="activeTab == 'service'">
				<div class="edit_container">
					<WangEditor :content="form.policy.service" @contens="contentm" @editorContent="getEditorContent">
					</WangEditor>
				</div>
				<div class="tips">
					进入app和登录、注册页面显示
				</div>
			</el-form-item>
			<el-form-item label=" " :rules="[{ required: true, message: ' ' }]" prop="policy.privacy"
				v-if="activeTab == 'privacy'">
				<div class="edit_container">
					<WangEditor :content="form.policy.privacy" @contens="contentm" @editorContent="getEditorContent">
					</WangEditor>
				</div>
			</el-form-item>
			<!--提交-->
			<div class="common-button-wrapper">
				<el-button type="primary" @click="onSubmit" :loading="loading">提交</el-button>
			</div>
		</el-form>
		<!--上传图片-->
		<Upload v-if="isupload" :isupload="isupload" :config="{ total: 3 }" @returnImgs="returnImgsFunc"></Upload>
	</div>
</template>

<script>
	import SettingApi from "@/api/setting.js";
	import Upload from "@/components/file/Upload.vue";
	import {
		formatModel
	} from "@/utils/base.js";
	export default {
		components: {
			Upload,
		},
		data() {
			return {
				/*是否正在加载*/
				loading: false,
				/*form表单数据*/
				form: {
					name: "",
					kuaiDi100: {
						customer: "",
						key: "",
					},
					deliveryType: [],
					isGetLog: 0,
					policy: {
						service: "",
						privacy: "",
					},
				},
				all_type: [],
				delivery_type: [],
				/*是否打开图片选择*/
				isupload: false,
				activeTab: "service",
			};
		},
		created() {
			this.getParams();
		},

		methods: {
			contentm(e) {
				if (this.activeTab == "service") {
					this.form.policy.service = e;
				} else {
					this.form.policy.privacy = e;
				}
			},
			getEditorContent(data) {
				if (this.activeTab == "service") {
					this.form.policy.service = data;
				} else {
					this.form.policy.privacy = data;
				}
			},
			/*获取配置数据*/
			getParams() {
				let self = this;
				SettingApi.storeDetail({}, true)
					.then((res) => {
						self.form = formatModel(self.form, res.data);
						self.all_type = res.data.allType;
						self.loading = false;
					})
					.catch((error) => {
						console.log(error);
					});
			},

			/*提交*/
			onSubmit() {
				let self = this;
				let params = this.form;
				if (params.deliveryType.length < 1) {
					ElMessage({
						message: "配送方式至少选择一种！",
						type: "warning",
					});
					return;
				}

				self.$refs.form.validate((valid) => {
					if (valid) {
						self.loading = true;
						SettingApi.editStore(params, true)
							.then((data) => {
								self.loading = false;
								ElMessage({
									message: "恭喜你，商城设置成功",
									type: "success",
								});
								self.$router.push("/setting/store/index");
								// location.reload();
							})
							.catch((error) => {
								self.loading = false;
							});
					}
				});
			},
		},
	};
</script>
<style>

</style>