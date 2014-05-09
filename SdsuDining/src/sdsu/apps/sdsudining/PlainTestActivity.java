package sdsu.apps.sdsudining;

import java.util.ArrayList;
import java.util.HashMap;

import parse.ContactParser;

import database.DatabaseHelper;
import android.os.Bundle;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class PlainTestActivity extends ListActivity {
	
	private String TAG = "PLAIN TEST";	
	
	
	@Override
	protected void onPause(){
		super.onPause();
		SdsuDining.appStatus(this);
	}

	@Override
	protected void onResume(){
		super.onResume();
		
		boolean isForeground = SdsuDining.isAppStart();
		if(isForeground){
			if(SdsuDining.isNetworkConnected(this)){
				ContactParser cp = new ContactParser("http://api.androidhive.info/contacts/", this.getApplicationContext());
				//cp.parse();
			}
			else{
				final AlertDialog alert = new AlertDialog.Builder(this).create();
				alert.setMessage("No Internet Connection\nFailed to Fetch Data from Server");
				alert.setButton(DialogInterface.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						alert.cancel();
					}
				});
				alert.show();
			}
		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	
		setContentView(R.layout.activity_plain_test);
		
		
		
		DatabaseHelper db = new DatabaseHelper(getApplicationContext());
		final ArrayList<HashMap<String, String>> contactsList = db.getUserDetails();
		
		Log.i(TAG, String.valueOf(contactsList.size()));
		
		ListAdapter adapter = new SimpleAdapter(PlainTestActivity.this, contactsList, R.layout.activity_plain_test_list_item, new String[] {"name", "mobile"}, new int[] {R.id.name, R.id.mobile});
		setListAdapter(adapter);
		
		ListView listView = getListView();
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				
				Intent intent = new Intent(getApplicationContext(), DetailsActivity.class);
				intent.putExtra("tabsCount", contactsList.size());
				intent.putExtra("titles", getNames(contactsList));
				
				startActivity(intent);
			}
			
		});
		
		
	}
	
	private ArrayList<String> getNames(ArrayList<HashMap<String, String>> contactsList){
		ArrayList<String> names = new ArrayList<String>();
		for(HashMap<String, String> each: contactsList){
			names.add(each.get("name"));
			Log.i(TAG, each.get("name"));
		}
		
		return names;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.plain_test, menu);
		return true;
	}

}
