axios.interceptors.request.use(function (config) {
	if(config.loading){
		document.body.classList.add('loading-indicator');
	}
	
	const token = window.localStorage.token;
  	if (token) {
     	config.headers.Authorization = `token ${token}`
  	}
	return config
}, function (error) {
	return Promise.reject(error);
});

axios.interceptors.response.use(function (response) {
	if(response.config.loading){
		document.body.classList.remove('loading-indicator');
	}
	
  	return response;
}, function (error) {
	return Promise.reject(error);
});