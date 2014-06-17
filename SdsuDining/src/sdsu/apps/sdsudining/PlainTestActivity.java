package sdsu.apps.sdsudining;

import java.util.ArrayList;
import java.util.HashMap;

import sdsu.apps.sdsudining.database.SdsuDBHelper;
import sdsu.apps.sdsudining.parse.SweetParser;


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
				//ContactsParser cp = new ContactsParser("http://api.androidhive.info/contacts/", this.getApplicationContext());
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
		
		
		SdsuDBHelper db = new SdsuDBHelper(getApplicationContext());
		ArrayList<HashMap<String, String>> dbList = db.getUniqueRestaurantsExceptFarmersMarket();
		/*Log.i(TAG, "All Restaurants Except Farmers: "+String.valueOf(dbList.size()));
		for(int i=0; i<dbList.size(); i++){
			HashMap<String, String> entry = dbList.get(i);
			//Log.i(TAG, entry.get(this.getApplicationContext().getString(R.string.RESTAURANT_IMAGE)));
			Log.i(TAG, entry.get(this.getApplicationContext().getString(R.string.RESTAURANT_NAME)));
		}*/
		
		Log.i(TAG, "******************");
		dbList = db.getAllRestaurantDetailsOf("Aztec Market & Convenience Store");
		Log.i(TAG, "Aztec Market Details: "+String.valueOf(dbList.size()));
		for(int i=0; i<dbList.size(); i++){
			HashMap<String, String> entry = dbList.get(i);
			Log.i(TAG, entry.get(this.getApplicationContext().getString(R.string.RESTAURANT_ID)));
			//Log.i(TAG, entry.get(this.getApplicationContext().getString(R.string.RESTAURANT_IMAGE)));
			//Log.i(TAG, entry.get(this.getApplicationContext().getString(R.string.RESTAURANT_LOCATION_ID)));
			Log.i(TAG, entry.get(this.getApplicationContext().getString(R.string.RESTAURANT_LOCATION_NAME)));
			Log.i(TAG, entry.get(this.getApplicationContext().getString(R.string.RESTAURANT_NAME)));
			//Log.i(TAG, entry.get(this.getApplicationContext().getString(R.string.RESTAURANT_PHONE)));
			//Log.i(TAG, entry.get(this.getApplicationContext().getString(R.string.RESTAURANT_WEBSITE)));
		}
		
		Log.i(TAG, "******************");
		dbList = db.getUniqueLocationsExceptFarmersMarket();
		Log.i(TAG, "All Locations Except Farmers Market: "+String.valueOf(dbList.size()));
		for(int i=0; i<dbList.size(); i++){
			HashMap<String, String> entry = dbList.get(i);
			//Log.i(TAG, entry.get(this.getApplicationContext().getString(R.string.RESTAURANT_LOCATION_ID)));
			Log.i(TAG, entry.get(this.getApplicationContext().getString(R.string.RESTAURANT_LOCATION_NAME)));
		}
		
		Log.i(TAG, "******************");
		dbList = db.getRestaurantsAt("Aztec Student Union");
		Log.i(TAG, "All Rests At: "+String.valueOf(dbList.size()));
		for(int i=0; i<dbList.size(); i++){
			HashMap<String, String> entry = dbList.get(i);
			Log.i(TAG, entry.get(this.getApplicationContext().getString(R.string.RESTAURANT_ID)));
			//Log.i(TAG, entry.get(this.getApplicationContext().getString(R.string.RESTAURANT_IMAGE)));
			Log.i(TAG, entry.get(this.getApplicationContext().getString(R.string.RESTAURANT_NAME)));
		}
		
		Log.i(TAG, "******************");
		dbList = db.getRestaurantsAtFarmersMarket();
		Log.i(TAG, "All Rests At: "+String.valueOf(dbList.size()));
		for(int i=0; i<dbList.size(); i++){
			HashMap<String, String> entry = dbList.get(i);
			Log.i(TAG, entry.get(this.getApplicationContext().getString(R.string.RESTAURANT_ID)));
			//Log.i(TAG, entry.get(this.getApplicationContext().getString(R.string.RESTAURANT_IMAGE)));
			Log.i(TAG, entry.get(this.getApplicationContext().getString(R.string.RESTAURANT_NAME)));
		}
		
		
		
		
/*		Log.i(TAG, "******************");
		dbList = db.getContactDetails();
		
		Log.i(TAG, "Contact: "+String.valueOf(dbList.size()));
		for(int i=0; i<dbList.size(); i++){
			
			HashMap<String, String> entry = dbList.get(i);

			Log.i(TAG, entry.get(this.getApplicationContext().getString(R.string.CONTACT_PHONE)));
			Log.i(TAG, entry.get(this.getApplicationContext().getString(R.string.CONTACT_FAX)));
			Log.i(TAG, entry.get(this.getApplicationContext().getString(R.string.CONTACT_ADDRESS)));	
		}
		
		Log.i(TAG, "******************");
		dbList = db.getSweetDetails();
		
		Log.i(TAG, "Sweet: "+String.valueOf(dbList.size()));
		for(int i=0; i<dbList.size(); i++){
			HashMap<String, String> entry = dbList.get(i);
					
			Log.i(TAG, entry.get(this.getApplicationContext().getString(R.string.SWEET_PHONE)));
			Log.i(TAG, entry.get(this.getApplicationContext().getString(R.string.SWEET_FAX)));
			Log.i(TAG, entry.get(this.getApplicationContext().getString(R.string.SWEET_EMAIL)));
			Log.i(TAG, entry.get(this.getApplicationContext().getString(R.string.SWEET_WEBSITE)));
			Log.i(TAG, entry.get(this.getApplicationContext().getString(R.string.SWEET_MENU)));
			Log.i(TAG, entry.get(this.getApplicationContext().getString(R.string.SWEET_ORDER_FORM)));			
		}
		
		
		Log.i(TAG, "******************");
		dbList = db.getCateringDetails();
		
		Log.i(TAG, "Catering: "+String.valueOf(dbList.size()));
		for(int i=0; i<dbList.size(); i++){
			HashMap<String, String> entry = dbList.get(i);
					
			Log.i(TAG, entry.get(this.getApplicationContext().getString(R.string.CATERING_PHONE)));
			Log.i(TAG, entry.get(this.getApplicationContext().getString(R.string.CATERING_FAX)));
			Log.i(TAG, entry.get(this.getApplicationContext().getString(R.string.CATERING_EMAIL)));
			Log.i(TAG, entry.get(this.getApplicationContext().getString(R.string.CATERING_ADDRESS)));
			Log.i(TAG, entry.get(this.getApplicationContext().getString(R.string.CATERING_SNIPPET)));
			Log.i(TAG, entry.get(this.getApplicationContext().getString(R.string.CATERING_GUIDELINES)));
			Log.i(TAG, entry.get(this.getApplicationContext().getString(R.string.CATERING_SERVICE_LEVEL)));
		}*/
		
		
		
		/*ListAdapter adapter = new SimpleAdapter(PlainTestActivity.this, contactsList, R.layout.activity_plain_test_list_item, new String[] {"name", "mobile"}, new int[] {R.id.name, R.id.mobile});
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
			
		});*/
		
		
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
