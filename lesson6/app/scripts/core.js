/*global define*/
define(['zepto'], function ($) {
    'use strict';

    var core;

    core = {

        plan: {},

        setMessage: function (message) {
            $('#message').html(message);
        },

        renderView: function renderView(data) {
            var row, ids = ['datum', 'prvek', 'delka'];

            for (var i = ids.length - 1; i >= 0; i--) {
                row = document.getElementById(ids[i]);
                row.innerHTML = data[ids[i]];
            }
        },

        renderResponse: function renderResponse(response) {
            core.plan = response;
            core.plan.currentDay = '1386284400';
            core.renderView(core.plan['1386284400']);
        },

        init: function init(url) {
            $.ajax({
                type: 'GET',
                url: url,
                // type of data we are expecting in return:
                dataType: 'json',
                timeout: 300,
                context: $('body'),
                success: core.renderResponse,
                error: function (xhr, type) {
                    window.alert('Ajax error!');
                    console.log(JSON.stringify(xhr));
                    console.log(type);
                }
            });
        }

    };

    return core;

});
