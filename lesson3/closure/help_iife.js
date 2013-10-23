(function () {
    'use strict';

    var showHelp, setupHelp;

    showHelp = function (help) {
      document.getElementById('help').innerHTML = help;
    };

    setupHelp = function () {
      var helpText = [
          {'id': 'name', 'help': 'Vaše celé jméno'},
          {'id': 'email', 'help': 'Vaše emailová adresa'},
          {'id': 'phone', 'help': 'Vaše telefonní číslo'}
        ];

      for (var i = 0; i < helpText.length; i++) {
        //
        // Tuto konstrukci označujeme jako IIFE 
        // Immediately Invoked Function Expressions
        // Funkce je spuštěna okamžitě po jejím zpracování interpretem JS
        // Po vykonání už není možné jí znovu spustit
        //
        // V tomto případě pomocí ní řešíme problém blok scope ve forcyklu
        // při každém průchodu cyklem se funkce provede a tím se vytvoří
        // pro každý z elementů callback, který má k dispozici právě ten 
        // kontext, při kterém byl vytvořen.
        //
        (function(i){
          var item = helpText[i];
          document.getElementById(item.id).onfocus = function() {
            showHelp(item.help);
          };
        })(i);
      }
    };
    setupHelp();
}());