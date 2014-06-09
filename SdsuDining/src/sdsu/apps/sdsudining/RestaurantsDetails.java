package sdsu.apps.sdsudining;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

public class RestaurantsDetails extends FragmentActivity implements ActionBar.TabListener {

	
	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	private ViewPager viewPager;
	
	@Override
	protected void onPause(){
		super.onPause();
		SdsuDining.appStatus(this);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_restaurants_details);
		Intent intent = getIntent();
		this.setTitle(intent.getStringExtra("labelString"));
	
		// Set up the action bar.
				final ActionBar actionBar = getActionBar();
				actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
				actionBar.setDisplayHomeAsUpEnabled(true);

	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			Intent intent = new Intent(this, HomeActivity.class);
			startActivity(intent);
			overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
			finish();
			return true;
		}
		return false;
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detail, menu);
		return true;
	}
	

	@Override
	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		// When the given tab is selected, switch to the corresponding page in
		// the ViewPager.
		viewPager.setCurrentItem(tab.getPosition());

	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction){
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

}
