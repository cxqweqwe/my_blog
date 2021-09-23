module.exports = {
	lintOnSave: false,
	configureWebpack: {
		resolve: {
			alias: {
				'assets': '@/assets',
				'common': '@/common',
				'components': '@/components',
				'network': '@/network',
				'views': '@/views',
				'router': '@/router',
				'store': '@/store'
			}
		},
	},
	chainWebpack: config => {
		config.module
			.rule('md')
			.test(/\.md$/)
			.use('html-loader')
			.loader('html-loader')
			.end()
			.use('markdown-loader')
			.loader('markdown-loader')
			.end()
	}
};


// const path = require('path');
//
// function resolve(dir) {
// 	return path.join(__dirname, dir);
// }
//
// module.exports = {
// 	lintOnSave: false,
// 	chainWebpack: (config) => {
// 		config.resolve.alias
// 			.set('@', resolve('src'))
// 			.set('@assets',resolve('src/assets'))
// 			.set('@components',resolve('src/components'))
// 		// 这里只写了两个个，你可以自己再加，按这种格式.set('', resolve(''))
// 	}
// };
