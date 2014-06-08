package sdsu.apps.sdsudining;

import java.util.ArrayList;
import java.util.HashMap;
import com.androidquery.AQuery;

import sdsu.apps.sdsudining.database.SdsuDBHelper;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

public class RestaurantsListActivity extends Activity {

	private Button browseByMap;
	private AQuery aq;
	private ArrayList<String> entries = new ArrayList<String>();
	private static final String TAG = "RESTAURANT";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_restaurants_list);

		// Enable Home button on action bar
		final ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);

		browseByMap = (Button) findViewById(R.id.browseByMap);
		browseByMap.setOnClickListener(getBrowseByMapButtonListener);

		/*RestaurantsListViewAdapter adapter = new RestaurantsListViewAdapter(getApplicationContext());
		ListView restaurantListView = (ListView) findViewById(R.id.restaurantsListView);
		restaurantListView.setAdapter(adapter);*/

		
		SdsuDBHelper db = new SdsuDBHelper(this);
		ArrayList<HashMap<String,String>> dbList = db.getUniqueRestaurantsExceptFarmersMarket();


		aq = new AQuery(findViewById(R.layout.activity_restaurants_list));
		for(int i=0; i<dbList.size(); i++){
			HashMap<String, String> entry = dbList.get(i);
			entries.add(entry.get(getString(R.string.RESTAURANT_IMAGE)));
		}
		db.close();
		


		ArrayAdapter<ArrayList<String>> adapter = new ArrayAdapter<ArrayList<String>>(this, R.layout.activity_restaurant_list_row){
			@Override
			public View getView(int position, View convertView, ViewGroup parent){
				Log.i(TAG, "here");
				if(convertView == null){
					convertView = getLayoutInflater().inflate(R.layout.activity_restaurant_list_row, parent, false);
				}

				AQuery aq = new AQuery(convertView);
				String url = entries.get(position);
				Log.i(TAG, "URL: "+url);
				aq.id(R.id.restaurantListViewImage).image(url, true, true, 0, 0, null, AQuery.FADE_IN_NETWORK, 1.0f);
				return convertView;
			}
		};
		aq.id(R.id.restaurantsList).adapter(adapter);
		Log.i(TAG, "done");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.restaurants_list, menu);
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
			return true;
		}
		return false;
	}

	public OnClickListener getBrowseByMapButtonListener = new OnClickListener(){

		@Override
		public void onClick(View v) {
			Intent intent = new Intent(RestaurantsListActivity.this, BrowseByLocationActivity.class);
			intent.putExtra("labelString", getResources().getString(R.string.browseByMapString));
			startActivity(intent);
		}

	};





	@Override
	protected void onPause(){
		super.onPause();
		SdsuDining.appStatus(this);
	}




}
