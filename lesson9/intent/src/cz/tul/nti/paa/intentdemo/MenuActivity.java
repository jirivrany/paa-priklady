package cz.tul.nti.paa.intentdemo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MenuActivity extends Activity implements OnClickListener {
	
	final public static int CONTACT_CODE = 11150;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		Button gallery = (Button) findViewById(R.id.buttonGallery);
		Button contact = (Button) findViewById(R.id.buttonContacts);
		Button nextpage = (Button) findViewById(R.id.buttonNext);
		Button call = (Button) findViewById(R.id.buttonCall);
		
		call.setOnClickListener(this);
		gallery.setOnClickListener(this);
		contact.setOnClickListener(this);
		nextpage.setOnClickListener(this);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.buttonGallery:
			showGallery();
			break;
		case R.id.buttonCall:
			showCallLog();
			break;
		case R.id.buttonContacts:
			pickContact();
			break;
		case R.id.buttonNext:
			goToNextPage();
			break;
		}
	}

	private void goToNextPage() {
		Intent intent = new Intent(Intent.ACTION_VIEW); 
	    intent.setData(Uri.parse("http://www.google.com")); 
	    startActivity(intent); 
	}

	private void pickContact() {
		Intent myIntent = new Intent();
		myIntent.setAction(Intent.ACTION_PICK);
		myIntent.setData(android.provider.ContactsContract.Contacts.CONTENT_URI);
		startActivityForResult(myIntent, CONTACT_CODE);
		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			switch (requestCode){
			case CONTACT_CODE:
				Uri result = data.getData();
				Toast.makeText(this,"ID kontaktu:" + result.getLastPathSegment(), Toast.LENGTH_LONG).show();
				break;
			}
			
		}
		else {
			Toast.makeText(this, "pickup failed", Toast.LENGTH_LONG).show();
		}
	}
	
	
	/**
	 * Ukazka built-in intentu pro zavolani call logu
	 */
	
	private void showCallLog() {
		Intent myIntent = new Intent();
		myIntent.setAction(Intent.ACTION_CALL_BUTTON);
		startActivity(myIntent);
	
	}

	private void showGallery() {
		Intent myIntent = new Intent();
		myIntent.setAction(Intent.ACTION_VIEW);
		myIntent.setData(android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
		startActivity(myIntent);
		
	}
	

}
