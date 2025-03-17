/**
 * @description 字符串去除/ 拼接成字符串，用于导航name拼接
 * @param value
 * @returns {boolean}
 */
function strReplace(str) {
	let strList = Array.from(str);
	if (strList && strList.length > 0) {
		strList.forEach((v, idx) => {
			if (v == '/') {
				strList.splice(idx, 1);
			}
		});
		return strList.join('');
	}
	return '';
}
/**
 * @description 导航数据处理
 * @param value
 * @returns {boolean}
 */
function handMenu(data, arr) {
	if (data && data.length > 0) {
		data.forEach((v) => {
			v.meta = {
				title: v.name,
				topTree: v.path,
				keepAlive: false
			};
			v.name = strReplace(v.path);
			const {
				accessId,
				icon,
				isMenu,
				isRoute,
				isShow,
				meta,
				redirectName,
				path
			} = v;
			arr.push({
				accessId,
				icon,
				isMenu,
				isRoute,
				isShow,
				meta,
				path,
				redirectName,
			})
			handMenu(v.children, arr);
		})
	}
	return arr
}
/**
 * @description 加入路由表的数据
 * @returns
 */
export function handRouterTable(menus) {
	let menuList = [];
	if (menus && menus.length > 0) {
		menus.forEach((items, indexs) => {
			let list = [];
			items.meta = {
				title: items.name,
				topTree: items.path,
				keepAlive: false
			};
			items.name = strReplace(items.path);
			let topTree = items.path;
			const {
				accessId,
				icon,
				isMenu,
				isRoute,
				isShow,
				meta,
				redirectName,
				path
			} = items;
			list.push({
				accessId,
				icon,
				isRoute,
				isShow,
				meta,
				redirectName,
				path,
				topTree,
				isMenu,
			})
			if (items.children && items.children.length > 0) {
				items.children.forEach((v) => {
					v.meta = {
						title: v.name,
						topTree: v.path,
						keepAlive: false
					};
					v.name = strReplace(v.path);
					let topTree = v.path;
					const {
						accessId,
						icon,
						isMenu,
						isRoute,
						isShow,
						meta,
						redirectName,
						path
					} = v;
					list.push({
						accessId,
						icon,
						isMenu,
						isRoute,
						isShow,
						meta,
						redirectName,
						path,
						topTree,
					})
				})
				list = list.concat(handMenu(items.children, []));
			}
			menuList = menuList.concat(list)
			list = [];
		})
	}
	console.log('menus-----------', menuList)
	return menuList;
}
/**
 * @description 显示导航数据处理
 * @param value
 * @returns {boolean}
 */
export function handMenuData(data) {
	let showMenuData = [];
	if (data && data.length > 0) {
		let childrenList = [];
		data.forEach((v, idx) => {
			const {
				name,
				path,
				redirectName,
				app_id,
				alias,
				isMenu,
				icon
			} = v;
			showMenuData.push({
				name,
				path,
				redirectName,
				app_id,
				alias,
				isMenu,
				icon,
			})
			if (v.children && v.children.length > 0) {
				v.children.forEach((item) => {
					const {
						name,
						path,
						redirectName,
						app_id,
						alias,
						icon,
						isMenu,
					} = item;
					/* 处理children */
					let {
						children
					} = item;
					let childrens = [];
					children.forEach(citem => {
						let cchildrenList = [].concat(getChildrenList(citem.children, []));
						cchildrenList.push(citem.path)
						childrens.push({
							name: citem.name,
							path: citem.path,
							redirectName: citem.redirectName,
							app_id: citem.app_id,
							alias: citem.alias,
							icon: citem.icon,
							isMenu: citem.isMenu,
							childrenList: cchildrenList
						})
					})

					/* 处理children结束 */
					showMenuData[idx].children = showMenuData[idx].children || [];
					showMenuData[idx].children.push({
						name,
						path,
						redirectName,
						app_id,
						alias,
						isMenu,
						icon,
						children: childrens
					})
					childrenList = [].concat(getChildrenList(v.children, []));
				})
			}
			childrenList.push(v.path)
			showMenuData[idx].childrenList = childrenList;
		});
	}
	return showMenuData;
}

function getChildrenList(data, arr) {
	data.forEach((v) => {
		arr.push(v.path)
		if (v.children && v.children.length > 0) {
			getChildrenList(v.children, arr);
		}
	})
	return arr
}