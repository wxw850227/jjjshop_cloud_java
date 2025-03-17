import request from '@/utils/request'

let UserApi = {

  /*用户登录*/
  login (data, errorback) {
    return request._postBody('/shop/passport/login', data, errorback);
  },
  saasLogin (data, errorback) {
    return request._post('/shop/passport/saasLogin', data, errorback);
  },
  /*退出登录*/
  loginOut (data, errorback) {
    return request._post('/shop/passport/logout', data, errorback);
  },
  /*添加用户*/
  adduser (data, errorback) {
    return request._post('/shop/user/user/add', data, errorback);
  },
  /*修改用户*/
  edituser (data, errorback) {
    return request._postBody('/shop/user/user/edit', data, errorback);
  },
  /*充值*/
  userRecharge (data, errorback) {
    return request._postBody('/shop/user/user/recharge', data, errorback);
  },
  /*删除用户*/
  deleteuser (data, errorback) {
    return request._post('/shop/user/user/delete', data, errorback);
  },
  /*用户列表*/
  userlist (data, errorback) {
    return request._postBody('/shop/user/user/index', data, errorback);
  },
  /*等级列表*/
  gradelist (data, errorback) {
    return request._postBody('/shop/user/grade/index', data, errorback);
  },
  /*等级列表*/
  gradelog (data, errorback) {
    return request._postBody('/shop/user/grade/log', data, errorback);
  },
  /*添加等级*/
  addgrade (data, errorback) {
    return request._postBody('/shop/user/grade/add', data, errorback);
  },
  /*修改等级*/
  editGrade (data, errorback) {
    return request._postBody('/shop/user/grade/edit', data, errorback);
  },
  /*删除等级*/
  deletegrade (data, errorback) {
    return request._post('/shop/user/grade/delete', data, errorback);
  },
  /*用户余额*/
  BalanceLog (data, errorback) {
    return request._postBody('/shop/user/balance/log', data, errorback);
  },
  /*修改密码*/
  EditPass (data, errorback) {
    return request._post('/shop/passport/editPass', data, errorback);
  },
  /*用户标签*/
  toEditTag (data, errorback) {
    return request._get('/shop/user/user/toEditTag', data, errorback);
  },
  /*用户标签*/
  editTag (data, errorback) {
    return request._postBody('/shop/user/user/editTag', data, errorback);
  },
}

export default UserApi;
