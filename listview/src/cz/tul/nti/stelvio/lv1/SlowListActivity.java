package cz.tul.nti.stelvio.lv1;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;


/**
 * 
 * @author Jiri Vrany
 *
 * Opet dedime od ListActivty
 * Ale na rozdil od prvniho prikaldu, zde pouzijeme vlastni adapter
 * 
 */
public class SlowListActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String[] hodnoty = new String[] { "Android","Mrkvoid","Droid","Andy","Text",
				"Tyranosaurs","Rex","Asta","Chlastá","Android","Mrkvoid","Droid","Andy","Text",
				"Tyranosaurs","Rex","Asta","Chlastá","Android","Mrkvoid","Droid","Andy","Text",
				"Tyranosaurs","Rex","Asta","Chlastá","Android","Mrkvoid","Droid","Andy","Text",
				"Tyranosaurs","Rex","Asta","Chlastá","Android","Mrkvoid","Droid","Andy","Text",
				"Tyranosaurs","Rex","Asta","Chlastá","Android","Mrkvoid","Droid","Andy","Text",
				"Tyranosaurs","Rex","Asta","Chlastá"
		};
		MySlowArrayAdapter adapter = new MySlowArrayAdapter(this, hodnoty);
		setListAdapter(adapter);
		
	}

	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		String item = (String) getListAdapter().getItem(position);
		Toast.makeText(this, item + " selected", Toast.LENGTH_SHORT).show();
	}
	
	
	
	
    
}