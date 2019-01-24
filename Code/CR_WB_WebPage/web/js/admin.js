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
    let username = document.getElementById('user_name');
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

        let display = username.value + ', ' + msg.date + ': ' + msg.text;

        msg_display.innerHTML += '</br>' + display;

        ws.send(JSON.stringify(msg));
    }

    function onMessage(event)
    {
        var obj = JSON.parse(event.data);
        let msg = obj.name + ', ' + obj.date + ': ' + obj.text;

        msg_display.innerHTML += '</br>' + msg;

    }

})(window, document, JSON);
