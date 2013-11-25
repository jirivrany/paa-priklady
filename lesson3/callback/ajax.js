(function () {
    "use strict";

    var getRequestHandler, getDataText;

    getRequestHandler = function () {
        /*
        * Otestuje prohlizec a vrati prislusny XML HTTP reuqest handler
        */
        var httpRequest;

        if (window.XMLHttpRequest) {
            httpRequest = new XMLHttpRequest();
        } else if (window.ActiveXObject) {
            // Internet Explorer is strange...
            httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
        }

        return httpRequest;

    };

    getDataText = function (url, callback) {
        // XMLHttpRequest object - AJAX
        var httpRequest = getRequestHandler();

        httpRequest.onreadystatechange = function () {
            // dokud neni stav done (4) a vysledek ok (200) cekame
            // 100% kod by zde mel obsahovat obsluhu chyb 404, 500 atd.
            if (httpRequest.readyState === 4 && httpRequest.status === 200) {
                callback.call(JSON.parse(httpRequest.responseText));
            }
        };
        httpRequest.open("GET", url);
        httpRequest.send();
    };

    // call the function
    getDataText("top10.json", function () {
        console.log(this);
    });
    console.log("posledni radek scriptu, presto je nastartovan driv nez callback");
}());