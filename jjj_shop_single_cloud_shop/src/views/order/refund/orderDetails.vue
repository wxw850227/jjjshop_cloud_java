<template>
	<div>
		<el-drawer title="订单详情" :size="1000" v-model="dialogVisible" :before-close="handleClose">
			<div v-if="orderDatalist">
				<div class="head">
					<div class="full">
						<img class="order_icon" :src="orderImg" alt="" />
						<div class="text">
							<div class="title">售后信息</div>
							<div>
								<span class="mr20">订单号：{{
                    orderDatalist.order && orderDatalist.order.orderNo
                  }}</span>
							</div>
						</div>
					</div>
					<!-- <ul class="list">
            <li class="item">
              <div class="title">订单状态</div>
              <div>{{ orderDatalist.orderStatusText }}</div>
            </li>
            <li class="item">
              <div class="title">实际支付</div>
              <div>¥ {{ orderDatalist.payPrice || "0.0" }}</div>
            </li>
            <li class="item">
              <div class="title">支付方式</div>
              <div>{{ orderDatalist.payTypeText }}</div>
            </li>
            <li class="item">
              <div class="title">支付时间</div>
              <div>{{ orderDatalist.payTime }}</div>
            </li>
          </ul> -->
				</div>
				<el-tabs type="border-card" v-model="activeName" @tab-click="tabClick">
					<el-tab-pane label="售后信息" name="detail">
						<div class="section">
							<div class="title">基本信息</div>
							<ul class="list">
								<li class="item">
									<div class="">买家信息：</div>
									<div class="value">
										{{
                      (orderDatalist.user && orderDatalist.user.nickName) || ""
                    }}
									</div>
								</li>
								<li class="item">
									<div>退款金额(元)：</div>
									<div class="value">
										{{ orderDatalist.refundMoney }}
									</div>
								</li>
								<li class="item">
									<div>处理状态：</div>
									<div class="value">
										{{ orderDatalist.statusText }}
									</div>
								</li>
								<li class="item">
									<div>售后类型：</div>
									<div class="value">
										{{ orderDatalist.typeText }}
									</div>
								</li>
							</ul>
						</div>
						<div class="section">
							<div class="title">商品信息</div>
							<ul class="list">
								<li class="item">
									<div>商品编码：</div>
									<div class="value">
										{{ orderDatalist.nickName }}
									</div>
								</li>
								<li class="item">
									<div>商品名称：</div>
									<div class="value">
										{{
                      orderDatalist.orderProduct &&
                      orderDatalist.orderProduct.productName
                    }}
									</div>
								</li>
								<li class="item">
									<div>重量(Kg)：</div>
									<div class="value">
										{{
                      orderDatalist.orderProduct &&
                      orderDatalist.orderProduct.productWeight
                    }}
									</div>
								</li>
								<li class="item">
									<div>单价：</div>
									<div class="value">
										{{
                      orderDatalist.orderProduct &&
                      orderDatalist.orderProduct.linePrice
                    }}
									</div>
								</li>
								<li class="item">
									<div>购买数量：</div>
									<div class="value">
										{{
                      orderDatalist.orderProduct &&
                      orderDatalist.orderProduct.totalNum
                    }}
									</div>
								</li>
								<li class="item" v-if="
                    orderDatalist.order && orderDatalist.order.orderSource == 80
                  ">
									<div>付款价：</div>
									<div class="value">
										{{ (orderDatalist.order.payPrice * 1).toFixed(2) }}
									</div>
								</li>
								<li class="item" v-else>
									<div>付款价：</div>
									<div class="value">
										{{
                      orderDatalist.orderProduct &&
                      orderDatalist.orderProduct.totalPayPrice
                    }}
									</div>
								</li>
							</ul>
						</div>
						<div class="section">
							<div class="title">用户申请原因</div>
							<ul class="list">
								<li class="item">
									<div></div>
									<div class="value">
										{{ orderDatalist.applyDesc }}
									</div>
								</li>
								<li class="item">
									<div></div>
									<div class="value">
										<div class="d-s-c d-r" v-if="
                        orderDatalist.images && orderDatalist.images.length > 0
                      ">
											<div class="" v-for="(item, index) in orderDatalist.images" :key="index">
												<el-image :src="item.filePath" :preview-src-list="[item.filePath]"
													style="width: 80px; height: 80px" />
											</div>
										</div>
									</div>
								</li>
							</ul>
						</div>
						<div class="section" v-if="orderDatalist.isAgree == 10 && orderDatalist.type != 30">
							<div class="title">退货地址</div>
							<ul class="list">
								<li class="item">
									<div class="">收货人：</div>
									<div class="value">
										{{ orderDatalist.address && orderDatalist.address.name }}
									</div>
								</li>
								<li class="item">
									<div>收货电话：</div>
									<div class="value">
										{{ orderDatalist.address && orderDatalist.address.phone }}
									</div>
								</li>
								<li class="item">
									<div>收货地址：</div>
									<div class="value">
										{{ orderDatalist.address && orderDatalist.address.detail }}
									</div>
								</li>
							</ul>
						</div>
						<div class="section" v-if="orderDatalist.isAgree == 20">
							<div class="title">商家拒绝原因</div>
							<ul class="list">
								<li class="item">
									<div class=""></div>
									<div class="value">
										{{ orderDatalist.refuseDesc }}
									</div>
								</li>
							</ul>
						</div>
						<div class="section" v-if="
                orderDatalist.type != 30 &&
                orderDatalist.isAgree == 10 &&
                orderDatalist.isUserSend == 1
              ">
							<div class="title">用户发货信息</div>
							<ul class="list">
								<li class="item">
									<div class="">物流公司：</div>
									<div class="value">
										{{
                      orderDatalist.express && orderDatalist.express.expressName
                    }}
									</div>
								</li>
								<li class="item">
									<div>物流单号：</div>
									<div class="value">
										{{ orderDatalist.expressNo }}
									</div>
								</li>
								<li class="item">
									<div>用户发货状态：</div>
									<div class="value">已发货</div>
								</li>
								<li class="item">
									<div>发货时间：</div>
									<div class="value">
										{{ orderDatalist.sendTime }}
									</div>
								</li>
								<li class="item">
									<div>商家收货状态：</div>
									<div class="value">
										<template v-if="orderDatalist.isReceipt == 1">
											已收货
										</template>
										<template v-else> 待收货 </template>
									</div>
								</li>
							</ul>
						</div>
						<div class="section" v-if="
                orderDatalist.type == 20 &&
                orderDatalist.isAgree == 10 &&
                orderDatalist.isUserSend == 1 &&
                orderDatalist.isPlateSend == 1
              ">
							<div class="title">平台发货信息</div>
							<ul class="list">
								<li class="item">
									<div>物流公司：</div>
									<div class="value">
										{{ orderDatalist.sendExpressName }}
									</div>
								</li>
								<li class="item">
									<div>物流单号：</div>
									<div class="value">{{ orderDatalist.sendExpressNo }}</div>
								</li>
								<li class="item">
									<div>发货时间：</div>
									<div class="value">
										{{ orderDatalist.deliverTime }}
									</div>
								</li>
							</ul>
						</div>
					</el-tab-pane>
					<el-tab-pane label="商家审核" name="goods" v-if="
              orderDatalist.isAgree == 0 ||
              (orderDatalist.type == 20 &&
                orderDatalist.isAgree == 10 &&
                orderDatalist.isUserSend == 1 &&
                orderDatalist.isPlateSend == 0) ||
              (orderDatalist.type == 10 &&
                orderDatalist.isReceipt == 0 &&
                orderDatalist.isAgree == 10 &&
                orderDatalist.isUserSend == 1)
            ">
						<el-form v-if="orderDatalist.isAgree == 0" size="small" ref="form" :model="form"
							label-width="80px">
							<el-form-item label="售后类型">
								<span class="orange">{{ orderDatalist.typeText }}</span>
							</el-form-item>
							<el-form-item label="审核状态">
								<el-radio-group v-model="form.isAgree">
									<el-radio :label="10">同意</el-radio>
									<el-radio :label="20">拒绝</el-radio>
								</el-radio-group>
							</el-form-item>
							<el-form-item label="退货地址" v-if="form.isAgree == 10 && orderDatalist.type != 30">
								<el-select v-model="form.addressId" placeholder="请选择地址">
									<el-option v-for="(item, index) in addressList" :key="index" :label="item.detail"
										:value="item.addressId"></el-option>
								</el-select>
							</el-form-item>
							<el-form-item label="退款金额" v-if="form.isAgree == 10 && orderDatalist.type == 30">
								<el-input v-model="form.refundMoney"></el-input>
								<p>
									请输入退款金额，最多{{
                    orderDatalist.orderProduct.totalPayPrice
                  }}
									元。注：该操作将执行订单原路退款
									并关闭当前售后单，请确认并填写退款的金额（不能大于订单实付款）
								</p>
							</el-form-item>
							<el-form-item label="拒绝原因" v-if="form.isAgree == 20"><el-input type="textarea"
									v-model="form.refuseDesc" class="max-w460"></el-input></el-form-item>
						</el-form>
						<div class="mt20" v-if="
                orderDatalist.type == 20 &&
                orderDatalist.isAgree == 10 &&
                orderDatalist.isUserSend == 1
              ">
							<div class="section" v-if="orderDatalist.isPlateSend == 0">
								<div class="title">发货</div>
								<li class="item">
									<div></div>
									<div class="value">
										<el-form size="small" ref="deliver" :model="deliver" label-width="100px">
											<el-form-item label="物流公司">
												<el-select v-model="deliver.sendExpressId" placeholder="请选择快递公司">
													<el-option :label="item.expressName"
														v-for="(item, index) in expressList" :key="index"
														:value="item.expressId"></el-option>
												</el-select>
											</el-form-item>
											<el-form-item label="物流单号"><el-input v-model="deliver.sendExpressNo"
													class="max-w460"></el-input></el-form-item>
										</el-form>
									</div>
								</li>
							</div>
						</div>
						<div class="mt20" v-if="
                orderDatalist.type == 10 &&
                orderDatalist.isReceipt == 0 &&
                orderDatalist.isAgree == 10 &&
                orderDatalist.isUserSend == 1
              ">
							<div class="section">
								<div class="title">确认收货并退款</div>
								<li class="item">
									<div></div>
									<div class="value">
										<el-form size="small" ref="money" :model="money" label-width="80px">
											<p>
												注：该操作将执行订单原路退款
												并关闭当前售后单，请确认并填写退款的金额（不能大于订单实付款）
											</p>
											<el-form-item label="售后类型">
												<span class="orange">{{ orderDatalist.typeText }}</span>
											</el-form-item>
											<el-form-item label="退款金额">
												<el-input v-model="money.refundMoney"></el-input>
												<p>
													请输入退款金额，最多{{
                            orderDatalist.orderProduct.totalPayPrice
                          }}
													元
												</p>
											</el-form-item>
										</el-form>
									</div>
								</li>
							</div>
						</div>
						<div class="btn-group">
							<!--商家审核-->
							<template v-if="orderDatalist.isAgree == 0">
								<el-button size="small" type="primary"
									@click="onSubmit(orderDatalist.orderRefundId)">确认审核</el-button>
							</template>
							<template v-if="
                  orderDatalist.type != 30 &&
                  orderDatalist.isAgree == 10 &&
                  orderDatalist.isUserSend == 1 &&
                  orderDatalist.isReceipt == 0
                ">
								<el-button size="small" type="primary"
									@click="refundMoney(orderDatalist.orderRefundId)">确认发货</el-button>
							</template>
						</div>
					</el-tab-pane>
				</el-tabs>
			</div>
		</el-drawer>
	</div>
</template>
<script>
	import OrderApi from "@/api/order.js";
	import orderImg from "@/assets/img/order_icon.png";
	export default {
		data() {
			return {
				activeName: "detail",
				modal2: false,
				dialogVisible: false,
				grid: {
					xl: 8,
					lg: 8,
					md: 12,
					sm: 24,
					xs: 24,
				},
				result: [],
				orderImg: orderImg,
				recordData: [],
				page: {
					page: 1, // 当前页
					limit: 15, // 每页显示条数
				},
				form: {
					isAgree: 10,
					addressId: "",
					refuseDesc: "",
					refundMoney: "",
				},
				loading: false,
				orderDatalist: {},
				addressList: [],
				/*快递公司列表*/
				expressList: [],
				/*订单详情*/
				order: {},
				/*退货地址列表*/
				addressList: {},
				money: {
					refundMoney: "",
				},
				orderRefundId: 0,
				deliver: {
					sendExpressId: "",
					sendExpressNo: "",
				},
			};
		},
		props: ["orderId", "modals", "types"],
		watch: {
			modals(val) {
				if (val) {
					this.activeName = "detail";
				}
			},
		},
		filters: {
			payType(val) {
				let obj = {
					yue: "余额",
					weixin: "微信支付",
					alipay: "支付宝支付",
					offline: "线下支付",
				};
				return obj[val];
			},
		},
		created() {
			this.dialogVisible = this.modals;
			if (this.types) {
				this.activeName = this.types;
			} else {
				this.activeName = "detail";
			}
			console.log(this.orderId, "created");
			/*获取列表*/
			this.getParams();
		},
		methods: {
			onSubmit(e) {
				let self = this;
				let form = self.form;
				let orderRefundId = e;
				OrderApi.Approval({
							isAgree: form.isAgree,
							addressId: form.addressId,
							refuseDesc: form.refuseDesc,
							orderRefundId: orderRefundId,
							refundMoney: form.refundMoney,
						},
						true
					)
					.then((data) => {
						ElMessage({
							message: "恭喜你，操作成功",
							type: "success",
						});
						self.handleClose();
					})
					.catch((error) => {});
			},
			/*确认收货退款*/
			refundMoney(e) {
				let self = this;
				let form = {};
				let param = self.deliver;
				if (param.sendExpressId == "" && self.orderDatalist.type == 20) {
					ElMessage.error("请选择物流公司");
					return;
				}
				if (param.sendExpressNo == "" && self.orderDatalist.type == 20) {
					ElMessage.error("请填写物流单号");
					return;
				}

				if (self.orderDatalist.type == 20) {
					form.sendExpressId = param.sendExpressId;
					form.sendExpressNo = param.sendExpressNo;
				}
				form.refundMoney = self.money.refundMoney;
				form.orderRefundId = e;
				OrderApi.receipt(form, true)
					.then((data) => {
						ElMessage({
							message: "恭喜你，操作成功",
							type: "success",
						});
						self.handleClose();
					})
					.catch((error) => {});
			},
			getParams() {
				let self = this;
				// 取到路由带过来的参数
				const params = this.$props.orderId;
				OrderApi.refundDetail({
							orderRefundId: params,
						},
						true
					)
					.then((res) => {
						self.loading = false;
						self.orderDatalist = res.data;
						self.order = res.data.order;
						self.addressList = res.data.addressList;
						self.expressList = res.data.expressList;
					})
					.catch((error) => {
						self.loading = false;
					});
			},
			tabClick() {},
			handleClose() {
				this.$emit("closeDialog", {
					type: "success",
					openDialog: false,
				});
			},
			// handleClose() {
			//   this.dialogVisible = false;
			// },
		},
	};
</script>
<style lang="scss" scoped>
	:deep(.el-tabs--border-card > .el-tabs__header .el-tabs__item.is-active) {
		border-bottom: none;
	}

	:deep(.el-tabs__item) {
		height: 40px !important;
		line-height: 40px !important;
	}

	.btn-group {
		position: fixed;
		bottom: 10px;
		right: 400px;
	}

	.head {
		padding: 0 35px 24px;

		.full {
			display: flex;
			align-items: center;

			.order_icon {
				width: 60px;
				height: 60px;
			}

			.iconfont {
				color: var(--prev-color-primary);

				&.sale-after {
					color: #90add5;
				}
			}

			.text {
				align-self: center;
				flex: 1;
				min-width: 0;
				padding-left: 12px;
				font-size: 13px;
				color: #606266;

				.title {
					margin-bottom: 10px;
					font-weight: 500;
					font-size: 16px;
					line-height: 16px;
					color: rgba(0, 0, 0, 0.85);
				}

				.order-num {
					padding-top: 10px;
					white-space: nowrap;
				}
			}
		}

		.list {
			display: flex;
			margin-top: 20px;
			overflow: hidden;
			list-style: none;
			padding: 0;

			.item {
				flex: none;
				width: 200px;
				font-size: 14px;
				line-height: 14px;
				color: rgba(0, 0, 0, 0.85);

				.title {
					margin-bottom: 12px;
					font-size: 13px;
					line-height: 13px;
					color: #666666;
				}

				.value1 {
					color: #f56022;
				}

				.value2 {
					color: #1bbe6b;
				}

				.value3 {
					color: var(--prev-color-primary);
				}

				.value4 {
					color: #6a7b9d;
				}

				.value5 {
					color: #f5222d;
				}
			}
		}
	}

	.section {
		padding: 25px 0;
		border-bottom: 1px dashed #eeeeee;

		.title {
			position: relative;
			padding-left: 12px;
			border-left: 3px solid var(--prev-color-primary);
			font-size: 15px;
			font-weight: 700;
			line-height: 15px;
			color: #303133;
		}

		.title:before {
			position: absolute;
			content: "";
			width: 4px;
			height: 14px;
			left: 0;
			top: 50%;
			transform: translateY(-50%);
		}

		.title:before {
			background: #3a8ee6;
		}

		.list {
			display: flex;
			flex-wrap: wrap;
			list-style: none;
			padding: 0;
		}

		.item {
			flex: 0 0 calc(100% / 3);
			display: flex;
			margin-top: 16px;
			font-size: 13px;
			color: #666666;

			&:nth-child(3n + 1) {
				padding-right: 20px;
			}

			&:nth-child(3n + 2) {
				padding-right: 10px;
				padding-left: 10px;
			}

			&:nth-child(3n + 3) {
				padding-left: 20px;
			}
		}

		.value {
			flex: 1;

			image {
				display: inline-block;
				width: 40px;
				height: 40px;
				margin: 0 12px 12px 0;
				vertical-align: middle;
			}
		}

		.item.pic {
			display: flex;

			img {
				width: 80px;
				height: 80px;
			}
		}
	}

	.tab {
		display: flex;
		align-items: center;

		.el-image {
			width: 36px;
			height: 36px;
			margin-right: 10px;
		}
	}

	:deep(.el-drawer__body) {
		// padding: 0;
		overflow: auto;
	}

	.gary {
		color: #aaa;
	}

	:deep(.el-drawer__body) {
		padding: 20px 0;
	}

	:deep(.el-tabs--border-card>.el-tabs__content) {
		padding: 0 35px;
	}

	:deep(.el-tabs--border-card>.el-tabs__header),
	:deep(.el-tabs--border-card>.el-tabs__header .el-tabs__item:active) {
		border: none;
		height: 40px;
	}

	:deep(.el-tabs--border-card>.el-tabs__header .el-tabs__item.is-active) {
		border: none;
		border-top: 2px solid var(--prev-color-primary);
		font-size: 13px;
		font-weight: 500;
		color: #303133;
		line-height: 16px;
	}

	:deep(.el-tabs--border-card>.el-tabs__header .el-tabs__item) {
		border: none;
	}

	:deep(.el-tabs--border-card>.el-tabs__header .el-tabs__item) {
		margin-top: 0;
		transition: none;
		height: 40px !important;
		line-height: 40px !important;
		width: 92px !important;
		font-size: 13px;
		font-weight: 400;
		color: #303133;
		line-height: 16px;
	}

	:deep(.el-tabs--border-card) {
		border: none;
		box-shadow: none;
	}

	.logistics {
		align-items: center;
		padding: 10px 20px;

		.logistics_img {
			width: 45px;
			height: 45px;
			margin-right: 12px;

			img {
				width: 100%;
				height: 100%;
			}
		}

		.logistics_cent {
			span {
				display: block;
				font-size: 12px;
			}
		}
	}

	.trees-coadd {
		width: 100%;
		height: 400px;
		border-radius: 4px;
		overflow: hidden;

		.scollhide {
			width: 100%;
			height: 100%;
			overflow: auto;
			margin-left: 18px;
			padding: 10px 0 10px 0;
			box-sizing: border-box;

			.content {
				font-size: 12px;
			}

			.time {
				font-size: 12px;
				color: #2d8cf0;
			}
		}
	}
</style>