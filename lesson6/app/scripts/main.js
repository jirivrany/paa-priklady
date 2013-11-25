var BASE_URL = 'http://sotek.kai.tul.cz/list/mock/';
//var BASE_URL = 'http://localhost/mobi/servermock/';

require(['zepto', 'core'], function ($, core) {
    'use strict';
    $('#test_el').tap(function () {
        core.setMessage('tap');
    });

    $('#test_el').swipeLeft(function () {
        //prew
        var day;
        day = core.plan[core.plan.currentDay];
        core.plan.currentDay = day.prew;
        core.renderView(core.plan[day.prew]);
    });

    $('#test_el').swipeRight(function () {
        //next
        var day;
        day = core.plan[core.plan.currentDay];
        core.plan.currentDay = day.next;
        core.renderView(core.plan[day.next]);
    });

    console.log(BASE_URL);
    core.init(BASE_URL);
});
