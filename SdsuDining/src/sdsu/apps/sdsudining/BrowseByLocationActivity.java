package sdsu.apps.sdsudining;

import java.util.ArrayList;
import java.util.HashMap;

import sdsu.apps.sdsudining.database.SdsuDBHelper;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.ListActivity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class BrowseByLocationActivity extends ListActivity {
	
	private ArrayList<HashMap<String, String>> locations = new ArrayList<HashMap<String, String>>();
	private static final String TAG = "RESTAURANT";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_browse_by_location);
		
		Intent intent = getIntent();
		this.setTitle(intent.getStringExtra("labelString"));
		
		SdsuDBHelper db = new SdsuDBHelper(this);
		ArrayList<HashMap<String,String>> dbList = db.getUniqueLocationsExceptFarmersMarket();
		
		for(int i=0; i<dbList.size(); i++){
			HashMap<String, String> entry = dbList.get(i);
			locations.add(entry);
		}
		db.close();
		
		ListView listView = getListView();
		listView.setOnItemClickListener(getLocationItemClickListener);
		
		ListAdapter adapter = new SimpleAdapter(this, locations, R.layout.activity_browse_by_location_list_row, new String[]{getString(R.string.RESTAURANT_LOCATION_NAME)}, new int[]{R.id.locationListViewText});
		setListAdapter(adapter);
		
		
		
		
		// Enable Home button on action bar
		final ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
	}
	
	@Override
	protected void onResume(){
		super.onResume();
		boolean isForeground = SdsuDining.isAppStart();
		if(isForeground){

		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.browse_by_location, menu);
		return true;
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(this, HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                //finish();
                return true;
        }
        return false;
    }
	
	public OnItemClickListener getLocationItemClickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View view, int position, long arg3) {
			Log.i(TAG, String.valueOf(position));
			HashMap<String, String> s = locations.get(position);
			Log.i(TAG, s.get(getString(R.string.RESTAURANT_LOCATION_NAME)));
		}
	
	};

	@Override
	protected void onPause(){
		super.onPause();
		SdsuDining.appStatus(this);
	}
}
