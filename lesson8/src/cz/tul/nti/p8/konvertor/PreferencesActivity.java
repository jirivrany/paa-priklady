package cz.tul.nti.p8.konvertor;

import android.app.Activity;
import android.os.Bundle;

/*
 * Tato aktivita slouží jako obal pro SettingsFragment, který má za úkol
 * vlastní obsluhu preferencí.
 * Tento přístup je nutný od Androidu 3.0. Původně využívaná 
 * android.preference.PreferenceActivity je označena za Deprecated 
 * Bohužel pokud potřebujete podporovat Android 2.x je to problém,
 * díky jinému API androidu 4.x a support library pro starší verze.
 * Samozřejmě deprecated metody stále fungují, takže je možné je s tímto
 * vědomím použít. 
 * 
 * Více o Settings zde http://developer.android.com/guide/topics/ui/settings.html
 * 
 * Pokud se tato aktivita zobrazí na JellyBeans a novějších androidech, nabízí v 
 * actionBar tlačítko pro návrat do hlavní aktivity. Na rozdíl od předchozích verzí 
 * se toho dá dosáhnout jedninou instrucí přidanou do manifestu. 
 * Postačí android:parentActivityName="KonvertorActivity"
 * 
 * Ve starších verzích je k tomu potřeba několik desítek řádků kódu.
 */
public class PreferencesActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hlavním a jediným obsahem aktivity bude SettingsFragment
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();
    }
}