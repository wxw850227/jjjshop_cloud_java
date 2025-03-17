<template>
	<div class="right-content pr">
		<!--头部-->
		<Head @refresh="refreshView"></Head>
		<!--内容区域-->
		<div class="right-content-box" id="right-content-box">
			<div class="subject-wrap">
				<router-view v-slot="{ Component }" v-if="refreshRouter">
					<keep-alive>
						<component :is="Component" />
					</keep-alive>
				</router-view>
			</div>
		</div>
	</div>
</template>

<script>
	import Head from "@/views/layout/Head.vue";
	export default {
		components: {
			Head,
		},
		data() {
			return {
				refreshRouter: true,
			};
		},
		created() {},
		methods: {
			refreshView() {
				let self = this;
				// 通过设置 key 强制刷新 router-view
				self.refreshRouter = false;
				this.$nextTick(() => {
					self.refreshRouter = true;
				})
			},
		},
	};
</script>

<style></style>