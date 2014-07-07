package sdsu.apps.sdsudining;

import java.util.ArrayList;
import java.util.HashMap;
import com.androidquery.AQuery;

import sdsu.apps.sdsudining.database.SdsuDBHelper;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class RestaurantsListActivity extends Activity {

	private Button browseByMap;
	private AQuery rootAQuery;
	private ArrayList<String> entries = new ArrayList<String>();
	private ArrayList<String> restaurantNames = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_restaurants_list);

		// Enable Home button on action bar
		final ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);

		browseByMap = (Button) findViewById(R.id.browseByMap);
		Typeface font = Typeface.createFromAsset(getAssets(), "CenturyGothic.ttf");
		browseByMap.setTypeface(font);
		browseByMap.setOnClickListener(getBrowseByMapButtonListener);

		SdsuDBHelper db = new SdsuDBHelper(this);
		ArrayList<HashMap<String,String>> dbList = db.getUniqueRestaurants();


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

		ListView listView = (ListView) findViewById(R.id.restaurantsListView);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(getRestaurantItemClickListener);

		
		rootAQuery.id(R.id.restaurantsListView).adapter(adapter);
		
		
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
			Intent intent = new Intent(RestaurantsListActivity.this, BrowseByLocationListActivity.class);
			intent.putExtra("labelString", getResources().getString(R.string.browseByMapString));
			startActivity(intent);
		}

	};

	public OnItemClickListener getRestaurantItemClickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View view, int position, long arg3) {			
			Intent intent = new Intent(RestaurantsListActivity.this, RestaurantsDetails.class);
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
