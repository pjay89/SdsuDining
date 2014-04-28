package sdsu.apps.sdsudining;

import parse.ContactParser;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class HomeActivity extends Activity {

	private ImageButton restaurantsButton;
	private ImageButton farmersMarketButton;
	private ImageButton couponsButton;
	private ImageButton cateringButton;
	private ImageButton sweetButton;
	private ImageButton contactUsButton;
	
	private String TAG = "STATE TEST";	

	@Override
	protected void onPause(){
		super.onPause();
		/*SharedPreferences queryState = getSharedPreferences("SdsuDiningApp", Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = queryState.edit();
		editor.putBoolean("queryServer", true);
		editor.commit();
		this.finish();*/
		
		Log.i(TAG, "onPause");
		DiningApp appState = DiningApp.instance();
		appState.setTrue();
		Log.i(TAG, "appState: "+appState.getQueryState());
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		this.setTitle(R.string.home);
		
		// Restore preferences
		/*SharedPreferences queryState = getSharedPreferences("SdsuDiningApp", Context.MODE_PRIVATE);
		boolean queryServer = queryState.getBoolean("queryServer", true);
		if(queryServer){
			ContactParser cp = new ContactParser("http://api.androidhive.info/contacts/");
			cp.parse();
		}*/
		
		DiningApp appState = DiningApp.instance();
		boolean queryServer = appState.getQueryState();
		if(queryServer){
			ContactParser cp = new ContactParser("http://api.androidhive.info/contacts/");
			cp.parse();
			appState.setFalse();

			Log.i(TAG, "in OnCreate appState: "+appState.getQueryState());
		}
	
		

		restaurantsButton = (ImageButton) findViewById(R.id.restaurantsButton);
		restaurantsButton.setOnClickListener(getRestaurantButtonListner);
		
		farmersMarketButton = (ImageButton) findViewById(R.id.farmersMarketButton);
		farmersMarketButton.setOnClickListener(getFarmersMarketButtonListener);
		
		couponsButton = (ImageButton) findViewById(R.id.couponsButton);
		couponsButton.setOnClickListener(getCouponsButtonListener);
		
		cateringButton = (ImageButton) findViewById(R.id.cateringButton);
		cateringButton.setOnClickListener(getCateringButtonListener);
		
		sweetButton = (ImageButton) findViewById(R.id.sweetButton);
		sweetButton.setOnClickListener(getSweetButtonListener);
		
		contactUsButton = (ImageButton) findViewById(R.id.contactUsButton);
		contactUsButton.setOnClickListener(getContactUsButtonListener);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

	public OnClickListener getRestaurantButtonListner = new OnClickListener(){

		@Override
		public void onClick(View v) {
			Intent intent = new Intent(HomeActivity.this, RestaurantsListActivity.class);
			//intent.putExtra("code", "MSFT");
			startActivity(intent);
		}

	};
	
	public OnClickListener getFarmersMarketButtonListener = new OnClickListener(){

		@Override
		public void onClick(View v) {
			Intent intent = new Intent(HomeActivity.this, BrowseByLocationActivity.class);
			intent.putExtra("labelString", getResources().getString(R.string.farmersMarketString));
			startActivity(intent);
		}

	};
	
	public OnClickListener getCouponsButtonListener = new OnClickListener(){

		@Override
		public void onClick(View v) {
			Intent intent = new Intent(HomeActivity.this, CouponsActivity.class);
			intent.putExtra("labelString", getResources().getString(R.string.couponsString));
			//intent.putExtra("tabsCount", 3);
			startActivity(intent);
		}

	};
	
	public OnClickListener getCateringButtonListener = new OnClickListener(){

		@Override
		public void onClick(View v) {
			Intent intent = new Intent(HomeActivity.this, DetailsActivity.class);
			intent.putExtra("labelString", getResources().getString(R.string.cateringString));
			intent.putExtra("tabsCount", 3);
			intent.putExtra("code", "new+york");
			startActivity(intent);
		}

	};
	
	public OnClickListener getSweetButtonListener = new OnClickListener(){

		@Override
		public void onClick(View v) {
			Intent intent = new Intent(HomeActivity.this, DetailsActivity.class);
			intent.putExtra("labelString", getResources().getString(R.string.sweetString));
			intent.putExtra("tabsCount", 3);
			intent.putExtra("code", "san+diego");
			startActivity(intent);
		}

	};
	
	public OnClickListener getContactUsButtonListener = new OnClickListener(){

		@Override
		public void onClick(View v) {
			Intent intent = new Intent(HomeActivity.this, PlainTestActivity.class);
			intent.putExtra("labelString", getResources().getString(R.string.contactUsString));
			intent.putExtra("tabsCount", 2);
			intent.putExtra("code", "houston");
			startActivity(intent);
		}

	};

}
