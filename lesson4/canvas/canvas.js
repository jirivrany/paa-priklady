//Multitouch kreslení do Canvasu
//PAA přednáška 4

(function () {
    'use strict';

    var candemo = candemo || {};

    candemo.colors = ['#ffb100', '#11cd11', '#ff11cc', '#fe0000', '#11a56d', '#11ff00', '#ffcc00', '#0040ee', '#3388a9'];

    candemo.initCanvas = function initCanvas() {
        // Základní vykreslení Canvasu
        // získáme element a context
        candemo.cnv = document.getElementById('myCanvas');
        candemo.ctx = candemo.cnv.getContext('2d');
        candemo.ctx.canvas.width = window.innerWidth - 5;
        candemo.ctx.canvas.height = window.innerHeight - 50;
        candemo.ram = candemo.cnv.getBoundingClientRect(); //rozměry vypočteného rámečku kolem prvku - platí pro většinu prvků v DOM
        candemo.cnv.addEventListener('touchstart', candemo.handleTouch, false);
        candemo.cnv.addEventListener('touchmove', candemo.handleTouch, false);
    };

    candemo.drawCircle = function drawCircle(posX, posY, color) {
        //výchozí hodnota parametru - javascript nemá typovou kontrolu 
        color = color || '#aabb33'; 

        posX = posX - candemo.ram.left;
        posY = posY - candemo.ram.top;

        candemo.ctx.beginPath();
        candemo.ctx.arc(posX, posY, 10, 0, 2 * Math.PI);//vykreslení kruhu
        candemo.ctx.fillStyle = color;
        candemo.ctx.fill();
    };

    candemo.handleTouch = function handleTouch(e) {
        //výchozí chování na touch událost je obvykle scrolování nebo zoom
        e.preventDefault();
        for (var i = e.touches.length - 1; i >= 0; i--) {
            candemo.drawCircle(e.touches[i].pageX, e.touches[i].pageY, candemo.colors[i]);
        }

    };

    candemo.clearScreen = function clearScreen() {
        //kreslící plochu překreslíme prázdným obelníkem
        candemo.ctx.clearRect(candemo.ram.left, candemo.ram.top, candemo.ram.width, candemo.ram.height);
        candemo.initCanvas();
    };



    window.addEventListener('load', candemo.initCanvas, false);

    document.getElementById('clear').addEventListener('touchstart', candemo.clearScreen);

}());
