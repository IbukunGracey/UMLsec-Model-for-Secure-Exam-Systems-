var socket = io.connect('http://localhost:4040');
socket.emit('saveName', {
    handle: $.chat.username
});

if(localStorage.getItem('chatLogs') === null) {
    var chatLogs = '';
    localStorage.setItem('chatLogs', '');
} else {
    var chatLogs = localStorage.getItem('chatLogs');
    $('.direct-chat-messages').append(chatLogs);
}

$('body').on('submit', 'form#sendMessage', function(e) {
    e.preventDefault();
    var msg = $('form#sendMessage input[name=message]').val().trim();
    if(msg.length) {
        var posted_by = 'Toheeb';
        var date = new Date();
        var _a = 'am';
		var hour = date.getHours();
        if(hour > 12) {
            hour = hour-12;
            _a = 'pm';
        }
		var min = date.getMinutes();
		if(min.length < 2) {
			min = '0'+min;
		}
        var day = date.getDate();
		
		var monthNames = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'];
		
        var month = monthNames[date.getMonth()];
        var yy = date.getFullYear();
        var _date = month+' '+day+', '+yy+' '+hour+':'+min+' '+_a;
        var _content = '<div class="direct-chat-msg">\
                                <div class="direct-chat-info clearfix">\
                                    <span class="direct-chat-name pull-left">'+$.chat.username+'</span>\
                                    <span class="direct-chat-timestamp pull-right">'+_date+'</span>\
                                </div>\
                                <div class="direct-chat-text">\
                                    '+msg+'\
                                </div>\
                            </div>';
        var _content2 = '<div class="direct-chat-msg right">\
                                <div class="direct-chat-info clearfix">\
                                    <span class="direct-chat-name pull-right">'+$.chat.username+'</span>\
                                    <span class="direct-chat-timestamp pull-left">'+_date+'</span>\
                                </div>\
                                <div class="direct-chat-text">\
                                    '+msg+'\
                                </div>\
                            </div>';
        $('.direct-chat-messages').append(_content2);
        chatLogs += _content2
        localStorage.setItem('chatLogs', chatLogs);
        $('div.direct-chat-messages').animate({ scrollTop: $('div.direct-chat-messages').prop("scrollHeight")}, 1000);
   
        socket.emit('chatMsg', {
            message: _content,
            receiverHandle: $.chat.receiverHandle,
            handle: $.chat.username
        });
        $('form#sendMessage input[name="message"]').val('');
    }
});

$('form#sendMessage input[name="message"]').on('keypress', function() {
    socket.emit('typing', {
        receiverHandle: $.chat.receiverHandle,
        handle: $.chat.username
    });
});

$('form#sendMessage input[name="message"]').on('blur', function() {
    socket.emit('notyping', {
        receiverHandle: $.chat.receiverHandle,
        handle: $.chat.username
    });
});

socket.on('myid', function(data) {
    $('#output').append('<p><strong> Your chat ID: '+data.id+'</strong>')
});

socket.on('chatMsg', function(data) {
    $('small').empty();
    $('.direct-chat-messages').append(data.message);
    chatLogs += data.message;
    localStorage.setItem('chatLogs', chatLogs);
    $('div.direct-chat-messages').animate({ scrollTop: $('div.direct-chat-messages').prop("scrollHeight")}, 1000);
});

socket.on('typing', function(data) {
    $('small').html('<i>'+data.handle+' is typing...</i>');
});

socket.on('notyping', function(data) {
    $('small').empty();
});