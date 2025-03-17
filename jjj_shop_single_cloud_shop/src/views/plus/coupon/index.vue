<template>
	<div class="user">
		<div class="common-seach-wrap">
			<el-tabs v-model="activeName">
				<el-tab-pane label="优惠劵列表" name="list">
					<List v-if="activeName == 'list'"></List>
				</el-tab-pane>
				<el-tab-pane label="领取记录" name="receive">
					<Receive v-if="activeName == 'receive'"></Receive>
				</el-tab-pane>
				<el-tab-pane label="发送优惠券" name="send">
					<SendCoupon v-if="activeName == 'send'"></SendCoupon>
				</el-tab-pane>
			</el-tabs>
		</div>
	</div>
</template>
<script>
	import List from "./coupon/index.vue";
	import Receive from "./coupon/Receive.vue";
	import SendCoupon from "./coupon/SendCoupon.vue";
	export default {
		components: {
			List,
			Receive,
			SendCoupon,
		},
		data() {
			return {
				activeName: 'list',
				/*是否加载完成*/
				loading: true,
			}
		},
		created() {


		},
		methods: {}
	}
</script>

<style></style>