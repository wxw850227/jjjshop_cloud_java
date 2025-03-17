<template>
	<view :data-theme="theme()" :class="theme() || ''" class="" v-if="!loadding">
		<block v-if="DataList.length > 0">
			<view class="item-wrap" v-for="(item, index) in DataList" :key="index">
				<view :class="'coupon-item coupon-item-' + item.colorText" @click="lookRule(item)">
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
							<view v-if="item.state.value > 0" class="f26 coupon-btn theme-btn"
								v-on:click.stop="receive(item.couponId)">领取</view>
							<button type="default" v-else class="f26 coupon-btn btn-gray white"
								v-on:click.stop>{{ item.state.text }}</button>
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
		</block>
		<block v-else>
			<view class="none-data-box">
				<image src="/static/none.png" mode="widthFix"></image>
				<text>暂无数据</text>
			</view>
		</block>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				/*是否加载完成*/
				loadding: true,
				indicatorDots: true,
				autoplay: true,
				interval: 2000,
				duration: 500,
				DataList: [],
				/*当前页面*/
				page: 1,
				/*每页条数*/
				list_rows: 10
			};
		},
		onShow() {
			uni.showLoading({
				title: '加载中'
			});
			/*获取优惠券列表*/
			this.getData();
		},
		methods: {
			/*获取数据*/
			getData() {
				let self = this;
				self._get('coupon/coupon/lists', {}, function(res) {
					self.DataList = res.data;
					console.log(self.DataList);
					self.loadding = false;
					uni.hideLoading();
				});
			},
			/*查看规则*/
			lookRule(item) {
				item.rule = true;
			},

			/*关闭规则*/
			closeRule(item) {
				item.rule = false;
			},

			/*领取优惠券*/
			receive(e) {
				let self = this;
				uni.showLoading({
					title: '领取中'
				});
				self._post(
					'user/coupon/receive', {
						couponId: e
					},
					function(res) {
						uni.hideLoading();
						self.getData();
						uni.showToast({
							title: '领取成功',
							duration: 2000,
							icon: 'success'
						});
					}
				);
				self.getData();
			}
		}
	};
</script>

<style>
</style>