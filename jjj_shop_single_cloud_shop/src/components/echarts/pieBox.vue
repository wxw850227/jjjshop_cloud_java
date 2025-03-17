<template>
	<div class="ww100 mb30">
		<div class="d-e-c mb18">
			<div>
				<el-date-picker size="small" v-model="datePicker" type="daterange" align="right" unlink-panels
					format="YYYY-MM-DD" value-format="YYYY-MM-DD" range-separator="至" start-placeholder="开始日期"
					end-placeholder="结束日期" @change="changeDate" :shortcuts="shortcuts"></el-date-picker>
			</div>
		</div>
		<div class="">
			<div class="Echarts">
				<div ref="mainChart"></div>
			</div>
		</div>
	</div>
</template>

<script>
	import IndexApi from "@/api/index.js";
	import {
		formatDate
	} from "@/utils/DateTime.js";
	import * as echarts from "echarts";
	let myChart;
	export default {
		data() {
			let endDate = new Date();
			let startDate = new Date();
			startDate.setTime(startDate.getTime() - 3600 * 1000 * 24 * 7);
			return {
				/*是否正在加载*/
				loading: true,
				/*类别*/
				activeName: "new",
				DateType: 1,
				/*时间快捷选项*/

				shortcuts: [{
						text: "最近一周",
						value: () => {
							const end = new Date();
							const start = new Date();
							this.DateType = 1;
							start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
							return [start, end];
						},
					},
					{
						text: "最近一个月",
						value: () => {
							const end = new Date();
							const start = new Date();
							this.DateType = 1;
							start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
							return [start, end];
						},
					},
					{
						text: "最近三个月",
						value: () => {
							const end = new Date();
							const start = new Date();
							this.DateType = 1;
							start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
							return [start, end];
						},
					},
					{
						text: "本年",
						value: () => {
							this.DateType = 3;
							return [];
						},
					},
				],
				datePicker: [],
				startDate: formatDate(startDate, "YYYY-MM-DD"),
				endDate: formatDate(endDate, "YYYY-MM-DD"),
				/*数据对象*/
				dataList: null,
				/*交易统计图表对象*/
				// myChart: null,
				/*图表数据*/
				option: {
					title: {
						//text: 'ECharts 入门示例'
					},
					grid: {
						left: "3%",
						right: "4%",
						bottom: "3%",
						containLabel: true,
					},
					toolbox: {
						feature: {
							// dataView: {
							//   show: true,
							//   readOnly: false,
							//   title: "数据视图", // 设置为中文
							// },
							magicType: {
								show: true,
								type: ["line", "bar"],
								title: {
									line: "切换为折线图",
									bar: "切换为柱状图",
								},
							},
							restore: {
								show: true,
								title: "还原", // 设置为中文
							},
							saveAsImage: {
								show: true,
								title: "保存为图片", // 设置为中文
							},
						},
					},
					tooltip: {
						trigger: "axis",
						axisPointer: {
							type: "cross",
							crossStyle: {
								color: "#999",
							},
						},
					},
					yAxis: {},
				},
			};
		},
		created() {},
		mounted() {
			this.myEcharts();
			window.addEventListener("resize", this.resizeChart);
		},
		beforeDestroy() {
			window.removeEventListener("resize", this.resizeChart);
		},
		methods: {
			/*切换*/
			handleClick(e) {
				this.activeName = e;
				this.getData();
			},

			/*选择时间*/
			changeDate() {
				if (this.DateType !== 3) {
					this.startDate = this.datePicker[0];
					this.endDate = this.datePicker[1];
				}
				this.getData();
			},

			/*创建图表对象*/
			myEcharts() {
				// 基于准备好的dom，初始化echarts实例
				myChart = echarts.init(this.$refs.mainChart);
				/*获取列表*/
				this.getData();
			},
			resizeChart() {
				if (myChart) {
					myChart.resize();
				}
			},
			/*格式数据*/
			createOption() {
				if (!this.loading) {
					let names = ["支付笔数", "支付金额"];
					let xAxis = this.dataList.days;
					let series1 = [];
					let series2 = [];

					this.dataList.deal.forEach((item) => {
						series1.push(item.depositNum);
						series2.push(item.depositMoney);
					});
					// 指定图表的配置项和数据
					this.option.xAxis = {
						type: "category",
						data: xAxis,
						axisPointer: {
							type: "shadow",
						},
					};
					this.option.color = ["#1495EB", "#00CC66"];

					this.option.legend = {
						data: [names[0], names[1]],
					};
					this.option.yAxis = [{
							type: "value",
							name: "数量",
							min: 0,
							max: 100,
							interval: 20,
							axisLabel: {
								formatter: "{value} 笔",
							},
						},
						{
							type: "value",
							name: "金额",
							min: 0,
							max: 25000,
							interval: 5000,
							axisLabel: {
								formatter: "{value} 元",
							},
						},
					];
					this.option.series = [{
							name: names[0],
							type: "bar",
							tooltip: {
								valueFormatter: function(value) {
									return value + " 笔";
								},
							},
							data: series1,
						},
						{
							name: names[1],
							type: "line",
							yAxisIndex: 1,
							tooltip: {
								valueFormatter: function(value) {
									return value + " 元";
								},
							},
							data: series2,
						},
					];

					myChart.setOption(this.option);
					myChart.resize();
				}
			},

			/*获取列表*/
			getData() {
				let self = this;
				self.loading = true;
				const params =
					self.DateType == 3 ? {
						dateType: self.DateType
					} : {
						DateType: self.DateType,
						startDate: self.startDate,
						endDate: self.endDate,
					};
				IndexApi.getDeal(params, true)
					.then((res) => {
						self.dataList = res.data;
						self.loading = false;
						self.createOption();
					})
					.catch((error) => {});
			},
		},
	};
</script>

<style scoped="scoped">
	.Echarts {
		box-sizing: border-box;
	}

	.Echarts>div {
		height: 360px;
		box-sizing: border-box;
	}
</style>