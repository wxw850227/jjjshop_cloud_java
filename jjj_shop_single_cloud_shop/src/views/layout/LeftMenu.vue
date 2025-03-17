<template>
	<div class="left-menu-wrapper" v-if="active_menu != null && menuList[active_menu]">
		<!--主菜单-->
		<div class="menu-wrapper">
			<div class="nav-wrapper">
				<div class="first-menu-content">
					<el-menu class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose"
						active-text-color="#333">
						<template v-for="(item, index) in menuList[active_menu]['children']" :key="index">
							<el-sub-menu class="menu-item" :index="index + ''"
								v-if="item.isMenu == 1 && hasChildren(item)">
								<template #title>
									<span>{{ item.name }}</span>
								</template>
								<template v-for="(mitem, mindex) in item.children" :key="mindex">
									<el-menu-item-group v-if="mitem.isMenu == 1">
										<el-menu-item class="routre-link routre-link-group"
											:class="{'router-link-active': active_child3 == mindex}"
											:index="index + '-' + mindex"
											@click="choseMenu(4, mitem, mindex)">{{ mitem.name }}</el-menu-item>
									</el-menu-item-group>
								</template>
							</el-sub-menu>
							<el-menu-item class="menu-item" :class="{'router-link-active': active_child == index}"
								v-if="item.isMenu == 1 && !hasChildren(item)" @click="choseMenu(3, item, index)"
								:index="index + ''">{{ item.name }}</el-menu-item>
						</template>
					</el-menu>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
	import AuthApi from "@/api/auth.js";
	import logo1 from "/src/assets/img/logo2.png";
	import {
		useUserStore
	} from "@/store";
	import {
		reactive,
		toRefs,
		defineComponent,
		nextTick,
		watchEffect
	} from "vue";
	import {
		useRoute
	} from "vue-router";
	export default defineComponent({
		components: {},
		setup(props, {
			emit
		}) {
			const {
				userInfo,
				bus_emit,
				menus,
				renderMenus
			} = useUserStore();
			const route = useRoute();
			const state = reactive({
				route,
				/*传到顶部的标题*/
				munu_name: "首页",
				/*选中的菜单*/
				active_menu: null,
				/*子菜单选择*/
				active_child: 0,
				active_child3: 0,
				/*菜单数据*/
				menuList: renderMenus,
				/*商城名称*/
				shop_name: "",
				menus,
				hoverMenu: null,
				logo1: logo1,
				menuActiveState: {},
			});
			/*菜单*/
			var selectMenu = (to) => {
				let menupath = to.path;
				let active_menu = null;
				let active_child = null;
				let active_child3 = null;

				if (state.menuList && state.menuList.length > 0) {
					for (let i = 0; i < state.menuList.length; i++) {
						// 顶级菜单匹配
						if (state.menuList[i].path === menupath) {
							active_menu = i;
							break;
						}
						if (
							state.menuList[i].children &&
							state.menuList[i].children.length > 0
						) {
							for (let j = 0; j < state.menuList[i].children.length; j++) {
								// 二级菜单匹配
								if (state.menuList[i].children[j].path === menupath) {
									active_menu = i;
									active_child = j;
									break;
								}

								if (
									state.menuList[i].children[j].children &&
									state.menuList[i].children[j].children.length > 0
								) {
									for (
										let k = 0; k < state.menuList[i].children[j].children.length; k++
									) {
										if (
											state.menuList[i].children[j].children[k].path === menupath
										) {
											active_menu = i;
											active_child = j;
											active_child3 = k;
											break;
										}
									}
								}
							}
						}
						if (
							active_menu === null &&
							state.menuList[i].childrenList &&
							state.menuList[i].childrenList.includes(menupath)
						) {
							active_menu = i;
						}
					}
				}
				state.active_menu = active_menu;
				state.active_child = active_child;
				state.active_child3 = active_child3;

				// 更新菜单标题
				bus_emit("MenuName", (to.meta && to.meta.showMenuTitle) || to.meta.title);
				emit("selectMenu", active_menu);
			};
			// selectMenu(route);
			watchEffect(() => {
				selectMenu(route);
			});
			return {
				...toRefs(state),
				userInfo,
				selectMenu,
				bus_emit,
			};
		},
		beforeRouteUpdate(to, from, next) {
			this.selectMenu(to);
			next();
		},
		mounted() {
			this.getUserInfo();
			this.selectMenu(this.$route);
			// this.$nextTick(() => {

			// });
		},
		methods: {
			hasChildren(e) {
				return e.children && e.children.some((item) => item.isMenu);
			},
			handleOpen(key, keyPath) {
				console.log(key, keyPath);
			},
			handleClose(key, keyPath) {
				console.log(key, keyPath);
			},
			firstMenu(e) {
				let routeList = e.childrenList;
				let path = useRoute().path;
				return routeList.indexOf(path) != -1;
			},
			secondaryMenu(e) {
				let routeList = e.childrenList;
				let path = useRoute().path;
				return routeList.indexOf(path) != -1;
			},
			getUserInfo() {
				let self = this;
				AuthApi.getUserInfo({})
					.then((res) => {
						const {
							data
						} = res;
						self.shop_name = data.shop_name;
					})
					.catch((error) => {
						self.loading = false;
					});
			},
			/*点击菜单跳转*/
			choseMenu(type, item, index) {
				let path = item.path;
				if (item.redirectName) {
					path = item.redirectName;
				}
				if (type == 3) {
					this.active_menu = this.menuList.findIndex((menu) =>
						menu.childrenList.includes(item.path)
					);
					this.active_child = index;
					this.$router.push(path);
				} else if (type == 4) {
					const parentIndex = this.menuList.findIndex((menu) =>
						menu.childrenList.includes(item.path)
					);
					this.active_menu = parentIndex;
					// this.active_child = parentIndex;
					this.active_child3 = index;
					this.$router.push(path);
				}
			},
		},
	});
</script>
<style lang="scss" scoped>
	.router-link-active :deep(.el-sub-menu__title) {
		background: var(--el-color-primary);
		color: #ffffff;
	}

	:deep(.el-menu-item-group__title) {
		padding: 0;
	}

	:deep(.el-sub-menu__title) {
		padding-left: 10px !important;
		padding-right: 10px !important;
		height: auto;
		min-height: var(--el-menu-item-height);
		// color: #fff !important;

		// text-align: center;
		.el-icon svg {
			height: 1em;
			width: 1em;
		}

		.el-sub-menu__icon-arrow {
			right: 10px;
		}
	}

	.router-link-active:has(.routre-link-group) {
		background: #fff;
		color: #333;
	}

	.router-link-active {
		background: var(--el-color-primary);
		color: #ffffff;
	}

	.name.icon {
		font-size: 20px;
	}

	.menu-item-icon.icon.iconfont {
		font-size: 20px;
	}

	.menu-item .item-box {
		display: flex;
		flex-direction: column;
		width: 100px;
		height: 48px;
	}

	.item-box1 {
		display: flex;
		cursor: pointer;
		flex-direction: column;
		height: 48px;
		margin-top: 56px;
		margin-bottom: 20px;
	}

	.iconname1 {
		font-weight: 400;
		font-size: 12px;
		color: #333333;
		line-height: 10px;
		text-align: center;
	}

	.imgspan {
		display: inline-block;
		font-weight: normal;
		width: 100px;
		margin-top: 4px;
		// height: 48px;
		text-align: center;

		.iconfont {
			font-size: 20px;
		}
	}

	.iconname {
		font-weight: 400;
		font-size: 12px;
		color: #333333;
		margin-top: -33px;
		text-align: center;
	}
</style>