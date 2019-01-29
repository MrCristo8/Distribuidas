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
                $chatboxTitleClose = $('.chatbox__title__close');
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
        ws.onmessage = function (event)
        {
            var obj = JSON.parse(event.data);
            let msg = '<div class="chatbox__body__message chatbox__body__message--right">' +
                    '<div class="chatbox_timing">' +
                    '<ul>' +
                    '<li><a href="#"><i class="fa fa-calendar"></i>' + obj.date + '</a></li>' +
                    '</ul>' +
                    '</div>' +
                    '<div class="ul_section_full">' +
                    '<ul class="ul_msg">' +
                    '<li><strong>' + obj.name + '</strong></li>' +
                    '<li>' + obj.text + '</li>' +
                    '</ul>' +
                    '<ul class="ul_msg2">' +
                    '<li><a href="#"><i class="fa fa-pencil"></i> </a></li>' +
                    '<li><a href="#"><i class="fa fa-trash chat-trash"></i></a></li>' +
                    '</ul>' +
                    '</div>' +
                    '</div>';
            if (obj.name === $username.val())
            {
                $msg_display.html($msg_display.html() + msg);
            }
        };

        $send_btn.on('click', function (e) {
            var msg = {
                name: $username.val(),
                text: $message.val(),
                date: new Date().toLocaleString()
            };

            let display = '<div class="chatbox__body__message chatbox__body__message--left">' +
                    '<div class="chatbox_timing">' +
                    '<ul>' +
                    '<li><a href="#"><i class="fa fa-calendar"></i>' + msg.date + '</a></li>' +
                    '</ul>' +
                    '</div>' +
                    '<div class="ul_section_full">' +
                    '<ul class="ul_msg">' +
                    '<li><strong>' + msg.name + '</strong></li>' +
                    '<li>' + msg.text + '</li>' +
                    '</ul>' +
                    '<ul class="ul_msg2">' +
                    '<li><a href="#"><i class="fa fa-pencil"></i> </a></li>' +
                    '<li><a href="#"><i class="fa fa-trash chat-trash"></i></a></li>' +
                    '</ul>' +
                    '</div>' +
                    '</div>';

            $msg_display.html($msg_display.html() + display);

            $message.val("");

            ws.send(JSON.stringify(msg));
        });

    });
})(jQuery);

