/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * 
 * 
 * 
 */
$(document).ready(function () {
    $('[data-toggle=tooltip]').tooltip();
});
(function ($) {
    $(document).ready(function () {
        var url = 'ws://' + window.location.host + '/CR_WB_WebPage/chat';
        var ws = new WebSocket(url);
        var $send_btn = $('#btn_chat'), $msg_display = $('#msg'), $username = $('#user_name');
        var $chatbox = $('.chatbox'),
                $chatboxTitle = $('.chatbox__title'),
                $chatboxTitleClose = $('.chatbox__title__close'),
                $chatboxCredentials = $('.chatbox__credentials');
        var $message = $('#msg_input');
        $chatboxTitle.on('click', function () {
            $chatbox.toggleClass('chatbox--tray');
        });

        $chatboxTitleClose.on('click', function (e) {
            e.stopPropagation();
            $chatbox.addClass('chatbox--closed');
        });
        $chatbox.on('transitionend', function () {
            if ($chatbox.hasClass('chatbox--closed'))
                $chatbox.remove();
        });

        ws.onopen = function () {
            console.log('Connected Client');
        };
        ws.onclose = function () {
            console.log('Disconected Client');
        };
        ws.onmessage = function ()
        {
            var obj = JSON.parse(event.data);
            let msg = obj.name + ', ' + obj.date + ': ' + obj.text;
            if (obj.name === $username.val())
            {
                $msg_display.html($msg_display.html() + '</br>' + msg);
            }
        };

        $send_btn.on('click', function (e) {
            var msg = {
                name: $username.val(),
                text: $message.val(),
                date: new Date().toLocaleString()
            };

            $message.val("");

            ws.send(JSON.stringify(msg));
        });

    });
})(jQuery);

