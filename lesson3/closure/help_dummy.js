function showHelp(help) {
  document.getElementById('help').innerHTML = help;
}

function setupHelp() {
  var helpText = [
      {'id': 'name', 'help': 'Vaše celé jméno'},
      {'id': 'email', 'help': 'Vaše emailová adresa'},
      {'id': 'phone', 'help': 'Vaše telefonní číslo'}
    ];

  for (var i = 0; i < helpText.length; i++) {
    var item = helpText[i];
    document.getElementById(item.id).onfocus = function() {
      showHelp(item.help);
    }
  }
}

setupHelp();