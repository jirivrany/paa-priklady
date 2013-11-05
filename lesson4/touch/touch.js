(function () {
    'use strict';
    var draw, handleTouch, getPostition;

    draw = function draw(posX, posY, color) {
        var circle = document.createElement('div');
        circle.setAttribute('class', color);

        circle.style.top = posY + 'px';
        circle.style.left = posX + 'px';

        document.body.appendChild(circle);
    };

    getPostition = function getPostition(touch) {
        //ziska souradnice doteku, korekce na stred dotyku
        var posX, posY;

        posY = touch.pageY - 15;
        posX = touch.pageX - 15;

        return [posX, posY];
        
    };

    handleTouch = function handleTouch(e) {
        var position;
        e.preventDefault();

        

        if (e.touches.length === 1) { // Jeden prst
            position = getPostition(e.touches[0]);
            draw(position[0], position[1], 'blue');
        }

        if (e.touches.length === 2) { //Dva prsty
            console.log(e.touches);
            position = getPostition(e.touches[1]);
            draw(position[0], position[1], 'red');
            position = getPostition(e.touches[0]);
            draw(position[0], position[1], 'blue');
        }
    };

    addEventListener('touchstart', handleTouch, false);
    addEventListener('touchmove', handleTouch, false);
}());
