/**
 * Created by namee on 2016. 6. 17..
 */
'use strict';

import React from 'react';
import {Render} from 'react-dom';
import axios from 'axios';
const stompClient = require('../../websocket-listener');
import TextField from 'material-ui/TextField';
import RaisedButton from 'material-ui/RaisedButton';

axios.defaults.withCredentials = true;

var Login = React.createClass({
    getInitialState: function() {
        return {
            username: 'user',
            password: 'password',
            result : 'tttt'
        }
    },

    componentDidMount : function() {
        stompClient.register([
            {route: '/topic/lovedise', callback: this.websocketTest},
        ]);
    },

    websocketTest: function(message) {
      console.log('websocket: ', message);
      this.setResult('websocket : ', message);
    },

    handleSubmit: function(e) {
        e.preventDefault();
        // this.replaceState({
        //     username : this.refs.username.value,
        //     password : this.refs.password.value
        // });

        console.log(this.state);
        // const username = React.findDOMNode(this.refs.username).value.trim();
        // const password = React.findDOMNode(this.refs.password).value.trim();


        this.login(this.state.username, this.state.password);
    },

    handleUsernameChange(e) {
        this.setState({username : e.target.value});
    },

    handlerPasswordChange(e) {
        this.setState({password : e.target.value});
    },

    getSso: function() {
        axios.get("http://localhost:8080/api/sso")
            .then(data => {
              console.log(data);
              this.setResult(data.data);
            })
            .catch(err => console.log(err));
    },

    setResult: function(result) {
      this.setState({result : JSON.stringify(result)});
    },

    login: function(username, password) {
        axios.post('http://localhost:8080/auth/signin', {
            username: username,
            password: password
        }).then(data => {
          console.log(data.data);
          this.setResult(data.data);
            axios.defaults.headers.common['X-AUTH-TOKEN'] = data.data.data.token;
        }).catch(err => console.log(err));
        console.log('username, password : ', username, password);
        // axios({
        //     url: 'http://localhost:8080/api/session',
        //     method: 'post',
        //     // headers: {'Content-Type': 'application/x-www-form-urlencoded'},
        //     params: {
        //         username: username,
        //         password: password
        //     },
        //     data: {
        //         username: username,
        //         password: password
        //     },
        //     // withCredentials: true
        // })
    },

    logout: function() {
        axios.delete('http://localhost:8080/api/session').then(data => {
          console.log(data);
          this.setResult(data.data);
        }).catch(err => console.log(err));
    },

    adminRoleCheck: function() {
      axios.get('http://localhost:8080/protected')
          .then(data => {
            console.log(data.data);
            this.setResult(data.data);
          }).catch(err => {
            console.log(err.data);
          })
    },

    websocket: function() {
        stompClient.send('/app/hello', {}, 'hi');
    },

    render: function() {
        const style = {
          margin: 12,
        };
        return (
            <div>
              <form onSubmit={this.handleSubmit}>
                  <TextField hintText="username" floatingLabelText="username" iref="username" type="text" onChange={this.handleUsernameChange}/>
                  <TextField hintText="password" floatingLabelText="password" ref="password" type="text" onChange={this.handlerPasswordChange}/>
                  <RaisedButton label="login" onClick={this.handleSubmit}/>
              </form>
              <br/>
              <RaisedButton label="adminRoleCheck" onClick={this.adminRoleCheck} style={style} primary={true}/>
              <RaisedButton label="logout" onClick={this.logout} style={style} primary={true}/>
              <RaisedButton label="sso" onClick={this.getSso} style={style} primary={true} />
              <RaisedButton label="websocket" onClick={this.websocket} style={style} primary={true}/>
              <h2 ref="result">{this.state.result}</h2>
            </div>
        )
    }
});

export default Login;
