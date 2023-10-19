var express = require('express');
var socket = require('socket.io');

var app = express();
var server = app.listen(process.env.PORT || 4040, function() {
    console.log('Server listening on port 4040');
});

app.use(express.static('public'));

var io = socket(server);
var connections = [];
var users = {}

io.on('connection', function(socket) {
    connections.push(socket);
    console.log('User connected. Total users: ', connections.length, socket.id);
    
    socket.on("disconnect",function(){
        console.log(users[socket.id] + ' disconnected. Total users: ', connections.length);
    });
        
    socket.on('saveName', function(data) {
        users[data.handle] = socket.id; // We are using room of socket io
        console.log(users);
    });
    
    socket.on('chatMsg', function(data) {
        console.log('chat msg received:', users[data.receiverHandle]);
        io.to(users[data.receiverHandle]).emit('chatMsg', data);
    });
    socket.on('typing', function(data) {
        io.to(users[data.receiverHandle]).emit('typing', data);
    });
    socket.on('notyping', function(data) {
        io.to(users[data.receiverHandle]).emit('notyping');
    });
});