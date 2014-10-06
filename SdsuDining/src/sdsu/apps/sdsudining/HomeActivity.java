package sdsu.apps.sdsudining;

import java.util.ArrayList;

import com.androidquery.AQuery;



import sdsu.apps.sdsudining.objects.BusyWait;
import sdsu.apps.sdsudining.parse.CateringParser;
import sdsu.apps.sdsudining.parse.ContactParser;
import sdsu.apps.sdsudining.parse.CouponsParser;
import sdsu.apps.sdsudining.parse.FarmersParser;
import sdsu.apps.sdsudining.parse.HoursParser;
import sdsu.apps.sdsudining.parse.RestaurantsParser;
import sdsu.apps.sdsudining.parse.SweetParser;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class HomeActivity extends Activity {

	BusyWait hoursBusyWait;
	BusyWait farmersBusyWait;
	BusyWait contactBusyWait;
	BusyWait sweetBusyWait;
	BusyWait cateringBusyWait;
	BusyWait couponsBusyWait;
	BusyWait restaurantsBusyWait;
	
	@Override
	protected void onPause(){
		super.onPause();
		SdsuDining.appStatus(this);
		Log.i("STATE TEST", "HOME ONPAUSE");
	/*	SdsuDining.appStatus(this);
		hoursBusyWait.dismiss();
		farmersBusyWait.dismiss();
		contactBusyWait.dismiss();
		sweetBusyWait.dismiss();
		cateringBusyWait.dismiss();
		couponsBusyWait.dismiss();
		restaurantsBusyWait.dismiss();*/
	}
	
	@Override
	protected void onResume(){
		super.onResume();

		/*hoursBusyWait = new BusyWait(this);
		farmersBusyWait = new BusyWait(this);
		contactBusyWait = new BusyWait(this);
		sweetBusyWait = new BusyWait(this);
		cateringBusyWait = new BusyWait(this);
		couponsBusyWait = new BusyWait(this);
		restaurantsBusyWait = new BusyWait(this);
		
		
		boolean isForeground = SdsuDining.isAppStart();
		if(isForeground){
			if(SdsuDining.isNetworkConnected(this)){
				new HoursParser(this.getString(R.string.HOURS_URL), this.getApplicationContext(), hoursBusyWait);
				new FarmersParser(this.getString(R.string.FARMERS_URL), this.getApplicationContext(), farmersBusyWait);
				new ContactParser(this.getString(R.string.CONTACT_URL), this.getApplicationContext(), contactBusyWait);
				new SweetParser(this.getString(R.string.SWEET_URL), this.getApplicationContext(), sweetBusyWait);
				new CateringParser(this.getString(R.string.CATERING_URL), this.getApplicationContext(), cateringBusyWait);
				new CouponsParser(this.getString(R.string.COUPON_URL), this.getApplicationContext(), couponsBusyWait);
				new RestaurantsParser(this.getString(R.string.RESTAURANTS_URL), this.getApplicationContext(), restaurantsBusyWait);
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
		}*/
/*		else{
			this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER);
		}*/
		
		Log.i("STATE TEST", "HOME ONRESUME");
		boolean isForeground = SdsuDining.isAppStart();
		Log.i("STATE TEST", "no change status " + isForeground);
		if(isForeground){
			Intent intent = new Intent(this, LoadingActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			finish();
		}
		
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i("STATE TEST", "HOME ONCREATE");
		
		setContentView(R.layout.activity_home);

		AQuery rootAQuery = new AQuery(this);
		
		Bitmap bm = rootAQuery.getCachedImage(R.drawable.restaurants);
		rootAQuery.id(R.id.restaurantsButton).image(bm, AQuery.RATIO_PRESERVE);
		
		bm = rootAQuery.getCachedImage(R.drawable.farmersmarket);
		rootAQuery.id(R.id.farmersMarketButton).image(bm, AQuery.RATIO_PRESERVE);
		
		bm = rootAQuery.getCachedImage(R.drawable.coupons);
		rootAQuery.id(R.id.couponsButton).image(bm, AQuery.RATIO_PRESERVE);
		
		bm = rootAQuery.getCachedImage(R.drawable.catering);
		rootAQuery.id(R.id.cateringButton).image(bm, AQuery.RATIO_PRESERVE);
		
		bm = rootAQuery.getCachedImage(R.drawable.sweet);
		rootAQuery.id(R.id.sweetButton).image(bm, AQuery.RATIO_PRESERVE);
		
		bm = rootAQuery.getCachedImage(R.drawable.contactus);
		rootAQuery.id(R.id.contactUsButton).image(bm, AQuery.RATIO_PRESERVE);
		
		ImageView restaurantsButton = (ImageView) findViewById(R.id.restaurantsButton);
		restaurantsButton.setOnClickListener(getRestaurantButtonListner);
		
		ImageView farmersButtom = (ImageView) findViewById(R.id.farmersMarketButton);
		farmersButtom.setOnClickListener(getFarmersMarketButtonListener);
		
		ImageView couponsButton = (ImageView) findViewById(R.id.couponsButton);
		couponsButton.setOnClickListener(getCouponsButtonListener);
		
		ImageView cateringButton = (ImageView) findViewById(R.id.cateringButton);
		cateringButton.setOnClickListener(getCateringButtonListener);
		
		ImageView sweetButton = (ImageView) findViewById(R.id.sweetButton);
		sweetButton.setOnClickListener(getSweetButtonListener);
		
		ImageView  contactUsButton= (ImageView) findViewById(R.id.contactUsButton);
		contactUsButton.setOnClickListener(getContactUsButtonListener);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.home, menu);
		//return true;
		return false;
	}
	

	public OnClickListener getRestaurantButtonListner = new OnClickListener(){

		@Override
		public void onClick(View v) {
			Intent intent = new Intent(HomeActivity.this, RestaurantsListActivity.class);
			startActivity(intent);
		}

	};

	public OnClickListener getFarmersMarketButtonListener = new OnClickListener(){

		@Override
		public void onClick(View v) {
			ArrayList<String> titles = new ArrayList<String>();
			titles.add(getResources().getString(R.string.contact));
			titles.add(getResources().getString(R.string.hours));
			titles.add(getResources().getString(R.string.about));
			
			Intent intent = new Intent(HomeActivity.this, DetailsActivity.class);
			intent.putExtra("labelString", getResources().getString(R.string.farmersMarketString));
			intent.putExtra("titles", titles);
			startActivity(intent);
		}

	};

	public OnClickListener getCouponsButtonListener = new OnClickListener(){

		@Override
		public void onClick(View v) {
			Intent intent = new Intent(HomeActivity.this, CouponsListActivity.class);
			//intent.putExtra("labelString", getResources().getString(R.string.couponsString));
			startActivity(intent);
		}

	};

	public OnClickListener getCateringButtonListener = new OnClickListener(){

		@Override
		public void onClick(View v) {
			ArrayList<String> titles = new ArrayList<String>();
			titles.add(getResources().getString(R.string.contact));
			titles.add(getResources().getString(R.string.hours));
			titles.add(getResources().getString(R.string.about));
			
			Intent intent = new Intent(HomeActivity.this, DetailsActivity.class);
			intent.putExtra("labelString", getResources().getString(R.string.cateringString));
			intent.putExtra("titles", titles);
			startActivity(intent);
		}

	};

	public OnClickListener getSweetButtonListener = new OnClickListener(){

		@Override
		public void onClick(View v) {
			ArrayList<String> titles = new ArrayList<String>();
			titles.add(getResources().getString(R.string.contact));
			titles.add(getResources().getString(R.string.hours));
			titles.add(getResources().getString(R.string.about));
			
			Intent intent = new Intent(HomeActivity.this, DetailsActivity.class);
			intent.putExtra("labelString", getResources().getString(R.string.sweetString));
			intent.putExtra("titles", titles);
			startActivity(intent);
		}

	};

	public OnClickListener getContactUsButtonListener = new OnClickListener(){

		@Override
		public void onClick(View v) {
			ArrayList<String> titles = new ArrayList<String>();
			titles.add(getResources().getString(R.string.contact));
			titles.add(getResources().getString(R.string.hours));
			titles.add(getResources().getString(R.string.about));
			
			Intent intent = new Intent(HomeActivity.this, DetailsActivity.class);
			intent.putExtra("labelString", getResources().getString(R.string.contactUsString));
			intent.putExtra("titles", titles);
			startActivity(intent);
		}

	};

}
