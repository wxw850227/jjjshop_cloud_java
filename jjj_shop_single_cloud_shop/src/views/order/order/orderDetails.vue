<template>
	<div>
		<el-drawer title="订单详情" :size="1000" v-model="dialogVisible" :before-close="handleClose">
			<div v-if="orderDatalist">
				<div class="head">
					<div class="full">
						<img class="order_icon" :src="orderImg" alt="" />
						<div class="text">
							<div class="title">普通订单</div>
							<div>
								<span class="mr20">订单号：{{ orderDatalist.orderNo }}</span>
							</div>
						</div>
					</div>
					<ul class="list">
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
					</ul>
				</div>
				<el-tabs type="border-card" v-model="activeName" @tab-click="tabClick">
					<el-tab-pane label="订单信息" name="detail">
						<div class="section">
							<div class="title">基本信息</div>
							<ul class="list">
								<li class="item">
									<div class="">用户信息：</div>
									<div class="value">
										{{ orderDatalist.nickName || "" }}
									</div>
								</li>
								<li class="item">
									<div>订单金额：</div>
									<div class="value">
										{{ orderDatalist.orderPrice }}
									</div>
								</li>
								<li class="item">
									<div>运费金额：</div>
									<div class="value">
										{{ orderDatalist.expressPrice }}
									</div>
								</li>
								<li class="item">
									<div>实际支付：</div>
									<div class="value">
										{{ orderDatalist.payPrice || "0.0" }}
									</div>
								</li>
								<li class="item">
									<div>支付方式：</div>
									<div class="value">
										{{ orderDatalist.payTypeText }}
									</div>
								</li>
								<li class="item">
									<div>配送方式：</div>
									<div class="value">
										{{ orderDatalist.deliveryTypeText }}
									</div>
								</li>
								<li class="item">
									<div>下单时间：</div>
									<div class="value">
										{{ orderDatalist.createTime }}
									</div>
								</li>
								<li class="item">
									<div>交易状态：</div>
									<div class="value">
										{{ orderDatalist.orderStatusText }}
									</div>
								</li>
							</ul>
						</div>
						<div class="section" v-if="orderDatalist.deliveryType == 30">
							<div class="title">用户信息</div>
							<ul class="list">
								<li class="item">
									<div>用户名称：</div>
									<div class="value">
										{{ orderDatalist.nickName }}
									</div>
								</li>
								<li class="item">
									<div>绑定电话：</div>
									<div class="value">
										{{ orderDatalist.mobile || "" }}
									</div>
								</li>
							</ul>
						</div>
						<div class="section" v-if="orderDatalist.address && orderDatalist.deliveryType == 10">
							<div class="title">收货信息</div>
							<ul class="list">
								<!-- <li class="item">
                  <div>收货信息：</div>
                  <div class="value">{{ orderDatalist.user_address || '' }}</div>
                </li> -->
								<li class="item">
									<div>收货人：</div>
									<div class="value">
										{{ orderDatalist.address.name }}
									</div>
								</li>
								<li class="item">
									<div>收货电话：</div>
									<div class="value">
										{{ orderDatalist.address.phone }}
									</div>
								</li>
								<li class="item">
									<div>收货地址：</div>
									<div class="value">
										{{ orderDatalist.address.region.province }}
										{{ orderDatalist.address.region.city }}
										{{ orderDatalist.address.region.region }}
										{{ orderDatalist.address.detail }}
									</div>
								</li>
							</ul>
						</div>

						<div class="section" v-if="orderDatalist.express">
							<div class="title">
								{{ "物流信息" }}
							</div>
							<ul class="list">
								<li class="item">
									<div>
										{{ "物流公司：" }}
									</div>
									<div class="value">
										{{ orderDatalist.express.expressName }}
									</div>
								</li>
								<li class="item">
									<div>
										{{ "物流单号：" }}
									</div>
									<div class="value">
										{{ orderDatalist.expressNo }}
										<!-- <a
                      v-if="orderDatalist.delivery_type == 'express'"
                      @click="openLogistics"
                      >物流查询</a
                    > -->
									</div>
								</li>
							</ul>
						</div>
						<div class="section">
							<div class="title">付款信息</div>
							<ul class="list">
								<li class="item">
									<div>应付款金额：</div>
									<div class="value">
										{{ orderDatalist.payPrice }}
									</div>
								</li>
								<li class="item">
									<div>支付方式：</div>
									<div class="value">
										{{ orderDatalist.payTypeText }}
									</div>
								</li>
								<li class="item">
									<div>支付流水号：</div>
									<div class="value">
										{{ orderDatalist.transactionId }}
									</div>
								</li>
								<li class="item">
									<div>付款状态：</div>
									<div class="value">
										{{ orderDatalist.payStatusText }}
									</div>
								</li>
								<li class="item">
									<div>付款时间：</div>
									<div class="value">
										{{ orderDatalist.payTime }}
									</div>
								</li>
							</ul>
						</div>
						<div class="section" v-if="
                orderDatalist.payStatus == 20 &&
                orderDatalist.deliveryStatus == 10 &&
                orderDatalist.deliveryType == 10 &&
                [20, 21].indexOf(orderDatalist.orderStatus) === -1 &&
                (!orderDatalist.assembleStatus ||
                  orderDatalist.assembleStatus == 20)
              ">
							<div class="title">发货信息</div>
							<ul class="list">
								<li class="item">
									<div>物流公司：</div>
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
									<div>发货状态：</div>
									<div class="value">
										{{ orderDatalist.deliveryStatusText }}
									</div>
								</li>
								<li class="item">
									<div>付款时间：</div>
									<div class="value">
										{{ orderDatalist.deliveryTime }}
									</div>
								</li>
							</ul>
						</div>
						<div class="section" v-if="
                orderDatalist.payStatus == 20 && orderDatalist.orderStatus == 21
              ">
							<div class="title">用户取消订单</div>
							<ul class="list">
								<li class="item">
									<div style="white-space: nowrap">
										<p class="red pb16">
											当前买家已付款并申请取消订单，请审核是否同意，如同意则自动退回付款金额（微信支付原路退款）并关闭订单。
										</p>
										<el-form size="small" ref="forms" :model="forms">
											<el-form-item label="审核状态">
												<div>
													<el-radio v-model="forms.isCancel" :label="1">同意</el-radio>
													<el-radio v-model="forms.isCancel" :label="0">拒绝</el-radio>
												</div>
											</el-form-item>
										</el-form>
									</div>
									<div class="value"></div>
								</li>
							</ul>
						</div>
						<div class="section" v-if="
                orderDatalist.deliveryType == 20 &&
                orderDatalist.payStatus == 20 &&
                orderDatalist.orderStatus != 21 &&
                orderDatalist.orderStatus != 20
              ">
							<div class="title">门店自提核销</div>
							<ul class="list" v-if="orderDatalist.deliveryStatus == 10">
								<li class="item">
									<div style="white-space: nowrap">
										<el-form size="small" ref="extractForm" :model="extractForm"
											label-width="100px">
											<el-form-item label="门店核销员">
												<el-select v-model="extractClerkId" placeholder="点击选择">
													<el-option :label="item.realName"
														v-for="(item, index) in shopClerkList" :key="item.clerkId"
														:value="item.clerkId"></el-option>
												</el-select>
											</el-form-item>
											<el-form-item label="买家取货状态 "><el-radio
													v-model="extractForm.order.extractStatus"
													:label="1">已取货</el-radio></el-form-item>
										</el-form>
									</div>
									<div class="value"></div>
								</li>
							</ul>
							<ul class="list" v-else>
								<template v-if="orderDatalist.extractClerkId">
									<li class="item">
										<div>自提门店名称：</div>
										<div class="value">
											{{ orderDatalist.extractStoreName }}
										</div>
									</li>
									<li class="item">
										<div>核销员：</div>
										<div class="value">
											{{ orderDatalist.extractClerkName }}
										</div>
									</li>
									<li class="item">
										<div>核销状态：</div>
										<div class="value" v-if="orderDatalist.deliveryStatus == 20">
											已核销
										</div>
									</li>
									<li class="item">
										<div>核销时间：</div>
										<div class="value">
											{{ orderDatalist.deliveryTime }}
										</div>
									</li>
								</template>
							</ul>
						</div>
						<div class="section" v-if="
                orderDatalist.deliveryType == 30 &&
                orderDatalist.payStatus == 20 &&
                orderDatalist.orderStatus != 21 &&
                orderDatalist.orderStatus != 20
              ">
							<div class="title">虚拟商品发货</div>

							<div v-if="orderDatalist.deliveryStatus == 10"></div>
							<div v-else>
								<template v-if="orderDatalist.virtualContent">
									<ul class="list">
										<li class="item">
											<div>发货信息：</div>
											<div class="value">
												{{ orderDatalist.virtualContent }}
											</div>
										</li>
										<li class="item">
											<div>发货状态：</div>
											<div class="value">
												<template v-if="orderDatalist.deliveryStatus == 20">
													已发货
												</template>
											</div>
										</li>
										<li class="item">
											<div>发货时间：</div>
											<div class="value">
												{{ orderDatalist.deliveryTime }}
											</div>
										</li>
									</ul>
								</template>
							</div>
						</div>
						<div class="btn-group">
							<!--商家审核-->
							<template v-if="
                  orderDatalist.payStatus == 20 &&
                  orderDatalist.orderStatus == 21
                ">
								<el-button size="small" type="primary" @click="confirmCancel()">确认审核</el-button>
							</template>
							<template v-if="
                  orderDatalist.deliveryStatus == 10 &&
                  orderDatalist.deliveryType == 20 &&
                  orderDatalist.payStatus == 20 &&
                  orderDatalist.orderStatus != 21 &&
                  orderDatalist.orderStatus != 20
                ">
								<el-button type="primary" @click="onExtract(orderDatalist.orderId)">确认核销</el-button>
							</template>
						</div>
					</el-tab-pane>
					<el-tab-pane label="商品信息" name="goods">
						<el-table class="mt20" :data="orderDatalist.productList">
							<el-table-column label="商品信息" min-width="300">
								<template #default="scope">
									<div class="tab">
										<div class="demo-image__preview">
											<el-image :src="scope.row.imagePath"
												:preview-src-list="[scope.row.imagePath]" />
										</div>
										<div>
											<div class="line">
												{{ scope.row.productName }}
											</div>
											<!-- <div class="line1 gary">
                        规格：{{
                          scope.row.productInfo.attrInfo
                            ? scope.row.productInfo.attrInfo.suk
                            : "默认"
                        }}
                      </div> -->
										</div>
									</div>
								</template>
							</el-table-column>
							<el-table-column prop="productNo" label="商品编码"></el-table-column>
							<el-table-column prop="productWeight" label="重量 (Kg)"></el-table-column>
							<el-table-column prop="totalNum" label="购买数量">
								<template #default="scope">
									<p>x {{ scope.row.totalNum }}</p>
								</template>
							</el-table-column>
							<el-table-column prop="totalPrice" label="商品总价(元)">
								<template #default="scope">
									<p>￥ {{ scope.row.totalPrice }}</p>
								</template>
							</el-table-column>
						</el-table>
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
				loading: false,
				orderDatalist: {},
				forms: {
					isCancel: 1,
				},
				extractForm: {
					order: {
						extractStatus: 1,
					},
				},
				shopClerkList: [],
			};
		},
		props: ["orderId", "modals"],
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
			console.log(this.orderId, "created");
			/*获取列表*/
			this.getParams();
		},
		methods: {
			onExtract(e) {
				let self = this;
				let extractForm = self.extractForm;
				let param = {};
				param.orderId = e;
				param.extractStatus = extractForm.order.extractStatus;
				param.extractClerkId = self.extractClerkId;
				OrderApi.extract(param, true)
					.then((data) => {
						self.loading = false;
						ElMessage({
							message: "恭喜你，操作成功",
							type: "success",
						});
						self.getParams();
					})
					.catch((error) => {
						self.loading = false;
					});
			},
			confirmCancel() {
				let self = this;
				let orderId = this.$props.orderId;
				let isCancel = self.forms.isCancel;
				OrderApi.confirm({
							orderId: orderId,
							isCancel: isCancel,
						},
						true
					)
					.then((data) => {
						self.loading = false;
						ElMessage({
							message: "恭喜你，审核成功",
							type: "success",
						});
						self.getParams();
					})
					.catch((error) => {
						self.loading = false;
					});
			},
			getParams() {
				let self = this;
				// 取到路由带过来的参数
				const params = this.$props.orderId;
				OrderApi.orderdetail({
							orderId: params,
						},
						true
					)
					.then((res) => {
						self.loading = false;
						self.orderDatalist = res.data;
						console.log(self.detail.deliveryStatus == 10);
						self.expressList = res.data.expressList;
						self.shopClerkList = res.data.shopClerkList;
						self.addressData = res.data.address;
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
		position: relative;
		bottom: 0;
		left: 0;
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
			background: var(--el-color-primary);
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

	:deep(.el-tabs--border-card)  {
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