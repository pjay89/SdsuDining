package sdsu.apps.sdsudining;

import java.util.ArrayList;
import java.util.HashMap;

import sdsu.apps.sdsudining.database.SdsuDBHelper;

import com.androidquery.AQuery;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class RestaurantsAtLocationListActivity extends Activity {

	/*@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_restaurants_at_location_list);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.restaurants_at_location_list, menu);
		return true;
	}*/
	
	private AQuery rootAQuery;
	private ArrayList<String> entries = new ArrayList<String>();
	private ArrayList<String> restaurantNames = new ArrayList<String>();
	private String labelString = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_restaurants_at_location_list);

		Intent intent = getIntent();
		this.setTitle(intent.getStringExtra("labelString"));
		
		// Enable Home button on action bar
		final ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);

		SdsuDBHelper db = new SdsuDBHelper(this);
		ArrayList<HashMap<String,String>> dbList = db.getRestaurantsAt(labelString);


		rootAQuery = new AQuery(this);
		for(int i=0; i<dbList.size(); i++){
			HashMap<String, String> entry = dbList.get(i);
			entries.add(entry.get(getString(R.string.RESTAURANT_IMAGE)));
			restaurantNames.add(entry.get(getString(R.string.RESTAURANT_NAME)));
		}
		db.close();
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.activity_restaurant_list_row, entries){

			@Override
			public View getView(int position, View convertView, ViewGroup parent){
				if(convertView == null){
					convertView = getLayoutInflater().inflate(R.layout.activity_restaurant_list_row, null);
				}
				
							
				AQuery listRowAQuery = new AQuery(convertView);
				String url = entries.get(position);
				int id = getApplicationContext().getResources().getIdentifier(url, "drawable", getPackageName());
				Bitmap bm = listRowAQuery.getCachedImage(id);
				listRowAQuery.id(R.id.restaurantListViewImage).image(bm, AQuery.RATIO_PRESERVE);
				return convertView;
			}
		};

		ListView listView = (ListView) findViewById(R.id.restaurantsAtLocationListView);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(getRestaurantItemClickListener);

		
		rootAQuery.id(R.id.restaurantsAtLocationListView).adapter(adapter);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.restaurants_at_location_list, menu);
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

	public OnItemClickListener getRestaurantItemClickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View view, int position, long arg3) {			
			Intent intent = new Intent(RestaurantsAtLocationListActivity.this, RestaurantsDetails.class);
			intent.putExtra("labelString", restaurantNames.get(position));
			startActivity(intent);
		}
	
	};

	@Override
	protected void onPause(){
		super.onPause();
		SdsuDining.appStatus(this);
	}

}
