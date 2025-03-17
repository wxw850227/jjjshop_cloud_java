let url = 'http://192.168.31.226:8893';
if (process.env.NODE_ENV != 'development') {
	url = '/api';
}
export default {
	url
}