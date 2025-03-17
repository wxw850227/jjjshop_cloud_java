<template>
	<view :data-theme="theme()" :class="theme() || ''">
		<view class="top-tabbar">
			<view :class="state_active == 0 ? 'tab-item active' : 'tab-item'" @click="stateFunc(0)">未使用</view>
			<view :class="state_active == 1 ? 'tab-item active' : 'tab-item'" @click="stateFunc(1)">已使用</view>
			<view :class="state_active == 2 ? 'tab-item active' : 'tab-item'" @click="stateFunc(2)">已过期</view>
		</view>

		<!--列表-->
		<scroll-view scroll-y="true" class="scroll-Y" :style="'height:' + scrollviewHigh + 'px;'" lower-threshold="50"
			@scrolltoupper="scrolltoupperFunc" @scrolltolower="scrolltolowerFunc">
			<view class="">
				<view class="item-wrap mb20" v-for="(item, index) in listData" :key="index">
					<view
						:class="item.isExpire == 0 && item.isUse == 0 ? 'coupon-item coupon-item-' + item.colorText : 'coupon-item coupon-item-gray'">
						<image v-if="item.isUse == 1" class="coupon-disabled" src="/static/coupon-status1.png" mode="">
						</image>
						<image v-if="item.isExpire == 1" class="coupon-disabled" src="/static/coupon-status2.png"
							mode=""></image>
						<view class="operation d-b-c">
							<view class="flex-1 coupon-content">
								<view class="mb20 text-ellipsis">
									<text class="f40 fb">{{ item.name }}</text>
								</view>
								<view class="f22 gray9 mb20">
									<template v-if="item.expireType == 10">
										有效期：领取{{ item.expireDay }}天内有效
									</template>
									<template v-if="item.expireType == 20">
										有效期：{{ item.startTimeText }}至{{ item.endTimeText }}
									</template>
								</view>
								<view v-if="item.bigPrice != 0" class="f22">(最大优惠{{ item.bigPrice }}元)</view>
							</view>
							<view class="right-box d-c-c d-c">
								<view class="theme-price mb10" v-if="item.couponType == 10">
									<text class="f24">￥</text>
									<text class="f52 fb">{{ item.reducePrice * 1 }}</text>
								</view>
								<view class="mb10 theme-price" v-if="item.couponType == 20">
									<text class="f52 fb">{{ item.discount / 10 }}</text>
									<text class="f24">折</text>
								</view>
								<view class="f24 mb10">{{ item.minPrice > 0 ? '满' + item.minPrice * 1 + '元可用' : '无门槛' }}
								</view>
								<template v-if="item.isExpire == 0 && item.isUse == 0">
									<view v-if="item.applyRange != 10" class="f26 coupon-btn theme-btn"
										@click.stop="gotoPage('/pages/coupon/detail?couponId=' + item.couponId + '&applyRange=' + item.applyRange)">
										去使用
									</view>
									<view v-else class="f26 coupon-btn theme-btn"
										@click.stop="gotoPage('/pages/index/index')">去使用</view>
								</template>
							</view>
						</view>
					</view>
					<view class="range_item d-b-c" v-if="item.applyRange == 20"
						@click.stop="gotoPage('/pages/coupon/detail?couponId=' + item.couponId + '&applyRange=' + item.applyRange)">
						<view class="gray9 f24">
							限指定部分商品
							<text class="icon iconfont icon-jiantou" style="color: #999999; font-size: 22rpx;"></text>
						</view>
						<view class="gray9 f24">本券不支持转赠</view>
					</view>
					<view class="range_item d-b-c" v-else-if="item.applyRange == 30"
						@click.stop="gotoPage('/pages/coupon/detail?couponId=' + item.couponId + '&applyRange=' + item.applyRange)">
						<view class="gray9 f24">
							限指定分类商品
							<text class="icon iconfont icon-jiantou" style="color: #999999; font-size: 22rpx;"></text>
						</view>
						<view class="gray9 f24">本券不支持转赠</view>
					</view>
					<view class="range_item d-b-c" v-else>
						<view class="gray9 f24">
							全场通用
							<text class="icon iconfont icon-jiantou" style="color: #999999; font-size: 22rpx;"></text>
						</view>
						<view class="gray9 f24">本券不支持转赠</view>
					</view>
				</view>
			</view>
			<view class="d-c-c p30" v-if="listData.length == 0 && !loading">
				<text class="iconfont icon-wushuju"></text>
				<text class="cont">亲，暂无相关记录哦</text>
			</view>
			<uni-load-more v-else :loadingType="loadingType"></uni-load-more>
		</scroll-view>
	</view>
</template>

<script>
	import uniLoadMore from '@/components/uni-load-more.vue';
	export default {
		components: {
			uniLoadMore
		},
		data() {
			return {
				/*手机高度*/
				phoneHeight: 0,
				/*可滚动视图区域高度*/
				scrollviewHigh: 0,
				/*状态选中*/
				state_active: 0,
				/*列表*/
				/*当前第几页*/
				page: 1,
				/*一页多少条*/
				last_page: 0,
				list_rows: 15,
				listData: [],
				no_more: false,
				loading: false,
				data_type: 'not_use'
			};
		},
		mounted() {
			/*初始化*/
			this.init();
		},
		onShow() {
			this.initData();
			/*获取数据*/
			this.getData();
		},
		computed: {
			/*加载中状态*/
			loadingType() {
				if (this.loading) {
					return 1;
				} else {
					if (this.listData.length != 0 && this.no_more) {
						return 2;
					} else {
						return 0;
					}
				}
			}
		},
		methods: {
			/*初始化*/
			init() {
				let self = this;
				uni.getSystemInfo({
					success(res) {
						self.phoneHeight = res.windowHeight;
						// 计算组件的高度
						let view = uni.createSelectorQuery().select('.top-tabbar');
						view.boundingClientRect(data => {
							let h = self.phoneHeight - data.height;
							self.scrollviewHigh = h;
						}).exec();
					}
				});
			},
			initData() {
				this.listData = [];
				this.page = 1;
				this.no_more = false;
			},
			/*获取数据*/
			getData() {
				let self = this;
				self.loading = true;
				let data_type = self.data_type;
				self._postBody(
					'user/coupon/lists', {
						pageIndex: self.page,
						pageSize: self.list_rows,
						dataType: data_type
					},
					function(res) {
						self.loading = false;
						self.listData = self.listData.concat(res.data.records);
						self.last_page = res.data.lastPage;
						if (res.data.lastPage <= 1) {
							self.no_more = true;
							return false;
						}
					}
				);
			},

			/*可滚动视图区域到底触发*/
			scrolltolowerFunc() {
				let self = this;
				if (self.no_more) {
					return;
				}
				self.page++;
				if (self.page <= self.last_page) {
					self.getData();
				} else {
					self.no_more = true;
				}
			},
			/*状态切换*/
			stateFunc(e) {
				let self = this;
				if (self.state_active != e) {
					if (e == 0) {
						self.data_type = 'not_use';
					}
					if (e == 1) {
						self.data_type = 'is_use';
					}
					if (e == 2) {
						self.data_type = 'is_expire';
					}
					self.state_active = e;
					self.initData();
					self.getData();
				}
			},

			/*可滚动视图区域到顶触发*/
			scrolltoupperFunc() {
				console.log('滚动视图区域到顶');
			},
			/*查看规则*/
			lookRule(item) {
				item.rule = true;
			},

			/*关闭规则*/
			closeRule(item) {
				item.rule = false;
			}
		}
	};
</script>

<style>

</style>