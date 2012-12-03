package cz.tul.nti.stelvio.lv1;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * 
 * @author Jiri Vrany
 *
 * Zakladni pouziti listView, dedime od ListActivty
 * Pouzivame simle_list z prostredku nabidnutych frameworkem
 * 
 */
public class SimpleListActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String[] hodnoty = getResources().getStringArray(R.array.hodnoty);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, hodnoty);
		setListAdapter(adapter);
		
	}

	/**
	 * Metoda prislusna k listView - pri kliknuti na polozku
	 * dulezite parametry: 
	 * @param position - pozice v l - s tou pracujeme nejcasteji
	 * @param id - id toho ktereho radku
	 */
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		String item = (String) getListAdapter().getItem(position);
		String pokus = (String) String.valueOf(id);
		Toast.makeText(this, item + " selected" + pokus , Toast.LENGTH_LONG).show();
	}
	
	
	
	
    
}