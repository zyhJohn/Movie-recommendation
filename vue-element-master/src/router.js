// the config of app router

import App from './App.vue';
import login from './components/login/login.vue';
import forget from './components/login/forget.vue';
import register from './components/login/register.vue';
import movie from './components/movie/movie.vue';
import want from './components/want/want.vue';
import ratings from './components/ratings/ratings.vue';
import user from './components/user/user.vue';
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
				name: '电影管理',
				path: 'movie',
				component: movie
			},
			{
				name: '用户管理',
				path: 'user',
				component: user
			},
			{
				name: '评分管理',
				path: 'want',
				component: want
			},
			{
				name: '收藏管理',
				path: 'ratings',
				component: ratings
			},
		]
	},{
		path: '*',
		redirect: '/login'
	},{
		path: '/forget',
		component: forget,
	},{
		path: '/register',
		component: register,
	}
]
