'use strict';

var SockJS = require('sockjs-client'); // <1>

// var sock = new SockJS('http://localhost:8080/payroll');
// sock.onopen = function() {
// 	console.log('socket open');
// };
//
// sock.onclose = function () {
// 	console.log('socket close');
// }
//
// sock.onmessage = function(e){
// 	console.log('socket message', e);
// }
require('stompjs'); // <2>

var socket = SockJS('http://localhost:8080/websocket'); // <3>
var stompClient = Stomp.over(socket);

function register(registrations) {
	stompClient.connect({}, function(frame) {
		registrations.forEach(function (registration) { // <4>
			stompClient.subscribe(registration.route, registration.callback);
		});
	});
}

function send(topic, type, message) {
    stompClient.send(topic, type, message);
}


module.exports.register = register;
module.exports.send = send;
