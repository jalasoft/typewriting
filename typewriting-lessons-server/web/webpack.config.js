const path = require('path')
const CopyPlugin = require('copy-webpack-plugin')
const MiniCssExtractPlugin = require('mini-css-extract-plugin')

module.exports = {
    entry: {
        logic: "./src/ts/main.ts",
        styles: './src/sass/main.scss'
    },
    output: {
        path: path.resolve(__dirname, "dist") 
    },
    plugins: [
        new CopyPlugin({
            patterns: [
                { from: 'src/index.html', to: '.', }
            ]
        }),
        new MiniCssExtractPlugin({
            filename: '[name].css'
        })
    ],
    module: {
        rules: [
            {
                test: /\.ts$/,
                use: 'ts-loader'
            },
            {
                test: /\.scss$/,
                use: [
                    MiniCssExtractPlugin.loader,
                    'css-loader',
                    'sass-loader'
                ]
            }
        ]
    },
    resolve: {
        extensions: ['ts', ".wasm", ".ts", ".tsx", ".mjs", ".cjs", ".js", ".json"],
    },
    devServer: {
        contentBase: path.join(__dirname, "dist"),
        port: 8855,
        writeToDisk: true
    }
}