package cz.tul.nti.p8.konvertor;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/*
 * Settings fragment se postará o zpracování XML souboru
 * ve kterém se nastaví, jaké konkrétní prvky v preferencích chceme.
 * Zbytek funkcionality (načítání, ukládání, formuláře) nám poskytne framework.
 * 
 */

public class SettingsFragment extends PreferenceFragment {
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Načteme prefence s XML a je to
        addPreferencesFromResource(R.xml.konvpref);
    }

}
