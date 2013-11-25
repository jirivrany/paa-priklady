/*
* Co všechno je tu špatně? 
*/

/*
* Tento způsob deklarace funkcí je zastaralý a často je označován
* za antipattern. Doporučuje se používat funkce jako výrazu - function expresion
* mimo jiné proto, že je tak z kódu více patrné, že funkce v JS je objekt
* a může být tudíž přiřazována, předávána do funkce atd. jako každý
* jiný objekt.
*
* doporučný zápis je var showHelp = function(help) { ...
*/

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
    /*
    * Zde jsou chyby hned tři - slušný výkon na dva řádky kódu
    * 
    * 1.
    * první je relaivně malá - var uvnitř cyklu
    * proměnná item je tak při každém průchodu vytvářena znovu a znovu
    * což je pomalejší než prosté přiřazení do již existující proměnné
    * 
    * 2. 
    * Přiřazovat callback přímo - element.událost = function()
    * je anti-pattern. Mimo jiné takto nejde přiřadit více callback funkcí najednou.
    * 
    * 3. 
    * Vytvářet anonymní funkce ve forcyklu je velká chyba. JSHint či JSLint vás
    * na ní upozorní. Také je vidět, že kód nedělá to co dělat má.
    * Proč je to chyba? Protože anonymní funkce vytvářená během forcyklu je uzávěra. 
    * V rámci tohoto cyklu vytvoříme uzávěry celkem tři. Všechny si budou "pamatovat" kontext
    * funkce setupHelp() ve kterém byly postupně vytvářeny. 
    * Problém je, že kontext funkce se během forcyklu mění - inkrementujeme i. Uzávěra neuchová kontext
    * platný při jejím vytvoření. Uchová si pouze informaci o tom, který kontext má použít.
    * Takže v okamžiku vyvolání callbacku (událost focus) se začne shánět po kontextu a dostane poslední
    * platný kontext funkce setupHelp. Což je ten při kterém bylo i === 3. A proto to takto nefunguje
    * a help se u všech elementů ukazuje stále stejný ať děláte co děláte...
    */
  }
}

setupHelp();