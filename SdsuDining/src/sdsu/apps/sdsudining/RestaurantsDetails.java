package sdsu.apps.sdsudining;

import sdsu.apps.sdsudining.util.RestaurantsPagerAdapter;
import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

/**
 * RestaurantsDetails: 
 * 	- Fetch data if app is in foreground
 *  - Load data from db
 *  - Display data for the restaurant location details fragments 
 * @author Priya Jayaprakash
 *
 */


public class RestaurantsDetails extends FragmentActivity implements ActionBar.TabListener {
	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	private RestaurantsPagerAdapter restaurantsPagerAdapter;

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
	protected void onResume(){
		super.onResume();
		boolean isForeground = SdsuDining.isAppStart();
		if(isForeground){
			Intent intent = new Intent(this, LoadingActivity.class);
			startActivityForResult(intent, 0);
		}
		
		Intent intent = getIntent();
		this.setTitle(intent.getStringExtra("labelString"));

		// Set up the action bar.
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayHomeAsUpEnabled(true);

		// Refresh with new data
		if(viewPager != null){
			viewPager.removeAllViews();
			actionBar.removeAllTabs();
			restaurantsPagerAdapter.notifyDataSetChanged();
		}
		
		restaurantsPagerAdapter = new RestaurantsPagerAdapter(getSupportFragmentManager(), intent.getStringExtra("labelString"), getApplicationContext());
		
		// Set up the ViewPager with the sections adapter.
		viewPager = (ViewPager) findViewById(R.id.restaurantsPager);
		viewPager.setAdapter(restaurantsPagerAdapter);
		
		/* When swiping between different sections, select the corresponding tab. We can also use 
		 * ActionBar.Tab#select() to do this if we have a reference to the Tab.
		 */
		viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				actionBar.setSelectedNavigationItem(position);
			}
		});
		
		// For each of the sections in the app, add a tab to the action bar.
		for (int i = 0; i < restaurantsPagerAdapter.getCount(); i++) {
			/* Create a tab with text corresponding to the page title defined by the adapter. Also specify 
			 * this Activity object, which implements the TabListener interface, as the callback (listener) for when
			 * this tab is selected.
			 */
			actionBar.addTab(actionBar.newTab().setText(restaurantsPagerAdapter.getPageTitle(i)).setTabListener(this));
		}
		
		viewPager.setCurrentItem(restaurantsPagerAdapter.getPageTitlePosition(intent.getStringExtra("autoSelectTab")));
	}
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_restaurants_details);
		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			return true;
		}
		return false;
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return false;
	}


	@Override
	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		// When the given tab is selected, switch to the corresponding page in the ViewPager.
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
