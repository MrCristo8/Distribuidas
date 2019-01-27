/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


(function (window, document, JSON)
{
    'use strict';

    var url = 'ws://' + window.location.host + '/CR_WB_WebPage/chat';
    var ws = new WebSocket(url);
    let msg_display = document.getElementById('msg_display');
    var send_btn = document.getElementById('msg_btn');
    let message = document.getElementById('msg_txt');

    ws.onopen = onOpen;
    ws.onclose = onClose;
    ws.onmessage = onMessage;

    send_btn.addEventListener('click', send);

    function onOpen()
    {
        console.log('Connected Client');
    }

    function onClose()
    {
        console.log('Disconected Client');
    }

    function send()
    {
        var msg = {
            name: message.value.split(',')[0],
            text: message.value.split(',')[1],
            date: new Date().toLocaleString()
        };

        let display = '<div class="outgoing_msg">'+
                        '<div class="sent_msg">'+
                            '<p>'+msg.text+'</p>'+
                            '<span class="time_date">'+msg.date+'</span>'+
                        '</div>'+
                    '</div>';
        msg_display.innerHTML += display;

        ws.send(JSON.stringify(msg));
    }

    function onMessage(event)
    {
        var obj = JSON.parse(event.data);
        let msg = '<div class="incoming_msg">'+
                    '<div class="received_msg">'+
                        '<div class="received_withd_msg">'+
                             '<p>'+obj.name+': '+obj.text+'</p>'+
                            '<span class="time_date">'+obj.date+'</span>'+
                        '</div>'+
                    '</div>'+
                  '</div>';
      
        msg_display.innerHTML += msg;
    }
})(window, document, JSON);
