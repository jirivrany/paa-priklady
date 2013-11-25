(function () {
    'use strict';

    var showHelp, setupHelp, makeHelp;

    showHelp = function (help) {
        document.getElementById('help').innerHTML = help;
    };

    makeHelp = function (help) {
        return function () {
            showHelp(help);
        };
    };

    setupHelp = function () {
        var helpText, i, item;

        helpText = [{
            'id': 'name',
            'help': 'Vaše celé jméno'
        }, {
            'id': 'email',
            'help': 'Vaše emailová adresa'
        }, {
            'id': 'phone',
            'help': 'Vaše telefonní číslo'
        }];

        for (i = 0; i < helpText.length; i += 1) {
            item = helpText[i];
            document.getElementById(item.id).addEventListener('focus', makeHelp(item.help));
        }
    };
    setupHelp();
}());
