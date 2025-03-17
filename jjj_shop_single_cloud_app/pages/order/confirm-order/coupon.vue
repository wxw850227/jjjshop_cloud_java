<template>
	<view :class="Visible ? 'usable-coupon open' : 'usable-coupon close'">
		<view class="popup-bg" @click="closePopup(null)"></view>
		<view class="main pt30" v-on:click.stop :data-theme="theme()" :class="theme() || ''">
			<scroll-view scroll-y="true" class="scroll-Y" :style="'height:' + scrollviewHigh + 'px;'">
				<view class="p-0-30">
					<view @click="selectCoupon(item.userCouponId)" style="margin-bottom: 15rpx;" class="item-wrap"
						v-for="(item, index) in datalist" :key="index">
						<view :class="'coupon-item coupon-item-' + item.colorText">
							<view class="operation d-b-c pr">
								<view class="flex-1 coupon-content">
									<view class="mb20">
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
									<view v-if="item.bigPrice > 0" class=" f22">
										{{ item.bigPrice > 0 ? '最多抵扣' + item.bigPrice * 1 + '元' : '无最高抵扣限制' }}
									</view>
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
									<view class="f24 mb10">
										{{ item.minPrice > 0 ? '满' + item.minPrice * 1 + '元可用' : '无门槛' }}
									</view>
									<view class="f26 coupon-btn theme-btn">立即使用</view>
								</view>
							</view>
						</view>
					</view>
				</view>
			</scroll-view>
			<view class="coupon-btns"><button type="default" @click="closePopup(0)" class="btn-cancel">不使用优惠券</button>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				/*手机高度*/
				phoneHeight: 0,
				/*可滚动视图区域高度*/
				scrollviewHigh: 0,
				/*是否可见*/
				Visible: false,
				/*优惠券列表*/
				datalist: {},
				/*尺寸比例*/
				ratio: 1
			};
		},
		props: ['isCoupon', 'couponList'],

		onLoad() {},
		mounted() {
			this.init();
		},
		watch: {
			isCoupon: function(n, o) {
				if (n != o) {
					this.Visible = n;
					this.datalist = this.couponList;
					this.getHeight();
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
						self.ratio = res.windowWidth / 750;
						self.getHeight();
					}
				});
			},

			/*获取高度*/
			getHeight() {
				let count = Object.keys(this.couponList).length;
				if (count > 2) {
					this.scrollviewHigh = this.phoneHeight * 0.7;
				} else {
					if (count == 1) {
						this.scrollviewHigh = 260 * this.ratio;
					} else if (count == 2) {
						this.scrollviewHigh = 520 * this.ratio;
					}
				}
			},

			/*选择优惠券*/
			selectCoupon(e) {
				this.closePopup(e);
			},
			/*关闭弹窗*/
			closePopup(e) {
				this.$emit('close', e);
			}
		}
	};
</script>

<style lang="scss" scoped>
	.usable-coupon .popup-bg {
		position: fixed;
		top: 0;
		right: 0;
		bottom: 0;
		left: 0;
		background: rgba(0, 0, 0, 0.6);
		z-index: 99;
	}

	.usable-coupon .main {
		position: fixed;
		width: 100%;
		min-height: 600rpx;
		background-color: #fff;
		transform: translate3d(0, 2980rpx, 0);
		transition: transform 0.2s cubic-bezier(0, 0, 0.25, 1);
		bottom: env(safe-area-inset-bottom);
		z-index: 99;
	}

	.usable-coupon .main {
		position: fixed;
		width: 100%;
		min-height: 200rpx;
		background-color: #f6f6f6;
		transform: translate3d(0, 2980rpx, 0);
		transition: transform 0.2s cubic-bezier(0, 0, 0.25, 1);
		bottom: env(safe-area-inset-bottom);
		z-index: 99;
	}

	.usable-coupon.open .main {
		transform: translate3d(0, 0, 0);
	}

	.usable-coupon.close .popup-bg {
		display: none;
	}

	.coupon-btns .btn-cancel {
		height: 88rpx;
		line-height: 88rpx;
		font-size: 30rpx;
		background: #f6f6f6;
		color: #ffffff;
		border-radius: 0;
	}

	.item-wrap {
		border: 1px solid #eee;
	}
</style>