package sdsu.apps.sdsudining;

import java.util.ArrayList;

import com.androidquery.AQuery;



import sdsu.apps.sdsudining.parse.CateringParser;
import sdsu.apps.sdsudining.parse.ContactParser;
import sdsu.apps.sdsudining.parse.CouponsParser;
import sdsu.apps.sdsudining.parse.FarmersParser;
import sdsu.apps.sdsudining.parse.HoursParser;
import sdsu.apps.sdsudining.parse.RestaurantsParser;
import sdsu.apps.sdsudining.parse.SweetParser;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class HomeActivity extends Activity {

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
				ProgressDialog progressDialog = new ProgressDialog(this);
				progressDialog.setCanceledOnTouchOutside(false);
				progressDialog.setCancelable(false);
				progressDialog.setInverseBackgroundForced(true);
				progressDialog.setMessage("Loading...");
				progressDialog.show();
				timeDelayRemoveDialog(10000, progressDialog);
				
				new HoursParser(this.getString(R.string.HOURS_URL), this.getApplicationContext(), progressDialog);
				new ContactParser(this.getString(R.string.CONTACT_URL), this.getApplicationContext(), progressDialog);
				new SweetParser(this.getString(R.string.SWEET_URL), this.getApplicationContext(), progressDialog);
				new CateringParser(this.getString(R.string.CATERING_URL), this.getApplicationContext(), progressDialog);
				new CouponsParser(this.getString(R.string.COUPON_URL), this.getApplicationContext(), progressDialog);
				new FarmersParser(this.getString(R.string.FARMERS_URL), this.getApplicationContext(), progressDialog);
				new RestaurantsParser(this.getString(R.string.RESTAURANTS_URL), this.getApplicationContext(), progressDialog);
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
	
	private void timeDelayRemoveDialog(long time, final Dialog d){
		new Handler().postDelayed(new Runnable(){
			public void run(){
				d.dismiss();
			}
		}, time);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

/*			
		restaurantsButton = (ImageButton) findViewById(R.id.farmersMarketButton);//restaurantsButton);
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
*/
		
		
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
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
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
			intent.putExtra("labelString", getResources().getString(R.string.couponsString));
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
