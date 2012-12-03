package cz.tul.nti.stelvio.lv1;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class FastListActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		String[] hodnoty = new String[] { "Android","Mrkvoid","Droid","Andy","Text",
				"Tyranosaurs","Rex","Asta","Chlastá","Android","Mrkvoid","Droid","Andy","Text",
				"Tyranosaurs","Rex","Asta","Chlastá","Android","Mrkvoid","Droid","Andy","Text",
				"Tyranosaurs","Rex","Asta","Chlastá","Android","Mrkvoid","Droid","Andy","Text",
				"Tyranosaurs","Rex","Asta","Chlastá","Android","Mrkvoid","Droid","Andy","Text",
				"Tyranosaurs","Rex","Asta","Chlastá","Android","Mrkvoid","Droid","Andy","Text",
				"Tyranosaurs","Rex","Asta","Chlastá"
		};
		MyFasterArrayAdapter adapter = new MyFasterArrayAdapter(this, hodnoty);
		setListAdapter(adapter);
		ListView list = getListView();
		list.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(FastListActivity.this,"Item in position " + position + " clicked",Toast.LENGTH_SHORT).show();
				return false;
			}
		});
	}

	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		String item = (String) getListAdapter().getItem(position);
		Toast.makeText(this, item + " selected", Toast.LENGTH_SHORT).show();
	}
	
	
	
	
    
}