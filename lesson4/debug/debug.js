(function () {
    'use strict';
    var button, handleTouch, handleClick;

    button = document.getElementById('debug');

    handleTouch = function (event) {
        console.log(event);
    };

    handleClick =  function handleClick (event) {
        console.log(event);
    };

    button.addEventListener('touchstart', handleTouch, false);
    button.addEventListener('click', handleClick, false);
    
}());
