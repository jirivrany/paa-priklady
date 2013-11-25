(function (window) {
    'use strict';

    var app = app || {};
    

    app.init = function () {

        app.checkCache();
        document.getElementById('mainform').addEventListener('submit', app.saveKurz);
        document.getElementById('calc').addEventListener('submit', app.convert);
        app.displayKurz();

    };

    app.checkCache = function () {

        window.applicationCache.addEventListener('updateready', function () {
            if (window.applicationCache.status === window.applicationCache.UPDATEREADY) {
                window.applicationCache.swapCache();
                if (window.confirm('A new version of this site is available. Load it?')) {
                    window.location.reload();
                }
            }
        }, false);
    };

    app.saveKurz = function (e) {
        e.preventDefault();
        var kurz;

        kurz = document.getElementById('kurz').value;
        window.localStorage.kurz = kurz;
        app.displayKurz();

    };

    app.displayKurz = function () {
        var kurz, status;

        kurz = window.localStorage.kurz;
        status = document.getElementById('status');

        if (kurz) {
            status.innerHTML = 'uložený kurz je: ' + kurz;
        } else {
            status.innerHTML = 'nastavte kurz';
        }
    };

    app.convert = function (e) {
        var kurz, val;
        e.preventDefault();

        val = document.getElementById('data').value;
        kurz = window.localStorage.kurz;
        document.getElementById('result').innerHTML = 'výsledek: ' + val * kurz;

    };


    window.addEventListener('load', app.init, false);
}(window));
