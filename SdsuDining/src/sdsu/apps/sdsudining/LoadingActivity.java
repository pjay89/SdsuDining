package sdsu.apps.sdsudining;

import java.util.Observable;
import java.util.Observer;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

import sdsu.apps.sdsudining.database.SdsuDBHelper;
import sdsu.apps.sdsudining.gcm.Configurations;
import sdsu.apps.sdsudining.gcm.RegIdRegistrar;
import sdsu.apps.sdsudining.parse.CateringParser;
import sdsu.apps.sdsudining.parse.ContactParser;
import sdsu.apps.sdsudining.parse.CouponsParser;
import sdsu.apps.sdsudining.parse.FarmersParser;
import sdsu.apps.sdsudining.parse.HoursParser;
import sdsu.apps.sdsudining.parse.RestaurantsParser;
import sdsu.apps.sdsudining.parse.SweetParser;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

public class LoadingActivity extends Activity implements Observer{

	private int count = 0;
	private static int TOTAL_PARSERS_COUNT = 7;
	private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
	
	
	private static final String TAG = "SDSU_GCM";


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loading);
		
		if(checkGooglePlayServices()){
			SdsuDBHelper db = new SdsuDBHelper(getApplicationContext());
			if(db.getGCMRegId().equals("")){
				Log.i(TAG, "GCM regId is blank. Calling registerInBackground");
				registerInBackground();
			}
			db.close();
		}
	}

	@Override
	protected void onResume(){
		super.onResume();
		
		// You need to do the Play Services APK check here too.
		checkGooglePlayServices();

		Log.i("STATE TEST", "LOADING ONRESUME");
		if(SdsuDining.isNetworkConnected(this)){
			new HoursParser(this.getString(R.string.HOURS_URL), this.getApplicationContext(), this);
			new FarmersParser(this.getString(R.string.FARMERS_URL), this.getApplicationContext(), this);
			new ContactParser(this.getString(R.string.CONTACT_URL), this.getApplicationContext(), this);
			new SweetParser(this.getString(R.string.SWEET_URL), this.getApplicationContext(), this);
			new CateringParser(this.getString(R.string.CATERING_URL), this.getApplicationContext(), this);
			new CouponsParser(this.getString(R.string.COUPON_URL), this.getApplicationContext(), this);
			new RestaurantsParser(this.getString(R.string.RESTAURANTS_URL), this.getApplicationContext(), this);
		}
		else{
			hideProgressBar();
			
			final AlertDialog alert = new AlertDialog.Builder(this).create();
			alert.setMessage("No Internet Connection\nFailed to Fetch Data from Server");
			alert.setCanceledOnTouchOutside(false);
			alert.setCancelable(false);
			alert.setButton(DialogInterface.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					alert.cancel();
					navigateToActivity();
				}
			});
			alert.show();
		}

	}

	@Override
	public void update(Observable observable, Object data) {
		if(data.equals(getString(R.string.parserObserverComplete))){
			count++;
		}
		
		if(count == TOTAL_PARSERS_COUNT){
			navigateToActivity();
		}
	}
	
	private void hideProgressBar(){
		ProgressBar progressBar = (ProgressBar) findViewById(R.id.loadingProgressBar);
		progressBar.setVisibility(View.GONE);
	}

	private void navigateToActivity(){
		/*SdsuDining.setFalse();
		
		Intent notificationIntent = getIntent();
		
		Intent intent = new Intent(getApplicationContext(), MainActivity.class);
		intent.putExtra(Configurations.MESSAGE_KEY, notificationIntent.getStringExtra(Configurations.MESSAGE_KEY));
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
		finish();*/
		
		SdsuDining.setFalse();
		count = 0;
		
		Intent notificationIntent = getIntent();
		String notificationMessage = notificationIntent.getStringExtra(Configurations.MESSAGE_KEY);
		
		// If LoadingActivity is called by GCM notification, clear backstack, navigate to HomeActivity, and display message
		// Else navigate to backstack activity
		if(notificationMessage != null){
			Log.i("STATE TEST", "entered notif msg");
			
			Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
			intent.putExtra(Configurations.MESSAGE_KEY, notificationMessage);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(intent);
			finish();
		}
		else{
			Log.i("STATE TEST", "entered else");			

			finish();
		}
		
	}
	
	private boolean checkGooglePlayServices(){
		int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
		if(resultCode != ConnectionResult.SUCCESS){
			if(GooglePlayServicesUtil.isUserRecoverableError(resultCode)){
				GooglePlayServicesUtil.getErrorDialog(resultCode, this, PLAY_SERVICES_RESOLUTION_REQUEST).show();
			}
			else{
				Log.e(TAG, "This device is not supported");
				finish();
			}
			return false;
		}
		return true;
	}
	
	private void registerInBackground(){
		RegIdRegistrar registrar = new RegIdRegistrar(getApplicationContext());
		registrar.register();
	}

}
