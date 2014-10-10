package sdsu.apps.sdsudining;

import java.util.ArrayList;

import sdsu.apps.sdsudining.gcm.Configurations;

import com.androidquery.AQuery;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class HomeActivity extends Activity {
	
	@Override
	protected void onPause(){
		super.onPause();
		SdsuDining.appStatus(this);
		Log.i("STATE TEST", "HOME ONPAUSE");
	}
	
	@Override
	protected void onResume(){
		super.onResume();
		Log.i("STATE TEST", "HOME ONRESUME");
		boolean isForeground = SdsuDining.isAppStart();
		if(isForeground){
			/*Intent intent = new Intent(this, LoadingActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			finish();*/
			
			Intent intent = new Intent(this, LoadingActivity.class);
			startActivityForResult(intent, 0);
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
		
		showMsg();
	}

	private void showMsg(){
		Intent intent = getIntent();
		String msg = intent.getStringExtra(Configurations.MESSAGE_KEY);
		if(msg!=null){
			// Prevent alert recreation on orientation change
			Bundle extras = null;
			intent.replaceExtras(extras);
			
			final AlertDialog alert = new AlertDialog.Builder(this).create();
			alert.setMessage(msg);
			alert.setButton(DialogInterface.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					alert.cancel();
				}
			});
			alert.show();
		}
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
