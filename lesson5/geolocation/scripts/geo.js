var geo = (function () {
    'use strict';

    var geo = {
        /*
         * Geolocation error callbackk
         * @param {Object}  error  object
         */

        handle_error: function handle_error(error) {
            var msg;
            switch (error.code) {
            case error.PERMISSION_DENIED:
                msg = 'bez povolení nemohu pracovat';
                break;
            case error.POSITION_UNAVAILABLE:
                msg = 'nelze detekovat pozici';
                break;
            case error.TIMEOUT:
                msg = 'nelze detekovat pozici - time out';
                break;
            default:
                msg = '++++ PRESS RESET ++++';
                break;
            }

            return msg;

        }

    };
    return geo;

}());
