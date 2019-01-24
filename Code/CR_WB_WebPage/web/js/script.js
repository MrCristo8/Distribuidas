/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function ($) {
    $(document).ready(function () {
        var $chatbox = $('.chatbox'),
                $chatboxTitle = $('.chatbox__title'),
                $chatboxTitleClose = $('.chatbox__title__close'),
                $chatboxCredentials = $('.chatbox__credentials');
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

    });
})(jQuery);