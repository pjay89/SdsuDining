package sdsu.apps.sdsudining;

import java.util.ArrayList;
import java.util.Arrays;



import sdsu.apps.sdsudining.parse.CateringParser;
import sdsu.apps.sdsudining.parse.ContactParser;
import sdsu.apps.sdsudining.parse.ContactsParser;
import sdsu.apps.sdsudining.parse.SweetParser;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
				//new ContactsParser(this.getString(R.string.CONTACTS_URL), this.getApplicationContext());
				
				
				new ContactParser(this.getString(R.string.CONTACT_URL), this.getApplicationContext());
				new SweetParser(this.getString(R.string.SWEET_URL), this.getApplicationContext());
				new CateringParser(this.getString(R.string.CATERING_URL), this.getApplicationContext());
				//new BrowseByLocationParser("http://api.androidhive.info/contacts/", this.getApplicationContext());

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



		setContentView(R.layout.activity_home);

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
			ArrayList<String> titles = new ArrayList<String>();
			titles.add(getResources().getString(R.string.contact));
			titles.add(getResources().getString(R.string.information));
			titles.add(getResources().getString(R.string.getting_started));
			
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
			titles.add(getResources().getString(R.string.menu));
			titles.add(getResources().getString(R.string.brochure));
			
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
			titles.add(getResources().getString(R.string.address));
			
			Intent intent = new Intent(HomeActivity.this, DetailsActivity.class);
			intent.putExtra("labelString", getResources().getString(R.string.contactUsString));
			intent.putExtra("titles", titles);
			startActivity(intent);
		}

	};

}
