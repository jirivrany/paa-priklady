/**
 * PAA - přednáška 8 - jednoduchá aplikace pro Android
 * 
 * Tato aplikace ukazuje základní stavební prvky, které využijete při 
 * tvorbě prakticky každého android projektu. Jako demonstrační problém je zde
 * použit opět konvertor měn, podobně jako to bylo v JavaScriptu.
 * 
 * Konkrétně je zde ukázáno:
 * přidání onClickListeneru implementací rozhraní
 * menu a actionBar
 * sharedPreferences jako trvalé úložiště dat pro aplikaci
 * přechod z aktivity do aktivity pomocí Intent
 * zachytávání výjimek
 * 
 */

package cz.tul.nti.p8.konvertor;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class KonvertorActivity extends Activity implements OnClickListener {

	EditText zadanaHodnota;
	Double kurz;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konvertor);
        
        zadanaHodnota = (EditText) findViewById(R.id.editHodnota);
        Button bConvert = (Button) findViewById(R.id.buttonConverts);
        bConvert.setOnClickListener(this);
        Button bClear = (Button) findViewById(R.id.buttonClear);
        bClear.setOnClickListener(this);
        
        //k manipulaci se SharedPreferences zpravidla využíváme PreferenceManager
        SharedPreferences myPref = PreferenceManager.getDefaultSharedPreferences(this);
        
        //bohužel není možné využít built in editor preferenci k uložení float či double hodnoty
        //buď musíme data takto konvertovat, nebo si lze také vytvořit vlastní editor preferencí
        kurz = Double.parseDouble(myPref.getString(getString(R.string.rate), "27.0"));
    }

    /*
     * Tato metoda Activity se stará o vytvoření menu, definovaného v XML souboru
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       // Menu inflater přidá prvky do actionBar
        getMenuInflater().inflate(R.menu.konvertor, menu);
        return true;
    }
    
    /*
     * Obsluha menu, v tomto případě velice jednoduchá, protože menu má jen jednu položku
     */
    @Override
    public boolean onMenuItemSelected(int featureId, android.view.MenuItem item) {
    	switch (item.getItemId()) {
    	  case R.id.action_settings:
    	    // Dekladujeme záměr (Intent) přejít do PreferencesActivity
    	    Intent i = new Intent(this, PreferencesActivity.class);
    	    startActivity(i);
    	    break;
    	  }
    	  return true;
    };

    /*
     * implementace metody onClick je povinná díky tomu, že třída implementuje
     * rozhraní onClickListener
     */
	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.buttonConverts:
				this.konvertuj();
			break;

		case R.id.buttonClear:
				this.clearForm();
			break;
		}
		
	}
	
	/*
	 * Jednoduchá metoda pro smazání obsahu formuláře
	 */
	
	private void clearForm() {
		zadanaHodnota.setText("");
		zadanaHodnota.requestFocus();
		
	}

	/*
	 * Metoda pro konverzi čísla dle kurzu
	 * Výpočetní metody jsou ve třídě Converter
	 * Protože uživatel může odeslat i prázdný formulář
	 * musíme zachytávat výjimku 
	 */
	private void konvertuj() {
		RadioButton koruny = (RadioButton) findViewById(R.id.radioCzk);
		RadioButton eura = (RadioButton) findViewById(R.id.radioEur);
		
		try {
			double hodnota = Double.parseDouble(zadanaHodnota.getText().toString());
			if (koruny.isChecked()) {
				zadanaHodnota.setText(String.valueOf(Converter.toCzk(hodnota, kurz)));
				koruny.setChecked(false);
				eura.setChecked(true);
				
			}
			else if(eura.isChecked()) {
				zadanaHodnota.setText(String.valueOf(Converter.toEur(hodnota, kurz)));
				koruny.setChecked(true);
				eura.setChecked(false);
			}
		} catch (NumberFormatException e) {
			Toast.makeText(this, R.string.error_null, Toast.LENGTH_SHORT).show();	
		}
		
		
			
	}
    
}
