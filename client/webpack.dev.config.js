/**
 * Created by namee on 2016. 6. 14..
 */
var debug = process.env.NODE_ENV !== 'production';
var path = require('path');
var webpack = require('webpack');
var HtmlWebpackPlugin = require('html-webpack-plugin');
var node_dir = __dirname + '/node_modules';

module.exports = {
    // context: __dirname + '/src',
    devtool: debug ? 'inline-source-map' : null,
    entry: './src/app.js',
    module: {
        loaders: [
            {
                test: /\.js?$/,
                exclude: /(node_modules|bower_components)/,
                loader: 'babel-loader',
                query: {
                    presets: ['react', 'es2015'],
                    // plugins: ['react-html-attrs', 'transform-class-properties', 'transform-decorators-legacy']
                }
            }
        ]
    },
    output: {
        path: __dirname + '/dist/',
        filename: 'bundle.js',
        sourceMapFilename: 'bundle.map',
        publicPath: 'http://localhost:3000/'
    },
    resolve: {
        alias: {
            'stompjs': node_dir + '/stompjs/lib/stomp.js',
        },
        modulesDirectories: [
            'src',
            'node_modules'
        ],
        extensions: ['', '.json', '.js', '.jsx']
    },
    plugins: debug? [
        new webpack.HotModuleReplacementPlugin(),
        new webpack.NoErrorsPlugin(),
        // new HtmlWebpackPlugin({
        //     title: 'Boot React',
        //     template: path.join(__dirname, 'assets/index-template.html')
        // })
    ] : [
        new webpack.optimaze.DedupePlugin(),
        new webpack.optimaze.OccurenceOrderPlugin(),
        new webpack.optimaze.UglifyJsPlugin({mangle:false, sourcemap:false})
    ],


}

