package cz.nti.tul.konvertorpenez;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Kurz se uklada do sdilenych preferenci
 * Pro jejich obsluhu slouzi tato trida
 * 
 * @author Jiri Vrany
 *
 */

public class AppPreference extends PreferenceActivity {
	
	/**
	 * Nektere veci s preferenceActivity se presunuly do 
	 * PreferenceFragment, ale ten neni soucasti support library
	 * Pouzivat deprecated metody sice neni uplne idealni, ale zde je 
	 * to relativne nejsnadnejsi reseni.
	 * 
	 */
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences);
	}

}
