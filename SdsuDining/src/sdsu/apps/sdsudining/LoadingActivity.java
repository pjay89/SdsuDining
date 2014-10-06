package sdsu.apps.sdsudining;

import java.util.Observable;
import java.util.Observer;

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


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loading);

	}

	@Override
	protected void onResume(){
		super.onResume();

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
			ProgressBar progressBar = (ProgressBar) findViewById(R.id.loadingProgressBar);
			progressBar.setVisibility(View.GONE);

			final AlertDialog alert = new AlertDialog.Builder(this).create();
			alert.setMessage("No Internet Connection\nFailed to Fetch Data from Server");
			alert.setCanceledOnTouchOutside(false);
			alert.setCancelable(false);
			alert.setButton(DialogInterface.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					alert.cancel();
					navigateToHome();
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
			navigateToHome();
		}
	}
	

	private void navigateToHome(){
		SdsuDining.setFalse();
		Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
		finish();
	}
}
