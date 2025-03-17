<template>
	<div class="common-header-box">
		<div class="menu-logo d-c d-c-c">
			<el-image :src="logo1" style="width: 39px; height: 28px; object-fit: cover">
			</el-image>
		</div>
		<div class="common-header">
			<div class="breadcrumb">
				<!--一般的标题显示-->
				<div class="baseInfo-left-base home-btns flex-1 d-s-c">
					<div @click="choseMenu(1)" class="mr20 home-btn" :class="{ active: firstMenu('') }">
						首页
					</div>
					<template v-for="(item, index) in menuList" :key="index">
						<div @click="choseMenu(2, item, index)" class="mr20 home-btn"
							:class="{ active: firstMenu(item) }" v-if="item.isMenu == 1">
							{{ item.name }}
						</div>
					</template>
				</div>
				<div class="header-navbar">
					<!-- 鼠标移上去时显示下拉菜单 -->
					<div class="header-navbar-icon">
						<span class="text" style="color: white">当前版本：{{ userInfo.version }}</span>
					</div>
					<el-icon class="head-icon mr10" @click="launchFullScreen">
						<FullScreen />
					</el-icon>
					<el-icon class="head-icon mr10" :size="18" @click="refreshView">
						<RefreshRight />
					</el-icon>
					<!-- <span
            class="head-icon iconfont icon-shuaxin"
           
          ></span> -->

					<div class="header-navbar-icon user-dropdown">
						<span class="iconfont icon-guanliyuan head-icon"></span>
						<span class="text ml4" style="color: white">{{
              userInfo.userName
            }}</span>
						<el-icon>
							<ArrowDown />
						</el-icon>
						<!-- 下拉菜单 -->
						<div class="dropdown-menu">
							<div class="header-navbar-icon" @click="passwordFunc()">
								<span class="iconfont icon-xiugaimima"></span>
								<span class="text">修改密码</span>
							</div>
							<div class="header-navbar-icon" @click="exit()">
								<span class="iconfont icon-tuichu1"></span>
								<span class="text ml4">退出登录</span>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!--修改密码-->
			<UpdatePassword v-if="is_password" @close="closeFunc"></UpdatePassword>
		</div>
		<el-tabs v-if="editableTabs() != ''" v-model="activeName" type="card" closable class="head-tabs" @edit="handleTabsEdit"
			@tab-click="handleClick">
			<el-tab-pane v-for="item in editableTabs()" :key="item.path" :label="item.name"
				:name="item.path"></el-tab-pane>
		</el-tabs>
	</div>
</template>

<script>
	import {
		reactive,
		toRefs,
		defineComponent,
		onBeforeUnmount,
		ref,
		watch,
	} from "vue";
	import {
		useUserStore
	} from "@/store";
	import {
		useRouter,
		useRoute
	} from "vue-router";
	import UpdatePassword from "./part/UpdatePassword.vue";
	import logo1 from "/src/assets/img/logo1.png";
	export default defineComponent({
		components: {
			UpdatePassword,
		},
		setup(props, {
			emit
		}) {
			const router = useRouter();
			const route = useRoute();
			console.log(route);
			const currentPath = ref(route.path);
			const {
				userInfo,
				bus_on,
				bus_emit,
				bus_off,
				afterLogout,
				renderMenus
			} =
			useUserStore();
			const editableTabs = function() {
				const {
					historyMenus
				} = useUserStore();
				console.log(historyMenus)
				return historyMenus || [];
			};
			const state = reactive({
				fullscreen: false,
				/*菜单名称*/
				menu_title: "菜单",
				/*切换菜单*/
				tabList: [],
				/*切换选中*/
				activeValue: 0,
				edit_log: false,
				/*是否修改密码*/
				is_password: false,
				/*tab切换类别*/
				tab_type: "",
				activeName: route.fullPath,
				/*菜单数据*/
				menuList: renderMenus,
				/*选中的菜单*/
				active_menu: null,
				/*子菜单选择*/
				active_child: 0,
				logo1,
			});
			watch(
				() => route.fullPath,
				(newPath, oldPath) => {
					if (newPath != oldPath && editableTabs()) {
						if (editableTabs().find((item) => item.path === newPath)) {
							emit("refresh");
						}
					}
					currentPath.value = newPath;
					state.activeName = newPath;
				}, {
					immediate: true,
				}
			);
			bus_on("MenuName", (res) => {
				state.menu_title = res;
			});
			bus_on("tabData", (res) => {
				state.tabList = res.list;
				state.activeValue = res.active;
				state.tab_type = res.tab_type;
			});
			bus_on("activeValue", (res) => {
				state.activeValue = res;
			});
			bus_on("noTarget", (res) => {
				state.activeValue = res;
			});
			//发送给其它组件头部加载完成
			bus_emit("headLoad", true);
			onBeforeUnmount(() => {
				bus_off("menuName");
				bus_off("tabData");
			});
			return {
				...toRefs(state),
				userInfo,
				afterLogout,
				router,
				bus_emit,
				editableTabs,
			};
		},
		methods: {
			refreshView() {
				this.$emit("refresh");
			},
			//开启全屏
			launchFullScreen() {
				this.fullscreen = !this.fullscreen;
				let element = document.documentElement;
				if (this.fullscreen) {
					if (element.requestFullscreen) {
						element.requestFullscreen();
					} else if (element.mozRequestFullScreen) {
						element.mozRequestFullScreen();
					} else if (element.webkitRequestFullscreen) {
						element.webkitRequestFullscreen();
					} else if (element.msRequestFullscreen) {
						element.msRequestFullscreen();
					}
				} else {
					if (document.exitFullscreen) {
						document.exitFullscreen();
					} else if (document.mozExitFullScreen) {
						document.mozExitFullScreen();
					} else if (document.webkitExitFullscreen) {
						document.webkitExitFullscreen();
					}
				}
			},
			/*点击菜单跳转*/
			choseMenu(type, item, index) {
				if (type == 1) {
					this.active_menu = null;
					this.active_child = null;
					this.$router.push("/");
					this.$emit("selectMenu", null);
				} else if (type == 2) {
					this.active_menu = index;
					this.active_child = 0;
					if (item.childrenList.includes(item.redirectName)) {
						this.$router.push(item.redirectName);
					} else {
						this.$router.push(item.childrenList[0]);
					}
					// this.$router.push(item.redirectName);
					if (item.children) {
						this.$emit("selectMenu", false);
					}
				}
			},
			firstMenu(e) {
				let path = useRoute().path;
				if (!e) {
					return path == "/home";
				} else {
					let routeList = e.childrenList || [];
					return routeList.indexOf(path) != -1;
				}
			},
			update() {
				this.edit_log = true;
			},
			closeFunc1() {
				this.edit_log = false;
			},
			syncOrders() {},
			refreshPage() {
				this.$router.go(0);
			},
			handleClick(tab, event) {
				// console.log(tab)
				// console.log(tab.paneName)
				// 假设 tab.paneName 是 'some-path?param1=value1&param2=value2'
				const [path, search] = tab.paneName.split("?");

				// 如果没有查询参数，search 将是 undefined
				const query = search ?
					search.split("&").reduce((acc, param) => {
						const [key, value] = param.split("=");
						acc[key] = value;
						return acc;
					}, {}) : {};
				this.$router.push({
					path: path,
					query: query,
				});
			},
			handleTabsEdit(targetName, action) {
				let list = this.editableTabs();
				let n = list.findIndex((item) => item.path === targetName);
				const {
					delHistory
				} = useUserStore();
				let url = delHistory(n);
				this.$router.push({
					path: url,
				});
			},
			isSelect(e) {
				let path = useRoute().path;
				return path == e;
			},
			/*点击菜单跳转*/
			gotoPage(path) {
				this.$router.push(path);
			},
			exit() {
				ElMessageBox.confirm("此操作将退出登录, 是否继续?", "提示", {
						confirmButtonText: "确定",
						cancelButtonText: "取消",
						type: "warning",
					})
					.then(() => {
						this.logout();
						localStorage.removeItem("rememberLogin");
					})
					.catch(() => {
						ElMessage({
							type: "info",
							message: "已取消退出",
						});
					});
			},
			async logout() {
				await this.afterLogout();
				this.router.push("/login");
			},
			/*修改密码*/
			passwordFunc() {
				this.is_password = true;
			},

			/*关闭修改密码*/
			closeFunc() {
				this.is_password = false;
			},
		},
	});
</script>

<style lang="scss" scoped>
	.right-big .common-header {
		padding-left: 100px;
	}

	.right-big .menu-logo {
		left: 0;
	}

	.menu-logo {
		height: 50px;
		background-color: var(--color-primary-head-bg);
		position: absolute;
		left: -100px;
		z-index: 102;
		width: 100px;

		.home-login {
			.home-icon {
				.item-box1 {
					width: 100px;
					height: 58px;
					background: #ebefff;
				}

				.item-box1 .imgspan {
					color: #3b64fc;
				}

				.item-box1 .iconname1 {
					color: var(--el-color-primary);
				}
			}
		}
	}

	.user-dropdown {
		position: relative;
		cursor: pointer;

		&:hover .dropdown-menu {
			display: block;
			opacity: 1;
			transform: translateY(0);
		}
	}

	.dropdown-menu {
		display: none;
		opacity: 0;
		transform: translateY(-10px);
		position: absolute;
		top: 100%;
		left: 0;
		width: 100px;
		background-color: #fff;
		/* 白色背景 */
		border-radius: 8px;
		/* 圆角边框 */
		padding: 5px;
		/* 内边距 */
		box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
		/* 增加阴影效果 */
		border: none;
		/* 去掉边框 */
		z-index: 10;
		transition: opacity 0.3s ease, transform 0.3s ease;
		/* 添加过渡效果 */

		.header-navbar-icon {
			font-size: 12px;
			/* 调整字体大小 */
			color: #333;
			/* 字体颜色 */
			width: 100%;
			white-space: nowrap;
			/* 禁止文本换行 */
			cursor: pointer;

			&:hover {
				background-color: #ebefff;
				/* 鼠标悬停时的背景颜色 */
			}

			.icon {
				margin-right: 8px;
				/* 图标和文字之间的间距 */
				font-size: 12px;
				/* 调整图标大小 */
				color: #409eff;
				/* 图标颜色 */
			}
		}
	}

	.hoverable-option {
		cursor: pointer;
		margin-right: 4px;
		color: rgba(0, 0, 0, 0.87);
		font-size: 12px;
		font-weight: 500;
		display: inline-flex;
		align-items: center;

		&:hover {
			color: #409eff;
			text-decoration: underline;
		}

		el-icon {
			display: inline-flex;
			align-items: center;
			margin-right: 4px;

			&:hover {
				transform: scale(1.1);
				transition: transform 0.2s ease-in-out;
			}
		}
	}

	.home-btns {
		overflow-x: auto;

		.home-btn {
			cursor: pointer;
		}

		.home-btn.active {
			background-color: var(--color-primary-btn-bg);
			color: #fff;
		}
	}

	.top-menu-list {
		height: 50px;
		display: flex;
		justify-content: flex-start;
		align-items: center;

		.top-menu-item {
			display: flex;
			justify-content: center;
			align-items: center;
			padding: 0 12px;
			position: relative;

			.el-icon {
				font-size: 14px;
				margin-left: 8px;
			}
		}

		.top-menu-item::after {
			content: "";
			position: absolute;
			top: 0;
			bottom: 0;
			margin: auto;
			left: 1px;
			width: 1px;
			height: 16px;
			background-color: rgba(153, 153, 153, 1);
		}

		.top-menu-item:hover::after,
		.top-menu-item.active::after {
			width: 0;
		}

		.top-menu-item.active,
		.top-menu-item:hover {
			background-color: rgba(16, 16, 16, 0.05);
		}
	}

	.common-header .el-tabs__nav-wrap::after {
		display: none;
	}

	.common-header .el-tabs-container {
		margin-left: 20px;
		padding-left: 20px;
		border-left: 1px solid #eeeeee;
	}

	.common-header .el-tabs__header {
		margin-bottom: 0;
	}

	.login-out .icon-tuichu {
		color: #999;
		font-size: 18px;
	}

	.header-navbar-icon .icon-guanliyuan {
		font-size: 18px;
		color: #fff;
	}

	.icon-biaoqiankuozhan_shouye-146 {
		font-size: 20px;
		color: #333;
	}

	.common-header-box {
		position: relative;
	}

	.common-header-box .head-tabs {
		background-color: #fff;
		position: relative;
		/* 预留出足够的空间给右侧按钮 */
	}

	.common-header-box .extra-options {
		position: absolute;
		right: 10px;
		/* 固定在右侧 */
		top: 80%;
		/* 垂直居中 */
		transform: translateY(-50%);
		display: flex;
		gap: 6px;
	}

	.common-header-box .el-tabs__nav {
		margin-right: 150px;
		/* 确保标签不会覆盖按钮 */
	}

	.common-header-box .el-tabs__header {
		position: relative;
		z-index: 1;
		/* 确保标签显示在按钮下面 */
	}

	.common-header-box .head-tabs {
		:deep(.el-tabs__header) {
			height: 34px;
			margin: 0;
			padding-right: 80px;
			border-top: 1px solid #eee;
		}

		:deep(.el-tabs__header .el-tabs__item .is-icon-close) {
			width: 14px;
		}

		:deep(.el-tabs__nav) {
			border-top: none;
			border-bottom: none;
			border-radius: 0;
		}

		:deep(.el-tabs__item) {
			height: 32px;
			line-height: 32px;
			border-bottom: none;
		}
	}

	.head-icon {
		color: #fff;
		cursor: pointer;
		margin-left: 7px;
		font-size: 14px;
	}

	:deep(.el-tabs--card > .el-tabs__header .el-tabs__item) {
		border: none;
		border-radius: 10px 10px 0 0;
		font-weight: 400 !important;
	}

	:deep(.el-tabs--card > .el-tabs__header .el-tabs__nav) {
		border: none;
	}

	:deep(.el-tabs__item.is-active),
	:deep(.el-tabs__item:hover) {
		background-color: var(--el-color-primary-light-3);
		color: #333;
	}

	:deep(.el-tabs--card > .el-tabs__header) {
		border-bottom: none;
	}
</style>