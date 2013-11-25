/* global $ */
(function ($) {
    'use strict';

    $(window).ready(function () {
        $('#btn_start').on('click', start_tracking_location);
        $('#btn_stop').on('click', stop_tracking_location);

    });

    var trackProcess = null,
        start_tracking_location, stop_tracking_location, handle_geolocation_query, handle_error, handle_geolocation_map;

    start_tracking_location = function start_tracking_location() {
        if (trackProcess === null) {
            var options = {
                frequency: 2000
            }; //kontrola kazde 2 vteriny
            trackProcess = navigator.geolocation.watchPosition(handle_geolocation_map, handle_error, options);
        }
    };

    stop_tracking_location = function stop_tracking_location() {
        if (trackProcess !== null) {
            navigator.geolocation.clearWatch(trackProcess);
            trackProcess = null;
        }
    };

    handle_error = function handle_error(error) {
        var msg;
        console.log(error);
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
            msg = '++++PRESS RESET++++';
            break;
        }

        $('#message').html(msg);

    };

    handle_geolocation_query = function handle_geolocation_query(position) {
        $('#message').html('Lat: ' + position.coords.latitude + ' ' +
            'Lon: ' + position.coords.longitude);
    };

    handle_geolocation_map = function handle_geolocation_map(position) {
        var image_url = 'http://maps.google.com/maps/api/staticmap?sensor=true&center=' + position.coords.latitude + ',' +
            position.coords.longitude + '&zoom=16&size=300x400&markers=color:blue|label:T|' +
            position.coords.latitude + ',' + position.coords.longitude;
        $('#message').html('Lat: ' + position.coords.latitude + ' ' +
            'Lon: ' + position.coords.longitude + 'Alt: ' + position.coords.altitude);

        $('#map_holder').html('<img src="' + image_url + '" alt="mapa" id="mapa" />');
    };

}($));
