(function () {
    'use strict';
    /* V okamžiku kde nastane událost load (dokument se nahraje)
    * zkontrolujeme zda je něco nového k aktualizaci
    */
    window.addEventListener('load', function () {

        window.applicationCache.addEventListener('updateready', function () {
            if (window.applicationCache.status === window.applicationCache.UPDATEREADY) {
                // Nová verze aplikace je připravena a stažena v prohlížeči
                // Nahradíme původní obsah pomocí metody swapCache.
                window.applicationCache.swapCache();
                if (confirm('K dispozici je nová verze aplikace. aktualizovat?')) {
                    //eventuelně můžeme požádat uživatele o potvrzení zda se má aplikace aktualizovat
                    window.location.reload();
                }
            } else {
                // Žádná změna - else větev by tu v podstatě nemusela být
            }
        }, false);

    }, false);
}());
