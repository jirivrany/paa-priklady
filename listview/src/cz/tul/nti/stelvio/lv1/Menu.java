package cz.tul.nti.stelvio.lv1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * 
 * @author Jiri Vrany
 * 
 * Ukazka tri pristupu k vytvoreni ListView a Adapteru
 * 
 * doporucena literatura:
 * http://developer.android.com/reference/android/app/ListActivity.html
 * http://developer.android.com/training/improving-layouts/smooth-scrolling.html
 * http://www.vogella.com/articles/AndroidListView/article.html
 */

public class Menu extends Activity implements OnClickListener {
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Button simple = (Button) findViewById(R.id.buttonSimp);
		Button slow = (Button) findViewById(R.id.buttonSlow);
		Button fast = (Button) findViewById(R.id.buttonFast);
		
		simple.setOnClickListener(this);
		slow.setOnClickListener(this);
		fast.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){
		case R.id.buttonSimp:
			Intent i = new Intent(Menu.this,SimpleListActivity.class);
			startActivity(i);
			break;
		case R.id.buttonFast:
			Intent ii = new Intent(Menu.this,FastListActivity.class);
			startActivity(ii);
			break;
		case R.id.buttonSlow:
			Intent iii = new Intent(Menu.this,SlowListActivity.class);
			startActivity(iii);
			break;
		}

	}

}
