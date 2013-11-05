(function () {
    'use strict';
    var pacman,
        pacmanImage,
        runAnimation,
        canvas,
        gameLoop,
        sprite;

    // Hlavní animační smyčka - volá metody update a render  konkrétního sprite
    // předáváme informaci o tom zda se má či nemá objekt pohybovat
    gameLoop = function gameLoop() {

        window.requestAnimationFrame(gameLoop);
        pacman.update(runAnimation.value);
        pacman.render();

    };

    // Animační sprite
    // má dvě základní metody pro aktualizaci pozice objektu a animačního okénka update()
    // a dále pro překreslení plátna render()
    // používá vnější kontext předávaný v objektu options
    sprite = function sprite(options) {

        var that = {},
            frameIndex = 0,
            tickCount = 0,
            positionX = 0,
            ticksPerFrame = options.ticksPerFrame || 0,
            numberOfFrames = options.numberOfFrames || 1;

        that.context = options.context;
        that.width = options.width;
        that.height = options.height;
        that.image = options.image;

        // aktualizační funkce pro sprite
        // move je parametr který udává zda se má objekt posouvat či zůstat na místě
        that.update = function (move) {

            tickCount += 1;
            move = move || false;

            if ((positionX <= canvas.width - 66) && (move)) {
                positionX += 1;
            }

            if (tickCount > ticksPerFrame) {

                tickCount = 0;

                // Jestliže index rámečku je v rozsahu
                if (frameIndex < numberOfFrames - 1) {
                    // posuň se o další rámeček
                    frameIndex += 1;
                } else { //jinak posuň pásku zpět na začátek
                    frameIndex = 0;
                }
            }
        };

        that.render = function () {

            //smazat canvas
            that.context.clearRect(0, 0, canvas.width, canvas.height);

            // vykreslit objekt 
            // můžeme kreslit celý obrázek, nebo jeho výřez pomocí okénka
            // to právě využíváme při animacích pomocí spritů
            that.context.drawImage(
                that.image, //jaky obrazek
                frameIndex * that.width / numberOfFrames, //horní levý roh okénka - osa X
                0, // horní levý roh okénka - osa Y
                that.width / numberOfFrames, //šířka okénka
                that.height, // výška okénka
                positionX, // pozice okénka na plátně - osa X (horní levý roh)
                33, // pozice okénka na plátně - osa Y (horní levý roh)
                that.width / numberOfFrames, //šírka obrázku - v tomto případě stejná jako okénko
                that.height); //výška obrázku - v tomto případě stejná jako okénko
        };
        return that;

    };

    // Get canvas
    canvas = document.getElementById('myCanvas');
    canvas.width = window.innerWidth;
    canvas.height = 122;

    // Create sprite sheet
    pacmanImage = new Image();

    // Create sprite
    pacman = sprite({
        context: canvas.getContext('2d'),
        width: 198,
        height: 66,
        image: pacmanImage,
        numberOfFrames: 3,
        ticksPerFrame: 10
    });

    //hodnotu v objektu můžeme měnit díky referenci na objekt 
    runAnimation = {
        value: false
    };

    //základní inicializace
    pacmanImage.addEventListener('load', gameLoop);
    pacmanImage.src = 'pacman.gif';

    canvas.addEventListener('click', function () {
        // flip flag
        runAnimation.value = !runAnimation.value;
    });

}());
