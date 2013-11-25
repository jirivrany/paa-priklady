/* global $, google, geo */
(function ($, google, geo) {
    'use strict';

    var geocoder, map, initiate_geolocation,
        handle_geolocation_query, handle_geolocation_googlemap, handle_geolocation_simplemap,
        infowindow = new google.maps.InfoWindow(),
        marker;

    $(window).ready(function () {
        $('#btn_locate').on('click', initiate_geolocation);
        geocoder = new google.maps.Geocoder();

    });

    /*
     * Callback pro tlacitko, zjisti pozici
     */

    initiate_geolocation = function initiate_geolocation() {
        //navigator.geolocation.getCurrentPosition(handle_geolocation_googlemap, handle_error);
        navigator.geolocation.getCurrentPosition(handle_geolocation_googlemap, geo.handle_error);
    };

    
    /*
     * zpracuje pozici do strucneho prehledu
     * @param {Object}  Geoposition
     */

    handle_geolocation_query = function handle_geolocation_query(position) {
        console.log(position);
        $('#message').html('Lat: ' + position.coords.latitude + '<br/>' + 'Lon: ' + position.coords.longitude + '<br/>' + 'Acc: ' + position.coords.accuracy + '<br/>' + 'Alt: ' + position.coords.altitude + '<br/>' + 'AltAcc: ' + position.coords.altitudeAccuracy + '<br/>');
    };

    /*
     * zpracuje pozici s vyuzitim Gogle Map static AP
     * @param {Object}  Geoposition
     */

    handle_geolocation_simplemap = function handle_geolocation_simplemap(position) {
        var image_url = 'http://maps.google.com/maps/api/staticmap?sensor=false&center=' + position.coords.latitude + ',' +
            position.coords.longitude + '&zoom=16&size=600x400&markers=color:blue|label:T|' +
            position.coords.latitude + ',' + position.coords.longitude;
        $('#map_canvas').html('<img src="' + image_url + '" alt="mapa" id="mapa" />');
    };

    /*
     * zpracuje pozici s vyuzitim Google Maps API
     * @param {Object}  Geoposition
     */

    handle_geolocation_googlemap = function handle_geolocation_googlemap(position) {

        var lat = parseFloat(position.coords.latitude);
        var lng = parseFloat(position.coords.longitude);
        var latlng = new google.maps.LatLng(lat, lng);
        var mapOptions = {
            zoom: 15,
            center: latlng,
            mapTypeId: 'roadmap'
        };
        map = new google.maps.Map(document.getElementById('map_canvas'), mapOptions);

        geocoder.geocode({
            'latLng': latlng
        }, function (results, status) {
            if (status == google.maps.GeocoderStatus.OK) {
                if (results[1]) {
                    marker = new google.maps.Marker({
                        position: latlng,
                        map: map
                    });
                    infowindow.setContent(results[1].formatted_address);
                    infowindow.open(map, marker);
                } else {
                    alert('No results found');
                }
            } else {
                alert('Geocoder failed due to: ' + status);
            }
        });

    };
}($, google, geo));
