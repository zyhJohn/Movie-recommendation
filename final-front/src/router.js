// the config of app router

import App from './App.vue';
import login from './components/login/login.vue';
import forget from './components/login/forget.vue';
import register from './components/login/register.vue';
import movie from './components/movie/movie.vue';
import detail from './components/detail/detail.vue';
import recommend from './components/recommend/recommend.vue';
import main from './components/main.vue'

export default [{
		path: '/login',
		component: login,
	},{
		path: '/index',
		component: App,
		children: [
			{
				name: '',
				path: 'main',
				component: main
			},
			{
				name: '电影列表',
				path: 'movie',
				component: movie
			},
			{
				name: '电影推荐',
				path: 'recommend',
				component: recommend
			},
			{
				name: '详情',
				path: 'detail',
				component: detail
			},
		]
	},{
		path: '*',
		redirect: '/index'
	},{
		path: '/forget',
		component: forget,
	},{
		path: '/register',
		component: register,
	}
]
