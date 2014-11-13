package sdsu.apps.sdsudining;

import java.util.ArrayList;
import java.util.HashMap;

import sdsu.apps.sdsudining.database.SdsuDBHelper;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

/**
 * BrowseByLocationListActivity: 
 * 	- Fetch data if app is in foreground
 * 	- Load data from db
 *  - Display data and add click handlers
 * @author Priya Jayaprakash
 *
 */

public class BrowseByLocationListActivity extends ListActivity {

	private ArrayList<HashMap<String, String>> locations = new ArrayList<HashMap<String, String>>();
	ListAdapter adapter;

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
			Intent intent = new Intent(this, LoadingActivity.class);
			startActivityForResult(intent, 0);
		}

		// Refresh with new data
		if(adapter != null){
			locations.clear(); 
		}

		loadListItems();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_browse_by_location);


		// Enable Back button on action bar
		final ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);		
	}

	private void loadListItems(){
		SdsuDBHelper db = new SdsuDBHelper(this);
		ArrayList<HashMap<String,String>> dbList = db.getUniqueLocations();

		for(int i=0; i<dbList.size(); i++){
			HashMap<String, String> entry = dbList.get(i);
			locations.add(entry);
		}
		db.close();

		ListView listView = getListView();
		listView.setOnItemClickListener(getLocationItemClickListener);


		adapter = new SimpleAdapter(this, locations, R.layout.activity_browse_by_location_list_row, new String[]{getString(R.string.RESTAURANT_LOCATION_NAME)}, new int[]{R.id.locationListViewText});
		setListAdapter(adapter);
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return false;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			return true;
		}
		return false;
	}

	public OnItemClickListener getLocationItemClickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View view, int position, long arg3) {
			HashMap<String, String> item = locations.get(position);
			Intent intent = new Intent(BrowseByLocationListActivity.this, RestaurantsAtLocationListActivity.class);
			intent.putExtra("labelString", item.get(getString(R.string.RESTAURANT_LOCATION_NAME)));
			startActivity(intent);
		}

	};


}
