export default {
	baseURL: process.env.NODE_ENV === 'development' ? '/api/api' : `${import.meta.env.VITE_BASIC_URL}/api`,
	tokenName: 'tokenshop',
	strongToken: "ShopToken",
	renderMenu: "RenderMenus",
	menu: "Menus",
	isDev: process.env.NODE_ENV === 'development' ? true : false,
	contentType: 'application/x-www-form-urlencoded;charset=UTF-8',
	requestTimeout: 50000,
	successCode: [200, 0, '200', '0'],
	statusName: 'code',
	messageName: 'msg',
	withCredentials: true,
	responseType: 'json',
};