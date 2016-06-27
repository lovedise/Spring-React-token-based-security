/**
 * Created by namee on 2016. 6. 14..
 */
'use strict';
import React from 'react';
import ReactDOM from 'react-dom';
import axios from 'axios';
import Login from 'ui/components/login';
import Main from 'ui/containers/Main';
import injectTapEventPlugin from 'react-tap-event-plugin';

injectTapEventPlugin();

// var React = require('react');
// var ReactDOM = require('react-dom');
// var axios = require('axios');
// var Login = require('ui/components/login');

// class App extends React.Component{
//     constructor() {
//         super();
//         axios.post('http://localhost:8080/sso', {
//             companyCode: 'test',
//             hashType: 'md5'
//         }
//         ).then(res => console.log(res)
//         ).catch(err => console.log(err))
//
//         // axios.get('http://localhost:8080/sso'
//         // ).then(res => console.log(res)
//         // ).catch(err => console.log(err))
//     }
//
//     test() {
//         return 'a';
//     }
//
//     render() {
//         return (
//             <div>
//             <b>tttt</b>
//             </div>
//         )
//     }
// }

ReactDOM.render(<Main />, document.getElementById('root'));
