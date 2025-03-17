<template>
	<div class="product-add">
		<div class="tips-box">
			<div class="common-form1">微信小程序使用流程</div>
		</div>
		<div class="d-b-c" style="padding: 0 32px">
			<div class="d-c d-c-c">
				<img class="icon-img" src="/src/assets/img/wx01.jpg" alt="" />
				<div>注册微信小程序应用</div>
			</div>
			<div>
				<img class="jiantou-img" src="/src/assets/img/arr.jpg" alt="" />
			</div>
			<div class="d-c d-c-c">
				<img class="icon-img" src="/src/assets/img/wx02.jpg" alt="" />
				<div>信息完善</div>
			</div>
			<div>
				<img class="jiantou-img" src="/src/assets/img/arr.jpg" alt="" />
			</div>
			<div class="d-c d-c-c">
				<img class="icon-img" src="/src/assets/img/wx03.jpg" alt="" />
				<div>开发</div>
			</div>
			<div>
				<img class="jiantou-img" src="/src/assets/img/arr.jpg" alt="" />
			</div>
			<div class="d-c d-c-c">
				<img class="icon-img" src="/src/assets/img/wx04.jpg" alt="" />
				<div>提交审核</div>
			</div>
			<div>
				<img class="jiantou-img" src="/src/assets/img/arr.jpg" alt="" />
			</div>
			<div class="d-c d-c-c">
				<img class="icon-img" src="/src/assets/img/wx05.jpg" alt="" />
				<div>发布</div>
			</div>
		</div>
		<el-form size="small" ref="form" :model="form" label-width="250px">
			<!--小程序设置-->
			<div class="tips-box mt20">
				<div class="common-form1">微信小程序设置</div>
			</div>
			<el-form-item label="AppID 小程序ID">
				<el-input v-model="form.wxappId" class="max-w460"></el-input>
			</el-form-item>
			<el-form-item label="AppSecret 小程序密钥">
				<el-input v-model="form.wxappSecret" class="max-w460"></el-input>
			</el-form-item>

			<!--提交-->
			<div class="common-button-wrapper">
				<el-button type="primary" @click="onSubmit">提交</el-button>
			</div>
		</el-form>
	</div>
</template>

<script>
	import AppSettingApi from "@/api/appsetting.js";

	export default {
		data() {
			return {
				/*切换菜单*/
				// activeIndex: '1',
				/*form表单数据*/
				form: {},
			};
		},
		created() {
			this.getData();
		},

		methods: {
			getData() {
				let self = this;
				AppSettingApi.appwxDetail({}, true)
					.then((res) => {
						if (res.data != null) {
							self.form = res.data;
						}
					})
					.catch((error) => {});
			},
			//提交表单
			onSubmit() {
				let self = this;
				let params = this.form;
				AppSettingApi.editAppWx(params, true)
					.then((data) => {
						ElMessage({
							message: "恭喜你，设置成功",
							type: "success",
						});
						self.$router.push("/appsetting/appwx/index");
					})
					.catch((error) => {});
			},
		},
	};
</script>

<style lang="scss" scoped>
	.tips {
		color: #ccc;
	}

	.common-form1 {
		position: relative;
		padding-left: 12px;
		font-size: 14px;
		font-weight: 700;
	}

	.common-form1 span {
		font-size: inherit;
	}

	.common-form1:before {
		position: absolute;
		content: "";
		width: 4px;
		height: 14px;
		left: 0;
		top: 50%;
		transform: translateY(-50%);
	}

	.common-form1:before {
		background: var(--el-color-primary);
	}
</style>