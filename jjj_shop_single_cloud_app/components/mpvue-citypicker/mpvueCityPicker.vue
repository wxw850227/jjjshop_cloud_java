<template>
	<div class="mpvue-picker">
		<div :class="{'pickerMask':showPicker}" @click="maskClick" catchtouchmove="true"></div>
		<div class="mpvue-picker-content " :class="{'mpvue-picker-view-show':showPicker}">
			<div class="mpvue-picker__hd" catchtouchmove="true">
				<div class="mpvue-picker__action" @click="pickerCancel">取消</div>
				<div class="mpvue-picker__action" :style="{color:themeColor}" @click="pickerConfirm">确定</div>
			</div>
			<picker-view indicator-style="height: 40px;" class="mpvue-picker-view" :value="pickerValue"
				@change="pickerChange">

					<picker-view-column>
						<div class="picker-item" v-for="(item,index) in provinceDataList" :key="index">{{item.label}}
						</div>
					</picker-view-column>
					<picker-view-column>
						<div class="picker-item" v-for="(item,index) in cityDataList" :key="index">{{item.label}}</div>
					</picker-view-column>
					<picker-view-column>
						<div class="picker-item" v-for="(item,index) in areaDataList" :key="index">{{item.label}}</div>
					</picker-view-column>

			</picker-view>
		</div>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				pickerValue: [0, 0, 0],
				provinceDataList: [],
				cityDataList: [],
				areaDataList: [],
				/* 是否显示控件 */
				showPicker: false,
				provinceData: [],
				cityData: [],
				areaData: []
			};
		},
		created() {
			this.init()
		},
		props: {
			/* 默认值 */
			pickerValueDefault: {
				type: Array,
				default () {
					return [0, 0, 0]
				}
			},
			/* 主题色 */
			themeColor: String,
			province: {
				type: Array
			},
			city: {
				type: Array
			},
			area: {
				type: Array
			}
		},
		watch: {
			pickerValueDefault() {
				this.init();
			}
		},
		methods: {
			init() {
				this.provinceData = this.province;
				this.cityData = this.city;
				this.areaData = this.area;
				this.handPickValueDefault(); // 对 pickerValueDefault 做兼容处理
				this.provinceDataList = this.provinceData;
				this.cityDataList = this.cityData[this.pickerValueDefault[0]];
				this.areaDataList = this.areaData[this.pickerValueDefault[0]][this.pickerValueDefault[1]];
				this.pickerValue = this.pickerValueDefault;
			},
			show() {
				setTimeout(() => {
					this.showPicker = true;
				}, 0);
			},
			maskClick() {
				//this.pickerCancel();
			},
			pickerCancel() {
				this.showPicker = false;
				this._$emit('onCancel');
			},
			pickerConfirm(e) {
				this.showPicker = false;
				this._$emit('onConfirm');
			},
			showPickerView() {
				this.showPicker = true;
			},
			handPickValueDefault() {
				if (this.pickerValueDefault !== [0, 0, 0]) {
					if (this.pickerValueDefault[0] > this.provinceData.length - 1) {
						this.pickerValueDefault[0] = this.provinceData.length - 1;
					}
					if (this.pickerValueDefault[1] > this.cityData[this.pickerValueDefault[0]].length - 1) {
						this.pickerValueDefault[1] = this.cityData[this.pickerValueDefault[0]].length - 1;
					}
					if (this.pickerValueDefault[2] > this.areaData[this.pickerValueDefault[0]][this.pickerValueDefault[1]]
						.length - 1) {
						this.pickerValueDefault[2] = this.areaData[this.pickerValueDefault[0]][this.pickerValueDefault[1]]
							.length - 1;
					}
				}
			},
			pickerChange(e) {
				let changePickerValue = e.detail.value;
				if (this.pickerValue[0] !== changePickerValue[0]) {
					// 第一级发生滚动
					this.cityDataList = this.cityData[changePickerValue[0]];
					this.areaDataList = this.areaData[changePickerValue[0]][0];
					changePickerValue[1] = 0;
					changePickerValue[2] = 0;
				} else if (this.pickerValue[1] !== changePickerValue[1]) {
					// 第二级滚动
					this.areaDataList =
						this.areaData[changePickerValue[0]][changePickerValue[1]];
					changePickerValue[2] = 0;
				}
				this.pickerValue = changePickerValue;
				this._$emit('onChange');
			},
			_$emit(emitName) {
				let pickObj = {
					label: this._getLabel(),
					value: this.pickerValue,
					cityCode: this._getCityCode()
				};
				this.$emit(emitName, pickObj);
			},
			_getLabel() {
				let pcikerLabel =
					this.provinceDataList[this.pickerValue[0]].label +
					',' +
					this.cityDataList[this.pickerValue[1]].label +
					',' +
					this.areaDataList[this.pickerValue[2]].label;
				return pcikerLabel;
			},
			_getCityCode() {
				let pcikerCode = [0, 0, 0];
				pcikerCode[0] = this.provinceDataList[this.pickerValue[0]].value;
				pcikerCode[1] = this.cityDataList[this.pickerValue[1]].value;
				pcikerCode[2] = this.areaDataList[this.pickerValue[2]].value;
				return pcikerCode;
			}
		}
	};
</script>

<style>
	.pickerMask {
		position: fixed;
		z-index: 1000;
		top: 0;
		right: 0;
		left: 0;
		bottom: 0;
		background: rgba(0, 0, 0, 0.6);
	}

	.mpvue-picker-content {
		position: fixed;
		bottom: 0;
		left: 0;
		width: 100%;
		transition: all 0.3s ease;
		transform: translateY(100%);
		z-index: 3000;
	}

	.mpvue-picker-view-show {
		transform: translateY(0);
	}

	.mpvue-picker__hd {
		display: flex;
		padding: 9px 15px;
		background-color: #fff;
		position: relative;
		text-align: center;
		font-size: 17px;
	}

	.mpvue-picker__hd:after {
		content: ' ';
		position: absolute;
		left: 0;
		bottom: 0;
		right: 0;
		height: 1px;
		border-bottom: 1px solid #e5e5e5;
		color: #e5e5e5;
		transform-origin: 0 100%;
		transform: scaleY(0.5);
	}

	.mpvue-picker__action {
		display: block;
		flex: 1;
		color: #1aad19;
	}

	.mpvue-picker__action:first-child {
		text-align: left;
		color: #888;
	}

	.mpvue-picker__action:last-child {
		text-align: right;
	}

	.picker-item {
		text-align: center;
		line-height: 40px;
		text-overflow: ellipsis;
		white-space: nowrap;
		font-size: 16px;
	}

	.mpvue-picker-view {
		position: relative;
		bottom: 0;
		left: 0;
		width: 100%;
		height: 238px;
		background-color: rgba(255, 255, 255, 1);
	}
</style>