package cz.nti.tul.konvertorpenez;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

/**
 * Trida je odvozena od zakladni Aktivity
 * Dale implementuje rozhrani OnClickListener, coz se hodi 
 * zejmena pokud je objektu ke kliknuti vice. 
 * @author Jiri Vrany
 *	
 */


public class MainActivity extends Activity implements OnClickListener{

	private double kurz;
	private EditText hodnotaEdit;
	private SharedPreferences myPrefs;
	
	/**
	 * Metoda onCreate je zakladni setuUp aplikace
	 * Vola se vzdy pri startu ci restartu (viz. zivotni cyklus aktivity)
	 * Vetsinou se zde nastavuje pohled (setContentView)
	 */
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		hodnotaEdit = (EditText) findViewById(R.id.editText1);
		
		Button buttonConvert = (Button) findViewById(R.id.buttonConvert);
		buttonConvert.setOnClickListener(this);
		
		Button buttonClear = (Button) findViewById(R.id.buttonClear);
		buttonClear.setOnClickListener(this);
		
		Button buttonShare = (Button) findViewById(R.id.buttonShare);
		buttonShare.setOnClickListener(this);
		
		myPrefs = PreferenceManager.getDefaultSharedPreferences(this);
		kurz = Double.parseDouble(myPrefs.getString("kurz", "25.5"));
	}
	
	/**
	 * K implementaci teto metody jsme se zavazali v signature tridy
	 * Kazdy objekt na ktery jde kliknout ma definovane id
	 * to pouzijeme pro urceni dalsiho chovani aktivity
	 */
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.buttonConvert:
			convert();	
			break;
		
		case R.id.buttonClear:
			clearResult();
			break;
		
		case R.id.buttonShare:
			shareResult();
			break;
		}
		
	}
	
	/**
	 * Na zaklade zvolene meny provede konverzi na tu druhou
	 * Vysledek aktualizuje do View
	 */
	
	private boolean convert(){
		RadioButton koruny = (RadioButton) findViewById(R.id.radioCZK);
		RadioButton eura = (RadioButton) findViewById(R.id.radioEUR);
		
		try {
			double hodnota = Double.parseDouble(hodnotaEdit.getText().toString());
			kurz = Double.parseDouble(myPrefs.getString("kurz", "25.5"));
			
			if (eura.isChecked()) {
				hodnotaEdit.setText(String.valueOf(Convertor.convertCzkToEur(hodnota, kurz)));
			}
			else if (koruny.isChecked()) {
				hodnotaEdit.setText(String.valueOf(Convertor.convertEurToCzk(hodnota, kurz)));
			}
		} catch (Exception e) {
			Toast.makeText(this, R.string.hint, Toast.LENGTH_SHORT).show();
			return false;
		}
		
		return true;
	}
	
	
	/**
	 * nastavi hodnotu v zobrazovacim okne na prazdny retezec
	*/
	private void clearResult(){
		hodnotaEdit.setText("");
		hodnotaEdit.requestFocus();
	}
		
	
	/**
	 * Sdileni vysledku vypoctu
	 * Vytvori novy Intent (zamer) s built-in akci pro sdileni
	 * Objekt Intent v sobe muze nest dalsi informace (Extra)
	 * Zde to je: subject (napr. pro email) a hodnota vysledku
	 * 
	 * startActivity se postara o zavolani dalsi aktivity, 
	 * ktere je predan nas Intent. Zde konkretne pouzijeme 
	 * chooser / coz je dalsi z peknych vlastnosti frameworku
	 *
	 */
	
	private void shareResult() {
		Intent share = new Intent(Intent.ACTION_SEND);
		share.putExtra(Intent.EXTRA_SUBJECT, R.string.share_subject);
		share.putExtra(Intent.EXTRA_TEXT, hodnotaEdit.getText().toString());
		share.setType("text/plain");
		
		startActivity(Intent.createChooser(share, getString(R.string.button_share)));
		
	}
    
    

    /**
     * Opet jedna z built-in metod pro Aktivitu
     * Rozbali menu, pripadne prida akce primo do action bar (4.x)
     */
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	/**
	 * Tato metoda je podobna udalosti onClick, ale jeji udalost
	 * je navazana na Menu
	 * Menu teto aktivity ma pouze jednu polozku a o jeji obsluhu
	 * se postara aktivita AppPreference
	 * 
	 * Na rozdil od ukazky sdileni (metoda shareResult) je tento 
	 * intent velice jednoduchy.
	 */
	
	public boolean onOptionsItemSelected(MenuItem item){
    	switch(item.getItemId()) {
    	case R.id.menu_settings:
    		Intent i = new Intent(MainActivity.this, AppPreference.class);
    		startActivity(i);
    		
    		break;
    	}
    	
    	
    	return true;
    }
	

	

	
	
}
